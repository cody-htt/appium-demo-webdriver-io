# Project Skeleton
* Please clone this project and start implementing automation test scripts.
* We have 14 test cases, please split your tests to 2 devices to reduce testing time
(Make sure you can implement parallel test for 2 different approaches)

# Try to run project

# Useful commands

## Start an emulator with default settings
```
emulator -avd android_29 -wipe-data
```

## Finding PID for a port Windows
```
netstat -ano | findstr 8200
taskkill /F /PID 12345 (ex: 12345 is the PID)
```

## Finding PID for a port MacOS
```
lsof -t -i:8200 (ex: 8200 is the port)
kill -9 12345 (ex: 12345 is the PID)
```

## Remove appium settings on phone
```
adb uninstall io.appium.settings
adb uninstall io.appium.unlock
adb uninstall io.appium.uiautomator2
adb uninstall io.appium.uiautomator2.server.test
```

If you have more than one phone connected, you can use
```
adb -s device-udid uninstall io.appium.settings
...
```

## Maven Command
```
--- Trigger Build ---
mvn clean install -DskipTests=true

--- Trigger Test ---
mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/test-suites/Regression.xml -DotherParam=something
```

# Test cases
## Home Page
* HomePage_001: Make sure "App purpose" displayed
* HomePage_002: Make sure "Support" channel displayed

## Login
* Login_001: Make sure "missing email and password" displayed
* Login_002: Make sure "missing email" displayed
* Login_003: Make sure "missing password" displayed
* Login_004: User can login with correct creds

## Sign Up
* SignUp_001: Make sure "missing email and password" displayed
* SignUp_002: Make sure "missing email" displayed
* SignUp_003: Make sure "missing password" displayed
* SignUp_004: Make sure "confirm password is not matched" displayed
* SignUp_005: User can sign up with correct creds

## Form
* Form_001: what user input can be displayed
* Form_002: user can switch on/off and text displayed
* Form_003: user can switch on/off and text displayed
* Form_004: user can select dropdown webdriverio/appium/this app is awesome
* Form_005: Active/Inactive button works properly

# Swipe
* Swipe_001: User can swipe and texts are displayed correctly
* Swipe_002: Swipe vertically to see the icon at the end

# Webview
* Make sure the menu text and hyperlink displayed correctly
