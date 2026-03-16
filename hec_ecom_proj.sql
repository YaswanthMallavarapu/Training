-- MySQL Workbench Forward Engineering



-- -----------------------------------------------------
-- Schema hex_ecom_proj
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hex_ecom_proj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hex_ecom_proj` DEFAULT CHARACTER SET utf8 ;
USE `hex_ecom_proj` ;

-- -----------------------------------------------------
-- Table `hex_ecom_proj`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hex_ecom_proj`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `seq` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hex_ecom_proj`.`vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hex_ecom_proj`.`vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hex_ecom_proj`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hex_ecom_proj`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `price` DECIMAL NULL,
  `details` VARCHAR(255) NULL,
  `number_stock` VARCHAR(45) NULL,
  `category_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_category_idx` (`category_id` ASC) ,
  INDEX `fk_product_vendor1_idx` (`vendor_id` ASC) ,
  CONSTRAINT `fk_product_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `hex_ecom_proj`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_vendor1`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `hex_ecom_proj`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



