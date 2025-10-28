Windows PowerShell
Copyright (C) Microsoft Corporation. All rights reserved.

Install the latest PowerShell for new features and improvements! https://aka.ms/PSWindows

PS Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK> ./gradlew clean build --stacktrace --info                                                            
Initialized native services in: C:\Users\Ahmed\.gradle\native
Initialized jansi services in: C:\Users\Ahmed\.gradle\native
Found daemon DaemonInfo{pid=21164, address=[90ae4feb-f9d7-4f56-b582-a59ba15528c0 port:54714, addresses:[/127.0.0.1]], state=Idle, lastBusy=1761687531493, context
=DefaultDaemonContext[uid=cab42d61-c18c-4f76-92bd-2503773a3b0a,javaHome=C:\Program Files\Eclipse Foundation\jdk-8.0.302.8-hotspot,javaVersion=8,javaVendor=Temuri
n,daemonRegistryDir=C:\Users\Ahmed\.gradle\daemon,pid=21164,idleTimeout=10800000,priority=NORMAL,applyInstrumentationAgent=true,nativeServicesMode=ENABLED,daemonOpts=-Xmx2048m,-Dfile.encoding=UTF-8,-Duser.country=CA,-Duser.language=en,-Duser.variant]} however its context does not match the desired criteria.
JVM is incompatible.
Wanted: DaemonRequestContext{jvmCriteria=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot (no JDK specified, using current Java home), daemonOpts=[-Xmx2048m, -Dfile.encoding=UTF-8, -Duser.country=CA, -Duser.language=en, -Duser.variant], applyInstrumentationAgent=true, nativeServicesMode=ENABLED, priority=NORMAL}  
Actual: DefaultDaemonContext[uid=cab42d61-c18c-4f76-92bd-2503773a3b0a,javaHome=C:\Program Files\Eclipse Foundation\jdk-8.0.302.8-hotspot,javaVersion=8,javaVendor
=Temurin,daemonRegistryDir=C:\Users\Ahmed\.gradle\daemon,pid=21164,idleTimeout=10800000,priority=NORMAL,applyInstrumentationAgent=true,nativeServicesMode=ENABLED,daemonOpts=-Xmx2048m,-Dfile.encoding=UTF-8,-Duser.country=CA,-Duser.language=en,-Duser.variant]

  Looking for a different daemon...
Found daemon DaemonInfo{pid=26360, address=[835bbb76-0eab-4c5e-a296-2fbdc787db15 port:65361, addresses:[/127.0.0.1]], state=Idle, lastBusy=1761688738744, context
=DefaultDaemonContext[uid=bff41176-53ce-476d-82ee-c25a5aad5a68,javaHome=C:\Program Files\Android\Android Studio\jbr,javaVersion=21,javaVendor=JetBrains s.r.o.,da
emonRegistryDir=C:\Users\Ahmed\.gradle\daemon,pid=26360,idleTimeout=10800000,priority=NORMAL,applyInstrumentationAgent=true,nativeServicesMode=ENABLED,daemonOpts=-Xmx2048m,-Dfile.encoding=UTF-8,-Duser.country=CA,-Duser.language=en,-Duser.variant]} however its context does not match the desired criteria.
JVM is incompatible.
Wanted: DaemonRequestContext{jvmCriteria=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot (no JDK specified, using current Java home), daemonOpts=[-Xmx2048m, -Dfile.encoding=UTF-8, -Duser.country=CA, -Duser.language=en, -Duser.variant], applyInstrumentationAgent=true, nativeServicesMode=ENABLED, priority=NORMAL}  
Actual: DefaultDaemonContext[uid=bff41176-53ce-476d-82ee-c25a5aad5a68,javaHome=C:\Program Files\Android\Android Studio\jbr,javaVersion=21,javaVendor=JetBrains s.
r.o.,daemonRegistryDir=C:\Users\Ahmed\.gradle\daemon,pid=26360,idleTimeout=10800000,priority=NORMAL,applyInstrumentationAgent=true,nativeServicesMode=ENABLED,daemonOpts=-Xmx2048m,-Dfile.encoding=UTF-8,-Duser.country=CA,-Duser.language=en,-Duser.variant]

  Looking for a different daemon...
Removing 0 daemon stop events from registry
Previous Daemon (3496) stopped at Tue Oct 28 15:02:11 MDT 2025 other compatible daemons were started and after being idle for 0 minutes and not recently used    
Previous Daemon (34732) stopped at Tue Oct 28 15:26:20 MDT 2025 other compatible daemons were started and after being idle for 0 minutes and not recently used   
Previous Daemon (31604) stopped at Tue Oct 28 15:54:24 MDT 2025 stop command received
Previous Daemon (41648) stopped at Tue Oct 28 15:58:33 MDT 2025 stop command received
Starting a Gradle Daemon, 2 incompatible and 4 stopped Daemons could not be reused, use --status for details
Starting process 'Gradle build daemon'. Working directory: C:\Users\Ahmed\.gradle\daemon\8.13 Command: C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens
=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UN
NAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-ope
ns=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -Xmx2048m -D
file.encoding=UTF-8 -Duser.country=CA -Duser.language=en -Duser.variant -cp C:\Users\Ahmed\.gradle\wrapper\dists\gradle-8.13-bin\5xuhj0ry160q40clulazy9h7d\gradle
-8.13\lib\gradle-daemon-main-8.13.jar -javaagent:C:\Users\Ahmed\.gradle\wrapper\dists\gradle-8.13-bin\5xuhj0ry160q40clulazy9h7d\gradle-8.13\lib\agents\gradle-instrumentation-agent-8.13.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.13
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 1.19 secs.
The client will now receive all logging from the daemon (pid: 7032). The daemon log file: C:\Users\Ahmed\.gradle\daemon\8.13\daemon-7032.out.log                 
Starting build in new daemon [memory: 2 GiB]
Using 16 worker leases.
Received JVM installation metadata from 'C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot': {JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot, JAVA_VERSION=17.0.16, JAVA_VENDOR=Eclipse Adoptium, RUNTIME_NAME=OpenJDK Runtime Environment, RUNTIME_VERSION=17.0.16+8, VM_NAME=OpenJDK 64-Bit Server VM, VM_VERSION=17.0.16+8, VM_VENDOR=Eclipse Adoptium, OS_ARCH=amd64}
Watching the file system is configured to be enabled if available
Now considering [Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK] as hierarchies to watch
File system watching is active
Starting Build
Settings evaluated using settings file 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\settings.gradle.kts'.
Projects loaded. Root project using build file 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\build.gradle.kts'.
Included projects: [root project 'pLUCK', project ':app']

> Configure project :
Evaluating root project 'pLUCK' using build file 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\build.gradle.kts'.
Caching disabled for Kotlin DSL script compilation (Project/TopLevel/stage1) because:
  Build cache is disabled
Resolved plugin [id: 'com.android.application', version: '8.1.3', apply: false]
Resolved plugin [id: 'org.jetbrains.kotlin.android', version: '1.9.10', apply: false]
Resolved plugin [id: 'com.google.gms.google-services', version: '4.4.4', apply: false]

> Configure project :app
Evaluating project ':app' using build file 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build.gradle.kts'.
Caching disabled for Kotlin DSL script compilation (Project/TopLevel/stage1) because:
  Build cache is disabled
Resolved plugin [id: 'com.android.application', version: '8.1.3']
Resolved plugin [id: 'org.jetbrains.kotlin.android', version: '1.9.10']
Resolved plugin [id: 'com.google.gms.google-services', version: '4.4.4']
Using default execution profile
Using Kotlin Gradle Plugin gradle81 variant
Caching disabled for Kotlin DSL script compilation (Project/TopLevel/stage2) because:
  Build cache is disabled
All projects evaluated.
Task name matched 'clean'
Task name matched 'build'
Selected primary task 'clean' from project :
Selected primary task 'build' from project :
Warning: SDK processing. This version only understands SDK XML versions up to 3 but an SDK XML file of version 4 was encountered. This can happen if you use versions of Android Studio and the command-line tools that were released at different times.
Tasks to be executed: [task ':app:clean', task ':app:preBuild', task ':app:preDebugBuild', task ':app:mergeDebugNativeDebugMetadata', task ':app:checkDebugAarMetadata', task ':app:generateDebugResValues', task ':app:processDebugGoogleServices', task ':app:mapDebugSourceSetPaths', task ':app:generateDebugResources', task 
':app:mergeDebugResources', task ':app:packageDebugResources', task ':app:parseDebugLocalResources', task ':app:createDebugCompatibleScreenManifests', task ':app
:extractDeepLinksDebug', task ':app:processDebugMainManifest', task ':app:processDebugManifest', task ':app:processDebugManifestForPackage', task ':app:processDe
bugResources', task ':app:compileDebugKotlin', task ':app:javaPreCompileDebug', task ':app:compileDebugJavaWithJavac', task ':app:mergeDebugShaders', task ':app:
compileDebugShaders', task ':app:generateDebugAssets', task ':app:mergeDebugAssets', task ':app:compressDebugAssets', task ':app:desugarDebugFileDependencies', t
ask ':app:dexBuilderDebug', task ':app:mergeDebugGlobalSynthetics', task ':app:processDebugJavaRes', task ':app:mergeDebugJavaResource', task ':app:checkDebugDup
licateClasses', task ':app:mergeExtDexDebug', task ':app:mergeLibDexDebug', task ':app:mergeProjectDexDebug', task ':app:mergeDebugJniLibFolders', task ':app:mer
geDebugNativeLibs', task ':app:stripDebugDebugSymbols', task ':app:validateSigningDebug', task ':app:writeDebugAppMetadata', task ':app:writeDebugSigningConfigVe
rsions', task ':app:packageDebug', task ':app:createDebugApkListingFileRedirect', task ':app:assembleDebug', task ':app:buildKotlinToolingMetadata', task ':app:p
reReleaseBuild', task ':app:checkReleaseAarMetadata', task ':app:generateReleaseResValues', task ':app:processReleaseGoogleServices', task ':app:mapReleaseSource
SetPaths', task ':app:generateReleaseResources', task ':app:mergeReleaseResources', task ':app:packageReleaseResources', task ':app:parseReleaseLocalResources', 
task ':app:createReleaseCompatibleScreenManifests', task ':app:extractDeepLinksRelease', task ':app:processReleaseMainManifest', task ':app:processReleaseManifes
t', task ':app:processReleaseManifestForPackage', task ':app:processReleaseResources', task ':app:compileReleaseKotlin', task ':app:javaPreCompileRelease', task 
':app:compileReleaseJavaWithJavac', task ':app:extractProguardFiles', task ':app:lintVitalAnalyzeRelease', task ':app:lintVitalReportRelease', task ':app:lintVit
alRelease', task ':app:mergeReleaseJniLibFolders', task ':app:mergeReleaseNativeLibs', task ':app:stripReleaseDebugSymbols', task ':app:extractReleaseNativeSymbo
lTables', task ':app:mergeReleaseNativeDebugMetadata', task ':app:checkReleaseDuplicateClasses', task ':app:dexBuilderRelease', task ':app:desugarReleaseFileDepe
ndencies', task ':app:mergeExtDexRelease', task ':app:mergeDexRelease', task ':app:mergeReleaseArtProfile', task ':app:mergeReleaseGlobalSynthetics', task ':app:
compileReleaseArtProfile', task ':app:mergeReleaseShaders', task ':app:compileReleaseShaders', task ':app:generateReleaseAssets', task ':app:mergeReleaseAssets',
 task ':app:compressReleaseAssets', task ':app:processReleaseJavaRes', task ':app:mergeReleaseJavaResource', task ':app:optimizeReleaseResources', task ':app:col
lectReleaseDependencies', task ':app:sdkReleaseDependencyData', task ':app:writeReleaseAppMetadata', task ':app:writeReleaseSigningConfigVersions', task ':app:pa
ckageRelease', task ':app:createReleaseApkListingFileRedirect', task ':app:assembleRelease', task ':app:assemble', task ':app:bundleDebugClassesToCompileJar', ta
sk ':app:preDebugAndroidTestBuild', task ':app:generateDebugAndroidTestResValues', task ':app:lintAnalyzeDebug', task ':app:lintReportDebug', task ':app:lintDebu
g', task ':app:lint', task ':app:bundleDebugClassesToRuntimeJar', task ':app:compileDebugUnitTestKotlin', task ':app:preDebugUnitTestBuild', task ':app:javaPreCo
mpileDebugUnitTest', task ':app:compileDebugUnitTestJavaWithJavac', task ':app:processDebugUnitTestJavaRes', task ':app:testDebugUnitTest', task ':app:bundleRele
aseClassesToRuntimeJar', task ':app:bundleReleaseClassesToCompileJar', task ':app:compileReleaseUnitTestKotlin', task ':app:preReleaseUnitTestBuild', task ':app:
javaPreCompileReleaseUnitTest', task ':app:compileReleaseUnitTestJavaWithJavac', task ':app:processReleaseUnitTestJavaRes', task ':app:testReleaseUnitTest', task ':app:test', task ':app:check', task ':app:build']
Tasks that were excluded: []
Resolve mutations for :app:clean (Thread[Execution worker Thread 4,5,main]) started.
:app:clean (Thread[Execution worker Thread 4,5,main]) started.
destroyer locations for task group 0 (Thread[included builds,5,main]) started.

> Task :app:clean
Caching disabled for task ':app:clean' because:
  Build cache is disabled
Task ':app:clean' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Resolve mutations for :app:preBuild (Thread[Execution worker Thread 4,5,main]) started.
:app:preBuild (Thread[Execution worker Thread 4,5,main]) started.

> Task :app:preBuild UP-TO-DATE                                                                                                                                  
Skipping task ':app:preBuild' as it has no actions.
Resolve mutations for :app:preDebugBuild (Thread[Execution worker Thread 4,5,main]) started.
:app:preDebugBuild (Thread[Execution worker Thread 4,5,main]) started.

> Task :app:preDebugBuild UP-TO-DATE                                                                                                                             
Skipping task ':app:preDebugBuild' as it has no actions.
Resolve mutations for :app:mergeDebugNativeDebugMetadata (Thread[Execution worker Thread 4,5,main]) started.
:app:mergeDebugNativeDebugMetadata (Thread[Execution worker Thread 4,5,main]) started.

> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE                                                                                                              
Skipping task ':app:mergeDebugNativeDebugMetadata' as it has no source files and no previous output files.
Resolve mutations for :app:checkDebugAarMetadata (Thread[Execution worker Thread 4,5,main]) started.
:app:checkDebugAarMetadata (Thread[Execution worker Thread 4,5,main]) started.
Resolve mutations for :app:generateDebugResValues (Thread[Execution worker Thread 10,5,main]) started.
:app:generateDebugResValues (Thread[Execution worker Thread 10,5,main]) started.

> Task :app:checkDebugAarMetadata
Caching disabled for task ':app:checkDebugAarMetadata' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:checkDebugAarMetadata' is not up-to-date because:
  Output property 'dummyOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\aar_metadata_check\debug has been removed.

> Task :app:generateDebugResValues
Caching disabled for task ':app:generateDebugResValues' because:
  Build cache is disabled
Task ':app:generateDebugResValues' is not up-to-date because:
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\resValues\debug has been removed.       
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\resValues\debug\values has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\resValues\debug\values\gradleResValues.xml has been removed.
Resolve mutations for :app:processDebugGoogleServices (Thread[Execution worker Thread 4,5,main]) started.
:app:processDebugGoogleServices (Thread[Execution worker Thread 10,5,main]) started.

> Task :app:processDebugGoogleServices
Caching disabled for task ':app:processDebugGoogleServices' because:
  Build cache is disabled
Task ':app:processDebugGoogleServices' is not up-to-date because:
  Output property 'gmpAppId' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\gmpAppId\debug.txt has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\processDebugGoogleServices has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\processDebugGoogleServices\values has been removed.
  and more...
Parsing json file: Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\google-services.json
Resolve mutations for :app:mapDebugSourceSetPaths (Thread[Execution worker Thread 10,5,main]) started.
:app:mapDebugSourceSetPaths (Thread[Execution worker Thread 10,5,main]) started.

> Task :app:mapDebugSourceSetPaths
Caching disabled for task ':app:mapDebugSourceSetPaths' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:mapDebugSourceSetPaths' is not up-to-date because:
  Output property 'filepathMappingFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\source_set_path_map\debug\file-map.txt has been removed.
Resolve mutations for :app:generateDebugResources (Thread[Execution worker Thread 10,5,main]) started.
:app:generateDebugResources (Thread[Execution worker Thread 10,5,main]) started.

> Task :app:generateDebugResources
Skipping task ':app:generateDebugResources' as it has no actions.
Resolve mutations for :app:mergeDebugResources (Thread[Execution worker Thread 10,5,main]) started.
:app:mergeDebugResources (Thread[Execution worker Thread 10,5,main]) started.

> Task :app:mergeDebugResources
Caching disabled for task ':app:mergeDebugResources' because:
  Build cache is disabled
Task ':app:mergeDebugResources' is not up-to-date because:
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\debug\out has been removed.
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\debug\out\multi-v2 has been removed.
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\debug\out\multi-v2\debug.json has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeDebugResources'.
[MergeResources] Inputs are non-incremental full task action.
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-watch-v21\values-watch-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-watch-v21_values-watch-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-hi\values-hi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-hi_values-hi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values\values.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values_values.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-pt\values-pt.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-pt_values-pt.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-watch
-v20\values-watch-v20.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-watch-v20_values-watch-v20.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-hr\values-hr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-hr_values-hr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-hu\values-hu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-hu_values-hu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-hy\values-hy.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-hy_values-hy.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-h320d
p-port-v13\values-h320dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-h320dp-port-v13_values-h320dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v16\values-v16.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v16_values-v16.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v18\values-v18.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v18_values-v18.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v17\values-v17.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v17_values-v17.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ldltr
-v21\values-ldltr-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ldltr-v21_values-ldltr-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-w360d
p-port-v13\values-w360dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-w360dp-port-v13_values-w360dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-af\values-af.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-af_values-af.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-in\values-in.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-in_values-in.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ja\values-ja.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ja_values-ja.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-as\values-as.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-as_values-as.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v21\values-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v21_values-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-es-rUS\values-es-rUS.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-es-rUS_values-es-rUS.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-am\values-am.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-am_values-am.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-it\values-it.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-it_values-it.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v23\values-v23.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v23_values-v23.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v22\values-v22.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v22_values-v22.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-is\values-is.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-is_values-is.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v25\values-v25.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v25_values-v25.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-en-rCA\values-en-rCA.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-en-rCA_values-en-rCA.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-zh-rHK\values-zh-rHK.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-zh-rHK_values-zh-rHK.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v28\values-v28.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v28_values-v28.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sw600
dp-v13\values-sw600dp-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sw600dp-v13_values-sw600dp-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-bg\values-bg.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-bg_values-bg.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-b+sr+
Latn\values-b+sr+Latn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-b+sr+Latn_values-b+sr+Latn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ru\values-ru.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ru_values-ru.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-bn\values-bn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-bn_values-bn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-az\values-az.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-az_values-az.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-iw\values-iw.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-iw_values-iw.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ar\values-ar.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ar_values-ar.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-be\values-be.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-be_values-be.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-zu\values-zu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-zu_values-zu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-pt-rPT\values-pt-rPT.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-pt-rPT_values-pt-rPT.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v27\values-v27.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v27_values-v27.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sl\values-sl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sl_values-sl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sv\values-sv.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sv_values-sv.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ky\values-ky.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ky_values-ky.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-km\values-km.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-km_values-km.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-w320d
p-land-v13\values-w320dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-w320dp-land-v13_values-w320dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v24\values-v24.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v24_values-v24.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sw\values-sw.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sw_values-sw.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-bs\values-bs.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-bs_values-bs.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ro\values-ro.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ro_values-ro.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-v26\values-v26.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-v26_values-v26.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-h360d
p-land-v13\values-h360dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-h360dp-land-v13_values-h360dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ko\values-ko.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ko_values-ko.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sk\values-sk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sk_values-sk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-kk\values-kk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-kk_values-kk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-en-rAU\values-en-rAU.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-en-rAU_values-en-rAU.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-lt\values-lt.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-lt_values-lt.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-de\values-de.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-de_values-de.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-kn\values-kn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-kn_values-kn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sq\values-sq.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sq_values-sq.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-lv\values-lv.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-lv_values-lv.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-th\values-th.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-th_values-th.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-h480d
p-land-v13\values-h480dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-h480dp-land-v13_values-h480dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ka\values-ka.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ka_values-ka.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ca\values-ca.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ca_values-ca.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-si\values-si.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-si_values-si.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ta\values-ta.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ta_values-ta.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-h550d
p-port-v13\values-h550dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-h550dp-port-v13_values-h550dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-pt-rBR\values-pt-rBR.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-pt-rBR_values-pt-rBR.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-et\values-et.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-et_values-et.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-te\values-te.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-te_values-te.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-mr\values-mr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-mr_values-mr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ms\values-ms.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ms_values-ms.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-sr\values-sr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-sr_values-sr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ldrtl
-v17\values-ldrtl-v17.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ldrtl-v17_values-ldrtl-v17.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-eu\values-eu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-eu_values-eu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-h720d
p-v13\values-h720dp-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-h720dp-v13_values-h720dp-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ur\values-ur.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ur_values-ur.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-my\values-my.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-my_values-my.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-en-rGB\values-en-rGB.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-en-rGB_values-en-rGB.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-zh-rCN\values-zh-rCN.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-zh-rCN_values-zh-rCN.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-da\values-da.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-da_values-da.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-nb\values-nb.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-nb_values-nb.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-en-rIN\values-en-rIN.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-en-rIN_values-en-rIN.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-fi\values-fi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-fi_values-fi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-cs\values-cs.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-cs_values-cs.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-xlarg
e-v4\values-xlarge-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-xlarge-v4_values-xlarge-v4.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-mk\values-mk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-mk_values-mk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-land\values-land.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-land_values-land.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-tl\values-tl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-tl_values-tl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-tr\values-tr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-tr_values-tr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-zh-rTW\values-zh-rTW.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-zh-rTW_values-zh-rTW.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-lo\values-lo.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-lo_values-lo.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ml\values-ml.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ml_values-ml.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-w480d
p-port-v13\values-w480dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-w480dp-port-v13_values-w480dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-large
-v4\values-large-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-large-v4_values-large-v4.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-ne\values-ne.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-ne_values-ne.arsc.flat

Resolve mutations for :app:packageDebugResources (Thread[Execution worker,5,main]) started.
:app:packageDebugResources (Thread[Execution worker,5,main]) started.

> Task :app:packageDebugResources
Caching disabled for task ':app:packageDebugResources' because:
  Build cache is disabled
Task ':app:packageDebugResources' is not up-to-date because:
  Output property 'dataBindingLayoutInfoOutFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\data_binding_layout_info_type_package\debug\out has been removed.
  Output property 'incrementalFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\packageDebugResources has been removed.
  Output property 'incrementalFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\packageDebugResources\compile-file-map.properties has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:packageDebugResources'.
[MergeResources] Inputs are non-incremental full task action.
Resolve mutations for :app:parseDebugLocalResources (Thread[Execution worker,5,main]) started.
:app:parseDebugLocalResources (Thread[Execution worker,5,main]) started.
Resolve mutations for :app:createDebugCompatibleScreenManifests (Thread[Execution worker Thread 13,5,main]) started.
:app:createDebugCompatibleScreenManifests (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:createDebugCompatibleScreenManifests
Caching disabled for task ':app:createDebugCompatibleScreenManifests' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:createDebugCompatibleScreenManifests' is not up-to-date because:
  Output property 'outputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compatible_screen_manifest\debug has been removed.
  Output property 'outputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compatible_screen_manifest\debug\output-metadata.json has been removed.
Resolve mutations for :app:extractDeepLinksDebug (Thread[Execution worker Thread 13,5,main]) started.
:app:extractDeepLinksDebug (Thread[Execution worker Thread 8,5,main]) started.

> Task :app:extractDeepLinksDebug
Caching disabled for task ':app:extractDeepLinksDebug' because:
  Build cache is disabled
Task ':app:extractDeepLinksDebug' is not up-to-date because:
  Output property 'navigationJson' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\navigation_json\debug\navigation.json has been removed.
Resolve mutations for :app:processDebugMainManifest (Thread[Execution worker Thread 8,5,main]) started.
:app:processDebugMainManifest (Thread[Execution worker Thread 8,5,main]) started.

> Task :app:parseDebugLocalResources
Caching disabled for task ':app:parseDebugLocalResources' because:
  Build cache is disabled
Task ':app:parseDebugLocalResources' is not up-to-date because:
  Output property 'librarySymbolsFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\local_only_symbol_list\debug\R-def.txt has been removed.
The input changes require a full rebuild for incremental task ':app:parseDebugLocalResources'.

> Task :app:processDebugMainManifest
Caching disabled for task ':app:processDebugMainManifest' because:
  Build cache is disabled
Task ':app:processDebugMainManifest' is not up-to-date because:
  Value of input property 'optionalFeatures' has changed for task ':app:processDebugMainManifest'
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca7581fdd03ded8825166172f9397972\transformed\navigation-common-2.7.3\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4a61c7584fb1f9fb321e1fe7981aca7e\transformed\navigation-runtime-2.7.3\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c0cee090e873cd16b94165bdf40ed30d\transformed\navigation-common-ktx-2.7.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d55a05dfc813b771bcca5f09893c7115\transformed\navigation-runtime-ktx-2.7.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ce254a50ba83349b7cad4cd5a47a182b\transformed\navigation-compose-2.7.3\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d898b3bf7f14f3376b620881baa9ac7\transformed\accompanist-permissions-0.32.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\451d346a44b54de4969b9580f180368a\transformed\maps-compose-4.3.3\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c57e699d6d3fdfcc8117035fa000776\transformed\material3-release\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3ba880f9761f7dfb48c24e30540796ef\transformed\coil-compose-2.5.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d120d176e03cec0ff11698dbd2491a9\transformed\coil-compose-base-2.5.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b2acda9b91f386a67bd7ae9f1a2c7b43\transformed\camera-video-1.3.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\821c7f8ea92b1eb9f92d724d3dae2a9d\transformed\camera-view-1.3.0\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a938d1eec6912e763d9fb2fb712a289a\transformed\camera-lifecycle-1.3.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b43eab086c3fd4006c4ab3044874f2dd\transformed\camera-core-1.3.0\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\79a0e9d6e7549546d45b28012505c9ca\transformed\cloudinary-android-2.5.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5690d428903aa172c9993823502c6e91\transformed\cloudinary-android-preprocess-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d0c7b7133ba44e76ad823bb74c2f7b\transformed\cloudinary-android-download-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\827d372406ee44701ea85ff05ddc4b4b\transformed\material-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1c236d1be356058c5d38a6407020414\transformed\material-icons-core-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b00e01cd2630685c5896c00ed4d387a4\transformed\material-icons-extended-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0bca281d65c6305c18f08d784f02db44\transformed\material-ripple-release\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7ffcccc0c7d0fe0808a1e1d43a5c4bc\transformed\animation-core-release\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b45016de5999ab45e3c6f93f18b8f0b4\transformed\animation-release\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ad701c3da8b6f314a074147b61622f51\transformed\foundation-layout-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75cf2c8d302443c5f5a11437f45fc7cc\transformed\foundation-release\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\77783205e90b075fd7663add54d83c1f\transformed\ui-tooling-data-release\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\901560b52dc43160942dd52a8d5ddb66\transformed\ui-unit-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a0b4f0517b364306f98aab095621a04a\transformed\ui-geometry-release\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a2c176c83e3255c54c294c740419995\transformed\ui-util-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4ab1ce79a4e8be4025b59fbb6dbd8d67\transformed\ui-graphics-release\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e00013d98828add3ca1a88b1ea5b2fea\transformed\ui-tooling-preview-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\216fcb22cce24b735816ff2d550d4b95\transformed\ui-text-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\182fe93a2c05f703c0be618f4e29b6ea\transformed\material-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b917f088bb1e395972db2b08698549bd\transformed\coil-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2a4d4227201ca2b4355bd833c0587443\transformed\coil-base-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a42711f265d77257f392b6168bc60944\transformed\appcompat-resources-1.6.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9d26fad69a8035c5a1e8031032bda63\transformed\constraintlayout-2.0.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3527fb198cdd94198153373b9754785d\transformed\appcompat-1.6.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\314c779f047198335435d76c74a49af0\transformed\emoji2-views-helper-1.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\131407770d0a55c5099b1c9bcca5d9b3\transformed\lifecycle-service-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ef0651bbaaf20d6f1ce6842f3cf3ea4\transformed\maps-ktx-5.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc72f96d8c07a6bc682086f6a12afa94\transformed\barcode-scanning-17.2.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1ef8e61defd79da791ebb59714f2e6b\transformed\play-services-location-21.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\44332fe5700c324c2a841fed947afb20\transformed\barcode-scanning-common-17.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75f0140c7f717d66951ed03f3680fc27\transformed\runtime-saveable-release\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6dc17ed3b6be82e4f8af8c05b8da1b0c\transformed\runtime-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b518a01e24c8367d7c40d73f84fcb476\transformed\recaptcha-18.1.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dec6106073bd1ccc907541665e674944\transformed\play-services-auth-api-phone-17.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e020070785bab2e428d71a57f14adf\transformed\firebase-appcheck-interop-17.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3296b09c7f64b522e97abed6126219a2\transformed\firebase-database-collection-18.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d845df531a260f75739db15ce9396e59\transformed\firebase-analytics-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b2ab533ae5878b9cafe683ac52230b2\transformed\firebase-auth-interop-20.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c8cbc24cb735075edb099d47fc6cfce4\transformed\integrity-1.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\877481486b492ed837440e24d40d376c\transformed\vision-interfaces-16.2.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecde9336b62f39bd954535409773422b\transformed\firebase-installations-interop-17.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0e1681c588006a553017c69b9f3ee841\transformed\play-services-tasks-18.0.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\14bfdc109aaf5d07c0715e526294f3e9\transformed\play-services-measurement-sdk-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\494728eeabc92461d48f2864fe26da46\transformed\play-services-ads-identifier-18.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6649a9bc3f74cab6db315ced24506837\transformed\play-services-measurement-sdk-api-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\99363a2471989efa1468400d1e774fc3\transformed\play-services-measurement-base-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a2ceb738eefaa6214c9b16aa63616f0\transformed\play-services-stats-17.0.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5e4955c6525be4076f8f44f8317be0e6\transformed\firebase-measurement-connector-19.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\80c7788a63291a048d3114fba53e2731\transformed\legacy-support-v4-1.0.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a7b689b97281718ee32092e564efecb5\transformed\viewpager2-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a50c744dcf4b5e5e8edce004e0604f76\transformed\fragment-1.3.6\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be2d59222a581ea27079e1e5f50bfc22\transformed\lifecycle-livedata-core-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\03c32e1ac4a26acd85b5bcbb19ecee9b\transformed\lifecycle-viewmodel-ktx-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a40505be55533e631038632642044d4d\transformed\legacy-support-core-ui-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f66a86c88c7490b271b2934db5002420\transformed\dynamicanimation-1.0.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6316851f664031f40b83071bc15adb03\transformed\legacy-support-core-utils-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f72441850923c45aa75245fd7ca98102\transformed\loader-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9dc43b1ef6ea4e62e3378e37a4247f07\transformed\lifecycle-livedata-2.6.2\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3458cd08c021da105f701fc15f09ea17\transformed\activity-1.7.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c354034cefbd9814d2b305e4cda410ee\transformed\savedstate-ktx-1.2.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c15d40a248a43ccdee67d6766fcb6f26\transformed\savedstate-1.2.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d5f63eac777bcfe6b30d61b995a1c45\transformed\lifecycle-viewmodel-savedstate-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\35c18b15d8de5830b76dc4f86b05d870\transformed\litr-1.4.16\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b0b2bcd485495502849906c506e947e6\transformed\media3-exoplayer-dash-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2218549c45fe27b24d3d903e2ebcb2b9\transformed\media3-exoplayer-hls-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be618a094c6eadd34494571884db27e0\transformed\media3-exoplayer-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\38e0bd13d79fca62275fdd12c283e43a\transformed\media3-extractor-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c4e7b649c5c69897c9466a532c6dc9a\transformed\media3-container-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bfcbb13e13c03764b50018d4fbf6b43\transformed\media3-datasource-1.1.1\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d0c36692780219799cce233fa774885\transformed\media3-decoder-1.1.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d03c1b0898c92f40bf904e2184a2540\transformed\media3-database-1.1.1\AndroidManifest.xml    
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\353aa858e1189c1e15533a12e4bde9b1\transformed\media3-common-1.1.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\74feaf88e5d6ab5c88c4da35184e5d8f\transformed\media3-ui-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f80a44333a3d0d94d28b94082a80904e\transformed\recyclerview-1.3.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7666ab3b28cb3924d9f5e098debd7d8f\transformed\customview-poolingcontainer-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cd3359b6cbdaa1bef10e17bd7b91672b\transformed\core-ktx-1.12.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3569a112fda6949dbc9bf6f86c1158a1\transformed\browser-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62524a5742b998acb8b5c5dd6647626\transformed\autofill-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acca26e9976f0a24c694c71b93c16e0b\transformed\coordinatorlayout-1.1.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\250967770574026fa0b82276e0192fb6\transformed\transition-1.2.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\687b04085f858c1dee970df17d424330\transformed\vectordrawable-animated-1.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d6cb56e8858a9d0945b32a60153227\transformed\vectordrawable-1.1.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4b9d4797d7708ad60359aebb321d1f\transformed\media-1.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\43f5976b58f6a822c0f2d52c2a4f44aa\transformed\viewpager-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f1f690e76c48498566958dbcc549ce17\transformed\drawerlayout-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6064e677c16d5c58259d0cf627b5d8fd\transformed\slidingpanelayout-1.0.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\da2ed364818c61fb6178b7e31ec2a21d\transformed\customview-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b3f05fe21219c1462de54f164f791ff\transformed\swiperefreshlayout-1.0.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\266a76d3d546a9e118935d822877f314\transformed\asynclayoutinflater-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd1d24dd52e67068d44215574926172c\transformed\lifecycle-runtime-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\815557c2ae8a08884db25c998588790c\transformed\lifecycle-viewmodel-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aebb7c5ae338a679f119370e152f8566\transformed\lifecycle-viewmodel-compose-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e8fa69f2fc9288c4e50f2e3d7809464\transformed\accompanist-drawablepainter-0.32.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d9088c59b0fab5a007d7b04dd92a9f\transformed\ui-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\abc4525411768185054fd60bb82d62d9\transformed\ui-test-manifest-1.6.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d115dd7252bbf9ed6b0cc33f5ea878\transformed\ui-tooling-release\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\431591700a7d4c4a2d020513f2985bf1\transformed\activity-compose-1.7.2\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5638fabe0d5d244a1e5bdb4f9ebbd76e\transformed\activity-ktx-1.7.2\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\97fba2ce58e83f82cd34056ad1b2f944\transformed\lifecycle-runtime-ktx-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3c0ebdfb4381c963073963792850dfb\transformed\napier-debug\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\093f96a4c1a2638e480d7fbf99d765df\transformed\annotation-experimental-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\07daafe716d893f743308b2174374580\transformed\versionedparcelable-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9337281adcc4664583144eb68a4d78\transformed\firebase-components-17.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23f8a3b57d3b678bff82e4e4a2b0d8da\transformed\interpolator-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\657dcbddb3a2b9483703680d23a453a9\transformed\core-runtime-2.2.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9689239b917968370ee83770f980d8d3\transformed\localbroadcastmanager-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\154e3c535edbdd29d1f6d63c0fbc89b3\transformed\transport-api-2.2.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\085c58d7ccd3350d93d2385455be3130\transformed\firebase-encoders-json-17.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d4e55c19a61023e2414aba427f7766fd\transformed\viewbinding-7.4.2\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3bf891793924d464a72841108eb13580\transformed\startup-runtime-1.1.1\AndroidManifest.xml    
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\42cfd231f4c63e7c04d46c8dd3aaa08c\transformed\tracing-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\26f543fe221eecfd6592bfeb6d24ea8a\transformed\documentfile-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69f3a9b0a03b997a31ffb8beeeb09361\transformed\print-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c5f9a7307d88baffe4c41a6e6374054d\transformed\exifinterface-1.3.6\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3336ea62311c9c6f6a89ac10bc23051\transformed\cardview-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0d615bf23df10d96d1b53dd453627014\transformed\sqlite-framework-2.1.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7329e813a90b3c51a16cb2cf2e9da6e6\transformed\sqlite-2.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc457ff0ddba235c1477d57ed6838f6d\transformed\cursoradapter-1.0.0\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f8b1e25dbb65cc9b4986eb5d4812818\transformed\grpc-android-1.52.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c14b1f0304087127f8d0d5d65ec44ac4\transformed\protolite-well-known-types-18.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1debf71d1d368496a17f77aa5e301f0\transformed\image-1.0.0-beta1\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23264cf826b21068683bc0ce78f43f3e\transformed\fresco-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f5ef6ded1df61098fe18bc6c64e9b21\transformed\drawee-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\50d0d0f04287cb367c1ccf2956360961\transformed\nativeimagefilters-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b7fa33f44f3b80d898f9552ac844245\transformed\memory-type-native-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a4fe5708b949a2d6a5e628c9527e07b\transformed\memory-type-java-2.6.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d69946b300ec87149af263e1a8b97dd0\transformed\imagepipeline-native-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3be242a23f4988743ed36ef1693ed26f\transformed\memory-type-ashmem-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aaa20b67155259c75fb91f26fa0a8b93\transformed\imagepipeline-2.6.0\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dab1ba1b4aa7f5db10c1872e11a73484\transformed\nativeimagetranscoder-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b36b9dee027cfeda0b9fb3d854894fd5\transformed\imagepipeline-base-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d73423f7dad861f055ffe7756f86f5b1\transformed\soloader-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\282ba00101d4caddb474bbe6d8097617\transformed\soloader-0.10.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9c7bf14cb4e9247c6082c2483ba8be\transformed\middleware-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\30fcdd209d1f98a1d3e17edc923138fb\transformed\ui-common-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3808632869cf9db222c403621fd36c3\transformed\fbcore-2.6.0\AndroidManifest.xml
Merging main manifest Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml

Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca7581fdd03ded8825166172f9397972\transformed\navigation-common-2.7.3\AndroidManifest.xml  
Merging manifest with lower [androidx.navigation:navigation-common:2.7.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-common:2.7.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4a61c7584fb1f9fb321e1fe7981aca7e\transformed\navigation-runtime-2.7.3\AndroidManifest.xml 
Merging manifest with lower [androidx.navigation:navigation-runtime:2.7.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-runtime:2.7.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c0cee090e873cd16b94165bdf40ed30d\transformed\navigation-common-ktx-2.7.3\AndroidManifest.xml
Merging manifest with lower [androidx.navigation:navigation-common-ktx:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-common-ktx:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d55a05dfc813b771bcca5f09893c7115\transformed\navigation-runtime-ktx-2.7.3\AndroidManifest.xml
Merging manifest with lower [androidx.navigation:navigation-runtime-ktx:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-runtime-ktx:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ce254a50ba83349b7cad4cd5a47a182b\transformed\navigation-compose-2.7.3\AndroidManifest.xml 
Merging manifest with lower [androidx.navigation:navigation-compose:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-compose:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d898b3bf7f14f3376b620881baa9ac7\transformed\accompanist-permissions-0.32.0\AndroidManifest.xml
Merging manifest with lower [com.google.accompanist:accompanist-permissions:0.32.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.accompanist:accompanist-permissions:0.32.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\451d346a44b54de4969b9580f180368a\transformed\maps-compose-4.3.3\AndroidManifest.xml       
Merging manifest with lower [com.google.maps.android:maps-compose:4.3.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.maps.android:maps-compose:4.3.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c57e699d6d3fdfcc8117035fa000776\transformed\material3-release\AndroidManifest.xml        
Merging manifest with lower [androidx.compose.material3:material3-android:1.2.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material3:material3-android:1.2.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3ba880f9761f7dfb48c24e30540796ef\transformed\coil-compose-2.5.0\AndroidManifest.xml       
Merging manifest with lower [io.coil-kt:coil-compose:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-compose:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d120d176e03cec0ff11698dbd2491a9\transformed\coil-compose-base-2.5.0\AndroidManifest.xml  
Merging manifest with lower [io.coil-kt:coil-compose-base:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-compose-base:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b2acda9b91f386a67bd7ae9f1a2c7b43\transformed\camera-video-1.3.0\AndroidManifest.xml       
Merging manifest with lower [androidx.camera:camera-video:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-video:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\821c7f8ea92b1eb9f92d724d3dae2a9d\transformed\camera-view-1.3.0\AndroidManifest.xml        
Merging manifest with lower [androidx.camera:camera-view:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-view:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a938d1eec6912e763d9fb2fb712a289a\transformed\camera-lifecycle-1.3.0\AndroidManifest.xml   
Merging manifest with lower [androidx.camera:camera-lifecycle:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-lifecycle:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml     
Merging manifest with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:17:1-36:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:23:5-34:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b43eab086c3fd4006c4ab3044874f2dd\transformed\camera-core-1.3.0\AndroidManifest.xml        
Merging manifest with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:17:1-36:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:23:5-34:19
service#androidx.camera.core.impl.MetadataHolderService defined in both files...
Merging service#androidx.camera.core.impl.MetadataHolderService with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:29:9-33:78
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\79a0e9d6e7549546d45b28012505c9ca\transformed\cloudinary-android-2.5.0\AndroidManifest.xml 
Merging manifest with lower [com.cloudinary:cloudinary-android:2.5.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android:2.5.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:2:1-16:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:9:5-14:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5690d428903aa172c9993823502c6e91\transformed\cloudinary-android-preprocess-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-preprocess:2.5.0] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-preprocess:2.5.0] AndroidManifest.xml:6:5-9:61
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d0c7b7133ba44e76ad823bb74c2f7b\transformed\cloudinary-android-download-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-download:2.5.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-download:2.5.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:2:1-13:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:5:5-7:41
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:11:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml       
Merging manifest with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:17:1-145:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:21:5-23:41
Adopted [uses-permission: null]
Adopted [uses-permission: null]
Adopted [uses-permission: null]
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:30:5-143:19
Adopted [provider: null]
Adopted [service: null]
Adopted [service: null]
Adopted [service: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\827d372406ee44701ea85ff05ddc4b4b\transformed\material-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.material:material-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1c236d1be356058c5d38a6407020414\transformed\material-icons-core-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.material:material-icons-core-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-icons-core-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b00e01cd2630685c5896c00ed4d387a4\transformed\material-icons-extended-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.material:material-icons-extended-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-icons-extended-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0bca281d65c6305c18f08d784f02db44\transformed\material-ripple-release\AndroidManifest.xml  
Merging manifest with lower [androidx.compose.material:material-ripple-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-ripple-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7ffcccc0c7d0fe0808a1e1d43a5c4bc\transformed\animation-core-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.animation:animation-core-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.animation:animation-core-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b45016de5999ab45e3c6f93f18b8f0b4\transformed\animation-release\AndroidManifest.xml        
Merging manifest with lower [androidx.compose.animation:animation-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.animation:animation-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ad701c3da8b6f314a074147b61622f51\transformed\foundation-layout-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.foundation:foundation-layout-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.foundation:foundation-layout-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75cf2c8d302443c5f5a11437f45fc7cc\transformed\foundation-release\AndroidManifest.xml       
Merging manifest with lower [androidx.compose.foundation:foundation-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.foundation:foundation-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\77783205e90b075fd7663add54d83c1f\transformed\ui-tooling-data-release\AndroidManifest.xml  
Merging manifest with lower [androidx.compose.ui:ui-tooling-data-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-tooling-data-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\901560b52dc43160942dd52a8d5ddb66\transformed\ui-unit-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-unit-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-unit-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a0b4f0517b364306f98aab095621a04a\transformed\ui-geometry-release\AndroidManifest.xml      
Merging manifest with lower [androidx.compose.ui:ui-geometry-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-geometry-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a2c176c83e3255c54c294c740419995\transformed\ui-util-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-util-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-util-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4ab1ce79a4e8be4025b59fbb6dbd8d67\transformed\ui-graphics-release\AndroidManifest.xml      
Merging manifest with lower [androidx.compose.ui:ui-graphics-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-graphics-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e00013d98828add3ca1a88b1ea5b2fea\transformed\ui-tooling-preview-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-tooling-preview-android:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-tooling-preview-android:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\216fcb22cce24b735816ff2d550d4b95\transformed\ui-text-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-text-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-text-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\182fe93a2c05f703c0be618f4e29b6ea\transformed\material-1.4.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:22:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b917f088bb1e395972db2b08698549bd\transformed\coil-2.5.0\AndroidManifest.xml
Merging manifest with lower [io.coil-kt:coil:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2a4d4227201ca2b4355bd833c0587443\transformed\coil-base-2.5.0\AndroidManifest.xml
Merging manifest with lower [io.coil-kt:coil-base:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-base:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a42711f265d77257f392b6168bc60944\transformed\appcompat-resources-1.6.1\AndroidManifest.xml
Merging manifest with lower [androidx.appcompat:appcompat-resources:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.appcompat:appcompat-resources:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9d26fad69a8035c5a1e8031032bda63\transformed\constraintlayout-2.0.1\AndroidManifest.xml   
Merging manifest with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:9:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3527fb198cdd94198153373b9754785d\transformed\appcompat-1.6.1\AndroidManifest.xml
Merging manifest with lower [androidx.appcompat:appcompat:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.appcompat:appcompat:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\314c779f047198335435d76c74a49af0\transformed\emoji2-views-helper-1.3.0\AndroidManifest.xml
Merging manifest with lower [androidx.emoji2:emoji2-views-helper:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.emoji2:emoji2-views-helper:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml
Merging manifest with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:17:1-35:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:23:5-33:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:17:1-35:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:23:5-33:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\131407770d0a55c5099b1c9bcca5d9b3\transformed\lifecycle-service-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-service:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-service:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ef0651bbaaf20d6f1ce6842f3cf3ea4\transformed\maps-ktx-5.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.maps.android:maps-ktx:5.0.0] AndroidManifest.xml:18:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.maps.android:maps-ktx:5.0.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:17:1-44:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:23:5-79        
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:24:5-67
Adopted [uses-feature: null]
Adopted [queries: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:36:5-42:19
Adopted [uses-library: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml 
Merging manifest with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:2:1-17:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:7:5-15:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml     
Merging manifest with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:17:1-75:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:21:5-23:64
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:25:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:26:5-79
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:28:5-73:19
Adopted [activity: null]
Adopted [activity: null]
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:66:9-72:19    
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:8:5-16:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:9:9-15:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc72f96d8c07a6bc682086f6a12afa94\transformed\barcode-scanning-17.2.0\AndroidManifest.xml  
Merging manifest with lower [com.google.mlkit:barcode-scanning:17.2.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:barcode-scanning:17.2.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1ef8e61defd79da791ebb59714f2e6b\transformed\play-services-location-21.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:2:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:6:5-44
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:10:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:11:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:13:5-21:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:14:9-20:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:8:5-16:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\44332fe5700c324c2a841fed947afb20\transformed\barcode-scanning-common-17.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.mlkit:barcode-scanning-common:17.0.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:barcode-scanning-common:17.0.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml     
Merging manifest with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:8:5-16:19
service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService defined in both files...
Merging service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:9:9-15:19 
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml
Merging manifest with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:2:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:8:5-24:19
Adopted [provider: null]
service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService defined in both files...
Merging service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:15:9-23:19       
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:2:1-20:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:10:5-18:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:11:9-17:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75f0140c7f717d66951ed03f3680fc27\transformed\runtime-saveable-release\AndroidManifest.xml 
Merging manifest with lower [androidx.compose.runtime:runtime-saveable-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.runtime:runtime-saveable-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6dc17ed3b6be82e4f8af8c05b8da1b0c\transformed\runtime-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.runtime:runtime-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.runtime:runtime-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:2:1-17:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:7:5-15:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:8:9-14:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:2:1-20:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:10:5-18:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:11:9-17:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b518a01e24c8367d7c40d73f84fcb476\transformed\recaptcha-18.1.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:2:1-10:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:5:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:7:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:8:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml  
Merging manifest with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:15:1-38:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:19:5-21:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:25:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:26:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:28:5-36:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:29:9-35:19 
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dec6106073bd1ccc907541665e674944\transformed\play-services-auth-api-phone-17.4.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-auth-api-phone:17.4.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-auth-api-phone:17.4.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e020070785bab2e428d71a57f14adf\transformed\firebase-appcheck-interop-17.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:15:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:18:5-20:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:23:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3296b09c7f64b522e97abed6126219a2\transformed\firebase-database-collection-18.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-database-collection:18.0.1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-database-collection:18.0.1] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:16:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:18:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:19:5-23:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d845df531a260f75739db15ce9396e59\transformed\firebase-analytics-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:17:1-37:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:22:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:23:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:24:5-68        
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:27:5-35:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:28:9-34:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b2ab533ae5878b9cafe683ac52230b2\transformed\firebase-auth-interop-20.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:2:1-10:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:7:5-8:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:2:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:6:5-8:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:10:5-79       
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:11:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:14:5-22:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:15:9-21:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml   
Merging manifest with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:15:1-39:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:19:5-21:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:24:5-37:19
Adopted [provider: null]
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:32:9-36:35  
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c8cbc24cb735075edb099d47fc6cfce4\transformed\integrity-1.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:4:5-44
application defined in both files...
Merging application with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:5:5-6:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\877481486b492ed837440e24d40d376c\transformed\vision-interfaces-16.2.0\AndroidManifest.xml 
Merging manifest with lower [com.google.mlkit:vision-interfaces:16.2.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:vision-interfaces:16.2.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecde9336b62f39bd954535409773422b\transformed\firebase-installations-interop-17.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-installations-interop:17.0.1] AndroidManifest.xml:15:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-installations-interop:17.0.1] AndroidManifest.xml:19:5-21:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0e1681c588006a553017c69b9f3ee841\transformed\play-services-tasks-18.0.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:2:1-6:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:4:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:5:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:17:1-46:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:23:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:24:5-79 
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:25:5-68
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:28:5-44:19
Adopted [receiver: null]
Adopted [service: null]
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\14bfdc109aaf5d07c0715e526294f3e9\transformed\play-services-measurement-sdk-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:17:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:22:5-23:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:17:1-32:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:23:5-67        
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:24:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:25:5-68       
uses-permission#com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE defined in both files...
Merging uses-permission#com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:26:5-110
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:27:5-79
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:29:5-30:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\494728eeabc92461d48f2864fe26da46\transformed\play-services-ads-identifier-18.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:17:1-27:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:20:5-44
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:23:5-79
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:25:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6649a9bc3f74cab6db315ced24506837\transformed\play-services-measurement-sdk-api-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:17:1-28:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:23:5-67     
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:24:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:25:5-68    
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:26:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\99363a2471989efa1468400d1e774fc3\transformed\play-services-measurement-base-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a2ceb738eefaa6214c9b16aa63616f0\transformed\play-services-stats-17.0.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5e4955c6525be4076f8f44f8317be0e6\transformed\firebase-measurement-connector-19.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:17:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:22:5-23:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:16:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:18:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:20:5-24:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\80c7788a63291a048d3114fba53e2731\transformed\legacy-support-v4-1.0.0\AndroidManifest.xml  
Merging manifest with lower [androidx.legacy:legacy-support-v4:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-v4:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a7b689b97281718ee32092e564efecb5\transformed\viewpager2-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.viewpager2:viewpager2:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.viewpager2:viewpager2:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a50c744dcf4b5e5e8edce004e0604f76\transformed\fragment-1.3.6\AndroidManifest.xml
Merging manifest with lower [androidx.fragment:fragment:1.3.6] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.fragment:fragment:1.3.6] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be2d59222a581ea27079e1e5f50bfc22\transformed\lifecycle-livedata-core-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-livedata-core:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-livedata-core:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\03c32e1ac4a26acd85b5bcbb19ecee9b\transformed\lifecycle-viewmodel-ktx-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a40505be55533e631038632642044d4d\transformed\legacy-support-core-ui-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.legacy:legacy-support-core-ui:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-core-ui:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f66a86c88c7490b271b2934db5002420\transformed\dynamicanimation-1.0.0\AndroidManifest.xml   
Merging manifest with lower [androidx.dynamicanimation:dynamicanimation:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.dynamicanimation:dynamicanimation:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6316851f664031f40b83071bc15adb03\transformed\legacy-support-core-utils-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.legacy:legacy-support-core-utils:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-core-utils:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f72441850923c45aa75245fd7ca98102\transformed\loader-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.loader:loader:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.loader:loader:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9dc43b1ef6ea4e62e3378e37a4247f07\transformed\lifecycle-livedata-2.6.2\AndroidManifest.xml 
Merging manifest with lower [androidx.lifecycle:lifecycle-livedata:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-livedata:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3458cd08c021da105f701fc15f09ea17\transformed\activity-1.7.2\AndroidManifest.xml
Merging manifest with lower [androidx.activity:activity:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c354034cefbd9814d2b305e4cda410ee\transformed\savedstate-ktx-1.2.1\AndroidManifest.xml     
Merging manifest with lower [androidx.savedstate:savedstate-ktx:1.2.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.savedstate:savedstate-ktx:1.2.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c15d40a248a43ccdee67d6766fcb6f26\transformed\savedstate-1.2.1\AndroidManifest.xml
Merging manifest with lower [androidx.savedstate:savedstate:1.2.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.savedstate:savedstate:1.2.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d5f63eac777bcfe6b30d61b995a1c45\transformed\lifecycle-viewmodel-savedstate-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\35c18b15d8de5830b76dc4f86b05d870\transformed\litr-1.4.16\AndroidManifest.xml
Merging manifest with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:7:1-16:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:10:5-12:41
application defined in both files...
Merging application with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:14:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b0b2bcd485495502849906c506e947e6\transformed\media3-exoplayer-dash-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-exoplayer-dash:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer-dash:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2218549c45fe27b24d3d903e2ebcb2b9\transformed\media3-exoplayer-hls-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-exoplayer-hls:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer-hls:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be618a094c6eadd34494571884db27e0\transformed\media3-exoplayer-1.1.1\AndroidManifest.xml   
Merging manifest with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:17:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:20:5-22:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:24:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\38e0bd13d79fca62275fdd12c283e43a\transformed\media3-extractor-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-extractor:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-extractor:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c4e7b649c5c69897c9466a532c6dc9a\transformed\media3-container-1.1.1\AndroidManifest.xml   
Merging manifest with lower [androidx.media3:media3-container:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-container:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bfcbb13e13c03764b50018d4fbf6b43\transformed\media3-datasource-1.1.1\AndroidManifest.xml  
Merging manifest with lower [androidx.media3:media3-datasource:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-datasource:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d0c36692780219799cce233fa774885\transformed\media3-decoder-1.1.1\AndroidManifest.xml     
Merging manifest with lower [androidx.media3:media3-decoder:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-decoder:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d03c1b0898c92f40bf904e2184a2540\transformed\media3-database-1.1.1\AndroidManifest.xml    
Merging manifest with lower [androidx.media3:media3-database:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-database:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\353aa858e1189c1e15533a12e4bde9b1\transformed\media3-common-1.1.1\AndroidManifest.xml      
Merging manifest with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:17:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:20:5-22:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:24:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\74feaf88e5d6ab5c88c4da35184e5d8f\transformed\media3-ui-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-ui:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-ui:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f80a44333a3d0d94d28b94082a80904e\transformed\recyclerview-1.3.0\AndroidManifest.xml       
Merging manifest with lower [androidx.recyclerview:recyclerview:1.3.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.recyclerview:recyclerview:1.3.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7666ab3b28cb3924d9f5e098debd7d8f\transformed\customview-poolingcontainer-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.customview:customview-poolingcontainer:1.0.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.customview:customview-poolingcontainer:1.0.0] AndroidManifest.xml:20:5-21:38
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cd3359b6cbdaa1bef10e17bd7b91672b\transformed\core-ktx-1.12.0\AndroidManifest.xml
Merging manifest with lower [androidx.core:core-ktx:1.12.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.core:core-ktx:1.12.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3569a112fda6949dbc9bf6f86c1158a1\transformed\browser-1.4.0\AndroidManifest.xml
Merging manifest with lower [androidx.browser:browser:1.4.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.browser:browser:1.4.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62524a5742b998acb8b5c5dd6647626\transformed\autofill-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.autofill:autofill:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.autofill:autofill:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acca26e9976f0a24c694c71b93c16e0b\transformed\coordinatorlayout-1.1.0\AndroidManifest.xml  
Merging manifest with lower [androidx.coordinatorlayout:coordinatorlayout:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.coordinatorlayout:coordinatorlayout:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\250967770574026fa0b82276e0192fb6\transformed\transition-1.2.0\AndroidManifest.xml
Merging manifest with lower [androidx.transition:transition:1.2.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.transition:transition:1.2.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\687b04085f858c1dee970df17d424330\transformed\vectordrawable-animated-1.1.0\AndroidManifest.xml
Merging manifest with lower [androidx.vectordrawable:vectordrawable-animated:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.vectordrawable:vectordrawable-animated:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d6cb56e8858a9d0945b32a60153227\transformed\vectordrawable-1.1.0\AndroidManifest.xml     
Merging manifest with lower [androidx.vectordrawable:vectordrawable:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.vectordrawable:vectordrawable:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4b9d4797d7708ad60359aebb321d1f\transformed\media-1.6.0\AndroidManifest.xml
Merging manifest with lower [androidx.media:media:1.6.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media:media:1.6.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\43f5976b58f6a822c0f2d52c2a4f44aa\transformed\viewpager-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.viewpager:viewpager:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.viewpager:viewpager:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f1f690e76c48498566958dbcc549ce17\transformed\drawerlayout-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.drawerlayout:drawerlayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.drawerlayout:drawerlayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6064e677c16d5c58259d0cf627b5d8fd\transformed\slidingpanelayout-1.0.0\AndroidManifest.xml  
Merging manifest with lower [androidx.slidingpanelayout:slidingpanelayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.slidingpanelayout:slidingpanelayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\da2ed364818c61fb6178b7e31ec2a21d\transformed\customview-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.customview:customview:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.customview:customview:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b3f05fe21219c1462de54f164f791ff\transformed\swiperefreshlayout-1.0.0\AndroidManifest.xml 
Merging manifest with lower [androidx.swiperefreshlayout:swiperefreshlayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.swiperefreshlayout:swiperefreshlayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\266a76d3d546a9e118935d822877f314\transformed\asynclayoutinflater-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.asynclayoutinflater:asynclayoutinflater:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.asynclayoutinflater:asynclayoutinflater:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml
Merging manifest with lower [androidx.core:core:1.12.0] AndroidManifest.xml:17:1-30:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.core:core:1.12.0] AndroidManifest.xml:20:5-44
Adopted [permission: null]
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [androidx.core:core:1.12.0] AndroidManifest.xml:28:5-89
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd1d24dd52e67068d44215574926172c\transformed\lifecycle-runtime-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-runtime:2.6.2] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-runtime:2.6.2] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\815557c2ae8a08884db25c998588790c\transformed\lifecycle-viewmodel-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aebb7c5ae338a679f119370e152f8566\transformed\lifecycle-viewmodel-compose-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e8fa69f2fc9288c4e50f2e3d7809464\transformed\accompanist-drawablepainter-0.32.0\AndroidManifest.xml
Merging manifest with lower [com.google.accompanist:accompanist-drawablepainter:0.32.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.accompanist:accompanist-drawablepainter:0.32.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d9088c59b0fab5a007d7b04dd92a9f\transformed\ui-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\abc4525411768185054fd60bb82d62d9\transformed\ui-test-manifest-1.6.1\AndroidManifest.xml   
Merging manifest with lower [androidx.compose.ui:ui-test-manifest:1.6.1] AndroidManifest.xml:17:1-28:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-test-manifest:1.6.1] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [androidx.compose.ui:ui-test-manifest:1.6.1] AndroidManifest.xml:22:5-26:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d115dd7252bbf9ed6b0cc33f5ea878\transformed\ui-tooling-release\AndroidManifest.xml       
Merging manifest with lower [androidx.compose.ui:ui-tooling-android:1.6.1] AndroidManifest.xml:17:1-28:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-tooling-android:1.6.1] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [androidx.compose.ui:ui-tooling-android:1.6.1] AndroidManifest.xml:22:5-26:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\431591700a7d4c4a2d020513f2985bf1\transformed\activity-compose-1.7.2\AndroidManifest.xml   
Merging manifest with lower [androidx.activity:activity-compose:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity-compose:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5638fabe0d5d244a1e5bdb4f9ebbd76e\transformed\activity-ktx-1.7.2\AndroidManifest.xml       
Merging manifest with lower [androidx.activity:activity-ktx:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity-ktx:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\97fba2ce58e83f82cd34056ad1b2f944\transformed\lifecycle-runtime-ktx-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-runtime-ktx:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-runtime-ktx:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3c0ebdfb4381c963073963792850dfb\transformed\napier-debug\AndroidManifest.xml
Merging manifest with lower [io.github.aakira:napier-android-debug:1.4.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.github.aakira:napier-android-debug:1.4.1] AndroidManifest.xml:7:5-9:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\093f96a4c1a2638e480d7fbf99d765df\transformed\annotation-experimental-1.4.0\AndroidManifest.xml
Merging manifest with lower [androidx.annotation:annotation-experimental:1.4.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.annotation:annotation-experimental:1.4.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\07daafe716d893f743308b2174374580\transformed\versionedparcelable-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:17:1-27:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:20:5-22:41
application defined in both files...
Merging application with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:24:5-25:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9337281adcc4664583144eb68a4d78\transformed\firebase-components-17.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-components:17.1.0] AndroidManifest.xml:15:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-components:17.1.0] AndroidManifest.xml:18:5-20:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23f8a3b57d3b678bff82e4e4a2b0d8da\transformed\interpolator-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.interpolator:interpolator:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.interpolator:interpolator:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml       
Merging manifest with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:17:1-31:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:20:5-22:41
application defined in both files...
Merging application with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:24:5-29:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\657dcbddb3a2b9483703680d23a453a9\transformed\core-runtime-2.2.0\AndroidManifest.xml       
Merging manifest with lower [androidx.arch.core:core-runtime:2.2.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.arch.core:core-runtime:2.2.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9689239b917968370ee83770f980d8d3\transformed\localbroadcastmanager-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.localbroadcastmanager:localbroadcastmanager:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.localbroadcastmanager:localbroadcastmanager:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml
Merging manifest with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:15:1-38:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:19:5-21:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:25:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:26:5-67        
application defined in both files...
Merging application with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:28:5-36:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml  
Merging manifest with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:15:1-41:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:18:5-20:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:22:5-79
application defined in both files...
Merging application with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:25:5-39:19
Adopted [service: null]
Adopted [receiver: null]
service#com.google.android.datatransport.runtime.backends.TransportBackendDiscovery defined in both files...
Merging service#com.google.android.datatransport.runtime.backends.TransportBackendDiscovery with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:36:9-38:40
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\154e3c535edbdd29d1f6d63c0fbc89b3\transformed\transport-api-2.2.1\AndroidManifest.xml      
Merging manifest with lower [com.google.android.datatransport:transport-api:2.2.1] AndroidManifest.xml:15:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-api:2.2.1] AndroidManifest.xml:18:5-20:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\085c58d7ccd3350d93d2385455be3130\transformed\firebase-encoders-json-17.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-encoders-json:17.1.0] AndroidManifest.xml:15:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-encoders-json:17.1.0] AndroidManifest.xml:19:5-21:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d4e55c19a61023e2414aba427f7766fd\transformed\viewbinding-7.4.2\AndroidManifest.xml        
Merging manifest with lower [androidx.databinding:viewbinding:7.4.2] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.databinding:viewbinding:7.4.2] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml   
Merging manifest with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:17:1-55:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:23:5-53:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Adopted [receiver: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3bf891793924d464a72841108eb13580\transformed\startup-runtime-1.1.1\AndroidManifest.xml    
Merging manifest with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:17:1-33:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:21:5-23:41
application defined in both files...
Merging application with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:25:5-31:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:26:9-30:34
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\42cfd231f4c63e7c04d46c8dd3aaa08c\transformed\tracing-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.tracing:tracing:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.tracing:tracing:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\26f543fe221eecfd6592bfeb6d24ea8a\transformed\documentfile-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.documentfile:documentfile:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.documentfile:documentfile:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69f3a9b0a03b997a31ffb8beeeb09361\transformed\print-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.print:print:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.print:print:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c5f9a7307d88baffe4c41a6e6374054d\transformed\exifinterface-1.3.6\AndroidManifest.xml      
Merging manifest with lower [androidx.exifinterface:exifinterface:1.3.6] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.exifinterface:exifinterface:1.3.6] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3336ea62311c9c6f6a89ac10bc23051\transformed\cardview-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.cardview:cardview:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.cardview:cardview:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0d615bf23df10d96d1b53dd453627014\transformed\sqlite-framework-2.1.0\AndroidManifest.xml   
Merging manifest with lower [androidx.sqlite:sqlite-framework:2.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.sqlite:sqlite-framework:2.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7329e813a90b3c51a16cb2cf2e9da6e6\transformed\sqlite-2.1.0\AndroidManifest.xml
Merging manifest with lower [androidx.sqlite:sqlite:2.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.sqlite:sqlite:2.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc457ff0ddba235c1477d57ed6838f6d\transformed\cursoradapter-1.0.0\AndroidManifest.xml      
Merging manifest with lower [androidx.cursoradapter:cursoradapter:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.cursoradapter:cursoradapter:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f8b1e25dbb65cc9b4986eb5d4812818\transformed\grpc-android-1.52.1\AndroidManifest.xml      
Merging manifest with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:5:5-7:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:9:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c14b1f0304087127f8d0d5d65ec44ac4\transformed\protolite-well-known-types-18.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:protolite-well-known-types:18.0.0] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:protolite-well-known-types:18.0.0] AndroidManifest.xml:7:5-9:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1debf71d1d368496a17f77aa5e301f0\transformed\image-1.0.0-beta1\AndroidManifest.xml        
Merging manifest with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23264cf826b21068683bc0ce78f43f3e\transformed\fresco-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:fresco:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:fresco:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f5ef6ded1df61098fe18bc6c64e9b21\transformed\drawee-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:drawee:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:drawee:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\50d0d0f04287cb367c1ccf2956360961\transformed\nativeimagefilters-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:nativeimagefilters:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:nativeimagefilters:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b7fa33f44f3b80d898f9552ac844245\transformed\memory-type-native-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:memory-type-native:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-native:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a4fe5708b949a2d6a5e628c9527e07b\transformed\memory-type-java-2.6.0\AndroidManifest.xml   
Merging manifest with lower [com.facebook.fresco:memory-type-java:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-java:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d69946b300ec87149af263e1a8b97dd0\transformed\imagepipeline-native-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:imagepipeline-native:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline-native:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3be242a23f4988743ed36ef1693ed26f\transformed\memory-type-ashmem-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:memory-type-ashmem:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-ashmem:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aaa20b67155259c75fb91f26fa0a8b93\transformed\imagepipeline-2.6.0\AndroidManifest.xml      
Merging manifest with lower [com.facebook.fresco:imagepipeline:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dab1ba1b4aa7f5db10c1872e11a73484\transformed\nativeimagetranscoder-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:nativeimagetranscoder:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:nativeimagetranscoder:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b36b9dee027cfeda0b9fb3d854894fd5\transformed\imagepipeline-base-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:imagepipeline-base:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline-base:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d73423f7dad861f055ffe7756f86f5b1\transformed\soloader-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:soloader:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:soloader:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\282ba00101d4caddb474bbe6d8097617\transformed\soloader-0.10.1\AndroidManifest.xml
Merging manifest with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:2:1-13:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:7:5-9:41
application defined in both files...
Merging application with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:11:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9c7bf14cb4e9247c6082c2483ba8be\transformed\middleware-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:middleware:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:middleware:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\30fcdd209d1f98a1d3e17edc923138fb\transformed\ui-common-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:ui-common:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:ui-common:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3808632869cf9db222c403621fd36c3\transformed\fbcore-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:fbcore:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:fbcore:2.6.0] AndroidManifest.xml:5:5-7:41
Merging result: SUCCESS
1<?xml version="1.0" encoding="utf-8"?>
2<manifest xmlns:android="http://schemas.android.com/apk/res/android"
3    package="com.pluck"
4    android:versionCode="1"
5    android:versionName="1.0" >
6
7    <uses-sdk
8        android:minSdkVersion="26"
9        android:targetSdkVersion="34" />
10
11    <uses-permission android:name="android.permission.CAMERA" />
11-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:4:5-65
11-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:4:22-62
12    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
12-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:5:5-76
12-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:5:22-73
13    <uses-permission
13-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:6:5-7:38
14        android:name="android.permission.READ_EXTERNAL_STORAGE"
14-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:6:22-77
15        android:maxSdkVersion="32" />
15-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:7:9-35
16    <uses-permission
16-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:8:5-9:38
17        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
17-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:8:22-78
18        android:maxSdkVersion="32" />
18-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:9:9-35
19    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
19-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:10:5-79
19-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:10:22-76
20    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
20-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:11:5-81
20-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:11:22-78
21
22    <uses-feature
22-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:5-85
23        android:name="android.hardware.camera"
23-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:19-57
24        android:required="false" />
24-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:58-82
25    <uses-feature
25-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:5-95
26        android:name="android.hardware.camera.autofocus"
26-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:19-67
27        android:required="false" />
27-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:68-92
28
29    <uses-permission android:name="android.permission.INTERNET" />
29-->[com.cloudinary:cloudinary-android-core:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml:9:5-67
29-->[com.cloudinary:cloudinary-android-core:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml:9:22-64
30    <uses-permission android:name="android.permission.WAKE_LOCK" />
30-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:25:5-68
30-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:25:22-65
31    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
31-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:26:5-79
31-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:26:22-76
32    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
32-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:27:5-81
32-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:27:22-78
33    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
33-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:28:5-77
33-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:28:22-74
34
35    <uses-feature
35-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:26:5-28:35
36        android:glEsVersion="0x00020000"
36-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:27:9-41
37        android:required="true" />
37-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:28:9-32
38
39    <queries>
39-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:30:5-34:15
40
41        <!-- Needs to be explicitly declared on Android R+ -->
42        <package android:name="com.google.android.apps.maps" />
42-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:33:9-64
42-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:33:18-61
43    </queries>
44
45    <uses-permission android:name="com.google.android.gms.permission.AD_ID" />
45-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:25:5-79
45-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:25:22-76
46    <uses-permission android:name="com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE" />
46-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:26:5-110
46-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:26:22-107
47
48    <permission
48-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:22:5-24:47
49        android:name="com.pluck.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"
49-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:23:9-81
50        android:protectionLevel="signature" />
50-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:24:9-44
51
52    <uses-permission android:name="com.pluck.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION" />
52-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:26:5-97
52-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:26:22-94
53
54    <application
54-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:16:5-49:19
55        android:name="com.pluck.PLuckApp"
55-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:17:9-33
56        android:allowBackup="true"
56-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:18:9-35
57        android:appComponentFactory="androidx.core.app.CoreComponentFactory"
57-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:28:18-86
58        android:debuggable="true"
59        android:extractNativeLibs="false"
60        android:icon="@drawable/ic_launcher_pluck"
60-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:19:9-51
61        android:label="@string/app_name"
61-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:20:9-41
62        android:supportsRtl="true"
62-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:21:9-35
63        android:theme="@style/Theme.PLuck" >
63-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:22:9-43
64
65        <!-- Google Maps API Key -->
66        <meta-data
67            android:name="com.google.android.geo.API_KEY"
67-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:26:13-58
68            android:value="AIzaSyBxbRzj564Eug_xZkWlYNx6pwYkaDnXOwk" />
68-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:27:13-44
69
70        <!-- FileProvider for sharing files (CSV exports, image uploads) -->
71        <provider
72            android:name="androidx.core.content.FileProvider"
72-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:31:13-62
73            android:authorities="com.pluck.fileprovider"
73-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:32:13-64
74            android:exported="false"
74-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:33:13-37
75            android:grantUriPermissions="true" >
75-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:34:13-47
76            <meta-data
76-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:35:13-37:54
77                android:name="android.support.FILE_PROVIDER_PATHS"
77-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:36:17-67
78                android:resource="@xml/file_paths" />
78-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:37:17-51
79        </provider>
80
81        <activity
81-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:40:9-48:20
82            android:name="com.pluck.MainActivity"
82-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:41:13-41
83            android:exported="true"
83-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:42:13-36
84            android:windowSoftInputMode="adjustResize" >
84-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:43:13-55
85            <intent-filter>
85-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:44:13-47:29
86                <action android:name="android.intent.action.MAIN" />
86-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:45:17-69
86-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:45:25-66
87
88                <category android:name="android.intent.category.LAUNCHER" />
88-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:46:17-77
88-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:46:27-74
89            </intent-filter>
90        </activity>
91
92        <service
92-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:24:9-33:19
93            android:name="androidx.camera.core.impl.MetadataHolderService"
93-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:25:13-75
94            android:enabled="false"
94-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:26:13-36
95            android:exported="false" >
95-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:27:13-37
96            <meta-data
96-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:30:13-32:89
97                android:name="androidx.camera.core.impl.MetadataHolderService.DEFAULT_CONFIG_PROVIDER"
97-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:31:17-103
98                android:value="androidx.camera.camera2.Camera2Config$DefaultProvider" />
98-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:32:17-86
99        </service>
100
101        <activity
101-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:10:9-13:56
102            android:name="com.cloudinary.android.uploadwidget.ui.UploadWidgetActivity"
102-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:11:13-87
103            android:configChanges="orientation"
103-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:12:13-48
104            android:theme="@style/UploadWidgetTheme" />
104-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:13:13-53
105
106        <provider
106-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:31:9-39:20
107            android:name="androidx.startup.InitializationProvider"
107-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:32:13-67
108            android:authorities="com.pluck.androidx-startup"
108-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:33:13-68
109            android:exported="false" >
109-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:34:13-37
110            <meta-data
110-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:36:13-38:52
111                android:name="androidx.work.WorkManagerInitializer"
111-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:37:17-68
112                android:value="androidx.startup" />
112-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:38:17-49
113            <meta-data
113-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:29:13-31:52
114                android:name="androidx.emoji2.text.EmojiCompatInitializer"
114-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:30:17-75
115                android:value="androidx.startup" />
115-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:31:17-49
116            <meta-data
116-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:29:13-31:52
117                android:name="androidx.lifecycle.ProcessLifecycleInitializer"
117-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:30:17-78
118                android:value="androidx.startup" />
118-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:31:17-49
119            <meta-data
119-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:29:13-31:52
120                android:name="androidx.profileinstaller.ProfileInstallerInitializer"
120-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:30:17-85
121                android:value="androidx.startup" />
121-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:31:17-49
122        </provider>
123
124        <service
124-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:41:9-46:35
125            android:name="androidx.work.impl.background.systemalarm.SystemAlarmService"
125-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:42:13-88
126            android:directBootAware="false"
126-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:43:13-44
127            android:enabled="@bool/enable_system_alarm_service_default"
127-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:44:13-72
128            android:exported="false" />
128-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:45:13-37
129        <service
129-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:47:9-53:35
130            android:name="androidx.work.impl.background.systemjob.SystemJobService"
130-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:48:13-84
131            android:directBootAware="false"
131-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:49:13-44
132            android:enabled="@bool/enable_system_job_service_default"
132-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:50:13-70
133            android:exported="true"
133-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:51:13-36
134            android:permission="android.permission.BIND_JOB_SERVICE" />
134-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:52:13-69
135        <service
135-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:54:9-59:35
136            android:name="androidx.work.impl.foreground.SystemForegroundService"
136-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:55:13-81
137            android:directBootAware="false"
137-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:56:13-44
138            android:enabled="@bool/enable_system_foreground_service_default"
138-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:57:13-77
139            android:exported="false" />
139-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:58:13-37
140
141        <receiver
141-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:61:9-66:35
142            android:name="androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver"
142-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:62:13-88
143            android:directBootAware="false"
143-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:63:13-44
144            android:enabled="true"
144-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:64:13-35
145            android:exported="false" />
145-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:65:13-37
146        <receiver
146-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:67:9-77:20
147            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy"
147-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:68:13-106
148            android:directBootAware="false"
148-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:69:13-44
149            android:enabled="false"
149-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:70:13-36
150            android:exported="false" >
150-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:71:13-37
151            <intent-filter>
151-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:73:13-76:29
152                <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
152-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:74:17-87
152-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:74:25-84
153                <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
153-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:75:17-90
153-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:75:25-87
154            </intent-filter>
155        </receiver>
156        <receiver
156-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:78:9-88:20
157            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy"
157-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:79:13-104
158            android:directBootAware="false"
158-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:80:13-44
159            android:enabled="false"
159-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:81:13-36
160            android:exported="false" >
160-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:82:13-37
161            <intent-filter>
161-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:84:13-87:29
162                <action android:name="android.intent.action.BATTERY_OKAY" />
162-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:85:17-77
162-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:85:25-74
163                <action android:name="android.intent.action.BATTERY_LOW" />
163-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:86:17-76
163-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:86:25-73
164            </intent-filter>
165        </receiver>
166        <receiver
166-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:89:9-99:20
167            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy"
167-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:90:13-104
168            android:directBootAware="false"
168-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:91:13-44
169            android:enabled="false"
169-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:92:13-36
170            android:exported="false" >
170-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:93:13-37
171            <intent-filter>
171-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:95:13-98:29
172                <action android:name="android.intent.action.DEVICE_STORAGE_LOW" />
172-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:96:17-83
172-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:96:25-80
173                <action android:name="android.intent.action.DEVICE_STORAGE_OK" />
173-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:97:17-82
173-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:97:25-79
174            </intent-filter>
175        </receiver>
176        <receiver
176-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:100:9-109:20
177            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy"
177-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:101:13-103
178            android:directBootAware="false"
178-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:102:13-44
179            android:enabled="false"
179-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:103:13-36
180            android:exported="false" >
180-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:104:13-37
181            <intent-filter>
181-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:106:13-108:29
182                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
182-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:107:17-79
182-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:107:25-76
183            </intent-filter>
184        </receiver>
185        <receiver
185-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:110:9-121:20
186            android:name="androidx.work.impl.background.systemalarm.RescheduleReceiver"
186-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:111:13-88
187            android:directBootAware="false"
187-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:112:13-44
188            android:enabled="false"
188-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:113:13-36
189            android:exported="false" >
189-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:114:13-37
190            <intent-filter>
190-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:116:13-120:29
191                <action android:name="android.intent.action.BOOT_COMPLETED" />
191-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:117:17-79
191-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:117:25-76
192                <action android:name="android.intent.action.TIME_SET" />
192-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:118:17-73
192-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:118:25-70
193                <action android:name="android.intent.action.TIMEZONE_CHANGED" />
193-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:119:17-81
193-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:119:25-78
194            </intent-filter>
195        </receiver>
196        <receiver
196-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:122:9-131:20
197            android:name="androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver"
197-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:123:13-99
198            android:directBootAware="false"
198-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:124:13-44
199            android:enabled="@bool/enable_system_alarm_service_default"
199-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:125:13-72
200            android:exported="false" >
200-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:126:13-37
201            <intent-filter>
201-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:128:13-130:29
202                <action android:name="androidx.work.impl.background.systemalarm.UpdateProxies" />
202-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:129:17-98
202-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:129:25-95
203            </intent-filter>
204        </receiver>
205        <receiver
205-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:132:9-142:20
206            android:name="androidx.work.impl.diagnostics.DiagnosticsReceiver"
206-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:133:13-78
207            android:directBootAware="false"
207-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:134:13-44
208            android:enabled="true"
208-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:135:13-35
209            android:exported="true"
209-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:136:13-36
210            android:permission="android.permission.DUMP" >
210-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:137:13-57
211            <intent-filter>
211-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:139:13-141:29
212                <action android:name="androidx.work.diagnostics.REQUEST_DIAGNOSTICS" />
212-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:140:17-88
212-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:140:25-85
213            </intent-filter>
214        </receiver> <!-- Needs to be explicitly declared on P+ -->
215        <uses-library
215-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:39:9-41:40
216            android:name="org.apache.http.legacy"
216-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:40:13-50
217            android:required="false" />
217-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:41:13-37
218
219        <service
219-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:8:9-14:19
220            android:name="com.google.firebase.components.ComponentDiscoveryService"
220-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:9:13-84
221            android:directBootAware="true"
221-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:34:13-43
222            android:exported="false" >
222-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:10:13-37
223            <meta-data
223-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:11:13-13:85
224                android:name="com.google.firebase.components:com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar"
224-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:12:17-116
225                android:value="com.google.firebase.components.ComponentRegistrar" />
225-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:13:17-82
226            <meta-data
226-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:69:13-71:85
227                android:name="com.google.firebase.components:com.google.firebase.auth.FirebaseAuthRegistrar"
227-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:70:17-109
228                android:value="com.google.firebase.components.ComponentRegistrar" />
228-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:71:17-82
229            <meta-data
229-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:12:13-14:85
230                android:name="com.google.firebase.components:com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar"
230-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:13:17-126
231                android:value="com.google.firebase.components.ComponentRegistrar" />
231-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:14:17-82
232            <meta-data
232-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:17:13-19:85
233                android:name="com.google.firebase.components:com.google.firebase.firestore.FirestoreRegistrar"
233-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:18:17-111
234                android:value="com.google.firebase.components.ComponentRegistrar" />
234-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:19:17-82
235            <meta-data
235-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:14:13-16:85
236                android:name="com.google.firebase.components:com.google.firebase.storage.ktx.FirebaseStorageKtxRegistrar"
236-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:15:17-122
237                android:value="com.google.firebase.components.ComponentRegistrar" />
237-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:16:17-82
238            <meta-data
238-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:11:13-13:85
239                android:name="com.google.firebase.components:com.google.firebase.analytics.ktx.FirebaseAnalyticsKtxRegistrar"
239-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:12:17-126
240                android:value="com.google.firebase.components.ComponentRegistrar" />
240-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:13:17-82
241            <meta-data
241-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:14:13-16:85
242                android:name="com.google.firebase.components:com.google.firebase.ktx.FirebaseCommonKtxRegistrar"
242-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:15:17-113
243                android:value="com.google.firebase.components.ComponentRegistrar" />
243-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:16:17-82
244            <meta-data
244-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:32:13-34:85
245                android:name="com.google.firebase.components:com.google.firebase.storage.StorageRegistrar"
245-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:33:17-107
246                android:value="com.google.firebase.components.ComponentRegistrar" />
246-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:34:17-82
247            <meta-data
247-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:31:13-33:85
248                android:name="com.google.firebase.components:com.google.firebase.analytics.connector.internal.AnalyticsConnectorRegistrar"
248-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:32:17-139
249                android:value="com.google.firebase.components.ComponentRegistrar" />
249-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:33:17-82
250            <meta-data
250-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:18:13-20:85
251                android:name="com.google.firebase.components:com.google.firebase.installations.FirebaseInstallationsRegistrar"
251-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:19:17-127
252                android:value="com.google.firebase.components.ComponentRegistrar" />
252-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:20:17-82
253        </service>
254
255        <activity
255-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:29:9-46:20
256            android:name="com.google.firebase.auth.internal.GenericIdpActivity"
256-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:30:13-80
257            android:excludeFromRecents="true"
257-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:31:13-46
258            android:exported="true"
258-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:32:13-36
259            android:launchMode="singleTask"
259-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:33:13-44
260            android:theme="@android:style/Theme.Translucent.NoTitleBar" >
260-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:34:13-72
261            <intent-filter>
261-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:35:13-45:29
262                <action android:name="android.intent.action.VIEW" />
262-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:17-69
262-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:25-66
263
264                <category android:name="android.intent.category.DEFAULT" />
264-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:17-76
264-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:27-73
265                <category android:name="android.intent.category.BROWSABLE" />
265-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:17-78
265-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:27-75
266
267                <data
267-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:41:17-44:51
268                    android:host="firebase.auth"
268-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:42:21-49
269                    android:path="/"
269-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:43:21-37
270                    android:scheme="genericidp" />
270-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:44:21-48
271            </intent-filter>
272        </activity>
273        <activity
273-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:47:9-64:20
274            android:name="com.google.firebase.auth.internal.RecaptchaActivity"
274-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:48:13-79
275            android:excludeFromRecents="true"
275-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:49:13-46
276            android:exported="true"
276-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:50:13-36
277            android:launchMode="singleTask"
277-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:51:13-44
278            android:theme="@android:style/Theme.Translucent.NoTitleBar" >
278-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:52:13-72
279            <intent-filter>
279-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:53:13-63:29
280                <action android:name="android.intent.action.VIEW" />
280-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:17-69
280-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:25-66
281
282                <category android:name="android.intent.category.DEFAULT" />
282-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:17-76
282-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:27-73
283                <category android:name="android.intent.category.BROWSABLE" />
283-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:17-78
283-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:27-75
284
285                <data
285-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:41:17-44:51
286                    android:host="firebase.auth"
286-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:42:21-49
287                    android:path="/"
287-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:43:21-37
288                    android:scheme="recaptcha" />
288-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:44:21-48
289            </intent-filter>
290        </activity>
291
292        <service
292-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:9:9-15:19
293            android:name="com.google.mlkit.common.internal.MlKitComponentDiscoveryService"
293-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:10:13-91
294            android:directBootAware="true"
294-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:17:13-43
295            android:exported="false" >
295-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:11:13-37
296            <meta-data
296-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:12:13-14:85
297                android:name="com.google.firebase.components:com.google.mlkit.vision.barcode.internal.BarcodeRegistrar"
297-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:13:17-120
298                android:value="com.google.firebase.components.ComponentRegistrar" />
298-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:14:17-82
299            <meta-data
299-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:12:13-14:85
300                android:name="com.google.firebase.components:com.google.mlkit.vision.common.internal.VisionCommonRegistrar"
300-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:13:17-124
301                android:value="com.google.firebase.components.ComponentRegistrar" />
301-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:14:17-82
302            <meta-data
302-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:20:13-22:85
303                android:name="com.google.firebase.components:com.google.mlkit.common.internal.CommonComponentRegistrar"
303-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:21:17-120
304                android:value="com.google.firebase.components.ComponentRegistrar" />
304-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:22:17-82
305        </service>
306
307        <provider
307-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:9:9-13:38
308            android:name="com.google.mlkit.common.internal.MlKitInitProvider"
308-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:10:13-78
309            android:authorities="com.pluck.mlkitinitprovider"
309-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:11:13-69
310            android:exported="false"
310-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:12:13-37
311            android:initOrder="99" />
311-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:13:13-35
312
313        <activity
313-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:20:9-22:45
314            android:name="com.google.android.gms.common.api.GoogleApiActivity"
314-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:20:19-85
315            android:exported="false"
315-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:22:19-43
316            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
316-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:21:19-78
317
318        <provider
318-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:25:9-30:39
319            android:name="com.google.firebase.provider.FirebaseInitProvider"
319-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:26:13-77
320            android:authorities="com.pluck.firebaseinitprovider"
320-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:27:13-72
321            android:directBootAware="true"
321-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:28:13-43
322            android:exported="false"
322-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:29:13-37
323            android:initOrder="100" />
323-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:30:13-36
324
325        <receiver
325-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:29:9-33:20
326            android:name="com.google.android.gms.measurement.AppMeasurementReceiver"
326-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:30:13-85
327            android:enabled="true"
327-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:31:13-35
328            android:exported="false" >
328-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:32:13-37
329        </receiver>
330
331        <service
331-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:35:9-38:40
332            android:name="com.google.android.gms.measurement.AppMeasurementService"
332-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:36:13-84
333            android:enabled="true"
333-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:37:13-35
334            android:exported="false" />
334-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:38:13-37
335        <service
335-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:39:9-43:72
336            android:name="com.google.android.gms.measurement.AppMeasurementJobService"
336-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:40:13-87
337            android:enabled="true"
337-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:41:13-35
338            android:exported="false"
338-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:42:13-37
339            android:permission="android.permission.BIND_JOB_SERVICE" />
339-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:43:13-69
340
341        <meta-data
341-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:21:9-23:69
342            android:name="com.google.android.gms.version"
342-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:22:13-58
343            android:value="@integer/google_play_services_version" />
343-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:23:13-66
344
345        <activity
345-->[androidx.compose.ui:ui-test-manifest:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\abc4525411768185054fd60bb82d62d9\transformed\ui-test-manifest-1.6.1\AndroidManifest.xml:23:9-25:39
346            android:name="androidx.activity.ComponentActivity"
346-->[androidx.compose.ui:ui-test-manifest:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\abc4525411768185054fd60bb82d62d9\transformed\ui-test-manifest-1.6.1\AndroidManifest.xml:24:13-63
347            android:exported="true" />
347-->[androidx.compose.ui:ui-test-manifest:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\abc4525411768185054fd60bb82d62d9\transformed\ui-test-manifest-1.6.1\AndroidManifest.xml:25:13-36
348        <activity
348-->[androidx.compose.ui:ui-tooling-android:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d115dd7252bbf9ed6b0cc33f5ea878\transformed\ui-tooling-release\AndroidManifest.xml:23:9-25:39
349            android:name="androidx.compose.ui.tooling.PreviewActivity"
349-->[androidx.compose.ui:ui-tooling-android:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d115dd7252bbf9ed6b0cc33f5ea878\transformed\ui-tooling-release\AndroidManifest.xml:24:13-71
350            android:exported="true" />
350-->[androidx.compose.ui:ui-tooling-android:1.6.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d115dd7252bbf9ed6b0cc33f5ea878\transformed\ui-tooling-release\AndroidManifest.xml:25:13-36
351
352        <service
352-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:25:9-28:40
353            android:name="androidx.room.MultiInstanceInvalidationService"
353-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:26:13-74
354            android:directBootAware="true"
354-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:27:13-43
355            android:exported="false" />
355-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:28:13-37
356        <service
356-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:29:9-35:19
357            android:name="com.google.android.datatransport.runtime.backends.TransportBackendDiscovery"
357-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:30:13-103
358            android:exported="false" >
358-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:31:13-37
359            <meta-data
359-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:32:13-34:39
360                android:name="backend:com.google.android.datatransport.cct.CctBackendFactory"
360-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:33:17-94
361                android:value="cct" />
361-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:34:17-36
362        </service>
363        <service
363-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:26:9-30:19
364            android:name="com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService"
364-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:27:13-117
365            android:exported="false"
365-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:28:13-37
366            android:permission="android.permission.BIND_JOB_SERVICE" >
366-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:29:13-69
367        </service>
368
369        <receiver
369-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:32:9-34:40
370            android:name="com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver"
370-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:33:13-132
371            android:exported="false" />
371-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:34:13-37
372        <receiver
372-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:34:9-52:20
373            android:name="androidx.profileinstaller.ProfileInstallReceiver"
373-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:35:13-76
374            android:directBootAware="false"
374-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:36:13-44
375            android:enabled="true"
375-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:37:13-35
376            android:exported="true"
376-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:38:13-36
377            android:permission="android.permission.DUMP" >
377-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:39:13-57
378            <intent-filter>
378-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:40:13-42:29
379                <action android:name="androidx.profileinstaller.action.INSTALL_PROFILE" />
379-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:41:17-91
379-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:41:25-88
380            </intent-filter>
381            <intent-filter>
381-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:43:13-45:29
382                <action android:name="androidx.profileinstaller.action.SKIP_FILE" />
382-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:44:17-85
382-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:44:25-82
383            </intent-filter>
384            <intent-filter>
384-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:46:13-48:29
385                <action android:name="androidx.profileinstaller.action.SAVE_PROFILE" />
385-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:47:17-88
385-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:47:25-85
386            </intent-filter>
387            <intent-filter>
387-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:49:13-51:29
388                <action android:name="androidx.profileinstaller.action.BENCHMARK_OPERATION" />
388-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:50:17-95
388-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:50:25-92
389            </intent-filter>
390        </receiver>
391    </application>
392
393</manifest>

Merged manifest saved to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifest\debug\AndroidManifest.xml
Resolve mutations for :app:processDebugManifest (Thread[Execution worker Thread 8,5,main]) started.
:app:processDebugManifest (Thread[Execution worker Thread 8,5,main]) started.

> Task :app:processDebugManifest
Caching disabled for task ':app:processDebugManifest' because:
  Build cache is disabled
Task ':app:processDebugManifest' is not up-to-date because:
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\debug has been removed.
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\debug\AndroidManifest.xml has been removed.
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\debug\output-metadata.json has been removed.
Resolve mutations for :app:processDebugManifestForPackage (Thread[Execution worker Thread 8,5,main]) started.
:app:processDebugManifestForPackage (Thread[Execution worker Thread 8,5,main]) started.
Resolve mutations for :app:javaPreCompileDebug (Thread[Execution worker,5,main]) started.
:app:javaPreCompileDebug (Thread[Execution worker,5,main]) started.
Resolve mutations for :app:mergeDebugShaders (Thread[Execution worker Thread 3,5,main]) started.
:app:mergeDebugShaders (Thread[Execution worker Thread 3,5,main]) started.

> Task :app:javaPreCompileDebug
Caching disabled for task ':app:javaPreCompileDebug' because:
  Build cache is disabled
Task ':app:javaPreCompileDebug' is not up-to-date because:
  Output property 'annotationProcessorListFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\annotation_processor_list\debug\annotationProcessors.json has been removed.

> Task :app:mergeDebugShaders
Caching disabled for task ':app:mergeDebugShaders' because:
  Build cache is disabled
Task ':app:mergeDebugShaders' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_shaders\debug\out has been removed.    
The input changes require a full rebuild for incremental task ':app:mergeDebugShaders'.
Resolve mutations for :app:compileDebugShaders (Thread[Execution worker Thread 3,5,main]) started.
:app:compileDebugShaders (Thread[Execution worker Thread 3,5,main]) started.

> Task :app:compileDebugShaders NO-SOURCE                                                                                                                        
Skipping task ':app:compileDebugShaders' as it has no source files and no previous output files.
Resolve mutations for :app:generateDebugAssets (Thread[Execution worker Thread 3,5,main]) started.
:app:generateDebugAssets (Thread[Execution worker Thread 3,5,main]) started.

> Task :app:generateDebugAssets UP-TO-DATE                                                                                                                       
Skipping task ':app:generateDebugAssets' as it has no actions.
Resolve mutations for :app:mergeDebugAssets (Thread[Execution worker Thread 3,5,main]) started.
:app:mergeDebugAssets (Thread[Execution worker Thread 3,5,main]) started.

> Task :app:mergeDebugAssets
Caching disabled for task ':app:mergeDebugAssets' because:
  Build cache is disabled
Task ':app:mergeDebugAssets' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\debug has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\debug\mlkit_barcode_models has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\debug\mlkit_barcode_models\barcode_ssd_mobilenet_v1_dmp25_quant.tflite has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeDebugAssets'.
Resolve mutations for :app:compressDebugAssets (Thread[Execution worker Thread 3,5,main]) started.
:app:compressDebugAssets (Thread[Execution worker Thread 3,5,main]) started.
Resolve mutations for :app:desugarDebugFileDependencies (Thread[Execution worker Thread 9,5,main]) started.
:app:desugarDebugFileDependencies (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:desugarDebugFileDependencies
Caching disabled for task ':app:desugarDebugFileDependencies' because:
  Build cache is disabled
Task ':app:desugarDebugFileDependencies' is not up-to-date because:
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_file_lib_dex_archives\debug has been removed.
  Output property 'outputGlobalSynthetics' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\global_synthetics_file_lib\debug has been removed.
Resolve mutations for :app:checkDebugDuplicateClasses (Thread[Execution worker Thread 9,5,main]) started.
:app:checkDebugDuplicateClasses (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:compressDebugAssets
Caching disabled for task ':app:compressDebugAssets' because:
  Build cache is disabled
Task ':app:compressDebugAssets' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\debug\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\debug\out\assets has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\debug\out\assets\mlkit_barcode_models has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:compressDebugAssets'.
Resolve mutations for :app:mergeDebugJniLibFolders (Thread[included builds,5,main]) started.
:app:mergeDebugJniLibFolders (Thread[Execution worker Thread 5,5,main]) started.

> Task :app:mergeDebugJniLibFolders
Caching disabled for task ':app:mergeDebugJniLibFolders' because:
  Build cache is disabled
Task ':app:mergeDebugJniLibFolders' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_jni_libs\debug\out has been removed.   
The input changes require a full rebuild for incremental task ':app:mergeDebugJniLibFolders'.
Resolve mutations for :app:mergeDebugNativeLibs (Thread[included builds,5,main]) started.
:app:mergeDebugNativeLibs (Thread[included builds,5,main]) started.

> Task :app:mergeDebugResources
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-es\values-es.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-es_values-es.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_add.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_add.xml.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_launcher_pluck.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_launcher_pluck.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-el\values-el.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-el_values-el.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-fr-rCA\values-fr-rCA.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-fr-rCA_values-fr-rCA.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-or\values-or.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-or_values-or.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-uk\values-uk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-uk_values-uk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-w600d
p-land-v13\values-w600dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-w600dp-land-v13_values-w600dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-b+es+
419\values-b+es+419.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-b+es+419_values-b+es+419.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_bell.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_bell.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-fa\values-fa.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-fa_values-fa.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-mn\values-mn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-mn_values-mn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-hdpi-v4\values-hdpi-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-hdpi-v4_values-hdpi-v4.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-fr\values-fr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-fr_values-fr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-en-rXC\values-en-rXC.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-en-rXC_values-en-rXC.arsc.flat    
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-pa\values-pa.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-pa_values-pa.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-uz\values-uz.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-uz_values-uz.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-port\values-port.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-port_values-port.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_home.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_home.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-vi\values-vi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-vi_values-vi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-gl\values-gl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-gl_values-gl.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_profile.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_profile.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-pl\values-pl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-pl_values-pl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-night
-v8\values-night-v8.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-night-v8_values-night-v8.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\xml\file_paths.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\xml_file_paths.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-small
-v4\values-small-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-small-v4_values-small-v4.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-nl\values-nl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-nl_values-nl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\debug\mergeDebugResources\merged.dir\values-gu\values-gu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\values-gu_values-gu.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_qr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\debug\drawable_ic_qr.xml.flat

> Task :app:checkDebugDuplicateClasses
Caching disabled for task ':app:checkDebugDuplicateClasses' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:checkDebugDuplicateClasses' is not up-to-date because:
  Output property 'dummyOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\duplicate_classes_check\debug has been removed.
Resolve mutations for :app:mergeExtDexDebug (Thread[Execution worker Thread 10,5,main]) started.
:app:mergeExtDexDebug (Thread[Execution worker Thread 4,5,main]) started.

> Task :app:processDebugManifestForPackage
Custom actions are attached to task ':app:processDebugManifestForPackage'.
Caching disabled for task ':app:processDebugManifestForPackage' because:
  Build cache is disabled
Task ':app:processDebugManifestForPackage' is not up-to-date because:
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\debug has been removed.
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\debug\AndroidManifest.xml has been removed.
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\debug\output-metadata.json has been removed.

> Task :app:mergeDebugNativeLibs
Caching disabled for task ':app:mergeDebugNativeLibs' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:mergeDebugNativeLibs' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a has been removed.
  and more...
Resolve mutations for :app:mergeLibDexDebug (Thread[included builds,5,main]) started.
:app:mergeLibDexDebug (Thread[included builds,5,main]) started.
Resolve mutations for :app:stripDebugDebugSymbols (Thread[Execution worker Thread 2,5,main]) started.
:app:stripDebugDebugSymbols (Thread[Execution worker Thread 2,5,main]) started.
Resolve mutations for :app:validateSigningDebug (Thread[Execution worker Thread 9,5,main]) started.
:app:validateSigningDebug (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:mergeLibDexDebug
Caching disabled for task ':app:mergeLibDexDebug' because:
  Build cache is disabled
Task ':app:mergeLibDexDebug' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeLibDexDebug has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeLibDexDebug\0 has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeLibDexDebug\1 has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeLibDexDebug'.

> Task :app:validateSigningDebug
Caching disabled for task ':app:validateSigningDebug' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:validateSigningDebug' is not up-to-date because:
  Output property 'dummyOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\validate_signing_config\debug has been removed.
Resolve mutations for :app:writeDebugAppMetadata (Thread[Execution worker Thread 9,5,main]) started.
:app:writeDebugAppMetadata (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:writeDebugAppMetadata
Caching disabled for task ':app:writeDebugAppMetadata' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:writeDebugAppMetadata' is not up-to-date because:
  Output property 'outputFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\app_metadata\debug\app-metadata.properties has been removed.
Resolve mutations for :app:writeDebugSigningConfigVersions (Thread[Execution worker Thread 9,5,main]) started.
:app:writeDebugSigningConfigVersions (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:writeDebugSigningConfigVersions
Caching disabled for task ':app:writeDebugSigningConfigVersions' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:writeDebugSigningConfigVersions' is not up-to-date because:
  Output property 'outputFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\signing_config_versions\debug\signing-config-versions.json has been removed.
Resolve mutations for :app:buildKotlinToolingMetadata (Thread[Execution worker Thread 9,5,main]) started.
:app:buildKotlinToolingMetadata (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:buildKotlinToolingMetadata
Caching disabled for task ':app:buildKotlinToolingMetadata' because:
  Build cache is disabled
  Caching has not been enabled for the task
Task ':app:buildKotlinToolingMetadata' is not up-to-date because:
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlinToolingMetadata has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlinToolingMetadata\kotlin-tooling-metadata.json has been removed.
Resolve mutations for :app:preReleaseBuild (Thread[Execution worker Thread 9,5,main]) started.
:app:preReleaseBuild (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:preReleaseBuild UP-TO-DATE                                                                                                                           
Skipping task ':app:preReleaseBuild' as it has no actions.
Resolve mutations for :app:checkReleaseAarMetadata (Thread[Execution worker Thread 9,5,main]) started.
:app:checkReleaseAarMetadata (Thread[Execution worker Thread 9,5,main]) started.
Resolve mutations for :app:generateReleaseResValues (Thread[Execution worker Thread 13,5,main]) started.
:app:generateReleaseResValues (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:generateReleaseResValues
Caching disabled for task ':app:generateReleaseResValues' because:
  Build cache is disabled
Task ':app:generateReleaseResValues' is not up-to-date because:
  Value of input property 'items' has changed for task ':app:generateReleaseResValues'
Resolve mutations for :app:processReleaseGoogleServices (Thread[Execution worker Thread 15,5,main]) started.
:app:processReleaseGoogleServices (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:processReleaseGoogleServices
Caching disabled for task ':app:processReleaseGoogleServices' because:
  Build cache is disabled
Task ':app:processReleaseGoogleServices' is not up-to-date because:
  Output property 'gmpAppId' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\gmpAppId\release.txt has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\processReleaseGoogleServices has been removed.
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\generated\res\processReleaseGoogleServices\values has been removed.
  and more...
Parsing json file: Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\google-services.json
Resolve mutations for :app:mapReleaseSourceSetPaths (Thread[Execution worker Thread 13,5,main]) started.
:app:mapReleaseSourceSetPaths (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:checkReleaseAarMetadata
Caching disabled for task ':app:checkReleaseAarMetadata' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:checkReleaseAarMetadata' is not up-to-date because:
  Output property 'dummyOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\aar_metadata_check\release has been removed.

> Task :app:mapReleaseSourceSetPaths
Caching disabled for task ':app:mapReleaseSourceSetPaths' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:mapReleaseSourceSetPaths' is not up-to-date because:
  Output property 'filepathMappingFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\source_set_path_map\release\file-map.txt has been removed.
Resolve mutations for :app:generateReleaseResources (Thread[Execution worker Thread 13,5,main]) started.
:app:generateReleaseResources (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:generateReleaseResources
Skipping task ':app:generateReleaseResources' as it has no actions.
Resolve mutations for :app:mergeReleaseResources (Thread[Execution worker Thread 13,5,main]) started.
:app:mergeReleaseResources (Thread[Execution worker Thread 13,5,main]) started.

> Task :app:stripDebugDebugSymbols
Caching disabled for task ':app:stripDebugDebugSymbols' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:stripDebugDebugSymbols' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\debug\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\debug\out\lib has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\debug\out\lib\arm64-v8a has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:stripDebugDebugSymbols'.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: ndk.dir in local.properties is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: ndk.dir in local.properties is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: ndk.dir in local.properties is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: ndk.dir in local.properties is not set
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\armeabi-v7a\libbarhopper_v3.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86\libimage_processing_util_jni.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libimage_processing_util_jni.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86\libnative-imagetranscoder.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86\libimagepipeline.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\armeabi-v7a\libnative-filters.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\armeabi-v7a\libnative-imagetranscoder.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libbarhopper_v3.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libnative-imagetranscoder.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libnative-filters.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\armeabi-v7a\libimagepipeline.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86\libnative-filters.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\armeabi-v7a\libimage_processing_util_jni.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\arm64-v8a\libimagepipeline.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86\libbarhopper_v3.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86_64\libnative-filters.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86_64\libnative-imagetranscoder.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86_64\libimagepipeline.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86_64\libimage_processing_util_jni.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\debug\out\lib\x86_64\libbarhopper_v3.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
Unable to strip the following libraries, packaging them as they are: libbarhopper_v3.so, libimage_processing_util_jni.so, libimagepipeline.so, libnative-filters.so, libnative-imagetranscoder.so.

> Task :app:mergeExtDexDebug
Caching disabled for task ':app:mergeExtDexDebug' because:
  Build cache is disabled
Task ':app:mergeExtDexDebug' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeExtDexDebug has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeExtDexDebug\classes.dex has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeExtDexDebug\classes2.dex has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeExtDexDebug'.
Merging to 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\dex\debug\mergeExtDexDebug' with D8 from all or a subset of dex files
 in C:\Users\Ahmed\.gradle\caches\8.13\transforms\f83fa73add327b21a6d3725ef7ba1537\transformed\navigation-common-2.7.3-runtime\navigation-common-2.7.3-runtime_de
x, C:\Users\Ahmed\.gradle\caches\8.13\transforms\60f7a4b5ac6d99e2fb384d208352daf8\transformed\navigation-runtime-2.7.3-runtime\navigation-runtime-2.7.3-runtime_d
ex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b5397cda854000a885f243a56ba3bf5\transformed\navigation-compose-2.7.3-runtime\navigation-compose-2.7.3-runtime_
dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a36d6413bd297b3a09bbaa6519a83741\transformed\accompanist-permissions-0.32.0-runtime\accompanist-permissions-0.
32.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6d557a9d1de6f31ae2c1790581561eca\transformed\maps-compose-4.3.3-runtime\maps-compose-4.3.3-runtim
e_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2d315c5a8a26b05bbb14829199a73b55\transformed\material3-release-runtime\material3-release-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\2eb288f6ca5dec9fee5e8b12d987f358\transformed\coil-compose-2.5.0-runtime\coil-compose-2.5.0-runtime_dex, C:\Users\Ahmed\.g
radle\caches\8.13\transforms\4af03aa488c92c92d8f85c6b0027ef66\transformed\coil-compose-base-2.5.0-runtime\coil-compose-base-2.5.0-runtime_dex, C:\Users\Ahmed\.gr
adle\caches\8.13\transforms\d8e731840fcb53b2cc6a1f1a19c4077e\transformed\camera-video-1.3.0-runtime\camera-video-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches
\8.13\transforms\2f6cd0dafcf87d595c83620893389ced\transformed\camera-view-1.3.0-runtime\camera-view-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfo
rms\3b2cb59af8b1c0847f52c70849ea50e4\transformed\camera-lifecycle-1.3.0-runtime\camera-lifecycle-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms
\734db4b24a150d630b65739142691e75\transformed\camera-camera2-1.3.0-runtime\camera-camera2-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3f1e5f
96ab76123334474a4dfaa8c442\transformed\camera-core-1.3.0-runtime\camera-core-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fc12cf43d77f0dbb8a7
d9171152e0a61\transformed\cloudinary-android-2.5.0-runtime\cloudinary-android-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d14baee70c893f1e0e
e61e4f46cc941f\transformed\cloudinary-android-ui-2.5.0-runtime\cloudinary-android-ui-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ed4f850080f
ec719302814d9459e3b00\transformed\cloudinary-android-preprocess-2.5.0-runtime\cloudinary-android-preprocess-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13
\transforms\102f0c11a8fc3bd7c00aea97d72ec7f9\transformed\cloudinary-android-download-2.5.0-runtime\cloudinary-android-download-2.5.0-runtime_dex, C:\Users\Ahmed\
.gradle\caches\8.13\transforms\d009f5cf39451051215744680c59bb25\transformed\cloudinary-android-core-2.5.0-runtime\cloudinary-android-core-2.5.0-runtime_dex, C:\U
sers\Ahmed\.gradle\caches\8.13\transforms\7fae4d5295650cbcd88c4e6028d552f2\transformed\work-runtime-2.7.1-runtime\work-runtime-2.7.1-runtime_dex, C:\Users\Ahmed\
.gradle\caches\8.13\transforms\e39e73100e89d94bb9318acd90959f3c\transformed\material-release-runtime\material-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\
8.13\transforms\0d44c91cd64f78a4d18d9d9849edf7b5\transformed\material-icons-core-release-runtime\material-icons-core-release-runtime_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\bf1a0c87e9e4234551d685c506dfcfd4\transformed\material-icons-extended-release-runtime\material-icons-extended-release-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\33e0fa3e4b380f36bcfbda96a960ce5c\transformed\material-ripple-release-runtime\material-ripple-release-runtime_dex, C:\Users\
Ahmed\.gradle\caches\8.13\transforms\9535636bd61cc487aa649293390b7ee7\transformed\animation-core-release-runtime\animation-core-release-runtime_dex, C:\Users\Ahm
ed\.gradle\caches\8.13\transforms\28bcbc921f9e2f583977c5d729ee6c1b\transformed\animation-release-runtime\animation-release-runtime_dex, C:\Users\Ahmed\.gradle\ca
ches\8.13\transforms\aeb4faf5d17b314a4e2aad305ce5333c\transformed\foundation-layout-release-runtime\foundation-layout-release-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\c92bdba019337a64d2b65e0c3300cc92\transformed\foundation-release-runtime\foundation-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.1
3\transforms\096822115abc1f0bb661bfa9a552c073\transformed\ui-tooling-data-release-runtime\ui-tooling-data-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13
\transforms\02693d73c4aad2abcf13dba997e2c5e2\transformed\ui-unit-release-runtime\ui-unit-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\56f3f
b865cbc9926d8825f1c4fb63855\transformed\ui-geometry-release-runtime\ui-geometry-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\662fa02b3a4a85
abc6c5f2fa09302018\transformed\ui-util-release-runtime\ui-util-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1a6ae6697ec77470d73406b9d327cb2
9\transformed\ui-graphics-release-runtime\ui-graphics-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2facbbcd82640610e7860968c919a37a\transfo
rmed\ui-tooling-preview-release-runtime\ui-tooling-preview-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\313e20976b3df9d0ef7700a3451d14db\tr
ansformed\ui-text-release-runtime\ui-text-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\46be5ef166c141141938675218af1abb\transformed\materia
l-1.4.0-runtime\material-1.4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\54eb8d67d580b0f95cea57af93640f0c\transformed\coil-2.5.0-runtime\coil-2.
5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1e0c34eff84b3ef1020781d095ff29f5\transformed\coil-base-2.5.0-runtime\coil-base-2.5.0-runtime_dex, 
C:\Users\Ahmed\.gradle\caches\8.13\transforms\af8f7081d5f410aaed36d5b169469475\transformed\appcompat-resources-1.6.1-runtime\appcompat-resources-1.6.1-runtime_de
x, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d27d5c1ff7ec13e0da10b044fcb76747\transformed\constraintlayout-2.0.1-runtime\constraintlayout-2.0.1-runtime_dex, 
C:\Users\Ahmed\.gradle\caches\8.13\transforms\73538bb264ab806403789a2f37614830\transformed\appcompat-1.6.1-runtime\appcompat-1.6.1-runtime_dex, C:\Users\Ahmed\.g
radle\caches\8.13\transforms\f7205bd551322630fc3e8a039a4e2ed9\transformed\emoji2-views-helper-1.3.0-runtime\emoji2-views-helper-1.3.0-runtime_dex, C:\Users\Ahmed
\.gradle\caches\8.13\transforms\cfd51e5a6bc4ec4cf336e1404568741c\transformed\emoji2-1.3.0-runtime\emoji2-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tr
ansforms\95fdbb1f079087ef208d5c54b9b29003\transformed\lifecycle-process-2.6.2-runtime\lifecycle-process-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tra
nsforms\03940fb63906cbe081a8382b7cbc7d92\transformed\lifecycle-service-2.6.2-runtime\lifecycle-service-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tran
sforms\c16686d36f90f854ebf3f09a29f5cee3\transformed\maps-ktx-5.0.0-runtime\maps-ktx-5.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6d6f7df096c5
d93af6a6e41de3681319\transformed\play-services-maps-18.2.0-runtime\play-services-maps-18.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\63b782689
b0cc96be7ae72575697145e\transformed\firebase-auth-ktx-22.1.1-runtime\firebase-auth-ktx-22.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c5ed28e
334d8913e585f22be1b881be\transformed\firebase-auth-22.1.1-runtime\firebase-auth-22.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e39736ba66a50d1
18dbf87f1fdc12ba8\transformed\firebase-firestore-ktx-24.7.1-runtime\firebase-firestore-ktx-24.7.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c3c
7f08d9676db16e3643c2e338cba8\transformed\barcode-scanning-17.2.0-runtime\barcode-scanning-17.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3b51
ed047c8150d6d46ace398078f43\transformed\play-services-location-21.0.1-runtime\play-services-location-21.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\trans
forms\5ebe91881fe4117ef1dfc9ffad83a46a\transformed\firebase-firestore-24.7.1-runtime\firebase-firestore-24.7.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tr
ansforms\321d8fd1acd03e3d9954a513e394b1c1\transformed\play-services-mlkit-barcode-scanning-18.3.0-runtime\play-services-mlkit-barcode-scanning-18.3.0-runtime_dex
, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f4dcbcda1fd7df0698a53b10fa706371\transformed\barcode-scanning-common-17.0.0-runtime\barcode-scanning-common-17.0.
0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bd179097fc214e29911318a8641f1daf\transformed\vision-common-17.3.0-runtime\vision-common-17.3.0-runti
me_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\40422441c1feac12f090a0e0c84f7b55\transformed\common-18.9.0-runtime\common-18.9.0-runtime_dex, C:\Users\Ahme
d\.gradle\caches\8.13\transforms\a4f1d27d5702bb2998f0253bee957835\transformed\firebase-storage-ktx-20.2.1-runtime\firebase-storage-ktx-20.2.1-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\3b835cc21f445eeaee45d37f0b985dcf\transformed\runtime-saveable-release-runtime\runtime-saveable-release-runtime_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\af026cf026da954ee4b398254184d4c4\transformed\runtime-release-runtime\runtime-release-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\be20e08bd9034d4d616ddfc966871b4e\transformed\kotlinx-coroutines-core-jvm-1.7.3\kotlinx-coroutines-core-jvm-1.7.3_dex, C:\Users\Ahmed\.gra
dle\caches\8.13\transforms\0ca8ac4ff101ca0ed49c6dd85ab900ce\transformed\firebase-analytics-ktx-21.3.0-runtime\firebase-analytics-ktx-21.3.0-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\f162f5ae30b1c7887aa61eb03a89065d\transformed\firebase-common-ktx-20.3.1-runtime\firebase-common-ktx-20.3.1-runtime_dex, C:\
Users\Ahmed\.gradle\caches\8.13\transforms\cdd765016e7fa8bc388c9788136496a2\transformed\recaptcha-18.1.2-runtime\recaptcha-18.1.2-runtime_dex, C:\Users\Ahmed\.gr
adle\caches\8.13\transforms\1b6c5fffd4919be12e3f7a9b95e34e93\transformed\kotlinx-coroutines-play-services-1.7.3\kotlinx-coroutines-play-services-1.7.3_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\096f20de22a102496b705e3f50938a6a\transformed\firebase-storage-20.2.1-runtime\firebase-storage-20.2.1-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\58f3cf3f407c19b08589dcf73a40c592\transformed\play-services-auth-api-phone-17.4.0-runtime\play-services-auth-api-phone-17.
4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7fea8fa6da8033a80a0691bcd7e19bb8\transformed\firebase-appcheck-interop-17.0.0-runtime\firebase-app
check-interop-17.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a2f4f66ce1f23dd75ed7f51a001ce61e\transformed\firebase-database-collection-18.0.1-
runtime\firebase-database-collection-18.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7ecd1c4ccdad82370f4cc7193ea4b1ae\transformed\play-services
-base-18.1.0-runtime\play-services-base-18.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b93eb8cf0ca8da11d869cba60ab3a6cd\transformed\play-servi
ces-measurement-api-21.3.0-runtime\play-services-measurement-api-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\27b4b3b3c9987a49cae0bf01f1a13b
48\transformed\firebase-auth-interop-20.0.0-runtime\firebase-auth-interop-20.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1bf8df899d2fda0051024
95360482291\transformed\firebase-installations-17.0.1-runtime\firebase-installations-17.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\0cb4870fa2
b788f2570ca2069ffaf79a\transformed\firebase-common-20.3.2-runtime\firebase-common-20.3.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\58782c5c247db
4f03e6371907efd3d56\transformed\integrity-1.1.0-runtime\integrity-1.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2523c09bc55d78adbb4ab4eb29923d
5b\transformed\vision-interfaces-16.2.0-runtime\vision-interfaces-16.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\46f700861d4cb35a658fe7a5b6e19
d72\transformed\firebase-installations-interop-17.0.1-runtime\firebase-installations-interop-17.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\17
aced30712e93dd7a521b9b22c39553\transformed\play-services-tasks-18.0.2-runtime\play-services-tasks-18.0.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\f1c39c512c30939fffdc5296d8d581b3\transformed\play-services-measurement-21.3.0-runtime\play-services-measurement-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\cac
hes\8.13\transforms\e24aabb62c71399c1b33b24eb9803efe\transformed\play-services-measurement-sdk-21.3.0-runtime\play-services-measurement-sdk-21.3.0-runtime_dex, C
:\Users\Ahmed\.gradle\caches\8.13\transforms\657859fe6e2d435351a3bee29a243406\transformed\play-services-measurement-impl-21.3.0-runtime\play-services-measurement
-impl-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3f80e8abfc59259594d01d1aed21933\transformed\play-services-ads-identifier-18.0.0-runtime\
play-services-ads-identifier-18.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8dc38213e4ff7e57eeebd355e0395a62\transformed\play-services-measure
ment-sdk-api-21.3.0-runtime\play-services-measurement-sdk-api-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\741b197908cf902d84f9d507650ec0e9\
transformed\play-services-measurement-base-21.3.0-runtime\play-services-measurement-base-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bca9ac
683345b776eece10bf7eee56ea\transformed\play-services-stats-17.0.2-runtime\play-services-stats-17.0.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c
2e4ca84d4b897e0e9363a3b8c95484e\transformed\firebase-measurement-connector-19.0.0-runtime\firebase-measurement-connector-19.0.0-runtime_dex, C:\Users\Ahmed\.grad
le\caches\8.13\transforms\06a03dfe72fd7c9f3a0ce23947258dfe\transformed\play-services-basement-18.1.0-runtime\play-services-basement-18.1.0-runtime_dex, C:\Users\
Ahmed\.gradle\caches\8.13\transforms\698298a2a789e2e8c57bf5a3805e29db\transformed\viewpager2-1.0.0-runtime\viewpager2-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\c
aches\8.13\transforms\1842efbfc67180f177359df1400b0b7e\transformed\fragment-1.3.6-runtime\fragment-1.3.6-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\ee346639192f6d163c22d853b012b00b\transformed\lifecycle-livedata-core-2.6.2-runtime\lifecycle-livedata-core-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.
13\transforms\68415590f0e17db8f346f8c9d2243c40\transformed\lifecycle-viewmodel-ktx-2.6.2-runtime\lifecycle-viewmodel-ktx-2.6.2-runtime_dex, C:\Users\Ahmed\.gradl
e\caches\8.13\transforms\f0b93908dd5776b84938f03a72b92b97\transformed\legacy-support-core-ui-1.0.0-runtime\legacy-support-core-ui-1.0.0-runtime_dex, C:\Users\Ahm
ed\.gradle\caches\8.13\transforms\835f6ab5d6bb598788a197c0ba2cb6bc\transformed\dynamicanimation-1.0.0-runtime\dynamicanimation-1.0.0-runtime_dex, C:\Users\Ahmed\
.gradle\caches\8.13\transforms\bf153a3a59b77cfbd7024bd00b9757bf\transformed\legacy-support-core-utils-1.0.0-runtime\legacy-support-core-utils-1.0.0-runtime_dex, 
C:\Users\Ahmed\.gradle\caches\8.13\transforms\3e673e0a1c54ec857eb1c21ae3fa7aa3\transformed\loader-1.0.0-runtime\loader-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\7ee8a59311996ea37baa30ac0d6a9202\transformed\lifecycle-livedata-2.6.2-runtime\lifecycle-livedata-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\7f4487c5156c543a49a5a2168aa8f0de\transformed\activity-1.7.2-runtime\activity-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transf
orms\854e442219b3bd64396ce27fe39968cc\transformed\savedstate-ktx-1.2.1-runtime\savedstate-ktx-1.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\61
fac2c5647ab96984d2b734470df7ac\transformed\savedstate-1.2.1-runtime\savedstate-1.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6deac755d4fa15b3a
7f495e55a1b0d41\transformed\lifecycle-common-2.6.2\lifecycle-common-2.6.2_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\84aa5eecdf9a961c5b5f925e4d76668f\tra
nsformed\lifecycle-viewmodel-savedstate-2.6.2-runtime\lifecycle-viewmodel-savedstate-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b80a9d6188
6893aa1dfbf05310301ff\transformed\litr-1.4.16-runtime\litr-1.4.16-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a077fdd690f1ef38667e05b6a2414967\tra
nsformed\media3-exoplayer-dash-1.1.1-runtime\media3-exoplayer-dash-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\57c3c11c739e5ea6cb0bfe0bbbc8f
b7d\transformed\media3-exoplayer-hls-1.1.1-runtime\media3-exoplayer-hls-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a3772a89547b104c1e0f499e
afce238a\transformed\media3-exoplayer-1.1.1-runtime\media3-exoplayer-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1ee6054b1b7ccfaad68284670a4
5ed41\transformed\media3-extractor-1.1.1-runtime\media3-extractor-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c74fdbb5d35b9a8002acef9b21b5ec
70\transformed\media3-container-1.1.1-runtime\media3-container-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f26b3de2f7fc6b3fec0fa4fc520773b1\
transformed\media3-datasource-1.1.1-runtime\media3-datasource-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a4ea745fbcc5f9edbaaa6f55ccfbd3e\t
ransformed\media3-decoder-1.1.1-runtime\media3-decoder-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fa0b5f8e334d6ab58e007aa185efd1a9\transfor
med\media3-database-1.1.1-runtime\media3-database-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c78b4a52ce699615fb8d3e3a9bc1c861\transformed\m
edia3-common-1.1.1-runtime\media3-common-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\430a93b80a0b7f4c844f98d93d912def\transformed\media3-ui-
1.1.1-runtime\media3-ui-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c36a9a14e8750a75173b3eb0ef7c5bb\transformed\recyclerview-1.3.0-runtime\
recyclerview-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f4d343e53a13f0991a678ea1eb5370ca\transformed\customview-poolingcontainer-1.0.0-runt
ime\customview-poolingcontainer-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b2e300dbacafb690743fcf4a57139c9a\transformed\core-ktx-1.12.0-run
time\core-ktx-1.12.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c4d5b33acfad16fe3c85470a580204c9\transformed\browser-1.4.0-runtime\browser-1.4.0-
runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\87c63bd016c60874e6293cc19c8d0b92\transformed\autofill-1.0.0-runtime\autofill-1.0.0-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\aa1abce3cc2ab5c433819e021ef4566d\transformed\coordinatorlayout-1.1.0-runtime\coordinatorlayout-1.1.0-runtime_dex, C:\User
s\Ahmed\.gradle\caches\8.13\transforms\2c3f2636a9ddf75e100f6099009602fb\transformed\transition-1.2.0-runtime\transition-1.2.0-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\e72a640389f8ab01596cc2b5acaaa4a1\transformed\vectordrawable-animated-1.1.0-runtime\vectordrawable-animated-1.1.0-runtime_dex, C:\Users\Ah
med\.gradle\caches\8.13\transforms\ec5f6fe5ac8cee1c86a266d7524f07a4\transformed\vectordrawable-1.1.0-runtime\vectordrawable-1.1.0-runtime_dex, C:\Users\Ahmed\.gr
adle\caches\8.13\transforms\dcaf3a9f8fc22780af4a32b35af17e8a\transformed\media-1.6.0-runtime\media-1.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\cefdff97137ba28314fefc9eed1f37f7\transformed\viewpager-1.0.0-runtime\viewpager-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2ab7de522182c1
493a35a5435078723a\transformed\drawerlayout-1.0.0-runtime\drawerlayout-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9dae72a6e4ae3a01b96b50193
ff00c21\transformed\slidingpanelayout-1.0.0-runtime\slidingpanelayout-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bf45a1c8da5a3b50adc3b88c92
63633d\transformed\customview-1.0.0-runtime\customview-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9457036d0e0a70f0cf27036aa8b28b2d\transfor
med\swiperefreshlayout-1.0.0-runtime\swiperefreshlayout-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\45cd593f021eec755d4b0fe414bcb061\transfo
rmed\asynclayoutinflater-1.0.0-runtime\asynclayoutinflater-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\97f275e68ea7e56c7031a3809256eb11\tran
sformed\core-1.12.0-runtime\core-1.12.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7b7df4a0526040b10af457db1b7b699\transformed\lifecycle-runtime
-2.6.2-runtime\lifecycle-runtime-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a27222fdacec7d7e0345b57f0e4f8940\transformed\lifecycle-viewmode
l-2.6.2-runtime\lifecycle-viewmodel-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ea52f88df12bbe3234918abebb60b147\transformed\lifecycle-viewm
odel-compose-2.6.2-runtime\lifecycle-viewmodel-compose-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\373d21e3307940340abb9ffcf8ccfb80\transfor
med\accompanist-drawablepainter-0.32.0-runtime\accompanist-drawablepainter-0.32.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d9673d83f2ad0fcc17d4
0a190cf4e237\transformed\ui-release-runtime\ui-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3bb5ef4f7873efc6abb76fb7a8c3345\transformed\ui
-tooling-release-runtime\ui-tooling-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e0cb6bd21821cd2b9217724a774fdb6c\transformed\activity-comp
ose-1.7.2-runtime\activity-compose-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5192cb483e2dcaa180abf0599c329759\transformed\activity-ktx-1.7
.2-runtime\activity-ktx-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d0018f7f0df007344f6f102f702f567f\transformed\lifecycle-runtime-ktx-2.6.2
-runtime\lifecycle-runtime-ktx-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2fdc8f7cc0b57662833e5a650495d163\transformed\kotlinx-coroutines-a
ndroid-1.7.3\kotlinx-coroutines-android-1.7.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\68fe62ccfa849a45ba9ce75d0aa185dd\transformed\napier-debug-runtim
e\napier-debug-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\811b676c8a3f5cf5618458fd4a0b7012\transformed\annotation-experimental-1.4.0-runtime\anno
tation-experimental-1.4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e85f05bbe2807e785448233d4da0360\transformed\grpc-okhttp-1.52.1\grpc-okhttp-
1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d2c714130dcb4db9a1127e0a49b75f3d\transformed\okhttp-4.12.0\okhttp-4.12.0_dex, C:\Users\Ahmed\.gradle\ca
ches\8.13\transforms\d18463e60db30ae088ca7fdf641e8709\transformed\okio-jvm-3.6.0\okio-jvm-3.6.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\09e4afdb913234
de21d7add3c98fbf28\transformed\versionedparcelable-1.1.1-runtime\versionedparcelable-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f420c8425a1
373e8d928b37f1b138cbf\transformed\collection-jvm-1.4.0\collection-jvm-1.4.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bac989f3645387cc8e01037cf046d31\t
ransformed\firebase-components-17.1.0-runtime\firebase-components-17.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c4aed8bab6c43608d695c31c51b0b
c5c\transformed\interpolator-1.0.0-runtime\interpolator-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6a36cd5892d493ac70bf997d413a290e\transfo
rmed\room-runtime-2.2.5-runtime\room-runtime-2.2.5-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\aeef3d9ed0410d6bc413d4ae6f449f94\transformed\core-r
untime-2.2.0-runtime\core-runtime-2.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5572803ea49fd6e2dd545f4da0d34025\transformed\core-common-2.2.0
\core-common-2.2.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\67b943911cf82a0cf603daed2b1b899a\transformed\localbroadcastmanager-1.0.0-runtime\localbroad
castmanager-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\236f0b1597d70ebaa8ba922baef6da70\transformed\transport-backend-cct-2.3.3-runtime\tra
nsport-backend-cct-2.3.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\04b9fa21a0d329896c713ba2dbc4535f\transformed\transport-runtime-2.2.6-runtime\
transport-runtime-2.2.6-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\4c6d3a349d576a12847bcd2c4e5d00b9\transformed\transport-api-2.2.1-runtime\trans
port-api-2.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9aeef0998d84d11c4dbaed51b8d4f553\transformed\firebase-encoders-json-17.1.0-runtime\fire
base-encoders-json-17.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ece266fc01dbfa015cec111834067a6d\transformed\firebase-encoders-16.1.0\fireba
se-encoders-16.1.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1f231af4f94bea444ccb618aab570021\transformed\viewbinding-7.4.2-runtime\viewbinding-7.4.2-ru
ntime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\54850b93ab3073e01f181a283330b203\transformed\profileinstaller-1.3.1-runtime\profileinstaller-1.3.1-runti
me_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2399f551a28f824c58853eeb11ed0c7c\transformed\startup-runtime-1.1.1-runtime\startup-runtime-1.1.1-runtime_de
x, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4cce56f7b7d58ea7a7b25225bcec6b\transformed\tracing-1.0.0-runtime\tracing-1.0.0-runtime_dex, C:\Users\Ahmed\.gr
adle\caches\8.13\transforms\e0651f68c5e5e4ee914163434ba0bfab\transformed\concurrent-futures-1.1.0\concurrent-futures-1.1.0_dex, C:\Users\Ahmed\.gradle\caches\8.1
3\transforms\6c3cc909688bc14fed11decab6c267ec\transformed\documentfile-1.0.0-runtime\documentfile-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transform
s\887523b003b3e084ea1507c841623519\transformed\print-1.0.0-runtime\print-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\40c36634a19a1cc422f5a13
73255e836\transformed\exifinterface-1.3.6-runtime\exifinterface-1.3.6-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2b7fb9fb05553f8520cdecb4d1aa0d6f
\transformed\cardview-1.0.0-runtime\cardview-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b5ef29cbdd17062aff13e65daf501f9\transformed\sqlite
-framework-2.1.0-runtime\sqlite-framework-2.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bb6a1c648a09f4cfcfd6acc83793b1ab\transformed\sqlite-2.
1.0-runtime\sqlite-2.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ae48e2f4a0ed3083916c9710398920e5\transformed\cursoradapter-1.0.0-runtime\curs
oradapter-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7061458d0a6578c77fb3c80780ef79a5\transformed\room-common-2.2.5\room-common-2.2.5_dex, 
C:\Users\Ahmed\.gradle\caches\8.13\transforms\16cb4b7650e5c5db9095a3101803fc86\transformed\resourceinspection-annotation-1.0.1\resourceinspection-annotation-1.0.
1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\93320bd27225f384a7bb2bd2038152fd\transformed\annotation-jvm-1.7.0\annotation-jvm-1.7.0_dex, C:\Users\Ahmed\.
gradle\caches\8.13\transforms\d48df13bdf48e8ab0492a59253e82d83\transformed\kotlin-stdlib-1.9.22\kotlin-stdlib-1.9.22_dex, C:\Users\Ahmed\.gradle\caches\8.13\tran
sforms\9da9e3d7bfb6083eb9133896c5abf65e\transformed\core-3.5.3\core-3.5.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e73bd1943a55a49535f4619be8cf501e\tra
nsformed\auto-value-annotations-1.6.3\auto-value-annotations-1.6.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d02e00029fa13cd7c9e7269af4572a3\transforme
d\grpc-stub-1.52.1\grpc-stub-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a5dbc43c7598c1385195f7ff04cace17\transformed\grpc-protobuf-lite-1.52.1\grp
c-protobuf-lite-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\47a2428c1101e81b38b1c66f2d600e65\transformed\grpc-android-1.52.1-runtime\grpc-android-1
.52.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\efe1b4c9d0581e62838d944737962aed\transformed\grpc-core-1.52.1\grpc-core-1.52.1_dex, C:\Users\Ahm
ed\.gradle\caches\8.13\transforms\e030f9de030b3f94d753d9251e173665\transformed\grpc-api-1.52.1\grpc-api-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms
\a06828d4099f488937fdf31ad9847d19\transformed\guava-31.1-android\guava-31.1-android_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\82b184c7abb78aef8a483fc990
343c1d\transformed\annotations-23.0.0\annotations-23.0.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\edd6fc5b9717c95d0b22e65ee8815baa\transformed\firebase
-annotations-16.2.0\firebase-annotations-16.2.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fa1ccd819810f2ed46901a5cfd30d5ec\transformed\error_prone_annot
ations-2.14.0\error_prone_annotations-2.14.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\99ab846e2916875f0355480aa4347ffc\transformed\protolite-well-known
-types-18.0.0-runtime\protolite-well-known-types-18.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\729ead6accadd9c1ce11424651173daa\transformed\i
mage-1.0.0-beta1-runtime\image-1.0.0-beta1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ee83c11f12cbfa6f688c0b71a890cc0b\transformed\cloudinary-cor
e-1.35.0\cloudinary-core-1.35.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2e7a0b180358917555b0931740877080\transformed\fresco-2.6.0-runtime\fresco-2.6.0
-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ddda9b0f69716bde895071848112b94b\transformed\javax.inject-1\javax.inject-1_dex, C:\Users\Ahmed\.gradl
e\caches\8.13\transforms\27bb3c214008aa50379efa5fba395dd5\transformed\protobuf-javalite-3.25.0\protobuf-javalite-3.25.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\t
ransforms\888a2b61d3dfedb48de5cbb5b70a4ee3\transformed\drawee-2.6.0-runtime\drawee-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3bee5d8e3c055
09879ee3f3b36a9a016\transformed\nativeimagefilters-2.6.0-runtime\nativeimagefilters-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\84165c780691
4b4f167889aa1a990d32\transformed\memory-type-native-2.6.0-runtime\memory-type-native-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\424def3ef1a
87b041ff30f2d4aa3321d\transformed\memory-type-java-2.6.0-runtime\memory-type-java-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c48ce4a0fe7591
bc72e24946e8fd6ca7\transformed\imagepipeline-native-2.6.0-runtime\imagepipeline-native-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\dd4896e07
c0391c43986a48bf29088d0\transformed\memory-type-ashmem-2.6.0-runtime\memory-type-ashmem-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d156586a
ff76e27919e187d011037db4\transformed\imagepipeline-2.6.0-runtime\imagepipeline-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b9142a54f5abe333
5d1d36aead53c7b\transformed\nativeimagetranscoder-2.6.0-runtime\nativeimagetranscoder-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1b4a4bc424
a9467f5ed1816b20f63038\transformed\imagepipeline-base-2.6.0-runtime\imagepipeline-base-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f54172b68
fb2811eafdfd33d0cebc3bb\transformed\infer-annotation-0.18.0\infer-annotation-0.18.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bd329f53eaa47c760f79524727
974340\transformed\jsr305-3.0.2\jsr305-3.0.2_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bbdcaf9be358322e0ba48f74ee586f4\transformed\perfmark-api-0.25.0\
perfmark-api-0.25.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a3f22dd018ff2f68858689eaf409560\transformed\soloader-2.6.0-runtime\soloader-2.6.0-runtime
_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\91b764ad9589af31d81f6f7fc33697ba\transformed\soloader-0.10.1-runtime\soloader-0.10.1-runtime_dex, C:\Users\Ah
med\.gradle\caches\8.13\transforms\28bb7cba94656aaa0c405d98532d68ed\transformed\nativeloader-0.10.1\nativeloader-0.10.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\t
ransforms\f10227b20c292503bbbed0d2eb1a5911\transformed\middleware-2.6.0-runtime\middleware-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b5fa5
33be4fe9e2d5371468203a387dc\transformed\ui-common-2.6.0-runtime\ui-common-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c04dbf090e59bae2c72150
2819c1d968\transformed\fbcore-2.6.0-runtime\fbcore-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\02e21bf71167bd78ab890e4445a7cbb0\transformed\
grpc-context-1.52.1\grpc-context-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b692fd14a4955dd75361070735438634\transformed\failureaccess-1.0.1\failu
reaccess-1.0.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ebbac34cf43f300f622b78430200e7ee\transformed\checker-qual-3.12.0\checker-qual-3.12.0_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\922d8e0c2f9bfcb8e76ffb6777c4e861\transformed\j2objc-annotations-1.3\j2objc-annotations-1.3_dex, C:\Users\Ahmed\.gradle\c
aches\8.13\transforms\af4f1a69eb212e6086fa616c73de9dfa\transformed\gson-2.9.0\gson-2.9.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ebc334f18b5579ec4cf45
e60e4c36305\transformed\annotations-4.1.1.4\annotations-4.1.1.4_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2f346fa8a42d8e144f5e90499f937442\transformed\a
nimal-sniffer-annotations-1.21\animal-sniffer-annotations-1.21_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1461eb9f3539f46e38b76a2ca1149c2\transformed\co
nstraintlayout-solver-2.0.1\constraintlayout-solver-2.0.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9ee9ffae93f12772f38030f936324d0\transformed\annotat
ion-0.10.1\annotation-0.10.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8f25fe10ef7f98c86313e77594522337\transformed\bolts-tasks-1.4.0\bolts-tasks-1.4.0_
dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\158019c2d4c819f7bb175197c6149054\transformed\kotlin-annotations-jvm-1.3.72\kotlin-annotations-jvm-1.3.72_dex, and from all global synthetics files in

Resolve mutations for :app:packageReleaseResources (Thread[Execution worker Thread 2,5,main]) started.
:app:packageReleaseResources (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:packageReleaseResources
Caching disabled for task ':app:packageReleaseResources' because:
  Build cache is disabled
Task ':app:packageReleaseResources' is not up-to-date because:
  Output property 'dataBindingLayoutInfoOutFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\data_binding_layout_info_type_package\release\out has been removed.
  Output property 'incrementalFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\packageReleaseResources has been removed.
  Output property 'incrementalFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\packageReleaseResources\compile-file-map.properties has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:packageReleaseResources'.
[MergeResources] Inputs are non-incremental full task action.
Resolve mutations for :app:parseReleaseLocalResources (Thread[Execution worker Thread 2,5,main]) started.
:app:parseReleaseLocalResources (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:mergeReleaseResources
Caching disabled for task ':app:mergeReleaseResources' because:
  Build cache is disabled
Task ':app:mergeReleaseResources' is not up-to-date because:
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\release\out has been removed.
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\release\out\multi-v2 has been removed.
  Output property 'blameLogOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res_blame_folder\release\out\multi-v2\release.json has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeReleaseResources'.
[MergeResources] Inputs are non-incremental full task action.
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values\values.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values_values.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-hi\values-hi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-hi_values-hi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w
atch-v21\values-watch-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-watch-v21_values-watch-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-pt\values-pt.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-pt_values-pt.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w
atch-v20\values-watch-v20.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-watch-v20_values-watch-v20.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-z
h-rHK\values-zh-rHK.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-zh-rHK_values-zh-rHK.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-hr\values-hr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-hr_values-hr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-it\values-it.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-it_values-it.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-am\values-am.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-am_values-am.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-is\values-is.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-is_values-is.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-hu\values-hu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-hu_values-hu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-zu\values-zu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-zu_values-zu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
360dp-land-v13\values-h360dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-h360dp-land-v13_values-h360dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-si\values-si.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-si_values-si.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v27\values-v27.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v27_values-v27.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-p
t-rPT\values-pt-rPT.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-pt-rPT_values-pt-rPT.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sl\values-sl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sl_values-sl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sq\values-sq.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sq_values-sq.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-iw\values-iw.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-iw_values-iw.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-b
+sr+Latn\values-b+sr+Latn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-b+sr+Latn_values-b+sr+Latn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
720dp-v13\values-h720dp-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-h720dp-v13_values-h720dp-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ur\values-ur.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ur_values-ur.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-tl\values-tl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-tl_values-tl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-hy\values-hy.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-hy_values-hy.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-th\values-th.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-th_values-th.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sk\values-sk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sk_values-sk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
480dp-land-v13\values-h480dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-h480dp-land-v13_values-h480dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
320dp-port-v13\values-h320dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-h320dp-port-v13_values-h320dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-as\values-as.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-as_values-as.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sr\values-sr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sr_values-sr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v26\values-v26.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v26_values-v26.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-mn\values-mn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-mn_values-mn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ar\values-ar.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ar_values-ar.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v28\values-v28.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v28_values-v28.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ca\values-ca.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ca_values-ca.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-km\values-km.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-km_values-km.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-da\values-da.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-da_values-da.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v16\values-v16.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v16_values-v16.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-te\values-te.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-te_values-te.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-n
ight-v8\values-night-v8.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-night-v8_values-night-v8.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-nb\values-nb.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-nb_values-nb.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ro\values-ro.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ro_values-ro.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-gu\values-gu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-gu_values-gu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v21\values-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v21_values-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
n-rAU\values-en-rAU.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-en-rAU_values-en-rAU.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-or\values-or.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-or_values-or.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ml\values-ml.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ml_values-ml.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-mk\values-mk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-mk_values-mk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-f
r-rCA\values-fr-rCA.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-fr-rCA_values-fr-rCA.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v18\values-v18.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v18_values-v18.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-mr\values-mr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-mr_values-mr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-x
large-v4\values-xlarge-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-xlarge-v4_values-xlarge-v4.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-kk\values-kk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-kk_values-kk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
s-rUS\values-es-rUS.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-es-rUS_values-es-rUS.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w
480dp-port-v13\values-w480dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-w480dp-port-v13_values-w480dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w
600dp-land-v13\values-w600dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-w600dp-land-v13_values-w600dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-b
+es+419\values-b+es+419.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-b+es+419_values-b+es+419.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-z
h-rCN\values-zh-rCN.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-zh-rCN_values-zh-rCN.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ne\values-ne.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ne_values-ne.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-fa\values-fa.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-fa_values-fa.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-tr\values-tr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-tr_values-tr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-be\values-be.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-be_values-be.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-port\values-port.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-port_values-port.arsc.flat      
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-s
w600dp-v13\values-sw600dp-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sw600dp-v13_values-sw600dp-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v17\values-v17.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v17_values-v17.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-gl\values-gl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-gl_values-gl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sv\values-sv.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sv_values-sv.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-pl\values-pl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-pl_values-pl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v23\values-v23.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v23_values-v23.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-pa\values-pa.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-pa_values-pa.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-uz\values-uz.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-uz_values-uz.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w
360dp-port-v13\values-w360dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-w360dp-port-v13_values-w360dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-l
dltr-v21\values-ldltr-v21.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ldltr-v21_values-ldltr-v21.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ru\values-ru.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ru_values-ru.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_launcher_pluck.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_launcher_pluck.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-kn\values-kn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-kn_values-kn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ja\values-ja.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ja_values-ja.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-af\values-af.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-af_values-af.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-in\values-in.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-in_values-in.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v22\values-v22.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v22_values-v22.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-nl\values-nl.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-nl_values-nl.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-bn\values-bn.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-bn_values-bn.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ko\values-ko.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ko_values-ko.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-de\values-de.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-de_values-de.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_qr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_qr.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-az\values-az.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-az_values-az.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v25\values-v25.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v25_values-v25.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
n-rCA\values-en-rCA.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-en-rCA_values-en-rCA.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-lt\values-lt.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-lt_values-lt.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ta\values-ta.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ta_values-ta.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-lo\values-lo.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-lo_values-lo.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-bg\values-bg.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-bg_values-bg.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ms\values-ms.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ms_values-ms.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ky\values-ky.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ky_values-ky.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-bs\values-bs.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-bs_values-bs.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-z
h-rTW\values-zh-rTW.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-zh-rTW_values-zh-rTW.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
n-rIN\values-en-rIN.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-en-rIN_values-en-rIN.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-el\values-el.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-el_values-el.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-sw\values-sw.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-sw_values-sw.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
550dp-port-v13\values-h550dp-port-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-h550dp-port-v13_values-h550dp-port-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-fi\values-fi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-fi_values-fi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-es\values-es.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-es_values-es.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-p
t-rBR\values-pt-rBR.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-pt-rBR_values-pt-rBR.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-et\values-et.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-et_values-et.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
n-rXC\values-en-rXC.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-en-rXC_values-en-rXC.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-lv\values-lv.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-lv_values-lv.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-l
drtl-v17\values-ldrtl-v17.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ldrtl-v17_values-ldrtl-v17.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-eu\values-eu.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-eu_values-eu.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-e
n-rGB\values-en-rGB.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-en-rGB_values-en-rGB.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_profile.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_profile.xml.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_add.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_add.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-h
dpi-v4\values-hdpi-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-hdpi-v4_values-hdpi-v4.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_home.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_home.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-land\values-land.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-land_values-land.arsc.flat      
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-fr\values-fr.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-fr_values-fr.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-my\values-my.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-my_values-my.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\xml\file_paths.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\xml_file_paths.xml.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-l
arge-v4\values-large-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-large-v4_values-large-v4.arsc.flat
Compiling xml file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\res\drawable\ic_bell.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\drawable_ic_bell.xml.flat

Resolve mutations for :app:createReleaseCompatibleScreenManifests (Thread[Execution worker Thread 9,5,main]) started.
:app:createReleaseCompatibleScreenManifests (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:createReleaseCompatibleScreenManifests
Caching disabled for task ':app:createReleaseCompatibleScreenManifests' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:createReleaseCompatibleScreenManifests' is not up-to-date because:
  Output property 'outputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compatible_screen_manifest\release has been removed.
  Output property 'outputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compatible_screen_manifest\release\output-metadata.json has been removed.
Resolve mutations for :app:extractDeepLinksRelease (Thread[Execution worker Thread 9,5,main]) started.
:app:extractDeepLinksRelease (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:parseReleaseLocalResources
Caching disabled for task ':app:parseReleaseLocalResources' because:
  Build cache is disabled
Task ':app:parseReleaseLocalResources' is not up-to-date because:
  Output property 'librarySymbolsFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\local_only_symbol_list\release\R-def.txt has been removed.
The input changes require a full rebuild for incremental task ':app:parseReleaseLocalResources'.

> Task :app:extractDeepLinksRelease
Caching disabled for task ':app:extractDeepLinksRelease' because:
  Build cache is disabled
Task ':app:extractDeepLinksRelease' is not up-to-date because:
  Value of input property 'manifestPlaceholders' has changed for task ':app:extractDeepLinksRelease'
Resolve mutations for :app:processReleaseMainManifest (Thread[Execution worker Thread 9,5,main]) started.
:app:processReleaseMainManifest (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:processReleaseMainManifest
Caching disabled for task ':app:processReleaseMainManifest' because:
  Build cache is disabled
Task ':app:processReleaseMainManifest' is not up-to-date because:
  Value of input property 'manifestPlaceholders' has changed for task ':app:processReleaseMainManifest'
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca7581fdd03ded8825166172f9397972\transformed\navigation-common-2.7.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4a61c7584fb1f9fb321e1fe7981aca7e\transformed\navigation-runtime-2.7.3\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c0cee090e873cd16b94165bdf40ed30d\transformed\navigation-common-ktx-2.7.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d55a05dfc813b771bcca5f09893c7115\transformed\navigation-runtime-ktx-2.7.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ce254a50ba83349b7cad4cd5a47a182b\transformed\navigation-compose-2.7.3\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d898b3bf7f14f3376b620881baa9ac7\transformed\accompanist-permissions-0.32.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\451d346a44b54de4969b9580f180368a\transformed\maps-compose-4.3.3\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c57e699d6d3fdfcc8117035fa000776\transformed\material3-release\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\79a0e9d6e7549546d45b28012505c9ca\transformed\cloudinary-android-2.5.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b2acda9b91f386a67bd7ae9f1a2c7b43\transformed\camera-video-1.3.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a938d1eec6912e763d9fb2fb712a289a\transformed\camera-lifecycle-1.3.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b43eab086c3fd4006c4ab3044874f2dd\transformed\camera-core-1.3.0\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\821c7f8ea92b1eb9f92d724d3dae2a9d\transformed\camera-view-1.3.0\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5690d428903aa172c9993823502c6e91\transformed\cloudinary-android-preprocess-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d0c7b7133ba44e76ad823bb74c2f7b\transformed\cloudinary-android-download-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\182fe93a2c05f703c0be618f4e29b6ea\transformed\material-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3ba880f9761f7dfb48c24e30540796ef\transformed\coil-compose-2.5.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d120d176e03cec0ff11698dbd2491a9\transformed\coil-compose-base-2.5.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b917f088bb1e395972db2b08698549bd\transformed\coil-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2a4d4227201ca2b4355bd833c0587443\transformed\coil-base-2.5.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a42711f265d77257f392b6168bc60944\transformed\appcompat-resources-1.6.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9d26fad69a8035c5a1e8031032bda63\transformed\constraintlayout-2.0.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3527fb198cdd94198153373b9754785d\transformed\appcompat-1.6.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ef0651bbaaf20d6f1ce6842f3cf3ea4\transformed\maps-ktx-5.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc72f96d8c07a6bc682086f6a12afa94\transformed\barcode-scanning-17.2.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1ef8e61defd79da791ebb59714f2e6b\transformed\play-services-location-21.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\44332fe5700c324c2a841fed947afb20\transformed\barcode-scanning-common-17.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1c236d1be356058c5d38a6407020414\transformed\material-icons-core-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b00e01cd2630685c5896c00ed4d387a4\transformed\material-icons-extended-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0bca281d65c6305c18f08d784f02db44\transformed\material-ripple-release\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7ffcccc0c7d0fe0808a1e1d43a5c4bc\transformed\animation-core-release\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b45016de5999ab45e3c6f93f18b8f0b4\transformed\animation-release\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ad701c3da8b6f314a074147b61622f51\transformed\foundation-layout-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75cf2c8d302443c5f5a11437f45fc7cc\transformed\foundation-release\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\901560b52dc43160942dd52a8d5ddb66\transformed\ui-unit-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a0b4f0517b364306f98aab095621a04a\transformed\ui-geometry-release\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a2c176c83e3255c54c294c740419995\transformed\ui-util-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4ab1ce79a4e8be4025b59fbb6dbd8d67\transformed\ui-graphics-release\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e00013d98828add3ca1a88b1ea5b2fea\transformed\ui-tooling-preview-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\216fcb22cce24b735816ff2d550d4b95\transformed\ui-text-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\314c779f047198335435d76c74a49af0\transformed\emoji2-views-helper-1.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\131407770d0a55c5099b1c9bcca5d9b3\transformed\lifecycle-service-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be2d59222a581ea27079e1e5f50bfc22\transformed\lifecycle-livedata-core-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\03c32e1ac4a26acd85b5bcbb19ecee9b\transformed\lifecycle-viewmodel-ktx-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d845df531a260f75739db15ce9396e59\transformed\firebase-analytics-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\80c7788a63291a048d3114fba53e2731\transformed\legacy-support-v4-1.0.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\14bfdc109aaf5d07c0715e526294f3e9\transformed\play-services-measurement-sdk-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a2ceb738eefaa6214c9b16aa63616f0\transformed\play-services-stats-17.0.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a40505be55533e631038632642044d4d\transformed\legacy-support-core-ui-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f66a86c88c7490b271b2934db5002420\transformed\dynamicanimation-1.0.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6316851f664031f40b83071bc15adb03\transformed\legacy-support-core-utils-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f72441850923c45aa75245fd7ca98102\transformed\loader-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9dc43b1ef6ea4e62e3378e37a4247f07\transformed\lifecycle-livedata-2.6.2\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c354034cefbd9814d2b305e4cda410ee\transformed\savedstate-ktx-1.2.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c15d40a248a43ccdee67d6766fcb6f26\transformed\savedstate-1.2.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d5f63eac777bcfe6b30d61b995a1c45\transformed\lifecycle-viewmodel-savedstate-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\35c18b15d8de5830b76dc4f86b05d870\transformed\litr-1.4.16\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b0b2bcd485495502849906c506e947e6\transformed\media3-exoplayer-dash-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2218549c45fe27b24d3d903e2ebcb2b9\transformed\media3-exoplayer-hls-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be618a094c6eadd34494571884db27e0\transformed\media3-exoplayer-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\38e0bd13d79fca62275fdd12c283e43a\transformed\media3-extractor-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c4e7b649c5c69897c9466a532c6dc9a\transformed\media3-container-1.1.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bfcbb13e13c03764b50018d4fbf6b43\transformed\media3-datasource-1.1.1\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d0c36692780219799cce233fa774885\transformed\media3-decoder-1.1.1\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d03c1b0898c92f40bf904e2184a2540\transformed\media3-database-1.1.1\AndroidManifest.xml    
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\353aa858e1189c1e15533a12e4bde9b1\transformed\media3-common-1.1.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\74feaf88e5d6ab5c88c4da35184e5d8f\transformed\media3-ui-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a7b689b97281718ee32092e564efecb5\transformed\viewpager2-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f80a44333a3d0d94d28b94082a80904e\transformed\recyclerview-1.3.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7666ab3b28cb3924d9f5e098debd7d8f\transformed\customview-poolingcontainer-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cd3359b6cbdaa1bef10e17bd7b91672b\transformed\core-ktx-1.12.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dec6106073bd1ccc907541665e674944\transformed\play-services-auth-api-phone-17.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e020070785bab2e428d71a57f14adf\transformed\firebase-appcheck-interop-17.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3296b09c7f64b522e97abed6126219a2\transformed\firebase-database-collection-18.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3569a112fda6949dbc9bf6f86c1158a1\transformed\browser-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62524a5742b998acb8b5c5dd6647626\transformed\autofill-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acca26e9976f0a24c694c71b93c16e0b\transformed\coordinatorlayout-1.1.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\250967770574026fa0b82276e0192fb6\transformed\transition-1.2.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\687b04085f858c1dee970df17d424330\transformed\vectordrawable-animated-1.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d6cb56e8858a9d0945b32a60153227\transformed\vectordrawable-1.1.0\AndroidManifest.xml     
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4b9d4797d7708ad60359aebb321d1f\transformed\media-1.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\43f5976b58f6a822c0f2d52c2a4f44aa\transformed\viewpager-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f1f690e76c48498566958dbcc549ce17\transformed\drawerlayout-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6064e677c16d5c58259d0cf627b5d8fd\transformed\slidingpanelayout-1.0.0\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\da2ed364818c61fb6178b7e31ec2a21d\transformed\customview-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b3f05fe21219c1462de54f164f791ff\transformed\swiperefreshlayout-1.0.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\266a76d3d546a9e118935d822877f314\transformed\asynclayoutinflater-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd1d24dd52e67068d44215574926172c\transformed\lifecycle-runtime-2.6.2\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\815557c2ae8a08884db25c998588790c\transformed\lifecycle-viewmodel-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aebb7c5ae338a679f119370e152f8566\transformed\lifecycle-viewmodel-compose-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e8fa69f2fc9288c4e50f2e3d7809464\transformed\accompanist-drawablepainter-0.32.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d9088c59b0fab5a007d7b04dd92a9f\transformed\ui-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75f0140c7f717d66951ed03f3680fc27\transformed\runtime-saveable-release\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6dc17ed3b6be82e4f8af8c05b8da1b0c\transformed\runtime-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b518a01e24c8367d7c40d73f84fcb476\transformed\recaptcha-18.1.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b2ab533ae5878b9cafe683ac52230b2\transformed\firebase-auth-interop-20.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c8cbc24cb735075edb099d47fc6cfce4\transformed\integrity-1.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\877481486b492ed837440e24d40d376c\transformed\vision-interfaces-16.2.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecde9336b62f39bd954535409773422b\transformed\firebase-installations-interop-17.0.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0e1681c588006a553017c69b9f3ee841\transformed\play-services-tasks-18.0.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\494728eeabc92461d48f2864fe26da46\transformed\play-services-ads-identifier-18.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6649a9bc3f74cab6db315ced24506837\transformed\play-services-measurement-sdk-api-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\99363a2471989efa1468400d1e774fc3\transformed\play-services-measurement-base-21.3.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5e4955c6525be4076f8f44f8317be0e6\transformed\firebase-measurement-connector-19.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a50c744dcf4b5e5e8edce004e0604f76\transformed\fragment-1.3.6\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3458cd08c021da105f701fc15f09ea17\transformed\activity-1.7.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\431591700a7d4c4a2d020513f2985bf1\transformed\activity-compose-1.7.2\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5638fabe0d5d244a1e5bdb4f9ebbd76e\transformed\activity-ktx-1.7.2\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\97fba2ce58e83f82cd34056ad1b2f944\transformed\lifecycle-runtime-ktx-2.6.2\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\db4e7d3ddc290ffaf179433361c13b5c\transformed\napier-release\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\093f96a4c1a2638e480d7fbf99d765df\transformed\annotation-experimental-1.4.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\07daafe716d893f743308b2174374580\transformed\versionedparcelable-1.1.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9337281adcc4664583144eb68a4d78\transformed\firebase-components-17.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23f8a3b57d3b678bff82e4e4a2b0d8da\transformed\interpolator-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\657dcbddb3a2b9483703680d23a453a9\transformed\core-runtime-2.2.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9689239b917968370ee83770f980d8d3\transformed\localbroadcastmanager-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml  
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\154e3c535edbdd29d1f6d63c0fbc89b3\transformed\transport-api-2.2.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\085c58d7ccd3350d93d2385455be3130\transformed\firebase-encoders-json-17.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d4e55c19a61023e2414aba427f7766fd\transformed\viewbinding-7.4.2\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3bf891793924d464a72841108eb13580\transformed\startup-runtime-1.1.1\AndroidManifest.xml    
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\42cfd231f4c63e7c04d46c8dd3aaa08c\transformed\tracing-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\26f543fe221eecfd6592bfeb6d24ea8a\transformed\documentfile-1.0.0\AndroidManifest.xml       
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69f3a9b0a03b997a31ffb8beeeb09361\transformed\print-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c5f9a7307d88baffe4c41a6e6374054d\transformed\exifinterface-1.3.6\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3336ea62311c9c6f6a89ac10bc23051\transformed\cardview-1.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0d615bf23df10d96d1b53dd453627014\transformed\sqlite-framework-2.1.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7329e813a90b3c51a16cb2cf2e9da6e6\transformed\sqlite-2.1.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc457ff0ddba235c1477d57ed6838f6d\transformed\cursoradapter-1.0.0\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f8b1e25dbb65cc9b4986eb5d4812818\transformed\grpc-android-1.52.1\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c14b1f0304087127f8d0d5d65ec44ac4\transformed\protolite-well-known-types-18.0.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1debf71d1d368496a17f77aa5e301f0\transformed\image-1.0.0-beta1\AndroidManifest.xml        
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23264cf826b21068683bc0ce78f43f3e\transformed\fresco-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f5ef6ded1df61098fe18bc6c64e9b21\transformed\drawee-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\50d0d0f04287cb367c1ccf2956360961\transformed\nativeimagefilters-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b7fa33f44f3b80d898f9552ac844245\transformed\memory-type-native-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a4fe5708b949a2d6a5e628c9527e07b\transformed\memory-type-java-2.6.0\AndroidManifest.xml   
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d69946b300ec87149af263e1a8b97dd0\transformed\imagepipeline-native-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3be242a23f4988743ed36ef1693ed26f\transformed\memory-type-ashmem-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aaa20b67155259c75fb91f26fa0a8b93\transformed\imagepipeline-2.6.0\AndroidManifest.xml      
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dab1ba1b4aa7f5db10c1872e11a73484\transformed\nativeimagetranscoder-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b36b9dee027cfeda0b9fb3d854894fd5\transformed\imagepipeline-base-2.6.0\AndroidManifest.xml 
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d73423f7dad861f055ffe7756f86f5b1\transformed\soloader-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\282ba00101d4caddb474bbe6d8097617\transformed\soloader-0.10.1\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9c7bf14cb4e9247c6082c2483ba8be\transformed\middleware-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\30fcdd209d1f98a1d3e17edc923138fb\transformed\ui-common-2.6.0\AndroidManifest.xml
Loading library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3808632869cf9db222c403621fd36c3\transformed\fbcore-2.6.0\AndroidManifest.xml
Merging main manifest Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml

Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca7581fdd03ded8825166172f9397972\transformed\navigation-common-2.7.3\AndroidManifest.xml  
Merging manifest with lower [androidx.navigation:navigation-common:2.7.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-common:2.7.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4a61c7584fb1f9fb321e1fe7981aca7e\transformed\navigation-runtime-2.7.3\AndroidManifest.xml 
Merging manifest with lower [androidx.navigation:navigation-runtime:2.7.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-runtime:2.7.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c0cee090e873cd16b94165bdf40ed30d\transformed\navigation-common-ktx-2.7.3\AndroidManifest.xml
Merging manifest with lower [androidx.navigation:navigation-common-ktx:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-common-ktx:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d55a05dfc813b771bcca5f09893c7115\transformed\navigation-runtime-ktx-2.7.3\AndroidManifest.xml
Merging manifest with lower [androidx.navigation:navigation-runtime-ktx:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-runtime-ktx:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ce254a50ba83349b7cad4cd5a47a182b\transformed\navigation-compose-2.7.3\AndroidManifest.xml 
Merging manifest with lower [androidx.navigation:navigation-compose:2.7.3] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.navigation:navigation-compose:2.7.3] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d898b3bf7f14f3376b620881baa9ac7\transformed\accompanist-permissions-0.32.0\AndroidManifest.xml
Merging manifest with lower [com.google.accompanist:accompanist-permissions:0.32.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.accompanist:accompanist-permissions:0.32.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\451d346a44b54de4969b9580f180368a\transformed\maps-compose-4.3.3\AndroidManifest.xml       
Merging manifest with lower [com.google.maps.android:maps-compose:4.3.3] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.maps.android:maps-compose:4.3.3] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c57e699d6d3fdfcc8117035fa000776\transformed\material3-release\AndroidManifest.xml        
Merging manifest with lower [androidx.compose.material3:material3-android:1.2.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material3:material3-android:1.2.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\79a0e9d6e7549546d45b28012505c9ca\transformed\cloudinary-android-2.5.0\AndroidManifest.xml 
Merging manifest with lower [com.cloudinary:cloudinary-android:2.5.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android:2.5.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b2acda9b91f386a67bd7ae9f1a2c7b43\transformed\camera-video-1.3.0\AndroidManifest.xml       
Merging manifest with lower [androidx.camera:camera-video:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-video:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a938d1eec6912e763d9fb2fb712a289a\transformed\camera-lifecycle-1.3.0\AndroidManifest.xml   
Merging manifest with lower [androidx.camera:camera-lifecycle:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-lifecycle:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml     
Merging manifest with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:17:1-36:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.camera:camera-camera2:1.3.0] AndroidManifest.xml:23:5-34:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b43eab086c3fd4006c4ab3044874f2dd\transformed\camera-core-1.3.0\AndroidManifest.xml        
Merging manifest with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:17:1-36:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:23:5-34:19
service#androidx.camera.core.impl.MetadataHolderService defined in both files...
Merging service#androidx.camera.core.impl.MetadataHolderService with lower [androidx.camera:camera-core:1.3.0] AndroidManifest.xml:29:9-33:78
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\821c7f8ea92b1eb9f92d724d3dae2a9d\transformed\camera-view-1.3.0\AndroidManifest.xml        
Merging manifest with lower [androidx.camera:camera-view:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.camera:camera-view:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:2:1-16:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.cloudinary:cloudinary-android-ui:2.5.0] AndroidManifest.xml:9:5-14:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5690d428903aa172c9993823502c6e91\transformed\cloudinary-android-preprocess-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-preprocess:2.5.0] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-preprocess:2.5.0] AndroidManifest.xml:6:5-9:61
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d0c7b7133ba44e76ad823bb74c2f7b\transformed\cloudinary-android-download-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-download:2.5.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-download:2.5.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml
Merging manifest with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:2:1-13:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:5:5-7:41
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.cloudinary:cloudinary-android-core:2.5.0] AndroidManifest.xml:11:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\182fe93a2c05f703c0be618f4e29b6ea\transformed\material-1.4.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.android.material:material:1.4.0] AndroidManifest.xml:22:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3ba880f9761f7dfb48c24e30540796ef\transformed\coil-compose-2.5.0\AndroidManifest.xml       
Merging manifest with lower [io.coil-kt:coil-compose:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-compose:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d120d176e03cec0ff11698dbd2491a9\transformed\coil-compose-base-2.5.0\AndroidManifest.xml  
Merging manifest with lower [io.coil-kt:coil-compose-base:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-compose-base:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b917f088bb1e395972db2b08698549bd\transformed\coil-2.5.0\AndroidManifest.xml
Merging manifest with lower [io.coil-kt:coil:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2a4d4227201ca2b4355bd833c0587443\transformed\coil-base-2.5.0\AndroidManifest.xml
Merging manifest with lower [io.coil-kt:coil-base:2.5.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.coil-kt:coil-base:2.5.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a42711f265d77257f392b6168bc60944\transformed\appcompat-resources-1.6.1\AndroidManifest.xml
Merging manifest with lower [androidx.appcompat:appcompat-resources:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.appcompat:appcompat-resources:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9d26fad69a8035c5a1e8031032bda63\transformed\constraintlayout-2.0.1\AndroidManifest.xml   
Merging manifest with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [androidx.constraintlayout:constraintlayout:2.0.1] AndroidManifest.xml:9:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3527fb198cdd94198153373b9754785d\transformed\appcompat-1.6.1\AndroidManifest.xml
Merging manifest with lower [androidx.appcompat:appcompat:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.appcompat:appcompat:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ef0651bbaaf20d6f1ce6842f3cf3ea4\transformed\maps-ktx-5.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.maps.android:maps-ktx:5.0.0] AndroidManifest.xml:18:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.maps.android:maps-ktx:5.0.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:17:1-44:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:20:5-44
Adopted [uses-permission: null]
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:24:5-67
Adopted [uses-feature: null]
Adopted [queries: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-maps:18.2.0] AndroidManifest.xml:36:5-42:19
Adopted [uses-library: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml 
Merging manifest with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:2:1-17:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth-ktx:22.1.1] AndroidManifest.xml:7:5-15:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml     
Merging manifest with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:17:1-75:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:21:5-23:64
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:25:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:26:5-79
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:28:5-73:19
Adopted [activity: null]
Adopted [activity: null]
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-auth:22.1.1] AndroidManifest.xml:66:9-72:19    
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:8:5-16:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-firestore-ktx:24.7.1] AndroidManifest.xml:9:9-15:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc72f96d8c07a6bc682086f6a12afa94\transformed\barcode-scanning-17.2.0\AndroidManifest.xml  
Merging manifest with lower [com.google.mlkit:barcode-scanning:17.2.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:barcode-scanning:17.2.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1ef8e61defd79da791ebb59714f2e6b\transformed\play-services-location-21.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-location:21.0.1] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:2:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:6:5-44
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:10:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:11:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:13:5-21:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-firestore:24.7.1] AndroidManifest.xml:14:9-20:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] AndroidManifest.xml:8:5-16:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\44332fe5700c324c2a841fed947afb20\transformed\barcode-scanning-common-17.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.mlkit:barcode-scanning-common:17.0.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:barcode-scanning-common:17.0.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml     
Merging manifest with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:2:1-18:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:8:5-16:19
service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService defined in both files...
Merging service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService with lower [com.google.mlkit:vision-common:17.3.0] AndroidManifest.xml:9:9-15:19 
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml
Merging manifest with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:2:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:6:5-44
application defined in both files...
Merging application with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:8:5-24:19
Adopted [provider: null]
service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService defined in both files...
Merging service#com.google.mlkit.common.internal.MlKitComponentDiscoveryService with lower [com.google.mlkit:common:18.9.0] AndroidManifest.xml:15:9-23:19       
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:2:1-20:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:10:5-18:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-storage-ktx:20.2.1] AndroidManifest.xml:11:9-17:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml       
Merging manifest with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:17:1-145:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:21:5-23:41
Adopted [uses-permission: null]
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:26:5-79
Adopted [uses-permission: null]
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [androidx.work:work-runtime:2.7.1] AndroidManifest.xml:30:5-143:19
Adopted [provider: null]
Adopted [service: null]
Adopted [service: null]
Adopted [service: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Adopted [receiver: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1c236d1be356058c5d38a6407020414\transformed\material-icons-core-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.material:material-icons-core-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-icons-core-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b00e01cd2630685c5896c00ed4d387a4\transformed\material-icons-extended-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.material:material-icons-extended-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-icons-extended-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0bca281d65c6305c18f08d784f02db44\transformed\material-ripple-release\AndroidManifest.xml  
Merging manifest with lower [androidx.compose.material:material-ripple-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.material:material-ripple-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7ffcccc0c7d0fe0808a1e1d43a5c4bc\transformed\animation-core-release\AndroidManifest.xml   
Merging manifest with lower [androidx.compose.animation:animation-core-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.animation:animation-core-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b45016de5999ab45e3c6f93f18b8f0b4\transformed\animation-release\AndroidManifest.xml        
Merging manifest with lower [androidx.compose.animation:animation-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.animation:animation-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ad701c3da8b6f314a074147b61622f51\transformed\foundation-layout-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.foundation:foundation-layout-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.foundation:foundation-layout-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75cf2c8d302443c5f5a11437f45fc7cc\transformed\foundation-release\AndroidManifest.xml       
Merging manifest with lower [androidx.compose.foundation:foundation-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.foundation:foundation-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\901560b52dc43160942dd52a8d5ddb66\transformed\ui-unit-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-unit-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-unit-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a0b4f0517b364306f98aab095621a04a\transformed\ui-geometry-release\AndroidManifest.xml      
Merging manifest with lower [androidx.compose.ui:ui-geometry-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-geometry-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a2c176c83e3255c54c294c740419995\transformed\ui-util-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-util-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-util-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\4ab1ce79a4e8be4025b59fbb6dbd8d67\transformed\ui-graphics-release\AndroidManifest.xml      
Merging manifest with lower [androidx.compose.ui:ui-graphics-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-graphics-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e00013d98828add3ca1a88b1ea5b2fea\transformed\ui-tooling-preview-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-tooling-preview-android:1.6.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-tooling-preview-android:1.6.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\216fcb22cce24b735816ff2d550d4b95\transformed\ui-text-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-text-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-text-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\314c779f047198335435d76c74a49af0\transformed\emoji2-views-helper-1.3.0\AndroidManifest.xml
Merging manifest with lower [androidx.emoji2:emoji2-views-helper:1.3.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.emoji2:emoji2-views-helper:1.3.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml
Merging manifest with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:17:1-35:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:23:5-33:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.emoji2:emoji2:1.3.0] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:17:1-35:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:23:5-33:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.lifecycle:lifecycle-process:2.6.2] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\131407770d0a55c5099b1c9bcca5d9b3\transformed\lifecycle-service-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-service:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-service:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be2d59222a581ea27079e1e5f50bfc22\transformed\lifecycle-livedata-core-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-livedata-core:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-livedata-core:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\03c32e1ac4a26acd85b5bcbb19ecee9b\transformed\lifecycle-viewmodel-ktx-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:2:1-17:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:7:5-15:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-analytics-ktx:21.3.0] AndroidManifest.xml:8:9-14:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d845df531a260f75739db15ce9396e59\transformed\firebase-analytics-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-analytics:21.3.0] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:17:1-46:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:23:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:24:5-79 
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:25:5-68
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement:21.3.0] AndroidManifest.xml:28:5-44:19
Adopted [receiver: null]
Adopted [service: null]
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\80c7788a63291a048d3114fba53e2731\transformed\legacy-support-v4-1.0.0\AndroidManifest.xml  
Merging manifest with lower [androidx.legacy:legacy-support-v4:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-v4:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\14bfdc109aaf5d07c0715e526294f3e9\transformed\play-services-measurement-sdk-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:17:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-sdk:21.3.0] AndroidManifest.xml:22:5-23:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:17:1-32:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:23:5-67        
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:24:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:25:5-68       
uses-permission#com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE defined in both files...
Merging uses-permission#com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:26:5-110
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-impl:21.3.0] AndroidManifest.xml:29:5-30:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a2ceb738eefaa6214c9b16aa63616f0\transformed\play-services-stats-17.0.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-stats:17.0.2] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a40505be55533e631038632642044d4d\transformed\legacy-support-core-ui-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.legacy:legacy-support-core-ui:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-core-ui:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f66a86c88c7490b271b2934db5002420\transformed\dynamicanimation-1.0.0\AndroidManifest.xml   
Merging manifest with lower [androidx.dynamicanimation:dynamicanimation:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.dynamicanimation:dynamicanimation:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6316851f664031f40b83071bc15adb03\transformed\legacy-support-core-utils-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.legacy:legacy-support-core-utils:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.legacy:legacy-support-core-utils:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f72441850923c45aa75245fd7ca98102\transformed\loader-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.loader:loader:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.loader:loader:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9dc43b1ef6ea4e62e3378e37a4247f07\transformed\lifecycle-livedata-2.6.2\AndroidManifest.xml 
Merging manifest with lower [androidx.lifecycle:lifecycle-livedata:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-livedata:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c354034cefbd9814d2b305e4cda410ee\transformed\savedstate-ktx-1.2.1\AndroidManifest.xml     
Merging manifest with lower [androidx.savedstate:savedstate-ktx:1.2.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.savedstate:savedstate-ktx:1.2.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c15d40a248a43ccdee67d6766fcb6f26\transformed\savedstate-1.2.1\AndroidManifest.xml
Merging manifest with lower [androidx.savedstate:savedstate:1.2.1] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.savedstate:savedstate:1.2.1] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d5f63eac777bcfe6b30d61b995a1c45\transformed\lifecycle-viewmodel-savedstate-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\35c18b15d8de5830b76dc4f86b05d870\transformed\litr-1.4.16\AndroidManifest.xml
Merging manifest with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:7:1-16:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:10:5-12:41
application defined in both files...
Merging application with lower [com.linkedin.android.litr:litr:1.4.16] AndroidManifest.xml:14:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b0b2bcd485495502849906c506e947e6\transformed\media3-exoplayer-dash-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-exoplayer-dash:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer-dash:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2218549c45fe27b24d3d903e2ebcb2b9\transformed\media3-exoplayer-hls-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-exoplayer-hls:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer-hls:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\be618a094c6eadd34494571884db27e0\transformed\media3-exoplayer-1.1.1\AndroidManifest.xml   
Merging manifest with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:17:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:20:5-22:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [androidx.media3:media3-exoplayer:1.1.1] AndroidManifest.xml:24:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\38e0bd13d79fca62275fdd12c283e43a\transformed\media3-extractor-1.1.1\AndroidManifest.xml   
Merging manifest with lower [androidx.media3:media3-extractor:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-extractor:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c4e7b649c5c69897c9466a532c6dc9a\transformed\media3-container-1.1.1\AndroidManifest.xml   
Merging manifest with lower [androidx.media3:media3-container:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-container:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bfcbb13e13c03764b50018d4fbf6b43\transformed\media3-datasource-1.1.1\AndroidManifest.xml  
Merging manifest with lower [androidx.media3:media3-datasource:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-datasource:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d0c36692780219799cce233fa774885\transformed\media3-decoder-1.1.1\AndroidManifest.xml     
Merging manifest with lower [androidx.media3:media3-decoder:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-decoder:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d03c1b0898c92f40bf904e2184a2540\transformed\media3-database-1.1.1\AndroidManifest.xml    
Merging manifest with lower [androidx.media3:media3-database:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-database:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\353aa858e1189c1e15533a12e4bde9b1\transformed\media3-common-1.1.1\AndroidManifest.xml      
Merging manifest with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:17:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:20:5-22:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [androidx.media3:media3-common:1.1.1] AndroidManifest.xml:24:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\74feaf88e5d6ab5c88c4da35184e5d8f\transformed\media3-ui-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.media3:media3-ui:1.1.1] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media3:media3-ui:1.1.1] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a7b689b97281718ee32092e564efecb5\transformed\viewpager2-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.viewpager2:viewpager2:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.viewpager2:viewpager2:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f80a44333a3d0d94d28b94082a80904e\transformed\recyclerview-1.3.0\AndroidManifest.xml       
Merging manifest with lower [androidx.recyclerview:recyclerview:1.3.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.recyclerview:recyclerview:1.3.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7666ab3b28cb3924d9f5e098debd7d8f\transformed\customview-poolingcontainer-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.customview:customview-poolingcontainer:1.0.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.customview:customview-poolingcontainer:1.0.0] AndroidManifest.xml:20:5-21:38
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cd3359b6cbdaa1bef10e17bd7b91672b\transformed\core-ktx-1.12.0\AndroidManifest.xml
Merging manifest with lower [androidx.core:core-ktx:1.12.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.core:core-ktx:1.12.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml  
Merging manifest with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:15:1-38:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:19:5-21:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:25:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:26:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:28:5-36:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-storage:20.2.1] AndroidManifest.xml:29:9-35:19 
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dec6106073bd1ccc907541665e674944\transformed\play-services-auth-api-phone-17.4.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-auth-api-phone:17.4.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-auth-api-phone:17.4.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e020070785bab2e428d71a57f14adf\transformed\firebase-appcheck-interop-17.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:15:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:18:5-20:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-appcheck-interop:17.0.0] AndroidManifest.xml:23:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3296b09c7f64b522e97abed6126219a2\transformed\firebase-database-collection-18.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-database-collection:18.0.1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-database-collection:18.0.1] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:16:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:18:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-base:18.1.0] AndroidManifest.xml:19:5-23:19
Adopted [activity: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3569a112fda6949dbc9bf6f86c1158a1\transformed\browser-1.4.0\AndroidManifest.xml
Merging manifest with lower [androidx.browser:browser:1.4.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.browser:browser:1.4.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62524a5742b998acb8b5c5dd6647626\transformed\autofill-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.autofill:autofill:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.autofill:autofill:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\acca26e9976f0a24c694c71b93c16e0b\transformed\coordinatorlayout-1.1.0\AndroidManifest.xml  
Merging manifest with lower [androidx.coordinatorlayout:coordinatorlayout:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.coordinatorlayout:coordinatorlayout:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\250967770574026fa0b82276e0192fb6\transformed\transition-1.2.0\AndroidManifest.xml
Merging manifest with lower [androidx.transition:transition:1.2.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.transition:transition:1.2.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\687b04085f858c1dee970df17d424330\transformed\vectordrawable-animated-1.1.0\AndroidManifest.xml
Merging manifest with lower [androidx.vectordrawable:vectordrawable-animated:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.vectordrawable:vectordrawable-animated:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d6cb56e8858a9d0945b32a60153227\transformed\vectordrawable-1.1.0\AndroidManifest.xml     
Merging manifest with lower [androidx.vectordrawable:vectordrawable:1.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.vectordrawable:vectordrawable:1.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4b9d4797d7708ad60359aebb321d1f\transformed\media-1.6.0\AndroidManifest.xml
Merging manifest with lower [androidx.media:media:1.6.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.media:media:1.6.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\43f5976b58f6a822c0f2d52c2a4f44aa\transformed\viewpager-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.viewpager:viewpager:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.viewpager:viewpager:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f1f690e76c48498566958dbcc549ce17\transformed\drawerlayout-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.drawerlayout:drawerlayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.drawerlayout:drawerlayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6064e677c16d5c58259d0cf627b5d8fd\transformed\slidingpanelayout-1.0.0\AndroidManifest.xml  
Merging manifest with lower [androidx.slidingpanelayout:slidingpanelayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.slidingpanelayout:slidingpanelayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\da2ed364818c61fb6178b7e31ec2a21d\transformed\customview-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.customview:customview:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.customview:customview:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b3f05fe21219c1462de54f164f791ff\transformed\swiperefreshlayout-1.0.0\AndroidManifest.xml 
Merging manifest with lower [androidx.swiperefreshlayout:swiperefreshlayout:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.swiperefreshlayout:swiperefreshlayout:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\266a76d3d546a9e118935d822877f314\transformed\asynclayoutinflater-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.asynclayoutinflater:asynclayoutinflater:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.asynclayoutinflater:asynclayoutinflater:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml
Merging manifest with lower [androidx.core:core:1.12.0] AndroidManifest.xml:17:1-30:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.core:core:1.12.0] AndroidManifest.xml:20:5-44
Adopted [permission: null]
Adopted [uses-permission: null]
application defined in both files...
Merging application with lower [androidx.core:core:1.12.0] AndroidManifest.xml:28:5-89
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd1d24dd52e67068d44215574926172c\transformed\lifecycle-runtime-2.6.2\AndroidManifest.xml  
Merging manifest with lower [androidx.lifecycle:lifecycle-runtime:2.6.2] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-runtime:2.6.2] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\815557c2ae8a08884db25c998588790c\transformed\lifecycle-viewmodel-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aebb7c5ae338a679f119370e152f8566\transformed\lifecycle-viewmodel-compose-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e8fa69f2fc9288c4e50f2e3d7809464\transformed\accompanist-drawablepainter-0.32.0\AndroidManifest.xml
Merging manifest with lower [com.google.accompanist:accompanist-drawablepainter:0.32.0] AndroidManifest.xml:17:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.accompanist:accompanist-drawablepainter:0.32.0] AndroidManifest.xml:21:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d9088c59b0fab5a007d7b04dd92a9f\transformed\ui-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.ui:ui-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.ui:ui-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\75f0140c7f717d66951ed03f3680fc27\transformed\runtime-saveable-release\AndroidManifest.xml 
Merging manifest with lower [androidx.compose.runtime:runtime-saveable-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.runtime:runtime-saveable-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6dc17ed3b6be82e4f8af8c05b8da1b0c\transformed\runtime-release\AndroidManifest.xml
Merging manifest with lower [androidx.compose.runtime:runtime-android:1.6.1] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.compose.runtime:runtime-android:1.6.1] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:2:1-20:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:5:5-7:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:10:5-18:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-common-ktx:20.3.1] AndroidManifest.xml:11:9-17:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b518a01e24c8367d7c40d73f84fcb476\transformed\recaptcha-18.1.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:2:1-10:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:5:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:7:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.recaptcha:recaptcha:18.1.2] AndroidManifest.xml:8:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:17:1-37:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:22:5-67
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:23:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:24:5-68        
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:25:5-79
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:27:5-35:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.android.gms:play-services-measurement-api:21.3.0] AndroidManifest.xml:28:9-34:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b2ab533ae5878b9cafe683ac52230b2\transformed\firebase-auth-interop-20.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:2:1-10:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-auth-interop:20.0.0] AndroidManifest.xml:7:5-8:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:2:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:6:5-8:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:10:5-79       
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:11:5-67
application defined in both files...
Merging application with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:14:5-22:19
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-installations:17.0.1] AndroidManifest.xml:15:9-21:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml   
Merging manifest with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:15:1-39:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:19:5-21:41
application defined in both files...
Merging application with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:24:5-37:19
Adopted [provider: null]
service#com.google.firebase.components.ComponentDiscoveryService defined in both files...
Merging service#com.google.firebase.components.ComponentDiscoveryService with lower [com.google.firebase:firebase-common:20.3.2] AndroidManifest.xml:32:9-36:35  
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c8cbc24cb735075edb099d47fc6cfce4\transformed\integrity-1.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:4:5-44
application defined in both files...
Merging application with lower [com.google.android.play:integrity:1.1.0] AndroidManifest.xml:5:5-6:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\877481486b492ed837440e24d40d376c\transformed\vision-interfaces-16.2.0\AndroidManifest.xml 
Merging manifest with lower [com.google.mlkit:vision-interfaces:16.2.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.mlkit:vision-interfaces:16.2.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecde9336b62f39bd954535409773422b\transformed\firebase-installations-interop-17.0.1\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-installations-interop:17.0.1] AndroidManifest.xml:15:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-installations-interop:17.0.1] AndroidManifest.xml:19:5-21:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0e1681c588006a553017c69b9f3ee841\transformed\play-services-tasks-18.0.2\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:2:1-6:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:4:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-tasks:18.0.2] AndroidManifest.xml:5:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\494728eeabc92461d48f2864fe26da46\transformed\play-services-ads-identifier-18.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:17:1-27:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:20:5-44
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:23:5-79
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-ads-identifier:18.0.0] AndroidManifest.xml:25:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6649a9bc3f74cab6db315ced24506837\transformed\play-services-measurement-sdk-api-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:17:1-28:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:20:5-44
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:23:5-67     
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:24:5-79
uses-permission#android.permission.WAKE_LOCK defined in both files...
Merging uses-permission#android.permission.WAKE_LOCK with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:25:5-68    
uses-permission#com.google.android.gms.permission.AD_ID defined in both files...
Merging uses-permission#com.google.android.gms.permission.AD_ID with lower [com.google.android.gms:play-services-measurement-sdk-api:21.3.0] AndroidManifest.xml:26:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\99363a2471989efa1468400d1e774fc3\transformed\play-services-measurement-base-21.3.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-measurement-base:21.3.0] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5e4955c6525be4076f8f44f8317be0e6\transformed\firebase-measurement-connector-19.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:17:1-25:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:20:5-44
application defined in both files...
Merging application with lower [com.google.firebase:firebase-measurement-connector:19.0.0] AndroidManifest.xml:22:5-23:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:16:1-26:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:18:5-43
application defined in both files...
Merging application with lower [com.google.android.gms:play-services-basement:18.1.0] AndroidManifest.xml:20:5-24:19
Adopted [meta-data: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\a50c744dcf4b5e5e8edce004e0604f76\transformed\fragment-1.3.6\AndroidManifest.xml
Merging manifest with lower [androidx.fragment:fragment:1.3.6] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.fragment:fragment:1.3.6] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3458cd08c021da105f701fc15f09ea17\transformed\activity-1.7.2\AndroidManifest.xml
Merging manifest with lower [androidx.activity:activity:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\431591700a7d4c4a2d020513f2985bf1\transformed\activity-compose-1.7.2\AndroidManifest.xml   
Merging manifest with lower [androidx.activity:activity-compose:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity-compose:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\5638fabe0d5d244a1e5bdb4f9ebbd76e\transformed\activity-ktx-1.7.2\AndroidManifest.xml       
Merging manifest with lower [androidx.activity:activity-ktx:1.7.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.activity:activity-ktx:1.7.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\97fba2ce58e83f82cd34056ad1b2f944\transformed\lifecycle-runtime-ktx-2.6.2\AndroidManifest.xml
Merging manifest with lower [androidx.lifecycle:lifecycle-runtime-ktx:2.6.2] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.lifecycle:lifecycle-runtime-ktx:2.6.2] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\db4e7d3ddc290ffaf179433361c13b5c\transformed\napier-release\AndroidManifest.xml
Merging manifest with lower [io.github.aakira:napier-android:1.4.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.github.aakira:napier-android:1.4.1] AndroidManifest.xml:7:5-9:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\093f96a4c1a2638e480d7fbf99d765df\transformed\annotation-experimental-1.4.0\AndroidManifest.xml
Merging manifest with lower [androidx.annotation:annotation-experimental:1.4.0] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.annotation:annotation-experimental:1.4.0] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\07daafe716d893f743308b2174374580\transformed\versionedparcelable-1.1.1\AndroidManifest.xml
Merging manifest with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:17:1-27:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:20:5-22:41
application defined in both files...
Merging application with lower [androidx.versionedparcelable:versionedparcelable:1.1.1] AndroidManifest.xml:24:5-25:19
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9337281adcc4664583144eb68a4d78\transformed\firebase-components-17.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-components:17.1.0] AndroidManifest.xml:15:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-components:17.1.0] AndroidManifest.xml:18:5-20:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23f8a3b57d3b678bff82e4e4a2b0d8da\transformed\interpolator-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.interpolator:interpolator:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.interpolator:interpolator:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml       
Merging manifest with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:17:1-31:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:20:5-22:41
application defined in both files...
Merging application with lower [androidx.room:room-runtime:2.2.5] AndroidManifest.xml:24:5-29:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\657dcbddb3a2b9483703680d23a453a9\transformed\core-runtime-2.2.0\AndroidManifest.xml       
Merging manifest with lower [androidx.arch.core:core-runtime:2.2.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.arch.core:core-runtime:2.2.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\9689239b917968370ee83770f980d8d3\transformed\localbroadcastmanager-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.localbroadcastmanager:localbroadcastmanager:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.localbroadcastmanager:localbroadcastmanager:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml
Merging manifest with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:15:1-38:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:19:5-21:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:25:5-79
uses-permission#android.permission.INTERNET defined in both files...
Merging uses-permission#android.permission.INTERNET with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:26:5-67        
application defined in both files...
Merging application with lower [com.google.android.datatransport:transport-backend-cct:2.3.3] AndroidManifest.xml:28:5-36:19
Adopted [service: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml
Merging manifest with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:15:1-41:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:18:5-20:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:22:5-79
application defined in both files...
Merging application with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:25:5-39:19
Adopted [service: null]
Adopted [receiver: null]
service#com.google.android.datatransport.runtime.backends.TransportBackendDiscovery defined in both files...
Merging service#com.google.android.datatransport.runtime.backends.TransportBackendDiscovery with lower [com.google.android.datatransport:transport-runtime:2.2.6] AndroidManifest.xml:36:9-38:40
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\154e3c535edbdd29d1f6d63c0fbc89b3\transformed\transport-api-2.2.1\AndroidManifest.xml      
Merging manifest with lower [com.google.android.datatransport:transport-api:2.2.1] AndroidManifest.xml:15:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.datatransport:transport-api:2.2.1] AndroidManifest.xml:18:5-20:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\085c58d7ccd3350d93d2385455be3130\transformed\firebase-encoders-json-17.1.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:firebase-encoders-json:17.1.0] AndroidManifest.xml:15:1-23:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:firebase-encoders-json:17.1.0] AndroidManifest.xml:19:5-21:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d4e55c19a61023e2414aba427f7766fd\transformed\viewbinding-7.4.2\AndroidManifest.xml        
Merging manifest with lower [androidx.databinding:viewbinding:7.4.2] AndroidManifest.xml:2:1-7:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.databinding:viewbinding:7.4.2] AndroidManifest.xml:5:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml   
Merging manifest with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:17:1-55:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:21:5-44
application defined in both files...
Merging application with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:23:5-53:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.profileinstaller:profileinstaller:1.3.1] AndroidManifest.xml:24:9-32:20
Adopted [meta-data: null]
Adopted [receiver: null]
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3bf891793924d464a72841108eb13580\transformed\startup-runtime-1.1.1\AndroidManifest.xml    
Merging manifest with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:17:1-33:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:21:5-23:41
application defined in both files...
Merging application with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:25:5-31:19
provider#androidx.startup.InitializationProvider defined in both files...
Merging provider#androidx.startup.InitializationProvider with lower [androidx.startup:startup-runtime:1.1.1] AndroidManifest.xml:26:9-30:34
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\42cfd231f4c63e7c04d46c8dd3aaa08c\transformed\tracing-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.tracing:tracing:1.0.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.tracing:tracing:1.0.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\26f543fe221eecfd6592bfeb6d24ea8a\transformed\documentfile-1.0.0\AndroidManifest.xml       
Merging manifest with lower [androidx.documentfile:documentfile:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.documentfile:documentfile:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\69f3a9b0a03b997a31ffb8beeeb09361\transformed\print-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.print:print:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.print:print:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c5f9a7307d88baffe4c41a6e6374054d\transformed\exifinterface-1.3.6\AndroidManifest.xml      
Merging manifest with lower [androidx.exifinterface:exifinterface:1.3.6] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.exifinterface:exifinterface:1.3.6] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3336ea62311c9c6f6a89ac10bc23051\transformed\cardview-1.0.0\AndroidManifest.xml
Merging manifest with lower [androidx.cardview:cardview:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.cardview:cardview:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0d615bf23df10d96d1b53dd453627014\transformed\sqlite-framework-2.1.0\AndroidManifest.xml   
Merging manifest with lower [androidx.sqlite:sqlite-framework:2.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.sqlite:sqlite-framework:2.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7329e813a90b3c51a16cb2cf2e9da6e6\transformed\sqlite-2.1.0\AndroidManifest.xml
Merging manifest with lower [androidx.sqlite:sqlite:2.1.0] AndroidManifest.xml:17:1-24:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.sqlite:sqlite:2.1.0] AndroidManifest.xml:20:5-22:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\cc457ff0ddba235c1477d57ed6838f6d\transformed\cursoradapter-1.0.0\AndroidManifest.xml      
Merging manifest with lower [androidx.cursoradapter:cursoradapter:1.0.0] AndroidManifest.xml:17:1-22:12
uses-sdk defined in both files...
Merging uses-sdk with lower [androidx.cursoradapter:cursoradapter:1.0.0] AndroidManifest.xml:20:5-44
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f8b1e25dbb65cc9b4986eb5d4812818\transformed\grpc-android-1.52.1\AndroidManifest.xml      
Merging manifest with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:5:5-7:41
uses-permission#android.permission.ACCESS_NETWORK_STATE defined in both files...
Merging uses-permission#android.permission.ACCESS_NETWORK_STATE with lower [io.grpc:grpc-android:1.52.1] AndroidManifest.xml:9:5-79
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\c14b1f0304087127f8d0d5d65ec44ac4\transformed\protolite-well-known-types-18.0.0\AndroidManifest.xml
Merging manifest with lower [com.google.firebase:protolite-well-known-types:18.0.0] AndroidManifest.xml:2:1-11:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.firebase:protolite-well-known-types:18.0.0] AndroidManifest.xml:7:5-9:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1debf71d1d368496a17f77aa5e301f0\transformed\image-1.0.0-beta1\AndroidManifest.xml        
Merging manifest with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:5:5-44
application defined in both files...
Merging application with lower [com.google.android.odml:image:1.0.0-beta1] AndroidManifest.xml:7:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\23264cf826b21068683bc0ce78f43f3e\transformed\fresco-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:fresco:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:fresco:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f5ef6ded1df61098fe18bc6c64e9b21\transformed\drawee-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:drawee:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:drawee:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\50d0d0f04287cb367c1ccf2956360961\transformed\nativeimagefilters-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:nativeimagefilters:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:nativeimagefilters:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b7fa33f44f3b80d898f9552ac844245\transformed\memory-type-native-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:memory-type-native:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-native:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a4fe5708b949a2d6a5e628c9527e07b\transformed\memory-type-java-2.6.0\AndroidManifest.xml   
Merging manifest with lower [com.facebook.fresco:memory-type-java:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-java:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d69946b300ec87149af263e1a8b97dd0\transformed\imagepipeline-native-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:imagepipeline-native:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline-native:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\3be242a23f4988743ed36ef1693ed26f\transformed\memory-type-ashmem-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:memory-type-ashmem:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:memory-type-ashmem:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\aaa20b67155259c75fb91f26fa0a8b93\transformed\imagepipeline-2.6.0\AndroidManifest.xml      
Merging manifest with lower [com.facebook.fresco:imagepipeline:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\dab1ba1b4aa7f5db10c1872e11a73484\transformed\nativeimagetranscoder-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:nativeimagetranscoder:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:nativeimagetranscoder:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b36b9dee027cfeda0b9fb3d854894fd5\transformed\imagepipeline-base-2.6.0\AndroidManifest.xml 
Merging manifest with lower [com.facebook.fresco:imagepipeline-base:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:imagepipeline-base:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\d73423f7dad861f055ffe7756f86f5b1\transformed\soloader-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:soloader:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:soloader:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\282ba00101d4caddb474bbe6d8097617\transformed\soloader-0.10.1\AndroidManifest.xml
Merging manifest with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:2:1-13:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:7:5-9:41
application defined in both files...
Merging application with lower [com.facebook.soloader:soloader:0.10.1] AndroidManifest.xml:11:5-20
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9c7bf14cb4e9247c6082c2483ba8be\transformed\middleware-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:middleware:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:middleware:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\30fcdd209d1f98a1d3e17edc923138fb\transformed\ui-common-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:ui-common:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:ui-common:2.6.0] AndroidManifest.xml:5:5-7:41
Merging library manifest C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3808632869cf9db222c403621fd36c3\transformed\fbcore-2.6.0\AndroidManifest.xml
Merging manifest with lower [com.facebook.fresco:fbcore:2.6.0] AndroidManifest.xml:2:1-9:12
uses-sdk defined in both files...
Merging uses-sdk with lower [com.facebook.fresco:fbcore:2.6.0] AndroidManifest.xml:5:5-7:41
Merging result: SUCCESS
1<?xml version="1.0" encoding="utf-8"?>
2<manifest xmlns:android="http://schemas.android.com/apk/res/android"
3    package="com.pluck"
4    android:versionCode="1"
5    android:versionName="1.0" >
6
7    <uses-sdk
8        android:minSdkVersion="26"
9        android:targetSdkVersion="34" />
10
11    <uses-permission android:name="android.permission.CAMERA" />
11-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:4:5-65
11-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:4:22-62
12    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
12-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:5:5-76
12-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:5:22-73
13    <uses-permission
13-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:6:5-7:38
14        android:name="android.permission.READ_EXTERNAL_STORAGE"
14-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:6:22-77
15        android:maxSdkVersion="32" />
15-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:7:9-35
16    <uses-permission
16-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:8:5-9:38
17        android:name="android.permission.WRITE_EXTERNAL_STORAGE"
17-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:8:22-78
18        android:maxSdkVersion="32" />
18-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:9:9-35
19    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
19-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:10:5-79
19-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:10:22-76
20    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
20-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:11:5-81
20-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:11:22-78
21
22    <uses-feature
22-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:5-85
23        android:name="android.hardware.camera"
23-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:19-57
24        android:required="false" />
24-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:13:58-82
25    <uses-feature
25-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:5-95
26        android:name="android.hardware.camera.autofocus"
26-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:19-67
27        android:required="false" />
27-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:14:68-92
28
29    <uses-permission android:name="android.permission.INTERNET" /> <!-- Include required permissions for Google Maps API to run. -->
29-->[com.cloudinary:cloudinary-android-core:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml:9:5-67
29-->[com.cloudinary:cloudinary-android-core:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0\AndroidManifest.xml:9:22-64
30    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
30-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:23:5-79
30-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:23:22-76
31
32    <uses-feature
32-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:26:5-28:35
33        android:glEsVersion="0x00020000"
33-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:27:9-41
34        android:required="true" />
34-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:28:9-32
35
36    <queries>
36-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:30:5-34:15
37
38        <!-- Needs to be explicitly declared on Android R+ -->
39        <package android:name="com.google.android.apps.maps" />
39-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:33:9-64
39-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:33:18-61
40    </queries>
41
42    <uses-permission android:name="android.permission.WAKE_LOCK" />
42-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:25:5-68
42-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:25:22-65
43    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
43-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:27:5-81
43-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:27:22-78
44    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
44-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:28:5-77
44-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:28:22-74
45    <uses-permission android:name="com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE" />
45-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:26:5-110
45-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:26:22-107
46    <uses-permission android:name="com.google.android.gms.permission.AD_ID" />
46-->[com.google.android.gms:play-services-measurement-impl:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml:27:5-79
46-->[com.google.android.gms:play-services-measurement-impl:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\011c8e08329cb80123d3d8970b75da68\transformed\play-services-measurement-impl-21.3.0\AndroidManifest.xml:27:22-76
47
48    <permission
48-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:22:5-24:47
49        android:name="com.pluck.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION"
49-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:23:9-81
50        android:protectionLevel="signature" />
50-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:24:9-44
51
52    <uses-permission android:name="com.pluck.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION" />
52-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:26:5-97
52-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:26:22-94
53
54    <application
54-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:16:5-49:19
55        android:name="com.pluck.PLuckApp"
55-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:17:9-33
56        android:allowBackup="true"
56-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:18:9-35
57        android:appComponentFactory="androidx.core.app.CoreComponentFactory"
57-->[androidx.core:core:1.12.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c594a420e9e1ece7903be8543d78132\transformed\core-1.12.0\AndroidManifest.xml:28:18-86
58        android:extractNativeLibs="false"
59        android:icon="@drawable/ic_launcher_pluck"
59-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:19:9-51
60        android:label="@string/app_name"
60-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:20:9-41
61        android:supportsRtl="true"
61-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:21:9-35
62        android:theme="@style/Theme.PLuck" >
62-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:22:9-43
63
64        <!-- Google Maps API Key -->
65        <meta-data
66            android:name="com.google.android.geo.API_KEY"
66-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:26:13-58
67            android:value="AIzaSyBxbRzj564Eug_xZkWlYNx6pwYkaDnXOwk" />
67-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:27:13-44
68
69        <!-- FileProvider for sharing files (CSV exports, image uploads) -->
70        <provider
71            android:name="androidx.core.content.FileProvider"
71-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:31:13-62
72            android:authorities="com.pluck.fileprovider"
72-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:32:13-64
73            android:exported="false"
73-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:33:13-37
74            android:grantUriPermissions="true" >
74-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:34:13-47
75            <meta-data
75-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:35:13-37:54
76                android:name="android.support.FILE_PROVIDER_PATHS"
76-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:36:17-67
77                android:resource="@xml/file_paths" />
77-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:37:17-51
78        </provider>
79
80        <activity
80-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:40:9-48:20
81            android:name="com.pluck.MainActivity"
81-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:41:13-41
82            android:exported="true"
82-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:42:13-36
83            android:windowSoftInputMode="adjustResize" >
83-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:43:13-55
84            <intent-filter>
84-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:44:13-47:29
85                <action android:name="android.intent.action.MAIN" />
85-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:45:17-69
85-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:45:25-66
86
87                <category android:name="android.intent.category.LAUNCHER" />
87-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:46:17-77
87-->Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\AndroidManifest.xml:46:27-74
88            </intent-filter>
89        </activity>
90
91        <service
91-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:24:9-33:19
92            android:name="androidx.camera.core.impl.MetadataHolderService"
92-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:25:13-75
93            android:enabled="false"
93-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:26:13-36
94            android:exported="false" >
94-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:27:13-37
95            <meta-data
95-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:30:13-32:89
96                android:name="androidx.camera.core.impl.MetadataHolderService.DEFAULT_CONFIG_PROVIDER"
96-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:31:17-103
97                android:value="androidx.camera.camera2.Camera2Config$DefaultProvider" />
97-->[androidx.camera:camera-camera2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2847dabe33e919fb8ac91c7c29162d47\transformed\camera-camera2-1.3.0\AndroidManifest.xml:32:17-86
98        </service>
99
100        <activity
100-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:10:9-13:56
101            android:name="com.cloudinary.android.uploadwidget.ui.UploadWidgetActivity"
101-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:11:13-87
102            android:configChanges="orientation"
102-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:12:13-48
103            android:theme="@style/UploadWidgetTheme" /> <!-- Needs to be explicitly declared on P+ -->
103-->[com.cloudinary:cloudinary-android-ui:2.5.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0\AndroidManifest.xml:13:13-53
104        <uses-library
104-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:39:9-41:40
105            android:name="org.apache.http.legacy"
105-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:40:13-50
106            android:required="false" />
106-->[com.google.android.gms:play-services-maps:18.2.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0\AndroidManifest.xml:41:13-37
107
108        <service
108-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:8:9-14:19
109            android:name="com.google.firebase.components.ComponentDiscoveryService"
109-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:9:13-84
110            android:directBootAware="true"
110-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:34:13-43
111            android:exported="false" >
111-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:10:13-37
112            <meta-data
112-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:11:13-13:85
113                android:name="com.google.firebase.components:com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar"
113-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:12:17-116
114                android:value="com.google.firebase.components.ComponentRegistrar" />
114-->[com.google.firebase:firebase-auth-ktx:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\34464744029f2a465c2b772c0682ae65\transformed\firebase-auth-ktx-22.1.1\AndroidManifest.xml:13:17-82
115            <meta-data
115-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:69:13-71:85
116                android:name="com.google.firebase.components:com.google.firebase.auth.FirebaseAuthRegistrar"
116-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:70:17-109
117                android:value="com.google.firebase.components.ComponentRegistrar" />
117-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:71:17-82
118            <meta-data
118-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:12:13-14:85
119                android:name="com.google.firebase.components:com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar"
119-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:13:17-126
120                android:value="com.google.firebase.components.ComponentRegistrar" />
120-->[com.google.firebase:firebase-firestore-ktx:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\4df7c53624882a51d4c4f049330a6370\transformed\firebase-firestore-ktx-24.7.1\AndroidManifest.xml:14:17-82
121            <meta-data
121-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:17:13-19:85
122                android:name="com.google.firebase.components:com.google.firebase.firestore.FirestoreRegistrar"
122-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:18:17-111
123                android:value="com.google.firebase.components.ComponentRegistrar" />
123-->[com.google.firebase:firebase-firestore:24.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c242c7e762a38b3f64f0bb646f9365d\transformed\firebase-firestore-24.7.1\AndroidManifest.xml:19:17-82
124            <meta-data
124-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:14:13-16:85
125                android:name="com.google.firebase.components:com.google.firebase.storage.ktx.FirebaseStorageKtxRegistrar"
125-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:15:17-122
126                android:value="com.google.firebase.components.ComponentRegistrar" />
126-->[com.google.firebase:firebase-storage-ktx:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b1c7c87eb9a743c0eb5455883fc398b\transformed\firebase-storage-ktx-20.2.1\AndroidManifest.xml:16:17-82
127            <meta-data
127-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:11:13-13:85
128                android:name="com.google.firebase.components:com.google.firebase.analytics.ktx.FirebaseAnalyticsKtxRegistrar"
128-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:12:17-126
129                android:value="com.google.firebase.components.ComponentRegistrar" />
129-->[com.google.firebase:firebase-analytics-ktx:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e26b15fa337cbf10791fa1d32ce0a5ec\transformed\firebase-analytics-ktx-21.3.0\AndroidManifest.xml:13:17-82
130            <meta-data
130-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:32:13-34:85
131                android:name="com.google.firebase.components:com.google.firebase.storage.StorageRegistrar"
131-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:33:17-107
132                android:value="com.google.firebase.components.ComponentRegistrar" />
132-->[com.google.firebase:firebase-storage:20.2.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\121d76eef8058860cf32579fa86ef4ca\transformed\firebase-storage-20.2.1\AndroidManifest.xml:34:17-82
133            <meta-data
133-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:14:13-16:85
134                android:name="com.google.firebase.components:com.google.firebase.ktx.FirebaseCommonKtxRegistrar"
134-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:15:17-113
135                android:value="com.google.firebase.components.ComponentRegistrar" />
135-->[com.google.firebase:firebase-common-ktx:20.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7e2e99c0030d43eed93e385b694a9365\transformed\firebase-common-ktx-20.3.1\AndroidManifest.xml:16:17-82
136            <meta-data
136-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:31:13-33:85
137                android:name="com.google.firebase.components:com.google.firebase.analytics.connector.internal.AnalyticsConnectorRegistrar"
137-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:32:17-139
138                android:value="com.google.firebase.components.ComponentRegistrar" />
138-->[com.google.android.gms:play-services-measurement-api:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\60675b32f6b2b768de5f42bb3548fe1f\transformed\play-services-measurement-api-21.3.0\AndroidManifest.xml:33:17-82
139            <meta-data
139-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:18:13-20:85
140                android:name="com.google.firebase.components:com.google.firebase.installations.FirebaseInstallationsRegistrar"
140-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:19:17-127
141                android:value="com.google.firebase.components.ComponentRegistrar" />
141-->[com.google.firebase:firebase-installations:17.0.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ece62455f33943932f1d3a09961d499\transformed\firebase-installations-17.0.1\AndroidManifest.xml:20:17-82
142        </service>
143
144        <activity
144-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:29:9-46:20
145            android:name="com.google.firebase.auth.internal.GenericIdpActivity"
145-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:30:13-80
146            android:excludeFromRecents="true"
146-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:31:13-46
147            android:exported="true"
147-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:32:13-36
148            android:launchMode="singleTask"
148-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:33:13-44
149            android:theme="@android:style/Theme.Translucent.NoTitleBar" >
149-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:34:13-72
150            <intent-filter>
150-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:35:13-45:29
151                <action android:name="android.intent.action.VIEW" />
151-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:17-69
151-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:25-66
152
153                <category android:name="android.intent.category.DEFAULT" />
153-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:17-76
153-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:27-73
154                <category android:name="android.intent.category.BROWSABLE" />
154-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:17-78
154-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:27-75
155
156                <data
156-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:41:17-44:51
157                    android:host="firebase.auth"
157-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:42:21-49
158                    android:path="/"
158-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:43:21-37
159                    android:scheme="genericidp" />
159-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:44:21-48
160            </intent-filter>
161        </activity>
162        <activity
162-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:47:9-64:20
163            android:name="com.google.firebase.auth.internal.RecaptchaActivity"
163-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:48:13-79
164            android:excludeFromRecents="true"
164-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:49:13-46
165            android:exported="true"
165-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:50:13-36
166            android:launchMode="singleTask"
166-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:51:13-44
167            android:theme="@android:style/Theme.Translucent.NoTitleBar" >
167-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:52:13-72
168            <intent-filter>
168-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:53:13-63:29
169                <action android:name="android.intent.action.VIEW" />
169-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:17-69
169-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:36:25-66
170
171                <category android:name="android.intent.category.DEFAULT" />
171-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:17-76
171-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:38:27-73
172                <category android:name="android.intent.category.BROWSABLE" />
172-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:17-78
172-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:39:27-75
173
174                <data
174-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:41:17-44:51
175                    android:host="firebase.auth"
175-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:42:21-49
176                    android:path="/"
176-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:43:21-37
177                    android:scheme="recaptcha" />
177-->[com.google.firebase:firebase-auth:22.1.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\098548092df05cbf5fa57838f746b531\transformed\firebase-auth-22.1.1\AndroidManifest.xml:44:21-48
178            </intent-filter>
179        </activity>
180
181        <service
181-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:9:9-15:19
182            android:name="com.google.mlkit.common.internal.MlKitComponentDiscoveryService"
182-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:10:13-91
183            android:directBootAware="true"
183-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:17:13-43
184            android:exported="false" >
184-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:11:13-37
185            <meta-data
185-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:12:13-14:85
186                android:name="com.google.firebase.components:com.google.mlkit.vision.barcode.internal.BarcodeRegistrar"
186-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:13:17-120
187                android:value="com.google.firebase.components.ComponentRegistrar" />
187-->[com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\872fb2d2543fca5ee8b3e4cbfc4b4b01\transformed\play-services-mlkit-barcode-scanning-18.3.0\AndroidManifest.xml:14:17-82
188            <meta-data
188-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:12:13-14:85
189                android:name="com.google.firebase.components:com.google.mlkit.vision.common.internal.VisionCommonRegistrar"
189-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:13:17-124
190                android:value="com.google.firebase.components.ComponentRegistrar" />
190-->[com.google.mlkit:vision-common:17.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\3b67eb861d274667694e48cdab59cd8d\transformed\vision-common-17.3.0\AndroidManifest.xml:14:17-82
191            <meta-data
191-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:20:13-22:85
192                android:name="com.google.firebase.components:com.google.mlkit.common.internal.CommonComponentRegistrar"
192-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:21:17-120
193                android:value="com.google.firebase.components.ComponentRegistrar" />
193-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:22:17-82
194        </service>
195
196        <provider
196-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:9:9-13:38
197            android:name="com.google.mlkit.common.internal.MlKitInitProvider"
197-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:10:13-78
198            android:authorities="com.pluck.mlkitinitprovider"
198-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:11:13-69
199            android:exported="false"
199-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:12:13-37
200            android:initOrder="99" />
200-->[com.google.mlkit:common:18.9.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\b6b1fb1d9e6efca93658b1afc89ec176\transformed\common-18.9.0\AndroidManifest.xml:13:13-35
201        <provider
201-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:31:9-39:20
202            android:name="androidx.startup.InitializationProvider"
202-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:32:13-67
203            android:authorities="com.pluck.androidx-startup"
203-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:33:13-68
204            android:exported="false" >
204-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:34:13-37
205            <meta-data
205-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:36:13-38:52
206                android:name="androidx.work.WorkManagerInitializer"
206-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:37:17-68
207                android:value="androidx.startup" />
207-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:38:17-49
208            <meta-data
208-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:29:13-31:52
209                android:name="androidx.emoji2.text.EmojiCompatInitializer"
209-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:30:17-75
210                android:value="androidx.startup" />
210-->[androidx.emoji2:emoji2:1.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0\AndroidManifest.xml:31:17-49
211            <meta-data
211-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:29:13-31:52
212                android:name="androidx.lifecycle.ProcessLifecycleInitializer"
212-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:30:17-78
213                android:value="androidx.startup" />
213-->[androidx.lifecycle:lifecycle-process:2.6.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\e5b6761745fbade578e4647cfd322951\transformed\lifecycle-process-2.6.2\AndroidManifest.xml:31:17-49
214            <meta-data
214-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:29:13-31:52
215                android:name="androidx.profileinstaller.ProfileInstallerInitializer"
215-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:30:17-85
216                android:value="androidx.startup" />
216-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:31:17-49
217        </provider>
218
219        <service
219-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:41:9-46:35
220            android:name="androidx.work.impl.background.systemalarm.SystemAlarmService"
220-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:42:13-88
221            android:directBootAware="false"
221-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:43:13-44
222            android:enabled="@bool/enable_system_alarm_service_default"
222-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:44:13-72
223            android:exported="false" />
223-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:45:13-37
224        <service
224-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:47:9-53:35
225            android:name="androidx.work.impl.background.systemjob.SystemJobService"
225-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:48:13-84
226            android:directBootAware="false"
226-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:49:13-44
227            android:enabled="@bool/enable_system_job_service_default"
227-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:50:13-70
228            android:exported="true"
228-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:51:13-36
229            android:permission="android.permission.BIND_JOB_SERVICE" />
229-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:52:13-69
230        <service
230-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:54:9-59:35
231            android:name="androidx.work.impl.foreground.SystemForegroundService"
231-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:55:13-81
232            android:directBootAware="false"
232-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:56:13-44
233            android:enabled="@bool/enable_system_foreground_service_default"
233-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:57:13-77
234            android:exported="false" />
234-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:58:13-37
235
236        <receiver
236-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:61:9-66:35
237            android:name="androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver"
237-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:62:13-88
238            android:directBootAware="false"
238-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:63:13-44
239            android:enabled="true"
239-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:64:13-35
240            android:exported="false" />
240-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:65:13-37
241        <receiver
241-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:67:9-77:20
242            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryChargingProxy"
242-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:68:13-106
243            android:directBootAware="false"
243-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:69:13-44
244            android:enabled="false"
244-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:70:13-36
245            android:exported="false" >
245-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:71:13-37
246            <intent-filter>
246-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:73:13-76:29
247                <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
247-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:74:17-87
247-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:74:25-84
248                <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
248-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:75:17-90
248-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:75:25-87
249            </intent-filter>
250        </receiver>
251        <receiver
251-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:78:9-88:20
252            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$BatteryNotLowProxy"
252-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:79:13-104
253            android:directBootAware="false"
253-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:80:13-44
254            android:enabled="false"
254-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:81:13-36
255            android:exported="false" >
255-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:82:13-37
256            <intent-filter>
256-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:84:13-87:29
257                <action android:name="android.intent.action.BATTERY_OKAY" />
257-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:85:17-77
257-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:85:25-74
258                <action android:name="android.intent.action.BATTERY_LOW" />
258-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:86:17-76
258-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:86:25-73
259            </intent-filter>
260        </receiver>
261        <receiver
261-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:89:9-99:20
262            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$StorageNotLowProxy"
262-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:90:13-104
263            android:directBootAware="false"
263-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:91:13-44
264            android:enabled="false"
264-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:92:13-36
265            android:exported="false" >
265-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:93:13-37
266            <intent-filter>
266-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:95:13-98:29
267                <action android:name="android.intent.action.DEVICE_STORAGE_LOW" />
267-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:96:17-83
267-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:96:25-80
268                <action android:name="android.intent.action.DEVICE_STORAGE_OK" />
268-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:97:17-82
268-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:97:25-79
269            </intent-filter>
270        </receiver>
271        <receiver
271-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:100:9-109:20
272            android:name="androidx.work.impl.background.systemalarm.ConstraintProxy$NetworkStateProxy"
272-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:101:13-103
273            android:directBootAware="false"
273-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:102:13-44
274            android:enabled="false"
274-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:103:13-36
275            android:exported="false" >
275-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:104:13-37
276            <intent-filter>
276-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:106:13-108:29
277                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
277-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:107:17-79
277-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:107:25-76
278            </intent-filter>
279        </receiver>
280        <receiver
280-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:110:9-121:20
281            android:name="androidx.work.impl.background.systemalarm.RescheduleReceiver"
281-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:111:13-88
282            android:directBootAware="false"
282-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:112:13-44
283            android:enabled="false"
283-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:113:13-36
284            android:exported="false" >
284-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:114:13-37
285            <intent-filter>
285-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:116:13-120:29
286                <action android:name="android.intent.action.BOOT_COMPLETED" />
286-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:117:17-79
286-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:117:25-76
287                <action android:name="android.intent.action.TIME_SET" />
287-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:118:17-73
287-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:118:25-70
288                <action android:name="android.intent.action.TIMEZONE_CHANGED" />
288-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:119:17-81
288-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:119:25-78
289            </intent-filter>
290        </receiver>
291        <receiver
291-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:122:9-131:20
292            android:name="androidx.work.impl.background.systemalarm.ConstraintProxyUpdateReceiver"
292-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:123:13-99
293            android:directBootAware="false"
293-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:124:13-44
294            android:enabled="@bool/enable_system_alarm_service_default"
294-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:125:13-72
295            android:exported="false" >
295-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:126:13-37
296            <intent-filter>
296-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:128:13-130:29
297                <action android:name="androidx.work.impl.background.systemalarm.UpdateProxies" />
297-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:129:17-98
297-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:129:25-95
298            </intent-filter>
299        </receiver>
300        <receiver
300-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:132:9-142:20
301            android:name="androidx.work.impl.diagnostics.DiagnosticsReceiver"
301-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:133:13-78
302            android:directBootAware="false"
302-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:134:13-44
303            android:enabled="true"
303-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:135:13-35
304            android:exported="true"
304-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:136:13-36
305            android:permission="android.permission.DUMP" >
305-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:137:13-57
306            <intent-filter>
306-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:139:13-141:29
307                <action android:name="androidx.work.diagnostics.REQUEST_DIAGNOSTICS" />
307-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:140:17-88
307-->[androidx.work:work-runtime:2.7.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1\AndroidManifest.xml:140:25-85
308            </intent-filter>
309        </receiver>
310        <receiver
310-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:29:9-33:20
311            android:name="com.google.android.gms.measurement.AppMeasurementReceiver"
311-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:30:13-85
312            android:enabled="true"
312-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:31:13-35
313            android:exported="false" >
313-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:32:13-37
314        </receiver>
315
316        <service
316-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:35:9-38:40
317            android:name="com.google.android.gms.measurement.AppMeasurementService"
317-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:36:13-84
318            android:enabled="true"
318-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:37:13-35
319            android:exported="false" />
319-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:38:13-37
320        <service
320-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:39:9-43:72
321            android:name="com.google.android.gms.measurement.AppMeasurementJobService"
321-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:40:13-87
322            android:enabled="true"
322-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:41:13-35
323            android:exported="false"
323-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:42:13-37
324            android:permission="android.permission.BIND_JOB_SERVICE" />
324-->[com.google.android.gms:play-services-measurement:21.3.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f46bfbd4893af89850a1c185429cb04c\transformed\play-services-measurement-21.3.0\AndroidManifest.xml:43:13-69
325
326        <activity
326-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:20:9-22:45
327            android:name="com.google.android.gms.common.api.GoogleApiActivity"
327-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:20:19-85
328            android:exported="false"
328-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:22:19-43
329            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
329-->[com.google.android.gms:play-services-base:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c40b820201b5c28e2ea77cce3b307c7\transformed\play-services-base-18.1.0\AndroidManifest.xml:21:19-78
330
331        <provider
331-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:25:9-30:39
332            android:name="com.google.firebase.provider.FirebaseInitProvider"
332-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:26:13-77
333            android:authorities="com.pluck.firebaseinitprovider"
333-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:27:13-72
334            android:directBootAware="true"
334-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:28:13-43
335            android:exported="false"
335-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:29:13-37
336            android:initOrder="100" />
336-->[com.google.firebase:firebase-common:20.3.2] C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c5e1bc8741b7ef6de955bf65e2aff5b\transformed\firebase-common-20.3.2\AndroidManifest.xml:30:13-36
337
338        <meta-data
338-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:21:9-23:69
339            android:name="com.google.android.gms.version"
339-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:22:13-58
340            android:value="@integer/google_play_services_version" />
340-->[com.google.android.gms:play-services-basement:18.1.0] C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bf506075083b0dc7dd6ade839e922ad\transformed\play-services-basement-18.1.0\AndroidManifest.xml:23:13-66
341
342        <service
342-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:25:9-28:40
343            android:name="androidx.room.MultiInstanceInvalidationService"
343-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:26:13-74
344            android:directBootAware="true"
344-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:27:13-43
345            android:exported="false" />
345-->[androidx.room:room-runtime:2.2.5] C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5\AndroidManifest.xml:28:13-37
346        <service
346-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:29:9-35:19
347            android:name="com.google.android.datatransport.runtime.backends.TransportBackendDiscovery"
347-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:30:13-103
348            android:exported="false" >
348-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:31:13-37
349            <meta-data
349-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:32:13-34:39
350                android:name="backend:com.google.android.datatransport.cct.CctBackendFactory"
350-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:33:17-94
351                android:value="cct" />
351-->[com.google.android.datatransport:transport-backend-cct:2.3.3] C:\Users\Ahmed\.gradle\caches\8.13\transforms\0207cfb034257c0e96150af36ec42603\transformed\transport-backend-cct-2.3.3\AndroidManifest.xml:34:17-36
352        </service>
353        <service
353-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:26:9-30:19
354            android:name="com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService"
354-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:27:13-117
355            android:exported="false"
355-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:28:13-37
356            android:permission="android.permission.BIND_JOB_SERVICE" >
356-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:29:13-69
357        </service>
358
359        <receiver
359-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:32:9-34:40
360            android:name="com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerSchedulerBroadcastReceiver"
360-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:33:13-132
361            android:exported="false" />
361-->[com.google.android.datatransport:transport-runtime:2.2.6] C:\Users\Ahmed\.gradle\caches\8.13\transforms\617f13d89cca4a0d64e4f8efcd9c3a9c\transformed\transport-runtime-2.2.6\AndroidManifest.xml:34:13-37
362        <receiver
362-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:34:9-52:20
363            android:name="androidx.profileinstaller.ProfileInstallReceiver"
363-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:35:13-76
364            android:directBootAware="false"
364-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:36:13-44
365            android:enabled="true"
365-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:37:13-35
366            android:exported="true"
366-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:38:13-36
367            android:permission="android.permission.DUMP" >
367-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:39:13-57
368            <intent-filter>
368-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:40:13-42:29
369                <action android:name="androidx.profileinstaller.action.INSTALL_PROFILE" />
369-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:41:17-91
369-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:41:25-88
370            </intent-filter>
371            <intent-filter>
371-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:43:13-45:29
372                <action android:name="androidx.profileinstaller.action.SKIP_FILE" />
372-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:44:17-85
372-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:44:25-82
373            </intent-filter>
374            <intent-filter>
374-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:46:13-48:29
375                <action android:name="androidx.profileinstaller.action.SAVE_PROFILE" />
375-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:47:17-88
375-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:47:25-85
376            </intent-filter>
377            <intent-filter>
377-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:49:13-51:29
378                <action android:name="androidx.profileinstaller.action.BENCHMARK_OPERATION" />
378-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:50:17-95
378-->[androidx.profileinstaller:profileinstaller:1.3.1] C:\Users\Ahmed\.gradle\caches\8.13\transforms\f89a56434b922c9bad55dc2538d7948c\transformed\profileinstaller-1.3.1\AndroidManifest.xml:50:25-92
379            </intent-filter>
380        </receiver>
381    </application>
382
383</manifest>

Merged manifest saved to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifest\release\AndroidManifest.xml
Resolve mutations for :app:processReleaseManifest (Thread[Execution worker Thread 2,5,main]) started.
:app:processReleaseManifest (Thread[Execution worker Thread 9,5,main]) started.

> Task :app:processReleaseManifest
Caching disabled for task ':app:processReleaseManifest' because:
  Build cache is disabled
Task ':app:processReleaseManifest' is not up-to-date because:
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\release has been removed.
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\release\AndroidManifest.xml has been removed.
  Output property 'multiApkManifestOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_manifests\release\output-metadata.json has been removed.
Resolve mutations for :app:processReleaseManifestForPackage (Thread[Execution worker Thread 9,5,main]) started.
:app:processReleaseManifestForPackage (Thread[Execution worker Thread 9,5,main]) started.
Resolve mutations for :app:javaPreCompileRelease (Thread[Execution worker Thread 2,5,main]) started.
:app:javaPreCompileRelease (Thread[Execution worker Thread 2,5,main]) started.
Resolve mutations for :app:extractProguardFiles (Thread[Execution worker Thread 14,5,main]) started.
:app:extractProguardFiles (Thread[Execution worker Thread 14,5,main]) started.

> Task :app:javaPreCompileRelease
Caching disabled for task ':app:javaPreCompileRelease' because:
  Build cache is disabled
Task ':app:javaPreCompileRelease' is not up-to-date because:
  Output property 'annotationProcessorListFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\annotation_processor_list\release\annotationProcessors.json has been removed.

> Task :app:extractProguardFiles
Caching disabled for task ':app:extractProguardFiles' because:
  Build cache is disabled
  Tasks that execute quickly usually do not benefit from caching.
Task ':app:extractProguardFiles' is not up-to-date because:
  Output property 'proguardFilesDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\default_proguard_files\global has been removed.
  Output property 'proguardFilesDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\default_proguard_files\global\proguard-android-optimize.txt-8.1.3 has been removed.
  Output property 'proguardFilesDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\default_proguard_files\global\proguard-android.txt-8.1.3 has been removed.
  and more...
Resolve mutations for :app:mergeReleaseJniLibFolders (Thread[Execution worker Thread 14,5,main]) started.
:app:mergeReleaseJniLibFolders (Thread[Execution worker Thread 14,5,main]) started.

> Task :app:mergeReleaseJniLibFolders
Caching disabled for task ':app:mergeReleaseJniLibFolders' because:
  Build cache is disabled
Task ':app:mergeReleaseJniLibFolders' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_jni_libs\release\out has been removed.
The input changes require a full rebuild for incremental task ':app:mergeReleaseJniLibFolders'.
Resolve mutations for :app:mergeReleaseNativeLibs (Thread[Execution worker,5,main]) started.
:app:mergeReleaseNativeLibs (Thread[Execution worker,5,main]) started.
Resolve mutations for :app:checkReleaseDuplicateClasses (Thread[Execution worker Thread 14,5,main]) started.
:app:checkReleaseDuplicateClasses (Thread[Execution worker Thread 14,5,main]) started.

> Task :app:processReleaseManifestForPackage
Custom actions are attached to task ':app:processReleaseManifestForPackage'.
Caching disabled for task ':app:processReleaseManifestForPackage' because:
  Build cache is disabled
Task ':app:processReleaseManifestForPackage' is not up-to-date because:
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\release has been removed.
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\release\AndroidManifest.xml has been removed.
  Output property 'packageManifests' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\packaged_manifests\release\output-metadata.json has been removed.
Resolve mutations for :app:desugarReleaseFileDependencies (Thread[Execution worker Thread 2,5,main]) started.
:app:desugarReleaseFileDependencies (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:desugarReleaseFileDependencies
Caching disabled for task ':app:desugarReleaseFileDependencies' because:
  Build cache is disabled
Task ':app:desugarReleaseFileDependencies' is not up-to-date because:
  Output property 'outputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_file_lib_dex_archives\release has been removed.
  Output property 'outputGlobalSynthetics' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\global_synthetics_file_lib\release has been removed.
Resolve mutations for :app:mergeReleaseArtProfile (Thread[Execution worker Thread 2,5,main]) started.
:app:mergeReleaseArtProfile (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:checkReleaseDuplicateClasses
Caching disabled for task ':app:checkReleaseDuplicateClasses' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:checkReleaseDuplicateClasses' is not up-to-date because:
  Output property 'dummyOutputDirectory' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\duplicate_classes_check\release has been removed.

> Task :app:mergeReleaseResources
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-w320dp-land-v13\values-w320dp-land-v13.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-w320dp-land-v13_values-w320dp-land-v13.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-v24\values-v24.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-v24_values-v24.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-ka\values-ka.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-ka_values-ka.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-cs\values-cs.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-cs_values-cs.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-uk\values-uk.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-uk_values-uk.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-vi\values-vi.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-vi_values-vi.arsc.flat
Compiling XML table Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\incremental\release\mergeReleaseResources\merged.dir\values-s
mall-v4\values-small-v4.xml to Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_res\release\values-small-v4_values-small-v4.arsc.flat

> Task :app:mergeReleaseNativeLibs
Caching disabled for task ':app:mergeReleaseNativeLibs' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:mergeReleaseNativeLibs' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a has been removed.
  and more...
Resolve mutations for :app:processReleaseResources (Thread[Execution worker Thread 5,5,main]) started.
:app:processReleaseResources (Thread[Execution worker Thread 5,5,main]) started.

> Task :app:mergeReleaseArtProfile                                                                                                                               
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\79a0e9d6e7549546d45b28012505c9ca\transformed\cloudinary-android-2.5.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c57e699d6d3fdfcc8117035fa000776\transformed\material3-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\5690d428903aa172c9993823502c6e91\transformed\cloudinary-android-preprocess-2.5.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\451d346a44b54de4969b9580f180368a\transformed\maps-compose-4.3.3 because:        
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\65d0c7b7133ba44e76ad823bb74c2f7b\transformed\cloudinary-android-download-2.5.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\88ef52f015ec9d6fe8cbc4896fdecc97\transformed\cloudinary-android-core-2.5.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\182fe93a2c05f703c0be618f4e29b6ea\transformed\material-1.4.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e9d26fad69a8035c5a1e8031032bda63\transformed\constraintlayout-2.0.1 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ef0651bbaaf20d6f1ce6842f3cf3ea4\transformed\maps-ktx-5.0.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\37afc57fc8e17c84487bfd817601c819\transformed\play-services-maps-18.2.0 because: 
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6ead494a8b1e82288a5389039f03df63\transformed\work-runtime-2.7.1 because:        
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d1c236d1be356058c5d38a6407020414\transformed\material-icons-core-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b00e01cd2630685c5896c00ed4d387a4\transformed\material-icons-extended-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0bca281d65c6305c18f08d784f02db44\transformed\material-ripple-release because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b45016de5999ab45e3c6f93f18b8f0b4\transformed\animation-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c7ffcccc0c7d0fe0808a1e1d43a5c4bc\transformed\animation-core-release because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ad701c3da8b6f314a074147b61622f51\transformed\foundation-layout-release because: 
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\901560b52dc43160942dd52a8d5ddb66\transformed\ui-unit-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\75cf2c8d302443c5f5a11437f45fc7cc\transformed\foundation-release because:        
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a0b4f0517b364306f98aab095621a04a\transformed\ui-geometry-release because:       
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a2c176c83e3255c54c294c740419995\transformed\ui-util-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4ab1ce79a4e8be4025b59fbb6dbd8d67\transformed\ui-graphics-release because:       
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e00013d98828add3ca1a88b1ea5b2fea\transformed\ui-tooling-preview-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\216fcb22cce24b735816ff2d550d4b95\transformed\ui-text-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\314c779f047198335435d76c74a49af0\transformed\emoji2-views-helper-1.3.0 because: 
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\acb79c9da28122b07c91df3c73c67c9d\transformed\emoji2-1.3.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\131407770d0a55c5099b1c9bcca5d9b3\transformed\lifecycle-service-2.6.2 because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\80c7788a63291a048d3114fba53e2731\transformed\legacy-support-v4-1.0.0 because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a40505be55533e631038632642044d4d\transformed\legacy-support-core-ui-1.0.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f66a86c88c7490b271b2934db5002420\transformed\dynamicanimation-1.0.0 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b0b2bcd485495502849906c506e947e6\transformed\media3-exoplayer-dash-1.1.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2218549c45fe27b24d3d903e2ebcb2b9\transformed\media3-exoplayer-hls-1.1.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\35c18b15d8de5830b76dc4f86b05d870\transformed\litr-1.4.16 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\be618a094c6eadd34494571884db27e0\transformed\media3-exoplayer-1.1.1 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9c4e7b649c5c69897c9466a532c6dc9a\transformed\media3-container-1.1.1 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\38e0bd13d79fca62275fdd12c283e43a\transformed\media3-extractor-1.1.1 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\5bfcbb13e13c03764b50018d4fbf6b43\transformed\media3-datasource-1.1.1 because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9d0c36692780219799cce233fa774885\transformed\media3-decoder-1.1.1 because:      
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d03c1b0898c92f40bf904e2184a2540\transformed\media3-database-1.1.1 because:     
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\353aa858e1189c1e15533a12e4bde9b1\transformed\media3-common-1.1.1 because:       
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\74feaf88e5d6ab5c88c4da35184e5d8f\transformed\media3-ui-1.1.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a7b689b97281718ee32092e564efecb5\transformed\viewpager2-1.0.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f80a44333a3d0d94d28b94082a80904e\transformed\recyclerview-1.3.0 because:        
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\acca26e9976f0a24c694c71b93c16e0b\transformed\coordinatorlayout-1.1.0 because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\250967770574026fa0b82276e0192fb6\transformed\transition-1.2.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\5b4b9d4797d7708ad60359aebb321d1f\transformed\media-1.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6064e677c16d5c58259d0cf627b5d8fd\transformed\slidingpanelayout-1.0.0 because:   
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8b3f05fe21219c1462de54f164f791ff\transformed\swiperefreshlayout-1.0.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\266a76d3d546a9e118935d822877f314\transformed\asynclayoutinflater-1.0.0 because: 
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\75f0140c7f717d66951ed03f3680fc27\transformed\runtime-saveable-release because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\45d9088c59b0fab5a007d7b04dd92a9f\transformed\ui-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6dc17ed3b6be82e4f8af8c05b8da1b0c\transformed\runtime-release because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\093f96a4c1a2638e480d7fbf99d765df\transformed\annotation-experimental-1.4.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7afb9241d7e9978125cfc451bb1a1d12\transformed\room-runtime-2.2.5 because:        
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f082b50cabfe0fac7f56ffe164c4ffc\transformed\cloudinary-android-ui-2.5.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d4e55c19a61023e2414aba427f7766fd\transformed\viewbinding-7.4.2 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3336ea62311c9c6f6a89ac10bc23051\transformed\cardview-1.0.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7329e813a90b3c51a16cb2cf2e9da6e6\transformed\sqlite-2.1.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0d615bf23df10d96d1b53dd453627014\transformed\sqlite-framework-2.1.0 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7b7fa33f44f3b80d898f9552ac844245\transformed\memory-type-native-2.6.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f5ef6ded1df61098fe18bc6c64e9b21\transformed\drawee-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\50d0d0f04287cb367c1ccf2956360961\transformed\nativeimagefilters-2.6.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a4fe5708b949a2d6a5e628c9527e07b\transformed\memory-type-java-2.6.0 because:    
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\23264cf826b21068683bc0ce78f43f3e\transformed\fresco-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d69946b300ec87149af263e1a8b97dd0\transformed\imagepipeline-native-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\aaa20b67155259c75fb91f26fa0a8b93\transformed\imagepipeline-2.6.0 because:       
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3be242a23f4988743ed36ef1693ed26f\transformed\memory-type-ashmem-2.6.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\dab1ba1b4aa7f5db10c1872e11a73484\transformed\nativeimagetranscoder-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b36b9dee027cfeda0b9fb3d854894fd5\transformed\imagepipeline-base-2.6.0 because:  
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d73423f7dad861f055ffe7756f86f5b1\transformed\soloader-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\282ba00101d4caddb474bbe6d8097617\transformed\soloader-0.10.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\30fcdd209d1f98a1d3e17edc923138fb\transformed\ui-common-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2c9c7bf14cb4e9247c6082c2483ba8be\transformed\middleware-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for AarTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b3808632869cf9db222c403621fd36c3\transformed\fbcore-2.6.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for task ':app:mergeReleaseArtProfile' because:
  Build cache is disabled
  Tasks that merge files into a directory/jar without further processing usually do not benefit from caching. Also, their outputs are usually large, which would consume a lot of space when cached.
Task ':app:mergeReleaseArtProfile' is not up-to-date because:
  Output property 'outputFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_art_profile\release\baseline-prof.txt has been removed.
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\baselineProfiles', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\baselineProfiles', not found
Resolve mutations for :app:stripReleaseDebugSymbols (Thread[Execution worker Thread 14,5,main]) started.
:app:stripReleaseDebugSymbols (Thread[Execution worker Thread 14,5,main]) started.
Resolve mutations for :app:mergeExtDexRelease (Thread[Execution worker Thread 12,5,main]) started.
:app:mergeExtDexRelease (Thread[included builds,5,main]) started.

> Task :app:stripReleaseDebugSymbols
Caching disabled for task ':app:stripReleaseDebugSymbols' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:stripReleaseDebugSymbols' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\release\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\release\out\lib has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\stripped_native_libs\release\out\lib\arm64-v8a has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:stripReleaseDebugSymbols'.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libimage_processing_util_jni.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: android.ndkPath from module build.gradle is not set
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: android.ndkPath from module build.gradle is not set
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libimagepipeline.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
C/C++: ndk.dir in local.properties is not set
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libnative-imagetranscoder.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: ndk.dir in local.properties is not set
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libnative-filters.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkPath from module build.gradle is not set
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libbarhopper_v3.so' due to missing strip tool for ABI 'ARM64_V8A'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libimage_processing_util_jni.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libbarhopper_v3.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: ndk.dir in local.properties is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libbarhopper_v3.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libimagepipeline.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libbarhopper_v3.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libimagepipeline.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: android.ndkVersion from module build.gradle is [not set]
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libnative-filters.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: ndk.dir in local.properties is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libnative-imagetranscoder.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libnative-imagetranscoder.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libnative-filters.so' due to missing strip tool for ABI 'ARMEABI_V7A'. Packaging it as is.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libimage_processing_util_jni.so' due to missing strip tool for ABI 'X86'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: android.ndkPath from module build.gradle is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: ndk.dir in local.properties is not set
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libimagepipeline.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libimage_processing_util_jni.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libnative-imagetranscoder.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to strip library 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libnative-filters.so' due to missing strip tool for ABI 'X86_64'. Packaging it as is.
Unable to strip the following libraries, packaging them as they are: libbarhopper_v3.so, libimage_processing_util_jni.so, libimagepipeline.so, libnative-filters.so, libnative-imagetranscoder.so.

> Task :app:processReleaseResources
Registered task dependencies: app:releaseCompileClasspath
Skipping misunderstood TO dep string: androidx.compose.ui:ui
Skipping misunderstood TO dep string: androidx.compose.ui:ui-tooling-preview
Skipping misunderstood TO dep string: androidx.compose.material3:material3
Skipping misunderstood TO dep string: androidx.compose.material:material-icons-extended
Starting dependency analysis
Caching disabled for task ':app:processReleaseResources' because:
  Build cache is disabled
Task ':app:processReleaseResources' is not up-to-date because:
  Output property 'RClassOutputJar' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compile_and_runtime_not_namespaced_r_class_jar\release\R.jar has been removed.
  Output property 'resPackageOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\processed_res\release\out has been removed.
  Output property 'resPackageOutputFolder' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\processed_res\release\out\output-metadata.json has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:processReleaseResources'.
AAPT2 aapt2-8.1.3-10154469-windows Daemon #0: starting
Aapt output file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\processed_res\release\out\resources-release.ap_

> Task :app:mergeExtDexRelease
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c26f9d4b1b42a3b5de5896d96634a91\transformed\cloudinary-android-preprocess-2.5.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\6c26f9d4b1b42a3b5de5896d96634a91\transformed\cloudinary-android-preprocess-2.5.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\33df3ac21a7e2bb662b94adb6262cb2f\transformed\cloudinary-android-ui-2.5.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d8f24c7480f1166765379676cf3f00c0\transformed\maps-compose-4.3.3-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\d8f24c7480f1166765379676cf3f00c0\transformed\maps-compose-4.3.3-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\33df3ac21a7e2bb662b94adb6262cb2f\transformed\cloudinary-android-ui-2.5.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fc0cb1fd32094632969af323649e0edc\transformed\cloudinary-android-download-2.5.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\050cccd40f6f841244daac8bfce34b50\transformed\cloudinary-android-core-2.5.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4e491f9cf99b1ba8105a24bf6afe57cf\transformed\material-1.4.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\abcb0acdab5b0af701d6a124e03873a3\transformed\constraintlayout-2.0.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\050cccd40f6f841244daac8bfce34b50\transformed\cloudinary-android-core-2.5.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\fc0cb1fd32094632969af323649e0edc\transformed\cloudinary-android-download-2.5.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\4e491f9cf99b1ba8105a24bf6afe57cf\transformed\material-1.4.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\abcb0acdab5b0af701d6a124e03873a3\transformed\constraintlayout-2.0.1-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3dd67bf91a00381f4bac4709e256e82c\transformed\maps-ktx-5.0.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d5a6e29a01609f8adcd0ecd443aedc58\transformed\play-services-maps-18.2.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\3dd67bf91a00381f4bac4709e256e82c\transformed\maps-ktx-5.0.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\d5a6e29a01609f8adcd0ecd443aedc58\transformed\play-services-maps-18.2.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\22ab7ed2d7bef8468e542d4a18b67073\transformed\material-icons-core-release-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f96aa6e15788d613f5fc8d5aeca524b\transformed\material-icons-extended-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\22ab7ed2d7bef8468e542d4a18b67073\transformed\material-icons-core-release-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\4f96aa6e15788d613f5fc8d5aeca524b\transformed\material-icons-extended-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\5473b0719da58f6bba41bb3a253ce904\transformed\material-ripple-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\5473b0719da58f6bba41bb3a253ce904\transformed\material-ripple-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\11d32c01931115caaf6b5c6f7f95cdb8\transformed\work-runtime-2.7.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\11d32c01931115caaf6b5c6f7f95cdb8\transformed\work-runtime-2.7.1-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b149c3af6381eb59f6b1fe28ac37a2ca\transformed\animation-core-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\b149c3af6381eb59f6b1fe28ac37a2ca\transformed\animation-core-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\53420302d26fe51d1cbc106c5747f81d\transformed\cloudinary-android-preprocess-2.5.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f40a31ada8c6549f4b5b7de3c92bbcf2\transformed\cloudinary-android-download-2.5.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9188b23dd6b7abcb7cd56104030a247d\transformed\animation-release-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd5f5369378c85ddcac26172d8fd73c8\transformed\foundation-layout-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd5f5369378c85ddcac26172d8fd73c8\transformed\foundation-layout-release-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\9188b23dd6b7abcb7cd56104030a247d\transformed\animation-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\cac73b504f4418df7dcfb71ec61f82ef\transformed\maps-ktx-5.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\734cd36a0c834f353b7e8f71cdeb27ae\transformed\material-ripple-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\64a16ea237bbaa980f34ab6c1ed1c5c6\transformed\foundation-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\64a16ea237bbaa980f34ab6c1ed1c5c6\transformed\foundation-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8961fbd793217a8c5afe7da88a75d6ee\transformed\ui-unit-release-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\41786be34d3d5513bbdda2f50b1b7ee4\transformed\cloudinary-android-ui-2.5.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\8961fbd793217a8c5afe7da88a75d6ee\transformed\ui-unit-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bc934a8428d4699cf868bf2bbd96106\transformed\ui-geometry-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\6bc934a8428d4699cf868bf2bbd96106\transformed\ui-geometry-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\05c9443c6ec61cce85f0bb98afa66e46\transformed\cloudinary-android-core-2.5.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7cdab0de8391867a0d340d1b1aebf8f5\transformed\ui-util-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\7cdab0de8391867a0d340d1b1aebf8f5\transformed\ui-util-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\1c9b0e9444176aeee21beb123896b30e\transformed\ui-util-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\07d80a43fcfa87aaefeed19a7fc079f2\transformed\ui-graphics-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\07d80a43fcfa87aaefeed19a7fc079f2\transformed\ui-graphics-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\694a99823995b94c48c050eead7ad000\transformed\ui-geometry-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b4f531c884b81753b9cb718391e95199\transformed\ui-tooling-preview-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\b4f531c884b81753b9cb718391e95199\transformed\ui-tooling-preview-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ae24950507bdd15c6673b93e591bee5\transformed\ui-tooling-preview-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0ebedb42cf1715d8f7b9eb9c74aedcda\transformed\ui-text-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\0ebedb42cf1715d8f7b9eb9c74aedcda\transformed\ui-text-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f539ee854fc70b833bc0bc641e991333\transformed\ui-unit-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c993ba64902dd11ef5d3c5f5951a3f25\transformed\emoji2-views-helper-1.3.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\c993ba64902dd11ef5d3c5f5951a3f25\transformed\emoji2-views-helper-1.3.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fdfa3c8c021e38464cdff296f812fd14\transformed\animation-core-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\58d67342bb3e6f5859319a97de6c09aa\transformed\emoji2-1.3.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\58d67342bb3e6f5859319a97de6c09aa\transformed\emoji2-1.3.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9af0046693a0658ac5e3ccb68856c7aa\transformed\emoji2-views-helper-1.3.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\070bca69dd086a17448ae04aa98f2de8\transformed\lifecycle-service-2.6.2-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\070bca69dd086a17448ae04aa98f2de8\transformed\lifecycle-service-2.6.2-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b96030237b1b671504089bc376613549\transformed\lifecycle-service-2.6.2-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ea7b63c7476e381c18828e6eac4efa1b\transformed\legacy-support-v4-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\ea7b63c7476e381c18828e6eac4efa1b\transformed\legacy-support-v4-1.0.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9e134ccc027321ff6b8d4ecde6d873ef\transformed\legacy-support-core-ui-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\9e134ccc027321ff6b8d4ecde6d873ef\transformed\legacy-support-core-ui-1.0.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\46b39c13e54d8cb621fad5db1bd23e6e\transformed\material-icons-core-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6f0f4002a453ee18143604bc3e2e330b\transformed\dynamicanimation-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\6f0f4002a453ee18143604bc3e2e330b\transformed\dynamicanimation-1.0.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f0e6a7affa27d62e20edd50aa191e20b\transformed\maps-compose-4.3.3-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d7eade9c023d9bfa044454ef8f3af3d7\transformed\legacy-support-core-ui-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\547d20001430a29750ea5f62b77a0a36\transformed\litr-1.4.16-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\547d20001430a29750ea5f62b77a0a36\transformed\litr-1.4.16-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c41636b8081ec57abc8184d670a37083\transformed\media3-exoplayer-dash-1.1.1-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f157715ea797c3022da311d11957d76b\transformed\dynamicanimation-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\c41636b8081ec57abc8184d670a37083\transformed\media3-exoplayer-dash-1.1.1-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b5b051059a9492d482a49e29cd1c1429\transformed\media3-exoplayer-hls-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\b5b051059a9492d482a49e29cd1c1429\transformed\media3-exoplayer-hls-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\5f7f8990e677a9546387ce81f55b2c70\transformed\emoji2-1.3.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\72e203aab07cad1266c441b15dc552e6\transformed\media3-exoplayer-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\72e203aab07cad1266c441b15dc552e6\transformed\media3-exoplayer-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3679e132e53fb699b01dec210268eea0\transformed\litr-1.4.16-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\01325f91b3c1f8d58dfd6d37845197f5\transformed\ui-graphics-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\96fb8ef5e841c893588ff023a6a383ff\transformed\media3-extractor-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\96fb8ef5e841c893588ff023a6a383ff\transformed\media3-extractor-1.1.1-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c215c908094e46b2b7720951be9884e\transformed\media3-container-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\8c215c908094e46b2b7720951be9884e\transformed\media3-container-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\de42e3efd8b0d34c7610ce3b82e15a4e\transformed\media3-container-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\821b490bdfe3925d16965d2df3a2bb97\transformed\media3-datasource-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\821b490bdfe3925d16965d2df3a2bb97\transformed\media3-datasource-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\1888465fa6d82e24b8a7870bb8f8678c\transformed\media3-exoplayer-hls-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e11d472b9ac86d989a426a0a93398b7d\transformed\media3-decoder-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\e11d472b9ac86d989a426a0a93398b7d\transformed\media3-decoder-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62649df952eb9c006bbecd75c7b2aa7\transformed\media3-decoder-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\1997cad92ecfed121e97f447c30fd50d\transformed\media3-database-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\1997cad92ecfed121e97f447c30fd50d\transformed\media3-database-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e83f79ec6f238352501542f4d93f04ad\transformed\media3-database-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\19318b0936781cbb7c1d13cc042d2cbc\transformed\media3-common-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\19318b0936781cbb7c1d13cc042d2cbc\transformed\media3-common-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\845e4280577ab67df5990408811bd7dc\transformed\media3-exoplayer-dash-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\cfba7308eceabd96c38cd478044e5b3e\transformed\media3-ui-1.1.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\cfba7308eceabd96c38cd478044e5b3e\transformed\media3-ui-1.1.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ede5a8c5508292d2a4120b5803d25749\transformed\media3-datasource-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e9e678793f4759b0d1549c8d1ead3d\transformed\viewpager2-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\69e9e678793f4759b0d1549c8d1ead3d\transformed\viewpager2-1.0.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\701892e69bdaba122d9152e160f3f426\transformed\animation-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d496b1773fe040906420a5d64529af0b\transformed\recyclerview-1.3.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\d496b1773fe040906420a5d64529af0b\transformed\recyclerview-1.3.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a83f3fc4158159c28e4f91309311dba\transformed\viewpager2-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e8aa56e022240cf99222941ccfe5aa09\transformed\play-services-maps-18.2.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8e2bddc43bca43f6feeeb0b3cf72beb3\transformed\foundation-layout-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c2afad1f3f32b4d57dc22060699a2f26\transformed\coordinatorlayout-1.1.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\c2afad1f3f32b4d57dc22060699a2f26\transformed\coordinatorlayout-1.1.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e4b6ddfa90b47436597e09bd9c817306\transformed\media3-ui-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e6bc9da41d0a6e28a400b151eefa6ceb\transformed\transition-1.2.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\e6bc9da41d0a6e28a400b151eefa6ceb\transformed\transition-1.2.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b58d341dc97f695c3eb3b5091cfc8646\transformed\material3-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\b58d341dc97f695c3eb3b5091cfc8646\transformed\material3-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\330d15851f573bcd7d71bb3d5a451c37\transformed\media3-common-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\83305b7178a50e17af8aa47733b05ea3\transformed\media-1.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\83305b7178a50e17af8aa47733b05ea3\transformed\media-1.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca09c5d5155d8f573eb7cc72311b3599\transformed\coordinatorlayout-1.1.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2216d4449c2b6e92538515b3979972ef\transformed\slidingpanelayout-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\2216d4449c2b6e92538515b3979972ef\transformed\slidingpanelayout-1.0.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9e7a241b835c49e7553137b066b5844c\transformed\work-runtime-2.7.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0cfd29e777d9d69f3e538762bec6a53a\transformed\media3-extractor-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0c37843324b1e7d5a9f2e2cc4dc677e6\transformed\slidingpanelayout-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e81ce79b51863828415009bb51016abe\transformed\swiperefreshlayout-1.0.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\16b2423e2c56a7c20183fd844b2c14f1\transformed\asynclayoutinflater-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\e81ce79b51863828415009bb51016abe\transformed\swiperefreshlayout-1.0.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\16b2423e2c56a7c20183fd844b2c14f1\transformed\asynclayoutinflater-1.0.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\92587205b8fd9b9262ff72026771dc09\transformed\swiperefreshlayout-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\29adcfff083bf56135c551b2e9e00195\transformed\ui-text-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f7bd5d7842107812dea66e8eced952cf\transformed\cloudinary-android-2.5.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\f7bd5d7842107812dea66e8eced952cf\transformed\cloudinary-android-2.5.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c6662b4c759d87fdfdef7660664df335\transformed\ui-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\c6662b4c759d87fdfdef7660664df335\transformed\ui-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\22667a54cef4ae062dd58c0fb94f3329\transformed\runtime-saveable-release-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\22667a54cef4ae062dd58c0fb94f3329\transformed\runtime-saveable-release-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0019cc3cd0f25acfbeac1d342305fa3c\transformed\runtime-release-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd64285809e6962c717aed31f867b500\transformed\cloudinary-android-2.5.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\0019cc3cd0f25acfbeac1d342305fa3c\transformed\runtime-release-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b4b08d7559193a74913f3f09c3b68013\transformed\asynclayoutinflater-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\391c154bf192be9901cde2d7454c053f\transformed\transition-1.2.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\93ccb039cd15a155e179a45a38c07eff\transformed\runtime-saveable-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-jdk7\1.9.22\c4b17283ad7939e778989663f8301d871987fa5e\kotlin-stdlib-jdk7-1.9.22.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4bb78280d8cf87be0a380aff6a009a2b\transformed\media-1.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a13c7908f34d2578032bf379d547127\transformed\annotation-experimental-1.4.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\3a13c7908f34d2578032bf379d547127\transformed\annotation-experimental-1.4.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-jdk7\1.9.22\c4b17283ad7939e778989663f8301d871987fa5e\kotlin-stdlib-jdk7-1.9.22.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.collection\collection-ktx\1.4.0\2ad14aed781c4a73ed4dbb421966d408a0a06686\collection-ktx-1.4.0.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.collection\collection-jvm\1.4.0\e209fb7bd1183032f55a0408121c6251a81acb49\collection-jvm-1.4.0.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b7b2ee478eb90ee4deb8805d2d909aa2\transformed\annotation-experimental-1.4.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.collection\collection-ktx\1.4.0\2ad14aed781c4a73ed4dbb421966d408a0a06686\collection-ktx-1.4.0.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.collection\collection-jvm\1.4.0\e209fb7bd1183032f55a0408121c6251a81acb49\collection-jvm-1.4.0.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4d8e6689b182b1b026cac010d807a3e8\transformed\room-runtime-2.2.5-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\4d8e6689b182b1b026cac010d807a3e8\transformed\room-runtime-2.2.5-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecb5844961361d4230c7ec035ae653ea\transformed\viewbinding-7.4.2-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecb5844961361d4230c7ec035ae653ea\transformed\viewbinding-7.4.2-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\3c1d9e0b385c8173ebc0e9c2688c0ac0\transformed\cardview-1.0.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\3c1d9e0b385c8173ebc0e9c2688c0ac0\transformed\cardview-1.0.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\528b76bfcb883f3ea668a9c6595aa187\transformed\sqlite-framework-2.1.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\528b76bfcb883f3ea668a9c6595aa187\transformed\sqlite-framework-2.1.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2d6941013c1f987ca686f8ac3200ba34\transformed\cardview-1.0.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d94f18028ba41a789cef2545a269620e\transformed\sqlite-2.1.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\d94f18028ba41a789cef2545a269620e\transformed\sqlite-2.1.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\96856ddd8e4364ae37ca0282cbc0fe88\transformed\viewbinding-7.4.2-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b4f14ea5186347d701e36c0590798a7b\transformed\sqlite-2.1.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.room\room-common\2.2.5\f5e3b73a0c2ab5e276e26868e4ce3542baede207\room-common-2.2.5.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.room\room-common\2.2.5\f5e3b73a0c2ab5e276e26868e4ce3542baede207\room-common-2.2.5.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\9fb4092a8905489114547bfabdae6c96\transformed\sqlite-framework-2.1.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.9.22\d6c44cd08d8f3f9bece8101216dbe6553365c6e3\kotlin-stdlib-1.9.22.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.9.22\d6c44cd08d8f3f9bece8101216dbe6553365c6e3\kotlin-stdlib-1.9.22.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e6cdbbdf4751a1685904a0fefa48ea6f\transformed\room-common-2.2.5 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.cloudinary\cloudinary-core\1.35.0\2bb1c3cc41f1485895d184b76b8c0eecaeb650ff\cloudinary-core-1.35.0.jar because:                                                                                                             
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.cloudinary\cloudinary-core\1.35.0\2bb1c3cc41f1485895d184b76b8c0eecaeb650ff\cloudinary-core-1.35.0.jar'                                                                                                                      
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\74d54195a832dee2b5c8736323044fef\transformed\constraintlayout-2.0.1-runtime because:
  Build cache is disabled                                                                                                                                        
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\f4846627772a26bf857be1b86e819579\transformed\fresco-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\f4846627772a26bf857be1b86e819579\transformed\fresco-2.6.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\66752a51ec6abc8901d4e2f7354416c5\transformed\drawee-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\66752a51ec6abc8901d4e2f7354416c5\transformed\drawee-2.6.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\e231fb94685145bff06c4e95eedda66e\transformed\nativeimagefilters-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\e231fb94685145bff06c4e95eedda66e\transformed\nativeimagefilters-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\dbe4b5335a486a882fceb5770188d30a\transformed\room-runtime-2.2.5-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\27a297224c068741ce5cfbb622aedbb1\transformed\memory-type-native-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\27a297224c068741ce5cfbb622aedbb1\transformed\memory-type-native-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\baf0a937d80a09014ea65f1764c3da05\transformed\fresco-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\327c705b6ae40975ed16bf6824f792d3\transformed\memory-type-java-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\327c705b6ae40975ed16bf6824f792d3\transformed\memory-type-java-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2e21f5912ecf8222b8236a0181f920d7\transformed\nativeimagefilters-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecf977641fbc524cd5b0fdbfa8e09e37\transformed\imagepipeline-native-2.6.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2cc1707a9f040f34e8d3b723c79b8eb8\transformed\memory-type-native-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\ecf977641fbc524cd5b0fdbfa8e09e37\transformed\imagepipeline-native-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\65aac874ac0b4483a4effa47fdb0d249\transformed\memory-type-java-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4d0039d59f03a4bfbb3681dc20c212ea\transformed\memory-type-ashmem-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\4d0039d59f03a4bfbb3681dc20c212ea\transformed\memory-type-ashmem-2.6.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\75fe866748b85c6488cbe232b9c6a228\transformed\imagepipeline-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\75fe866748b85c6488cbe232b9c6a228\transformed\imagepipeline-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\1193ef93d71cd69c189eb68ffbb458f8\transformed\memory-type-ashmem-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d6f8a91124d4a4bf3c09013c9d9211db\transformed\nativeimagetranscoder-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\d6f8a91124d4a4bf3c09013c9d9211db\transformed\nativeimagetranscoder-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\befa291a316b8719d303c85fc424f524\transformed\nativeimagetranscoder-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7af29e117d4358c4569656b5107109c9\transformed\imagepipeline-native-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd8abe1dab235ca73ee1db75403c3901\transformed\imagepipeline-base-2.6.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.infer.annotation\infer-annotation\0.18.0\27539793fe93ed7d92b6376281c16cda8278ab2f\infer-annotation-0.18.0.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd8abe1dab235ca73ee1db75403c3901\transformed\imagepipeline-base-2.6.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.infer.annotation\infer-annotation\0.18.0\27539793fe93ed7d92b6376281c16cda8278ab2f\infer-annotation-0.18.0.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\d2439c12d3c569850d661aaf335da4e8\transformed\infer-annotation-0.18.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\ddaa23d64de0c5a0735e9125469d3c8a\transformed\soloader-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\ddaa23d64de0c5a0735e9125469d3c8a\transformed\soloader-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\37bce89958824c8a895214790b9329a9\transformed\soloader-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a19f1b67433e18058be7712474d1e06\transformed\soloader-0.10.1-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a19f1b67433e18058be7712474d1e06\transformed\soloader-0.10.1-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\11a737fdb80c7fa16501c40f19be51f7\transformed\media3-exoplayer-1.1.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.soloader\nativeloader\0.10.1\ba1b31b4b9f65494a90de7c2728b57155344a858\nativeloader-0.10.1.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.soloader\nativeloader\0.10.1\ba1b31b4b9f65494a90de7c2728b57155344a858\nativeloader-0.10.1.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\18b0c33b554c7dcca674b80c3dbce652\transformed\imagepipeline-base-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd30453f205d276a5f7f98ecc596e271\transformed\drawee-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\393e7d969c213dd9567b5a98269407cd\transformed\nativeloader-0.10.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\73352cd9e52ff13ec55aa6c3c6551803\transformed\imagepipeline-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\c31252ba153f19698f436627702070f8\transformed\middleware-2.6.0-runtime.jar because:
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a51785ebcfc21b66eb93d858abf232ff\transformed\ui-common-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\c31252ba153f19698f436627702070f8\transformed\middleware-2.6.0-runtime.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\a51785ebcfc21b66eb93d858abf232ff\transformed\ui-common-2.6.0-runtime.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\48dc0c2998f42609a7dbed4c48d1de7e\transformed\fbcore-2.6.0-runtime.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\8.13\transforms\48dc0c2998f42609a7dbed4c48d1de7e\transformed\fbcore-2.6.0-runtime.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\09dcae9fdf964e5b735f2e56d2a0de90\transformed\middleware-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\4cc8cc28f358770fdbaef293305e18c3\transformed\ui-common-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.constraintlayout\constraintlayout-solver\2.0.1\30988fe2d77f3fe3bf7551bb8a8b795fad7e7226\constraintlayout-solver-2.0.1.jar because:                                                                                    
  Build cache is disabled
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.soloader\annotation\0.10.1\dc58463712cb3e5f03d8ee5ac9743b9ced9afa77\annotation-0.10.1.jar because:
  Build cache is disabled                                                                                                                                        
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\androidx.constraintlayout\constraintlayout-solver\2.0.1\30988fe2d77f3fe3bf7551bb8a8b795fad7e7226\constraintlayout-solver-2.0.1.jar'
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.facebook.soloader\annotation\0.10.1\dc58463712cb3e5f03d8ee5ac9743b9ced9afa77\annotation-0.10.1.jar'
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.parse.bolts\bolts-tasks\1.4.0\d85884acf6810a3bbbecb587f239005cbc846dc4\bolts-tasks-1.4.0.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\com.parse.bolts\bolts-tasks\1.4.0\d85884acf6810a3bbbecb587f239005cbc846dc4\bolts-tasks-1.4.0.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\6e749c646b9e4191bf5ca38c88840a47\transformed\annotation-0.10.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingNoClasspathTransform: C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-annotations-jvm\1.3.72\7dba6c57de526588d8080317bda0c14cd88c8055\kotlin-annotations-jvm-1.3.72.jar because:
  Build cache is disabled
Running dexing transform non-incrementally for 'C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-annotations-jvm\1.3.72\7dba6c57de526588d8080317bda0c14cd88c8055\kotlin-annotations-jvm-1.3.72.jar'
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1053ba1983c3b19ac5fb46a0bf182f8\transformed\bolts-tasks-1.4.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\21a62771f287b12878838fc66a35bdfe\transformed\kotlin-annotations-jvm-1.3.72 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\8578f511c034838f89c34e3bbf845721\transformed\soloader-0.10.1-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\eb7c4c3e06658b16dee6e2d5bb74d131\transformed\fbcore-2.6.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\181a6a7abd7ff6830a762461cfff4288\transformed\recyclerview-1.3.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\2063433a50987588f058bde0a07eee41\transformed\foundation-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fba7a45a337b7f946c2916c9a88eed08\transformed\cloudinary-core-1.35.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\b8b17bd3ba2f8d880e3dd6a96453ba34\transformed\constraintlayout-solver-2.0.1 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\af28101cb50f826944875bd5165b5099\transformed\material-1.4.0-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\fb99309387b27495f9b959a2ee197493\transformed\collection-jvm-1.4.0 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\0f3a86a4208ac8ae6f288189a3deaefc\transformed\ui-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\bc36a07cd766d0ef3a70e7113ba038c8\transformed\runtime-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\1e0ccf6b1741ce8ca122ac6762249d02\transformed\kotlin-stdlib-1.9.22 because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\43619a091a83faa1dda7a68a86e3cf88\transformed\material3-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for DexingOutputSplitTransform: C:\Users\Ahmed\.gradle\caches\8.13\transforms\bd0d34e15e92b9319f658ada243a0099\transformed\material-icons-extended-release-runtime because:
  Build cache is disabled
  Caching not enabled.
Caching disabled for task ':app:mergeExtDexRelease' because:
  Build cache is disabled
Task ':app:mergeExtDexRelease' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_libs_dex\release\mergeExtDexRelease has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_libs_dex\release\mergeExtDexRelease\classes.dex has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_libs_dex\release\mergeExtDexRelease\classes2.dex has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeExtDexRelease'.

Resolve mutations for :app:compileReleaseKotlin (Thread[Execution worker Thread 4,5,main]) started.
:app:compileReleaseKotlin (Thread[Execution worker Thread 4,5,main]) started.
Resolve mutations for :app:extractReleaseNativeSymbolTables (Thread[Execution worker Thread 2,5,main]) started.
:app:extractReleaseNativeSymbolTables (Thread[Execution worker Thread 2,5,main]) started.
Resolve mutations for :app:mergeReleaseShaders (Thread[Execution worker Thread 7,5,main]) started.
:app:mergeReleaseShaders (Thread[Execution worker Thread 7,5,main]) started.

> Task :app:mergeReleaseShaders
Caching disabled for task ':app:mergeReleaseShaders' because:
  Build cache is disabled
Task ':app:mergeReleaseShaders' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_shaders\release\out has been removed.  
The input changes require a full rebuild for incremental task ':app:mergeReleaseShaders'.
Resolve mutations for :app:compileReleaseShaders (Thread[Execution worker Thread 7,5,main]) started.
:app:compileReleaseShaders (Thread[Execution worker Thread 7,5,main]) started.

> Task :app:compileReleaseShaders NO-SOURCE                                                                                                                      
Skipping task ':app:compileReleaseShaders' as it has no source files and no previous output files.
Resolve mutations for :app:generateReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.
:app:generateReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.

> Task :app:generateReleaseAssets UP-TO-DATE                                                                                                                     
Skipping task ':app:generateReleaseAssets' as it has no actions.
Resolve mutations for :app:mergeReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.
:app:mergeReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.

> Task :app:extractReleaseNativeSymbolTables
Caching disabled for task ':app:extractReleaseNativeSymbolTables' because:
  Build cache is disabled
Task ':app:extractReleaseNativeSymbolTables' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\native_symbol_tables\release\out has been removed.
C/C++: android.ndkVersion from module build.gradle is [not set]
C/C++: android.ndkPath from module build.gradle is not set
C/C++: ndk.dir in local.properties is not set
C/C++: Not considering ANDROID_NDK_HOME because support was removed after deprecation period.
C/C++: sdkFolder is C:\Users\Ahmed\AppData\Local\Android\Sdk
C/C++: NDK side-by-side folder from sdkFolder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk does not exist
C/C++: Because no explicit NDK was requested, the default version [25.1.8937393] for this Android Gradle Plugin will be used
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk\25.1.8937393 does not exist. Ignoring.
C/C++: Folder C:\Users\Ahmed\AppData\Local\Android\Sdk\ndk-bundle does not exist. Ignoring.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libbarhopper_v3.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libimagepipeline.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libimage_processing_util_jni.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libnative-filters.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\arm64-v8a\libnative-imagetranscoder.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libbarhopper_v3.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libimagepipeline.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libimage_processing_util_jni.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libnative-filters.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\armeabi-v7a\libnative-imagetranscoder.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libbarhopper_v3.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libimagepipeline.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libimage_processing_util_jni.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libnative-filters.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86\libnative-imagetranscoder.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libbarhopper_v3.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libimagepipeline.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libimage_processing_util_jni.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libnative-filters.so because the native debug metadata has already been stripped.
Unable to extract native debug metadata from Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\merged_native_libs\release\out\lib\x86_64\libnative-imagetranscoder.so because the native debug metadata has already been stripped.

> Task :app:mergeReleaseAssets
Caching disabled for task ':app:mergeReleaseAssets' because:
  Build cache is disabled
Task ':app:mergeReleaseAssets' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\release has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\release\mlkit_barcode_models has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\assets\release\mlkit_barcode_models\barcode_ssd_mobilenet_v1_dmp25_quant.tflite has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:mergeReleaseAssets'.
Resolve mutations for :app:mergeReleaseNativeDebugMetadata (Thread[Execution worker Thread 7,5,main]) started.
:app:mergeReleaseNativeDebugMetadata (Thread[Execution worker Thread 7,5,main]) started.

> Task :app:mergeReleaseNativeDebugMetadata NO-SOURCE
Skipping task ':app:mergeReleaseNativeDebugMetadata' as it has no source files and no previous output files.
Resolve mutations for :app:compressReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.
:app:compressReleaseAssets (Thread[Execution worker Thread 7,5,main]) started.
Resolve mutations for :app:optimizeReleaseResources (Thread[Execution worker Thread 2,5,main]) started.
:app:optimizeReleaseResources (Thread[Execution worker Thread 2,5,main]) started.
Resolve mutations for :app:collectReleaseDependencies (Thread[Execution worker Thread 15,5,main]) started.
:app:collectReleaseDependencies (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:compressReleaseAssets
Caching disabled for task ':app:compressReleaseAssets' because:
  Build cache is disabled
Task ':app:compressReleaseAssets' is not up-to-date because:
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\release\out has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\release\out\assets has been removed.
  Output property 'outputDir' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compressed_assets\release\out\assets\mlkit_barcode_models has been removed.
  and more...
The input changes require a full rebuild for incremental task ':app:compressReleaseAssets'.

> Task :app:mergeExtDexRelease
Merging to 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\external_libs_dex\release\mergeExtDexRelease' with D8 from all or a subset of dex files in C:\Users\Ahmed\.gradle\caches\8.13\transforms\e671a134cf59723854d9e44b2080f2dc\transformed\navigation-common-2.7.3-runtime\navigation-common-2.7.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c8ef671180031d73de820430d933d6ca\transformed\navigation-runtime-2.7.3-runtime\navigation-runtime-2.7.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d3b8b4f85efbea62eade7765a9988af2\transformed\navigation-compose-2.7.3-runtime\navigation-comp
ose-2.7.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9deee55a502bec2aabb1da37c64825ad\transformed\accompanist-permissions-0.32.0-runtime\accompan
ist-permissions-0.32.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f0e6a7affa27d62e20edd50aa191e20b\transformed\maps-compose-4.3.3-runtime\maps-co
mpose-4.3.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\43619a091a83faa1dda7a68a86e3cf88\transformed\material3-release-runtime\material3-release-r
untime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd64285809e6962c717aed31f867b500\transformed\cloudinary-android-2.5.0-runtime\cloudinary-android-2.5.0-
runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6f56f2678c2b13737784451ebb3a15b9\transformed\camera-video-1.3.0-runtime\camera-video-1.3.0-runtime_dex
, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1ed04b0b1e53ce045a9b5987d3ee9098\transformed\camera-lifecycle-1.3.0-runtime\camera-lifecycle-1.3.0-runtime_dex, C
:\Users\Ahmed\.gradle\caches\8.13\transforms\c8f55244ddac409dce7d3f2a716c9a59\transformed\camera-camera2-1.3.0-runtime\camera-camera2-1.3.0-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\f5c11da3d5fcb6f97dffb1c5789d2512\transformed\camera-core-1.3.0-runtime\camera-core-1.3.0-runtime_dex, C:\Users\Ahmed\.gradl
e\caches\8.13\transforms\8622586723fc7ff7b036a2cfdad879a3\transformed\camera-view-1.3.0-runtime\camera-view-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13
\transforms\41786be34d3d5513bbdda2f50b1b7ee4\transformed\cloudinary-android-ui-2.5.0-runtime\cloudinary-android-ui-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\cach
es\8.13\transforms\53420302d26fe51d1cbc106c5747f81d\transformed\cloudinary-android-preprocess-2.5.0-runtime\cloudinary-android-preprocess-2.5.0-runtime_dex, C:\U
sers\Ahmed\.gradle\caches\8.13\transforms\f40a31ada8c6549f4b5b7de3c92bbcf2\transformed\cloudinary-android-download-2.5.0-runtime\cloudinary-android-download-2.5.
0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\05c9443c6ec61cce85f0bb98afa66e46\transformed\cloudinary-android-core-2.5.0-runtime\cloudinary-androi
d-core-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\af28101cb50f826944875bd5165b5099\transformed\material-1.4.0-runtime\material-1.4.0-runtim
e_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\815ccd681af3eb25cde717bf410d97fa\transformed\coil-compose-2.5.0-runtime\coil-compose-2.5.0-runtime_dex, C:\U
sers\Ahmed\.gradle\caches\8.13\transforms\45945da8d8d2b99cac338291b3d5bad1\transformed\coil-compose-base-2.5.0-runtime\coil-compose-base-2.5.0-runtime_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\ba27bcdc3f8e8452cd245a23001ecf35\transformed\coil-2.5.0-runtime\coil-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.
13\transforms\6e49b1e07118f112889c916799d93039\transformed\coil-base-2.5.0-runtime\coil-base-2.5.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d6b
f3851978ec14543beb7a138702bc5\transformed\appcompat-resources-1.6.1-runtime\appcompat-resources-1.6.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\
74d54195a832dee2b5c8736323044fef\transformed\constraintlayout-2.0.1-runtime\constraintlayout-2.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7a8
e31424e1741d72087391ca02efaaf\transformed\appcompat-1.6.1-runtime\appcompat-1.6.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\cac73b504f4418df7dcf
b71ec61f82ef\transformed\maps-ktx-5.0.0-runtime\maps-ktx-5.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e8aa56e022240cf99222941ccfe5aa09\transf
ormed\play-services-maps-18.2.0-runtime\play-services-maps-18.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c3cafd0edd783f69909a8388ef574c7f\tra
nsformed\firebase-auth-ktx-22.1.1-runtime\firebase-auth-ktx-22.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\daed0afadc640a416c2a8b9e3cb8617c\tr
ansformed\firebase-auth-22.1.1-runtime\firebase-auth-22.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3d65b378bd6af06aad5d7ad825e70293\transform
ed\firebase-firestore-ktx-24.7.1-runtime\firebase-firestore-ktx-24.7.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ab9cd84affca9c36c4f5b25c70e0fcf
d\transformed\barcode-scanning-17.2.0-runtime\barcode-scanning-17.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e33ce2f2bd9d0d19f505c28f43bf77bb
\transformed\play-services-location-21.0.1-runtime\play-services-location-21.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b837197f772d8af3aa611
c799912e553\transformed\firebase-firestore-24.7.1-runtime\firebase-firestore-24.7.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6f7ab5fadead92b4f6
a5529307405c19\transformed\play-services-mlkit-barcode-scanning-18.3.0-runtime\play-services-mlkit-barcode-scanning-18.3.0-runtime_dex, C:\Users\Ahmed\.gradle\ca
ches\8.13\transforms\0b194cf4b7f288b4e0cf5d27d736a04d\transformed\barcode-scanning-common-17.0.0-runtime\barcode-scanning-common-17.0.0-runtime_dex, C:\Users\Ahm
ed\.gradle\caches\8.13\transforms\5e88c7cef751ddb734d4a0c28289de4f\transformed\vision-common-17.3.0-runtime\vision-common-17.3.0-runtime_dex, C:\Users\Ahmed\.gra
dle\caches\8.13\transforms\5366c6b80596bba527c61624cd9d6f3d\transformed\common-18.9.0-runtime\common-18.9.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\trans
forms\f6e22f261f5c9c79f0697fc1926673a9\transformed\firebase-storage-ktx-20.2.1-runtime\firebase-storage-ktx-20.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.1
3\transforms\9e7a241b835c49e7553137b066b5844c\transformed\work-runtime-2.7.1-runtime\work-runtime-2.7.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transform
s\46b39c13e54d8cb621fad5db1bd23e6e\transformed\material-icons-core-release-runtime\material-icons-core-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tr
ansforms\bd0d34e15e92b9319f658ada243a0099\transformed\material-icons-extended-release-runtime\material-icons-extended-release-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\734cd36a0c834f353b7e8f71cdeb27ae\transformed\material-ripple-release-runtime\material-ripple-release-runtime_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\fdfa3c8c021e38464cdff296f812fd14\transformed\animation-core-release-runtime\animation-core-release-runtime_dex, C:\Users\Ahmed\.gradle\cac
hes\8.13\transforms\701892e69bdaba122d9152e160f3f426\transformed\animation-release-runtime\animation-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tran
sforms\8e2bddc43bca43f6feeeb0b3cf72beb3\transformed\foundation-layout-release-runtime\foundation-layout-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\t
ransforms\2063433a50987588f058bde0a07eee41\transformed\foundation-release-runtime\foundation-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f
539ee854fc70b833bc0bc641e991333\transformed\ui-unit-release-runtime\ui-unit-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\694a99823995b94c48
c050eead7ad000\transformed\ui-geometry-release-runtime\ui-geometry-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1c9b0e9444176aeee21beb12389
6b30e\transformed\ui-util-release-runtime\ui-util-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\01325f91b3c1f8d58dfd6d37845197f5\transformed
\ui-graphics-release-runtime\ui-graphics-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ae24950507bdd15c6673b93e591bee5\transformed\ui-tooli
ng-preview-release-runtime\ui-tooling-preview-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\29adcfff083bf56135c551b2e9e00195\transformed\ui-
text-release-runtime\ui-text-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9af0046693a0658ac5e3ccb68856c7aa\transformed\emoji2-views-helper-
1.3.0-runtime\emoji2-views-helper-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5f7f8990e677a9546387ce81f55b2c70\transformed\emoji2-1.3.0-runt
ime\emoji2-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ba7cf26f6a0669ca3dca905e61fb9860\transformed\lifecycle-process-2.6.2-runtime\lifecycl
e-process-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b96030237b1b671504089bc376613549\transformed\lifecycle-service-2.6.2-runtime\lifecycle
-service-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b47cee2ba5e6da766ed6444a5daee9fd\transformed\lifecycle-livedata-core-2.6.2-runtime\life
cycle-livedata-core-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1651103b2fea0dc30e1ba572f42257ae\transformed\lifecycle-viewmodel-ktx-2.6.2-r
untime\lifecycle-viewmodel-ktx-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\40e5f2458a513c7c7859b02ba949ba1b\transformed\firebase-analytics-k
tx-21.3.0-runtime\firebase-analytics-ktx-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1a04f18fb909a0a9bfa7ebe2d366804b\transformed\play-serv
ices-measurement-21.3.0-runtime\play-services-measurement-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\046db4ac7596160b6690ef7ce4137949\tran
sformed\play-services-measurement-sdk-21.3.0-runtime\play-services-measurement-sdk-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\3f2c31aa0a0e
117597a820ae18dd7f27\transformed\play-services-measurement-impl-21.3.0-runtime\play-services-measurement-impl-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8
.13\transforms\35fabc289bdc118eb201058bc51caaf4\transformed\play-services-stats-17.0.2-runtime\play-services-stats-17.0.2-runtime_dex, C:\Users\Ahmed\.gradle\cac
hes\8.13\transforms\d7eade9c023d9bfa044454ef8f3af3d7\transformed\legacy-support-core-ui-1.0.0-runtime\legacy-support-core-ui-1.0.0-runtime_dex, C:\Users\Ahmed\.g
radle\caches\8.13\transforms\f157715ea797c3022da311d11957d76b\transformed\dynamicanimation-1.0.0-runtime\dynamicanimation-1.0.0-runtime_dex, C:\Users\Ahmed\.grad
le\caches\8.13\transforms\5e4038ad57b29387d1b828fe7d722b06\transformed\legacy-support-core-utils-1.0.0-runtime\legacy-support-core-utils-1.0.0-runtime_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\23b76fd72455867ca069068e8f480fb9\transformed\loader-1.0.0-runtime\loader-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\cache
s\8.13\transforms\5ea668420991b50aaa5d0f3d8122aff9\transformed\lifecycle-livedata-2.6.2-runtime\lifecycle-livedata-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\cach
es\8.13\transforms\bfb308c5d2f167a1d823b5282f907834\transformed\savedstate-ktx-1.2.1-runtime\savedstate-ktx-1.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13
\transforms\b4b50fe8418c4a03dc164e91e137533b\transformed\savedstate-1.2.1-runtime\savedstate-1.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bc1
4f0495d28bb1964b93e6625e4f59f\transformed\lifecycle-common-2.6.2\lifecycle-common-2.6.2_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e86ed7b1860528cc6d50cb
c3e5dd3524\transformed\lifecycle-viewmodel-savedstate-2.6.2-runtime\lifecycle-viewmodel-savedstate-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\3679e132e53fb699b01dec210268eea0\transformed\litr-1.4.16-runtime\litr-1.4.16-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\845e4280577ab67df59904
08811bd7dc\transformed\media3-exoplayer-dash-1.1.1-runtime\media3-exoplayer-dash-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1888465fa6d82e2
4b8a7870bb8f8678c\transformed\media3-exoplayer-hls-1.1.1-runtime\media3-exoplayer-hls-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\11a737fdb8
0c7fa16501c40f19be51f7\transformed\media3-exoplayer-1.1.1-runtime\media3-exoplayer-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\0cfd29e777d9d
69f3e538762bec6a53a\transformed\media3-extractor-1.1.1-runtime\media3-extractor-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\de42e3efd8b0d34c
7610ce3b82e15a4e\transformed\media3-container-1.1.1-runtime\media3-container-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ede5a8c5508292d2a41
20b5803d25749\transformed\media3-datasource-1.1.1-runtime\media3-datasource-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a62649df952eb9c006bb
ecd75c7b2aa7\transformed\media3-decoder-1.1.1-runtime\media3-decoder-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e83f79ec6f238352501542f4d93
f04ad\transformed\media3-database-1.1.1-runtime\media3-database-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\330d15851f573bcd7d71bb3d5a451c37
\transformed\media3-common-1.1.1-runtime\media3-common-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e4b6ddfa90b47436597e09bd9c817306\transfor
med\media3-ui-1.1.1-runtime\media3-ui-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8a83f3fc4158159c28e4f91309311dba\transformed\viewpager2-1.
0.0-runtime\viewpager2-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\181a6a7abd7ff6830a762461cfff4288\transformed\recyclerview-1.3.0-runtime\r
ecyclerview-1.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1e3368a10e48a4a35a5e43e2167e210\transformed\customview-poolingcontainer-1.0.0-runti
me\customview-poolingcontainer-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d6775e7a777fb3d7b55cf5525a71b345\transformed\core-ktx-1.12.0-runt
ime\core-ktx-1.12.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1bff31406ae64dea54ef3aec1f89b7ca\transformed\firebase-storage-20.2.1-runtime\fireb
ase-storage-20.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a5caff3b8392edc11603b9ec51b34dda\transformed\play-services-auth-api-phone-17.4.0-ru
ntime\play-services-auth-api-phone-17.4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\dba62ba269bd5a683c270d18c69fdde7\transformed\firebase-appche
ck-interop-17.0.0-runtime\firebase-appcheck-interop-17.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\899ad5b867913aeed484e5d09fce519f\transforme
d\firebase-database-collection-18.0.1-runtime\firebase-database-collection-18.0.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\4cf4d007ffeec1f19996
cf8fdc924bb4\transformed\play-services-base-18.1.0-runtime\play-services-base-18.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\90efdf009584c5e73
273e9a11513ded0\transformed\browser-1.4.0-runtime\browser-1.4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\16ba8e837410d6c8640048b20a631240\trans
formed\autofill-1.0.0-runtime\autofill-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca09c5d5155d8f573eb7cc72311b3599\transformed\coordinatorl
ayout-1.1.0-runtime\coordinatorlayout-1.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\391c154bf192be9901cde2d7454c053f\transformed\transition-1.
2.0-runtime\transition-1.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1527a4f9798a718f0ad673dc8208c04b\transformed\vectordrawable-animated-1.1.
0-runtime\vectordrawable-animated-1.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e839980bedc19f0d4bea230e270ca58e\transformed\vectordrawable-1.
1.0-runtime\vectordrawable-1.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\4bb78280d8cf87be0a380aff6a009a2b\transformed\media-1.6.0-runtime\medi
a-1.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9297ca9d8af377476295a92b199828a8\transformed\viewpager-1.0.0-runtime\viewpager-1.0.0-runtime_d
ex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2f3872265673bd3f7bbd58f87058a78b\transformed\drawerlayout-1.0.0-runtime\drawerlayout-1.0.0-runtime_dex, C:\User
s\Ahmed\.gradle\caches\8.13\transforms\0c37843324b1e7d5a9f2e2cc4dc677e6\transformed\slidingpanelayout-1.0.0-runtime\slidingpanelayout-1.0.0-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\8f89e365e2830ad8ba6ce0c01545c984\transformed\customview-1.0.0-runtime\customview-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\92587205b8fd9b9262ff72026771dc09\transformed\swiperefreshlayout-1.0.0-runtime\swiperefreshlayout-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle
\caches\8.13\transforms\b4b08d7559193a74913f3f09c3b68013\transformed\asynclayoutinflater-1.0.0-runtime\asynclayoutinflater-1.0.0-runtime_dex, C:\Users\Ahmed\.gra
dle\caches\8.13\transforms\da5e6a07bf50d29c0fc6479802f309c0\transformed\core-1.12.0-runtime\core-1.12.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transform
s\f297f7f8f7d47d3c9c899c7ffcbc9c34\transformed\lifecycle-runtime-2.6.2-runtime\lifecycle-runtime-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms
\ac0f7ba5cc1f9d94547ef65ce55806e0\transformed\lifecycle-viewmodel-2.6.2-runtime\lifecycle-viewmodel-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfo
rms\918ab49363b86c3821a7446a5d386b2c\transformed\lifecycle-viewmodel-compose-2.6.2-runtime\lifecycle-viewmodel-compose-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\ea996cbaccbf980658cc26aeb8592dfe\transformed\accompanist-drawablepainter-0.32.0-runtime\accompanist-drawablepainter-0.32.0-runtime_dex, C:
\Users\Ahmed\.gradle\caches\8.13\transforms\0f3a86a4208ac8ae6f288189a3deaefc\transformed\ui-release-runtime\ui-release-runtime_dex, C:\Users\Ahmed\.gradle\caches
\8.13\transforms\93ccb039cd15a155e179a45a38c07eff\transformed\runtime-saveable-release-runtime\runtime-saveable-release-runtime_dex, C:\Users\Ahmed\.gradle\cache
s\8.13\transforms\bc36a07cd766d0ef3a70e7113ba038c8\transformed\runtime-release-runtime\runtime-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms
\8a594c0f2449621167f4bf69db8cad0b\transformed\kotlinx-coroutines-core-jvm-1.7.3\kotlinx-coroutines-core-jvm-1.7.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfo
rms\c157b31d9da02a0d977079ba569165b3\transformed\firebase-common-ktx-20.3.1-runtime\firebase-common-ktx-20.3.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tr
ansforms\c28b36c072fce1d8dc7f0d5bd89c50a9\transformed\recaptcha-18.1.2-runtime\recaptcha-18.1.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f36435
980e3ee1b1f5300dc8542b19d3\transformed\kotlinx-coroutines-play-services-1.7.3\kotlinx-coroutines-play-services-1.7.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\tran
sforms\2d44dae10621974e976a0a88342704d5\transformed\play-services-measurement-api-21.3.0-runtime\play-services-measurement-api-21.3.0-runtime_dex, C:\Users\Ahmed
\.gradle\caches\8.13\transforms\30f79f61ff8cde9a415f83dfe0f1fd48\transformed\firebase-auth-interop-20.0.0-runtime\firebase-auth-interop-20.0.0-runtime_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\f0538229deb749a8afb1c7486a47fd3b\transformed\firebase-installations-17.0.1-runtime\firebase-installations-17.0.1-runtime
_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\806630a23e25567bcd62a433f263204a\transformed\firebase-common-20.3.2-runtime\firebase-common-20.3.2-runtime_de
x, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7c61d3d3cda31037252d3c566cac72e8\transformed\integrity-1.1.0-runtime\integrity-1.1.0-runtime_dex, C:\Users\Ahmed
\.gradle\caches\8.13\transforms\a9a3b601d739982c41d4ded24c8bfa0b\transformed\vision-interfaces-16.2.0-runtime\vision-interfaces-16.2.0-runtime_dex, C:\Users\Ahme
d\.gradle\caches\8.13\transforms\b9bac6caa8c91b769a06cb204c585539\transformed\firebase-installations-interop-17.0.1-runtime\firebase-installations-interop-17.0.1
-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\82a4fdcb6855ad99fcf04ac1fa03d667\transformed\play-services-tasks-18.0.2-runtime\play-services-tasks-1
8.0.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\7d967f9b789fc38422915e19de785d5e\transformed\play-services-ads-identifier-18.0.0-runtime\play-se
rvices-ads-identifier-18.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\68528952d563e29675006c72441bc5af\transformed\play-services-measurement-sd
k-api-21.3.0-runtime\play-services-measurement-sdk-api-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d47dbc28b889437c02ce592235a6709b\transfo
rmed\play-services-measurement-base-21.3.0-runtime\play-services-measurement-base-21.3.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\f43b77bc5d7b2
bf1f186738e0d8e98fa\transformed\firebase-measurement-connector-19.0.0-runtime\firebase-measurement-connector-19.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.
13\transforms\02235e22674f01e3316f90df78e58c9a\transformed\play-services-basement-18.1.0-runtime\play-services-basement-18.1.0-runtime_dex, C:\Users\Ahmed\.gradl
e\caches\8.13\transforms\cb9b0b3206fb8b9ec18ab623a40843fe\transformed\fragment-1.3.6-runtime\fragment-1.3.6-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\trans
forms\da44083994e106f2ff82f49bebb94c91\transformed\activity-1.7.2-runtime\activity-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\bb884d5e5c21c
20e354a4960d8064195\transformed\activity-compose-1.7.2-runtime\activity-compose-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9de6c4a00d9f617f
83d414a75a51d9ff\transformed\activity-ktx-1.7.2-runtime\activity-ktx-1.7.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\93da51cbcc432012f6e20ed5579
748e1\transformed\lifecycle-runtime-ktx-2.6.2-runtime\lifecycle-runtime-ktx-2.6.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\91056c343d3c819bc01f
90634feaefb2\transformed\kotlinx-coroutines-android-1.7.3\kotlinx-coroutines-android-1.7.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d2b99ec2e0fdf9621c3
109b2edee7ed2\transformed\napier-release-runtime\napier-release-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b7b2ee478eb90ee4deb8805d2d909aa2\trans
formed\annotation-experimental-1.4.0-runtime\annotation-experimental-1.4.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c1e1d78220ab7d96ca29e1d46da
d0586\transformed\grpc-okhttp-1.52.1\grpc-okhttp-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\edeaf0a933d8729fca00160b48008bdc\transformed\okhttp-4.
12.0\okhttp-4.12.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5f103fa3589bc25941a2973fcdc0360a\transformed\okio-jvm-3.6.0\okio-jvm-3.6.0_dex, C:\Users\Ah
med\.gradle\caches\8.13\transforms\c1eab827feef7bb23462c7017952c0bc\transformed\versionedparcelable-1.1.1-runtime\versionedparcelable-1.1.1-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\fb99309387b27495f9b959a2ee197493\transformed\collection-jvm-1.4.0\collection-jvm-1.4.0_dex, C:\Users\Ahmed\.gradle\caches\8
.13\transforms\f8de90c297ade7feab407580280b91a2\transformed\firebase-components-17.1.0-runtime\firebase-components-17.1.0-runtime_dex, C:\Users\Ahmed\.gradle\cac
hes\8.13\transforms\1b1c09eff63094fb60c33ff4e9e8b58e\transformed\interpolator-1.0.0-runtime\interpolator-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\tr
ansforms\dbe4b5335a486a882fceb5770188d30a\transformed\room-runtime-2.2.5-runtime\room-runtime-2.2.5-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c2
94b70b80897f98d2acc3698a282fd4\transformed\core-runtime-2.2.0-runtime\core-runtime-2.2.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\354f5fd91385c
bc51e16c02887ba7768\transformed\core-common-2.2.0\core-common-2.2.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a958b260e8a52aeb4957e389446f5dc7\transform
ed\localbroadcastmanager-1.0.0-runtime\localbroadcastmanager-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\69f70246b467bdd53e1b7de363462299\tr
ansformed\transport-backend-cct-2.3.3-runtime\transport-backend-cct-2.3.3-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5e0db42f5cdbb3e6b062f3851024
ea84\transformed\transport-runtime-2.2.6-runtime\transport-runtime-2.2.6-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\16d1b708d1c3b4a8c8c7625a27c0b
ffd\transformed\transport-api-2.2.1-runtime\transport-api-2.2.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\777e675b4f7674a9593fd7f9b3ae6d3c\trans
formed\firebase-encoders-json-17.1.0-runtime\firebase-encoders-json-17.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\5fd4f54b319869a9cc1b1146304
951e4\transformed\firebase-encoders-16.1.0\firebase-encoders-16.1.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\96856ddd8e4364ae37ca0282cbc0fe88\transform
ed\viewbinding-7.4.2-runtime\viewbinding-7.4.2-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\c15e77c87cf703ddcfce3e644179f65a\transformed\profileins
taller-1.3.1-runtime\profileinstaller-1.3.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9ffbc00e3a159707fe581fcc12067e3d\transformed\startup-runti
me-1.1.1-runtime\startup-runtime-1.1.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9fc4414f3f075ad3179409ad22d623e8\transformed\tracing-1.0.0-runt
ime\tracing-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\61e4a7809733a514d1a250cbbf863614\transformed\concurrent-futures-1.1.0\concurrent-fut
ures-1.1.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b9446e753565aa640733523f3c8f5a10\transformed\documentfile-1.0.0-runtime\documentfile-1.0.0-runtime_
dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\14d79ae97eb641b74e565c7d102c211f\transformed\print-1.0.0-runtime\print-1.0.0-runtime_dex, C:\Users\Ahmed\.grad
le\caches\8.13\transforms\a1a7353740fd41412ae2bbafa145f8d8\transformed\exifinterface-1.3.6-runtime\exifinterface-1.3.6-runtime_dex, C:\Users\Ahmed\.gradle\caches
\8.13\transforms\2d6941013c1f987ca686f8ac3200ba34\transformed\cardview-1.0.0-runtime\cardview-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\9f
b4092a8905489114547bfabdae6c96\transformed\sqlite-framework-2.1.0-runtime\sqlite-framework-2.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\b4f14
ea5186347d701e36c0590798a7b\transformed\sqlite-2.1.0-runtime\sqlite-2.1.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\525113522a290a55b9952dc687b9
1042\transformed\cursoradapter-1.0.0-runtime\cursoradapter-1.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\e6cdbbdf4751a1685904a0fefa48ea6f\tran
sformed\room-common-2.2.5\room-common-2.2.5_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\92058b1f230feede162e3b83a9986afb\transformed\resourceinspection-an
notation-1.0.1\resourceinspection-annotation-1.0.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ec89ec52d83308fa6c7fa9643aab2b3b\transformed\annotation-jvm
-1.7.0\annotation-jvm-1.7.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1e0ccf6b1741ce8ca122ac6762249d02\transformed\kotlin-stdlib-1.9.22\kotlin-stdlib-1.
9.22_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\2b9d77a4dc1a209134a0412f1993ff03\transformed\core-3.5.3\core-3.5.3_dex, C:\Users\Ahmed\.gradle\caches\8.1
3\transforms\85a89f23c84452d8889b9bdb8f6e682e\transformed\auto-value-annotations-1.6.3\auto-value-annotations-1.6.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\trans
forms\4bca7803335216570097ab73f4a54d63\transformed\grpc-stub-1.52.1\grpc-stub-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\82ce959ffb586ea36fbfe12db
32fb2fb\transformed\grpc-protobuf-lite-1.52.1\grpc-protobuf-lite-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\4068286945ad20bdee1592aa00419db1\trans
formed\grpc-android-1.52.1-runtime\grpc-android-1.52.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a582c5be24b895aa938f34a81781bc10\transformed\gr
pc-core-1.52.1\grpc-core-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\4871b2b1d202883c944623654b87c148\transformed\grpc-api-1.52.1\grpc-api-1.52.1_d
ex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\810b07816e2c24d8a53012da9740cdbe\transformed\guava-31.1-android\guava-31.1-android_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\f8b820f5805fe62263586d24e5d692c8\transformed\annotations-23.0.0\annotations-23.0.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\ca1a
d5cbe4567d769c58d1f10103c062\transformed\firebase-annotations-16.2.0\firebase-annotations-16.2.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\918116f863659
be54f82f90e6a4fe112\transformed\error_prone_annotations-2.14.0\error_prone_annotations-2.14.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\63a8e914734ab283
4507efe9b4001691\transformed\protolite-well-known-types-18.0.0-runtime\protolite-well-known-types-18.0.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\4cbfa3a93589d23aa3dbbc402e3257df\transformed\image-1.0.0-beta1-runtime\image-1.0.0-beta1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fba7a45a33
7b7f946c2916c9a88eed08\transformed\cloudinary-core-1.35.0\cloudinary-core-1.35.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\baf0a937d80a09014ea65f1764c3d
a05\transformed\fresco-2.6.0-runtime\fresco-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\d9e52b5eb08fe5e5fb641a72055f8efe\transformed\javax.i
nject-1\javax.inject-1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\798678bd6bd38af52aca2ecfa146c7cf\transformed\protobuf-javalite-3.25.0\protobuf-javalite
-3.25.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\fd30453f205d276a5f7f98ecc596e271\transformed\drawee-2.6.0-runtime\drawee-2.6.0-runtime_dex, C:\Users\A
hmed\.gradle\caches\8.13\transforms\2e21f5912ecf8222b8236a0181f920d7\transformed\nativeimagefilters-2.6.0-runtime\nativeimagefilters-2.6.0-runtime_dex, C:\Users\
Ahmed\.gradle\caches\8.13\transforms\2cc1707a9f040f34e8d3b723c79b8eb8\transformed\memory-type-native-2.6.0-runtime\memory-type-native-2.6.0-runtime_dex, C:\Users
\Ahmed\.gradle\caches\8.13\transforms\65aac874ac0b4483a4effa47fdb0d249\transformed\memory-type-java-2.6.0-runtime\memory-type-java-2.6.0-runtime_dex, C:\Users\Ah
med\.gradle\caches\8.13\transforms\7af29e117d4358c4569656b5107109c9\transformed\imagepipeline-native-2.6.0-runtime\imagepipeline-native-2.6.0-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\1193ef93d71cd69c189eb68ffbb458f8\transformed\memory-type-ashmem-2.6.0-runtime\memory-type-ashmem-2.6.0-runtime_dex, C:\Us
ers\Ahmed\.gradle\caches\8.13\transforms\73352cd9e52ff13ec55aa6c3c6551803\transformed\imagepipeline-2.6.0-runtime\imagepipeline-2.6.0-runtime_dex, C:\Users\Ahmed
\.gradle\caches\8.13\transforms\befa291a316b8719d303c85fc424f524\transformed\nativeimagetranscoder-2.6.0-runtime\nativeimagetranscoder-2.6.0-runtime_dex, C:\User
s\Ahmed\.gradle\caches\8.13\transforms\18b0c33b554c7dcca674b80c3dbce652\transformed\imagepipeline-base-2.6.0-runtime\imagepipeline-base-2.6.0-runtime_dex, C:\Use
rs\Ahmed\.gradle\caches\8.13\transforms\d2439c12d3c569850d661aaf335da4e8\transformed\infer-annotation-0.18.0\infer-annotation-0.18.0_dex, C:\Users\Ahmed\.gradle\
caches\8.13\transforms\cdd937774fc42ee10085c7b14a9ee919\transformed\jsr305-3.0.2\jsr305-3.0.2_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\544de73ca236d103
799ff21c5fa75052\transformed\perfmark-api-0.25.0\perfmark-api-0.25.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\37bce89958824c8a895214790b9329a9\transfor
med\soloader-2.6.0-runtime\soloader-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\8578f511c034838f89c34e3bbf845721\transformed\soloader-0.10.1
-runtime\soloader-0.10.1-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\393e7d969c213dd9567b5a98269407cd\transformed\nativeloader-0.10.1\nativeloader
-0.10.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\09dcae9fdf964e5b735f2e56d2a0de90\transformed\middleware-2.6.0-runtime\middleware-2.6.0-runtime_dex, C:
\Users\Ahmed\.gradle\caches\8.13\transforms\4cc8cc28f358770fdbaef293305e18c3\transformed\ui-common-2.6.0-runtime\ui-common-2.6.0-runtime_dex, C:\Users\Ahmed\.gra
dle\caches\8.13\transforms\eb7c4c3e06658b16dee6e2d5bb74d131\transformed\fbcore-2.6.0-runtime\fbcore-2.6.0-runtime_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfo
rms\8d9acb2d69dcd820eb036e3a7a5835e0\transformed\grpc-context-1.52.1\grpc-context-1.52.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\48f9435743367b71e2080
b4ed1c5e184\transformed\failureaccess-1.0.1\failureaccess-1.0.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\11d358993586044ca09eb897439dccf9\transformed\c
hecker-qual-3.12.0\checker-qual-3.12.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\033892a4317243461faba8d2d171ea6c\transformed\j2objc-annotations-1.3\j2o
bjc-annotations-1.3_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\1bd82661a28142c5065366d9ef4cee58\transformed\gson-2.9.0\gson-2.9.0_dex, C:\Users\Ahmed\.gr
adle\caches\8.13\transforms\261b21e975084577d50e20d14c042d89\transformed\annotations-4.1.1.4\annotations-4.1.1.4_dex, C:\Users\Ahmed\.gradle\caches\8.13\transfor
ms\c41ebc49ca071d72ebb2ed5a29259578\transformed\animal-sniffer-annotations-1.21\animal-sniffer-annotations-1.21_dex, C:\Users\Ahmed\.gradle\caches\8.13\transform
s\b8b17bd3ba2f8d880e3dd6a96453ba34\transformed\constraintlayout-solver-2.0.1\constraintlayout-solver-2.0.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\6e7
49c646b9e4191bf5ca38c88840a47\transformed\annotation-0.10.1\annotation-0.10.1_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\a1053ba1983c3b19ac5fb46a0bf182f8
\transformed\bolts-tasks-1.4.0\bolts-tasks-1.4.0_dex, C:\Users\Ahmed\.gradle\caches\8.13\transforms\21a62771f287b12878838fc66a35bdfe\transformed\kotlin-annotations-jvm-1.3.72\kotlin-annotations-jvm-1.3.72_dex, and from all global synthetics files in

> Task :app:collectReleaseDependencies
Caching disabled for task ':app:collectReleaseDependencies' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:collectReleaseDependencies' is not up-to-date because:
  Value of input property 'rootComponent' has changed for task ':app:collectReleaseDependencies'
Resolve mutations for :app:sdkReleaseDependencyData (Thread[Execution worker Thread 15,5,main]) started.
:app:sdkReleaseDependencyData (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:compileReleaseKotlin
Caching disabled for ClasspathEntrySnapshotTransform: Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\compile_and_runtime_not_namespaced_r_class_jar\release\R.jar because:
  Build cache is disabled
Registered task dependencies: app:kotlinCompilerClasspath
Starting dependency analysis
Registered task dependencies: app:kotlinCompilerPluginClasspathRelease
Starting dependency analysis
Caching disabled for task ':app:compileReleaseKotlin' because:
  Build cache is disabled
Task ':app:compileReleaseKotlin' is not up-to-date because:
  Value of input property 'kotlinJavaToolchainProvider.javaVersion' has changed for task ':app:compileReleaseKotlin'
The input changes require a full rebuild for incremental task ':app:compileReleaseKotlin'.
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\release\java', not found
Kotlin source files: Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\cloudinary\CloudinaryConfig.kt, Z:\328\CMPUT328-A2\
codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\DeviceAuthenticator.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\mai
n\java\com\pluck\data\DeviceAuthPreferences.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\DeviceIdProvider.kt, Z:\
328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\firebase\FirebaseEvent.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\de
v\pLUCK\app\src\main\java\com\pluck\data\firebase\FirebaseUser.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\fireb
ase\FirebaseWaitlistEntry.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\NotificationPreferences.kt, Z:\328\CMPUT32
8-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\AdminAccessRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev
\pLUCK\app\src\main\java\com\pluck\data\repository\CloudinaryUploadRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\p
luck\data\repository\CsvExportRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\EventRepository.
kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\NotificationRepository.kt, Z:\328\CMPUT328-A2\codexworks\
301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\UserRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\jav
a\com\pluck\data\repository\WaitlistRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\MainActivity.kt, Z:\328\CM
PUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\navigation\PLuckNavHost.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\ap
p\src\main\java\com\pluck\PLuckApp.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\components\BottomNavBar.kt, Z:\328\
CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\components\PluckDesignSystem.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\de
v\pLUCK\app\src\main\java\com\pluck\ui\components\QRCodeDisplay.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\compon
ents\SharedComponents.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\model\EntrantProfile.kt, Z:\328\CMPUT328-A2\code
xworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\model\Event.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\plu
ck\ui\model\NotificationModels.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\AdminDashboardScreen.kt, Z:\328
\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ChosenEntrantsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\d
ev\pLUCK\app\src\main\java\com\pluck\ui\screens\CreateAccountScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\sc
reens\CreateEventScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\CustomThemeCreatorScreen.kt, Z:\328\CM
PUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\EditEventPosterScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev
\pLUCK\app\src\main\java\com\pluck\ui\screens\EntrantLocationsMapScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\u
i\screens\EventDetailScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\HomeScreen.kt, Z:\328\CMPUT328-A2\
codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\MyEventsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\ma
in\java\com\pluck\ui\screens\NotificationsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\OrganizerDash
boardScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\PlaceholderScreen.kt, Z:\328\CMPUT328-A2\codexwork
s\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ProfileScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\co
m\pluck\ui\screens\QRScannerScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\SettingsScreen.kt, Z:\328\C
MPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ThemePickerScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pL
UCK\app\src\main\java\com\pluck\ui\screens\WaitlistScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\Welc
omeBackScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ContrastUtils.kt, Z:\328\CMPUT328-A2\codexworks\30
1\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\CustomTheme.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\
ui\theme\PluckTheme.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ThemeManager.kt, Z:\328\CMPUT328-A2\codexwor
ks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ThemePreferences.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\
com\pluck\ui\theme\Type.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\util\QRCodeGenerator.kt, Z:\328\CMPUT328-A2\co
dexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\AdminViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\ma
in\java\com\pluck\ui\viewmodel\EventViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\NotificationsViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\WaitlistViewModel.kt
Java source files:
Script source files:
Script file extensions:
Using Kotlin/JVM incremental compilation
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
i: starting the daemon as: C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java -cp C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.k
otlin\kotlin-compiler-embeddable\1.9.10\57ca1b0823ae3ecb451a97e1f8e6de0b19ea5294\kotlin-compiler-embeddable-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\fi
les-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.9.10\72812e8a368917ab5c0a5081b56915ffdfec93b7\kotlin-stdlib-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files
-2.1\org.jetbrains.kotlin\kotlin-script-runtime\1.9.10\398513a8534701579cccaf5953ea914416b4696c\kotlin-script-runtime-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\mo
dules-2\files-2.1\org.jetbrains.kotlin\kotlin-reflect\1.6.10\1cbe9c92c12a94eea200d23c2bbaedaf3daf5132\kotlin-reflect-1.6.10.jar;C:\Users\Ahmed\.gradle\caches\mod
ules-2\files-2.1\org.jetbrains.kotlin\kotlin-daemon-embeddable\1.9.10\bda2f7daa6b89d2ded2d9e1e0b0cadaf9446ade1\kotlin-daemon-embeddable-1.9.10.jar;C:\Users\Ahmed
\.gradle\caches\modules-2\files-2.1\org.jetbrains.intellij.deps\trove4j\1.0.20200330\3afb14d5f9ceb459d724e907a21145e8ff394f02\trove4j-1.0.20200330.jar;C:\Users\A
hmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.9.10\dafaf2c27f27c09220cee312df10917d9a5d97ce\kotlin-stdlib-common-1.9.10.jar
;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar -Djava.awt.headle
ss=true -D$java.rmi.server.hostname=127.0.0.1 -Xmx2048m -Dkotlin.environment.keepalive -ea --add-exports java.base/sun.nio.ch=ALL-UNNAMED org.jetbrains.kotlin.da
emon.KotlinCompileDaemon --daemon-runFilesPath C:\Users\Ahmed\AppData\Local\kotlin\daemon --daemon-autoshutdownIdleSeconds=7200 --daemon-compilerClasspath C:\Use
rs\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-compiler-embeddable\1.9.10\57ca1b0823ae3ecb451a97e1f8e6de0b19ea5294\kotlin-compiler-embed
dable-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.9.10\72812e8a368917ab5c0a5081b56915ffdfec93b7\kotlin-stdl
ib-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-script-runtime\1.9.10\398513a8534701579cccaf5953ea914416b4696c\kotlin
-script-runtime-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-reflect\1.6.10\1cbe9c92c12a94eea200d23c2bbaedaf3daf5132\
kotlin-reflect-1.6.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-daemon-embeddable\1.9.10\bda2f7daa6b89d2ded2d9e1e0b0cadaf
9446ade1\kotlin-daemon-embeddable-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.intellij.deps\trove4j\1.0.20200330\3afb14d5f9ceb459d
724e907a21145e8ff394f02\trove4j-1.0.20200330.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.9.10\dafaf2c27f27c
09220cee312df10917d9a5d97ce\kotlin-stdlib-common-1.9.10.jar;C:\Users\Ahmed\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar
i: Received the message signalling that the daemon is ready
i: new daemon started, trying to find it
i: found daemon on port 17850 (27 ms old), trying to connect
i: connected to the daemon
Options for KOTLIN DAEMON: IncrementalCompilationOptions(super=CompilationOptions(compilerMode=INCREMENTAL_COMPILER, targetPlatform=JVM, reportCategories=[0, 3],
 reportSeverity=2, requestedCompilationResults=[0], kotlinScriptExtensions=[]), areFileChangesKnown=false, modifiedFiles=null, deletedFiles=null, classpathChange
s=NotAvailableForNonIncrementalRun, workingDir=Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileReleaseKotlin\cacheable, multiModu
leICSettings=MultiModuleICSettings(buildHistoryFile=Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileReleaseKotlin\local-state\bui
ld-history.bin, useModuleDetection=true), usePreciseJavaTracking=true, outputFiles=[Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\tmp\kotlin-
classes\release, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileReleaseKotlin\cacheable, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileReleaseKotlin\local-state])

> Task :app:sdkReleaseDependencyData
Caching disabled for task ':app:sdkReleaseDependencyData' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:sdkReleaseDependencyData' is not up-to-date because:
  Output property 'sdkDependencyData' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\sdk_dependency_data\release\sdkDependencyData.pb has been removed.
  Output property 'sdkDependencyDataPublic' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\outputs\sdk-dependencies\release\sdkDependencies.txt has been removed.
Resolve mutations for :app:writeReleaseAppMetadata (Thread[Execution worker Thread 15,5,main]) started.
:app:writeReleaseAppMetadata (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:writeReleaseAppMetadata
Caching disabled for task ':app:writeReleaseAppMetadata' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:writeReleaseAppMetadata' is not up-to-date because:
  Output property 'outputFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\app_metadata\release\app-metadata.properties has been removed.
Resolve mutations for :app:writeReleaseSigningConfigVersions (Thread[Execution worker Thread 15,5,main]) started.
:app:writeReleaseSigningConfigVersions (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:writeReleaseSigningConfigVersions
Caching disabled for task ':app:writeReleaseSigningConfigVersions' because:
  Build cache is disabled
  Caching has been disabled for the task
Task ':app:writeReleaseSigningConfigVersions' is not up-to-date because:
  Output property 'outputFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\signing_config_versions\release\signing-config-versions.json has been removed.
Resolve mutations for :app:preDebugAndroidTestBuild (Thread[Execution worker Thread 15,5,main]) started.
:app:preDebugAndroidTestBuild (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:preDebugAndroidTestBuild SKIPPED                                                                                                                     
Skipping task ':app:preDebugAndroidTestBuild' as task onlyIf 'Task is enabled' is false.
Resolve mutations for :app:generateDebugAndroidTestResValues (Thread[Execution worker Thread 11,5,main]) started.
:app:generateDebugAndroidTestResValues (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:generateDebugAndroidTestResValues
Caching disabled for task ':app:generateDebugAndroidTestResValues' because:
  Build cache is disabled
Task ':app:generateDebugAndroidTestResValues' is not up-to-date because:
  Value of input property 'items' has changed for task ':app:generateDebugAndroidTestResValues'
Resolve mutations for :app:preDebugUnitTestBuild (Thread[Execution worker Thread 15,5,main]) started.
:app:preDebugUnitTestBuild (Thread[Execution worker Thread 15,5,main]) started.

> Task :app:preDebugUnitTestBuild UP-TO-DATE                                                                                                                     
Skipping task ':app:preDebugUnitTestBuild' as it has no actions.
Resolve mutations for :app:javaPreCompileDebugUnitTest (Thread[Execution worker Thread 15,5,main]) started.
:app:javaPreCompileDebugUnitTest (Thread[Execution worker Thread 15,5,main]) started.
Resolve mutations for :app:preReleaseUnitTestBuild (Thread[Execution worker Thread 6,5,main]) started.
:app:preReleaseUnitTestBuild (Thread[Execution worker Thread 6,5,main]) started.

> Task :app:preReleaseUnitTestBuild UP-TO-DATE
Skipping task ':app:preReleaseUnitTestBuild' as it has no actions.
Resolve mutations for :app:javaPreCompileReleaseUnitTest (Thread[Execution worker Thread 6,5,main]) started.
:app:javaPreCompileReleaseUnitTest (Thread[Execution worker Thread 6,5,main]) started.

> Task :app:javaPreCompileDebugUnitTest
Caching disabled for task ':app:javaPreCompileDebugUnitTest' because:
  Build cache is disabled
Task ':app:javaPreCompileDebugUnitTest' is not up-to-date because:
  Output property 'annotationProcessorListFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\annotation_processor_list\debugUnitTest\annotationProcessors.json has been removed.

> Task :app:javaPreCompileReleaseUnitTest
Caching disabled for task ':app:javaPreCompileReleaseUnitTest' because:
  Build cache is disabled
Task ':app:javaPreCompileReleaseUnitTest' is not up-to-date because:
  Output property 'annotationProcessorListFile' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\annotation_processor_list\releaseUnitTest\annotationProcessors.json has been removed.
Resolve mutations for :app:processDebugResources (Thread[Execution worker Thread 8,5,main]) started.
:app:processDebugResources (Thread[Execution worker Thread 8,5,main]) started.

> Task :app:optimizeReleaseResources
Custom actions are attached to task ':app:optimizeReleaseResources'.
Caching disabled for task ':app:optimizeReleaseResources' because:
  Build cache is disabled
Task ':app:optimizeReleaseResources' is not up-to-date because:
  Output property 'optimizedProcessedRes' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\optimized_processed_res\release has been removed.
  Output property 'optimizedProcessedRes' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\optimized_processed_res\release\output-metadata.json has been removed.
  Output property 'optimizedProcessedRes' file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\optimized_processed_res\release\resources-release-optimize.ap_ has been removed.

> Task :app:processDebugResources
Registered task dependencies: app:debugCompileClasspath
Skipping misunderstood TO dep string: androidx.compose.ui:ui-tooling
Skipping misunderstood TO dep string: androidx.compose.ui:ui-test-manifest
Skipping misunderstood TO dep string: androidx.compose.ui:ui
Skipping misunderstood TO dep string: androidx.compose.ui:ui-tooling-preview
Skipping misunderstood TO dep string: androidx.compose.material3:material3
Skipping misunderstood TO dep string: androidx.compose.material:material-icons-extended
Starting dependency analysis
Caching disabled for task ':app:processDebugResources' because:
  Build cache is disabled
Task ':app:processDebugResources' is not up-to-date because:
  Value of input property 'useStableIds' has changed for task ':app:processDebugResources'
The input changes require a full rebuild for incremental task ':app:processDebugResources'.
Aapt output file Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\intermediates\processed_res\debug\out\resources-debug.ap_
Resolve mutations for :app:compileDebugKotlin (Thread[included builds,5,main]) started.
:app:compileDebugKotlin (Thread[included builds,5,main]) started.

> Task :app:compileDebugKotlin                                                                                                                                   
Registered task dependencies: app:kotlinCompilerPluginClasspathDebug                                                                                             
Starting dependency analysis
Caching disabled for task ':app:compileDebugKotlin' because:
  Build cache is disabled
Task ':app:compileDebugKotlin' is not up-to-date because:
  Value of input property 'kotlinJavaToolchainProvider.javaVersion' has changed for task ':app:compileDebugKotlin'
The input changes require a full rebuild for incremental task ':app:compileDebugKotlin'.
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\kotlin', not found
file or directory 'Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\debug\java', not found
Kotlin source files: Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\cloudinary\CloudinaryConfig.kt, Z:\328\CMPUT328-A2\
codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\DeviceAuthenticator.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\mai
n\java\com\pluck\data\DeviceAuthPreferences.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\DeviceIdProvider.kt, Z:\
328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\firebase\FirebaseEvent.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\de
v\pLUCK\app\src\main\java\com\pluck\data\firebase\FirebaseUser.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\fireb
ase\FirebaseWaitlistEntry.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\NotificationPreferences.kt, Z:\328\CMPUT32
8-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\AdminAccessRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev
\pLUCK\app\src\main\java\com\pluck\data\repository\CloudinaryUploadRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\p
luck\data\repository\CsvExportRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\EventRepository.
kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\NotificationRepository.kt, Z:\328\CMPUT328-A2\codexworks\
301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\data\repository\UserRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\jav
a\com\pluck\data\repository\WaitlistRepository.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\MainActivity.kt, Z:\328\CM
PUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\navigation\PLuckNavHost.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\ap
p\src\main\java\com\pluck\PLuckApp.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\components\BottomNavBar.kt, Z:\328\
CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\components\PluckDesignSystem.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\de
v\pLUCK\app\src\main\java\com\pluck\ui\components\QRCodeDisplay.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\compon
ents\SharedComponents.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\model\EntrantProfile.kt, Z:\328\CMPUT328-A2\code
xworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\model\Event.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\plu
ck\ui\model\NotificationModels.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\AdminDashboardScreen.kt, Z:\328
\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ChosenEntrantsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\d
ev\pLUCK\app\src\main\java\com\pluck\ui\screens\CreateAccountScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\sc
reens\CreateEventScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\CustomThemeCreatorScreen.kt, Z:\328\CM
PUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\EditEventPosterScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev
\pLUCK\app\src\main\java\com\pluck\ui\screens\EntrantLocationsMapScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\u
i\screens\EventDetailScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\HomeScreen.kt, Z:\328\CMPUT328-A2\
codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\MyEventsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\ma
in\java\com\pluck\ui\screens\NotificationsScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\OrganizerDash
boardScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\PlaceholderScreen.kt, Z:\328\CMPUT328-A2\codexwork
s\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ProfileScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\co
m\pluck\ui\screens\QRScannerScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\SettingsScreen.kt, Z:\328\C
MPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\ThemePickerScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pL
UCK\app\src\main\java\com\pluck\ui\screens\WaitlistScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\screens\Welc
omeBackScreen.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ContrastUtils.kt, Z:\328\CMPUT328-A2\codexworks\30
1\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\CustomTheme.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\
ui\theme\PluckTheme.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ThemeManager.kt, Z:\328\CMPUT328-A2\codexwor
ks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\theme\ThemePreferences.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\
com\pluck\ui\theme\Type.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\util\QRCodeGenerator.kt, Z:\328\CMPUT328-A2\co
dexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\AdminViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\ma
in\java\com\pluck\ui\viewmodel\EventViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\NotificationsViewModel.kt, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\src\main\java\com\pluck\ui\viewmodel\WaitlistViewModel.kt
Java source files:
Script source files:
Script file extensions:
Using Kotlin/JVM incremental compilation
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
i: found daemon on port 17850 (5753 ms old), trying to connect
i: connected to the daemon
Options for KOTLIN DAEMON: IncrementalCompilationOptions(super=CompilationOptions(compilerMode=INCREMENTAL_COMPILER, targetPlatform=JVM, reportCategories=[0, 3],
 reportSeverity=2, requestedCompilationResults=[0], kotlinScriptExtensions=[]), areFileChangesKnown=false, modifiedFiles=null, deletedFiles=null, classpathChange
s=NotAvailableForNonIncrementalRun, workingDir=Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileDebugKotlin\cacheable, multiModule
ICSettings=MultiModuleICSettings(buildHistoryFile=Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileDebugKotlin\local-state\build-h
istory.bin, useModuleDetection=true), usePreciseJavaTracking=true, outputFiles=[Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\tmp\kotlin-clas
ses\debug, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileDebugKotlin\cacheable, Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\app\build\kotlin\compileDebugKotlin\local-state])
Unable to clear jar cache after compilation, maybe daemon is already down: java.rmi.ConnectException: Connection refused to host: 127.0.0.1; nested exception is:

        java.net.ConnectException: Connection refused: connect
e: Daemon compilation failed: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more

Failed to compile with Kotlin daemon: org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more
Using fallback strategy: Compile without Kotlin daemon
Try ./gradlew --stop if this issue persists.

> Task :app:compileReleaseKotlin
Unable to clear jar cache after compilation, maybe daemon is already down: java.rmi.ConnectException: Connection refused to host: 127.0.0.1; nested exception is:

        java.net.ConnectException: Connection refused: connect
e: Daemon compilation failed: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more

Failed to compile with Kotlin daemon: org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more
Using fallback strategy: Compile without Kotlin daemon
Try ./gradlew --stop if this issue persists.

> Task :app:compileDebugKotlin FAILED
#
# There is insufficient memory for the Java Runtime Environment to continue.
# Native memory allocation (mmap) failed to map 536870912 bytes. Error detail: G1 virtual space
# An error report file with more information is saved as:
# Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\hs_err_pid40756.log
exception: openJDK 64-Bit Server VM warning: INFO: os::commit_memory(0x0000000603800000, 536870912, 0) failed; error='The paging file is too small for this operation to complete' (DOS error/errno=1455)
Errors were stored into Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\.gradle\kotlin\errors\errors-1761688770520.log

> Task :app:compileReleaseKotlin
exception: app\src\main\java\com\pluck\ui\screens\ChosenEntrantsScreen.kt:151:50: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
exception:                     imageVector = Icons.Outlined.ArrowBack,
exception:                                                  ^
exception: app\src\main\java\com\pluck\ui\screens\CustomThemeCreatorScreen.kt:272:45: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
exception:                 imageVector = Icons.Default.ArrowBack,
exception:                                             ^
exception: app\src\main\java\com\pluck\ui\screens\EditEventPosterScreen.kt:343:50: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
exception:                     imageVector = Icons.Outlined.ArrowBack,
exception:                                                  ^
exception: app\src\main\java\com\pluck\ui\screens\EntrantLocationsMapScreen.kt:80:58: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
exception:                             imageVector = Icons.Outlined.ArrowBack,
exception:                                                          ^
exception: app\src\main\java\com\pluck\ui\screens\EventDetailScreen.kt:143:54: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
exception:                         imageVector = Icons.Outlined.ArrowBack,
exception:                                                      ^
exception: app\src\main\java\com\pluck\ui\screens\QRScannerScreen.kt:136:53: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
exception:                         imageVector = Icons.Default.ArrowBack,
exception:                                                     ^
exception: app\src\main\java\com\pluck\ui\screens\ThemePickerScreen.kt:123:48: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
exception:                     imageVector = Icons.Filled.ArrowBack,
exception:                                                ^
exception: app\src\main\java\com\pluck\ui\screens\WaitlistScreen.kt:160:53: warning: 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
exception:                         imageVector = Icons.Default.ArrowBack,
exception:                                                     ^
exception: app\src\main\java\com\pluck\ui\theme\ContrastUtils.kt:63:9: warning: variable 'bgLuminance' is never used
exception:     val bgLuminance = backgroundColor.relativeLuminance()
exception:         ^
exception: app\src\main\java\com\pluck\ui\viewmodel\EventViewModel.kt:238:31: warning: parameter 'selectedIds' is never used, could be renamed to _
exception:                 onSuccess = { selectedIds ->
exception:                               ^
Errors were stored into Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\.gradle\kotlin\errors\errors-1761688770877.log
AAPT2 aapt2-8.1.3-10154469-windows Daemon #0: shutdown

[Incubating] Problems report is available at: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/build/reports/problems/problems-report.html

FAILURE: Build failed with an exception.                                                                                                                         

* What went wrong:
Execution failed for task ':app:compileDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':app:compileDebugKotlin'.
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.lambda$executeIfValid$1(ExecuteActionsTaskExecuter.java:130)
        at org.gradle.internal.Try$Failure.ifSuccessfulOrElse(Try.java:293)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:128)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
        at org.gradle.api.internal.tasks.execution.ProblemsTaskPathTrackingTaskExecuter.execute(ProblemsTaskPathTrackingTaskExecuter.java:40)
        at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
        at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
        at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
        at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
        at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
        at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)      
        at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
        at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
        at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
        at org.gradle.execution.plan.DefaultPlanExecutor.process(DefaultPlanExecutor.java:111)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph.executeWithServices(DefaultTaskExecutionGraph.java:138)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph.execute(DefaultTaskExecutionGraph.java:123)
        at org.gradle.execution.SelectedTaskExecutionAction.execute(SelectedTaskExecutionAction.java:35)
        at org.gradle.execution.DryRunBuildExecutionAction.execute(DryRunBuildExecutionAction.java:51)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor$ExecuteTasks.call(BuildOperationFiringBuildWorkerExecutor.java:54)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor$ExecuteTasks.call(BuildOperationFiringBuildWorkerExecutor.java:43)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor.execute(BuildOperationFiringBuildWorkerExecutor.java:40)
        at org.gradle.internal.build.DefaultBuildLifecycleController.lambda$executeTasks$10(DefaultBuildLifecycleController.java:313)
        at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
        at org.gradle.internal.model.StateTransitionController.lambda$tryTransition$8(StateTransitionController.java:177)
        at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
        at org.gradle.internal.model.StateTransitionController.tryTransition(StateTransitionController.java:177)
        at org.gradle.internal.build.DefaultBuildLifecycleController.executeTasks(DefaultBuildLifecycleController.java:304)
        at org.gradle.internal.build.DefaultBuildWorkGraphController$DefaultBuildWorkGraph.runWork(DefaultBuildWorkGraphController.java:220)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.composite.internal.DefaultBuildController.doRun(DefaultBuildController.java:181)
        at org.gradle.composite.internal.DefaultBuildController.access$000(DefaultBuildController.java:50)
        at org.gradle.composite.internal.DefaultBuildController$BuildOpRunnable.lambda$run$0(DefaultBuildController.java:198)
        at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
        at org.gradle.composite.internal.DefaultBuildController$BuildOpRunnable.run(DefaultBuildController.java:198)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.gradle.workers.internal.DefaultWorkerExecutor$WorkExecutionException: A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
        at org.gradle.workers.internal.DefaultWorkerExecutor$WorkItemExecution.waitForCompletion(DefaultWorkerExecutor.java:287)
        at org.gradle.internal.work.DefaultAsyncWorkTracker.lambda$waitForItemsAndGatherFailures$2(DefaultAsyncWorkTracker.java:130)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLocks(DefaultWorkerLeaseService.java:335)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLocks(DefaultWorkerLeaseService.java:318)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLock(DefaultWorkerLeaseService.java:323)
        at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForItemsAndGatherFailures(DefaultAsyncWorkTracker.java:126)
        at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForItemsAndGatherFailures(DefaultAsyncWorkTracker.java:92)
        at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForAll(DefaultAsyncWorkTracker.java:78)
        at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForCompletion(DefaultAsyncWorkTracker.java:66)
        at org.gradle.api.internal.tasks.execution.TaskExecution$3.run(TaskExecution.java:252)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:30)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:27)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:48)
        at org.gradle.api.internal.tasks.execution.TaskExecution.executeAction(TaskExecution.java:229)
        at org.gradle.api.internal.tasks.execution.TaskExecution.executeActions(TaskExecution.java:212)
        at org.gradle.api.internal.tasks.execution.TaskExecution.executeWithPreviousOutputFiles(TaskExecution.java:195)
        at org.gradle.api.internal.tasks.execution.TaskExecution.execute(TaskExecution.java:162)
        at org.gradle.internal.execution.steps.ExecuteStep.executeInternal(ExecuteStep.java:105)
        at org.gradle.internal.execution.steps.ExecuteStep.access$000(ExecuteStep.java:44)
        at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:59)
        at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:56)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:56)
        at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:44)
        at org.gradle.internal.execution.steps.CancelExecutionStep.execute(CancelExecutionStep.java:42)
        at org.gradle.internal.execution.steps.TimeoutStep.executeWithoutTimeout(TimeoutStep.java:75)
        at org.gradle.internal.execution.steps.TimeoutStep.execute(TimeoutStep.java:55)
        at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:50)
        at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:28)
        at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:67)
        at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:37)
        at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:61)
        at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:26)
        at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:69)
        at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:46)
        at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:40)
        at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:29)
        at org.gradle.internal.execution.steps.BuildCacheStep.executeWithoutCache(BuildCacheStep.java:189)
        at org.gradle.internal.execution.steps.BuildCacheStep.lambda$execute$1(BuildCacheStep.java:75)
        at org.gradle.internal.Either$Right.fold(Either.java:175)
        at org.gradle.internal.execution.caching.CachingState.fold(CachingState.java:62)
        at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:73)
        at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:48)
        at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:46)
        at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:35)
        at org.gradle.internal.execution.steps.SkipUpToDateStep.executeBecause(SkipUpToDateStep.java:75)
        at org.gradle.internal.execution.steps.SkipUpToDateStep.lambda$execute$2(SkipUpToDateStep.java:53)
        at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:53)
        at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:35)
        at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:37)
        at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:27)
        at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:49)
        at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:27)
        at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:71)
        at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:39)
        at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:65)
        at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:36)
        at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:107)
        at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:56)
        at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:64)
        at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:43)
        at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.executeWithNonEmptySources(AbstractSkipEmptyWorkStep.java:125)
        at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:61)
        at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:36)
        at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
        at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:36)
        at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:23)
        at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:75)
        at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:41)
        at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.lambda$execute$0(AssignMutableWorkspaceStep.java:35)
        at org.gradle.api.internal.tasks.execution.TaskExecution$4.withWorkspace(TaskExecution.java:289)
        at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:31)
        at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:22)
        at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:40)
        at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:23)
        at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.lambda$execute$2(ExecuteWorkBuildOperationFiringStep.java:67)
        at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:67)
        at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:39)
        at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:46)
        at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:34)
        at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:48)
        at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:35)
        at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:61)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:127)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
        at org.gradle.api.internal.tasks.execution.ProblemsTaskPathTrackingTaskExecuter.execute(ProblemsTaskPathTrackingTaskExecuter.java:40)
        at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
        at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
        at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
        at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
        at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
        at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)      
        at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
        at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
        at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
        at org.gradle.execution.plan.DefaultPlanExecutor.process(DefaultPlanExecutor.java:111)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph.executeWithServices(DefaultTaskExecutionGraph.java:138)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph.execute(DefaultTaskExecutionGraph.java:123)
        at org.gradle.execution.SelectedTaskExecutionAction.execute(SelectedTaskExecutionAction.java:35)
        at org.gradle.execution.DryRunBuildExecutionAction.execute(DryRunBuildExecutionAction.java:51)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor$ExecuteTasks.call(BuildOperationFiringBuildWorkerExecutor.java:54)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor$ExecuteTasks.call(BuildOperationFiringBuildWorkerExecutor.java:43)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.execution.BuildOperationFiringBuildWorkerExecutor.execute(BuildOperationFiringBuildWorkerExecutor.java:40)
        at org.gradle.internal.build.DefaultBuildLifecycleController.lambda$executeTasks$10(DefaultBuildLifecycleController.java:313)
        at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
        at org.gradle.internal.model.StateTransitionController.lambda$tryTransition$8(StateTransitionController.java:177)
        at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
        at org.gradle.internal.model.StateTransitionController.tryTransition(StateTransitionController.java:177)
        at org.gradle.internal.build.DefaultBuildLifecycleController.executeTasks(DefaultBuildLifecycleController.java:304)
        at org.gradle.internal.build.DefaultBuildWorkGraphController$DefaultBuildWorkGraph.runWork(DefaultBuildWorkGraphController.java:220)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.composite.internal.DefaultBuildController.doRun(DefaultBuildController.java:181)
        at org.gradle.composite.internal.DefaultBuildController.access$000(DefaultBuildController.java:50)
        at org.gradle.composite.internal.DefaultBuildController$BuildOpRunnable.lambda$run$0(DefaultBuildController.java:198)
        at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
        at org.gradle.composite.internal.DefaultBuildController$BuildOpRunnable.run(DefaultBuildController.java:198)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.jetbrains.kotlin.gradle.tasks.CompilationErrorException: Compilation error. See log for more details
        at org.jetbrains.kotlin.gradle.tasks.TasksUtilsKt.throwExceptionIfCompilationFailed(tasksUtils.kt:22)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:144)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        ... 2 more


Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.13/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.      

BUILD FAILED in 2m 32s
65 actionable tasks: 65 executed
Watched directory hierarchies: [Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK]
PS Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK> ./gradlew clean build                    

> Task :app:stripDebugDebugSymbols
Unable to strip the following libraries, packaging them as they are: libbarhopper_v3.so, libimage_processing_util_jni.so, libimagepipeline.so, libnative-filters.so, libnative-imagetranscoder.so.

> Task :app:stripReleaseDebugSymbols
Unable to strip the following libraries, packaging them as they are: libbarhopper_v3.so, libimage_processing_util_jni.so, libimagepipeline.so, libnative-filters.so, libnative-imagetranscoder.so.

> Task :app:compileDebugKotlin
Unable to clear jar cache after compilation, maybe daemon is already down: java.rmi.ConnectException: Connection refused to host: 127.0.0.1; nested exception is:

        java.net.ConnectException: Connection refused: connect
e: Daemon compilation failed: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more

Failed to compile with Kotlin daemon: org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more
Using fallback strategy: Compile without Kotlin daemon
Try ./gradlew --stop if this issue persists.

> Task :app:compileReleaseKotlin
Unable to clear jar cache after compilation, maybe daemon is already down: java.rmi.ConnectException: Connection refused to host: 127.0.0.1; nested exception is:

        java.net.ConnectException: Connection refused: connect
e: Daemon compilation failed: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more

Failed to compile with Kotlin daemon: org.jetbrains.kotlin.gradle.tasks.DaemonCrashedException: Connection to the Kotlin daemon has been unexpectedly lost. This might be caused by the daemon being killed by another process or the operating system, or by JVM crash.
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:253)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemonOrFallbackImpl(GradleKotlinCompilerWork.kt:178)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:138)
        at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)     
        at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
        at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
        at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:210)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:205)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:67)
        at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:167)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:60)
        at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:54)
        at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
        at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
        at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
        at org.gradle.internal.Factories$1.create(Factories.java:31)
        at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
        at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
        at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
        at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
        at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
        at java.base/java.lang.Thread.run(Thread.java:840)
Caused by: java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is:
        java.net.SocketException: Connection reset
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:255)
        at java.rmi/sun.rmi.server.UnicastRef.invoke(UnicastRef.java:165)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invokeRemoteMethod(RemoteObjectInvocationHandler.java:215)
        at java.rmi/java.rmi.server.RemoteObjectInvocationHandler.invoke(RemoteObjectInvocationHandler.java:160)
        at jdk.proxy4/jdk.proxy4.$Proxy163.compile(Unknown Source)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.incrementalCompilationWithDaemon(GradleKotlinCompilerWork.kt:343)
        at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.compileWithDaemon(GradleKotlinCompilerWork.kt:242)
        ... 37 more
Caused by: java.net.SocketException: Connection reset
        at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:328)
        at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:355)
        at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:808)
        at java.base/java.net.Socket$SocketInputStream.read(Socket.java:966)
        at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244)
        at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:263)
        at java.base/java.io.DataInputStream.readUnsignedByte(DataInputStream.java:288)
        at java.base/java.io.DataInputStream.readByte(DataInputStream.java:268)
        at java.rmi/sun.rmi.transport.StreamRemoteCall.executeCall(StreamRemoteCall.java:241)
        ... 43 more
Using fallback strategy: Compile without Kotlin daemon
Try ./gradlew --stop if this issue persists.
#
exception: openJDK 64-Bit Server VM warning: INFO: os::commit_memory(0x0000000613400000, 192937984, 0) failed; error='The paging file is too small for this operation to complete' (DOS error/errno=1455)
# There is insufficient memory for the Java Runtime Environment to continue.
# Native memory allocation (mmap) failed to map 192937984 bytes. Error detail: G1 virtual space
# An error report file with more information is saved as:
# Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\hs_err_pid456.log
Errors were stored into Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\.gradle\kotlin\errors\errors-1761688969114.log

> Task :app:compileReleaseKotlin FAILED

> Task :app:compileDebugKotlin
exception: app\src\main\java\com\pluck\data\repository\NotificationRepository.kt:23:7: error: imports are only allowed in the beginning of file
exception: public import kotlinx.coroutines.flow.combine
exception:       ^
Errors were stored into Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK\.gradle\kotlin\errors\errors-1761688969079.log

> Task :app:compileDebugKotlin FAILED

[Incubating] Problems report is available at: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/build/reports/problems/problems-report.html

FAILURE: Build completed with 2 failures.

1: Task failed with an exception.
-----------
* What went wrong:
Execution failed for task ':app:compileReleaseKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.
==============================================================================

2: Task failed with an exception.
-----------
* What went wrong:
Execution failed for task ':app:compileDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.
==============================================================================

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.13/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.      

BUILD FAILED in 1m 33s
65 actionable tasks: 65 executed
PS Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK>  









PS Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK> .\gradlew.bat assembleDebug assembleDebugAndroidTest installDebug installDebugAndroidTest

> Task :app:compileDebugKotlin
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/ChosenEntrantsScreen.kt:151:50 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/CustomThemeCreatorScreen.kt:272:45 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/EditEventPosterScreen.kt:343:50 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/EntrantLocationsMapScreen.kt:80:58 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/EventDetailScreen.kt:143:54 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/QRScannerScreen.kt:136:53 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/ThemePickerScreen.kt:123:48 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/screens/WaitlistScreen.kt:160:53 'ArrowBack: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.ArrowBack
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/theme/ContrastUtils.kt:63:9 Variable 'bgLuminance' is never used
w: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/main/java/com/pluck/ui/viewmodel/EventViewModel.kt:238:31 Parameter 'selectedIds' is never used, could be renamed to _

> Task :app:compileDebugAndroidTestKotlin FAILED
e: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/androidTest/java/com/pluck/ui/screens/ProfileScreenTest.kt:35:21 No value passed for parameter 'profileImageUrl'
e: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/androidTest/java/com/pluck/ui/screens/ProfileScreenTest.kt:62:21 No value passed for parameter 'profileImageUrl'
e: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/app/src/androidTest/java/com/pluck/ui/screens/ScreensSmokeTest.kt:160:21 No value passed for parameter 'profileImageUrl'

> Task :app:installDebug
Installing APK 'app-debug.apk' on 'Pixel_9_Pro_XL(AVD) - 16' for :app:debug
Installed on 1 device.

[Incubating] Problems report is available at: file:///Z:/328/CMPUT328-A2/codexworks/301/VertexLotto/dev/pLUCK/build/reports/problems/problems-report.html

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:compileDebugAndroidTestKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.13/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD FAILED in 53s
55 actionable tasks: 8 executed, 47 up-to-date
PS Z:\328\CMPUT328-A2\codexworks\301\VertexLotto\dev\pLUCK>      