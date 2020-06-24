package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.repositories.ResourceMetadataRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ResourceMetadataServiceTest {
    @Mock
    ResourceMetadataRepository metadataRepository;
    @InjectMocks
    ResourceMetadataService metadataService;

    /**
     * testFindMetadataByValidId ensures ResourceMetadataService().findById() returns an existing ResourceMetadata object.
     */
    @Test
    public void testFindMetadataByValidId() {
        //Arrange
        ResourceMetadata expectedMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        //Act
        when(metadataRepository.findByResourceId(1)).thenReturn(expectedMetadata);
        ResourceMetadata actualMetadata = metadataService.findById(1);
        //Assert
        assertEquals(expectedMetadata, actualMetadata);
    }

    /**
     * testSaveMetadataWithValidMetadata ensures ResourceMetadataService().save is functioning properly with valid input.
     * A non-null metadata object should be returned.
     */
    @Test
    public void testSaveMetadataWithValidMetadata() {
        //Arrange
        ResourceMetadata testMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        ResourceMetadata expectedMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        //Act
        when(metadataRepository.save(testMetadata)).thenReturn(expectedMetadata);
        ResourceMetadata actualMetadata = metadataService.save(testMetadata);
        //Assert
        assertEquals(expectedMetadata, actualMetadata);
    }

    /**
     * testUpdateMetadataWithValidMetadata ensures ResourceMetadataService().update is functioning properly with valid input.
     * A non-null metadata object should be returned.
     */
    @Test
    public void testUpdateMetadataWithValidMetadata() {
        //Arrange
        ResourceMetadata testMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        ResourceMetadata expectedMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        //Act
        when(metadataRepository.save(testMetadata)).thenReturn(expectedMetadata);
        ResourceMetadata actualMetadata = metadataService.update(testMetadata);
        //Assert
        assertEquals(expectedMetadata, actualMetadata);
    }

    /**
     * testFindMetadataByValidCreatorId ensures ResourceMetadataService().findbyCreator() returns an existing ResourceMetadata object.
     */
    @Test
    public void testFindMetadataByValidCreatorId() {
        //Arrange
        ResourceMetadata expectedMetadata = new ResourceMetadata(1,
                1, "Then",
                1, "Now",
                1, true);
        //Act
        when(metadataRepository.findByResourceCreator(1)).thenReturn(expectedMetadata);
        ResourceMetadata actualMetadata = metadataService.findbyCreator(1);
        //Assert
        assertEquals(expectedMetadata, actualMetadata);
    }

}
