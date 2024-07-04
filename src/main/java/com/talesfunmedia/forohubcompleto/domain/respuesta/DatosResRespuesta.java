package com.talesfunmedia.forohubcompleto.domain.respuesta;

import java.time.LocalDateTime;

public record DatosResRespuesta(

        Long id,
        String mensajeRespuesta,
        String tituloTopico,
        String mensajeTopico,
        LocalDateTime fecha,
        String AutorSolucion,
        Boolean solucion
) {
}
