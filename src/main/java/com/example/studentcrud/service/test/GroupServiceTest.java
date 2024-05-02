package com.example.studentcrud.service.test;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.repository.GroupRepository;
import com.example.studentcrud.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAllGroups() {
        List<Group> groups = Arrays.asList(
                new Group(1L, "Group A", "Description A"),
                new Group(2L, "Group B", "Description B")
        );
        when(groupRepository.findAll()).thenReturn(groups);

        List<Group> actualGroups = groupService.listAll();

        assertEquals(2, actualGroups.size());
        assertEquals("Group A", actualGroups.get(0).getGroupName());
        assertEquals("Description A", actualGroups.get(0).getDescription());
        assertEquals("Group B", actualGroups.get(1).getGroupName());
        assertEquals("Description B", actualGroups.get(1).getDescription());
    }

    @Test
    public void testSaveGroup() {
        Group group = new Group(1L, "Group A", "Description A");
        when(groupRepository.save(group)).thenReturn(group);

        Group savedGroup = groupService.save(group);

        assertEquals("Group A", savedGroup.getGroupName());
        assertEquals("Description A", savedGroup.getDescription());
    }

    @Test
    public void testGetGroupById() {
        Group group = new Group(1L, "Group A", "Description A");
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        Group actualGroup = groupService.get(1L);

        assertEquals("Group A", actualGroup.getGroupName());
        assertEquals("Description A", actualGroup.getDescription());
    }

    @Test
    public void testDeleteGroup() {
        groupService.delete(1L);
        verify(groupRepository, times(1)).deleteById(1L);
    }
}
