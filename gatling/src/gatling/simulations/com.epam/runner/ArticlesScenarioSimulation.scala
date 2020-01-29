package com.epam.runner

import com.epam.gatling.config.{ArticlesConfig, TotalConfig}
import com.epam.gatling.scenario.ArticlesScenario
import io.gatling.core.Predef.{atOnceUsers, rampUsers, _}
import io.gatling.core.scenario.Simulation

import scala.concurrent.duration._

class ArticlesScenarioSimulation extends Simulation {

  private val create_articles = ArticlesScenario.articlesScenario
    .inject(
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      rampUsers(ArticlesConfig.articles_setup_users_start_rate) during (ArticlesConfig.articles_setup_duration seconds),
      atOnceUsers(ArticlesConfig.articles_setup_users_start_rate))

  setUp(create_articles).protocols(TotalConfig.total_http_protocol)
}