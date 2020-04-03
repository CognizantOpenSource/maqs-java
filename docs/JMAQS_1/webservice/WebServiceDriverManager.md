# <img src="resources/maqslogo.ico" height="32" width="32"> Web Service Driver Manager

## Overview
The WebService Driver Manager has overreach of the Base Driver Manager.

[OverrideDriver](#OverrideDriver)
[GetWebSeriveDriver](#GetWebSeriveDriver)  
[Close](#Close)  


## OverrideDriver
Override the http driver
```java
this.webServiceDriver = driver;
 ```

## GetWebServiceDriver
Get the http driver
 ```java
WebServiceDriver = getWebServiceDriver();
 ```


## Close
Close Method sets Base Driver to Null.
```java
this.setBaseDriver(null)
```

