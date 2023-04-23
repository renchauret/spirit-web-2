plugins {
    kotlin("js") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
}

group = "com.chauret"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser()
    }.binaries.executable()
//    js {
//        browser {
//            commonWebpackConfig {
////                cssSupport.enabled = true
//            }
//        }
//        binaries.executable()
//    }
}

dependencies {
//    // tailwind
//    implementation(npm("tailwindcss", "3.2.1"))
//    implementation(npm("@tailwindcss/forms", "0.5.3"))
//
//    // webpack
//    implementation(devNpm("postcss", "8.4.17"))
//    implementation(devNpm("postcss-loader", "7.0.1"))
//    implementation(devNpm("autoprefixer", "10.4.12"))
//    implementation(devNpm("css-loader", "6.7.1"))
//    implementation(devNpm("style-loader", "3.3.1"))
//    implementation(devNpm("cssnano", "5.1.13"))

    //React, React DOM + Wrappers (chapter 3)
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.354"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")

    //Kotlin React Emotion (CSS) (chapter 3)
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")

    //Video Player (chapter 7)
    implementation(npm("react-player", "2.10.1"))

    //Share Buttons (chapter 7)
    implementation(npm("react-share", "4.4.0"))

    //Coroutines & serialization (chapter 8)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

// Heroku Deployment (chapter 9)
tasks.register("stage") {
    dependsOn("build")
}

rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}