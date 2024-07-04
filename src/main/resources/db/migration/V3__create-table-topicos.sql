CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(200) NOT NULL UNIQUE,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    fecha DATETIME NOT NULL,
    estado TINYINT NOT NULL,
    autorId BIGINT NOT NULL,
    cursoId BIGINT NOT NULL,

    PRIMARY KEY (id),

     constraint fk_topicos_autor_id foreign key(autorId) references usuarios(id),
     constraint fk_topicos_curso_id foreign key(cursoId) references cursos(id)
);