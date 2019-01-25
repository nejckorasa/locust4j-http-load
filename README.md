# Locust4j http load

An example project for http load testing using [locust4j](https://github.com/myzhan/locust4j), written in Java and Kotlin.

Inspired by/based on [locust4j-http](https://github.com/myzhan/locust4j-http)

### Run 

It acts as Locust slave that connect to master Locust instance.

#### Bash

You can run master using bash scripts in [master-bash](https://github.com/nejckorasa/locust4j-http-load/tree/master/locust4j-http-load/master-bash) directory.

- `start-locust-web.sh` runs Locust master with web interface
- `start-locust.sh` runs Locust master without in no-web mode

See the scripts for more configuration options.

#### Docker

Run Locust master in Docker, see [master-docker](https://github.com/nejckorasa/locust4j-http-load/tree/master/locust4j-http-load/master-docker) directory.

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

### Write your own task objects

See `GetExampleTask` and `PostExampleTask` for examples