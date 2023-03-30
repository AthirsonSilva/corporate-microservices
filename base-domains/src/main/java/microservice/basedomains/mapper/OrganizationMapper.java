package microservice.basedomains.mapper;

import microservice.basedomains.entity.OrganizationEntity;
import microservice.basedomains.payload.OrganizationPayload;
import org.springframework.beans.BeanUtils;

public class OrganizationMapper {
    public static OrganizationEntity mapToEntity(OrganizationPayload organizationPayload) {
        OrganizationEntity entity = new OrganizationEntity();
        BeanUtils.copyProperties(organizationPayload, entity);

        return entity;
    }

    public static OrganizationPayload mapToPayload(OrganizationEntity organizationEntity) {
        OrganizationPayload payload = new OrganizationPayload();
        BeanUtils.copyProperties(organizationEntity, payload);

        return payload;
    }
}
