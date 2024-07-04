package com.talesfunmedia.forohubcompleto.domain.respuesta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosResolverRespuesta(
        @NotNull
        Long id,
        LocalDateTime fechaCreacion
) {
}
