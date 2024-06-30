package br.dev.amiranda.sge.service;

import br.dev.amiranda.sge.domain.models.Class;

import java.util.List;

public interface ClassService {
    Class findById(Long id);
    List<Class> findAll();
    Class create(Class student);
    Class update(Class student);
    void delete(Long id);
}
