package microservice.organizationservice.kafka;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.OrganizationEntity;
import microservice.basedomains.event.OrganizationEvent;
import microservice.basedomains.mapper.OrganizationMapper;
import microservice.organizationservice.repository.OrganizationRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationProducer.class);
    private final KafkaTemplate<String, OrganizationEvent> kafkaTemplate;
    private final NewTopic topic;
    private final OrganizationRepository repository;

    public OrganizationEntity publish(OrganizationEvent event) {
        LOGGER.info(String.format("ORGANIZATION PRODUCER: -> Producing message -> %s", event.toString()));

        Message<OrganizationEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        OrganizationEntity entity = OrganizationMapper.mapToEntity(event.getOrganizationPayload());
        OrganizationEntity savedEntity = repository.save(entity);

        event.getOrganizationPayload().setId(savedEntity.getId());

        LOGGER.info(String.format("ORGANIZATION PRODUCER: -> Saving organization -> %s", entity));

        kafkaTemplate.send(message);

        return savedEntity;
    }
}
