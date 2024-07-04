package com.talesfunmedia.forohubcompleto.domain.topico;

import com.talesfunmedia.forohubcompleto.domain.curso.Curso;
import com.talesfunmedia.forohubcompleto.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String titulo;
    private LocalDateTime fecha;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "autorId")
    private Usuario autorId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "cursoId")
    private Curso cursoId;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.mensaje = datosRegistroTopico.mensaje();
        this.titulo = datosRegistroTopico.titulo();
        this.fecha = LocalDateTime.now();
        this.estado = false;
        this.autorId = new Usuario(datosRegistroTopico.autorId());
        this.cursoId = new Curso(datosRegistroTopico.cursoId()) ;
    }

    public Topico(Long id) {
        this.id = id;
    }


    public void actualizarTopicos(DatosActualizarTopico datosActualizarTopico) {

        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }

        if (datosActualizarTopico.autorId() != null) {
            this.autorId = new Usuario(datosActualizarTopico.autorId());
        }

        if (datosActualizarTopico.cursoId() != null) {
            this.cursoId = new Curso(datosActualizarTopico.cursoId());
        }

        this.fecha = LocalDateTime.now();

        this.estado = false;
    }

    public void topicoResuelto(DatosResolverTopico datosResolverTopico) {

//        if (this.estado == false) {
//            this.estado = true;
//        } else {
//            this.estado = false;
//        }

        this.estado = true;
        this.fecha = LocalDateTime.now();
    }
}
