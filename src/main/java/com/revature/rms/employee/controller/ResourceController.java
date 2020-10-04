package com.revature.rms.employee.controller;

import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.services.ResourceMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    private ResourceMetadataService service;

    @Autowired
    public ResourceController(ResourceMetadataService service) {
        this.service = service;
    }

    /**
     * saveData Method: Takes in a ResourceMetadata object as the input.
     * @param data ResourceMetadata object that is inputted and saved
     * @return The newly saved ResourceMetadata object
     */
    @PostMapping(value = "/adddata", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata saveData (@RequestBody ResourceMetadata data)
    {
        return service.save(data);
    }

    /**
     * updateModifier method: The ResourceMetadata object is inputted and the modifier is changed, and updated.
     * The modified object is returned.
     * @param data ResourceMetadata object that is inputted and saved
     * @return updated/modified ResourceMetadata object
     */
    @PostMapping(value = "/updatemodifier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata updateModifier(@RequestBody ResourceMetadata data){
        ResourceMetadata meta = service.findById(data.getResourceId());
        meta.setLastModifier(data.getLastModifier());

        return service.update(meta);
    }

    /**
     * updateAll method: The ResourceMetadata object is inputted and changes are saved.
     * The modified object is returned.
     * @param data ResourceMetadata object that is inputted and saved
     * @return updated/modified ResourceMetadata object
     */
    @PostMapping(value = "/updateall", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata updateAll(@RequestBody ResourceMetadata data){
        ResourceMetadata meta = service.findById(data.getResourceId());
        meta.setLastModifier(data.getLastModifier());
        meta.setResourceOwner(data.getResourceOwner());

        return service.update(meta);
    }

    /**
     * findbyid method: Returns an ResourceMetadata object when the id int matches a record in the database.
     * @param data ResourceMetadata object
     * @return a ResourceMetadata with matching id
     */
    @PostMapping(value = "/findbyid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata findById(@RequestBody ResourceMetadata data){
        int id = data.getResourceId();

        return service.findById(id);
    }

    /**
     * findByCreator Method: Returns an resource metadata object when the creatorId int matches a record in the database.
     * @param data ResourceMetadata object
     * @return a ResourceMetadata with matching id
     */
    @PostMapping(value = "/findbycreator", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata findByCreator(@RequestBody ResourceMetadata data){
        int id = data.getResourceCreator();

        return service.findbyCreator(id);
    }

    /**
     * test method: test endpoint to ensure controller is working
     * @return String saying "resourceController loaded"
     */
    @GetMapping("/test")
    public @ResponseBody String test() {
        return "resourceController loaded";
    }
}
