-- Datos iniciales de prueba para la base tecno_raee.
-- Ejecutar manualmente sobre una base con las tablas creadas y todavía vacías.
-- No borrar ni truncar datos: las tablas se cargan en orden de dependencias.
-- Las contraseñas son texto de prueba, no hashes; el proyecto no implementa autenticación/login.

USE `tecno_raee`;

-- Roles: IDs fijos referenciados por Personas.
INSERT INTO `Roles` (`id_rol`, `nombre`) VALUES
    (1, 'Cliente'),
    (2, 'Personal Convocatoria');

-- Categorías: IDs fijos referenciados por RAEES.
INSERT INTO `Categorias` (`id_categoria`, `nombre`) VALUES
    (1, 'Informática'),
    (2, 'Telefonía móvil'),
    (3, 'Periféricos'),
    (4, 'Impresión');

-- Personas: IDs explícitos para las llamadas de prueba a los endpoints.
-- Ana: cliente.demo / ClienteDemo2026! (valor almacenado en texto plano).
-- Bruno: personal.demo / PersonalDemo2026! (valor almacenado en texto plano).
-- Carla y Diego son usuarios adicionales para pruebas con ambos roles.
INSERT INTO `Personas`
    (`id_persona`, `nombre`, `dni`, `direccion`, `password`, `nombre_usuario`, `puntos`, `id_rol`)
VALUES
    (1, 'Ana Cliente', '30000101', 'Av. Siempre Viva 101', 'ClienteDemo2026!', 'cliente.demo', 1200, 1),
    (2, 'Bruno Convocatoria', '30000102', 'Calle Verde 202', 'PersonalDemo2026!', 'personal.demo', 0, 2),
    (3, 'Carla Cliente', '30000103', NULL, 'ClienteDemo2!', 'cliente.demo2', 350, 1),
    (4, 'Diego Convocatoria', '30000104', 'Pasaje del Reciclaje 404', 'PersonalDemo2!', 'personal.demo2', 0, 2);

-- RAEES: cada registro apunta a una categoría existente; puntos no negativos.
INSERT INTO `RAEES`
    (`id_raee`, `nombre`, `unidad_medida`, `puntos`, `puntos_extra`, `id_categoria`)
VALUES
    (1, 'Notebook', 'unidad', 100, 40, 1),
    (2, 'CPU de escritorio', 'unidad', 80, 30, 1),
    (3, 'Teléfono móvil', 'unidad', 45, 20, 2),
    (4, 'Teclado', 'unidad', 15, 5, 3),
    (5, 'Impresora', 'unidad', 40, 15, 4),
    (6, 'Cargador', 'unidad', 10, 3, 2);

-- Convocatorias: asignadas a personal existente; estados aceptados por el conversor JPA.
INSERT INTO `Convocatorias`
    (`id_convocatoria`, `descripcion`, `nombre`, `longitud`, `latitud`, `fecha_hora_inicio`, `fecha_hora_fin`, `direccion`, `estado`, `id_persona`)
VALUES
    (1, 'Recepción y clasificación de equipos informáticos.', 'Jornada RAEE - Centro', -58.3816000, -34.6037000,
     '2027-03-15 09:00:00', '2027-03-15 15:00:00', 'Av. de Mayo 100', 'Pendiente', 2),
    (2, 'Punto de recepción de celulares y accesorios.', 'Punto verde - Palermo', -58.4260000, -34.5880000,
     '2027-04-10 10:00:00', '2027-04-10 16:00:00', 'Av. Santa Fe 3500', 'En curso', 4),
    (3, 'Recepción de periféricos e impresoras en desuso.', 'Campaña RAEE - Caballito', -58.4400000, -34.6180000,
     '2027-05-22 08:30:00', '2027-05-22 13:30:00', 'Av. Rivadavia 5000', 'Pendiente', 2);

-- Cupones para descuentos porcentuales en productos RAEE reacondicionados.
INSERT INTO `Cupones` (`id_cupon`, `nombre`, `descripcion`, `valor_descuento`, `costo_cupon`, `activo`)
VALUES
    (1, 'Descuento en notebooks reacondicionadas', 'Descuento del 15% en la compra de notebooks reacondicionadas.', 15, 500, 1),
    (2, 'Descuento en equipos de escritorio', 'Descuento del 10% en la compra de equipos de escritorio reacondicionados.', 10, 400, 1),
    (3, 'Descuento en celulares reacondicionados', 'Descuento del 20% en la compra de celulares reacondicionados.', 20, 350, 1),
    (4, 'Descuento en periféricos', 'Descuento del 10% en la compra de periféricos reacondicionados.', 10, 200, 1);

-- No insertar Donaciones, Detalle_Donaciones ni Inventario_Cupones:
-- esos registros se generan mediante los endpoints de la aplicación.
