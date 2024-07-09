package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.dto.StudentDTO;

import java.util.Set;

public interface StudentService {
    StudentDTO findById(Long id);
    Set<StudentDTO> findAll();
    StudentDTO create(StudentDTO studentDTO);
    StudentDTO update(StudentDTO studentDTO);
    void delete(Long id);
}
