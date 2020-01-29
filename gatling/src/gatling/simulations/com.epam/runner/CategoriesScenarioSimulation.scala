package com.epam.runner

import com.epam.gatling.config.{CategoriesConfig, TotalConfig}
import com.epam.gatling.scenario.CategoriesScenario
import io.gatling.core.Predef.{atOnceUsers, rampUsers, _}
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration._

class CategoriesScenarioSimulation extends Simulation {

  private val create_categories = CategoriesScenario.categoriesScenario
    .inject(
      rampUsers(CategoriesConfig.categories_setup_users_start_rate) during (CategoriesConfig.categories_setup_duration seconds),
      rampUsers(CategoriesConfig.categories_setup_users_start_rate) during (CategoriesConfig.categories_setup_duration seconds),
      atOnceUsers(CategoriesConfig.categories_setup_users_start_rate)
    )

  setUp(create_categories).protocols(TotalConfig.total_http_protocol)
}