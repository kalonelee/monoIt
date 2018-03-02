# monoIt
Add a tile to quicksettings for switch monochrome screen mode on Android.

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
