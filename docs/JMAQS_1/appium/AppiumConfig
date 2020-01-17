# <img src="resources/maqslogo.ico" height="32" width="32"> AppiumConfig
Appium Configuration class

## Inheritance Hierarchy
```java
com.magenic.jmaqs.appium.AppiumConfig
```
Package: com.magenic.jmaqs.appium;  
Assembly: import com.magenic.jmaqs.appium.AppiumConfig

## Syntax
Java
```java
public class AppiumTestObject
```
The AppiumTestObject type exposes the following members.

## Methods
These values come from your config.xml, appsettings.json and/or test run parameters.  

[GetPlatformName](#GetPlatformName) - Gets the platform name  
[GetPlatformVersion](#GetPlatformVersion) - Gets the Appium platform version    
[GetDeviceName](#GetDeviceName) - Get the Device Name  
[GetSavePageSourceOnFail](#GetSavePageSourceOnFail) - Get if we should save page source on fail  
[GetSoftAssertScreenShot](#GetSoftAssertScreenShot) - Get if we should save screenshots on soft alert fails    
[GetMobileHubUrlString](#GetMobileHubUrlString) - Get the mobile hub url as a string  
[GetMobileHubUrl](#GetMobileHubUrl)  - Get the mobile hub url   
[GetCommandTimeout](#GetCommandTimeout)  - Get the initialize Appium timeout timespan    
[GetMobileTimeout](#GetMobileTimeout)  - Get the mobile timeout  
[GetCapabilitiesAsStrings](#GetCapabilitiesAsStrings)  - Gets capabilities as strings   
[GetCapabilitiesAsObjects](#GetCapabilitiesAsObjects)  - Gets capabilities as objects   
[GetDeviceType](#GetDeviceType)  - Gets the Device Type  

## GetPlatformName
Get the platform name
#### Written as
```java
static String getPlatformName()
```
#### Example
```java
String driverName = SeleniumConfig.getPlatformName();
```

## GetPlatformVersion
Get the platform version
#### Written as
```java
static String getPlatformVersion()
```
#### Example
```java
String driverName = SeleniumConfig.getPlatformVersion();
```

## GetDeviceName
Get the device name
#### Written as
```java
static String getDeviceName()
```
#### Example
```java
String driver = SeleniumConfig.getDeviceName());
```

## GetSavePageSourceOnFail
Get save page source on fail
#### Written as
```java
static boolean getSavePageSourceOnFail()
```
#### Example
```java
boolean save = SeleniumConfig.getSavePageSourceOnFail();
```

## GetSoftAssertScreenShot
Get soft assert screen shot
#### Written as
```java
static boolean getSoftAssertScreenShot()
```
#### Example
```java
boolean getScreenshot = SeleniumConfig.getSoftAssertScreenShot();
```

## GetMobileHubUrlString
Gets the mobile hub url string
#### Written as
```java
static String getMobileHubUrlString()
```
#### Example
```java
String url = SeleniumConfig.getMobileHubUrlString();
```

## GetMobileHubUrl
Get the mobile hub url
#### Written as
```java
static URL getMobileHubUrl()
```
#### Example
```java
URL url = SeleniumConfig.getMobileHubUrl();
```

## GetCommandTimeout
Get the command timeout:
#### Written as
```java
static Duration getCommandTimeout()
```
#### Example
```java
Duration timeout = SeleniumConfig.getCommandTimeout();
```

## GetMobileTimeout
Get the mobile timeout
#### Written as
```java
static Duration getMobileTimeout()
```
#### Example
```java
Duration timeout = SeleniumConfig.getMobileTimeout();
```

## GetCapabilitiesAsStrings
Gets capabilities as strings
## Written as
```java
static Map<String, String> getCapabilitiesAsStrings()
```
#### Example
```java
Map<String, String> Capabilities = SeleniumConfig.getCapabilitiesAsStrings();
```

## GetCapabilitiesAsObjects
Gets capabilities as objects
#### Written as
```java
static Map<String, Object> getCapabilitiesAsObjects()
```
#### Example
```java
Map<String, Object> Capabilities = SeleniumConfig.getCapabilitiesAsObjects();
```

## GetDeviceType
Get the device type
#### Written as
```java
static PlatformType getDeviceType()
```
#### Example
```java
PlatformType platform = SeleniumConfig.getDeviceType();
```

## AppiumMaqs
The AppiumMaqs configuration section contains the following Keys:  
* ***PlatformName*** : The platform type being used
* ***PlatformVersion*** : The version of the platform type being used
* ***DeviceName*** : Name of the mobile type
* ***MobileHubUrl*** : The grid URL
* ***MobileCommandTimeout*** : How long wait before saying the connection to Selenium has died
* ***MobileWaitTime*** : How long to wait before rechecking for something - Used heavily with the MAQS waits
* ***MobileTimeout*** : How long to wait for something before timing out - Used heavily with the MAQS waits
* ***SoftAssertScreenshot*** : Determines if a screenshot is taken when a Soft Assert Fails
* ***ImageFormat*** : What format screenshot should be saved as
* ***SavePageSourceOnFail*** : If page source is saved when a test fails

## AppiumCapsMaqs
These are key value pairs that get added to the remote web driver's desired capabilities.
<br>The AppiumCapsMaqs configuration section contains the following Keys:  
* ***Username*** : Name of the user
* ***AccessKey*** : The key to access the testing device
* ***DeviceOrientation*** : The screen orientation of the device (portrait or auto-rotate)
* ***BrowserName*** : Name of the Browser being used

## Sample config files
#### config.xml
```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
   <AppiumMaqs>
          <!-- Device settings -->
          <PlatformName>Android</PlatformName>
          <PlatformVersion>6.0</PlatformVersion>
          <DeviceName>Android GoogleAPI Emulator</DeviceName>
  
          <!-- Appium or grid connection -->
          <MobileHubUrl>http://ondemand.saucelabs.com:80/wd/hub</MobileHubUrl>
  
          <!-- Command time-out in milliseconds -->
          <MobileCommandTimeout>122000</MobileCommandTimeout>
  
          <!-- Wait time in milliseconds - AKA how long do you wait for rechecking something -->
          <MobileWaitTime>1000</MobileWaitTime>
  
          <!-- Time-out in milliseconds -->
          <MobileTimeout>10000</MobileTimeout>
  
          <!-- Do you want to take screenshots upon Soft Assert Failures
          <SoftAssertScreenshot>YES</SoftAssertScreenshot>
          <SoftAssertScreenshot>NO</SoftAssertScreenshot> -->
          <SoftAssertScreenshot>NO</SoftAssertScreenshot>
  
          <!-- Screenshot Image Formats
          <ImageFormat>Bmp</ImageFormat>
          <ImageFormat>Gif</ImageFormat>
          <ImageFormat>Jpeg</ImageFormat>
          <ImageFormat>Png</ImageFormat>
          <ImageFormat>Tiff</ImageFormat>-->
          <ImageFormat>Png</ImageFormat>
  
          <!-- Do you want to save page source when a Soft Assert fails
          <SavePageSourceOnFail>YES</SavePageSourceOnFail>
          <SavePageSourceOnFail>NO</SavePageSourceOnFail>-->
          <SavePageSourceOnFail>NO</SavePageSourceOnFail>
      </AppiumMaqs>
  <AppiumCapsMaqs>
          <username>Partner_Magenic</username>
          <accessKey>0a4d7d84-f93b-43e6-9af1-1c89ac143355</accessKey>
          <deviceOrientation>portrait</deviceOrientation>
          <browserName>Chrome</browserName>
  </AppiumCapsMaqs>
  <MagenicMaqs>
    <!-- Generic wait time in milliseconds - AKA how long do you wait for rechecking something -->
    <add key="WaitTime" value="1000" />

    <!-- Generic time-out in milliseconds -->
    <add key="Timeout" value="10000" />

    <!-- Do you want to create logs for your tests
    <add key="Log" value="YES"/>
    <add key="Log" value="NO"/>
    <add key="Log" value="OnFail"/>-->
    <add key="Log" value="OnFail" />

    <!--Logging Levels
    <add key="LogLevel" value="VERBOSE"/>
    <add key="LogLevel" value="INFORMATION"/>
    <add key="LogLevel" value="GENERIC"/>
    <add key="LogLevel" value="SUCCESS"/>
    <add key="LogLevel" value="WARNING"/>
    <add key="LogLevel" value="ERROR"/>-->
    <add key="LogLevel" value="INFORMATION" />

    <!-- Logging Types
    <add key="LogType" value="CONSOLE"/>
    <add key="LogType" value="TXT"/>
    <add key="LogType" value="HTML"/>-->
    <add key="LogType" value="TXT" />

    <!-- Log file path - Defaults to build location if no value is defined
    <add key="FileLoggerPath" value="C:\Frameworks\"/>-->
  </MagenicMaqs>
</configuration>
```
### appsettings.json
```json
{
  "SeleniumMaqs": {
    "PlatformName": "Android",
    "PlatformVersion": "6.0",
    "DeviceName": "Android GoogleAPI Emulator",
    "MobileHubUrl": "http://ondemand.saucelabs.com:80/wd/hub",
    "MobileCommandTimeout": "122000",
    "MobileWaitTime": "1000",
    "MobileTimeout": "10000",
    "BrowserSize": "MAXIMIZE",
    "SoftAssertScreenshot": "NO",
    "ImageFormat": "Png",
    "SavePagesourceOnFail": "NO"
  },
  "RemoteSeleniumCapsMaqs": {
    "username": "Partner_Magenic",
    "accessKey": "0a4d7d84-f93b-43e6-9af1-1c89ac143355",
    "deviceOrientation": "portrait",
    "browserName": "Chrome"
  },
  "MagenicMaqs": {
    "WaitTime": "100",
    "Timeout": "10000",
    "Log": "OnFail",
    "LogLevel": "INFORMATION",
    "LogType": "TXT"
  }
}
```