package microservice.departmentservice.repository;

import microservice.basedomains.entity.DepartmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<DepartmentEntity, String> {
}
