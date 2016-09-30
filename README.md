# Andy - Assistant for Home Nurse


This app is the first attempt on making a native android application to help nurse manage her home visits.

## Build

1. Make sure you have installed the latest version of Android Studio.
2. Open the project root folder in Android Studio.
3. After the initial gradle configuration done by the IDE, you should see at least two build configurations "app" and "andy" in the "Build Configuration" dropdown list.
4. Select the "andy" configuration.
5. Click the "Build" button or (Cmd + F9) to build.

## Run in emulator

1. Make sure you have installed an emulator (either using Android Virtual Devices or Genymotion) with OS image Lollipop 5.0.
2. Click the run button to start the app on your desired emulator.

## Notes on Server Connection

Chansey is the backend service for this app. Currently this app is using the Chansey instance deployed at "http://young-journey-22645.herokuapp.com/"

You may use your own server by changing the "CHANSEY_HOST" value in "EndpointFactory.java" file.

## TODOs

Somethings that are good to have in this project:
1. Setup a nice Android Unit Test framework. Roboelectric seems like a good option but it refuses to run with Android API level 24.
2. Setup a hosted CI service. Bitrise (https://www.bitrise.io/) could be an option.
