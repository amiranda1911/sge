package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.dto.TeacherDTO;
import br.dev.amiranda.sge.domain.models.Teacher;
import br.dev.amiranda.sge.domain.repositories.TeacherRepository;
import br.dev.amiranda.sge.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public TeacherDTO findById(Long id) {
        var teacher = repository.findById(id).orElse(null);
        if(teacher == null)
            throw new NoSuchElementException("Element not Found");
        return new TeacherDTO(teacher);
    }

    @Override
    public Set<TeacherDTO> findAll() {
        var teachers = repository.findAll();
        return teachers.stream().map(TeacherDTO::new)
                .collect(Collectors.toSet());
    }

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) {
        if (repository.existsById(teacherDTO.id()))
            throw new IllegalArgumentException("Teacher already exists!");
        return save(teacherDTO);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        if (!repository.existsById(teacherDTO.id()))
            throw new IllegalArgumentException("Teacher does not exist!");
        return save(teacherDTO);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Teacher does not exist!");
        repository.deleteById(id);
    }


    private TeacherDTO save(TeacherDTO teacherDTO) {
        var teacher = new Teacher();
        teacher.setId(teacherDTO.id());
        teacher.setName(teacherDTO.name());

        var teacherCreated = repository.save(teacher);
        return new TeacherDTO(teacherCreated);
    }
}
