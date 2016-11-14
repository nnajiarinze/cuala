USE cuala;

CREATE TABLE bf_tbl_users
(
id int NOT NULL AUTO_INCREMENT,
fb_id varchar(30) NOT NULL UNIQUE,
`name` varchar(255) NOT NULL,
email varchar(255) NOT NULL UNIQUE,
phone varchar(255)  NOT NULL UNIQUE,
matric_no varchar(11)  NOT NULL UNIQUE,
reg_no varchar(10) NULL,
grad_year INT NOT null,
image varchar(255) NULL,
course varchar(255),
occupation varchar(255),
PRIMARY KEY (ID)
);

CREATE TABLE bf_tbl_news
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


CREATE TABLE bf_tbl_events
(
 id int NOT NULL AUTO_INCREMENT,
 title varchar(250) NOT NULL,
 location varchar(250) NOT NULL,
 `date` DATETIME NOT NULL,
 image varchar(1000) NULL,
 description TEXT NOT NULL,
 created_date DATE NOT NULL,
 deleted BOOLEAN default FALSE,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);


CREATE TABLE bf_tbl_events_invitations
(
 id int NOT NULL AUTO_INCREMENT,
 user_id INT NOT NULL,
 event_id INT NOT NULL,
 attending BOOLEAN NOT NULL,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);

CREATE TABLE bf_tbl_news_comments
(
 id int NOT NULL AUTO_INCREMENT,
 news_id INT NOT NULL,
 user_id INT NOT NULL,
 comment TEXT NOT NULL,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);





CREATE TABLE bf_tbl_jobs
(
 id INT NOT NULL AUTO_INCREMENT,
 category_id INT NOT NULL,
 title varchar(255) NOT NULL,
 location varchar(255) NOT NULL,
 description TEXT NOT NULL,
 created_date DATE NOT NULL,
 end_date DATE NOT NULL,
 last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (ID)
);


CREATE TABLE bf_tbl_job_category
(
 id INT NOT NULL AUTO_INCREMENT,
 `name` varchar(255) NOT NULL UNIQUE,
 PRIMARY KEY (ID)
)



DELIMITER ;
DROP procedure IF exists psp_retrieve_users;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_users(
    IN page_num INT,
    IN page_size INT
)
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
    SELECT * FROM bf_tbl_users ORDER BY name asc LIMIT offsett,page_size;

END$$

DELIMITER ;


DELIMITER ;
DROP procedure IF exists psp_retrieve_users_by_course;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_users_by_course(
    IN page_num INT,
    IN page_size INT,
    IN coursee varchar(100)
)
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
    SELECT * FROM bf_tbl_users WHERE course=coursee ORDER BY name asc LIMIT offsett,page_size;

END$$

DELIMITER ;



DELIMITER ;
DROP procedure IF exists psp_retrieve_users_by_grad_year;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_users_by_grad_year(
    IN page_num INT,
    IN page_size INT,
    IN grad_yearr INT
)
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
    SELECT * FROM bf_tbl_users WHERE grad_year=grad_yearr ORDER BY name asc LIMIT offsett,page_size;

END$$

DELIMITER ;



DELIMITER ;
DROP procedure IF exists psp_search_users_by_name;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_search_users_by_name(
    IN page_num INT,
    IN page_size INT,
    IN namee varchar(255)
)
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
    SELECT * FROM bf_tbl_users WHERE name LIKE CONCAT('%',namee,'%') LIMIT offsett,page_size;

END$$

DELIMITER ;







DROP procedure IF exists psp_retrieve_user_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_id (
  IN userId INT
  )
BEGIN
	SELECT * FROM bf_tbl_users WHERE id=userId;
END$$

DELIMITER ;



DROP procedure IF exists psp_retrieve_user_by_email;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_email (
  IN userEmail VARCHAR(50)
  )
BEGIN
	SELECT * FROM bf_tbl_users WHERE email=userEmail;
END$$

DELIMITER ;


DROP procedure IF exists psp_retrieve_user_by_phone;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_phone (
  IN mobileNumber VARCHAR(50)
  )
BEGIN
	SELECT * FROM bf_tbl_users WHERE phone=mobileNumber;
END$$

DELIMITER ;





DROP procedure IF exists psp_retrieve_user_by_matric_no;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_matric_no (
  IN matricNumber VARCHAR(50)
  )
BEGIN
	SELECT * FROM bf_tbl_users WHERE matric_no=matricNumber;
END$$

DELIMITER ;


DROP procedure IF exists psp_retrieve_user_by_facebook_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_retrieve_user_by_facebook_id (
  IN facebook_id varchar(30)
  )
BEGIN
	SELECT * FROM bf_tbl_users WHERE fb_id=facebook_id;
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
  IN imagee varchar(255),
  IN coursee varchar(255),
  IN occupationn varchar(255),
  OUT id INT
  )
BEGIN
	INSERT INTO bf_tbl_users(fb_id,`name`,email,phone,matric_no,reg_no,image,grad_year,course,occupation)
    VALUES(fb_idd,namee,emaill,phonee,matric_noo,reg_noo,imagee,grad_yearr,coursee,occupationn);

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
  IN imagee varchar(255),
  IN coursee varchar(255),
  IN occupationn varchar(255)
  )
BEGIN
     UPDATE bf_tbl_users SET fb_id=fb_idd ,`name`=namee, email=emaill,phone=phonee,matric_no=matric_noo,reg_no=reg_noo,grad_year=grad_yearr,
     course=coursee,occupation=occupationn,image=imagee WHERE id=idd;
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
	INSERT INTO bf_tbl_news(headline,brief,description,author,publish_date,tweet_text,created_date,tags,image,deleted)
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
	SELECT * FROM bf_tbl_news WHERE deleted=false ORDER BY id desc LIMIT offsett,page_size;

END$$

DELIMITER ;
DROP procedure IF exists psp_get_news_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_news_by_id (
  IN idd INT
  )
BEGIN

	SELECT * FROM bf_tbl_news WHERE id=idd;

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
    UPDATE bf_tbl_news SET headline=headlinee, brief=brieff, description= descriptionn, author=authorr, publish_date= publish_datee,tweet_text=tweet_textt,created_date=created_datee,
    tags=tagss,image =imagee,deleted=deletedd WHERE id=idd;

END$$


DELIMITER ;
DROP procedure IF exists psp_delete_news;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_delete_news (
  IN idd INT
  )
BEGIN
        UPDATE bf_tbl_news SET deleted=true WHERE id=idd;

END$$


DELIMITER ;
DROP procedure IF exists psp_create_event;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_event (
  IN titlee varchar(250),
  IN locationn varchar(250),
  IN datee DATETIME,
  IN imagee varchar(1000),
  IN descriptionn TEXT,
  IN created_datee DATE,
  OUT id INT
  )
BEGIN
        INSERT INTO bf_tbl_events (title,location,`date`,image,description,created_date)
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
    SELECT * FROM bf_tbl_events WHERE id=idd;
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
	SELECT * FROM bf_tbl_events WHERE deleted=false ORDER BY id DESC LIMIT offsett,page_size;

END$$



DELIMITER ;
DROP procedure IF exists psp_create_event_invitation;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_event_invitation (
  IN event_idd INT,
  IN user_idd INT,
  IN attendingg TINYINT,
  OUT id INT
  )
BEGIN
        INSERT INTO bf_tbl_events_invitations (event_id,user_id,attending)
         VALUES (event_idd,user_idd,attendingg);

        SET id = LAST_INSERT_ID();
END$$



DELIMITER ;
DROP procedure IF exists psp_get_user_event_invitation_response;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_user_event_invitation_response (
  IN event_idd INT,
  IN user_idd INT
  )
BEGIN
      SELECT * FROM bf_tbl_events_invitations WHERE event_id=event_idd AND user_id = user_idd;

END$$



DELIMITER ;
DROP procedure IF exists psp_create_news_comment;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_news_comment (
  IN news_idd INT,
  IN user_idd INT,
  IN commentt TEXT,
  OUT id INT
  )
BEGIN
        INSERT INTO bf_tbl_news_comments (news_id,user_id,comment)
         VALUES (news_idd,user_idd,commentt);

        SET id = LAST_INSERT_ID();
END$$



DELIMITER ;
DROP procedure IF exists psp_create_job;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_job (
  IN category_idd INT,
  IN titlee varchar(255),
  IN locationn varchar(255),
  IN descriptionn TEXT,
  IN created_datee DATE,
  IN end_datee DATE,
  OUT id INT
  )
BEGIN
        INSERT INTO bf_tbl_jobs (category_id,title,location,description,created_date,end_date)
         VALUES (category_idd,titlee,locationn,descriptionn,created_datee,end_datee);

        SET id = LAST_INSERT_ID();
END$$





DELIMITER ;
DROP procedure IF exists psp_get_job_by_id;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_job_by_id (
  IN idd INT
  )
BEGIN
    SELECT bf_tbl_jobs.*, bf_tbl_job_category.name as category_name FROM bf_tbl_jobs
      JOIN bf_tbl_job_category ON bf_tbl_jobs.category_id=bf_tbl_job_category.id
      WHERE bf_tbl_jobs.id=idd;
END$$




DELIMITER ;
DROP procedure IF exists psp_get_paginated_jobs;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_paginated_jobs (
  IN page_size INT,
  IN page_num INT
  )
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
SELECT bf_tbl_jobs.* , bf_tbl_job_category.name as category_name
FROM bf_tbl_jobs JOIN bf_tbl_job_category ON bf_tbl_jobs.category_id=bf_tbl_job_category.id
  ORDER BY id desc LIMIT offsett,page_size;

END$$





DELIMITER ;
DROP procedure IF exists psp_update_job;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_update_job (
  IN idd INT,
  IN category_idd INT,
  IN titlee varchar(255),
  IN locationn varchar(255),
  IN descriptionn TEXT,
  IN created_datee DATE,
  IN end_datee DATE
  )
BEGIN
        UPDATE bf_tbl_jobs SET category_id=category_idd,title=titlee, location=locationn, description=descriptionn, created_date=created_datee, end_date=end_datee WHERE id=idd;
END$$




DELIMITER ;
DROP procedure IF exists psp_create_job_category;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_create_job_category (
  IN namee varchar(255),
  OUT id INT
  )
BEGIN
        INSERT INTO bf_tbl_job_category (`name`) VALUES (namee);

        SET id = LAST_INSERT_ID();
END$$


DELIMITER ;
DROP procedure IF exists psp_get_job_categories;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_job_categories ()
BEGIN
    SELECT * FROM bf_tbl_job_category;

END$$





DELIMITER ;
DROP procedure IF exists psp_get_jobs_per_category;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_jobs_per_category (
  IN category_idd INT,
   IN page_size INT,
    IN page_num INT
  )
BEGIN
    DECLARE offsett INT;
    SET offsett = (page_num - 1) * page_size;
	SELECT * FROM bf_tbl_jobs WHERE category_id=category_idd ORDER BY id desc LIMIT offsett,page_size;

END$$

DELIMITER ;
DROP procedure IF exists psp_get_event_by_date;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_event_by_date (
  IN datee DATE
  )
BEGIN
    SELECT * FROM bf_tbl_events WHERE CAST(date AS DATE)=CAST(datee AS DATE) ORDER BY id desc;
END$$


DELIMITER ;
DROP procedure IF exists psp_get_events_between_date;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE psp_get_events_between_date (
  IN start_datee DATE,
  IN end_datee DATE
  )
BEGIN
    SELECT * FROM bf_tbl_events WHERE  CAST(date AS DATE) >= CAST(start_datee AS DATE) AND date<=CAST(end_datee AS DATE) ORDER BY id desc;
END$$

