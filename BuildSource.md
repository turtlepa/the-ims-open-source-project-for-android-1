#Build the ims-stack and testclient from source

# Setup the build environment #
  * Download the Android SDK and Android NDK from http://developer.android.com/sdk/index.html and install http://developer.android.com/sdk/installing.html (Note: we are using the console development, so you can skip all Eclipse installation).
  * Install the android-4.0 SDK (API Level 14)
  * Setup the android-sdk-linux/tools/ and android-ndk-{version}/ (We are using android-ndk-[r7](https://code.google.com/p/the-ims-open-source-project-for-android/source/detail?r=7)) in your PATH environment.
```
export PATH=[path to android sdk]/tools:[path to android ndk]/:$PATH
```

# Download the source code #

  * http://code.google.com/p/the-ims-open-source-project-for-android/source/checkout

# Build it #
  * Change directory into the source tree
```
cd the-ims-open-source-project-for-android
```
  * Update the ant build.xml by using android command
```
ant update
```
  * Build all
```
ant all
```
  * If there is no error during the build, the apk will exist under test-client/bin/IMSTestClient-debug.apk