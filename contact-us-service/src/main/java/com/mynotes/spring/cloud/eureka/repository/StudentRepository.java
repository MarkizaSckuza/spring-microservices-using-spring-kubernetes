package com.mynotes.spring.cloud.eureka.repository;

import com.mynotes.spring.cloud.eureka.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
