package microservice.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import microservice.basedomains.entity.EmployeeEntity;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.event.EmployeeEvent;
import microservice.basedomains.payload.EmployeePayload;
import microservice.employeeservice.kafka.EmployeeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeProducer producer;

    @PostMapping
    public ResponseEntity<EmployeePayload> create(@RequestBody EmployeePayload payload) {
        LOGGER.info(String.format("EMPLOYEE CONTROLLER -> Creating employee -> %s", payload.toString()));

        EmployeeEvent event = new EmployeeEvent();

        event.setEmployeePayload(payload);
        event.setStatus(EventStatus.PENDING);
        event.setMessage(String.format("Employee creation is in %s status", event.getStatus()));

        EmployeeEntity entity = producer.publish(event);

        payload.setId(entity.getId());

        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
}
