# Locust4j http load

An example project for http load testing using [locust4j](https://github.com/myzhan/locust4j), written in Java and Kotlin.

Inspired by/based on [locust4j-http](https://github.com/myzhan/locust4j-http)

### Run 

It acts as Locust slave that connect to master Locust instance.
You can run master using scripts in [dist](https://github.com/nejckorasa/locust4j-http-load/tree/master/dist) directory:

- `start-locust-web.sh` runs Locust master with web interface
- `start-locust.sh` runs Locust master without in no-web mode

See the scripts for more configuration options.

### Write your own task objects

See [GetExampleTask](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/kotlin/io/github/nejckorasa/locust4j/http/task/GetExampleTask.kt) and [PostExampleTask](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/kotlin/io/github/nejckorasa/locust4j/http/task/PostExampleTask.kt) for examples
