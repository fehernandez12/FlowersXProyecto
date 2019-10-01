-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-10-2019 a las 19:00:50
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `flowersx`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `idciudad` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idPais` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`idciudad`, `nombre`, `idPais`) VALUES
(1, 'Bogotá D.C.', 8),
(2, 'Nueva York', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedad`
--

CREATE TABLE `novedad` (
  `idNovedad` int(5) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha` date NOT NULL,
  `Pedido_idPedido` int(5) NOT NULL,
  `Usuario_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenproduccion`
--

CREATE TABLE `ordenproduccion` (
  `idOrdenProduccion` int(5) NOT NULL,
  `fechainicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `Pedido_idPedido` int(5) NOT NULL,
  `Usuario_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `idPago` int(5) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `codigoDeSeguridad` varchar(45) NOT NULL,
  `fechaDeVencimiento` varchar(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `Pedido_idPedido` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE `pais` (
  `idpais` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_ingles` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`idpais`, `nombre`, `nombre_ingles`) VALUES
(2, 'Australia', 'Australia'),
(3, 'Austria', 'Austria'),
(4, 'Brasil', 'Brazil'),
(5, 'Canadá', 'Canada'),
(6, 'Catar', 'Qatar'),
(7, 'Chile', 'Chile'),
(8, 'Colombia', 'Colombia'),
(9, 'Emiratos Árabes Unidos', 'United Arab Emirates'),
(10, 'España', 'Spain'),
(11, 'Estados Unidos', 'United States'),
(12, 'Francia', 'France'),
(13, 'Italia', 'Italy'),
(14, 'Mónaco', 'Monaco'),
(15, 'Países Bajos', 'Netherlands'),
(16, 'Portugal', 'Portugal'),
(17, 'Reino Unido', 'United Kingdom'),
(18, 'Rusia', 'Russia'),
(19, 'Sudáfrica', 'South Africa'),
(20, 'Suecia', 'Sweden'),
(21, 'Suiza', 'Switzerland'),
(22, 'Uruguay', 'Uruguay'),
(24, 'Alemania', 'Germany');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idPedido` int(5) NOT NULL,
  `fechaDeCreacion` date NOT NULL,
  `fechaDeEntrega` date NOT NULL,
  `subTotal` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `Usuario_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`idPedido`, `fechaDeCreacion`, `fechaDeEntrega`, `subTotal`, `total`, `Usuario_id`) VALUES
(1, '2019-09-22', '2019-12-22', 250000, 300000, 12),
(2, '2019-09-23', '2019-09-23', 0, 0, 12),
(3, '2019-09-23', '2019-09-23', 0, 0, 12),
(4, '2019-09-23', '2019-09-23', 0, 0, 12),
(5, '2019-09-23', '2019-09-23', 0, 0, 12),
(6, '2019-10-01', '2019-10-01', 0, 0, 12),
(7, '2019-10-01', '2019-10-01', 0, 0, 12),
(8, '2019-10-01', '2019-10-01', 0, 0, 12),
(9, '2019-02-01', '2019-02-01', 950, 1121, 12),
(10, '2020-01-01', '2020-01-01', 450, 531, 12),
(11, '2020-01-01', '2020-01-01', 650, 767, 12),
(12, '2020-04-01', '2020-04-01', 650, 767, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `idpermisos` int(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_en` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `icon` varchar(45) DEFAULT NULL,
  `permiso_padre` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`idpermisos`, `nombre`, `nombre_en`, `url`, `icon`, `permiso_padre`) VALUES
(1, 'Usuarios', 'Users', NULL, NULL, NULL),
(2, 'Solicitudes', 'Requests', NULL, NULL, NULL),
(3, 'Producción', 'Production', NULL, NULL, NULL),
(4, 'Catálogo', 'Catalog', NULL, NULL, NULL),
(5, 'Crear Usuario', 'Create user', 'Admin/crear-usuario.xhtml', 'x', 1),
(6, 'Gestionar Usuarios', 'Manage users', 'Admin/gestionar-usuarios.xhtml', 'x', 1),
(7, 'Crear Solicitud', 'Create request', 'Admin/crear-solicitud.xhtml', 'x', 2),
(8, 'Gestionar Solicitudes', 'Manage requests', 'Admin/gestionar-solicitudes.xhtml', 'x', 2),
(9, 'Consultar órdenes de producción', 'Search production orders', 'Admin/gestionar-ordenProduccion-admin.xhtml', 'x', 3),
(10, 'Crear Novedad', 'Create novelty', 'Admin/crear-novedad.xhtml', 'x', 3),
(11, 'Gestionar Novedades', 'Manage novelties', 'Admin/gestionar-novedades.xhtml', 'x', 3),
(12, 'Registrar producto', 'Add product', 'Admin/crear-catalogo.xhtml', 'x', 4),
(13, 'Gestionar Catálogo', 'Manage catalog', 'Admin/gestionar-catalogo.xhtml', 'x', 4),
(14, 'Nueva orden de producción', 'Create production order', 'Ingeniero/crear-ordenProduccion.xhtml', 'x', 3),
(15, 'Gestionar órdenes de producción', 'Manage production orders', 'Ingeniero/gestionar-ordenProduccion.xhtml', 'x', 3),
(17, 'Ventas', 'Sales', '', '', NULL),
(18, 'Nuevo pedido', 'New sales order', 'Vendedor/crear-pedido-vendedor.xhtml', 'x', 17),
(19, 'Consultar pedidos', 'Search sales orders', 'Vendedor/gestionar-pedido-vendedor.xhtml', 'x', 17),
(20, 'Pagos', 'Payments', NULL, NULL, NULL),
(21, 'Registrar método de pago', 'Add payment method', 'Cliente/crear-pago.xhtml', 'x', 20),
(22, 'Gestionar métodos de pago', 'Manage payment methods', 'Cliente/gestionar-pago.xhtml', 'x', 20),
(23, 'Comprar', 'Buy', NULL, NULL, NULL),
(24, 'Nuevo pedido', 'New order', 'Cliente/nuevo-pedido.xhtml', 'x', 23),
(25, 'Consultar pedidos', 'Search orders', 'Cliente/gestionar-pedido.xhtml', 'x', 23),
(26, 'Consultar catálogo', 'Catalog lookup', 'Cliente/consultar-catalogo.xhtml', 'x', 4),
(27, 'Consultar pedidos', 'Search orders', 'Admin/consultar-pedidos.xhtml', 'x', 3),
(28, 'Consultar pedidos', 'Search orders', 'Ingeniero/consultar-pedidos.xhtml', 'x', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(5) NOT NULL,
  `nombreProducto` varchar(45) NOT NULL,
  `foto` text NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `tiempoDeCultivo` int(11) NOT NULL,
  `existencias` int(10) NOT NULL,
  `precio` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombreProducto`, `foto`, `descripcion`, `tiempoDeCultivo`, `existencias`, `precio`) VALUES
(1, 'Rosas', '/FlowersXProyecto/Archivos/rosas.jpg', 'Varios colores disponibles', 3, 50000, 300),
(2, 'Orquídeas', '/FlowersXProyecto/Archivos/orchid.jpg', 'Varios colores disponibles', 6, 20000, 200),
(3, 'Girasoles', '/FlowersXProyecto/Archivos/sunflower.jpg', 'Varios colores disponibles', 3, 80000, 150),
(4, 'Tulipanes', '/FlowersXProyecto/Archivos/tulip.jpg', 'Varios colores disponibles', 4, 0, 250),
(5, 'Azucenas', '/FlowersXProyecto/Archivos/azucena.jpg', 'Varios colores disponibles.', 4, 500, 300),
(6, 'Violetas', '/FlowersXProyecto/Archivos/violeta.jpg', 'Disponible en un solo color.', 2, 0, 250),
(7, 'Claveles', '/FlowersXProyecto/Archivos/flor-clavel.jpg', 'Varios colores disponibles', 4, 600, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_has_pedido`
--

CREATE TABLE `producto_has_pedido` (
  `producto_idProducto` int(5) NOT NULL,
  `pedido_idPedido` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto_has_pedido`
--

INSERT INTO `producto_has_pedido` (`producto_idProducto`, `pedido_idPedido`) VALUES
(1, 1),
(1, 10),
(1, 11),
(1, 12),
(2, 1),
(2, 11),
(2, 12),
(3, 1),
(3, 10),
(3, 11),
(3, 12),
(5, 1),
(7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `nombre`, `name`) VALUES
(1, 'Administrador', 'Administrator'),
(2, 'Supervisor de siembra', 'Seeds Supervisor'),
(3, 'Ingeniero de siembra', 'Planting Engineer'),
(4, 'Vendedor', 'Seller'),
(5, 'Cliente', 'Customer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_has_permisos`
--

CREATE TABLE `rol_has_permisos` (
  `Rol_idRol` int(5) NOT NULL,
  `permisos_idpermisos` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol_has_permisos`
--

INSERT INTO `rol_has_permisos` (`Rol_idRol`, `permisos_idpermisos`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 27),
(2, 2),
(2, 3),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 28),
(3, 3),
(3, 14),
(3, 15),
(4, 17),
(4, 18),
(4, 19),
(5, 4),
(5, 20),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25),
(5, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `idSolicitud` int(5) NOT NULL,
  `fecha` date NOT NULL,
  `destinatario` varchar(45) NOT NULL,
  `Pedido_idPedido` int(5) NOT NULL,
  `Usuario_id` int(5) NOT NULL,
  `soporte1` text NOT NULL,
  `soporte2` text,
  `soporte3` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`idSolicitud`, `fecha`, `destinatario`, `Pedido_idPedido`, `Usuario_id`, `soporte1`, `soporte2`, `soporte3`) VALUES
(1, '2019-08-19', 'fehernandez12@misena.edu.co', 1, 1, '/FlowersX/Archivos/CQYYPWK1YQPZN42', '/FlowersX/Archivos/APVW88RB8Z5Y4FA', '/FlowersX/Archivos/0G821B95H77PCF2'),
(2, '2019-08-19', 'fehernandez12@misena.edu.co', 1, 1, '/FlowersX/Archivos/FFJ8I4HZ3DIBZ63', '/FlowersX/Archivos/W6577CQOEYMNB4M', '/FlowersX/Archivos/IK9UOCRYUXDKK62'),
(3, '2019-08-19', 'fehernandez12@misena.edu.co', 1, 1, '/FlowersX/Archivos/65MYQ8ZZSLR1NOZ', '/FlowersX/Archivos/XJZECG82QHGT3RK', '/FlowersX/Archivos/9ZAON0G2XCWDKHT'),
(4, '2018-12-11', 'feehernandezba@gmail.com', 1, 1, '/FlowersX/Archivos/LCXM7SQDV1ZLLMF', '/FlowersX/Archivos/J0WTTMRFFQR6S3Z', '/FlowersX/Archivos/15A7NRLNK4VKADB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(5) NOT NULL,
  `titular` varchar(45) NOT NULL,
  `razonSocial` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL,
  `Rol_idRol` int(5) NOT NULL,
  `pais_idpais` int(11) NOT NULL,
  `ciudad_idciudad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `titular`, `razonSocial`, `email`, `password`, `estado`, `Rol_idRol`, `pais_idpais`, `ciudad_idciudad`) VALUES
(1, 'Felipe Hernández', 'FlowersX', 'feehernandezba@gmail.com', 'a873de377ffbd389c31e5fc9f5c6dcd8', 1, 1, 8, 1),
(3, 'Santiago Ruiz', 'SENA', 'sruiz8864@misena.edu.co', 'XRO31ZLzk9', 0, 5, 8, 1),
(4, 'Sergio Quintero', 'SENA', 'saquintero074@misena.edu.co', '7qLhDRZEye', 0, 5, 8, 1),
(5, 'Jonathan Patiño', 'SENA', 'japatino967@misena.edu.co', 'ge4lyDur5H', 0, 5, 8, 1),
(6, 'Santiago Reyes', 'SENA', 'sreyes98@misena.edu.co', '123456789', 0, 5, 8, 1),
(7, 'Santiago Salinas', 'SENA', 'dssalinas5@misena.edu.co', 'gv27tbNduB', 0, 5, 8, 1),
(8, 'Diego Arenas', 'SENA', 'daarenas83@misena.edu.co', 'Ec1x6m4Sjv', 0, 5, 8, 1),
(9, 'Valentina Marín', 'SENA', 'dvmarin3@misena.edu.co', 'SGnCP5rBgf', 0, 1, 8, 1),
(12, 'Shirley Bernal', 'SENA', 'stbernal0@misena.edu.co', '25f9e794323b453885f5181f1b624d0b', 0, 5, 8, 1),
(14, 'Juan Pablo Rodríguez', 'SENA', 'jprodriguez744@misena.edu.co', '0ed48cf6dc34f1f72a141a8014545629', 0, 3, 8, 1),
(15, 'Jonathan Patiño', 'SENA', 'japatino967@misena.edu.co', '24c05dc3d1ad0d0c705cdbf96ac04551', 0, 5, 8, 1),
(16, 'Esteban Hernández', 'FlowersX', 'fehernandez12@misena.edu.co', 'e17021b4daeac324281163cc77d21e00', 0, 1, 8, 1),
(17, 'Shirley Bernal', 'SENA', 'shirleybernal70@gmail.com', '55bd47587094e64f54103647e536505e', 0, 5, 8, 1),
(19, 'Santiago Reyes', 'SENA', 'sreyes98@misena.edu.co', '1f74398941cae3bce7e71d675790593a', 0, 5, 8, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`idciudad`),
  ADD KEY `FK_Pais` (`idPais`);

--
-- Indices de la tabla `novedad`
--
ALTER TABLE `novedad`
  ADD PRIMARY KEY (`idNovedad`),
  ADD KEY `fk_Novedad_Pedido1_idx` (`Pedido_idPedido`),
  ADD KEY `fk_Novedad_Usuario1_idx` (`Usuario_id`);

--
-- Indices de la tabla `ordenproduccion`
--
ALTER TABLE `ordenproduccion`
  ADD PRIMARY KEY (`idOrdenProduccion`),
  ADD KEY `fk_OrdenDeProduccion_Pedido1_idx` (`Pedido_idPedido`),
  ADD KEY `fk_OrdenDeProduccion_Usuario1_idx` (`Usuario_id`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`idPago`),
  ADD KEY `fk_Pago_Pedido1_idx` (`Pedido_idPedido`);

--
-- Indices de la tabla `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`idpais`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `fk_Pedido_Usuario1_idx` (`Usuario_id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`idpermisos`),
  ADD KEY `fk_permiso_padre` (`permiso_padre`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `producto_has_pedido`
--
ALTER TABLE `producto_has_pedido`
  ADD PRIMARY KEY (`producto_idProducto`,`pedido_idPedido`),
  ADD KEY `fk_producto_has_pedido_pedido1_idx` (`pedido_idPedido`),
  ADD KEY `fk_producto_has_pedido_producto1_idx` (`producto_idProducto`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `rol_has_permisos`
--
ALTER TABLE `rol_has_permisos`
  ADD PRIMARY KEY (`Rol_idRol`,`permisos_idpermisos`),
  ADD KEY `fk_Rol_has_permisos_permisos1_idx` (`permisos_idpermisos`),
  ADD KEY `fk_Rol_has_permisos_Rol1_idx` (`Rol_idRol`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`idSolicitud`),
  ADD KEY `fk_Solicitud_Pedido1_idx` (`Pedido_idPedido`),
  ADD KEY `fk_Solicitud_Usuario1_idx` (`Usuario_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Usuario_Rol1_idx` (`Rol_idRol`),
  ADD KEY `fk_usuario_pais1_idx` (`pais_idpais`),
  ADD KEY `fk_usuario_ciudad1_idx` (`ciudad_idciudad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `idciudad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `novedad`
--
ALTER TABLE `novedad`
  MODIFY `idNovedad` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ordenproduccion`
--
ALTER TABLE `ordenproduccion`
  MODIFY `idOrdenProduccion` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `idPago` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pais`
--
ALTER TABLE `pais`
  MODIFY `idpais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idPedido` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `idSolicitud` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `FK_Pais` FOREIGN KEY (`idPais`) REFERENCES `pais` (`idpais`);

--
-- Filtros para la tabla `novedad`
--
ALTER TABLE `novedad`
  ADD CONSTRAINT `fk_Novedad_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `fk_Novedad_Usuario1` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `ordenproduccion`
--
ALTER TABLE `ordenproduccion`
  ADD CONSTRAINT `fk_OrdenDeProduccion_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `fk_OrdenDeProduccion_Usuario1` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `fk_Pago_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_Pedido_Usuario1` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `fk_permiso_padre` FOREIGN KEY (`permiso_padre`) REFERENCES `permisos` (`idpermisos`);

--
-- Filtros para la tabla `producto_has_pedido`
--
ALTER TABLE `producto_has_pedido`
  ADD CONSTRAINT `fk_producto_has_pedido_pedido1` FOREIGN KEY (`pedido_idPedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `fk_producto_has_pedido_producto1` FOREIGN KEY (`producto_idProducto`) REFERENCES `producto` (`idProducto`);

--
-- Filtros para la tabla `rol_has_permisos`
--
ALTER TABLE `rol_has_permisos`
  ADD CONSTRAINT `fk_Rol_has_permisos_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `fk_Rol_has_permisos_permisos1` FOREIGN KEY (`permisos_idpermisos`) REFERENCES `permisos` (`idpermisos`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_Usuario_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `fk_usuario_ciudad1` FOREIGN KEY (`ciudad_idciudad`) REFERENCES `ciudad` (`idciudad`),
  ADD CONSTRAINT `fk_usuario_pais1` FOREIGN KEY (`pais_idpais`) REFERENCES `pais` (`idpais`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
