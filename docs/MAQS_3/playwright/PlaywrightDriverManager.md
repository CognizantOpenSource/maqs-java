# <img src="resources/MAQS.jpg" height="32" width="32"> Page Driver Manager

## Overview
The page Driver Manager gets and closes the appium driver.

## GetPageDriver
Get the Playwright page driver
```java
 public PageDriver getPageDriver() {
    if (this.pageDriver == null) {
      this.pageDriver = new PageDriver(getBase().getAsyncPage());

      // Log the setup
      this.loggingStartup(this.baseDriver);
    }

    return this.pageDriver;
  }

```

## Close
Cleanup the page driver
```java
 public void close() {
    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      PageDriver driver = this.getPageDriver();
      driver.close();
    } catch (Exception e) {
      getLogger().logMessage(MessageType.ERROR,
          StringProcessor.safeFormatter("Failed to close web driver because: %s", e.getMessage()));
    }
  }
```