CREATE TABLE `SEQUENCE` (
	`SEQ_NAME` varchar(50) collate utf8_unicode_ci NOT NULL,
	`SEQ_COUNT` decimal(38,0) default NULL,
	PRIMARY KEY  (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `OBSERVATION_CATEGORIE` (
	`ID` bigint(20) NOT NULL,
	`TITLE` varchar(255) collate utf8_unicode_ci NOT NULL,
	`DESCRIPTION` varchar(1000) collate utf8_unicode_ci NOT NULL,
	PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `USER_RIGHT` (
	`VALUE` int(11) NOT NULL,
	`DESCRIPTION` varchar(1000) collate utf8_unicode_ci NOT NULL,
	`NAME` varchar(255) collate utf8_unicode_ci NOT NULL,
	PRIMARY KEY  (`VALUE`),
	UNIQUE KEY `VALUE` (`VALUE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `USER` (
	`ID` bigint(20) NOT NULL,
	`CREATION_DATE` datetime NOT NULL,
	`NAME` varchar(255) collate utf8_unicode_ci NOT NULL,
	`MAIL_ADDRESS` varchar(255) collate utf8_unicode_ci NOT NULL,
	`FAMILY_NAME` varchar(255) collate utf8_unicode_ci NOT NULL,
	`HASHED_PASSWORD` varchar(255) collate utf8_unicode_ci NOT NULL,
	`USER_RIGHT_ID` int(11) NOT NULL, PRIMARY KEY  (`ID`), UNIQUE KEY
	`MAIL_ADDRESS` (`MAIL_ADDRESS`),
	KEY `FK_USER_USER_RIGHT_ID` (`USER_RIGHT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `OBSERVATION` (
	`ID` bigint(20) NOT NULL,
	`TITLE` varchar(255) collate utf8_unicode_ci NOT NULL,
	`DESCRIPTION` varchar(1000) collate utf8_unicode_ci NOT NULL,
	`CREATION_TIME` datetime NOT NULL,
	`OBSERVATION_CATEGORIE_ID` bigint(20) NOT NULL,
	`USER_ID` bigint(20) NOT NULL,
	`LONGITUDE` double NOT NULL,
	`LATITUDE` double NOT NULL,
	PRIMARY KEY  (`ID`),
	KEY `FK_OBSERVATION_OBSERVATION_CATEGORIE_ID` (`OBSERVATION_CATEGORIE_ID`),
	KEY `FK_OBSERVATION_USER_ID` (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
