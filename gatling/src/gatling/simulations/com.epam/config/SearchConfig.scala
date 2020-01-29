package com.epam.gatling.config

import com.typesafe.config.ConfigFactory

import scala.util.Random

object SearchConfig {
  val config = ConfigFactory.load()

  // Search transtation random
  val search_transtation_string = config.getString("test.search.translation")
  val search_transtation_list: List[String] = search_transtation_string.split(",").map(_.trim).toList
  val search_transtation_random_map = Iterator.continually(
    Map("search_transtation_random" -> (search_transtation_list(Random.nextInt(search_transtation_list.size)))))

  // Search account random
  val search_account_string = config.getString("test.search.account")
  val search_account_list: List[String] = search_account_string.split(",").map(_.trim).toList
  val search_account_random_map = Iterator.continually(
    Map("search_account_random" -> (search_account_list(Random.nextInt(search_account_list.size)))))

  // SetUp SIMULATION:
  val search_pause_time = config.getString("test.simulation.search.pause-time").toInt // pause between operations in milliseconds
  val search_scenario_repeats = config.getString("test.simulation.search.scenario-repeats").toInt // Number of scenario repeats
  val search_setup_users_start_rate = config.getString("test.simulation.search.setup-users-start-rate").toInt // MIN users INJECTED in ONE second
  val search_setup_users_end_rate = config.getString("test.simulation.search.setup-users-end-rate").toInt // MAX users INJECTED in ONE second
  val search_setup_duration = config.getString("test.simulation.search.setup-duration").toInt // Total duration in seconds

  // Enabled
  val search_enabled = config.getString("test.search.enabled")
}