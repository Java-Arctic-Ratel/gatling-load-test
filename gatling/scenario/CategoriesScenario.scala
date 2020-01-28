package com.epam.gatling.scenario

import com.epam.gatling.config.{CategoriesConfig, TotalConfig}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CategoriesScenario {

  val categoriesScenario = scenario("CategoriesScenarioSimulation")
    .repeat(CategoriesConfig.categories_scenario_repeats) {
      feed(CategoriesConfig.categories_id_random_map)
        .feed(CategoriesConfig.categories_subcategories_random_map)
        .feed(CategoriesConfig.categories_transtation_random_map)

        .exec(http("Request_categories_GET")
          .get("/categories/")
          .headers(TotalConfig.total_headers_0)
          .check(
            status.is(200)
          )
        )
        .pause(CategoriesConfig.categories_pause_time)

        .exec(http("Request_categories_ID_GET")
          .get("/categories/${categories_id_random}")
          .headers(TotalConfig.total_headers_0)
          .check(
            status.is(200)
          )
        )
        .pause(CategoriesConfig.categories_pause_time)

        .exec(http("Request_categories_ALL_GET")
          .get("/categories/${categories_id_random}/all?subcategories=${categories_subcategories_random}&translation=${categories_transtation_random}")
          .headers(TotalConfig.total_headers_0)
          .check(
            status.is(200)
          )
        )
        .pause(CategoriesConfig.categories_pause_time)
    }
}