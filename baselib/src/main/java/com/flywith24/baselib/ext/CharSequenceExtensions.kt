package com.flywith24.baselib.ext

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.*
import android.view.View
import androidx.annotation.ColorInt
import java.util.*
import java.util.regex.Pattern

/**
 * Provides [String.formatSpanned] style functions that work with [Spanned] strings and preserve formatting.
 *
 * @see [Original source](https://github.com/george-steel/android-utils/blob/master/src/org/oshkimaadziig/george/androidutils/SpanFormatter.java)
 *
 * @see [Modifications from Android Developers website](http://developer.android.com/guide/topics/resources/string-resource.html)
 *
 *
 * Created by tj.dahunsi on 5/20/17.
 */

private val FORMAT_SEQUENCE =
    Pattern.compile("%([0-9]+\\$|<?)([^a-zA-z%]*)([[a-zA-Z%]&&[^tT]]|[tT][a-zA-Z])")
private const val CONCATENATE_FORMATTER = "%1\$s%2\$s"

operator fun CharSequence.plus(other: CharSequence): SpannableStringBuilder =
    CONCATENATE_FORMATTER.formatSpanned(this, other)

fun <T : CharacterStyle> CharSequence.applyStyles(vararg styles: T): CharSequence =
    this.applyTags(*styles)

fun CharSequence.bold(): CharSequence = applyStyles(StyleSpan(Typeface.BOLD))

fun CharSequence.italic(): CharSequence = applyStyles(StyleSpan(Typeface.ITALIC))

fun CharSequence.underline(): CharSequence = applyStyles(UnderlineSpan())

fun CharSequence.scale(relativeSize: Float): CharSequence =
    applyStyles(RelativeSizeSpan(relativeSize))

fun CharSequence.scaleX(relativeSize: Float): CharSequence = applyStyles(ScaleXSpan(relativeSize))

fun CharSequence.backgroundColor(@ColorInt color: Int): CharSequence =
    applyStyles(BackgroundColorSpan(color))

fun CharSequence.strikeThrough(): CharSequence = applyStyles(StrikethroughSpan())

fun CharSequence.superScript(): CharSequence = applyStyles(SuperscriptSpan())

fun CharSequence.subScript(): CharSequence = applyStyles(SubscriptSpan())

fun CharSequence.color(@ColorInt color: Int): CharSequence = applyStyles(ForegroundColorSpan(color))

fun CharSequence.shiftBaseline(ratio: Float): CharSequence = applyStyles(BaselineShiftSpan(ratio))

fun CharSequence.click(
    paintConsumer: (TextPaint) -> Unit = {},
    clickAction: () -> Unit
): CharSequence = this.applyTags(object : ClickableSpan() {
    override fun onClick(widget: View) = clickAction.invoke()

    override fun updateDrawState(paint: TextPaint) = paintConsumer.invoke(paint)
})

/**
 * Version of [String.format] that works on [Spanned] strings to preserve rich text formatting.
 * Both the `format` as well as any `%s args` can be Spanned and will have their formatting preserved.
 * Due to the way [Spannable]s work, any argument's spans will can only be included **once** in the result.
 * Any duplicates will appear as text only.
 *
 * @param args   the list of arguments passed to the formatter. If there are
 * more arguments than required by `format`,
 * additional arguments are ignored.
 * @see [java.util.Formatter.format]
 * @return the formatted string (with spans).
 */
fun CharSequence.formatSpanned(vararg args: Any): SpannableStringBuilder =
    formatActual(Locale.getDefault(), this, *args)

/**
 * Applies a list of zero or more tags to the entire range in the CharSequence.
 *
 * @param tags    the styled span objects to apply to the content
 * such as android.text.style.StyleSpan
 */
private fun CharSequence.applyTags(vararg tags: Any): CharSequence {
    val text = SpannableStringBuilder()
    openTags(text, tags)

    text.append(this)

    closeTags(text, tags)
    return text
}

/**
 * Iterates over an array of tags and applies them to the beginning of the specified
 * Spannable object so that future text appended to the text will have the styling
 * applied to it. Do not call this method directly.
 */
private fun openTags(text: Spannable, tags: Array<out Any>) {
    for (tag in tags) text.setSpan(tag, 0, 0, Spannable.SPAN_MARK_MARK)
}

/**
 * "Closes" the specified tags on a Spannable by updating the spans to be
 * endpoint-exclusive so that future text appended to the end will not take
 * on the same styling. Do not call this method directly.
 */
private fun closeTags(text: Spannable, tags: Array<out Any>) {
    val len = text.length
    for (tag in tags)
        if (len > 0) text.setSpan(tag, 0, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        else text.removeSpan(tag)
}

/**
 * Version of [String.formatSpanned] that works on [Spanned] strings to preserve rich text formatting.
 * Both the `format` as well as any `%s args` can be Spanned and will have their formatting preserved.
 * Due to the way [Spannable]s work, any argument's spans will can only be included **once** in the result.
 * Any duplicates will appear as text only.
 *
 * @param locale the locale to apply; `null` value means no localization.
 * @param format the format string (see [java.util.Formatter.format])
 * @param args   the list of arguments passed to the formatter.
 * @return the formatted string (with spans).
 * @see String.formatSpanned
 */
private fun formatActual(
    locale: Locale,
    format: CharSequence,
    vararg args: Any
): SpannableStringBuilder {
    val out = SpannableStringBuilder(format)

    var start = 0
    var argAt = -1

    while (start < out.length) {
        val matcher = FORMAT_SEQUENCE.matcher(out)
        if (!matcher.find(start)) break

        start = matcher.start()
        val exprEnd = matcher.end()

        val argTerm = matcher.group(1)
        val modTerm = matcher.group(2)
        val typeTerm = matcher.group(3)

        val cookedArg: CharSequence = when (typeTerm) {
            "%" -> "%"
            "n" -> "\n"
            else -> {
                val argIdx: Int = when (argTerm) {
                    "" -> ++argAt
                    "<" -> argAt
                    else -> Integer.parseInt(argTerm!!.substring(0, argTerm.length - 1)) - 1
                }

                val argItem = args[argIdx]

                if (typeTerm == "s" && argItem is Spanned) argItem
                else String.format(locale, "%$modTerm$typeTerm", argItem)
            }
        }

        out.replace(start, exprEnd, cookedArg)
        start += cookedArg.length
    }

    return out
}