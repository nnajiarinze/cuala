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


CREATE TABLE tbl_events
(
 id int NOT NULL AUTO_INCREMENT,
 title varchar(250) NOT NULL,
 location varchar(250) NOT NULL,
 `date` DATE NOT NULL,
 image varchar(1000) NULL,
 description TEXT NOT NULL,
 created_date DATE NOT NULL,
 deleted BOOLEAN default FALSE,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);


CREATE TABLE tbl_events_invitations
(
 id int NOT NULL AUTO_INCREMENT,
 user_id INT NOT NULL,
 event_id INT NOT NULL,
 attending BOOLEAN NOT NULL,
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
BEGIN
     UPDATE tbl_users SET fb_id=fb_idd ,name=namee, email=emaill,phone=phonee,matric_no=matric_noo,reg_no=reg_noo,grad_year=grad_yearr,
     course=coursee,occupation=occupationn WHERE id=idd;
END$$

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

DELIMITER ;
DROP procedure IF exists psp_fetch_paginated_news;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_fetch_paginated_news (
  IN page_size INT,
  IN page_num INT
  )
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
	SELECT * FROM tbl_news WHERE deleted=false LIMIT offsett,page_size;

END$$

DELIMITER ;
DROP procedure IF exists psp_get_news_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_news_by_id (
  IN idd INT
  )
BEGIN

	SELECT * FROM tbl_news WHERE id=idd;

END$$


DELIMITER ;
DROP procedure IF exists psp_update_news;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_update_news (
  IN idd INT,
  IN headlinee varchar(250),
  IN brieff varchar(150),
  IN descriptionn TEXT,
  IN authorr varchar(150),
  IN publish_datee DATE,
  IN tweet_textt varchar(140),
  IN created_datee DATE,
  IN tagss TEXT,
  IN imagee varchar(1000),
  IN deletedd TINYINT
 )
BEGIN
    UPDATE tbl_news SET headline=headlinee, brief=brieff, description= descriptionn, author=authorr, publish_date= publish_datee,tweet_text=tweet_textt,created_date=created_datee,
    tags=tagss,image =imagee,deleted=deletedd WHERE id=idd;

END$$


DELIMITER ;
DROP procedure IF exists psp_delete_news;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_delete_news (
  IN idd INT
  )
BEGIN
        UPDATE tbl_news SET deleted=true WHERE id=idd;

END$$


DELIMITER ;
DROP procedure IF exists psp_create_event;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_event (
  IN titlee varchar(250),
  IN locationn varchar(250),
  IN datee DATE,
  IN imagee varchar(1000),
  IN descriptionn TEXT,
  IN created_datee DATE,
  OUT id INT
  )
BEGIN
        INSERT INTO tbl_events (title,location,`date`,image,description,created_date)
         VALUES (titlee,locationn,datee,imagee,descriptionn,created_datee);

        SET id = LAST_INSERT_ID();
END$$




DELIMITER ;
DROP procedure IF exists psp_get_event_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_event_by_id (
  IN idd INT
  )
BEGIN
    SELECT * FROM tbl_events WHERE id=idd;
END$$



DELIMITER ;
DROP procedure IF exists psp_get_paginated_events;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_paginated_events (
  IN page_size INT,
  IN page_num INT
  )
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
	SELECT * FROM tbl_events WHERE deleted=false LIMIT offsett,page_size;

END$$





