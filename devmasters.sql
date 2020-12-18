-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-12-2020 a las 20:05:22
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `devmasters`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consultas_medicas`
--

CREATE TABLE `consultas_medicas` (
  `noConsulta` bigint(20) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `fechaConsulta` date NOT NULL,
  `horaConsulta` time NOT NULL,
  `idPaciente` varchar(13) NOT NULL,
  `idMedico` varchar(13) NOT NULL,
  `motivo` text NOT NULL,
  `idEstadoConsulta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `consultas_medicas`
--

INSERT INTO `consultas_medicas` (`noConsulta`, `fechaCreacion`, `fechaConsulta`, `horaConsulta`, `idPaciente`, `idMedico`, `motivo`, `idEstadoConsulta`) VALUES
(1, '2020-12-18', '2020-12-21', '14:30:00', '0301199800256', '1201199900302', 'Dolor de cabeza', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_empleados`
--

CREATE TABLE `correo_empleados` (
  `idCorreo` int(11) NOT NULL,
  `idEmpleado` varchar(13) NOT NULL,
  `correo` varchar(35) NOT NULL,
  `tipoCorreo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_empleados`
--

INSERT INTO `correo_empleados` (`idCorreo`, `idEmpleado`, `correo`, `tipoCorreo`) VALUES
(22, '1201199800278', 'martha.padilla@ujcv.edu.hn', 2),
(23, '1201199800278', 'martha156@yahoo.com', 1),
(29, '0302200200478', 'yarlenny.torres@gmail.com', 1),
(36, '1201199900302', 'juanvergas@yahoo.com', 1),
(37, '1202199702568', 'mariog1997@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_pacientes`
--

CREATE TABLE `correo_pacientes` (
  `idCorreo` int(11) NOT NULL,
  `idPaciente` varchar(13) NOT NULL,
  `correo` varchar(35) NOT NULL,
  `tipoCorreo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_pacientes`
--

INSERT INTO `correo_pacientes` (`idCorreo`, `idPaciente`, `correo`, `tipoCorreo`) VALUES
(13, '1201199900302', 'jose.castillo@ujcv.edu.hn', 2),
(14, '0301199800256', 'mariaz134@gmail.com', 1),
(15, '0302199800256', 'daniel.mejia@ujcv.edu.hn', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_proveedores`
--

CREATE TABLE `correo_proveedores` (
  `idCorreo` int(11) NOT NULL,
  `idProveedor` varchar(13) NOT NULL,
  `correo` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_proveedores`
--

INSERT INTO `correo_proveedores` (`idCorreo`, `idProveedor`, `correo`) VALUES
(1, '1201', 'feliperuiz45@yahoo.com'),
(2, '5689', 'juan.sabarro@yahoo.es'),
(4, '4646464', 'drogueriamiguel@gmail.com'),
(5, '4646464', 'miguelh13@gmail.com'),
(6, '8985678', 'clinilab@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_facturacion`
--

CREATE TABLE `detalle_facturacion` (
  `idDetalle` bigint(20) NOT NULL,
  `idFacturacion` bigint(20) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_facturacion`
--

INSERT INTO `detalle_facturacion` (`idDetalle`, `idFacturacion`, `idProducto`, `cantidad`) VALUES
(1, 1, 1230, 6),
(2, 1, 25258, 8),
(3, 5, 25258, 7),
(4, 6, 25258, 10),
(5, 7, 25258, 14),
(6, 8, 25258, 2),
(7, 9, 1230, 3),
(8, 10, 1230, 5),
(9, 11, 1230, 10),
(10, 12, 25258, 10),
(11, 12, 1230, 15),
(12, 13, 1213, 10),
(13, 15, 1213, 5),
(14, 15, 1230, 8),
(15, 16, 1213, 3),
(16, 17, 1213, 5),
(17, 17, 1213, 5),
(18, 19, 1213, 5),
(19, 19, 1213, 5),
(20, 21, 1213, 5),
(21, 21, 25258, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_servicios`
--

CREATE TABLE `detalle_servicios` (
  `idDetalle` bigint(20) NOT NULL,
  `idFacturacion` bigint(20) NOT NULL,
  `idServicio` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalle_servicios`
--

INSERT INTO `detalle_servicios` (`idDetalle`, `idFacturacion`, `idServicio`, `cantidad`) VALUES
(1, 4, 1, 1),
(2, 5, 1, 1),
(3, 14, 1, 1),
(4, 14, 2, 1),
(5, 15, 1, 1),
(6, 16, 1, 1),
(7, 21, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `idEmpleado` varchar(13) NOT NULL,
  `nombres` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `idGenero` int(11) NOT NULL,
  `idNacionalidad` int(11) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `tipoEmpleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idEmpleado`, `nombres`, `apellidos`, `fechaNacimiento`, `idGenero`, `idNacionalidad`, `direccion`, `tipoEmpleado`) VALUES
('0302200200478', 'Yarlenny', 'Torres', '2003-09-24', 2, 1, 'Barrio San Juan, La Paz', 3),
('1201199800278', 'Martha Nelly', 'Isaula Padilla', '1998-12-12', 2, 1, 'Barrio La Concepción, La Paz', 2),
('1201199900302', 'Juan', 'Vargas', '1998-12-10', 2, 1, 'Barrio La Joya, Tegucigalpa', 1),
('1202199702568', 'Maynor', 'Castro', '1997-12-12', 1, 1, 'Barrio Suyapa, Comayagua', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermeras`
--

CREATE TABLE `enfermeras` (
  `idEmpleado` varchar(13) NOT NULL,
  `idEspecialidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidades`
--

CREATE TABLE `especialidades` (
  `idEspecialidad` int(11) NOT NULL,
  `especialidad` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `especialidades`
--

INSERT INTO `especialidades` (`idEspecialidad`, `especialidad`) VALUES
(1, 'Médico General'),
(2, 'Ortopeda'),
(3, 'Cardiólogo'),
(4, 'Ginecólogo'),
(5, 'Pediatra'),
(6, 'Dermatólogo'),
(7, 'Neurólogo'),
(8, 'Urólogo'),
(9, 'Dentista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_consultas`
--

CREATE TABLE `estado_consultas` (
  `idEstado` int(11) NOT NULL,
  `estado` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estado_consultas`
--

INSERT INTO `estado_consultas` (`idEstado`, `estado`) VALUES
(1, 'Agendada'),
(2, 'Confirmada'),
(3, 'Cancelada'),
(4, 'Pospuesta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fabricantes`
--

CREATE TABLE `fabricantes` (
  `idFabricante` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `fabricante` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturacion`
--

CREATE TABLE `facturacion` (
  `idFacturacion` bigint(20) NOT NULL,
  `fechaFactura` date NOT NULL,
  `idEmpleado` varchar(13) NOT NULL,
  `idPaciente` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `facturacion`
--

INSERT INTO `facturacion` (`idFacturacion`, `fechaFactura`, `idEmpleado`, `idPaciente`) VALUES
(1, '2020-12-11', '1201199800278', '1201199900302'),
(2, '2020-12-11', '1201199800278', '1201199900302'),
(3, '2020-12-11', '1206897456566', '0301199800256'),
(4, '2020-12-11', '1206897456566', '0301199800256'),
(5, '2020-12-11', '1201199800278', '1201199900302'),
(6, '2020-12-11', '1201199800278', '1201199900302'),
(7, '2020-12-11', '1201199800278', '1201199900302'),
(8, '2020-12-11', '1201199800278', '1201199900302'),
(9, '2020-12-11', '1201199800278', '1201199900302'),
(10, '2020-12-11', '1201199800278', '1201199900302'),
(11, '2020-12-11', '1201199800278', '1201199900302'),
(12, '2020-12-11', '1201199800278', '1201199900302'),
(13, '2020-12-12', '1201199800278', '1201199900302'),
(14, '2020-12-04', '1201199800278', '1201199900302'),
(15, '2020-12-09', '1201199800278', '1201199900302'),
(16, '2020-12-09', '1206897456566', '1201199900302'),
(17, '2020-12-11', '1201199800278', '1201199900302'),
(18, '2020-12-11', '1201199800278', '1201199900302'),
(19, '2020-12-11', '1201199800278', '1201199900302'),
(20, '2020-12-11', '1201199800278', '1201199900302'),
(21, '2020-12-11', '1201199800278', '1201199900302');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `idGenero` int(11) NOT NULL,
  `genero` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`idGenero`, `genero`) VALUES
(1, 'Masculino'),
(2, 'Femenino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_medico`
--

CREATE TABLE `historial_medico` (
  `idHistorial` bigint(20) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `noConsulta` bigint(20) NOT NULL,
  `anamnesis` text NOT NULL,
  `diagnostico` text DEFAULT NULL,
  `tratamiento` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `historial_medico`
--

INSERT INTO `historial_medico` (`idHistorial`, `fechaCreacion`, `noConsulta`, `anamnesis`, `diagnostico`, `tratamiento`) VALUES
(1, '2020-12-18', 1, 'SIntomas fuertes de dolor de cabeza', 'Tomar dos panadol por 2 días', 'Tomar dos panadol por 2 días');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `idEmpleado` varchar(13) NOT NULL,
  `idEspecialidad` int(11) NOT NULL,
  `licenciaMedica` int(7) NOT NULL,
  `añosExperiencia` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medicos`
--

INSERT INTO `medicos` (`idEmpleado`, `idEspecialidad`, `licenciaMedica`, `añosExperiencia`) VALUES
('1201199800278', 1, 16564601, '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidades`
--

CREATE TABLE `nacionalidades` (
  `idNacionalidad` int(11) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `nacionalidades`
--

INSERT INTO `nacionalidades` (`idNacionalidad`, `nacionalidad`) VALUES
(1, 'Hondureña'),
(2, 'Estadounidense'),
(3, 'Guatemalteco'),
(4, 'Salvadoreño'),
(5, 'Japones'),
(6, 'Frances'),
(7, 'Holandes '),
(8, 'Cubano'),
(9, 'Ingles'),
(10, 'Nicaraguense'),
(11, 'Peruano'),
(12, 'Paraguayo'),
(13, 'Panameño'),
(14, 'Portugues'),
(15, 'Puertoriqueño'),
(16, 'Dominicano'),
(17, 'Ruso'),
(18, 'Rumano'),
(19, 'Sueco'),
(20, 'Tailandes'),
(21, 'Suizo'),
(22, 'Taiwandes'),
(23, 'Turco'),
(24, 'Ucraniano'),
(25, 'Venezolano'),
(26, 'Uruguayo'),
(27, 'Vitenamita'),
(28, 'Griego'),
(29, 'Español'),
(30, 'Filipino'),
(31, 'Danes'),
(32, 'Frances'),
(33, 'Etiope');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `idPaciente` varchar(13) NOT NULL,
  `nombres` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `idGenero` int(11) NOT NULL,
  `idNacionalidad` int(11) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `peso` varchar(5) NOT NULL,
  `altura` varchar(5) NOT NULL,
  `tipoSangre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`idPaciente`, `nombres`, `apellidos`, `fechaNacimiento`, `idGenero`, `idNacionalidad`, `direccion`, `peso`, `altura`, `tipoSangre`) VALUES
('0301199800256', 'María Alejandra', 'Zuniga', '1998-09-17', 2, 1, 'San Pedro Sula', '130', '130', 3),
('0302199800256', 'Daniel Alejandro', 'Mejía Perdonmo', '1998-08-15', 1, 1, 'Barrio San Juan, La Paz', '150', '165', 3),
('1201199900302', 'José Manuel', 'Castillo', '1999-01-25', 1, 1, 'La Paz', '150', '170', 2),
('1202199800269', 'J', 'M', '2020-12-11', 1, 1, '', '', '', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `idPrecioHis` int(11) NOT NULL,
  `fechaVencimiento` date NOT NULL,
  `stock` int(11) NOT NULL,
  `contenidoNeto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `idPrecioHis`, `fechaVencimiento`, `stock`, `contenidoNeto`) VALUES
(1213, 'Paracetamol', 3, '2021-04-15', 100, 50),
(1230, 'SUDAGRIP', 7, '2022-10-10', 50, 10),
(25258, 'Doloneurobión', 11, '2022-07-21', 100, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `idProveedor` varchar(13) NOT NULL,
  `RTN` int(14) NOT NULL,
  `nombreProveedor` varchar(50) NOT NULL,
  `nombreContacto` varchar(25) NOT NULL,
  `direccion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedor`, `RTN`, `nombreProveedor`, `nombreContacto`, `direccion`) VALUES
('1201', 12019, 'LABORATORIO SAN JORGE', 'FELIPE RUIZ', 'COMAYAGUELA, FRANCISCO MORAZA'),
('4646464', 646464, 'Drogeria Miguel', 'Miguel Sanchez', 'Avenida Junior, 4ta calle, San Pedro Sula'),
('4987979', 57849, 'DFGRG', 'DGDGD', 'BARRIO LA ALAMEDA, COMAYAGUA'),
('5689', 1201998, 'JETSTEREO', 'JOSUE GONZALES', 'BARRIO LA ALAMEDA, COMAYAGUA'),
('8985678', 464323, 'Grupo Clinilab', 'Oscar Torres', 'Barrio La Guadalupe, Tegucigalpa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `idServicio` int(11) NOT NULL,
  `servicio` varchar(40) NOT NULL,
  `precio` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`idServicio`, `servicio`, `precio`) VALUES
(1, 'Consulta Médica', '650'),
(2, 'Hospitalización', '1500'),
(3, 'Servicio de Ambulancia', '600'),
(4, 'Rayos X', '500'),
(5, 'Urgencias', '800'),
(6, 'Cuidados Intensivos', '1350');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `signos_vitales`
--

CREATE TABLE `signos_vitales` (
  `idSignosVitales` bigint(20) NOT NULL,
  `idHistorial` bigint(20) NOT NULL,
  `temperatura` varchar(3) NOT NULL,
  `presion` varchar(10) NOT NULL,
  `pulso` varchar(3) NOT NULL,
  `respiracion` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `signos_vitales`
--

INSERT INTO `signos_vitales` (`idSignosVitales`, `idHistorial`, `temperatura`, `presion`, `pulso`, `respiracion`) VALUES
(3, 1, '35', '135', '40', '60');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_empleados`
--

CREATE TABLE `telefonos_empleados` (
  `idTelefono` bigint(20) NOT NULL,
  `idEmpleado` varchar(13) NOT NULL,
  `telefono` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_empleados`
--

INSERT INTO `telefonos_empleados` (`idTelefono`, `idEmpleado`, `telefono`) VALUES
(25, '1201199800278', 27745689),
(26, '1201199800278', 88113504),
(33, '0302200200478', 27745689),
(41, '1201199900302', 99632145),
(42, '1202199702568', 97852314);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_pacientes`
--

CREATE TABLE `telefonos_pacientes` (
  `idTelefono` int(11) NOT NULL,
  `idPaciente` varchar(13) NOT NULL,
  `telefono` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_pacientes`
--

INSERT INTO `telefonos_pacientes` (`idTelefono`, `idPaciente`, `telefono`) VALUES
(16, '1201199900302', 32852493),
(17, '1201199900302', 27742347),
(18, '0301199800256', 25555689),
(19, '0302199800256', 27742347);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_proveedores`
--

CREATE TABLE `telefonos_proveedores` (
  `idTelefono` int(11) NOT NULL,
  `idProveedor` varchar(13) NOT NULL,
  `telefono` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_proveedores`
--

INSERT INTO `telefonos_proveedores` (`idTelefono`, `idProveedor`, `telefono`) VALUES
(1, '1201', 88745632),
(2, '5689', 27785632),
(4, '4646464', 25552378),
(5, '4646464', 99745687),
(6, '8985678', 27452310),
(7, '8985678', 98745623);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_correo`
--

CREATE TABLE `tipo_correo` (
  `idTipoCorreo` int(11) NOT NULL,
  `tipoCorreo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_correo`
--

INSERT INTO `tipo_correo` (`idTipoCorreo`, `tipoCorreo`) VALUES
(1, 'Personal'),
(2, 'Empresa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_empleado`
--

CREATE TABLE `tipo_empleado` (
  `idTipoEmpleado` int(11) NOT NULL,
  `tipoEmpleado` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_empleado`
--

INSERT INTO `tipo_empleado` (`idTipoEmpleado`, `tipoEmpleado`) VALUES
(1, 'Medico'),
(2, 'Enfermera'),
(3, 'Cajero'),
(4, 'Farmaceutico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_sangre`
--

CREATE TABLE `tipo_sangre` (
  `idSangre` int(11) NOT NULL,
  `tipoSangre` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_sangre`
--

INSERT INTO `tipo_sangre` (`idSangre`, `tipoSangre`) VALUES
(1, '-O'),
(2, '+O'),
(3, '+A'),
(4, '-A'),
(5, '-B'),
(6, '+B'),
(7, '-AB'),
(8, '+AB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `idEmpleado` varchar(13) NOT NULL,
  `nombreUsuario` varchar(20) NOT NULL,
  `contraseña` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `idEmpleado`, `nombreUsuario`, `contraseña`) VALUES
(3, '0302200200478', 'yarlenny.rodas', '396b127b4d939a2c23d8526206d09690'),
(10, '1201199900302', 'juanver', 'dd522ee57f0715c0724c3092abc60fdb'),
(11, '1202199702568', 'mariog74', 'd97665aa12c56ee1bdcd59e539f43016');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consultas_medicas`
--
ALTER TABLE `consultas_medicas`
  ADD PRIMARY KEY (`noConsulta`),
  ADD KEY `idPaciente` (`idPaciente`),
  ADD KEY `idMedico` (`idMedico`),
  ADD KEY `idEstadoConsulta` (`idEstadoConsulta`);

--
-- Indices de la tabla `correo_empleados`
--
ALTER TABLE `correo_empleados`
  ADD PRIMARY KEY (`idCorreo`,`idEmpleado`),
  ADD KEY `idEmpleado` (`idEmpleado`),
  ADD KEY `tipoCorreo` (`tipoCorreo`);

--
-- Indices de la tabla `correo_pacientes`
--
ALTER TABLE `correo_pacientes`
  ADD PRIMARY KEY (`idCorreo`,`idPaciente`),
  ADD KEY `idPaciente` (`idPaciente`),
  ADD KEY `tipoCorreo` (`tipoCorreo`);

--
-- Indices de la tabla `correo_proveedores`
--
ALTER TABLE `correo_proveedores`
  ADD PRIMARY KEY (`idCorreo`,`idProveedor`),
  ADD KEY `idProveedor` (`idProveedor`);

--
-- Indices de la tabla `detalle_facturacion`
--
ALTER TABLE `detalle_facturacion`
  ADD PRIMARY KEY (`idDetalle`,`idFacturacion`),
  ADD KEY `idFacturacion` (`idFacturacion`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `detalle_servicios`
--
ALTER TABLE `detalle_servicios`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `idFacturacion` (`idFacturacion`),
  ADD KEY `idServicio` (`idServicio`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`idEmpleado`),
  ADD KEY `idGenero` (`idGenero`),
  ADD KEY `empleados_ibfk_2` (`idNacionalidad`),
  ADD KEY `empleados_ibfk_3` (`tipoEmpleado`);

--
-- Indices de la tabla `enfermeras`
--
ALTER TABLE `enfermeras`
  ADD PRIMARY KEY (`idEmpleado`);

--
-- Indices de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  ADD PRIMARY KEY (`idEspecialidad`);

--
-- Indices de la tabla `estado_consultas`
--
ALTER TABLE `estado_consultas`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `fabricantes`
--
ALTER TABLE `fabricantes`
  ADD PRIMARY KEY (`idFabricante`,`idProducto`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  ADD PRIMARY KEY (`idFacturacion`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`idGenero`);

--
-- Indices de la tabla `historial_medico`
--
ALTER TABLE `historial_medico`
  ADD PRIMARY KEY (`idHistorial`),
  ADD KEY `noConsulta` (`noConsulta`);

--
-- Indices de la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD PRIMARY KEY (`idEmpleado`),
  ADD KEY `idEspecialidad` (`idEspecialidad`);

--
-- Indices de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  ADD PRIMARY KEY (`idNacionalidad`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idPaciente`),
  ADD KEY `idNacionalidad` (`idNacionalidad`),
  ADD KEY `tipoSangre` (`tipoSangre`),
  ADD KEY `idGenero` (`idGenero`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`idProveedor`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`idServicio`);

--
-- Indices de la tabla `signos_vitales`
--
ALTER TABLE `signos_vitales`
  ADD PRIMARY KEY (`idSignosVitales`,`idHistorial`),
  ADD KEY `idHistorial` (`idHistorial`);

--
-- Indices de la tabla `telefonos_empleados`
--
ALTER TABLE `telefonos_empleados`
  ADD PRIMARY KEY (`idTelefono`,`idEmpleado`),
  ADD KEY `idEmpleado` (`idEmpleado`);

--
-- Indices de la tabla `telefonos_pacientes`
--
ALTER TABLE `telefonos_pacientes`
  ADD PRIMARY KEY (`idTelefono`,`idPaciente`),
  ADD KEY `idPaciente` (`idPaciente`);

--
-- Indices de la tabla `telefonos_proveedores`
--
ALTER TABLE `telefonos_proveedores`
  ADD PRIMARY KEY (`idTelefono`,`idProveedor`),
  ADD KEY `idProveedor` (`idProveedor`);

--
-- Indices de la tabla `tipo_correo`
--
ALTER TABLE `tipo_correo`
  ADD PRIMARY KEY (`idTipoCorreo`);

--
-- Indices de la tabla `tipo_empleado`
--
ALTER TABLE `tipo_empleado`
  ADD PRIMARY KEY (`idTipoEmpleado`);

--
-- Indices de la tabla `tipo_sangre`
--
ALTER TABLE `tipo_sangre`
  ADD PRIMARY KEY (`idSangre`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD KEY `usuarios_ibfk_1` (`idEmpleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consultas_medicas`
--
ALTER TABLE `consultas_medicas`
  MODIFY `noConsulta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `correo_empleados`
--
ALTER TABLE `correo_empleados`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `correo_pacientes`
--
ALTER TABLE `correo_pacientes`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `correo_proveedores`
--
ALTER TABLE `correo_proveedores`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `detalle_facturacion`
--
ALTER TABLE `detalle_facturacion`
  MODIFY `idDetalle` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `detalle_servicios`
--
ALTER TABLE `detalle_servicios`
  MODIFY `idDetalle` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  MODIFY `idEspecialidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `estado_consultas`
--
ALTER TABLE `estado_consultas`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `fabricantes`
--
ALTER TABLE `fabricantes`
  MODIFY `idFabricante` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `facturacion`
--
ALTER TABLE `facturacion`
  MODIFY `idFacturacion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `idGenero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `nacionalidades`
--
ALTER TABLE `nacionalidades`
  MODIFY `idNacionalidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25259;

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `idServicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `signos_vitales`
--
ALTER TABLE `signos_vitales`
  MODIFY `idSignosVitales` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `telefonos_empleados`
--
ALTER TABLE `telefonos_empleados`
  MODIFY `idTelefono` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `telefonos_pacientes`
--
ALTER TABLE `telefonos_pacientes`
  MODIFY `idTelefono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `telefonos_proveedores`
--
ALTER TABLE `telefonos_proveedores`
  MODIFY `idTelefono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tipo_correo`
--
ALTER TABLE `tipo_correo`
  MODIFY `idTipoCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_empleado`
--
ALTER TABLE `tipo_empleado`
  MODIFY `idTipoEmpleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipo_sangre`
--
ALTER TABLE `tipo_sangre`
  MODIFY `idSangre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consultas_medicas`
--
ALTER TABLE `consultas_medicas`
  ADD CONSTRAINT `consultas_medicas_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`),
  ADD CONSTRAINT `consultas_medicas_ibfk_2` FOREIGN KEY (`idMedico`) REFERENCES `empleados` (`idEmpleado`),
  ADD CONSTRAINT `consultas_medicas_ibfk_3` FOREIGN KEY (`idEstadoConsulta`) REFERENCES `estado_consultas` (`idEstado`);

--
-- Filtros para la tabla `correo_empleados`
--
ALTER TABLE `correo_empleados`
  ADD CONSTRAINT `correo_empleados_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`) ON DELETE CASCADE,
  ADD CONSTRAINT `correo_empleados_ibfk_2` FOREIGN KEY (`tipoCorreo`) REFERENCES `tipo_correo` (`idTipoCorreo`);

--
-- Filtros para la tabla `correo_pacientes`
--
ALTER TABLE `correo_pacientes`
  ADD CONSTRAINT `correo_pacientes_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`) ON DELETE CASCADE,
  ADD CONSTRAINT `correo_pacientes_ibfk_2` FOREIGN KEY (`tipoCorreo`) REFERENCES `tipo_correo` (`idTipoCorreo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `correo_proveedores`
--
ALTER TABLE `correo_proveedores`
  ADD CONSTRAINT `correo_proveedores_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detalle_facturacion`
--
ALTER TABLE `detalle_facturacion`
  ADD CONSTRAINT `detalle_facturacion_ibfk_1` FOREIGN KEY (`idFacturacion`) REFERENCES `facturacion` (`idFacturacion`),
  ADD CONSTRAINT `detalle_facturacion_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `detalle_servicios`
--
ALTER TABLE `detalle_servicios`
  ADD CONSTRAINT `detalle_servicios_ibfk_1` FOREIGN KEY (`idFacturacion`) REFERENCES `facturacion` (`idFacturacion`),
  ADD CONSTRAINT `detalle_servicios_ibfk_2` FOREIGN KEY (`idServicio`) REFERENCES `servicios` (`idServicio`);

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`),
  ADD CONSTRAINT `empleados_ibfk_3` FOREIGN KEY (`tipoEmpleado`) REFERENCES `tipo_empleado` (`idTipoEmpleado`);

--
-- Filtros para la tabla `enfermeras`
--
ALTER TABLE `enfermeras`
  ADD CONSTRAINT `enfermeras_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`);

--
-- Filtros para la tabla `fabricantes`
--
ALTER TABLE `fabricantes`
  ADD CONSTRAINT `fabricantes_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `historial_medico`
--
ALTER TABLE `historial_medico`
  ADD CONSTRAINT `historial_medico_ibfk_1` FOREIGN KEY (`noConsulta`) REFERENCES `consultas_medicas` (`noConsulta`);

--
-- Filtros para la tabla `medicos`
--
ALTER TABLE `medicos`
  ADD CONSTRAINT `medicos_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`),
  ADD CONSTRAINT `medicos_ibfk_2` FOREIGN KEY (`idEspecialidad`) REFERENCES `especialidades` (`idEspecialidad`);

--
-- Filtros para la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD CONSTRAINT `pacientes_ibfk_1` FOREIGN KEY (`idNacionalidad`) REFERENCES `nacionalidades` (`idNacionalidad`),
  ADD CONSTRAINT `pacientes_ibfk_2` FOREIGN KEY (`tipoSangre`) REFERENCES `tipo_sangre` (`idSangre`),
  ADD CONSTRAINT `pacientes_ibfk_3` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`);

--
-- Filtros para la tabla `signos_vitales`
--
ALTER TABLE `signos_vitales`
  ADD CONSTRAINT `signos_vitales_ibfk_1` FOREIGN KEY (`idHistorial`) REFERENCES `historial_medico` (`idHistorial`);

--
-- Filtros para la tabla `telefonos_empleados`
--
ALTER TABLE `telefonos_empleados`
  ADD CONSTRAINT `telefonos_empleados_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`) ON DELETE CASCADE;

--
-- Filtros para la tabla `telefonos_pacientes`
--
ALTER TABLE `telefonos_pacientes`
  ADD CONSTRAINT `telefonos_pacientes_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`) ON DELETE CASCADE;

--
-- Filtros para la tabla `telefonos_proveedores`
--
ALTER TABLE `telefonos_proveedores`
  ADD CONSTRAINT `telefonos_proveedores_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
