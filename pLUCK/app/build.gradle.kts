import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.services)
    id("org.jetbrains.dokka") version "2.1.0"

}
dokka {
    dokkaPublications.html {
        outputDirectory.set(layout.projectDirectory.dir("ktdoc/dokka"))
    }
}

// Load local.properties
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

android {
    namespace = "com.pluck"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pluck"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Make Maps API key available to manifest
        val mapsApiKey = localProperties.getProperty("MAPS_API_KEY") ?: "YOUR_MAPS_API_KEY_HERE"
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey

        // Cloudinary configuration sourced from local.properties for flexibility per environment
        val cloudinaryCloudName = localProperties
            .getProperty("CLOUDINARY_CLOUD_NAME")
            ?.takeIf { it.isNotBlank() }
            ?: "pluck"

        val cloudinaryUploadPreset = localProperties
            .getProperty("CLOUDINARY_UPLOAD_PRESET")
            ?.takeIf { it.isNotBlank() }
            ?: "pluck_unsigned"

        val cloudinaryAllowPublicIdOverride = localProperties
            .getProperty("CLOUDINARY_ALLOW_PUBLIC_ID_OVERRIDE")
            ?.takeIf { it.isNotBlank() }
            ?.toBooleanStrictOrNull()
            ?: false

        val cloudinaryApiKey = localProperties
            .getProperty("CLOUDINARY_API_KEY")
            ?.takeIf { it.isNotBlank() }
            ?: ""

        resValue("string", "cloudinary_cloud_name", cloudinaryCloudName)
        resValue("string", "cloudinary_upload_preset", cloudinaryUploadPreset)
        resValue("string", "cloudinary_api_key", cloudinaryApiKey)
        resValue("bool", "cloudinary_allow_public_id_override", cloudinaryAllowPublicIdOverride.toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = false
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("com.google.protobuf:protobuf-javalite:3.25.0")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)

    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))
    implementation("com.google.firebase:firebase-messaging")

    implementation(libs.mlkit.barcode)
    implementation(libs.play.services.location)
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.zxing:core:3.5.3")
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Cloudinary for image uploads
    implementation("com.cloudinary:cloudinary-android:2.5.0")

    // CameraX
    implementation(libs.camerax.core)
    implementation(libs.camerax.camera2)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.view)

    // Permissions
    implementation(libs.accompanist.permissions)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation("com.google.protobuf:protobuf-javalite:3.25.0")
    debugImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
