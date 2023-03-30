package microservice.basedomains.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.payload.Department;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentEvent {
    private String message;
    private EventStatus status;
    private Department department;
}
