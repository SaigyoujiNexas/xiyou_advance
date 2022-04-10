dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven(url= "https://jitpack.io")
        mavenCentral()
        jcenter()
    }
}
rootProject.name = "行之"
include(":app")
include(":modulesBase:libBase")
include(":modulesPublic:common")

include(":modulesCore:main")
