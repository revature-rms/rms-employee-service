package com.revature.rms.employee.dtos;

// TODO this class should be renamed to "ResourceMetadataDto"
// TODO this should include the resource creation time and the last modified time
public class ResourceCreds {
    private int resourceCreator;
    private int lastModifier;
    private int resourceOwner;

    public ResourceCreds() {super();
    }

    public ResourceCreds(int resourceCreator, int lastModifier, int resourceOwner) {

        this.resourceCreator = resourceCreator;
        this.lastModifier = lastModifier;
        this.resourceOwner = resourceOwner;
    }

    public int getResourceCreator() {
        return resourceCreator;
    }

    public void setResourceCreator(int resourceCreator) {
        this.resourceCreator = resourceCreator;
    }

    public int getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(int lastModifier) {
        this.lastModifier = lastModifier;
    }

    public int getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(int resourceOwner) {
        this.resourceOwner = resourceOwner;
    }
}
