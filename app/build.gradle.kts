plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")


}

android {
    namespace = "com.andrayudu.newsappcomposemvvmtej"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.andrayudu.newsappcomposemvvmtej"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"yourkeyhere\"")
            //FIXME remove the string and add your api key eg. if your api key is 12345sdakjfhsadfhj
            // then it should be like this "\"12345sdakjfhsadfhj\""
        }
        release {
            //isMinifyEnabled = true enables the proguard and obfuscates the code
            isMinifyEnabled = true
            //reduces the apk size
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("com.google.android.material:material:1.9.0") //google material library




    //Coil library
    implementation("io.coil-kt:coil-compose:2.4.0")


    //compose viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")




    //Dagger-Hilt
    val hilt_version = "2.48.1"
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    ksp ("com.google.dagger:hilt-compiler:$hilt_version")

    //hilt viewmodel and hilt navigation - dependency injection , navigation related
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2") // collectAsStateWithLifecycle


    //material1
    implementation("androidx.compose.material:material:1.5.4")


    //navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("androidx.navigation:navigation-compose:2.6.0") //compose navigation



    //okHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    //PullToRefresh-Accompanist
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.36.0")


    //retrofit
    val retrofit_version = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")


    //room
    // Room
    val roomVersion = "2.4.3"
    implementation ("androidx.room:room-runtime:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")



    //Test
    testImplementation(libs.junit)

    //coroutines-Test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // mockito
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0") //mockwebserver, also need to add okhttp dependency

    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("com.google.truth:truth:1.1")





    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}