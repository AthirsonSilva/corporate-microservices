package microservice.basedomains.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.basedomains.enums.EventStatus;
import microservice.basedomains.payload.OrganizationPayload;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationEvent {
    private String message;
    private EventStatus status;
    private OrganizationPayload organizationPayload;
}
