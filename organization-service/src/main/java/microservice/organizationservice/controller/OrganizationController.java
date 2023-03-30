package microservice.organizationservice.controller;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.OrganizationEntity;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.event.OrganizationEvent;
import microservice.basedomains.payload.OrganizationPayload;
import microservice.organizationservice.kafka.OrganizationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationProducer producer;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @PostMapping
    public ResponseEntity<OrganizationPayload> create(@RequestBody OrganizationPayload request) {
        LOGGER.info(String.format("ORGANIZATION CONTROLLER: -> Creating organization -> %s", request.toString()));

        OrganizationEvent event = new OrganizationEvent();

        event.setStatus(EventStatus.PENDING);
        event.setOrganizationPayload(request);
        event.setMessage(String.format("Organization creation is in %s status", event.getStatus()));

        OrganizationEntity entity = producer.publish(event);

        request.setId(entity.getId());

        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
