/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Estructura de tabla partida

DROP TABLE IF EXISTS `partida`;
CREATE TABLE `partida` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `fecha_hora` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `intentos` INT DEFAULT 0,
    `dificultad` ENUM('facil','normal','dificil') DEFAULT 'normal',
    `palabra` VARCHAR(45) NOT NULL DEFAULT '',
    `puntos` INT NOT NULL DEFAULT '0',
    `id_jugador` BIGINT DEFAULT null,
    `id_juego` BIGINT DEFAULT null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;