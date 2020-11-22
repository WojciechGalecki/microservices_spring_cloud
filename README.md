# Microservices + Spring Cloud

Examples of request through Zuul API-Gateway: 
- http://localhost:8001/user-service/users/status

# Spring Cloud Config Server + Spring Cloud Bus + RabbitMQ
- run config-server (fetching configuration from remote private repository)
- run following endpoint to refresh configuration in the services via rabbitmq without restarting:
POST http://localhost:8010/actuator/bus-refresh

# Zipkin - distributed tracing system
- http://localhost:9411/zipkin/



