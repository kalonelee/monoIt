# monoIt
Add a tile to quicksettings for switch monochrome (grayscale) screen mode on Android, help you sleep better during the night.

添加一个磁贴到快速设置界面，点击即可把屏幕切换至黑白（灰度）模式，帮助你速速进入梦乡。

## Usage
1. Install the apk downloaded from release or build by yourself.
2. Grant Write Secure Settings Permission.
- With adbtool
  - Install adbtool on your PC.
  - Enable USB debugging in Developer Options.
  - Link your phone to your PC.
  - Run following command.
  ```
  adb shell pm grant net.kalone.monochrome android.permission.WRITE_SECURE_SETTINGS
  ```
  
- With Terminal Emulator on your Device **[Need Root]**
  - run following command in Terminal Emulator
  ```
  su
  pm grant net.kalone.monochrome android.permission.WRITE_SECURE_SETTINGS 
  ```
3. Add tile to Quicksettings Panel

## 使用方法
1. 安装应用 
  你可以从发布界面下载，也可以自行编译。
2. 授予 安全设置 权限
- 用 adbtool
  - 在电脑上安装 adbtool
  - 在 Android 设备 的 开发者设置 中启用 USB调试
  - 把你的 Android 设备连到电脑
  - 运行下述代码
  ```
  adb shell pm grant net.kalone.monochrome android.permission.WRITE_SECURE_SETTINGS
  ```
  
- 使用Android设备上的终端程序 **[需要 Root]**
  - 在终端模拟器中运行下述代码
  ```
  su
  pm grant net.kalone.monochrome android.permission.WRITE_SECURE_SETTINGS 
  ```
3. 把磁贴添加到快速设置面板
