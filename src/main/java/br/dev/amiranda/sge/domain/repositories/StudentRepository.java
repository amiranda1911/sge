package br.dev.amiranda.sge.domain.repositories;

import br.dev.amiranda.sge.domain.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
