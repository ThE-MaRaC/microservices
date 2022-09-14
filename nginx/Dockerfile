FROM nginx:stable
MAINTAINER Mario Hercog <mario.hercog@gmail.com>

COPY nginx.conf /etc/nginx/nginx.conf
COPY conf.d/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost || exit 1
