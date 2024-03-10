pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        https://github.com/a914-gowtham/compose-ratingbar/blob/main/README.md
        maven { setUrl("https://jitpack.io")  }
    }
}

rootProject.name = "Level-3-task-1"
include(":app")
