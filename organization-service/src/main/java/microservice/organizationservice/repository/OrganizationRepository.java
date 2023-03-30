package microservice.organizationservice.repository;

import microservice.basedomains.entity.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends MongoRepository<OrganizationEntity, String> {
}
