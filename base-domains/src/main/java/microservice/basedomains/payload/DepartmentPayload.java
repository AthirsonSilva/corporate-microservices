package microservice.basedomains.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentPayload {
    private String id;
    private String name;
    private String description;
    private String code;
    private String organizationCode;
}
