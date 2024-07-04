package com.talesfunmedia.forohubcompleto.domain.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank
        String mensaje,
        @NotBlank
        String titulo,
        LocalDateTime fecha,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId

) {
}
