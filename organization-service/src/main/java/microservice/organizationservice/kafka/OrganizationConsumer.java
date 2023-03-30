package microservice.organizationservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrganizationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Object event) {
        LOGGER.info(String.format("ORGANIZATION CONSUMER -> Consumed JSON message -> %s", event.toString()));
    }
}
