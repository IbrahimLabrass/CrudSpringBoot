package com.example.studentcrud.repository;

import com.example.studentcrud.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
