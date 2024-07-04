package com.talesfunmedia.forohubcompleto.controller;

import com.talesfunmedia.forohubcompleto.domain.curso.Curso;
import com.talesfunmedia.forohubcompleto.domain.curso.CursoRepository;
import com.talesfunmedia.forohubcompleto.domain.topico.*;
import com.talesfunmedia.forohubcompleto.domain.usuario.Usuario;
import com.talesfunmedia.forohubcompleto.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));

        Usuario autor = usuarioRepository.findById(topico.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topico.getCursoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                autor.getNombre(),
                curso.getNombre(),
                curso.getCategoria().toString());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    //topicosResueltos
    @GetMapping("/resueltos")
    public Page<DatosListadoTopico> listadoTopicosActivos(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
        return topicoRepository.findByEstado(true, paginacion).map(DatosListadoTopico::new);
    }

    //topicos sin resolver
    @GetMapping("/pendientes")
    public Page<DatosListadoTopico> listadoTopicosSinResolver(@PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) Pageable paginacion) {
        return topicoRepository.findByEstado(false, paginacion).map(DatosListadoTopico::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopicos(datosActualizarTopico);

        Usuario autor = usuarioRepository.findById(topico.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topico.getCursoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                autor.getNombre(),
                curso.getNombre(),
                curso.getCategoria().toString()
        ));
    }
  
    @PutMapping("/resuelto")
    @Transactional
    public ResponseEntity resolverTopico(@RequestBody @Valid DatosResolverTopico datosResolverTopico) {
        Topico topico = topicoRepository.getReferenceById(datosResolverTopico.id());
        topico.topicoResuelto(datosResolverTopico);

        Usuario autor = usuarioRepository.findById(topico.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topico.getCursoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                autor.getNombre(),
                curso.getNombre(),
                curso.getCategoria().toString()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        Usuario autor = usuarioRepository.findById(topico.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(topico.getCursoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        var datosTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstado(),
                autor.getNombre(),
                curso.getNombre(),
                curso.getCategoria().toString());

        return ResponseEntity.ok(datosTopico);
    }

}
