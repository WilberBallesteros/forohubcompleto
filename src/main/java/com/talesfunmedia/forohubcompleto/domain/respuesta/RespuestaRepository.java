package com.talesfunmedia.forohubcompleto.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Page<Respuesta> findBySolucion(boolean solucion, Pageable pageable);
}
