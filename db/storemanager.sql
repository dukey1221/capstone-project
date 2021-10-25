/**
 * Author:  lentrix
 * Created: 21 Apr 2021
 */

DROP DATABASE IF EXISTS storemanager;
CREATE DATABASE storemanager;
USE storemanager;

CREATE TABLE `users` (
    `id`            INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `fullname`      VARCHAR(90) NOT NULL,
    `username`      VARCHAR(25) NOT NULL,
    `password`      VARCHAR(225) NOT NULL,
    `role`          ENUM('admin','cashier') DEFAULT 'cashier',
    `last_login`    TIMESTAMP
);

INSERT INTO `users` (`fullname`, `username`, `password`, `role`, `last_login`)
VALUES  ('Benjie B. Lenteria', 'lentrix', MD5('password'), 'admin', NOW()),
        ('Emily Barrentos', 'emz20', MD5('password'), 'cashier', NOW() ),
        ('Sophie Garreta', 'sophie', MD5('password'), 'cashier', NOW() );
CREATE TABLE `logs` (
    `id`            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `user_id`       INTEGER UNSIGNED NOT NULL,
    `log`           VARCHAR(225) NOT NULL,
    FOREIGN KEY(`user_id`) REFERENCES users(`id`)
);

CREATE TABLE `items` (
    `id`            BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `bar_code`      VARCHAR(90) UNIQUE,
    `name`          VARCHAR(100) NOT NULL,
    `description`   VARCHAR(200),
    `volume`        VARCHAR(30),
    `ws_unit`       VARCHAR(30),
    `ws_qty`        INTEGER UNSIGNED NOT NULL,
    `ws_price`      DECIMAL(8,2) NOT NULL,
    `rt_unit`       VARCHAR(30),
    `rt_price`      DECIMAL(8,2) NOT NULL,
    `qty`           INTEGER UNSIGNED NOT NULL
);

INSERT INTO `items` ( `bar_code`, `name`, `description`, `volume`, `ws_unit`, `ws_qty`, `ws_price`, `rt_unit`, `rt_price`, `qty` )
VALUES  ( '1', 'Downy Expert Antibac' , 'Fabric Conditioner with Antibac in Sachet', '38mL', 'dozen', 12, 78.50, 'sachet', 6.99, 120 ),
        ( '2', 'Efficascent Oil' , 'Methyl Salicylate Camphor+Menthol' , '100mL', 'bottle', 1, 45.60, 'bottle', 45.60, 32 ),
        ( '3', '555 Sardines (Green)' , 'Sardines in Tomato sauce regular', '155g', 'dozen', 12, 250.00, 'can', 21.20, 90 );

CREATE TABLE `customers` (
    `id`            INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `name`          VARCHAR(90) NOT NULL,
    `address`       VARCHAR(200) NOT NULL,
    `phone`         VARCHAR(15) NOT NULL,
    `credit_limit`  DECIMAL(8,2) NOT NULL
);

INSERT INTO `customers` ( `name`, `address`, `phone`, `credit_limit` )
VALUES  ( 'Sulpicia Aparicio', 'Camambugan, Ubay, Bohol', '09125452569', 5000 ),
        ( 'Cipriano Reyes', 'Fatima, Ubay, Bohol', '09225458789', 7000 ),
        ( 'Fulgencia Boyles', 'Bood, Ubay, Bohol', '92587458563', 8000 );

CREATE TABLE `sales` (
    `id`            INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `date`          DATE DEFAULT NOW(),
    `time`          TIME DEFAULT NOW(),
    `customer_id`   INTEGER UNSIGNED,
    `user_id`       INTEGER UNSIGNED NOT NULL,
    `discount`      DECIMAL(8,2) DEFAULT 0,
    FOREIGN KEY (`customer_id`) REFERENCES `customers`(`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`)
);

CREATE TABLE `sales_items` (
    `id`            INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `item_id`       BIGINT UNSIGNED NOT NULL,
    `sales_id`      INTEGER UNSIGNED,
    `qty`           INTEGER NOT NULL,
    `price`         DECIMAL(8,2) NOT NULL,
    `is_wholesale`  BOOLEAN DEFAULT 1,
    FOREIGN KEY (`item_id`) REFERENCES `items`(`id`),
    FOREIGN KEY (`sales_id`) REFERENCES `sales`(`id`)
);