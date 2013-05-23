---Scheme "App"

--- Users
CREATE TABLE app.users (
  user_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(120),
  password VARCHAR(75) NOT NULL
 );

 
--- Roles
CREATE TABLE app.roles (
  role_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(120) NOT NULL
);

--- User_role_map
CREATE TABLE app.user_role_map (
	userid_user_id INTEGER PRIMARY KEY NOT NULL,  
	roleid_role_id INTEGER NOT NULL,
  CONSTRAINT role_id_fk FOREIGN KEY (roleid_role_id)
	REFERENCES app.roles (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT user_id_fk FOREIGN KEY (userid_user_id)
	REFERENCES app.users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

--- drinks
CREATE TABLE app.drinks (
  drink_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL UNIQUE,
  ingredients VARCHAR(200) NOT NULL,
  price DECIMAL(5,2) NOT NULL
);


--- consumers
CREATE TABLE app.consumers (
	consumer_id  INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	place VARCHAR(20) NOT NULL,
	time_ TIMESTAMP NOT NULL,
	user_user_id INTEGER NOT NULL,
	closed BOOLEAN DEFAULT false,
CONSTRAINT consumers_user_id_fk FOREIGN KEY (user_user_id)
	REFERENCES app.users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);	

--- orders	
CREATE TABLE app.orders (
	order_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	consumer_consumer_id INTEGER NOT NULL,
	status VARCHAR(35) NOT NULL,
	bill DECIMAL(5,2) NOT NULL,
CONSTRAINT orders_consumer_id_fk FOREIGN KEY (consumer_consumer_id)
	REFERENCES app.consumers (consumer_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
);	

--- ordered_drinks
CREATE TABLE app.ordered_drinks (
	orderentity_order_id INTEGER,
	drinks INTEGER,
	drinks_key INTEGER,
 CONSTRAINT ordered_drinks_drink_id_fk FOREIGN KEY (drinks_key)
	REFERENCES app.drinks (drink_id) ON DELETE NO ACTION ON UPDATE NO ACTION
CONSTRAINT ordered_drinks_order_id_fk FOREIGN KEY (orderentity_order_id)
	REFERENCES app.orders (order_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);	

