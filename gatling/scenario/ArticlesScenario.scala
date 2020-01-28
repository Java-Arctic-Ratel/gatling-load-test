package com.epam.gatling.scenario

import com.epam.gatling.config.{ArticlesConfig, TotalConfig}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ArticlesScenario {

  val articlesScenario = scenario("ArticlesScenarioSimulation")
    .repeat(ArticlesConfig.articles_scenario_repeats) {
      feed(ArticlesConfig.articles_id_random_map)

        .exec(http("Request_articles_ID_GET")
          .get("/articles/${articles_id_random}")
          .headers(TotalConfig.total_headers_0)
          .check(
            status.is(200)
          )
        )
        .exec { session => println(session); session }
        .pause(ArticlesConfig.articles_pause_time)
    }
}