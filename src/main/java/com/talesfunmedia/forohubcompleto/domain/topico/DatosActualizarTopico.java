package com.talesfunmedia.forohubcompleto.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(

        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotBlank
        String titulo,
        LocalDateTime fechaCreacion,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId


) {
}
