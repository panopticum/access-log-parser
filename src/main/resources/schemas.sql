CREATE SCHEMA access_logs;
USE access_logs;

CREATE TABLE `requests` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `ip` VARCHAR(32) NOT NULL,
  `method` VARCHAR(45) NOT NULL,
  `code` INT(11) UNSIGNED NOT NULL,
  `header` VARCHAR(2048) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `ip_restrictions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ip` VARCHAR(32) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));
