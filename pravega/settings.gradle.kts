pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace?.startsWith("com.android") == true) {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}
rootProject.name = "shibasispatnaik/pravega"

