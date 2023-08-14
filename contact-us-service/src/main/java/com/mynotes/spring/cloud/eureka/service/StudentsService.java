package com.mynotes.spring.cloud.eureka.service;


import com.mynotes.spring.cloud.eureka.entity.Student;
import com.mynotes.spring.cloud.eureka.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(new Student());
    }

    public void saveStudent(final Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}