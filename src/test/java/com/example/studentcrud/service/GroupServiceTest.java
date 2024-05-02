package com.example.studentcrud.service;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.repository.GroupRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllGroups() {
        // Arrange
        List<Group> groups = Arrays.asList(new Group(), new Group());
        when(groupRepository.findAll()).thenReturn(groups);

        // Act
        List<Group> result = groupService.listAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveGroup() {
        // Arrange
        Group group = new Group();
        when(groupRepository.save(group)).thenReturn(group);

        // Act
        groupService.save(group);

        // Assert
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testGetGroupById() {
        // Arrange
        long groupId = 1L;
        Group group = new Group();
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        // Act
        Group result = groupService.get(groupId);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteGroup() {
        // Arrange
        long groupId = 1L;

        // Act
        groupService.delete(groupId);

        // Assert
        verify(groupRepository, times(1)).deleteById(groupId);
    }
}
