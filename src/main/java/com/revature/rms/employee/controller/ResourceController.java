package com.revature.rms.employee.controller;

import com.revature.rms.employee.entities.ResourceMetadata;
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

    @PostMapping(value = "/adddata", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata saveData (@RequestBody ResourceMetadata data)
    {
        return service.save(data);
    }

    @PostMapping(value = "/updatemodifier", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata updateModifier(@RequestBody ResourceMetadata data){
        ResourceMetadata meta = service.findById(data.getResourceId());
        meta.setLastModifier(data.getLastModifier());

        return service.update(meta);
    }

    @PostMapping(value = "/updateall", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata updateAll(@RequestBody ResourceMetadata data){
        ResourceMetadata meta = service.findById(data.getResourceId());
        meta.setLastModifier(data.getLastModifier());
        meta.setResourceOwner(data.getResourceOwner());

        return service.update(meta);
    }

    @PostMapping(value = "/findbyid", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata findById(@RequestBody ResourceMetadata data){
        int id = data.getResourceId();

        return service.findById(id);
    }

    @PostMapping(value = "/findbycreator", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResourceMetadata findByCreator(@RequestBody ResourceMetadata data){
        int id = data.getResourceCreator();

        return service.findbyCreator(id);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        return "resourceController loaded";
    }
}
