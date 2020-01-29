# Gatling

## Task
Make performance testing for given endpoints

## Configuration test
Configuration for performance metrics: [application.properties](src/gatling/resources/application.properties)

## Run test
Run command for articles test:

`gradle gatlingRun-com.epam.runner.ArticlesScenarioSimulation`

Run command for categories test:

`gradle gatlingRun-com.epam.runner.CategoriesScenarioSimulation`

Run command for search test:

`gradle gatlingRun-com.epam.runner.SearchScenarioSimulation`

Run command for mixed test:

`gradle gatlingRun-com.epam.runner.TotalScenariosSimulation`