package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.models.Student;

import java.util.List;

public interface StudentService {
    Student findById(Long id);
    List<Student> findAll();
    Student create(Student student);
    Student update(Student student);
    void delete(Long id);
}
