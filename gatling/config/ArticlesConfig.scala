package com.epam.gatling.config

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object ArticlesConfig {
  val config = ConfigFactory.load()

  val articles_user_url = config.getString("test.articles.protocol") + "://" + config.getString("test.articles.host") + ":" + config.getString("test.articles.server.port") + config.getString("test.articles.point.api-v1")

  // Articles ID random
  val articles_id_random_map = Iterator.continually(
    Map("articles_id_random" -> (Random.nextInt(config.getString("test.articles.max-id").toInt) + 1 + config.getString("test.articles.error-id").toInt)))

  // SetUp SIMULATION:
  val articles_pause_time = config.getString("test.simulation.articles.pause-time").toInt // pause between operations in milliseconds
  val articles_scenario_repeats = config.getString("test.simulation.articles.scenario-repeats").toInt // Number of scenario repeats
  val articles_setup_users_start_rate = config.getString("test.simulation.articles.setup-users-start-rate").toInt // MIN users INJECTED in ONE second
  val articles_setup_users_end_rate = config.getString("test.simulation.articles.setup-users-end-rate").toInt // MAX users INJECTED in ONE second
  val articles_setup_duration = config.getString("test.simulation.articles.setup-duration").toInt // Total duration in seconds

  // HTTP Protocol
  val articles_http_protocol = http
    .baseUrl(articles_user_url)
    .acceptHeader("application/json")

  // HEADER:
  val articles_headers_0 = Map(
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  // Enabled
  val articles_enabled = config.getString("test.articles.enabled")
}