package com.talesfunmedia.forohubcompleto.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
         @NotBlank
         String mensaje,
         @NotNull
         Long topicoId,
         LocalDateTime fechaCreacion,
         @NotNull
         Long autorId

) {
}
