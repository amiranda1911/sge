package br.dev.amiranda.sge.controller;

import br.dev.amiranda.sge.domain.models.Student;
import br.dev.amiranda.sge.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Student>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student studentCreated = service.create(student);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentCreated.getId())
                .toUri();

        return ResponseEntity.created(location).body(studentCreated);
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student studentUpdated = service.update(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(studentUpdated.getId())
                .toUri();

        return ResponseEntity.created(location).body(studentUpdated);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
