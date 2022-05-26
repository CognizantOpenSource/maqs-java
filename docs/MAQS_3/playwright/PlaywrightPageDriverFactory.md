# <img src="resources/MAQS.jpg" height="32" width="32"> Playwright Driver Factory

## Overview
Takes care of the Playwright driver set up, teardown and optimization.

[GetDefaultPageDriver](#GetDefaultPageDriver)  
[GetBrowserWithDefaults](#GetBrowserWithDefaults)  
[GetPageDriverFromBrowser](#GetPageDriverFromBrowser)  
[GetNewPageDriverFromBrowserContext](#GetNewPageDriverFromBrowserContext)  
[GetDefaultOptions](#GetDefaultOptions)  
[GetDefaultChromeOptions](#GetDefaultChromeOptions)  
[GetDefaultEdgeOptions](#GetDefaultEdgeOptions)  
[GetChromiumBasedBrowser](#GetChromiumBasedBrowser)  
[GetFirefoxBasedBrowser](#GetFirefoxBasedBrowser)  
[GetWebkitBasedBrowser](#GetWebkitBasedBrowser)   

## GetDefaultPageDriver
Gets default mobile driver
```java
PageDriver defaultPageDriver = PageDriverFactory.getDefaultPageDriver();
```

## GetBrowserWithDefaults
Gets default page driver for specific browser
```java
PageDriver defaultChromiumPageDriver = PageDriverFactory.getBrowserWithDefaults(PlaywrightBrowser.CHROMIUM);
```

## GetPageDriverFromBrowser
Get new page (tab) driver for an existing browser
```java
PageDriver newTabPageDriver = PageDriverFactory.getPageDriverFromBrowser(this.getPageDriver().getParentBrowser());
```

## GetNewPageDriverFromBrowserContext
Get new page (tab) driver for an existing browser context
```java
PageDriver newTabPageDriver = PageDriverFactory.getNewPageDriverFromBrowserContext(this.getPageDriver().getParentBrowser().contexts().get(0));
```

## GetDefaultOptions
Gets default page options.  
These options include things like resolution, tracing, and video capture.
```java
PageDriver defaultPageDriver = PageDriverFactory.getChromiumBasedBrowser(playwright, PageDriverFactory.getDefaultOptions());
```
## GetDefaultChromeOptions
Gets default Chrome page options.  
These options include things like resolution, tracing, and video capture.e
```java
PageDriver chromePageDriver = PageDriverFactory.getChromiumBasedBrowser(playwright, PageDriverFactory.getDefaultChromeOptions());
```

## GetDefaultEdgeOptions
Gets default Edge page options.  
These options include things like resolution, tracing, and video capture.
```java
PageDriver edgePageDriver = PageDriverFactory.getChromiumBasedBrowser(playwright, PageDriverFactory.getDefaultEdgeOptions());
```

## GetChromiumBasedBrowser
Gets a Chromium based (Chrome, Chromium and Edge) page driver
```java
PageDriver defaultPageDriver = PageDriverFactory.getChromiumBasedBrowser(playwright, PageDriverFactory.getDefaultEdgeOptions());
```

## GetFirefoxBasedBrowser
Gets a Firefox page driver
```java
PageDriver defaultPageDriver = PageDriverFactory.getFirefoxBasedBrowser(playwright, PageDriverFactory.getDefaultOptions());
```

## GetWebkitBasedBrowser
Gets a Webkit (what Safari is base on) page driver
```java
PageDriver defaultPageDriver = PageDriverFactory.getWebkitBasedBrowser(playwright, PageDriverFactory.getDefaultOptions());
```





