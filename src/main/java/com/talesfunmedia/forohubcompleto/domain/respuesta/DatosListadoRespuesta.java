package com.talesfunmedia.forohubcompleto.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta(

        String mensaje,
        String mensajeTopico,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        Boolean solucion
) {
    public DatosListadoRespuesta (Respuesta respuesta) {
        this(
                respuesta.getMensaje(),
                respuesta.getTopicoId().getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutorId().getNombre(),
                respuesta.getSolucion()
        );
    }
}
