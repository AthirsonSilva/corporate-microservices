package microservice.basedomains.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.payload.EmployeePayload;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEvent {
    private String message;
    private EventStatus status;
    private EmployeePayload employeePayload;
}
