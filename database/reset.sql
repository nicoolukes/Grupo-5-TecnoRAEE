-- OPERACIÓN DESTRUCTIVA: elimina todos los registros de tecno_raee.
-- Hacé un respaldo antes de ejecutar este archivo si necesitás conservar los datos.
-- Mantiene las tablas, columnas, claves, relaciones e índices.
-- Usar manualmente sobre la base tecno_raee; este archivo no crea ni elimina la base.

USE `tecno_raee`;

-- Se usa DELETE en orden de tablas hijas a tablas padre para mantener activas las FK.
-- Reinicia las nueve tablas de la estructura vigente del proyecto.
DELETE FROM `detalle_donaciones`;
DELETE FROM `inventario_cupones`;
DELETE FROM `convocatorias`;
DELETE FROM `donaciones`;
DELETE FROM `raees`;
DELETE FROM `personas`;
DELETE FROM `cupones`;
DELETE FROM `categorias`;
DELETE FROM `roles`;

-- DELETE conserva los AUTO_INCREMENT; se reinician a 1 solo en tablas que
-- realmente tengan una columna AUTO_INCREMENT en el esquema actual.
SELECT IF(
    EXISTS (
        SELECT 1 FROM `information_schema`.`COLUMNS`
        WHERE `TABLE_SCHEMA` = DATABASE() AND `TABLE_NAME` = 'personas'
          AND `EXTRA` LIKE '%auto_increment%'
    ),
    'ALTER TABLE `personas` AUTO_INCREMENT = 1',
    'SELECT 1'
) INTO @reset_ai_sql;
PREPARE reset_ai_stmt FROM @reset_ai_sql;
EXECUTE reset_ai_stmt;
DEALLOCATE PREPARE reset_ai_stmt;

SELECT IF(
    EXISTS (
        SELECT 1 FROM `information_schema`.`COLUMNS`
        WHERE `TABLE_SCHEMA` = DATABASE() AND `TABLE_NAME` = 'convocatorias'
          AND `EXTRA` LIKE '%auto_increment%'
    ),
    'ALTER TABLE `convocatorias` AUTO_INCREMENT = 1',
    'SELECT 1'
) INTO @reset_ai_sql;
PREPARE reset_ai_stmt FROM @reset_ai_sql;
EXECUTE reset_ai_stmt;
DEALLOCATE PREPARE reset_ai_stmt;

SELECT IF(
    EXISTS (
        SELECT 1 FROM `information_schema`.`COLUMNS`
        WHERE `TABLE_SCHEMA` = DATABASE() AND `TABLE_NAME` = 'donaciones'
          AND `EXTRA` LIKE '%auto_increment%'
    ),
    'ALTER TABLE `donaciones` AUTO_INCREMENT = 1',
    'SELECT 1'
) INTO @reset_ai_sql;
PREPARE reset_ai_stmt FROM @reset_ai_sql;
EXECUTE reset_ai_stmt;
DEALLOCATE PREPARE reset_ai_stmt;

SELECT IF(
    EXISTS (
        SELECT 1 FROM `information_schema`.`COLUMNS`
        WHERE `TABLE_SCHEMA` = DATABASE() AND `TABLE_NAME` = 'cupones'
          AND `EXTRA` LIKE '%auto_increment%'
    ),
    'ALTER TABLE `cupones` AUTO_INCREMENT = 1',
    'SELECT 1'
) INTO @reset_ai_sql;
PREPARE reset_ai_stmt FROM @reset_ai_sql;
EXECUTE reset_ai_stmt;
DEALLOCATE PREPARE reset_ai_stmt;

SELECT IF(
    EXISTS (
        SELECT 1 FROM `information_schema`.`COLUMNS`
        WHERE `TABLE_SCHEMA` = DATABASE() AND `TABLE_NAME` = 'inventario_cupones'
          AND `EXTRA` LIKE '%auto_increment%'
    ),
    'ALTER TABLE `inventario_cupones` AUTO_INCREMENT = 1',
    'SELECT 1'
) INTO @reset_ai_sql;
PREPARE reset_ai_stmt FROM @reset_ai_sql;
EXECUTE reset_ai_stmt;
DEALLOCATE PREPARE reset_ai_stmt;

-- No se desactivan las comprobaciones de claves foráneas.
