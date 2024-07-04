package com.talesfunmedia.forohubcompleto.domain.respuesta;

import com.talesfunmedia.forohubcompleto.domain.curso.Curso;
import com.talesfunmedia.forohubcompleto.domain.topico.DatosResolverTopico;
import com.talesfunmedia.forohubcompleto.domain.topico.Topico;
import com.talesfunmedia.forohubcompleto.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "topicoId")
    private Topico topicoId;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "autorId")
    private Usuario autorId;
    private Boolean solucion;

    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta) {

        this.mensaje = datosRegistroRespuesta.mensaje();
        this.topicoId = new Topico(datosRegistroRespuesta.topicoId());
        this.fechaCreacion = LocalDateTime.now();
        this.autorId = new Usuario(datosRegistroRespuesta.autorId());
        this.solucion = false;
    }

    public void actualizarRespuestas(DatosActualizarRespuesta datosActualizarRespuesta) {

        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();
        }

        if (datosActualizarRespuesta.topicoId() != null) {
            this.topicoId = new Topico(datosActualizarRespuesta.topicoId());
        }

        if (datosActualizarRespuesta.autorId() != null) {
            this.autorId = new Usuario(datosActualizarRespuesta.autorId());
        }

        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }

    public void resResuelto(DatosResolverRespuesta datosResolverRespuesta) {

        this.solucion = true;
        this.fechaCreacion = LocalDateTime.now();


    }
}
