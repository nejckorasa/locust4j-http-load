FROM python:alpine3.7

RUN apk -U add ca-certificates python python-dev py-pip build-base && \
    pip install --upgrade pip && \
    pip install locustio pyzmq && \
    apk del python-dev && \
    rm -r /var/cache/apk/* && \
    mkdir /locust

WORKDIR /locust
ADD . /locust

RUN apk add --update --no-cache g++ gcc libxslt-dev
RUN test -f requirements.txt && pip install -r requirements.txt; exit 0

EXPOSE 8089 5557 5558
ENTRYPOINT [ "/usr/local/bin/locust" ]
