package br.dev.amiranda.sge.domain.repositories;

import br.dev.amiranda.sge.domain.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
