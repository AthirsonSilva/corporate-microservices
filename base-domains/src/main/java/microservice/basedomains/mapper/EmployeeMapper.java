package microservice.basedomains.mapper;

import microservice.basedomains.entity.EmployeeEntity;
import microservice.basedomains.payload.EmployeePayload;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    public static EmployeeEntity mapToEntity(EmployeePayload organizationPayload) {
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(organizationPayload, entity);

        return entity;
    }

    public static EmployeePayload mapToPayload(EmployeeEntity organizationEntity) {
        EmployeePayload payload = new EmployeePayload();
        BeanUtils.copyProperties(organizationEntity, payload);

        return payload;
    }
}
