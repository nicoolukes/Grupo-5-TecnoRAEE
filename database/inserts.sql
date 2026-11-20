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
    (101, 'Ana Cliente', '30000101', 'Av. Siempre Viva 101', 'ClienteDemo2026!', 'cliente.demo', 1200, 1),
    (102, 'Bruno Convocatoria', '30000102', 'Calle Verde 202', 'PersonalDemo2026!', 'personal.demo', 0, 2),
    (103, 'Carla Cliente', '30000103', NULL, 'ClienteDemo2!', 'cliente.demo2', 350, 1),
    (104, 'Diego Convocatoria', '30000104', 'Pasaje del Reciclaje 404', 'PersonalDemo2!', 'personal.demo2', 0, 2);

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
    (201, 'Recepción y clasificación de equipos informáticos.', 'Jornada RAEE - Centro', -58.3816000, -34.6037000,
     '2027-03-15 09:00:00', '2027-03-15 15:00:00', 'Av. de Mayo 100', 'Pendiente', 102),
    (202, 'Punto de recepción de celulares y accesorios.', 'Punto verde - Palermo', -58.4260000, -34.5880000,
     '2027-04-10 10:00:00', '2027-04-10 16:00:00', 'Av. Santa Fe 3500', 'En curso', 104),
    (203, 'Recepción de periféricos e impresoras en desuso.', 'Campaña RAEE - Caballito', -58.4400000, -34.6180000,
     '2027-05-22 08:30:00', '2027-05-22 13:30:00', 'Av. Rivadavia 5000', 'Pendiente', 102);

-- Cupones: valor_descuento es un entero de prueba; no se presupone su unidad funcional.
INSERT INTO `Cupones` (`id_cupon`, `nombre`, `descripcion`, `valor_descuento`, `costo_cupon`, `activo`)
VALUES
    (301, 'Descuento en librería', 'Cupón de prueba para artículos de librería.', 10, 100, 1),
    (302, 'Beneficio en cafetería', 'Cupón de prueba para consumo en cafetería.', 500, 250, 1),
    (303, 'Accesorio sustentable', 'Cupón de prueba para un accesorio reutilizable.', 15, 400, 1),
    (304, 'Cupón inactivo de prueba', 'Ejemplo para probar cupones no disponibles.', 100, 50, 0);

-- No insertar Donaciones, Detalle_Donaciones ni Inventario_Cupones:
-- esos registros se generan mediante los endpoints de la aplicación.
