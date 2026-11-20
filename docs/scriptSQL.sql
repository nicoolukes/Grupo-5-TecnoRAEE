-- Estructura de la base de datos definida por las entidades JPA del proyecto RAEE.
-- Compatible con MariaDB 10.4.32. Este archivo no contiene datos iniciales.

CREATE DATABASE IF NOT EXISTS `tecno_raee`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE `tecno_raee`;

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` INT NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `uq_roles_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personas` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `dni` VARCHAR(10) NOT NULL,
  `direccion` VARCHAR(150) NULL DEFAULT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nombre_usuario` VARCHAR(50) NOT NULL,
  `puntos` INT NOT NULL DEFAULT 0,
  `id_rol` INT NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE KEY `uq_personas_dni` (`dni`),
  UNIQUE KEY `uq_personas_nombre_usuario` (`nombre_usuario`),
  KEY `idx_personas_id_rol` (`id_rol`),
  CONSTRAINT `chk_personas_puntos_no_negativos`
    CHECK (`puntos` >= 0),
  CONSTRAINT `fk_personas_roles`
    FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categorias` (
  `id_categoria` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `uq_categorias_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `raees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `raees` (
  `id_raee` INT NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `puntos` INT NOT NULL,
  `unidad_medida` VARCHAR(10) NOT NULL,
  `puntos_extra` INT NOT NULL,
  `id_categoria` INT NOT NULL,
  PRIMARY KEY (`id_raee`),
  KEY `idx_raees_id_categoria` (`id_categoria`),
  CONSTRAINT `chk_raees_puntos_no_negativos`
    CHECK (`puntos` >= 0 AND `puntos_extra` >= 0),
  CONSTRAINT `fk_raees_categorias`
    FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `convocatorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `convocatorias` (
  `id_convocatoria` INT NOT NULL AUTO_INCREMENT,
  `descripcion` TEXT NULL DEFAULT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `longitud` DECIMAL(10,7) NULL DEFAULT NULL,
  `latitud` DECIMAL(10,7) NULL DEFAULT NULL,
  `fecha_hora_inicio` DATETIME NOT NULL,
  `fecha_hora_fin` DATETIME NOT NULL,
  `direccion` VARCHAR(150) NULL DEFAULT NULL,
  `estado` VARCHAR(20) NOT NULL,
  `id_persona` INT NOT NULL,
  PRIMARY KEY (`id_convocatoria`),
  KEY `idx_convocatorias_id_persona` (`id_persona`),
  CONSTRAINT `chk_convocatorias_fechas`
    CHECK (`fecha_hora_fin` >= `fecha_hora_inicio`),
  CONSTRAINT `fk_convocatorias_personas`
    FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `donaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `donaciones` (
  `id_donacion` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `puntos_totales` INT NOT NULL DEFAULT 0,
  `id_persona` INT NOT NULL,
  PRIMARY KEY (`id_donacion`),
  KEY `idx_donaciones_id_persona` (`id_persona`),
  CONSTRAINT `chk_donaciones_puntos_no_negativos`
    CHECK (`puntos_totales` >= 0),
  CONSTRAINT `fk_donaciones_personas`
    FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `detalle_donaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `detalle_donaciones` (
  `id_donacion` INT NOT NULL,
  `id_raee` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `funciona` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_donacion`, `id_raee`),
  KEY `idx_detalle_donaciones_id_raee` (`id_raee`),
  CONSTRAINT `chk_detalle_donaciones_cantidad_positiva`
    CHECK (`cantidad` > 0),
  CONSTRAINT `fk_detalle_donaciones_donaciones`
    FOREIGN KEY (`id_donacion`) REFERENCES `donaciones` (`id_donacion`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detalle_donaciones_raees`
    FOREIGN KEY (`id_raee`) REFERENCES `raees` (`id_raee`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `cupones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cupones` (
  `id_cupon` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `costo_cupon` INT NOT NULL,
  `valor_descuento` INT NOT NULL,
  `activo` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_cupon`),
  CONSTRAINT `chk_cupones_costo_positivo`
    CHECK (`costo_cupon` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `inventario_cupones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventario_cupones` (
  -- Nombre físico producido por la estrategia de nombres Hibernate
  -- a partir de @Column(name = "id_inventarioCupon").
  `id_inventario_cupon` INT NOT NULL AUTO_INCREMENT,
  `id_persona` INT NOT NULL,
  `id_cupon` INT NOT NULL,
  `puntos_gastados` INT NOT NULL,
  `fecha_canje` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `fecha_uso` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id_inventario_cupon`),
  KEY `idx_inventario_cupones_id_persona` (`id_persona`),
  KEY `idx_inventario_cupones_id_cupon` (`id_cupon`),
  CONSTRAINT `chk_inventario_cupones_puntos_no_negativos`
    CHECK (`puntos_gastados` >= 0),
  CONSTRAINT `fk_inventario_cupones_personas`
    FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventario_cupones_cupones`
    FOREIGN KEY (`id_cupon`) REFERENCES `cupones` (`id_cupon`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
