package com.talesfunmedia.forohubcompleto.domain.topico;

import com.talesfunmedia.forohubcompleto.domain.curso.Categoria;
import java.time.LocalDateTime;

public record DatosRespuestaTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        Boolean estado,
        String autor,
        String curso,
        String categoria
) {
}
