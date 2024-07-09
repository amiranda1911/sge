package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.dto.SchoolClassDTO;
import br.dev.amiranda.sge.domain.models.SchoolClass;
import br.dev.amiranda.sge.domain.models.Teacher;
import br.dev.amiranda.sge.domain.repositories.ClassRepository;
import br.dev.amiranda.sge.domain.repositories.StudentRepository;
import br.dev.amiranda.sge.domain.repositories.TeacherRepository;
import br.dev.amiranda.sge.service.SchoolClassService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final ClassRepository repository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public SchoolClassServiceImpl(ClassRepository repository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public SchoolClassDTO findById(Long id) {

        var schoolClass = repository.findById(id).orElse(null);
        if (schoolClass == null)
            throw new NoSuchElementException("Element not Found");

        return new SchoolClassDTO(schoolClass);
    }

    @Override
    public Set<SchoolClassDTO> findAll() {
        var classes = repository.findAll();
        return classes.stream().map(SchoolClassDTO::new).collect(Collectors.toSet());
    }

    @Override
    public SchoolClassDTO create(SchoolClassDTO schoolClassDTO) {
        if (repository.existsById(schoolClassDTO.id()))
            throw new IllegalArgumentException("Class already exists!");
        return save(schoolClassDTO);
    }

    @Override
    public SchoolClassDTO update(SchoolClassDTO schoolClassDTO) {
        if (!repository.existsById(schoolClassDTO.id()))
            throw new IllegalArgumentException("Class does not exist!");
        return save(schoolClassDTO);
    }

    @Override
    public SchoolClassDTO addTeacher(Long classId, Long teacherId) {
        var teacher = this.teacherRepository.findById(teacherId).orElse(null);
        var schoolClass = this.repository.findById(classId).orElse(null);
        if(teacher == null || schoolClass == null ){
            throw new NoSuchElementException("Element not found");
        }
        schoolClass.setTeacher(teacher);
        this.repository.save(schoolClass);

        return new SchoolClassDTO(schoolClass);
    }

    @Override
    public SchoolClassDTO removeTeacher(Long classId, Long teacherId) {
        return null;
    }

    @Override
    public SchoolClassDTO addStudent(Long classId, Long studentId) {
        var schoolClass = this.repository.findById(classId).orElse(null);
        var student = this.studentRepository.findById(studentId).orElse(null);
        if(schoolClass == null || student == null ){
            throw new NoSuchElementException("Element not found");
        }

        schoolClass.getStudents().add(student);
        this.repository.save(schoolClass);
        return new SchoolClassDTO(schoolClass);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Class does not exist!");
        repository.deleteById(id);
    }


    private SchoolClassDTO save(SchoolClassDTO schoolClassDTO) {
        var schoolClass = new SchoolClass();

        if(schoolClassDTO.teacher() != null) {
            var teacher = new Teacher();
            teacher.setId(schoolClassDTO.teacher().id());
            teacher.setName(schoolClassDTO.teacher().name());
            schoolClass.setTeacher(teacher);
        }

        schoolClass.setId(schoolClassDTO.id());
        schoolClass.setName(schoolClassDTO.name());

        var classCreated = this.repository.save(schoolClass);
        return new SchoolClassDTO(classCreated);
    }
}
