package com.example.studentcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.repository.GroupRepository;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> listAll() {
        return groupRepository.findAll();
    }

    public Group save(Group group) {
        groupRepository.save(group);
        return group;
    }

    public Group get(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
