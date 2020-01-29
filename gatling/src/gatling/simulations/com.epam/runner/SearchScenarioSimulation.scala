package com.epam.runner

import com.epam.gatling.config.{SearchConfig, TotalConfig}
import com.epam.gatling.scenario.SearchScenario
import io.gatling.core.Predef.{atOnceUsers, rampUsers, _}
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration._


class SearchScenarioSimulation extends Simulation {

  private val create_search = SearchScenario.searchScenario
    .inject(
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      atOnceUsers(SearchConfig.search_setup_users_start_rate)
    )

  setUp(create_search).protocols(TotalConfig.total_http_protocol)
}