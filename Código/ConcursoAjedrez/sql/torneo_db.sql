CREATE DATABASE torneo_db;


-- Tabla Jugador
CREATE TABLE Jugador (
    jugador_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inscripcion DATE
);

-- Tabla Torneo
CREATE TABLE Torneo (
    torneo_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE
);

-- Tabla Ronda
CREATE TABLE Ronda (
    ronda_id INT AUTO_INCREMENT PRIMARY KEY,
    torneo_id INT NOT NULL,
    nombre_ronda VARCHAR(50),
    fecha DATE,
    FOREIGN KEY (torneo_id) REFERENCES Torneo(torneo_id)
);

-- Tabla Partida
CREATE TABLE Partida (
    partida_id INT AUTO_INCREMENT PRIMARY KEY,
    ronda_id INT NOT NULL,
    jugador_blancas_id INT NOT NULL,
    jugador_negras_id INT NOT NULL,
    ganador_id INT,
    estado ENUM('EN_CURSO', 'FINALIZADA', 'CANCELADA') DEFAULT 'EN_CURSO',
    fecha_hora_inicio DATETIME,
    fecha_hora_fin DATETIME,
    FOREIGN KEY (ronda_id) REFERENCES Ronda(ronda_id),
    FOREIGN KEY (jugador_blancas_id) REFERENCES Jugador(jugador_id),
    FOREIGN KEY (jugador_negras_id) REFERENCES Jugador(jugador_id),
    FOREIGN KEY (ganador_id) REFERENCES Jugador(jugador_id)
);

ALTER TABLE Partida ADD COLUMN duracion DOUBLE;

SELECT * FROM Jugador;

-- Tabla PosicionPieza
CREATE TABLE PosicionPieza (
    posicion_id INT AUTO_INCREMENT PRIMARY KEY,
    partida_id INT NOT NULL,
    tipo_pieza VARCHAR(20),
    color ENUM('BLANCO', 'NEGRO'),
    coordenada VARCHAR(5),
    fecha_hora_guardado DATETIME,
    formato VARCHAR(10),
    FOREIGN KEY (partida_id) REFERENCES Partida(partida_id)
);

-- Datos de prueba
INSERT INTO Jugador (jugador_id, nombre, fecha_inscripcion) 
VALUES 
(1, 'Alejandro López', '2025-01-01'),
(2, 'María Fernández', '2025-01-02'),
(3, 'Juan Martínez', '2025-01-03'),
(4, 'Laura Gómez', '2025-01-04'),
(5, 'Carlos Pérez', '2025-01-05'),
(6, 'Ana Sánchez', '2025-01-06'),
(7, 'Luis Ramírez', '2025-01-07'),
(8, 'Marta Torres', '2025-01-08'),
(9, 'Sofía García', '2025-01-09'),
(10, 'David Ruiz', '2025-01-10'),
(11, 'Pablo González', '2025-01-11'),
(12, 'Lucía Navarro', '2025-01-12'),
(13, 'Javier Vargas', '2025-01-13'),
(14, 'Elena Moreno', '2025-01-14'),
(15, 'Diego Jiménez', '2025-01-15'),
(16, 'Claudia Herrera', '2025-01-16'),
(17, 'Andrés Castro', '2025-01-17'),
(18, 'Natalia Vega', '2025-01-18'),
(19, 'Manuel Silva', '2025-01-19'),
(20, 'Isabel Romero', '2025-01-20'),
(21, 'Álvaro Ortega', '2025-01-21'),
(22, 'Cristina Delgado', '2025-01-22'),
(23, 'Francisco Aguilar', '2025-01-23'),
(24, 'Raquel Prieto', '2025-01-24'),
(25, 'Antonio Ríos', '2025-01-25'),
(26, 'Carmen Serrano', '2025-01-26'),
(27, 'Miguel Domínguez', '2025-01-27'),
(28, 'Teresa Pacheco', '2025-01-28'),
(29, 'Fernando Méndez', '2025-01-29'),
(30, 'Eva Lozano', '2025-01-30'),
(31, 'Hugo Molina', '2025-01-31'),
(32, 'Sara León', '2025-02-01'),
(33, 'Ricardo Peña', '2025-02-02'),
(34, 'Irene Campos', '2025-02-03'),
(35, 'Jaime Cruz', '2025-02-04'),
(36, 'Patricia Bravo', '2025-02-05'),
(37, 'Adrián Morales', '2025-02-06'),
(38, 'Julia Fuentes', '2025-02-07'),
(39, 'Iván Cano', '2025-02-08'),
(40, 'Paula Cabrera', '2025-02-09'),
(41, 'Sergio Muñoz', '2025-02-10'),
(42, 'Rocío Nieto', '2025-02-11'),
(43, 'Mario Blanco', '2025-02-12'),
(44, 'Beatriz Ortiz', '2025-02-13'),
(45, 'Héctor Márquez', '2025-02-14'),
(46, 'Andrea Cortés', '2025-02-15'),
(47, 'Daniel Suárez', '2025-02-16'),
(48, 'Alicia Guzmán', '2025-02-17'),
(49, 'Enrique Reyes', '2025-02-18'),
(50, 'Verónica Castillo', '2025-02-19'),
(51, 'Óscar Ferrer', '2025-02-20'),
(52, 'Nuria Medina', '2025-02-21'),
(53, 'Rubén Arias', '2025-02-22'),
(54, 'Sandra Benítez', '2025-02-23'),
(55, 'Gonzalo Mármol', '2025-02-24'),
(56, 'Silvia Esteban', '2025-02-25'),
(57, 'Eduardo Pascual', '2025-02-26'),
(58, 'Inés Carrasco', '2025-02-27'),
(59, 'Ángel Valero', '2025-02-28'),
(60, 'Gloria Montes', '2025-03-01'),
(61, 'Ramón Quintana', '2025-03-02'),
(62, 'Carolina Cruz', '2025-03-03'),
(63, 'Pedro Salazar', '2025-03-04'),
(64, 'Victoria Redondo', '2025-03-05');

SELECT * FROM Torneo;

INSERT INTO Torneo (nombre, fecha_inicio, fecha_fin) 
VALUES ('Torneo Ajedrez 2025', '2025-01-01', '2025-02-01');

USE torneo_db;
INSERT INTO Ronda (torneo_id, nombre_ronda, fecha) VALUES
(1, 'Treintaidosavos', '2025-01-01'),
(1, 'Dieciseisavos', '2025-01-02'),
(1, 'Octavos de final', '2025-01-03'),
(1, 'Cuartos de final', '2025-01-04'),
(1, 'Semifinales', '2025-01-05'),
(1, 'Final', '2025-01-06');
