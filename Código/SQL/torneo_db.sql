CREATE DATABASE torneo_db;
USE torneo_db;

-- Obtener el ID de "Semifinales"
SELECT ronda_id FROM Ronda WHERE nombre_ronda = 'Semifinales';

-- Buscar partidas con ese ronda_id
SELECT * FROM Partida WHERE ronda_id =5;

SELECT * FROM Partida;
SELECT * FROM Ronda;

SELECT p.* 
FROM Partida p
JOIN Ronda r ON p.ronda_id = r.ronda_id
WHERE r.nombre_ronda = 'Semifinales';

ALTER TABLE Jugador ADD COLUMN puesto INT DEFAULT 0;


 SELECT * FROM Ronda;
 SELECT * FROM Partida;
 SELECT * FROM Torneo;
 SELECT * FROM Jugador;
---------
-- Elimina tablas existentes
DROP TABLE IF EXISTS Partida;
DROP TABLE IF EXISTS Ronda;
DROP TABLE IF EXISTS Torneo;
DROP TABLE IF EXISTS Jugador;

SELECT * FROM Ronda;

ALTER TABLE Jugador DROP COLUMN puesto;

SELECT ronda_id, nombre_ronda, fecha FROM Ronda;
SHOW TABLE STATUS LIKE 'Ronda';
ALTER TABLE Ronda AUTO_INCREMENT = 1;

-- Tabla Jugador
CREATE TABLE Jugador (
    jugador_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inscripcion DATE
);

ALTER TABLE Jugador ADD COLUMN puesto INT DEFAULT 0;
SELECT jugador_id, nombre, puesto FROM Jugador;

describe Jugador;

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

SELECT * FROM Partida;
SELECT * FROM Jugador;
SELECT * FROM Ronda;

SELECT ronda_id, nombre_ronda FROM Ronda;

-- Partidas de Treintaidósavos (ronda_id = 1)
INSERT INTO Partida (ronda_id, jugador_blancas_id, jugador_negras_id, ganador_id, estado, fecha_hora_inicio, fecha_hora_fin)
VALUES
-- Partidas 1 a 8
(1, 1, 2, 1, 'FINALIZADA', '2025-01-01 10:00:00', '2025-01-01 10:30:00'),
(1, 3, 4, 3, 'FINALIZADA', '2025-01-01 10:30:00', '2025-01-01 11:00:00'),
(1, 5, 6, 5, 'FINALIZADA', '2025-01-01 11:00:00', '2025-01-01 11:30:00'),
(1, 7, 8, 7, 'FINALIZADA', '2025-01-01 11:30:00', '2025-01-01 12:00:00'),
(1, 9, 10, 9, 'FINALIZADA', '2025-01-01 12:00:00', '2025-01-01 12:30:00'),
(1, 11, 12, 11, 'FINALIZADA', '2025-01-01 12:30:00', '2025-01-01 13:00:00'),
(1, 13, 14, 13, 'FINALIZADA', '2025-01-01 13:00:00', '2025-01-01 13:30:00'),
(1, 15, 16, 15, 'FINALIZADA', '2025-01-01 13:30:00', '2025-01-01 14:00:00'),
(1, 17, 18, 17, 'FINALIZADA', '2025-01-01 14:00:00', '2025-01-01 14:30:00'),
(1, 19, 20, 19, 'FINALIZADA', '2025-01-01 14:30:00', '2025-01-01 15:00:00'),
(1, 21, 22, 21, 'FINALIZADA', '2025-01-01 15:00:00', '2025-01-01 15:30:00'),
(1, 23, 24, 23, 'FINALIZADA', '2025-01-01 15:30:00', '2025-01-01 16:00:00'),
(1, 25, 26, 25, 'FINALIZADA', '2025-01-01 16:00:00', '2025-01-01 16:30:00'),
(1, 27, 28, 27, 'FINALIZADA', '2025-01-01 16:30:00', '2025-01-01 17:00:00'),
(1, 29, 30, 29, 'FINALIZADA', '2025-01-01 17:00:00', '2025-01-01 17:30:00'),
(1, 31, 32, 31, 'FINALIZADA', '2025-01-01 17:30:00', '2025-01-01 18:00:00'),
(1, 33, 34, 33, 'FINALIZADA', '2025-01-01 18:00:00', '2025-01-01 18:30:00'),
(1, 35, 36, 35, 'FINALIZADA', '2025-01-01 18:30:00', '2025-01-01 19:00:00'),
(1, 37, 38, 37, 'FINALIZADA', '2025-01-01 19:00:00', '2025-01-01 19:30:00'),
(1, 39, 40, 39, 'FINALIZADA', '2025-01-01 19:30:00', '2025-01-01 20:00:00'),
(1, 41, 42, 41, 'FINALIZADA', '2025-01-01 20:00:00', '2025-01-01 20:30:00'),
(1, 43, 44, 43, 'FINALIZADA', '2025-01-01 20:30:00', '2025-01-01 21:00:00'),
(1, 45, 46, 45, 'FINALIZADA', '2025-01-01 21:00:00', '2025-01-01 21:30:00'),
(1, 47, 48, 47, 'FINALIZADA', '2025-01-01 21:30:00', '2025-01-01 22:00:00'),
(1, 49, 50, 49, 'FINALIZADA', '2025-01-01 22:00:00', '2025-01-01 22:30:00'),
(1, 51, 52, 51, 'FINALIZADA', '2025-01-01 22:30:00', '2025-01-01 23:00:00'),
(1, 53, 54, 53, 'FINALIZADA', '2025-01-01 23:00:00', '2025-01-01 23:30:00'),
(1, 55, 56, 55, 'FINALIZADA', '2025-01-01 23:30:00', '2025-01-02 00:00:00'),
(1, 57, 58, 57, 'FINALIZADA', '2025-01-02 00:00:00', '2025-01-02 00:30:00'),
(1, 59, 60, 59, 'FINALIZADA', '2025-01-02 00:30:00', '2025-01-02 01:00:00'),
(1, 61, 62, 61, 'FINALIZADA', '2025-01-02 01:00:00', '2025-01-02 01:30:00'),
(1, 63, 64, 63, 'FINALIZADA', '2025-01-02 01:30:00', '2025-01-02 02:00:00'),


(2, 1, 3, 1, 'FINALIZADA', '2025-01-02 10:00:00', '2025-01-02 10:30:00'),
(2, 5, 7, 5, 'FINALIZADA', '2025-01-02 10:30:00', '2025-01-02 11:00:00'),
(2, 9, 11, 9, 'FINALIZADA', '2025-01-02 11:00:00', '2025-01-02 11:30:00'),
(2, 13, 15, 13, 'FINALIZADA', '2025-01-02 11:30:00', '2025-01-02 12:00:00'),
(2, 17, 19, 17, 'FINALIZADA', '2025-01-02 12:00:00', '2025-01-02 12:30:00'),
(2, 21, 23, 21, 'FINALIZADA', '2025-01-02 12:30:00', '2025-01-02 13:00:00'),
(2, 25, 27, 25, 'FINALIZADA', '2025-01-02 13:00:00', '2025-01-02 13:30:00'),
(2, 29, 31, 29, 'FINALIZADA', '2025-01-02 13:30:00', '2025-01-02 14:00:00'),
(2, 33, 35, 33, 'FINALIZADA', '2025-01-02 14:00:00', '2025-01-02 14:30:00'),
(2, 37, 39, 37, 'FINALIZADA', '2025-01-02 14:30:00', '2025-01-02 15:00:00'),
(2, 41, 43, 41, 'FINALIZADA', '2025-01-02 15:00:00', '2025-01-02 15:30:00'),
(2, 45, 47, 45, 'FINALIZADA', '2025-01-02 15:30:00', '2025-01-02 16:00:00'),
(2, 49, 51, 49, 'FINALIZADA', '2025-01-02 16:00:00', '2025-01-02 16:30:00'),
(2, 53, 55, 53, 'FINALIZADA', '2025-01-02 16:30:00', '2025-01-02 17:00:00'),
(2, 57, 59, 57, 'FINALIZADA', '2025-01-02 17:00:00', '2025-01-02 17:30:00'),
(2, 61, 63, 61, 'FINALIZADA', '2025-01-02 17:30:00', '2025-01-02 18:00:00'),
(3, 1, 5, 1, 'FINALIZADA', '2025-01-03 10:00:00', '2025-01-03 10:30:00'),
(3, 9, 13, 9, 'FINALIZADA', '2025-01-03 10:30:00', '2025-01-03 11:00:00'),
(3, 17, 21, 17, 'FINALIZADA', '2025-01-03 11:00:00', '2025-01-03 11:30:00'),
(3, 25, 29, 25, 'FINALIZADA', '2025-01-03 11:30:00', '2025-01-03 12:00:00'),
(3, 33, 37, 33, 'FINALIZADA', '2025-01-03 12:00:00', '2025-01-03 12:30:00'),
(3, 41, 45, 41, 'FINALIZADA', '2025-01-03 12:30:00', '2025-01-03 13:00:00'),
(3, 49, 53, 49, 'FINALIZADA', '2025-01-03 13:00:00', '2025-01-03 13:30:00'),
(3, 57, 61, 57, 'FINALIZADA', '2025-01-03 13:30:00', '2025-01-03 14:00:00'),
(4, 1, 9, 1, 'FINALIZADA', '2025-01-04 10:00:00', '2025-01-04 10:30:00'),
(4, 17, 25, 17, 'FINALIZADA', '2025-01-04 10:30:00', '2025-01-04 11:00:00'),
(4, 33, 41, 33, 'FINALIZADA', '2025-01-04 11:00:00', '2025-01-04 11:30:00'),
(4, 49, 57, 49, 'FINALIZADA', '2025-01-04 11:30:00', '2025-01-04 12:00:00'),
(5, 1, 17, 1, 'FINALIZADA', '2025-01-05 10:00:00', '2025-01-05 10:30:00'),

-- Semifinal 2
(5, 33, 49, 33, 'FINALIZADA', '2025-01-05 10:30:00', '2025-01-05 11:00:00'),
(6, 1, 33, 1, 'FINALIZADA', '2025-01-06 10:00:00', '2025-01-06 10:30:00');

-- Partidas



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


INSERT INTO Torneo (nombre, fecha_inicio, fecha_fin) 
VALUES ('Torneo Ajedrez 2025', '2025-01-01', '2025-02-01');

INSERT INTO Ronda (torneo_id, nombre_ronda, fecha) 
VALUES 
(1, 'Treintaidósavos', '2025-01-01'),
(1, 'Dieciseisavos', '2025-01-02'),
(1, 'Octavos de final', '2025-01-03'),
(1, 'Cuartos de final', '2025-01-04'),
(1, 'Semifinales', '2025-01-05'),
(1, 'Final', '2025-01-06');



