package br.dev.amiranda.sge.domain.dto;

import br.dev.amiranda.sge.domain.models.SchoolClass;
import br.dev.amiranda.sge.domain.models.Student;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record StudentDTO(Long id, String name, Date birthDate, Set<SchoolClassDTO> classes) {
    public StudentDTO(Student student) {
        this(
        student.getId(),
        student.getName(),
        student.getBirthDate(),
        student.getClasses() == null? new HashSet<>() : student.getClasses().stream().map(SchoolClassDTO::new).collect(Collectors.toSet())
        );
    }
}
