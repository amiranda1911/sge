package br.dev.amiranda.sge.domain.dto;

import br.dev.amiranda.sge.domain.models.Teacher;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record TeacherDTO(Long id, String name) {
    public TeacherDTO (Teacher teacher) {
        this (
            teacher.getId(),
            teacher.getName()
        );
    }
}
