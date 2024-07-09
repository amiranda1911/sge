package br.dev.amiranda.sge.domain.repositories;

import br.dev.amiranda.sge.domain.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<SchoolClass, Long> {

}
