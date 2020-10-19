package com.revature.rms.employee.dtos;

public class ResourceMetadataDto {

    private int resourceCreator;
    private String resourceCreationDateTime;
    private int lastModifier;
    private String lastModifiedDateTime;
    private int resourceOwner;

    public ResourceMetadataDto() {super();}

    public ResourceMetadataDto(int resourceCreator,
                               String resourceCreationDateTime,
                               int lastModifier,
                               String lastModifiedDateTime,
                               int resourceOwner) {

        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.resourceOwner = resourceOwner;
    }

    public int getResourceCreator() {return resourceCreator;}

    public void setResourceCreator(int resourceCreator) {this.resourceCreator = resourceCreator;}

    public String getResourceCreationDateTime() {return resourceCreationDateTime;}

    public void setResourceCreationDateTime(String resourceCreationDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
    }

    public int getLastModifier() {return lastModifier;}

    public void setLastModifier(int lastModifier) {this.lastModifier = lastModifier;}

    public String getLastModifiedDateTime() {return lastModifiedDateTime;}

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public int getResourceOwner() {return resourceOwner;}

    public void setResourceOwner(int resourceOwner) {this.resourceOwner = resourceOwner;}

}
