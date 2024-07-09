package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.dto.SchoolClassDTO;

import java.util.Set;

public interface SchoolClassService {
    SchoolClassDTO findById(Long id);
    Set<SchoolClassDTO> findAll();
    SchoolClassDTO create(SchoolClassDTO student);
    SchoolClassDTO update(SchoolClassDTO student);
    SchoolClassDTO addTeacher(Long classId, Long teacherId);
    SchoolClassDTO removeTeacher(Long classId, Long teacherId);
    SchoolClassDTO addStudent(Long classId, Long studentId);
    void delete(Long id);
}
