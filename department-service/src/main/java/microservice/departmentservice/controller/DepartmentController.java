package microservice.departmentservice.controller;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.DepartmentEntity;
import microservice.basedomains.entity.OrganizationEntity;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.event.DepartmentEvent;
import microservice.basedomains.payload.DepartmentPayload;
import microservice.departmentservice.kafka.DepartmentProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentProducer producer;
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping
    public ResponseEntity<DepartmentPayload> create(@RequestBody DepartmentPayload request) {
        LOGGER.info(String.format("DEPARTMENT CONTROLLER -> Creating department -> %s", request.toString()));

        DepartmentEvent event = new DepartmentEvent();

        event.setStatus(EventStatus.PENDING);
        event.setDepartmentPayload(request);
        event.setMessage(String.format("Department creation is in %s status", EventStatus.PENDING));

        DepartmentEntity entity = producer.publish(event);

        request.setId(entity.getId());

        return ResponseEntity.ok(request);
    }
}
