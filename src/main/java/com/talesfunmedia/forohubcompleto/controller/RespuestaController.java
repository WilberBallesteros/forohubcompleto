package com.talesfunmedia.forohubcompleto.controller;

import com.talesfunmedia.forohubcompleto.domain.respuesta.*;
import com.talesfunmedia.forohubcompleto.domain.topico.Topico;
import com.talesfunmedia.forohubcompleto.domain.topico.TopicoRepository;
import com.talesfunmedia.forohubcompleto.domain.usuario.Usuario;
import com.talesfunmedia.forohubcompleto.domain.usuario.UsuarioRepository;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder) {
        Respuesta respuesta =  respuestaRepository.save(new Respuesta(datosRegistroRespuesta));

        Usuario autor = usuarioRepository.findById(respuesta.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Topico topico = topicoRepository.findById(respuesta.getTopicoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));

        DatosResRespuesta datosResRespuesta = new DatosResRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                topico.getTitulo(),
                topico.getMensaje(),
                respuesta.getFechaCreacion(),
                autor.getNombre(),
                respuesta.getSolucion());

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosResRespuesta);
    }

    @GetMapping
    public Page<DatosListadoRespuesta> listarRespuestas(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new);
    }

    @GetMapping("/solucionadas")
    public Page<DatosListadoRespuesta> listadoRespuestasActivos( Pageable paginacion) {
        return respuestaRepository.findBySolucion(true, paginacion).map(DatosListadoRespuesta::new);
    }

    @GetMapping("/incorrectas")
    public Page<DatosListadoRespuesta> listadoRespuestasIncorrectas( Pageable paginacion) {
        return respuestaRepository.findBySolucion(false, paginacion).map(DatosListadoRespuesta::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarRespuestas(datosActualizarRespuesta);

        Usuario autor = usuarioRepository.findById(respuesta.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Topico topico = topicoRepository.findById(respuesta.getTopicoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));

        return ResponseEntity.ok(new DatosResRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                topico.getTitulo(),
                topico.getMensaje(),
                respuesta.getFechaCreacion(),
                autor.getNombre(),
                respuesta.getSolucion()
        ));
    }

    @PutMapping("/resuelto")
    @Transactional
    public ResponseEntity resolverRespuesta(@RequestBody @Valid DatosResolverRespuesta datosResolverRespuesta) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datosResolverRespuesta.id());
        respuesta.resResuelto(datosResolverRespuesta);

        Usuario autor = usuarioRepository.findById(respuesta.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Topico topico = topicoRepository.findById(respuesta.getTopicoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));

        return ResponseEntity.ok(new DatosResRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                topico.getTitulo(),
                topico.getMensaje(),
                respuesta.getFechaCreacion(),
                autor.getNombre(),
                respuesta.getSolucion()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespues(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosResRespuesta> retornarDatosRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);

        Usuario autor = usuarioRepository.findById(respuesta.getAutorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Topico topico = topicoRepository.findById(respuesta.getTopicoId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));

        var datosRspuesta = new DatosResRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                topico.getTitulo(),
                topico.getMensaje(),
                respuesta.getFechaCreacion(),
                autor.getNombre(),
                respuesta.getSolucion());
        return ResponseEntity.ok(datosRspuesta);
    }
}
