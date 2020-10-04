package com.revature.rms.employee.services;


import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.exceptions.ResourcePersistenceException;
import com.revature.rms.employee.repositories.ResourceMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

// TODO Delete this class, resource metadata should be considered as a part of a persistent resource entity
@Service
public class ResourceMetadataService {

    private ResourceMetadataRepository resourceRepo;

    @Autowired
    public ResourceMetadataService(ResourceMetadataRepository repo) {
        super();
        this.resourceRepo = repo;
    }

    /**
     * findById method: Returns an ResourceMetadata object when the id int matches a record in the database.
     * @param id resourceMetadataId int
     * @return a ResourceMetadata with matching id
     * @throws ResourceNotFoundException when a ResourceMetadata is not found
     */
    @Transactional(readOnly = true)
    public ResourceMetadata findById(int id)throws ResourceNotFoundException {
        return resourceRepo.findByResourceId (id);
    }

    /**
     * save Method: Takes in a ResourceMetadata object as the input. The input ResourceMetadata
     * object is tested to ensure that it is not null. If the ResourceMetadata object
     * is null then it will throw a ResourceNotFoundException.
     * Once the ResourceMetadata object passes the test it is then added or persisted
     * to the database.
     * @param newResource ResourceMetadata object that is inputted and saved
     * @return The newly saved ResourceMetadata object
     */
    @Transactional
    public ResourceMetadata save(ResourceMetadata newResource) throws ResourcePersistenceException{
        if(newResource == null){
            throw new ResourceNotFoundException();
        }
        LocalDate today = LocalDate.now();
        String createdDate = today.toString();
        String lastModifiedDate = today.toString();
        newResource.setResourceCreationDateTime(createdDate);
        newResource.setLastModifiedDateTime(lastModifiedDate);
        return resourceRepo.save(newResource);
    }

    /**
     * Update Method: The ResourceMetadata object is inputted and changes are saved.
     * The modified object is returned.
     * @param updatedResource ResourceMetadata object that is inputted
     * @return updated/modified ResourceMetadata object
     */
    @Transactional
    public ResourceMetadata update(ResourceMetadata updatedResource){
        LocalDate today = LocalDate.now();
        String lastModifiedDate = today.toString();
        updatedResource.setLastModifiedDateTime(lastModifiedDate);
        return resourceRepo.save(updatedResource);
    }

    /**
     * findbyCreator Method: Returns an resource metadata object when the creatorId int matches a record in the database.
     * @param id appUserId int
     * @return a ResourceMetadata with matching id
     * @throws ResourceNotFoundException when a ResourceMetadata is not found
     */
    @Transactional(readOnly = true)
    public ResourceMetadata findbyCreator(int id)throws ResourceNotFoundException {
        return resourceRepo.findByResourceCreator(id);
    }
}
