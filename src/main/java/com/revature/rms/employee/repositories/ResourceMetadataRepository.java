package com.revature.rms.employee.repositories;

import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// TODO Delete this class, resource metadata should be considered as a part of a persistent resource entity
@Repository
public interface ResourceMetadataRepository extends CrudRepository<ResourceMetadata,Integer> {

    /**
     * findByResourceCreator method: The id parameter is passed as the input.
     * A ResourceMetadata is returned when the input appUserId matches a database record.
     * @param id appUserId int
     * @return a ResourceMetadata with matching id
     */
    ResourceMetadata findByResourceCreator(int id);

    /**
     * findByResourceId method: Returns an ResourceMetadata object when the id int matches a record in the database.
     * @param id resourceMetadataId int
     * @return a ResourceMetadata with matching id
     */
    ResourceMetadata findByResourceId(int id);
}