package com.epam.gatling.config

import com.typesafe.config.ConfigFactory

import scala.util.Random

object CategoriesConfig {
  val config = ConfigFactory.load()

  // Categories ID random
  val categories_id_random_map = Iterator.continually(
    Map("categories_id_random" -> (Random.nextInt(config.getString("test.categories.max-id").toInt) + 1 + config.getString("test.categories.error-id").toInt)))

  // Categories subcategories random
  val categories_subcategories_string = config.getString("test.categories.subcategories")
  val categories_subcategories_list: List[String] = categories_subcategories_string.split(",").map(_.trim).toList
  val categories_subcategories_random_map = Iterator.continually(
    Map("categories_subcategories_random" -> (categories_subcategories_list(Random.nextInt(categories_subcategories_list.size)))))

  // Categories transtation random
  val categories_transtation_string = config.getString("test.categories.translation")
  val categories_transtation_list: List[String] = categories_transtation_string.split(",").map(_.trim).toList
  val categories_transtation_random_map = Iterator.continually(
    Map("categories_transtation_random" -> (categories_transtation_list(Random.nextInt(categories_transtation_list.size)))))

  // SetUp SIMULATION:
  val categories_pause_time = config.getString("test.simulation.categories.pause-time").toInt // pause between operations in milliseconds
  val categories_scenario_repeats = config.getString("test.simulation.categories.scenario-repeats").toInt // Number of scenario repeats
  val categories_setup_users_start_rate = config.getString("test.simulation.categories.setup-users-start-rate").toInt // MIN users INJECTED in ONE second
  val categories_setup_users_end_rate = config.getString("test.simulation.categories.setup-users-end-rate").toInt // MAX users INJECTED in ONE second
  val categories_setup_duration = config.getString("test.simulation.categories.setup-duration").toInt // Total duration in seconds

  // Enabled
  val categories_enabled = config.getString("test.categories.enabled")
}