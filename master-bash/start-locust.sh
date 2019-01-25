#!/usr/bin/env bash

# start locust master (no web interface)

nohup locust -f bin/dummy.py --master --master-bind-host=127.0.0.1 \
    --run-time=180s \
    --clients=10 \
    --hatch-rate=1 \
    --no-web \
    --print-stats \
    --expect-slaves=1 \
    --csv-base-name=example \
    --master-bind-port=5557 \
    --web-port=10001 &
