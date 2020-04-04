# <img src="resources/maqslogo.ico" height="32" width="32"> Appium FAQ

## What Browsers can I use?
- Any browser that has a IWebDriver.  
If you want to use a browser that JMAQS doesn't natively support you can just initialize the driver yourself.
```java
this.setWebDriver(new OperaDriver("path_to_opera_driver.exe"));
```
- Natively supported browsers are:  Firefox | Chrome | Edge | PhantomJS | Internet Explorer. 
Find the configuration of browsers within the app.config file and define one and only one.

### How do I fix common user errors?

### My code can't find the config class  
```java
// TODO: finish
```
### Test can't find the page element for a test, errors indicate something is wrong with Selenium  
- Kill the existing Chrome processes spawned and orphaned from the test kick off then update Browser and start fresh
### Test fails on driver, such as Chrome  
- Update the Chrome browser  (In Chrome click the HotDog icon (formerly known as Wrench) > help > about Google Chrome > Update , the key is that the Chrome browser version and the ChromeDriver versions must be compatible with each other.

## Build Solution Fails
```java
//TODO: finish
```

## Driver doesn't want to run
- You should receive a pop-up stating Windows Firewall blocked some features of this app.  Enable Private networks, such as my home or work network and Enable Public networks, such as those in airports and coffee shops (note this is not recommended) Finally, Click Allow Access.