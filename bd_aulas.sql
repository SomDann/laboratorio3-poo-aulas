-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-10-2026 a las 02:28:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_aulas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aulas`
--

CREATE TABLE `aulas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `edificio` varchar(50) NOT NULL,
  `planta` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `responsable` varchar(100) NOT NULL,
  `id_estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `aulas`
--

INSERT INTO `aulas` (`id`, `nombre`, `edificio`, `planta`, `capacidad`, `id_tipo`, `responsable`, `id_estado`) VALUES
(1, 'B-204', 'Edificio A', 1, 30, 1, 'Carlos', 4),
(2, 'B-205', 'Edificio B', 2, 23, 4, 'Jose', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_aula`
--

CREATE TABLE `estado_aula` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_aula`
--

INSERT INTO `estado_aula` (`id`, `nombre`) VALUES
(1, 'Asignada'),
(2, 'Asignada temporalmente'),
(4, 'Disponible'),
(3, 'En mantenimiento');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planta_aula`
--

CREATE TABLE `planta_aula` (
  `id` int(11) NOT NULL,
  `nivel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `planta_aula`
--

INSERT INTO `planta_aula` (`id`, `nivel`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_aula`
--

CREATE TABLE `tipo_aula` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_aula`
--

INSERT INTO `tipo_aula` (`id`, `nombre`) VALUES
(2, 'Centro de computo'),
(4, 'Especial'),
(3, 'Laboratorio'),
(1, 'Normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(32) NOT NULL,
  `rol` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `correo`, `clave`, `rol`, `estado`) VALUES
(1, 'Administrador', 'Sistema', 'admin@correo.com', '827ccb0eea8a706c4c34a16891f84e7b', 'ADMIN', 'ACTIVO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aulas`
--
ALTER TABLE `aulas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_aula_edificio` (`nombre`,`edificio`),
  ADD UNIQUE KEY `UK8n4tnjltnxrhufob3puda7h8m` (`nombre`,`edificio`),
  ADD KEY `fk_aula_tipo` (`id_tipo`),
  ADD KEY `fk_aula_estado` (`id_estado`);

--
-- Indices de la tabla `estado_aula`
--
ALTER TABLE `estado_aula`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `planta_aula`
--
ALTER TABLE `planta_aula`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKcpr2q4oq2n8lx78j18m1dwqxj` (`nivel`);

--
-- Indices de la tabla `tipo_aula`
--
ALTER TABLE `tipo_aula`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aulas`
--
ALTER TABLE `aulas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estado_aula`
--
ALTER TABLE `estado_aula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `planta_aula`
--
ALTER TABLE `planta_aula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_aula`
--
ALTER TABLE `tipo_aula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aulas`
--
ALTER TABLE `aulas`
  ADD CONSTRAINT `fk_aula_estado` FOREIGN KEY (`id_estado`) REFERENCES `estado_aula` (`id`),
  ADD CONSTRAINT `fk_aula_tipo` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_aula` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
