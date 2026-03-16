-- MySQL Workbench Forward Engineering



-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hexaware_assest_management
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hexaware_assest_management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hexaware_assest_management` DEFAULT CHARACTER SET utf8mb3 ;
USE `hexaware_assest_management` ;

-- -----------------------------------------------------
-- Table `hexaware_assest_management`.`asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hexaware_assest_management`.`asset` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `category` VARCHAR(255) NOT NULL,
  `stock_count` INT NOT NULL,
  `details` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `hexaware_assest_management`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hexaware_assest_management`.`department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `hexaware_assest_management`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hexaware_assest_management`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `job_title` VARCHAR(255) NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`id`, `department_id`),
  INDEX `fk_employee_department1_idx` (`department_id` ASC) ,
  CONSTRAINT `fk_employee_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `hexaware_assest_management`.`department` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `hexaware_assest_management`.`asset_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hexaware_assest_management`.`asset_employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `date_of_allocation` DATE NOT NULL,
  `serial_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`, `asset_id`, `employee_id`),
  INDEX `fk_assest_has_employee_employee1_idx` (`employee_id` ASC) ,
  INDEX `fk_assest_has_employee_assest_idx` (`asset_id` ASC) ,
  CONSTRAINT `fk_assest_has_employee_assest`
    FOREIGN KEY (`asset_id`)
    REFERENCES `hexaware_assest_management`.`asset` (`id`),
  CONSTRAINT `fk_assest_has_employee_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `hexaware_assest_management`.`employee` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `hexaware_assest_management`.`asset_manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hexaware_assest_management`.`asset_manager` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

USE hexaware_assest_management;

INSERT INTO department (name) VALUES
('Information Technology'),
('Human Resources'),
('Finance'),
('Marketing'),
('Operations');

INSERT INTO employee (name, job_title, department_id) VALUES
('Alice Johnson', 'Senior Developer', 1),
('Bob Smith', 'IT Support Engineer', 1),
('Charlie Davis', 'HR Manager', 2),
('Diana Prince', 'Financial Analyst', 3),
('Edward Norton', 'Marketing Lead', 4);

INSERT INTO asset (title, category, stock_count, details) VALUES
('MacBook Pro 16', 'Laptop', 10, 'M3 Chip, 32GB RAM'),
('Dell UltraSharp 27', 'Monitor', 15, '4K UHD Display'),
('Logitech MX Master 3', 'Peripheral', 20, 'Wireless Mouse'),
('iPhone 15 Pro', 'Mobile', 5, '128GB Titanium'),
('Ergonomic Chair', 'Furniture', 12, 'Lumbar Support Mesh');

INSERT INTO asset_employee (asset_id, employee_id, date_of_allocation, serial_number) VALUES
(1, 1, '2024-01-10', 'LAP-MAC-001'),
(3, 1, '2024-01-10', 'MOU-LOG-045'),
(2, 2, '2024-02-05', 'MON-DEL-110'),
(5, 3, '2024-03-12', 'FUR-CHR-021'),
(4, 4, '2024-04-01', 'MOB-IPH-777');

INSERT INTO asset_manager (name) VALUES
('Ravi Kumar'),
('Sneha Reddy'),
('Arjun Mehta'),
('Priya Sharma'),
('Vikram Rao');