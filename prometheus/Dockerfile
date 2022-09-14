FROM prom/prometheus:latest
MAINTAINER Mario Hercog <mario.hercog@gmail.com>

COPY prometheus.yml /prometheus/prometheus.yml

EXPOSE 9090

ENTRYPOINT ["/bin/prometheus"]

HEALTHCHECK --interval=30s --timeout=30s CMD wget --spider http://localhost:9090 || exit 1
