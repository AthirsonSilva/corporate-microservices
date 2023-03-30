package microservice.basedomains.mapper;

import microservice.basedomains.entity.DepartmentEntity;
import microservice.basedomains.payload.DepartmentPayload;
import org.springframework.beans.BeanUtils;

public class DepartmentMapper {
    public static DepartmentEntity mapToEntity(DepartmentPayload organizationPayload) {
        DepartmentEntity entity = new DepartmentEntity();
        BeanUtils.copyProperties(organizationPayload, entity);

        return entity;
    }

    public static DepartmentPayload mapToPayload(DepartmentEntity organizationEntity) {
        DepartmentPayload payload = new DepartmentPayload();
        BeanUtils.copyProperties(organizationEntity, payload);

        return payload;
    }
}
