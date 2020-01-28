package com.epam.gatling.runner

import com.epam.gatling.config.{ArticlesConfig, SearchConfig}
import com.epam.gatling.scenario.{ArticlesScenario, SearchScenario}
import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef.{atOnceUsers, rampUsers, _}
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration._

class RunnerScenariosSimulation extends Simulation {
  val config = ConfigFactory.load()

  private val create_search = SearchScenario.searchScenario
    .inject(
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      rampUsers(SearchConfig.search_setup_users_start_rate) during (SearchConfig.search_setup_duration seconds),
      atOnceUsers(SearchConfig.search_setup_users_start_rate))

  private val create_articles = ArticlesScenario.articlesScenario
    .inject(
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      atOnceUsers(ArticlesConfig.articles_setup_users_start_rate))

//  var fruits = new ListBuffer[Object]()
//
//    if (SearchConfig.search_enabled.toBoolean) {
//      fruits += create_search
//    }
//
//    if (ArticlesConfig.articles_enabled.toBoolean) {
//      fruits += create_articles
//    }
//
//  val fruitsList: List[Object] = fruits.toList

  if (SearchConfig.search_enabled.toBoolean && ArticlesConfig.articles_enabled.toBoolean) {
    setUp(create_search, create_articles).protocols(SearchConfig.search_http_protocol, ArticlesConfig.articles_http_protocol)
  }
  else if (SearchConfig.search_enabled.toBoolean) {
    setUp(create_search).protocols(SearchConfig.search_http_protocol)
  }
  else if (ArticlesConfig.articles_enabled.toBoolean) {
    setUp(create_articles).protocols(ArticlesConfig.articles_http_protocol)
  }
}