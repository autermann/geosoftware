CREATE TABLE sloth_obsTypes (
  obsTypeID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  obsTypeName VARCHAR(45) NOT NULL,
  obsTypeDesc VARCHAR(100) NOT NULL,
  PRIMARY KEY(obsTypeID)
);

CREATE TABLE sloth_userAccessRights (
  uarID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  uarName VARCHAR(20) NOT NULL,
  uarDesc VARCHAR(100) NULL,
  PRIMARY KEY(uarID)
);

CREATE TABLE sloth_users (
  userID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sloth_userAccessRights_uarID INTEGER UNSIGNED NOT NULL,
  userLastName VARCHAR(45) NOT NULL,
  userFirstName VARCHAR(45) NOT NULL,
  userSignDate DATE NOT NULL,
  userHashedPwd CHAR(100) NOT NULL,
  userEmail CHAR(60) NOT NULL,
  PRIMARY KEY(userID),
  INDEX sloth_users_FKIndex1(sloth_userAccessRights_uarID),
  FOREIGN KEY(sloth_userAccessRights_uarID)
    REFERENCES sloth_userAccessRights(uarID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE sloth_observations (
  obsID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  sloth_obsTypes_obsTypeID INTEGER UNSIGNED NOT NULL,
  obsName VARCHAR(45) NOT NULL,
  obsDescription VARCHAR(1000) NOT NULL,
  obsDate DATE NOT NULL,
  obsTime TIME NOT NULL,
  PRIMARY KEY(obsID),
  INDEX sloth_observations_FKIndex1(sloth_obsTypes_obsTypeID),
  FOREIGN KEY(sloth_obsTypes_obsTypeID)
    REFERENCES sloth_obsTypes(obsTypeID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE sloth_usrobs (
  sloth_users_userID INTEGER UNSIGNED NOT NULL,
  sloth_observations_obsID INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(sloth_users_userID, sloth_observations_obsID),
  INDEX sloth_usrobs_FKIndex1(sloth_users_userID),
  INDEX sloth_usrobs_FKIndex2(sloth_observations_obsID),
  FOREIGN KEY(sloth_users_userID)
    REFERENCES sloth_users(userID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(sloth_observations_obsID)
    REFERENCES sloth_observations(obsID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE sloth_obsCoordinates (
  sloth_observations_obsID INTEGER UNSIGNED NOT NULL,
  obsLat DOUBLE NOT NULL,
  obsLong DOUBLE NOT NULL,
  PRIMARY KEY(sloth_observations_obsID),
  INDEX sloth_obsCoordinates_FKIndex1(sloth_observations_obsID),
  FOREIGN KEY(sloth_observations_obsID)
    REFERENCES sloth_observations(obsID)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);


