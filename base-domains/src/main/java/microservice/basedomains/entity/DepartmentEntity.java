package microservice.basedomains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "departments")
public class DepartmentEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String code;
    private String organizationCode;
    @CreatedDate
    private String createdAt;
    @LastModifiedDate
    private String updatedAt;
}
