package com.epam.gatling

class PerformanceTest extends Simulation {

  // URL
  val user_url = "http://localhost:8090/api/v1"

  //   Search transtation random
  val transtationList = List("ru", "en", "de", "error")
  val search_transtation_random_map = Iterator.continually(
    Map("search_transtation_random" -> (transtationList(Random.nextInt(transtationList.size)))))

  //   Search query random
  val queryList = List("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "last", "error")
  val search_query_random_map = Iterator.continually(
    Map("search_query_random" -> (queryList(Random.nextInt(queryList.size)))))

  // Articles ID random
  val articles_id_random_map = Iterator.continually(
    Map("articles_id_random" -> Random.nextInt(250)))

  // Categories ID random
  val categories_id_random_map = Iterator.continually(
    Map("categories_id_random" -> Random.nextInt(250)))

  // Categories number random
  val numberList = List("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "last", "error")
  val categories_number_random_map = Iterator.continually(
    Map("categories_number_random" -> (numberList(Random.nextInt(numberList.size)))))

  // Categories number random
  val categories_transtation_random_map = Iterator.continually(
    Map("categories_transtation_random" -> (transtationList(Random.nextInt(transtationList.size)))))

  // SetUp SIMULATION:
  val pause_time = 1
  val scenario_repeats = 2
  val setup_users_start_rate = 50
  val setup_users_end_rate = 1500
  val setup_duration1 = 1
  val setup_duration5 = 5

  val httpProtocol = http
    .baseUrl(user_url)
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/79.0.3945.79 Chrome/79.0.3945.79 Safari/537.36")

  val headers_0 = Map(
    "Proxy-Connection" -> "keep-alive",
    "Upgrade-Insecure-Requests" -> "1")

  val scn = scenario("AndreysTaskSimulation")
    .repeat(scenario_repeats) {
      feed(search_transtation_random_map)
        .feed(search_query_random_map)
        .feed(articles_id_random_map)
        .feed(categories_id_random_map)
        .feed(categories_number_random_map)
        .feed(categories_transtation_random_map)

        .exec(http("Request_search_GET")
          .get("/search?account=${search_query_random}&translation=${search_transtation_random}")
          .headers(headers_0))
        .pause(pause_time microseconds)

        .exec(http("Request_articles_ID_GET")
          .get("/articles/${articles_id_random}")
          .headers(headers_0)
        )
        .pause(pause_time microseconds)

        .exec(http("Request_categories_GET")
          .get("/categories/")
          .headers(headers_0)
        )
        .pause(pause_time microseconds)

        .exec(http("Request_categories_ID_GET")
          .get("/categories/${categories_id_random}")
          .headers(headers_0)
        )
        .pause(pause_time microseconds)

        .exec(http("Request_categories_ALL_GET")
          .get("/categories/${categories_id_random}/all?subcategories=${categories_number_random}&translation=${categories_transtation_random}")
          .headers(headers_0))
        .pause(pause_time microseconds)

    }

  setUp(
    scn.inject(
      rampUsers(setup_users_start_rate) during (setup_duration5 seconds),
      constantUsersPerSec(setup_users_end_rate) during (setup_duration5 seconds) randomized,
      rampUsers(setup_users_end_rate) during (setup_duration5 seconds),
      rampUsers(setup_users_end_rate) during (setup_duration1 seconds),
      atOnceUsers(setup_users_end_rate)
    ).protocols(httpProtocol)
  )
}