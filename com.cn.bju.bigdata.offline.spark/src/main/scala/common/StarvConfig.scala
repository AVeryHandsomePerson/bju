package common

import java.sql.{Connection, DriverManager}
import java.util.Properties


object StarvConfig {

  val properties = new Properties()
  private var in = StarvConfig.getClass.getClassLoader.getResourceAsStream("application.properties")
  properties.load(in)

  in.close()
  private val isDev = properties.getProperty("profile") == "dev"
  if (isDev) {
    in = StarvConfig.getClass.getClassLoader.getResourceAsStream("dev.properties")
  } else {
    in = StarvConfig.getClass.getClassLoader.getResourceAsStream("prod.properties")
  }
  properties.load(in)
  in.close()
  val url: String = properties.getProperty("url")
  val user: String = properties.getProperty("user")
  val password: String = properties.getProperty("password")
  val driver: String = properties.getProperty("driver")

  def getProperty(key: String): String = {
    properties.getProperty(key)
  }

  def getMyJDBCConfig(dbtable: String): Map[String, String] = {
    Map(
      "url" -> url,
      "dbtable" -> dbtable,
      "user" -> user,
      "password" -> password,
      "driver" -> driver
    )
  }

  def getMyConn: Connection = {
    Class.forName(driver).newInstance()
    DriverManager.getConnection(url, user, password)
  }

}
