package microservice.basedomains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class EmployeeEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
    @CreatedDate
    private String createdAt;
    @LastModifiedDate
    private String updatedAt;
}
