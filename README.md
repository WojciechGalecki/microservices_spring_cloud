# Microservices + Spring Cloud

Examples of request through Zuul API-Gateway: 
- http://localhost:8001/user-service/users/status

## Eureka
- http://localhost:8000
- user: `eureka`
- password: `eureka`

## Spring Cloud Config Server + Spring Cloud Bus + RabbitMQ
- run config-server (fetching configuration from remote private repository)
- run following endpoint to refresh configuration in the services via rabbitmq without restarting:
POST http://localhost:8010/actuator/bus-refresh

## Zipkin - distributed tracing system
- http://localhost:9411/zipkin/

## ELK stack
### Elasticsearch
- http://localhost:9200
- http://localhost:9200/_cat

### Kibana
- http://localhost:5601/app/home
