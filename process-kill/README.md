该 module 为 进程篇的示例
- 主要包含各种杀进程的方法
- 由于杀进程需要相应权限，因此该 module 只在特定编译的 ROM 中生效
- manifest 中配置了android:sharedUserId="android.uid.system"
- 签名文件使用了自己编译 ROM 的系统签名
- 为了调用 @hide 方法，引入了 framework.jar
