
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-dontwarn uk.**
-keep class retrofit.** { *; }
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}
-keep class com.technocracy.app.aavartan.** { *; }
-keep public class com.google.gson