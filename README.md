# Appium Mobile Automation Framework - The Score Andorid App

Framework for Mobile test automation Native app on Android devices and emulator

<img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/Architecture.png" width="500" height="300">
                       
                       Appium Architecture 
                       
                       
## :rocket: Quick Start - Appium set up on Windows (Android):

1) Install [Java JDK11](https://www.oracle.com/ca-en/java/technologies/javase/jdk11-archive-downloads.html)
   and [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)
2) Install [NodeJS](https://nodejs.org/en/download/)
3) Install [Android studio](https://developer.android.com/studio)

      How to create or modify environment variables on Windows 10: 
      
      https://docs.oracle.com/en/database/oracle/machine-learning/oml4r/1.5.1/oread/creating-and-modifying-environment-variables-on-windows.html#GUID-DD6F9982-60D5-48F6-8270-A27EC53807D0
      
       Add below Android SDK path in the environment variable

        ```
            - ANDROID_HOME = <path to Sdk folder>
            - %ANDROID_HOME%\tools
            - %ANDROID_HOME%\tools\bin
            - %ANDROID_HOME%\platform-tools
            - 
   ## :pushpin: Appium server installation to verify the installation
   
1) Install Appium Server using npm (CLI) command `npm install -g appium`. Appium server version 1.22.3

```
Command to check the installed appium version: `appium --version`

```
2) Install appium-doctor using command `npm install -g appium-doctor`
3) To view the list of available options `appium-doctor --help`

```


## :pushpin: Appium inspector installation & appium desired capabilities

 Install [Appium Inspector-Appium-Inspector-windows-2023.4.2.exe] 
 
 download from(https://github.com/appium/appium-inspector/releases)
```

<img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/Appium%20Inspector.png" width="500" height="300">
      
      
         
## :pushpin: Creating Android Virtual Device (Emulator) from Android Studio:


## :pushpin: Android Real Device Set up:

1) Open Android Studio, Navigate to Device Manager
      
2) Select option to Pair Newdevice Over Wifi and Select Pair Using QR Code
    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/PairNewDevice.png" width="200" height="200">  
3) Open your Camera and Scan QR code
    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/PairedDevice.png" width="200" height="200">
4) Click on Run Device - it will take take 2min to display the device
    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/ConnectedDevice.png" width="200" height="200">
     

## :pushpin: Installing App on device

1) Drag app the app from 

## :pushpin: Start Android Emulator from Command line

1) Open command prompt, go to `<sdk emulator path>`

```
Command to stard AVD: `emulator -avd <avd_name>`
Command to stop/kill AVD: `adb -e emu kill`
```

## :pushpin: Pushing the App (.apk file) to Android Emulator:

1) Copy the .apk file and paste it in the path - `<path to sdk platform-tools>`
2) Open the cmd terminal from the directory where APK file is placed and enter command `adb install <apk filename>`

## :pushpin: Android - Finding appPackage and appActivity:

If the app is already installed on your device then we can make use of appPackage and appActivity to launch the app

<b> Option 1 : </b>
1) Open the app on the device, for which appPackage and appActivity is required.
2) Open powershell and enter command `adb shell dumpsys window | grep -E 'mCurrentFocus|mFocusedApp'`
NOTE: This command may not work for newer Android OS (10 or 11). In that case, use command:
   `adb shell "dumpsys activity activities | grep mResumedActivity"`

<b> Option 2 : </b>
Install <b> APK info </b> app to retrieve appPackage and appActivity for the app installed in your device

## :pushpin: Inspecting Elements

### uiautomatorviewer

1) Go to the path - `<path to sdk folder>\tools\bin\`
2) click on `uiautomatorviewer`
3) On the UI Automator Viewer, click on Device Screenshot (uiautomator dump). Ui automator will capture the screenshot
   of current open screen in the device.

<img width="700" height="450" alt="UiAutomatorViewer" src="https://user-images.githubusercontent.com/48508827/136441564-56869f6b-d220-4114-b00b-1312957f3d5b.png">

### Appium Inspector

1) Start the Appium Server and connect with Real device/Emulator.
2) Open Appium Inspector app and provide the appium server details and Desired Capabilities.

<img width="700" alt="Appium Inspector" src="https://user-images.githubusercontent.com/48508827/136668610-7507a27d-1304-490a-b954-687d3bf6caa2.png">

3) Click on Start session which will start the appium inspector with layout shown below.

<img width="700" alt="Appium " src="https://user-images.githubusercontent.com/48508827/136668632-343aab0d-9eef-4a2d-beee-fa35825f361a.png">

## :pushpin: Inspecting Element for mobile web browser

```
Type url `chrome://inspect/#devices` in the desktop chrome browser and start inspecting element
```

<img width="800" alt="Capture" src="https://user-images.githubusercontent.com/48508827/146682499-00cb158a-5f9b-4daf-87ba-ca197b2804de.PNG">

## :pushpin: Launching Android Emulator Automatically

Add below lines in the Desired capabilities

```
capability.setCapability(AndroidMobileCapabilityType.AVD, "Pixel_3a");
capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, "180000");
```

## :pushpin: Auto Discovery of compatible ChromeDriver

Start appium server using command `appium --allow-insecure chromedriver_autodownload`

## :pushpin: Auto download of compatible ChromeDriver programmatically

Add below line in the `AppiumServiceBuilder`

```
AppiumServiceBuilder builder = new AppiumServiceBuilder();
builder.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload");
```

## :pushpin: Start Appium server programmatically

Use `AppiumServiceBuilder` and `AppiumDriverLocalService` to start the server programmatically Set environment
variable `APPIUM_HOME = <path to npm folder>\node_modules\appium\build\lib` where `main.js` file is present

## :pushpin: Key Features

:point_right: Supports Android and Emulators.

:point_right: Supports capturing appium server logs on run-time.

:point_right: Page object model design.


:point_right: Ability to capture screen(video) recording of tests on Android and iOS. Configurable
through `config.properties`

:point_right: Supports capturing screenshots for passed/failed/skipped steps which is configurable
through `config.properties`

:point_right: Ability to retry failed tests which is configurable through `config.properties`

:point_right: Customised exception handling to provide the exceptions in a meaningful way.

:point_right: Custom framework annotation to provide author name and category for each test.

:point_right: Supports utilities to read test data from excel workbook and provides data to each test based on the test
name.

## :pushpin: Running tests through Maven

:point_right: Run test using command `mvn test -Dsurefire.suiteXmlFiles=<provide the testng xml to execute>`

## :pushpin: Running tests through testng xml

:point_right: Create or Select the required testng xml -> Right click and select Run

## :pushpin: Custom Configurations in config.properties

<img width="550" alt="Capture" src="https://user-images.githubusercontent.com/48508827/150834643-8c995b84-ffb7-4f0e-9ccd-4d1019440f2a.PNG">

## :pushpin: Report (Extent reports)

![Web capture_24-1-2022_224531_](https://user-images.githubusercontent.com/48508827/150834585-bf17de21-9e56-494c-b0f6-9ba8451638e6.jpeg)

![Web capture_24-1-2022_224634_](https://user-images.githubusercontent.com/48508827/150834616-3c15ee3a-67cd-4e90-90c7-1f664848fd82.jpeg)
