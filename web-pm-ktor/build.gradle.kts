plugins {
	kotlin("multiplatform") version "2.1.0"
	alias(libs.plugins.kotlin.plugin.serialization)
//	alias(libs.plugins.ksp)
}

//ksp {
//	arg("output-package", "cn.yjl.dto")
//	arg("output-filename", "GeneratedQueries")
//}

group = "cn.yjl"
version = "0.0.1"

repositories {
	// 阿里云镜像
	maven("https://maven.aliyun.com/repository/public/")

	// 清华大学镜像
	maven("https://mirrors.tuna.tsinghua.edu.cn/maven/")

	mavenLocal()

	// 中央仓库
	mavenCentral()

	// JetBrains 仓库
	maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
}

//dependencies {
//	ksp("io.github.smyrgeorge:sqlx4k-codegen:0.35.0")
//}

kotlin {
	val hostOs = System.getProperty("os.name")
	val arch = System.getProperty("os.arch")
	val nativeTarget = when {
		hostOs == "Mac OS X" && arch == "x86_64" -> macosX64("native")
		hostOs == "Mac OS X" && arch == "aarch64" -> macosArm64("native")
		hostOs == "Linux" -> linuxX64("native")
		else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
	}

	nativeTarget.apply {
		binaries {
			executable {
				entryPoint = "cn.yjl.main"
			}
		}
	}
	sourceSets {
		val nativeMain by getting {
			dependencies {
				implementation(libs.ktor.server.core)
				implementation(libs.ktor.server.cio)
				implementation(libs.ktor.server.content.negotiation)
				implementation(libs.ktor.serialization.kotlinx.json)
				implementation(libs.ktor.server.config.yaml)
				implementation(libs.sqlx4k.mysql)
			}
		}
	}
}
