# ProGuard/R8 configuration for BloquinhoPy release builds
# This file specifies which classes and members should NOT be obfuscated
# to maintain app functionality

# Keep all classes that are referenced via reflection or by the JNI
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
-keepattributes Exceptions

# Keep all Activity, Service, Receiver, and Provider subclasses
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.fragment.app.Fragment

# Keep View constructors for inflation from XML
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Keep Parcelable implementations
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep Serializable implementations
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep Compose runtime
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }

# Keep Hilt-generated classes
-keep class * extends dagger.hilt.android.lifecycle.HiltViewModel
-keep class ** extends dagger.hilt.android.internal.managers.** { *; }
-keep class dagger.hilt.** { *; }

# Keep Jetpack classes
-keep class androidx.lifecycle.** { *; }
-keep class androidx.navigation.** { *; }
-keep class androidx.activity.compose.** { *; }

# Keep NotebookViewModel and related classes
-keep class io.github.bcmaymonegalvao.bloquinhopy.feature.notebooks.** { *; }
-keep class io.github.bcmaymonegalvao.bloquinhopy.runtime.** { *; }

# Keep Python runtime (Chaquopy)
-keep class com.chaquo.python.** { *; }
-keepclassmembers class com.chaquo.python.** { *; }

# Keep data classes
-keepclassmembers class ** {
    *** component*();
    *** copy*();
}

# Remove logging in release builds (optional)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Optimization settings
-optimizationpasses 5
-allowaccessmodification
-repackageclasses
