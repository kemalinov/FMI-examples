--<ScriptOptions statementTerminator=";"/>

CREATE TABLE USERS (
		USER_ID INTEGER DEFAULT AUTOINCREMENT: start 1 increment 1 NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 3),
		NAME VARCHAR(120),
		PASSWORD VARCHAR(75) NOT NULL
	);

CREATE UNIQUE INDEX SQL130501173117590 ON USERS (USER_ID ASC);

ALTER TABLE USERS ADD CONSTRAINT SQL130501173117590 PRIMARY KEY (USER_ID);


--<ScriptOptions statementTerminator=";"/> 
CREATE TABLE ROLES (
		ROLE_ID INTEGER DEFAULT AUTOINCREMENT: start 1 increment 1 NOT NULL GENERATED ALWAYS AS IDENTITY  (START WITH 1 ,INCREMENT BY 4),
		NAME VARCHAR(120) NOT NULL
	);

CREATE UNIQUE INDEX SQL130501184259890 ON ROLES (ROLE_ID ASC);

ALTER TABLE ROLES ADD CONSTRAINT SQL130501184259890 PRIMARY KEY (ROLE_ID);

--<ScriptOptions statementTerminator=";"/>

CREATE TABLE USER_ROLE_MAP (
		USERID_USER_ID INTEGER NOT NULL,
		ROLEID_ROLE_ID INTEGER NOT NULL
	);

CREATE UNIQUE INDEX SQL130501233617710 ON USER_ROLE_MAP (USERID_USER_ID ASC);

CREATE INDEX SQL130501233617711 ON USER_ROLE_MAP (ROLEID_ROLE_ID ASC);

CREATE INDEX SQL130501233617712 ON USER_ROLE_MAP (USERID_USER_ID ASC);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT SQL130501233617710 PRIMARY KEY (USERID_USER_ID);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT ROLE_ID_FK FOREIGN KEY (ROLEID_ROLE_ID)
	REFERENCES ROLES (ROLE_ID);

ALTER TABLE USER_ROLE_MAP ADD CONSTRAINT USER_ID_FK FOREIGN KEY (USERID_USER_ID)
	REFERENCES USERS (USER_ID);
