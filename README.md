[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)]( https://twitter.com/home?status=Great%20%23Locust4j%20http%20load%20test%20example%20in%20%23Java%20and%20%23Kotlin%20https%3A//github.com/nejckorasa/locust4j-http-load%20%23github%20%23code%20%23development%20%23developers%20%23programming%20%23programmers%20%23testing%20%23software%20%23developing%20via%20%40nejckorasa)

# Locust4j http load

An example project for http load testing using [locust4j](https://github.com/myzhan/locust4j), written in Java and Kotlin.

This is not a library of any sort, just an example on how to use Locust and Locust4j to write load tests for more complex scenarios (dependent subsequent requests, ...)

Inspired by/based on [locust4j-http](https://github.com/myzhan/locust4j-http)

## Build & Run

App acts as Locust slave that connects to Locust master, hence you need to also run master.

### Run slave

- Build and package with Maven:
```
$ git clone https://github.com/nejckorasa/locust4j-http-load
$ cd locust4j-http-load
$ mvn package
```
- Run jar:
```
$ java -jar locust4j-load-http-1.0-SNAPSHOT-fat.jar
```

Jar accepts arguments to configure Locust, see [ConfigurationContext](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/java/io/github/nejckorasa/locust4j/http/config/ConfigurationContext.java). 

### Run master (Bash)

You can run master using bash scripts in [master-bash](https://github.com/nejckorasa/locust4j-http-load/tree/master/master-bash) directory.

- [start-locust-web.sh](https://github.com/nejckorasa/locust4j-http-load/blob/master/dist/start-locust-web.sh) runs Locust master with web interface
- [start-locust.sh](https://github.com/nejckorasa/locust4j-http-load/blob/master/dist/start-locust.sh) runs Locust master without in no-web mode

See the scripts for more configuration options.

### Run master (Docker)

Run Locust master in Docker, see [master-docker](https://github.com/nejckorasa/locust4j-http-load/tree/master/master-docker) directory.

Build image:
```
$ docker build -t master-locust .
```

Run with web:
```
$ docker run --rm --name master-locust -p 8089:8089 -p 5557:5557 -p 5558:5558 master-locust \
    --master \
    --master-bind-port=5557
```

Run with no-web (for 180s):
```
$ docker run --rm --name master-locust -p 8089:8089 -p 5557:5557 -p 5558:5558 -v $PWD:/locust master-locust \
    --master \
    --master-bind-port=5557 \
    --run-time=180s \
    --clients=10 \
    --hatch-rate=1 \
    --no-web \
    --print-stats \
    --expect-slaves=1 \
    --csv-base-name=example
```

## Write your own task objects

See [GetExampleTask](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/kotlin/io/github/nejckorasa/locust4j/http/task/GetExampleTask.kt) and [PostExampleTask](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/kotlin/io/github/nejckorasa/locust4j/http/task/PostExampleTask.kt) for examples


## Http

Http requests are made using [HttpRequests](https://github.com/nejckorasa/locust4j-http-load/blob/master/src/main/kotlin/io/github/nejckorasa/locust4j/http/HttpRequests.kt), with options to record or not record the request with Locust.

