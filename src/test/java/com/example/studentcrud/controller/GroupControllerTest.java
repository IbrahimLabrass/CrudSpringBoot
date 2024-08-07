package com.example.studentcrud.controller;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.GroupService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private ExportService exportService;

    @InjectMocks
    private GroupController groupController;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGroups() {
        // Arrange
        List<Group> groups = Arrays.asList(new Group(), new Group());
        when(groupService.listAll()).thenReturn(groups);

        // Act
        ResponseEntity<List<Group>> result = groupController.getAllGroups();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(2, result.getBody().size());
    }

    @Test
    public void testAddGroup() {
        // Arrange
        Group group = new Group();
        when(groupService.save(group)).thenReturn(group);

        // Act
        ResponseEntity<Group> result = groupController.addGroup(group);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetGroupById() {
        // Arrange
        long groupId = 1L;
        Group group = new Group();
        when(groupService.get(groupId)).thenReturn(group);

        // Act
        ResponseEntity<Group> result = groupController.getGroupById(groupId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void testGetGroupById_NotFound() {
        // Arrange
        long groupId = 1L;
        when(groupService.get(groupId)).thenReturn(null);

        // Act
        ResponseEntity<Group> result = groupController.getGroupById(groupId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testDeleteGroup() {
        // Arrange
        long groupId = 1L;

        // Act
        ResponseEntity<Void> result = groupController.deleteGroup(groupId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(groupService, times(1)).delete(groupId);
    }

}

