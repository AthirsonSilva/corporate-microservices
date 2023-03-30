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
@Document(collection = "organizations")
public class OrganizationEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String code;
    @CreatedDate
    private String createdAt;
    @LastModifiedDate
    private String updatedAt;
}
