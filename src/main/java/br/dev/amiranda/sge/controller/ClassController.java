package br.dev.amiranda.sge.controller;

import br.dev.amiranda.sge.domain.models.Class;
import br.dev.amiranda.sge.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Class>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Class> save(@RequestBody Class _class) {
        Class classCreated = service.create(_class);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(classCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(classCreated);
    }

    @PutMapping
    public ResponseEntity<Class> update(@RequestBody Class _class) {
        Class classUpdated = service.update(_class);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(classUpdated.getId())
                .toUri();

        return ResponseEntity.created(location).body(classUpdated);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Class> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
