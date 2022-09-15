# 1. Overview
Microservices example using SpringBoot framework, AxonServer, Eureka Server
# 2. Run
***Run Axon Server, install https://developer.axoniq.io/download, to tracking request***
move AxonServer-4.6.3 to project
cd  to AxonServer-4.6.3
```
java -jar axonserver.jar
```
Axonserver running in host:8024
</br>
***Run discovery-service***
```
discovery-service running in host: 8761
```
***Run book-service***
```
discovery-service running in host: 9001
```
console database in : http://localhost:9001/h2-console
