include(
    ":app", ":viewpagerdotsindicator",
    ":dhis_android_analytics", ":form", ":commons",
    ":dhis2_android_maps", ":compose-table", ":ui-components",
    ":stock-usecase"
)
include(":dhis2-mobile-program-rules")
include(":support-module")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
include(":tracker")
