.\gradlew.bat assembleDebug assembleDebugAndroidTest installDebug installDebugAndroidTest   
in gradle

 .\gradlew.bat test      
 in gradle

& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe"  shell am instrument -w com.pluck.test/androidx.test.runner.AndroidJUnitRunner 
in powershell because android studio has an issue and cant run the ui test
