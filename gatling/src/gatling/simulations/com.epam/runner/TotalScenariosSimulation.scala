package com.epam.gatling.runner

import com.epam.gatling.config.{ArticlesConfig, CategoriesConfig, SearchConfig, TotalConfig}
import com.epam.gatling.scenario.{ArticlesScenario, CategoriesScenario, SearchScenario}
import io.gatling.core.Predef.{atOnceUsers, rampUsers, _}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}

import scala.concurrent.duration._

object TotalScenariosSimulation {

  val searchScenario: ScenarioBuilder = scenario("SearchScenarioSimulation").exec(SearchScenario.searchScenario)
  val articlesScenario: ScenarioBuilder = scenario("ArticlesScenarioSimulation").exec(ArticlesScenario.articlesScenario)
  val categoriesScenario: ScenarioBuilder = scenario("CategoriesScenarioSimulation").exec(CategoriesScenario.categoriesScenario)
}

class TotalScenariosSimulation extends Simulation {

  import TotalScenariosSimulation._

  var scenarios: List[PopulationBuilder] = List()

  if (SearchConfig.search_enabled.toBoolean) {
    scenarios = scenarios :+ searchScenario.inject(
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      atOnceUsers(SearchConfig.search_setup_users_start_rate)
    )
  }

  if (ArticlesConfig.articles_enabled.toBoolean) {
    scenarios = scenarios :+ articlesScenario.inject(
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      atOnceUsers(ArticlesConfig.articles_setup_users_start_rate)
    )
  }

  if (CategoriesConfig.categories_enabled.toBoolean) {
    scenarios = scenarios :+ categoriesScenario.inject(
      rampUsers(CategoriesConfig.categories_setup_users_start_rate) during (CategoriesConfig.categories_setup_duration seconds),
      rampUsers(CategoriesConfig.categories_setup_users_start_rate) during (CategoriesConfig.categories_setup_duration seconds),
      atOnceUsers(CategoriesConfig.categories_setup_users_start_rate)
    )
  }

  setUp(scenarios).protocols(TotalConfig.total_http_protocol)
}