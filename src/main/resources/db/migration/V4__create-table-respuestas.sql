CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(255) NOT NULL,
    topicoId BIGINT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    autorId BIGINT NOT NULL,
    solucion TINYINT NOT NULL,

    PRIMARY KEY (id),

     constraint fk_mensajes_topico_id foreign key(topicoId) references topicos(id),
     constraint fk_mensajes_autor_id foreign key(autorId) references usuarios(id)

);