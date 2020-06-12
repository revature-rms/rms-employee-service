package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.exceptions.ResourcePersistenceException;
import com.revature.rms.employee.repositories.ResourceMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ResourceMetadataService {

    private final ResourceMetadataRepository resourceRepo;

    @Autowired
    public ResourceMetadataService(ResourceMetadataRepository repo) {
        super();
        this.resourceRepo = repo;
    }

    @Transactional(readOnly = true)
    public ResourceMetadata findById(int id)throws ResourceNotFoundException {
        return resourceRepo.findByResourceId (id);
    }

    @Transactional
    public ResourceMetadata save(ResourceMetadata newResource) throws ResourcePersistenceException{
        LocalDate today = LocalDate.now();
        String createdDate = today.toString();
        String lastModifiedDate = today.toString();
        newResource.setResourceCreationDateTime(createdDate);
        newResource.setLastModifiedDateTime(lastModifiedDate);
        return resourceRepo.save(newResource);
    }

    @Transactional
    public ResourceMetadata update(ResourceMetadata updatedResource){
        LocalDate today = LocalDate.now();
        String lastModifiedDate = today.toString();
        updatedResource.setLastModifiedDateTime(lastModifiedDate);
        return resourceRepo.save(updatedResource);
    }

    @Transactional(readOnly = true)
    public ResourceMetadata findbyCreator(int id)throws ResourceNotFoundException {
        return resourceRepo.findByResourceCreator(id);
    }
}
