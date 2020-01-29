package com.epam.gatling.config

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TotalConfig {
  val config = ConfigFactory.load()

  // Total URL:
  val total_url = config.getString("test.total.protocol") + "://" + config.getString("test.total.host") + ":" + config.getString("test.total.server.port") + config.getString("test.total.point.api-v1")

  // Headers:
  val total_headers_0 = Map(
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  // HTTP Protocol
  val total_http_protocol = http
    .baseUrl(TotalConfig.total_url)
    .acceptHeader("application/json")
}