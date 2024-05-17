plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = "${project.rootDir}/jacoco/jacoco.gradle.kts")

android {
    namespace = "org.dhis2.dhis2_mobile_program_rules"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":commons"))
    testImplementation(libs.test.mockitoCore)
    testImplementation(libs.test.mockitoInline)
    testImplementation(libs.test.mockitoKotlin)
    coreLibraryDesugaring(libs.desugar)
}