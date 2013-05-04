---Scheme "App"

--- Users
CREATE TABLE app.users (
  user_id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(120),
  password VARCHAR(75) NOT NULL,
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

drop table app.drinks

--- orders (1:n)
CREATE TABLE app.OrderEntity_DRINKS (
	order_id INTEGER,
	OrderEntity_ORDER_ID INTEGER,
	drinks INTEGER,
	drinks_key INTEGER,
  CONSTRAINT orders_drink_id_fk FOREIGN KEY (drinks_key)
	REFERENCES app.drinks (drink_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);


drop table app.OrderEntity_DRINKS

RENAME COLUMN app.orders.ORDER_ID TO OrderEntity_ORDER_ID

ALTER TABLE app.orders add COLUMN ORDER_ID INTEGER

OrderEntity_ORDER_ID, DRINKS, drinks_KEY

select * from app.OrderEntity_DRINKS where OrderEntity_order_id=1

delete from app.OrderEntity_DRINKS where drinks is null and order_id is null



ALTER TABLE app.orders DROP CONSTRAINT orders_drink_id_fk
--- TODO:
ALTER TABLE app.orders ADD CONSTRAINT orders_drink_id_fk FOREIGN KEY (drink_id)
	REFERENCES app.drinks (drink_id) ON DELETE NO ACTION ON UPDATE NO ACTION


--- clients
CREATE TABLE app.consumers (
	consumer_id  INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	place VARCHAR(20) NOT NULL,
	time_ TIMESTAMP NOT NULL,
	bill DECIMAL(5,2) NOT NULL,
	user_id INTEGER NOT NULL,
CONSTRAINT consumers_user_id_fk FOREIGN KEY (user_id)
	REFERENCES app.users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);	


delete from app.consumers

drop table app.consumers
select * from app.consumers

--------	
	
CREATE TABLE app.orders (
	consumerEntity_consumer_ID INTEGER,
	drinks INTEGER,
	drinks_key INTEGER,
  CONSTRAINT orders_2_drink_id_fk FOREIGN KEY (drinks_key)
	REFERENCES app.drinks (drink_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);	

RENAME TABLE app.OrderEntity_DRINKS_2 TO orders

RENAME COLUMN app.orders.ordered_Drink TO drinks

select * from app.orders

delete from app.OrderEntity_DRINKS_2
drop table app.OrderEntity_DRINKS_2

select * from app.consumers


	
--- client_order_map (1:n)




--- DEPRECATED !!! - drink_price_map (1:n)
CREATE TABLE app.drink_price_map (
	drink_id INTEGER PRIMARY KEY NOT NULL,
	price DECIMAL(5,2) NOT NULL,
  CONSTRAINT drink_id_fk FOREIGN KEY (drink_id)
	REFERENCES app.drinks (drink_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

drop table app.drink_price_map
-----------------------------------------------------
