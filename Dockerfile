# use a node base image
FROM node:7-onbuild

# set maintainer
LABEL maintainer "dhusar200@caledonian.ac.uk"

# set a health check
HEALTHCHECK --interval=5s \
            --timeout=5s \
            CMD curl -f http://23.96.122.79:8080 || exit 1

# tell docker what port to expose
EXPOSE 8080
