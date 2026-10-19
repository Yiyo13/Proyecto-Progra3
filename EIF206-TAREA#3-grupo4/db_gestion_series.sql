
DROP DATABASE IF EXISTS db_gestion_series;
CREATE DATABASE db_gestion_series;
USE db_gestion_series;

-- =========================================
-- TABLA: GENEROS
-- =========================================

CREATE TABLE generos (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

INSERT INTO generos (nombre) VALUES
('Drama'),
('Comedia'),
('Ciencia ficción'),
('Fantasía'),
('Misterio'),
('Acción');


-- =========================================
-- TABLA: SERIES
-- =========================================

CREATE TABLE series (
    id_serie INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    temporadas INT NOT NULL,
    calificacion DECIMAL(3,1) NOT NULL,
    id_genero INT NOT NULL,
    FOREIGN KEY (id_genero) REFERENCES generos(id_genero)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- =========================================
-- DATOS DE PRUEBA
-- =========================================

INSERT INTO series
(titulo, temporadas, calificacion, id_genero)
VALUES

-- DRAMA (id_genero = 1)
('Breaking Bad', 5, 9.5, 1),
('Better Call Saul', 6, 9.0, 1),
('The Crown', 6, 8.6, 1),

-- COMEDIA (id_genero = 2)
('The Office', 9, 9.0, 2),
('Friends', 10, 8.9, 2),
('Brooklyn Nine-Nine', 8, 8.4, 2),

-- CIENCIA FICCION (id_genero = 3)
('Stranger Things', 4, 8.7, 3),
('Dark', 3, 8.8, 3),
('The Mandalorian', 3, 8.5, 3),

-- FANTASIA (id_genero = 4)
('Game of Thrones', 8, 9.3, 4),
('The Witcher', 3, 8.2, 4),

-- MISTERIO (id_genero = 5)
('Sherlock', 4, 9.1, 5),
('Only Murders in the Building', 4, 8.1, 5),

-- ACCION (id_genero = 6)
('The Boys', 4, 8.7, 6),
('Reacher', 2, 8.5, 6);
