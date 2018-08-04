# To run locally on Mac
```
1. eval $(docker-machine env default)
2. export DOCKER_MACHINE_IP=`docker-machine ip default`
3. docker-compose up --build
```


# To run in production 
```
docker-compose -f docker-compose.yml -f docker-compose-prod.yml up
```

