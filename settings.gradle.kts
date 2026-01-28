pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


rootProject.name = "bloquinhopy-android"

include(":app")
include(":core")
include(":feature-notebooks")
include(":feature-projects")
include(":feature-github")
include(":runtime-python")
include(":python-pack")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
