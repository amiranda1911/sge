package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.dto.TeacherDTO;

import java.util.Set;

public interface TeacherService {
    TeacherDTO findById(Long id);
    Set<TeacherDTO> findAll();
    TeacherDTO create(TeacherDTO teacherDTO);
    TeacherDTO update(TeacherDTO teacherDTO);
    void delete(Long id);
}
