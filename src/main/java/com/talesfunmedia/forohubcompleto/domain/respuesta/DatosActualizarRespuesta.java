package com.talesfunmedia.forohubcompleto.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarRespuesta(

        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        LocalDateTime fechaCreacion,
        @NotNull
        Long autorId

) {

}
