 CREATE TABLE user_password (
  password_id INT NOT NULL AUTO_INCREMENT,
  password VARCHAR(250) NOT NULL,
  create_date DATETIME NOT NULL,
  update_date DATETIME NOT NULL,
  PRIMARY KEY (password_id)
 );
 
 
 CREATE TABLE user (
  user_id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  middle_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  user_name VARCHAR(45) DEFAULT NULL,
  create_date DATETIME NOT NULL,
  update_date DATETIME NOT NULL,
  password_id INT NOT NULL,
  PRIMARY KEY (user_id),
  CONSTRAINT fk_User_User_Password1 FOREIGN KEY (password_id) REFERENCES user_password (password_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
 
  
  
  CREATE TABLE delivery_details (
  delivery_id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  road_number INT NOT NULL,
  car_number VARCHAR(100) NOT NULL,
  start_car_meter_reader BIGINT(19) NOT NULL,
  end_car_meter_reader BIGINT(19) NULL,
  delivery_start_time DATETIME NOT NULL,
  delivery_end_time DATETIME NULL,
  PRIMARY KEY (delivery_id),
  CONSTRAINT user_delivery_details_FK
    FOREIGN KEY (user_id)
    REFERENCES user (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

CREATE TABLE delivered_packet (
  packet_id INT NOT NULL AUTO_INCREMENT,
  delivery_id INT NOT NULL,
  location VARCHAR(150) NOT NULL,
  kilometers_number BIGINT(19) NOT NULL,
  barcode_photo_path VARCHAR(150) NOT NULL,
  PRIMARY KEY (packet_id),
  CONSTRAINT delivered_packet_delivery_details_FK
    FOREIGN KEY (delivery_id)
    REFERENCES delivery_details (delivery_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );
    
 CREATE TABLE menu (
  menu_id INT(11) NOT NULL AUTO_INCREMENT,
  menu_name VARCHAR(45) NOT NULL,
  link VARCHAR(45) NOT NULL,
  user_id INT(11) NOT NULL,
  model_type VARCHAR(45) DEFAULT NULL,
  parent_id INT(11) DEFAULT NULL,
  menu_order INT(11) DEFAULT NULL,
  PRIMARY KEY (menu_id),
  CONSTRAINT fk_Menu_User1 FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);