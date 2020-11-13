-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2020 a las 22:40:56
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
  `idPaciente` int(14) NOT NULL,
  `idMedico` int(14) NOT NULL,
  `motivo` text NOT NULL,
  `idEstadoConsulta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `consultas_medicas`
--

INSERT INTO `consultas_medicas` (`noConsulta`, `fechaCreacion`, `fechaConsulta`, `horaConsulta`, `idPaciente`, `idMedico`, `motivo`, `idEstadoConsulta`) VALUES
(1, '2020-11-13', '2020-11-27', '08:30:00', 1201199, 1205, 'DOLOR FUERTE DE ESTMAGO', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_empleados`
--

CREATE TABLE `correo_empleados` (
  `idCorreo` int(11) NOT NULL,
  `idEmpleado` int(14) NOT NULL,
  `correo` varchar(35) NOT NULL,
  `tipoCorreo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_empleados`
--

INSERT INTO `correo_empleados` (`idCorreo`, `idEmpleado`, `correo`, `tipoCorreo`) VALUES
(1, 1213, 'ana.castillo@gmail.com', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_pacientes`
--

CREATE TABLE `correo_pacientes` (
  `idCorreo` int(11) NOT NULL,
  `idPaciente` int(14) NOT NULL,
  `correo` varchar(35) NOT NULL,
  `tipoCorreo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_pacientes`
--

INSERT INTO `correo_pacientes` (`idCorreo`, `idPaciente`, `correo`, `tipoCorreo`) VALUES
(1, 1201199, 'emiflow@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_proveedores`
--

CREATE TABLE `correo_proveedores` (
  `idCorreo` int(11) NOT NULL,
  `idProveedor` int(14) NOT NULL,
  `correo` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `correo_proveedores`
--

INSERT INTO `correo_proveedores` (`idCorreo`, `idProveedor`, `correo`) VALUES
(1, 1201, 'feliperuiz45@yahoo.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_facturacion`
--

CREATE TABLE `detalle_facturacion` (
  `idDetalle` bigint(20) NOT NULL,
  `idFacturacion` bigint(20) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `precio` double NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `idEmpleado` int(14) NOT NULL,
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
(1205, 'MARTHA', 'ISAULA', '1999-05-15', 2, 2, 'BARRIO SAN JUAN', 2),
(1206, 'JOSE MANUEL', 'CASTILLO', '1999-01-25', 1, 1, 'BARRIO ARRIBA', 1),
(1207, 'MANUEL ORLANDO', 'FIALLOS', '2000-10-25', 1, 5, 'YARUMELA', 3),
(1210, 'ALEX DAVID', 'VASQUEZ', '1999-12-12', 1, 3, 'BARRIO LA GRANJA, LA PAZ', 3),
(1211, 'ALEX DAVID', 'VASQUEZ', '1999-12-12', 1, 3, 'BARRIO LA GRANJA, LA PAZ', 3),
(1213, 'ANA GABRIELA', 'CASTILLO', '1998-10-26', 2, 1, 'BARRIO LA CONCEPCION, LA PAZ', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfermeras`
--

CREATE TABLE `enfermeras` (
  `idEmpleado` int(14) NOT NULL,
  `idEspecialidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `idEmpleado` int(12) NOT NULL,
  `idPaciente` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, '2020-11-13', 1, 'EL PACIENTE PADECE DOLORES DE CABEZA MUY\nFUERTES.', 'TOMAR DOS PANADOL AL DIA POR TRES DIAS', 'TOMAR DOS PANADOL AL DIA POR TRES DIAS'),
(115, '2020-11-13', 1, 'EL PACIENTE PADECE DOLORES DE CABEZA FUERTES', 'TOMAR 2 PANADOL AL DIA POR 1 SEMANA', 'TOMAR 2 PANADOL AL DIA POR 1 SEMANA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicos`
--

CREATE TABLE `medicos` (
  `idEmpleado` int(14) NOT NULL,
  `idEspecialidad` int(11) NOT NULL,
  `licenciaMedica` int(7) NOT NULL,
  `añosExperiencia` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `idPaciente` int(14) NOT NULL,
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
(1201199, 'EDUARDO EMILIANO', 'SUAZO MURILLO', '2003-06-16', 1, 1, 'BARRIO SAN JUAN, LA PAZ', '170', '1.68', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `idPrecioHis` int(11) NOT NULL,
  `fechaVencimiento` date NOT NULL,
  `stock` varchar(50) NOT NULL,
  `contenidoNeto` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `idPrecioHis`, `fechaVencimiento`, `stock`, `contenidoNeto`) VALUES
(1230, 'SUDAGRIP', 7, '2022-10-10', '50', '10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `idProveedor` int(14) NOT NULL,
  `RTN` int(14) NOT NULL,
  `nombreProveedor` varchar(50) NOT NULL,
  `nombreContacto` varchar(25) NOT NULL,
  `direccion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`idProveedor`, `RTN`, `nombreProveedor`, `nombreContacto`, `direccion`) VALUES
(1201, 12019, 'LABORATORIO SAN JORGE', 'FELIPE RUIZ', 'COMAYAGUELA, FRANCISCO MORAZA');

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
(1, 115, '20', '15', '50', '40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_empleados`
--

CREATE TABLE `telefonos_empleados` (
  `idTelefono` bigint(20) NOT NULL,
  `idEmpleado` int(14) NOT NULL,
  `telefono` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_empleados`
--

INSERT INTO `telefonos_empleados` (`idTelefono`, `idEmpleado`, `telefono`) VALUES
(1, 1205, 27742347),
(2, 1207, 27746358),
(3, 1213, 99856321);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_pacientes`
--

CREATE TABLE `telefonos_pacientes` (
  `idTelefono` int(11) NOT NULL,
  `idPaciente` int(14) NOT NULL,
  `telefono` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_pacientes`
--

INSERT INTO `telefonos_pacientes` (`idTelefono`, `idPaciente`, `telefono`) VALUES
(1, 1201199, 32654712);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_proveedores`
--

CREATE TABLE `telefonos_proveedores` (
  `idTelefono` int(11) NOT NULL,
  `idProveedor` int(14) NOT NULL,
  `telefono` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `telefonos_proveedores`
--

INSERT INTO `telefonos_proveedores` (`idTelefono`, `idProveedor`, `telefono`) VALUES
(1, 1201, 88745632);

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
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `idTipoUsuario` int(11) NOT NULL,
  `tipoUsuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `idEmpleado` int(14) NOT NULL,
  `tipoUsuario` int(4) NOT NULL,
  `contraseña` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`idEmpleado`);

--
-- Indices de la tabla `enfermeras`
--
ALTER TABLE `enfermeras`
  ADD PRIMARY KEY (`idEmpleado`);

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
  ADD PRIMARY KEY (`idEmpleado`);

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
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`idTipoUsuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consultas_medicas`
--
ALTER TABLE `consultas_medicas`
  MODIFY `noConsulta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `correo_empleados`
--
ALTER TABLE `correo_empleados`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `correo_pacientes`
--
ALTER TABLE `correo_pacientes`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `correo_proveedores`
--
ALTER TABLE `correo_proveedores`
  MODIFY `idCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_facturacion`
--
ALTER TABLE `detalle_facturacion`
  MODIFY `idDetalle` bigint(20) NOT NULL AUTO_INCREMENT;

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
  MODIFY `idFacturacion` bigint(20) NOT NULL AUTO_INCREMENT;

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
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1231;

--
-- AUTO_INCREMENT de la tabla `signos_vitales`
--
ALTER TABLE `signos_vitales`
  MODIFY `idSignosVitales` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `telefonos_empleados`
--
ALTER TABLE `telefonos_empleados`
  MODIFY `idTelefono` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `telefonos_pacientes`
--
ALTER TABLE `telefonos_pacientes`
  MODIFY `idTelefono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `telefonos_proveedores`
--
ALTER TABLE `telefonos_proveedores`
  MODIFY `idTelefono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tipo_correo`
--
ALTER TABLE `tipo_correo`
  MODIFY `idTipoCorreo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tipo_empleado`
--
ALTER TABLE `tipo_empleado`
  MODIFY `idTipoEmpleado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_sangre`
--
ALTER TABLE `tipo_sangre`
  MODIFY `idSangre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `idTipoUsuario` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `correo_empleados_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`),
  ADD CONSTRAINT `correo_empleados_ibfk_2` FOREIGN KEY (`tipoCorreo`) REFERENCES `tipo_correo` (`idTipoCorreo`);

--
-- Filtros para la tabla `correo_pacientes`
--
ALTER TABLE `correo_pacientes`
  ADD CONSTRAINT `correo_pacientes_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`),
  ADD CONSTRAINT `correo_pacientes_ibfk_2` FOREIGN KEY (`tipoCorreo`) REFERENCES `tipo_correo` (`idTipoCorreo`);

--
-- Filtros para la tabla `correo_proveedores`
--
ALTER TABLE `correo_proveedores`
  ADD CONSTRAINT `correo_proveedores_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`);

--
-- Filtros para la tabla `detalle_facturacion`
--
ALTER TABLE `detalle_facturacion`
  ADD CONSTRAINT `detalle_facturacion_ibfk_1` FOREIGN KEY (`idFacturacion`) REFERENCES `facturacion` (`idFacturacion`),
  ADD CONSTRAINT `detalle_facturacion_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`);

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
  ADD CONSTRAINT `medicos_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`);

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
  ADD CONSTRAINT `telefonos_empleados_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`);

--
-- Filtros para la tabla `telefonos_pacientes`
--
ALTER TABLE `telefonos_pacientes`
  ADD CONSTRAINT `telefonos_pacientes_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`);

--
-- Filtros para la tabla `telefonos_proveedores`
--
ALTER TABLE `telefonos_proveedores`
  ADD CONSTRAINT `telefonos_proveedores_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedores` (`idProveedor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
