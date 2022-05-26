# <img src="resources/MAQS.jpg" height="32" width="32"> Playwright Configuration

## Overview
The PlaywrightConfig class is used to get values from the PlaywrightMaqs section of your test run properties.
<br>These values come from your config.xml and/or test run parameters.

## PlaywrightMaqs
The PlaywrightMaqs configuration section contains the following Keys:  
* ***WebBase*** : The base website url
* ***BrowserType*** : Enum representing the desire browser
* ***BrowserName*** : String representing the desire browser  
*This value should only be used for validation, MAQS uses 'GetBrowserType'
* ***Headless*** : If we want Playwright tests to be run headless
* ***CommandTimeout*** : How long wait before saying the connection to Playwright has died
* ***TimeoutTime*** : How long to wait for something before timing out
* ***CaptureVideo*** : If you want to capture video
* ***CaptureScreenshots*** : If you want to capture screenshots  
*This option can bloat your test result, so be very careful about using it with big test runs
* ***CaptureSnapshots*** : If we want to capture snapshots (DOM and network activity) on each action
* ***BrowserSize*** : The browser resolution
* ***UseProxy*** : If the browser should use a proxy address
    * If this value is "YES" then ***ProxyAddress*** is required
* ***ProxyAddress*** : The proxy address and port the browser will use

## Available methods
Get the base web site url:
```csharp
String siteUrl = PlaywrightConfig.getWebBase();
```
Get the browser name:
```csharp
String browserName = PlaywrightConfig.getBrowserName();
```
Get a page driver type based on your configuration:
```csharp
PlaywrightBrowser type = PlaywrightConfig.getBrowserType();
```
Get a page driver type for the specified browser:
```csharp
BrowserType type = PlaywrightConfig.getBrowserType("Chrome");
```
Get if the browser should be headless:
```csharp
boolean headless = PlaywrightConfig.getHeadless();
```
Get the command timeout:
```csharp
double initTimeout = PlaywrightConfig.getCommandTimeout();
```
Get the search timeout:
```csharp
double findTimeout = PlaywrightConfig.getTimeoutTime();
```
Get if we capture video:
```csharp
boolean captureVideo = PlaywrightConfig.getCaptureVideo();
```
Get if we capture screenshots:
```csharp
boolean captureScreenshots = PlaywrightConfig.getCaptureScreenshots();
```
Get if we capture snapshots:
```csharp
boolean captureSnapshots = PlaywrightConfig.getCaptureSnapshots();
```

Get the if page driver should use proxy
```csharp
boolean useProxy = PlaywrightConfig.getUseProxy();
```
Get the proxy address to use
```csharp
String proxyAddress = PlaywrightConfig.getProxyAddress();
```

# Sample config files
## config.xml
```xml
<?xml version="1.0" encoding="utf-8" ?>
<configuration>
  <configSections>
    <section name="PlaywrightMaqs" type="System.Configuration.NameValueSectionHandler"/>
    <section name="GlobalMaqs" type="System.Configuration.NameValueSectionHandler" />
  </configSections>
	<PlaywrightMaqs>
		<!--Default base web url-->
		<add key="WebBase" value="https://cognizantopensource.github.io/maqs-dotnet-templates/Static/Automation/" />

		<!--Local browser settings
		<add key="Browser" value="Chrome"/>
		<add key="Browser" value="Chromium"/>
		<add key="Browser" value="Firefox"/>
		<add key="Browser" value="Edge"/>
		<add key="Browser" value="Webkit"/>-->
		<add key="Browser" value="Chromium" />
		<add key="Headless" value="YES" />

		<!--Playwright specific timeouts in milliseconds-->
		<add key="Timeout" value="20000" />
		<add key="CommandTimeout" value="200000" />

		<!--Playwright specific logging options-->
		<add key="CaptureVideo" value="No" />
		<add key="CaptureScreenshots" value="No" />
		<add key="CaptureSnapshots" value="No" />

		<!--Browser Resize settings - The Default is 1280x720
		<add key="BrowserSize" value="DEFAULT" />
		<add key="BrowserSize" value="300x300" />-->

		<!--Proxy  settings-->
		<add key="UseProxy" value="No" />
		<add key="ProxyAddress" value="http://localhost:8002" />
	</PlaywrightMaqs>
  <GlobalMaqs>
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
  </GlobalMaqs>
</configuration>
```