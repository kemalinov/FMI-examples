--<ScriptOptions statementTerminator=";"/>

ALTER TABLE ORDERED_DRINKS DROP CONSTRAINT RDRDDRINKSDRNKSKEY;

ALTER TABLE ORDERED_DRINKS DROP CONSTRAINT RDRDDRNRDRNTTYRDRD;

ALTER TABLE USER_ROLE_MAP DROP CONSTRAINT SRROLEMAPSRDUSERID;

ALTER TABLE USER_ROLE_MAP DROP CONSTRAINT SRROLEMAPRLDROLEID;

ALTER TABLE CONSUMERS DROP CONSTRAINT SQL130511195424860;

ALTER TABLE USERS DROP CONSTRAINT SQL130511195425780;

ALTER TABLE ORDERS DROP CONSTRAINT SQL130511195425580;

ALTER TABLE USER_ROLE_MAP DROP CONSTRAINT SQL130511195426040;

ALTER TABLE DRINKS DROP CONSTRAINT SQL130511195425080;

ALTER TABLE ROLES DROP CONSTRAINT SQL130511195425290;

DROP INDEX SQL130511195425780;

DROP INDEX SQL130511195426700;

DROP INDEX SQL130511195426870;

DROP INDEX SQL130511195425290;

DROP INDEX SQL130511195426300;

DROP INDEX SQL130511195426550;

DROP INDEX SQL130511195425080;

DROP INDEX SQL130511195424860;

DROP INDEX SQL130511195425580;

DROP INDEX SQL130511195426040;

DROP TABLE CONSUMERS;

DROP TABLE ORDERS;

DROP TABLE ROLES;

DROP TABLE DRINKS;

DROP TABLE USERS;

DROP TABLE USER_ROLE_MAP;

DROP TABLE ORDERED_DRINKS;

CREATE TABLE CONSUMERS (
		CONSUMER_ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL,
		PLACE VARCHAR(255),
		TIME_ TIMESTAMP,
		USER_ID INTEGER
	);

CREATE TABLE ORDERS (
		ORDER_ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL,
		CONSUMER_ID INTEGER,
		STATUS VARCHAR(255),
		BILL DECIMAL(5 , 2) NOT NULL
	);

CREATE TABLE ROLES (
		ROLE_ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL,
		NAME VARCHAR(255)
	);

CREATE TABLE DRINKS (
		DRINK_ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL,
		INGREDIENTS VARCHAR(255),
		NAME VARCHAR(255),
		PRICE DECIMAL(5 , 2) NOT NULL
	);

CREATE TABLE USERS (
		USER_ID INTEGER DEFAULT GENERATED_BY_DEFAULT NOT NULL,
		NAME VARCHAR(255),
		PASSWORD VARCHAR(255)
	);

CREATE TABLE USER_ROLE_MAP (
		USERID_USER_ID INTEGER NOT NULL,
		ROLEID_ROLE_ID INTEGER NOT NULL
	);

CREATE TABLE ORDERED_DRINKS (
		ORDERENTITY_ORDER_ID INTEGER,
		DRINKS INTEGER,
		DRINKS_KEY INTEGER
	);

CREATE UNIQUE INDEX SQL130511195425780 ON USERS (USER_ID ASC);

CREATE INDEX SQL130511195426700 ON USER_ROLE_MAP (ROLEID_ROLE_ID ASC);

CREATE INDEX SQL130511195426870 ON USER_ROLE_MAP (USERID_USER_ID ASC);

CREATE UNIQUE INDEX SQL130511195425290 ON ROLES (ROLE_ID ASC);

CREATE INDEX SQL130511195426300 ON ORDERED_DRINKS (DRINKS_KEY ASC);

CREATE INDEX SQL130511195426550 ON ORDERED_DRINKS (ORDERENTITY_ORDER_ID ASC);

CREATE UNIQUE INDEX SQL130511195425080 ON DRINKS (DRINK_ID ASC);

CREATE UNIQUE INDEX SQL130511195424860 ON CONSUMERS (CONSUMER_ID ASC);

CREATE UNIQUE INDEX SQL130511195425580 ON ORDERS (ORDER_ID ASC);

CREATE UNIQUE INDEX SQL130511195426040 ON USER_ROLE_MAP (USERID_USER_ID ASC, ROLEID_ROLE_ID ASC);

ALTER TABLE CONSUMERS ADD CONSTRAINT SQL130511195424860 PRIMARY KEY (CONSUMER_ID);

ALTER TABLE USERS ADD CONSTRAINT SQL130511195425780 PRIMARY KEY (USER_ID);

ALTER TABLE ORDERS ADD CONSTRAINT SQL130511195425580 PRIMARY KEY (ORDER_ID);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT SQL130511195426040 PRIMARY KEY (USERID_USER_ID, ROLEID_ROLE_ID);

ALTER TABLE DRINKS ADD CONSTRAINT SQL130511195425080 PRIMARY KEY (DRINK_ID);

ALTER TABLE ROLES ADD CONSTRAINT SQL130511195425290 PRIMARY KEY (ROLE_ID);

ALTER TABLE ORDERED_DRINKS ADD CONSTRAINT RDRDDRINKSDRNKSKEY FOREIGN KEY (DRINKS_KEY)
	REFERENCES DRINKS (DRINK_ID);

ALTER TABLE ORDERED_DRINKS ADD CONSTRAINT RDRDDRNRDRNTTYRDRD FOREIGN KEY (ORDERENTITY_ORDER_ID)
	REFERENCES ORDERS (ORDER_ID);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT SRROLEMAPSRDUSERID FOREIGN KEY (USERID_USER_ID)
	REFERENCES USERS (USER_ID);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT SRROLEMAPRLDROLEID FOREIGN KEY (ROLEID_ROLE_ID)
	REFERENCES ROLES (ROLE_ID);
