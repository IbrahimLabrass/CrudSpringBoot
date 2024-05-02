package com.example.studentcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcrud.domain.School;
import com.example.studentcrud.repository.SchoolRepository;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> listAll() {
        return schoolRepository.findAll();
    }

    public School save(School school) {
        schoolRepository.save(school);
        return school;
    }

    public School get(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        schoolRepository.deleteById(id);
    }
}
