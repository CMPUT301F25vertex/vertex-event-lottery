# ProGuard Rules for pLUCK Application
# Protects application code from reverse engineering and ensures proper operation with third-party libraries

# ==================================================================================
# SECURITY: Code Obfuscation and String Encryption
# ==================================================================================
# Enable aggressive obfuscation to protect against decompilation
-repackageclasses
-allowaccessmodification
-optimizationpasses 5

# Keep line numbers for crash reporting but hide source file names
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ==================================================================================
# CLOUDINARY: Image Upload Service
# ==================================================================================
# Keep Cloudinary classes and methods required for unsigned uploads
# Note: NO API keys or secrets are stored in the app - only cloud name and unsigned preset
-keep class com.cloudinary.** { *; }
-keep interface com.cloudinary.** { *; }
-keepclassmembers class com.cloudinary.** { *; }
-dontwarn com.cloudinary.**

# Keep our Cloudinary configuration (contains only public cloud name and unsigned preset)
-keep class com.pluck.data.cloudinary.CloudinaryConfig { *; }
-keep class com.pluck.data.repository.CloudinaryUploadRepository { *; }
-keep class com.pluck.data.repository.CloudinaryUploadResult { *; }

# ==================================================================================
# FIREBASE: Backend Services
# ==================================================================================
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# Keep Firebase data models for proper serialization
-keep class com.pluck.data.firebase.** { *; }
-keepclassmembers class com.pluck.data.firebase.** { *; }

# ==================================================================================
# KOTLIN: Coroutines and Reflection
# ==================================================================================
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**
-keepclassmembers class kotlinx.coroutines.** { *; }

# Keep Kotlin metadata for reflection
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# ==================================================================================
# JETPACK COMPOSE: UI Framework
# ==================================================================================
-keep class androidx.compose.** { *; }
-keepclassmembers class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Keep all Composable functions
-keep @androidx.compose.runtime.Composable class * { *; }
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}

# ==================================================================================
# DATA MODELS: Preserve for Firestore Serialization
# ==================================================================================
# Keep all data classes and their properties
-keep class com.pluck.ui.model.** { *; }
-keepclassmembers class com.pluck.ui.model.** { *; }

# ==================================================================================
# NETWORKING: HTTP and Image Loading
# ==================================================================================
# OkHttp (used by Cloudinary)
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Coil (image loading)
-keep class coil.** { *; }
-dontwarn coil.**

# ==================================================================================
# BARCODE SCANNING: ML Kit and ZXing
# ==================================================================================
-keep class com.google.mlkit.** { *; }
-dontwarn com.google.mlkit.**

-keep class com.google.zxing.** { *; }
-dontwarn com.google.zxing.**

# ==================================================================================
# MAPS: Google Maps Integration
# ==================================================================================
-keep class com.google.android.gms.maps.** { *; }
-dontwarn com.google.android.gms.maps.**

# ==================================================================================
# GENERAL: Android and Java Libraries
# ==================================================================================
# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelable implementations
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}