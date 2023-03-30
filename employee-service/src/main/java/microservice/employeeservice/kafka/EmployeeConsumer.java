package microservice.employeeservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Object event) {
        LOGGER.info(String.format("EMPLOYEE CONSUMER -> Consumed JSON Message -> %s", event.toString()));
    }
}
