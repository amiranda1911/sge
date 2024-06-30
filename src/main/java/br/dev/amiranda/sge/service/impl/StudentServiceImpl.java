package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.models.Student;
import br.dev.amiranda.sge.domain.repositories.StudentRepository;
import br.dev.amiranda.sge.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student findById(Long id) {
        var student = repository.findById(id).orElse(null);
        if(student == null)
            throw new NoSuchElementException("Element not Found");
        return student;
    }

    @Override
    public List<Student> findAll() {
        var students = repository.findAll();
        return students;
    }

    @Override
    public Student create(Student student) {
        if (repository.existsById(student.getId()))
            throw new IllegalArgumentException("Student already exists!");
        return save(student);
    }

    @Override
    public Student update(Student student) {
        if (!repository.existsById(student.getId()))
            throw new IllegalArgumentException("Student does not exist!");
        return save(student);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Student does not exist!");
        repository.deleteById(id);
    }


    private Student save(Student student) {
        var studentCreated = repository.save(student);
        return studentCreated;
    }

}
