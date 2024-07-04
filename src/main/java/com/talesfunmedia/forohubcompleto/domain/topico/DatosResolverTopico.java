package com.talesfunmedia.forohubcompleto.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosResolverTopico(
        @NotNull
        Long id,
        LocalDateTime fechaCreacion
) {
}
