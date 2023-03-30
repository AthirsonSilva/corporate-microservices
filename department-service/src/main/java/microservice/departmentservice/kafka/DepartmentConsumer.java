package microservice.departmentservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DepartmentConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Object event) {
        LOGGER.info(String.format("DEPARTMENT CONSUMER -> Consumed JSON Message -> %s", event.toString()));


    }
}
