package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher findById(Long id);
    List<Teacher> findAll();
    Teacher create(Teacher student);
    Teacher update(Teacher student);
    void delete(Long id);
}
