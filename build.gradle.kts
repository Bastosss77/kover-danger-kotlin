plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.kover)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.dangerSdk)
    implementation(platform(libs.jackson.bom))
    implementation(libs.jackson.xml)
    implementation(libs.jackson.kotlin)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
}

kotlin {
    jvmToolchain(
        libs.versions.jvmVersion
            .get()
            .toInt(),
    )
}

group = libs.versions.group.get()
version = libs.versions.version.get()

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    coordinates(group.toString(), "kover-danger-kotlin", version.toString())

    pom {
        name.set("Kover Danger Kotlin")
        description.set("A Kover plugin for Danger Kotlin")
        inceptionYear.set("2026")
        url.set("https://github.com/Bastosss77/kover-danger-kotlin")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("Bastosss77")
                name.set("Bastosss77")
                url.set("https://github.com/Bastosss77")
                email.set("bastosss77@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/Bastosss77/kover-danger-kotlin")
            connection.set("scm:git:git://github.com/Bastosss77/kover-danger-kotlin.git")
            developerConnection.set("scm:git:ssh://git@github.com/Bastosss77/kover-danger-kotlin.git")
        }
    }
}
