package com.revature.rms.employee.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ResourceMetadata {

    @Column(nullable=false)
    private int resourceCreator;

    @Column(nullable=false)
    private String resourceCreationDateTime = LocalDateTime.now().toString();

    @Column(nullable=false)
    private int lastModifier;

    @Column(nullable=false)
    private String lastModifiedDateTime = LocalDateTime.now().toString();

    @Column(nullable=false)
    private int resourceOwner;

    @Column
    private boolean currentlyActive;

    public ResourceMetadata() {
        super();
    }

    public ResourceMetadata(int resourceCreator, String resourceCreationDateTime, int lastModifier, String lastModifiedDateTime, int resourceOwner, boolean currentlyActive) {
        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = resourceCreationDateTime;
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.resourceOwner = resourceOwner;
        this.currentlyActive = currentlyActive;
    }

    public ResourceMetadata(int resourceCreator, int lastModifier, int resourceOwner) {
        this.resourceCreator = resourceCreator;
        this.resourceCreationDateTime = LocalDateTime.now().toString();
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = LocalDateTime.now().toString();
        this.resourceOwner = resourceOwner;
        this.currentlyActive = true;
    }

    public ResourceMetadata(int lastModifier, int resourceOwner) {
        this.lastModifier = lastModifier;
        this.lastModifiedDateTime = LocalDateTime.now().toString();
        this.resourceOwner = resourceOwner;
    }

    public int getResourceCreator() {
        return resourceCreator;
    }

    public void setResourceCreator(int resourceCreator) {
        this.resourceCreator = resourceCreator;
    }

    public String getResourceCreationDateTime() {
        return resourceCreationDateTime;
    }

    public void setResourceCreationDateTime(String resourceCreationDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
    }

    public int getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(int lastModifier) {
        this.lastModifier = lastModifier;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public int getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(int resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(boolean isActive) {
        this.currentlyActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceMetadata that = (ResourceMetadata) o;
        return resourceCreator == that.resourceCreator &&
                lastModifier == that.lastModifier &&
                resourceOwner == that.resourceOwner &&
                currentlyActive == that.currentlyActive &&
                Objects.equals(resourceCreationDateTime, that.resourceCreationDateTime) &&
                Objects.equals(lastModifiedDateTime, that.lastModifiedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceCreator, resourceCreationDateTime, lastModifier, lastModifiedDateTime, resourceOwner, currentlyActive);
    }

    @Override
    public String toString() {
        return "ResourceMetadata{" +
                ", resourceCreator=" + resourceCreator +
                ", resourceCreationDateTime='" + resourceCreationDateTime + '\'' +
                ", lastModifier=" + lastModifier +
                ", lastModifiedDateTime='" + lastModifiedDateTime + '\'' +
                ", resourceOwner=" + resourceOwner +
                ", isActive=" + currentlyActive +
                '}';
    }
}
