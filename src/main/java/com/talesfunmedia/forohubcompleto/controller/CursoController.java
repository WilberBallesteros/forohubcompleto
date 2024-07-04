package com.talesfunmedia.forohubcompleto.controller;

import com.talesfunmedia.forohubcompleto.domain.curso.*;
import com.talesfunmedia.forohubcompleto.domain.topico.DatosListadoTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key") //documentacion
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));

        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public Page<DatosListadoCurso> listadoCursos(Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso) {
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        curso.actualizarDatos(datosActualizarCurso);

        return ResponseEntity.ok(new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornarDatosCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosRespuestaCurso(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria());
        return ResponseEntity.ok(datosCurso);
    }
}


