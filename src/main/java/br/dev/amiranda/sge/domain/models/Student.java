package br.dev.amiranda.sge.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sge_tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false)
    private Date birthDate;



    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private Set<SchoolClass> classes = new HashSet<>();

}
