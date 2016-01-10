-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema development
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema development
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `development` DEFAULT CHARACTER SET utf8 ;
USE `development` ;

-- -----------------------------------------------------
-- Table `development`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `development`.`users` (
  `login` NVARCHAR(255) NOT NULL,
  `passwd` TINYTEXT NOT NULL,
  `ChaseID` INT NULL,
  `HunterID` INT NULL,
  `UserID` INT NOT NULL AUTO_INCREMENT,
  UNIQUE INDEX `login` (`login` ASC),
  PRIMARY KEY (`UserID`, `login`))
ENGINE = InnoDB
AUTO_INCREMENT = 4;


-- -----------------------------------------------------
-- Table `development`.`characters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `development`.`characters` (
  `CharacterID` INT NOT NULL AUTO_INCREMENT,
  `lvl` INT NOT NULL DEFAULT '1',
  `role` TINYTEXT NOT NULL,
  `class` NVARCHAR(255) NOT NULL DEFAULT 'basic',
  `experience` INT NOT NULL DEFAULT '0',
  `skill_points` INT NOT NULL DEFAULT '0',
  `skill_1_lvl` INT NOT NULL DEFAULT '0',
  `skill_2_lvl` INT NOT NULL DEFAULT '0',
  `skill_3_lvl` INT NOT NULL DEFAULT '0',
  `skill_4_lvl` INT NOT NULL DEFAULT '0',
  `skill_5_lvl` INT NOT NULL DEFAULT '0',
  `users_login` NVARCHAR(255) NOT NULL,
  PRIMARY KEY (`CharacterID`, `users_login`),
  INDEX `fk_characters_users1_idx` (`users_login` ASC),
  CONSTRAINT `fk_characters_users1`
    FOREIGN KEY (`users_login`)
    REFERENCES `development`.`users` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13;


-- -----------------------------------------------------
-- Table `development`.`lobbies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `development`.`lobbies` (
  `LobbyID` INT NOT NULL AUTO_INCREMENT,
  `Title` NVARCHAR(255) NOT NULL DEFAULT 'New lobby',
  `PlayersNO` INT NOT NULL DEFAULT '10',
  `GameTime` INT NOT NULL DEFAULT '30',
  `RunTime` INT NOT NULL DEFAULT '5',
  `Status` NVARCHAR(255) NOT NULL DEFAULT 'WAIT',
  `Hunter_login` NVARCHAR(255) NULL,
  PRIMARY KEY (`LobbyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `development`.`lobbies_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `development`.`lobbies_users` (
  `Status` NVARCHAR(255) NOT NULL DEFAULT 'WAIT',
  `lobbies_LobbyID` INT NOT NULL,
  `users_login` NVARCHAR(255) NOT NULL,
  INDEX `fk_lobbies_users_lobbies1_idx` (`lobbies_LobbyID` ASC),
  INDEX `fk_lobbies_users_users1_idx` (`users_login` ASC),
  UNIQUE INDEX `users_login_UNIQUE` (`users_login` ASC),
  PRIMARY KEY (`users_login`, `lobbies_LobbyID`),
  CONSTRAINT `fk_lobbies_users_lobbies1`
    FOREIGN KEY (`lobbies_LobbyID`)
    REFERENCES `development`.`lobbies` (`LobbyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lobbies_users_users1`
    FOREIGN KEY (`users_login`)
    REFERENCES `development`.`users` (`login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
