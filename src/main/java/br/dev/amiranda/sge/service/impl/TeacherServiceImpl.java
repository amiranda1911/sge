package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.models.Teacher;
import br.dev.amiranda.sge.domain.repositories.TeacherRepository;
import br.dev.amiranda.sge.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Teacher findById(Long id) {
        var teacher = repository.findById(id).orElse(null);
        if(teacher == null)
            throw new NoSuchElementException("Element not Found");
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        var teachers = repository.findAll();
        return teachers;
    }

    @Override
    public Teacher create(Teacher teacher) {
        if (repository.existsById(teacher.getId()))
            throw new IllegalArgumentException("Teacher already exists!");
        return save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (!repository.existsById(teacher.getId()))
            throw new IllegalArgumentException("Teacher does not exist!");
        return save(teacher);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Teacher does not exist!");
        repository.deleteById(id);
    }


    private Teacher save(Teacher teacher) {
        var teacherCreated = repository.save(teacher);
        return teacherCreated;
    }
}
