DROP DATABASE IF EXISTS vdl;

CREATE DATABASE vdl;

CREATE TABLE `vdl`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`product_id`))
COMMENT = 'maintains the list of products with unique id';

CREATE TABLE `vdl`.`units` (
  `unit_id` INT NOT NULL AUTO_INCREMENT,
  `unit` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`unit_id`))
COMMENT = 'maintains the list of units with unique id';

CREATE TABLE `vdl`.`vendors` (
  `vendor_id` INT NOT NULL AUTO_INCREMENT,
  `vendor_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`vendor_id`))
COMMENT = 'maintains the list of vendors with unique id';

CREATE TABLE `vdl`.`products_inventory` (
  `pi_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  `qty` DECIMAL NOT NULL,
  `unit_id` INT NOT NULL,
  `modified_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pi_id`),
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `vdl`.`products` (`product_id`),
  CONSTRAINT `fk_vendor_id`  
	FOREIGN KEY (`vendor_id`)
    REFERENCES `vdl`.`vendors` (`vendor_id`),
  CONSTRAINT `fk_unit_id` 
	FOREIGN KEY (`unit_id`)
    REFERENCES `vdl`.`units` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Holds the products inventory data with available stock';

CREATE TABLE `vdl`.`purchase_history` (
  `ph_id` INT NOT NULL AUTO_INCREMENT,
  `pi_id` INT NOT NULL,
  `qty` DECIMAL NOT NULL,
  `price_per_unit` DECIMAL NOT NULL,
  `total_price` DECIMAL NOT NULL,
  `purchase_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ph_id`),
  INDEX `ph_id_idx` (`ph_id` ASC) VISIBLE,
  CONSTRAINT `ph_pi_id`
    FOREIGN KEY (`pi_id`)
    REFERENCES `vdl`.`products_inventory` (`pi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    COMMENT = 'Holds the products purchased history information';


CREATE TABLE `vdl`.`sale_history` (
  `sh_id` INT NOT NULL AUTO_INCREMENT,
  `pi_id` INT NOT NULL,
  `qty` DECIMAL NOT NULL,
  `price_per_unit` DECIMAL NOT NULL,
  `total_price` DECIMAL NOT NULL,
  `sale_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sh_id`),
  INDEX `pi_id_idx` (`pi_id` ASC) VISIBLE,
  CONSTRAINT `sh_pi_id`
    FOREIGN KEY (`pi_id`)
    REFERENCES `vdl`.`products_inventory` (`pi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    COMMENT = 'Holds the products sold history information';
	
CREATE TABLE `vdl`.`product_summary` (
  `ps_id` INT NOT NULL AUTO_INCREMENT,
  `pi_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `opening_stock` INT NOT NULL,
  `closing_stock` INT NOT NULL,
  `total_purchase_rate` DECIMAL NOT NULL,
  `total_sale_rate` DECIMAL NOT NULL,
  PRIMARY KEY (`ps_id`),
  INDEX `ps_ps_id_idx` (`ps_id` ASC) VISIBLE,
  CONSTRAINT `ps_pi_id`
    FOREIGN KEY (`pi_id`)
    REFERENCES `vdl`.`products_inventory` (`pi_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Holds the summary of products stock, products purchased and sold information';


CREATE VIEW purchase_history_view AS
SELECT ph.ph_id, ph.pi_id, p.product_name, v.vendor_name,
ph.qty as purchase_qty, 
u.unit, 
ph.price_per_unit, 
ph.total_price,
pi.modified_date 
FROM purchase_history ph, products_inventory pi, products p, vendors v, units u 
WHERE ph.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id

CREATE VIEW sale_history_view AS
SELECT sh.sh_id, sh.pi_id, p.product_name, v.vendor_name,
sh.qty as purchase_qty, 
u.unit, 
sh.price_per_unit, 
sh.total_price,
pi.modified_date 
FROM sale_history sh, products_inventory pi, products p, vendors v, units u 
WHERE sh.pi_id = pi.pi_id AND pi.product_id = p.product_id AND pi.vendor_id = v.vendor_id AND pi.unit_id = u.unit_id