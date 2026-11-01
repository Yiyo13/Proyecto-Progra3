-- ============================================================
-- EIF206 - Programación III - Proyecto #2 - Grupo 4
-- Sistema de Clínica Veterinaria
-- Base de datos: db_pf_programacion3_g4
-- Ubicación en el proyecto: src/resources/db_pf_programacion3_g4.sql
-- ============================================================

DROP DATABASE IF EXISTS db_pf_programacion3_g4;
CREATE DATABASE db_pf_programacion3_g4
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
USE db_pf_programacion3_g4;

-- ------------------------------------------------------------
-- Tablas
-- ------------------------------------------------------------
CREATE TABLE propietarios (
    id_propietario INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    telefono       VARCHAR(20)  NOT NULL,
    correo         VARCHAR(100) NOT NULL,
    direccion      VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE mascotas (
    id_mascota       INT AUTO_INCREMENT PRIMARY KEY,
    id_propietario   INT          NOT NULL,
    nombre           VARCHAR(100) NOT NULL,
    especie          VARCHAR(50)  NOT NULL,
    raza             VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE         NOT NULL,
    CONSTRAINT fk_mascotas_propietarios
        FOREIGN KEY (id_propietario) REFERENCES propietarios(id_propietario)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE consultas (
    id_consulta  INT AUTO_INCREMENT PRIMARY KEY,
    id_mascota   INT           NOT NULL,
    fecha        DATE          NOT NULL,
    motivo       VARCHAR(255)  NOT NULL,
    diagnostico  TEXT          NOT NULL,
    costo        DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_consultas_mascotas
        FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota)
        ON DELETE CASCADE ON UPDATE CASCADE -- si se elimina un propietario se eliminan todos los registros con el
) ENGINE=InnoDB;

-- ------------------------------------------------------------
-- Datos de prueba: 10 propietarios, 2 mascotas c/u, 3 consultas por mascota
-- Fechas de las consultas distribuidas durante 2026
-- ------------------------------------------------------------

INSERT INTO propietarios (nombre, telefono, correo, direccion) VALUES
('Carlos Jiménez Mora', '8888-1234', 'cjimenez@gmail.com', 'San José, Montes de Oca, Residencial Las Flores'),
('María Fernanda Rojas', '8321-5566', 'mfrojas@hotmail.com', 'Heredia, Barva, 200 m norte de la iglesia'),
('Luis Alberto Vargas', '8456-7890', 'lvargas@gmail.com', 'Alajuela, San Ramón, Barrio San Juan'),
('Ana Lucía Soto', '8710-2233', 'asoto@yahoo.com', 'Cartago, Paraíso, frente al parque central'),
('José Pablo Araya', '8899-4455', 'jparaya@gmail.com', 'San Carlos, Ciudad Quesada, Barrio El Carmen'),
('Daniela Quesada Cruz', '8605-9988', 'dquesada@outlook.com', 'Sarapiquí, Puerto Viejo, 100 m sur de la escuela'),
('Andrés Chaves Solano', '8123-6677', 'achaves@gmail.com', 'Limón, Guápiles, Residencial Los Almendros'),
('Valeria Mena Castro', '8544-3322', 'vmena@hotmail.com', 'Heredia, Santo Domingo, Urbanización La Esperanza'),
('Diego Alvarado Rivera', '8277-1100', 'dalvarado@gmail.com', 'San José, Escazú, Calle Los Anonos'),
('Sofía Herrera Campos', '8960-4411', 'sherrera@yahoo.com', 'Guanacaste, Liberia, Barrio Capulín');

INSERT INTO mascotas (id_propietario, nombre, especie, raza, fecha_nacimiento) VALUES
(1, 'Max', 'Perro', 'Labrador Retriever', '2021-06-12'),
(1, 'Luna', 'Gato', 'Siamés', '2022-03-08'),
(2, 'Rocky', 'Perro', 'Bulldog Francés', '2020-09-21'),
(2, 'Mía', 'Gato', 'Persa', '2023-01-15'),
(3, 'Toby', 'Perro', 'Beagle', '2019-11-02'),
(3, 'Nala', 'Perro', 'Golden Retriever', '2022-07-30'),
(4, 'Simba', 'Gato', 'Criollo', '2021-04-17'),
(4, 'Coco', 'Ave', 'Loro', '2018-05-25'),
(5, 'Bruno', 'Perro', 'Pastor Alemán', '2020-02-14'),
(5, 'Pelusa', 'Conejo', 'Cabeza de León', '2023-08-09'),
(6, 'Kira', 'Perro', 'Husky Siberiano', '2021-12-01'),
(6, 'Tom', 'Gato', 'Criollo', '2020-10-10'),
(7, 'Thor', 'Perro', 'Rottweiler', '2019-06-06'),
(7, 'Princesa', 'Gato', 'Angora', '2022-09-19'),
(8, 'Lola', 'Perro', 'Poodle', '2021-01-23'),
(8, 'Chispa', 'Hámster', 'Sirio', '2024-02-03'),
(9, 'Zeus', 'Perro', 'Boxer', '2020-08-28'),
(9, 'Michi', 'Gato', 'Criollo', '2023-05-05'),
(10, 'Dante', 'Perro', 'Chihuahua', '2022-10-12'),
(10, 'Rita', 'Tortuga', 'Tortuga de orejas rojas', '2017-03-30');

INSERT INTO consultas (id_mascota, fecha, motivo, diagnostico, costo) VALUES
(1, '2026-01-05', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 15000.00),
(1, '2026-05-05', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 40000.00),
(1, '2026-09-03', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 28500.00),
(2, '2026-02-10', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 25000.00),
(2, '2026-06-11', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 15000.00),
(2, '2026-10-10', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 40000.00),
(3, '2026-03-19', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 35000.00),
(3, '2026-07-18', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 25000.00),
(3, '2026-11-16', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 15000.00),
(4, '2026-04-25', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 12500.00),
(4, '2026-08-24', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 35000.00),
(4, '2026-12-23', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 25000.00),
(5, '2026-06-01', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 22000.00),
(5, '2026-09-30', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 12500.00),
(5, '2026-02-03', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 35000.00),
(6, '2026-07-08', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 32000.00),
(6, '2026-11-06', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 22000.00),
(6, '2026-03-12', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 12500.00),
(7, '2026-08-14', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 45000.00),
(7, '2026-12-13', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 32000.00),
(7, '2026-04-18', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 22000.00),
(8, '2026-09-20', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 18500.00),
(8, '2026-01-24', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 45000.00),
(8, '2026-05-25', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 32000.00),
(9, '2026-10-27', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 28500.00),
(9, '2026-03-02', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 18500.00),
(9, '2026-07-01', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 45000.00),
(10, '2026-12-03', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 40000.00),
(10, '2026-04-08', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 28500.00),
(10, '2026-08-07', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 18500.00),
(11, '2026-01-14', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 15000.00),
(11, '2026-05-15', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 40000.00),
(11, '2026-09-13', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 28500.00),
(12, '2026-02-20', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 25000.00),
(12, '2026-06-21', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 15000.00),
(12, '2026-10-20', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 40000.00),
(13, '2026-03-29', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 35000.00),
(13, '2026-07-28', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 25000.00),
(13, '2026-11-26', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 15000.00),
(14, '2026-05-05', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 12500.00),
(14, '2026-09-03', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 35000.00),
(14, '2026-01-07', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 25000.00),
(15, '2026-06-11', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 22000.00),
(15, '2026-10-10', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 12500.00),
(15, '2026-02-13', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 35000.00),
(16, '2026-07-18', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 32000.00),
(16, '2026-11-16', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 22000.00),
(16, '2026-03-22', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 12500.00),
(17, '2026-08-24', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 45000.00),
(17, '2026-12-23', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 32000.00),
(17, '2026-04-28', 'Control de rutina', 'Mascota en buen estado de salud. Se recomienda continuar con su plan de vacunación.', 22000.00),
(18, '2026-09-30', 'Vacunación anual', 'Se aplica refuerzo de vacunas sin reacciones adversas.', 18500.00),
(18, '2026-02-03', 'Desparasitación', 'Se administra antiparasitario interno y externo. Repetir en tres meses.', 45000.00),
(18, '2026-06-04', 'Problemas digestivos', 'Gastroenteritis leve. Se indica dieta blanda e hidratación.', 32000.00),
(19, '2026-11-06', 'Revisión dental', 'Sarro moderado. Se recomienda limpieza dental preventiva.', 28500.00),
(19, '2026-03-12', 'Alergia en la piel', 'Dermatitis alérgica. Se receta tratamiento tópico y cambio de alimento.', 18500.00),
(19, '2026-07-11', 'Cojera en pata trasera', 'Esguince leve. Reposo por una semana y antiinflamatorio.', 45000.00),
(20, '2026-12-13', 'Chequeo post operatorio', 'Cicatrización correcta, sin signos de infección.', 40000.00),
(20, '2026-04-18', 'Pérdida de apetito', 'Sin hallazgos graves. Se ajusta la alimentación y se da seguimiento.', 28500.00),
(20, '2026-12-18', 'Revisión de oídos', 'Otitis leve. Se indica limpieza y gotas óticas por siete días.', 18500.00);