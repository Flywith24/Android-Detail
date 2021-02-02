dependencies {
    api(AndroidX.coreKtx)
    api(AndroidX.appcompat)
    api(AndroidX.constraintlayout)
    api(Google.material)

    /*阅读源码使用*/
    compileOnly(AndroidX.Fragment.fragment)
    compileOnly(ThirdParty.permission)
    compileOnly(ThirdParty.retrofit)
    compileOnly(ThirdParty.adapterRxjava2)
    compileOnly(ThirdParty.adapterRxjava3)
    compileOnly(ThirdParty.converterGson)
    compileOnly(ThirdParty.converterMoshi)
    compileOnly(ThirdParty.converterProtobuf)
    compileOnly(ThirdParty.okhttp)
}