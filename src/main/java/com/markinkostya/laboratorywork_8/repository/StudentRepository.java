package com.markinkostya.laboratorywork_8.repository;

import com.markinkostya.laboratorywork_8.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
