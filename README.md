# Distributed Tracing in Springboot using Sleuth and Zipkin

## TechStack
1. Springboot v2.4.2
2. Spring Cloud Sleuth v3.0.0
3. Zipkin v2.23
4. Postgres DB
5. Apache Kafka v2.7.0

## Read More About,
1. Springboot sleuth - https://spring.io/projects/spring-cloud-sleuth
2. Zipkin - https://zipkin.io/
3. Kafka - https://kafka.apache.org/
4. Postgres - https://www.postgresql.org/
   
## Start Zipkin-Server as docker container
```
docker run -itd --name zipkin-server -p 9411:9411 openzipkin/zipkin
```

## Kafka Download
```
1. Download kafka(2.7.0) from following link - https://kafka.apache.org/downloads
2. Extract the kafka zip from downloads
```
## To start kafka and zookeeper
```
1. Go to kafka-${version} directory
2. Run following command
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
3. Open new terminal and run command to start kafka-server
     .\bin\windows\kafka-server-start.bat .\config\server.properties
```

## To create kafka-topic, Open new terminal and run following command
```
./bin/windows/kafka-topics.bat --create --topic order --bootstrap-server localhost:9092
```

## To start the application
```
1. git clone git@github.com:NirakhRastogi/Springboot-Sleuth-Zipkin.git
2. cd Springboot-Sleuth-Zipkin
3. Run all the applications
```

# Endpoints
## Create Order
```
POST/ http://localhost:8080/order/

Body - 
{
    "customerName": "TestCustomer",
    "deliveryAddress": "TestAddress",
    "deliveryPhoneNumber": 8899887788,
    "orderStatus": "PLACED",
    "paymentStatus": "INCOMPLETE",
    "items": [
        {
            "id": "yu7yuyg",
            "name": "Item1",
            "price": 78.5,
            "quantity": 7
        }
    ]
}
```

## Track Order
```
GET/ http://localhost:8082/order-tracking/{orderId}
```

## Change Order Status
```
GET/ http://localhost:8080/{orderId}/status/{status}
```

## Change Payment Status
```
GET/ http://localhost:8080/{orderId}/payment/status/{status}
```

## Zipkin Screenshots
![Zipkin-All-Trace-Screenshot](/Screenshots/Zipkin-Main-Page.png?raw=true "Zipkin All Trace")
![Zipkin-Create Order-Trace-Screenshot](/Screenshots/Zipkin-Create-order-trace.png?raw=true "Zipkin Create Order Trace")
![Zipkin-Track-Order-Trace-Screenshot](/Screenshots/Zipkin-track-order-trace.png?raw=true "Zipkin TracK Order Trace")