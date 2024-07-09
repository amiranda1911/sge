package br.dev.amiranda.sge.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sge_tb_class")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne
    private Teacher teacher;

    @JsonIgnoreProperties("classes")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sge_tb_student_class",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )

    private Set<Student> students = new HashSet<>();


}
