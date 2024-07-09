package br.dev.amiranda.sge.domain.dto;

import br.dev.amiranda.sge.domain.models.SchoolClass;
import br.dev.amiranda.sge.domain.models.Teacher;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record SchoolClassDTO(Long id, String name, TeacherDTO teacher, Set<StudentDTO> students) {
    public SchoolClassDTO(SchoolClass schoolClass) {
        this(
                schoolClass.getId(),
                schoolClass.getName(),
                schoolClass.getTeacher() == null ? null :  new TeacherDTO(schoolClass.getTeacher()),
                schoolClass.getStudents() == null ? new HashSet<>() : schoolClass.getStudents().stream().map(StudentDTO::new)
                        .collect(Collectors.toSet())
        );
    }
}
