# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.Faq {
   public *;
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

-keep class com.google.android.gms.internal.** { *; }
-dontwarn com.google.android.gms.**
-dontskipnonpubliclibraryclasses
-keep public class com.google.android.gms.* { public *; }

-keep class com.facebook.ads.** { *; }
# Package
#-keeppackagenames com.kampen.riksSe{public *;}

# ActivityDaily Classes
# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
-keep class com.shockwave.**
-keepclasseswithmembers class com.kampen.riksSe.SplashActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.login.LoginActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.ForgotPassword.EmailVerificationActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.ForgotPassword.ForgotPasswordActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.WPlans.IntroActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.MainLeaderActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.WeeklyActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.DailyActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.DailyImageActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.nutrition.IngredientsWeeklySummary{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.nutrition.RecipeSummary{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.RecipeVideoActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.traings.workout.WorkOutNewUIActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.traings.workout.WorkOutNewUIDyActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.VideoPlayActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.WorkoutMotivationVideo{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.WorkOutVideoActivityNew{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.About{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.Faq{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.PrivacyPolicy{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.AddAllergies{ *;}

# BroadCast Recivers

-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.DateChangeReceiver{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.MyBroadcastReceiver{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.UpdateReceiver{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.DailyActivitySyncService{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.VideoOFfclick.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.mYourBroadcastReceiver.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.mBroadcastReceiverSteps.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.CompitionCounterFinish.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.m_timeChangedReceiver.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.DateChangeReceiver.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.ActivityFragment.DailyDiaryQuestionclick.{ *;}

-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.CountDownCompitition.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.CountDownCompitition.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.map.Map.CountDownCompitition.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.account.profile.CountDownCompitition.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService.StepCountingBroadCast.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService.ScreenOpen.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService.DateChangeReceiver.{ *;}
-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService.m_timeChangedReceiver.{ *;}

# Services

-keepclasseswithmembers class com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.DailyStepsService{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.DailyStepsServiceInternet{ *;}
-keepclasseswithmembers class com.kampen.riksSe.BroadCastReciver.NotificationAlertService{ *;}


# Realm classes

-keep @interface io.realm.annotations.RealmModule { *; }
-keep class io.realm.annotations.RealmModule { *; }

# 3rd Party Libraries

# Twilio Video Library
-keep class tvi.webrtc.** { *; }
-keep class com.twilio.video.** { *; }
-keepattributes InnerClasses

-keepclasseswithmembers class com.github.arturogutierrez{ *;}
-keepclasseswithmembers class me.leolin{ *;}
-keepclasseswithmembers class com.github.julkar-nain{ *;}
-keepclasseswithmembers class com.nex3z{ *;}
-keepclasseswithmembers class io.github.luizgrp.sectionedrecyclerviewadapter{ *;}
-keepclasseswithmembers class com.intuit.sdp{ *;}
-keepclasseswithmembers class uk.co.chrisjenx{ *;}
-keepclasseswithmembers class com.squareup.picasso{ *;}
-keepclasseswithmembers class org.parceler{ *;}
-keepclasseswithmembers class fm.jiecao{ *;}
-keepclasseswithmembers class io.nlopez.smartlocation{ *;}
-keepclasseswithmembers class de.hdodenhof{ *;}
-keepclasseswithmembers class com.makeramen{ *;}
-keepclasseswithmembers class com.akexorcist{ *;}
-keepclasseswithmembers class biz.kasual{ *;}
-keepclasseswithmembers class com.google.code.gson{ *;}
-keepclasseswithmembers class com.squareup.retrofit2{ *;}
-keepclasseswithmembers class com.squareup.okhttp3{ *;}
-keepclasseswithmembers class com.mikhaellopez{ *;}
-keepclasseswithmembers class com.github.PhilJay{ *;}
-keepclasseswithmembers class com.facebook.stetho{ *;}
-keepclasseswithmembers class com.uphyca{ *;}
-keepclasseswithmembers class com.github.maayyaannkk{ *;}
-keepclasseswithmembers class com.github.timigod{ *;}
-keepclasseswithmembers class com.wang.avi{ *;}
-keepclasseswithmembers class com.google.android.exoplayer{ *;}
-keepclasseswithmembers class net.butterflytv.utils{ *;}
-keepclasseswithmembers class com.github.bumptech.glide{ *;}
-keepclasseswithmembers class pub.devrel{ *;}
-keepclasseswithmembers class com.loopj.android{ *;}
-keepclasseswithmembers class com.google.maps.android{ *;}
-keepclasseswithmembers class com.parse{ *;}
-keepclasseswithmembers class com.crashlytics.sdk.android{ *;}
-keepclasseswithmembers class com.parse.ParseApacheHttpClient{ *;}
-keepclasseswithmembers class com.wang.avi{ *;}

-keepclasseswithmembers class com.google.** { *; }
-keepclasseswithmembers class com.github.** { *; }
-keepclasseswithmembers class org.apache.** { *; }
-keepclasseswithmembers class com.android.** { *; }
-keepclasseswithmembers class junit.** { *; }


-dontwarn com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar
-dontwarn android.net.SSLCertificateSocketFactory
-dontwarn retrofit.**
-dontwarn com.androidpagecontrol.PageControl
-dontwarn com.androidpagecontrol.PageControl$Companion
-dontwarn com.androidpagecontrol.PageControl$setViewPager$1
-dontwarn com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity$11$1
-dontwarn com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity$12$1

-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Parcel library
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class org.parceler.Parceler$$Parcels

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}



# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule