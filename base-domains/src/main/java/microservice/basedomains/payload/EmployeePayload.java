package microservice.basedomains.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeePayload {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
}
