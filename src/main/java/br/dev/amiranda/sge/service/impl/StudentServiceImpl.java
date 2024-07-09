package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.dto.StudentDTO;
import br.dev.amiranda.sge.domain.models.Student;
import br.dev.amiranda.sge.domain.repositories.StudentRepository;
import br.dev.amiranda.sge.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public StudentDTO findById(Long id) {
        var student = repository.findById(id).orElse(null);
        if(student == null)
            throw new NoSuchElementException("Element not Found");
        return new StudentDTO(student);
    }

    @Override
    public Set<StudentDTO> findAll() {
        var students = repository.findAll();

        return students.stream().map(
                StudentDTO::new
        ).collect(Collectors.toSet());
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        if (repository.existsById(studentDTO.id()))
            throw new IllegalArgumentException("Student already exists!");
        return save(studentDTO);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        if (!repository.existsById(studentDTO.id()))
            throw new IllegalArgumentException("Student does not exist!");
        return save(studentDTO);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Student does not exist!");
        repository.deleteById(id);
    }


    private StudentDTO save(StudentDTO studentDTO) {
        var student = new Student();
        student.setId(studentDTO.id());
        student.setName(studentDTO.name());
        student.setBirthDate(studentDTO.birthDate());
        var studentCreated = repository.save(student);
        return new StudentDTO(studentCreated);
    }

}
