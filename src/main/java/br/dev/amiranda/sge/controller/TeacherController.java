package br.dev.amiranda.sge.controller;

import br.dev.amiranda.sge.domain.models.Teacher;
import br.dev.amiranda.sge.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher) {
        Teacher teacherCreated = service.create(teacher);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(teacherCreated);
    }

    @PutMapping
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher) {
        Teacher teacherUpdated = service.update(teacher);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(teacherUpdated.getId())
                .toUri();

        return ResponseEntity.created(location).body(teacherUpdated);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Teacher> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
