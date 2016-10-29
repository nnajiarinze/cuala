USE cuala;

CREATE TABLE tbl_users
(
id int NOT NULL AUTO_INCREMENT,
fb_id varchar(30) NOT NULL UNIQUE,
name varchar(255) NOT NULL,
email varchar(255) NOT NULL UNIQUE,
phone varchar(255)  NOT NULL UNIQUE,
matric_no varchar(11)  NOT NULL UNIQUE,
reg_no varchar(10) NULL,
grad_year INT NOT null,
course varchar(255),
occupation varchar(255),
PRIMARY KEY (ID)
);

CREATE TABLE tbl_news
(
 id int NOT NULL AUTO_INCREMENT,
 headline varchar(250) NOT NULL,
 brief varchar(150) NULL,
 description TEXT NOT NULL,
 publish_date DATE NULL,
 author varchar(150) NOT NULL,
 tweet_text varchar(140) NULL,
 created_date DATE NULL,
 tags TEXT NULL,
 image varchar(1000) NULL,
 deleted BOOLEAN  default FALSE,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);

DROP procedure IF exists psp_retrieve_users;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_users()
BEGIN
  SELECT * FROM tbl_users;
END$$

DELIMITER ;


DROP procedure IF exists psp_retrieve_user_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_id (
  IN userId INT
  )
BEGIN
	SELECT * FROM tbl_users WHERE id=userId;
END$$

DELIMITER ;



DROP procedure IF exists psp_retrieve_user_by_email;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_email (
  IN userEmail VARCHAR(50)
  )
BEGIN
	SELECT * FROM tbl_users WHERE email=userEmail;
END$$

DELIMITER ;


DROP procedure IF exists psp_retrieve_user_by_phone;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_phone (
  IN mobileNumber VARCHAR(50)
  )
BEGIN
	SELECT * FROM tbl_users WHERE phone=mobileNumber;
END$$

DELIMITER ;





DROP procedure IF exists psp_retrieve_user_by_matric_no;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_matric_no (
  IN matricNumber VARCHAR(50)
  )
BEGIN
	SELECT * FROM tbl_users WHERE matric_no=matricNumber;
END$$

DELIMITER ;


DROP procedure IF exists psp_retrieve_user_by_facebook_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_facebook_id (
  IN facebook_id varchar(30)
  )
BEGIN
	SELECT * FROM tbl_users WHERE fb_id=facebook_id;
END$$

DELIMITER ;



DROP procedure IF exists psp_create_user;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_user (
  IN fb_idd varchar(30),
  IN namee varchar(255),
  IN emaill varchar(255),
  IN phonee varchar(255),
  IN matric_noo varchar(11),
  IN reg_noo varchar(10),
  IN grad_yearr INT,
  IN coursee varchar(255),
  IN occupationn varchar(255),
  OUT id INT
  )
BEGIN
	INSERT INTO tbl_users(fb_id,name,email,phone,matric_no,reg_no,grad_year,course,occupation)
    VALUES(fb_idd,namee,emaill,phonee,matric_noo,reg_noo,grad_yearr,coursee,occupationn);

	SET id = LAST_INSERT_ID();
END$$


DELIMITER ;
DROP procedure IF exists psp_update_user;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_update_user (
  IN idd INT,
  IN fb_idd varchar(30),
  IN namee varchar(255),
  IN emaill varchar(255),
  IN phonee varchar(255),
  IN matric_noo varchar(11),
  IN reg_noo varchar(10),
  IN grad_yearr INT,
  IN coursee varchar(255),
  IN occupationn varchar(255)
  )

     UPDATE tbl_users SET fb_id=fb_idd ,name=namee, email=emaill,phone=phonee,matric_no=matric_noo,reg_no=reg_noo,grad_year=grad_yearr,
     course=coursee,occupation=occupationn WHERE id=idd


DELIMITER ;


DROP procedure IF exists psp_create_news;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_news (
  IN headlinee varchar(250),
  IN brieff varchar(150),
  IN descriptionn TEXT,
  IN authorr varchar(150),
  IN publish_datee DATE,
  IN tweet_textt varchar(140),
  IN created_datee DATE,
  IN tagss TEXT,
  IN imagee varchar(1000),
  IN deletedd TINYINT,
  OUT id INT
  )
BEGIN
	INSERT INTO tbl_news(headline,brief,description,author,publish_date,tweet_text,created_date,tags,image,deleted)
    VALUES(headlinee,brieff,descriptionn,authorr,publish_datee,tweet_textt,created_datee,tagss,imagee,deletedd);

	SET id = LAST_INSERT_ID();
END$$

