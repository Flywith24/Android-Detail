dependencies {
    api(AndroidX.coreKtx)
    api(AndroidX.appcompat)
    api(AndroidX.constraintlayout)
    api(Google.material)
    api(AndroidX.Fragment.fragmentKtx)

    implementation("com.bytedance.tools.codelocator:codelocator-core:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-xml:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-activity:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-view:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-toast:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-dialog:1.0.0")
    debugImplementation("com.bytedance.tools.codelocator:codelocator-lancet-popup:1.0.0")

    /*阅读源码使用*/
    compileOnly(AndroidX.Fragment.fragmentKtx)
    compileOnly(AndroidX.coreKtx)
    compileOnly(ThirdParty.permission)
    compileOnly(ThirdParty.retrofit)
    compileOnly(ThirdParty.adapterRxjava2)
    compileOnly(ThirdParty.adapterRxjava3)
    compileOnly(ThirdParty.converterGson)
    compileOnly(ThirdParty.converterMoshi)
    compileOnly(ThirdParty.converterProtobuf)
    compileOnly(ThirdParty.okhttp)
}