Kafka Commands:


.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
.\bin\windows\kafka-server-start.bat .\config\server.properties


--to list all topics
kafka-topics.bat --list --zookeeper localhost:2181

--to create a topic
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic customer-region-1

To know group ID: kafka-topics.bat --create --zookeeper localhost:9092 --replication-factor 1 --partitions 1 --topic customer-region-1

--to start producer
kafka-console-producer.bat --broker-list localhost:9092 --topic customer-region-1

--to start consumer
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --describe --all-groups
 
