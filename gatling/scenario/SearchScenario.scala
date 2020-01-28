package com.epam.gatling.scenario

import com.epam.gatling.config.SearchConfig
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SearchScenario {

  val searchScenario = scenario("SearchScenarioSimulation")
    .repeat(SearchConfig.search_scenario_repeats) {
      feed(SearchConfig.search_transtation_random_map)
        .feed(SearchConfig.search_account_random_map)

        .exec(http("Request_search_GET")
          .get("/search?account=${search_account_random}&translation=${search_transtation_random}")
          .headers(SearchConfig.search_headers_0)
        )
        .exec { session => println(session); session }
        .pause(SearchConfig.search_pause_time)
    }
}