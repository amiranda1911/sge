package br.dev.amiranda.sge.service.impl;

import br.dev.amiranda.sge.domain.models.Class;
import br.dev.amiranda.sge.domain.repositories.ClassRepository;
import br.dev.amiranda.sge.service.ClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;

    public ClassServiceImpl(ClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class findById(Long id) {

        var _class = repository.findById(id).orElse(null);
        if (_class == null)
            throw new NoSuchElementException("Element not Found");

        return _class;
    }

    @Override
    public List<Class> findAll() {
        var classes = repository.findAll();
        return classes;
    }

    @Override
    public Class create(Class _class) {
        if (repository.existsById(_class.getId()))
            throw new IllegalArgumentException("Class already exists!");
        return save(_class);
    }

    @Override
    public Class update(Class _class) {
        if (!repository.existsById(_class.getId()))
            throw new IllegalArgumentException("Class does not exist!");
        return save(_class);
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id))
            throw new IllegalArgumentException("Class does not exist!");
        repository.deleteById(id);
    }


    private Class save(Class _class) {
        var classCreated = repository.save(_class);
        return classCreated;
    }
}
