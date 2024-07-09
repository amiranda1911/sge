package br.dev.amiranda.sge.domain.dto;

import br.dev.amiranda.sge.domain.models.Student;

import java.util.Date;

public record StudentDTO(Long id, String name, Date birthDate) {
    public StudentDTO(Student student) {
        this(
        student.getId(),
        student.getName(),
        student.getBirthDate()
        );
    }
}