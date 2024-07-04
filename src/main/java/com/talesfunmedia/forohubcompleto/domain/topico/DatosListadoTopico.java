package com.talesfunmedia.forohubcompleto.domain.topico;

import com.talesfunmedia.forohubcompleto.domain.curso.Curso;
import com.talesfunmedia.forohubcompleto.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean estado,
        String autor,
        String curso


) {

    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                topico.getAutorId().getNombre(),
                topico.getCursoId().getNombre()
        );
    }
}
