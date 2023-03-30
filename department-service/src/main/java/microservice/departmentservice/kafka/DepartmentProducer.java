package microservice.departmentservice.kafka;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.DepartmentEntity;
import microservice.basedomains.event.DepartmentEvent;
import microservice.basedomains.mapper.DepartmentMapper;
import microservice.departmentservice.repository.DepartmentRepository;
import org.apache.kafka.clients.admin.NewTopic;
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
public class DepartmentProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentProducer.class);
    private final KafkaTemplate<String, DepartmentEvent> kafkaTemplate;
    private final DepartmentRepository repository;
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public DepartmentEntity publish(DepartmentEvent event) {
        LOGGER.info(String.format("DEPARTMENT PRODUCER -> Publishing event -> %s", event.toString()));

        Message<DepartmentEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        DepartmentEntity entity = DepartmentMapper.mapToEntity(event.getDepartmentPayload());
        entity = repository.save(entity);

        event.getDepartmentPayload().setId(entity.getId());

        kafkaTemplate.send(message);

        return entity;
    }
}
