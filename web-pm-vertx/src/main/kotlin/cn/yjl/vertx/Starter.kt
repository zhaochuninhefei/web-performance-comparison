package cn.yjl.vertx

import io.vertx.core.Launcher
import java.io.IOException
import java.util.logging.LogManager
import java.util.logging.Logger
import kotlin.system.exitProcess

object Starter {
  @JvmStatic
  fun main(args: Array<String>) {
    Launcher.main(args)
  }

  init {
    try {
      Starter::class.java.getResourceAsStream("logging.properties")
      LogManager.getLogManager().readConfiguration(Starter::class.java.getResourceAsStream("/logging.properties"))
      val logger = Logger.getLogger(Starter::class.java.name)
      logger.info("logger init completed")
    } catch (e: IOException) {
      e.printStackTrace()
      exitProcess(0)
    }
  }
}
