# Appium Mobile Automation Framework - The Score Andorid App

Framework for Mobile test automation Native app on Android devices and emulator

<img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/Architecture.png" width="500" height="300">
                       
                       Appium Architecture 
                       
 ## :pushpin: Test Scenario Scope for the demo

* Launch the score app and Verify the home screen (Welcome text)
* Click on "Get Started" > Navigates Legues screen > Click on Continue
* close the pop-up and display the Favorite Teams screen
* Select your favourite team (Toronto Maples Leafs) and verify the selected team(TOR)
* Click on the Player stats screen and verify the screen
* Click on the back navigation and verify the screen.

 ## Executed the  Scneario on real device and captured the Video: 
  
  https://github.com/vasudevus/ScoreAppAutomation/blob/main/ExtentReports_Score/recorded_video_score_2023-04-26-18-15-26.mp4
  

 ##  Device OS Scope Android 11& Above
 ##  Programming language: Java 11
 ##  IDE IntelliJ IDE 

 
                       
                       
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
      


## :pushpin: Android Real Device Set up:

1) Open Android Studio, Navigate to Device Manager
      
2) Select option to Pair Newdevice Over Wifi and Select Pair Using QR Code

    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/PairNewDevice.png" width="200" height="200">
    
    
3) Open your Camera and Scan QR code


    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/PairedDevice.png" width="200" height="200">
    
    
4) Click on Run Device - it will take take 2min to display the device


    <img src = "https://github.com/vasudevus/desktop-tutorial/blob/main/ConnectedDevice.png" width="200" height="200">
     

## :pushpin: Installing App on device

    1) Search your app and download from : https://m.apkpure.com/search?q=
    2) Save the app in Project folder//src//test//java//resources//app 
    3) Drage and drop the .apk file to the device.
    

##  :pushpin: Virtual device connection steps

1) Open Android Studio and configuration virtual device/Emulator: Open Studio 64 or Android Studio from C:\Program Files\Android\Android Studio\bin. 
2) Select ” Import Android Code Sample” ? Then choose any sample.  and click on next next until you reach on home window.
From Tools select Device Manager’ or Search ‘AVD Manager’>+Create Virtual Device>Select the device (ex: pixel 3)>

3)Select the Android version (Make sure to Download the release first; ex: Pie) and complete the download version>

4)click on Advance setting>Give AVD name (ex:Demo).>finish
5)click on launch to open the virtual mobile device

## :pushpin: Start Android Emulator from Command line

1) Open command prompt, go to `<sdk emulator path>`

```
Command to stard AVD: `emulator -avd <avd_name>`
Command to stop/kill AVD: `adb -e emu kill`
```

## :pushpin: Pushing the App (.apk file) to Android Emulator:

1) Copy the .apk file and paste it in the path - `<path to sdk platform-tools>`
2) Open the cmd terminal from the directory where APK file is placed and enter command `adb install <apk filename>`



## :pushpin: Inspecting Elements


### Appium Inspector : Inspecting the element on real device

1) Start the Appium Server and connect with Real device/Emulator.
2) Open Appium Inspector app and provide the appium server details and Desired Capabilities
3) Click on Start session which will start the appium inspector with layout shown below.

<img width="700" alt="Appium " src="https://github.com/vasudevus/desktop-tutorial/blob/main/AppiumInspectorSelectedElement.png">

## :pushpin: Launching Android Emulator Automatically

Add below lines in the Desired capabilities

```
capability.setCapability(AndroidMobileCapabilityType.AVD, "Pixel_3a");
capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT, "180000");
```

## :pushpin: Start Appium server programmatically

Use `AppiumServiceBuilder` and `AppiumDriverLocalService` to start the server programmatically Set environment
variable `APPIUM_HOME = <path to npm folder>\node_modules\appium\build\lib` where `main.js` file is present

## :pushpin: Execution:  Running tests from IDE

Select the ScoreApp.java and run the code >  /main/src/test/java/com/score/ScoreApp.java

<img width="700" alt="Extent Report " src="https://github.com/vasudevus/desktop-tutorial/blob/main/ProjectStructure.png">

## :pushpin: Running tests through testng xml 

:point_right: Create or Select the required testng xml > //main/testng.xml -> Right click and select Run

## :pushpin: Results Report (Extent reports)

<img width="700" alt="Extent Report " src="https://github.com/vasudevus/desktop-tutorial/blob/main/Screen%20Shot%202023-04-27%20at%2010.55.51%20AM.png">

