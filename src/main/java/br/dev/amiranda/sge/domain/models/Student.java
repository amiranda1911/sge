package br.dev.amiranda.sge.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity(name = "sge_tb_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false)
    private Date birthDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Class> classes = new ArrayList<>();

}
