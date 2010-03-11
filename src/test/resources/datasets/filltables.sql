INSERT INTO USER_RIGHT (VALUE,NAME,DESCRIPTION) VALUES (0, 'Guest', 'Description of Guest')
INSERT INTO USER_RIGHT (VALUE,NAME,DESCRIPTION) VALUES (10, 'User', 'Description of User')
INSERT INTO USER_RIGHT (VALUE,NAME,DESCRIPTION) VALUES (20, 'Admin', 'Description of Admin')

INSERT INTO OBSERVATION_CATEGORIE (ID,TITLE,DESCRIPTION) VALUES (1, 'Categorie 1', 'Description of Categorie 1')
INSERT INTO OBSERVATION_CATEGORIE (ID,TITLE,DESCRIPTION) VALUES (2, 'Categorie 2', 'Description of Categorie 2')
INSERT INTO OBSERVATION_CATEGORIE (ID,TITLE,DESCRIPTION) VALUES (3, 'Categorie 3', 'Description of Categorie 3')

INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (1,'user1@example.com','UserName1','UserFamilyName1','1','password1','2010-03-07 19:01:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (2,'user2@example.com','UserName2','UserFamilyName2','2','password2','2010-03-07 19:02:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (3,'user3@example.com','UserName3','UserFamilyName3','3','password3','2010-03-07 19:04:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (4,'user4@example.com','UserName4','UserFamilyName4','1','password4','2010-03-07 19:03:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (5,'user5@example.com','UserName5','UserFamilyName5','2','password5','2010-03-07 19:05:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (6,'user6@example.com','UserName6','UserFamilyName6','3','password6','2010-03-07 19:06:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (7,'user7@example.com','UserName7','UserFamilyName7','1','password7','2010-03-07 19:07:00')
INSERT INTO USER (ID,MAIL_ADDRESS,NAME,FAMILY_NAME,USER_RIGHT_ID,HASHED_PASSWORD,CREATION_DATE) VALUES (8,'user8@example.com','UserName8','UserFamilyName8','2','password8','2010-03-07 19:08:00')

INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (1,'Observation 1','Description of Observation 1',1,'2010-03-07 19:40:00',1,1.0,9.0)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (2,'Observation 2','Description of Observation 2',2,'2010-03-07 19:41:00',2,1.1,8.9)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (3,'Observation 3','Description of Observation 3',3,'2010-03-07 19:42:00',3,1.2,8.8)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (4,'Observation 4','Description of Observation 4',4,'2010-03-07 19:43:00',1,1.3,8.7)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (5,'Observation 5','Description of Observation 5',5,'2010-03-07 19:44:00',2,1.4,8.6)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (6,'Observation 6','Description of Observation 6',6,'2010-03-07 19:45:00',3,1.5,8.5)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (7,'Observation 7','Description of Observation 7',7,'2010-03-07 19:46:00',1,1.6,8.4)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (8,'Observation 8','Description of Observation 8',8,'2010-03-07 19:47:00',2,1.7,8.3)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (9,'Observation 9','Description of Observation 9',1,'2010-03-07 19:48:00',3,1.8,8.2)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (10,'Observation 10','Description of Observation 10',2,'2010-03-07 19:49:00',1,1.9,8.1)
INSERT INTO OBSERVATION (ID,TITLE,DESCRIPTION,USER_ID,CREATION_TIME,OBSERVATION_CATEGORIE_ID,LONGITUDE,LATITUDE) VALUES (11,'Observation 11','Description of Observation 11',3,'2010-03-07 19:50:00',2,2.0,8.0)