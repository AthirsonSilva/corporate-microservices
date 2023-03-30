package microservice.employeeservice.kafka;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.EmployeeEntity;
import microservice.basedomains.event.EmployeeEvent;
import microservice.basedomains.mapper.EmployeeMapper;
import microservice.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeProducer.class);
    private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;
    private final EmployeeRepository repository;
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public EmployeeEntity publish(EmployeeEvent event) {
        LOGGER.info(String.format("EMPLOYEE PRODUCER -> Publishing event -> %s", event.toString()));

        Message<EmployeeEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        EmployeeEntity entity = EmployeeMapper.mapToEntity(event.getEmployeePayload());
        entity = repository.save(entity);

        event.getEmployeePayload().setId(entity.getId());

        LOGGER.info(String.format("EMPLOYEE PRODUCER -> Publishing event -> %s", event));

        kafkaTemplate.send(message);

        return entity;
    }

}
