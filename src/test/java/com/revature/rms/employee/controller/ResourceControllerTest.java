package com.revature.rms.employee.controller;

import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.services.ResourceMetadataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *  These tests are for validation that metadata is created and persists when a new employee
 *  is created.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceControllerTest {
    @InjectMocks
    private ResourceController resourceController;

    @Mock
    private ResourceMetadataService resourceMetadataService;

    /**
     * tests saveData by checking a passed in resourceMetadata to what it returns
     */
    @Test
    public void saveResourceWhenCreatingNewEmployee() {
        ResourceMetadata expectedResults = new ResourceMetadata(1, "3.16.2020", 1, "3.16.2020", 1, true);
        ResourceMetadata actualResults = new ResourceMetadata(1, "3.16.2020", 1, "3.16.2020", 1, true);

        when(resourceMetadataService.save(expectedResults)).thenReturn(actualResults);
        assertEquals(resourceController.saveData(expectedResults), actualResults);
    }
}
