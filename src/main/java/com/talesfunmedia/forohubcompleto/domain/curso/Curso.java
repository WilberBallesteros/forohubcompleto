package com.talesfunmedia.forohubcompleto.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre();
        this.categoria = datosRegistroCurso.categoria();
    }

    public Curso(Long id) {
        this.id = id;
    }

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {

        if (datosActualizarCurso.nombre() != null) {
            this.nombre = datosActualizarCurso.nombre();
        }

        if (datosActualizarCurso.categoria() != null) {
            this.categoria = datosActualizarCurso.categoria();
        }
    }
}
