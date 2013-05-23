INSERT INTO app.users (name, password) VALUES ('gosho', 'ddd');
INSERT INTO app.user_role_map (userid_user_id, roleid_role_id) VALUES (3,2);

INSERT INTO app.roles (name) VALUES ('WAITER');
INSERT INTO app.roles (name) VALUES ('BARMAN');
INSERT INTO app.roles (name) VALUES ('MANAGER');


update app.users set name='kalin' where user_id=2;
update app.roles set role_id=1 where name='WAITER';

RENAME COLUMN app.roles.rolename TO name

select * from app.roles;
select * from app.users;
select * from app.user_role_map;

drop table app.user_role_map

delete from APP.USER_ROLE_MAP where userid_user_id=6;
delete from app.users where user_id=6;
delete from app.roles where role_id=5;

RENAME COLUMN app.users.id TO user_id
RENAME COLUMN app.roles.id TO role_id

RENAME COLUMN app.user_role_map.role_id TO roleid_role_id
RENAME COLUMN app.user_role_map.user_id TO userid_user_id

ALTER TABLE app.user_role_map ADD UNIQUE (user_id) 

ALTER TABLE app.users DROP COLUMN role_id
ALTER TABLE app.users DROP COLUMN active
ALTER TABLE app.users ADD COLUMN role_id INTEGER

ALTER TABLE app.user_role_map ADD CONSTRAINT role_id_fk FOREIGN KEY (roleid_role_id)
	REFERENCES app.roles (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE app.user_role_map ADD CONSTRAINT user_id_fk FOREIGN KEY (userid_user_id)
	REFERENCES app.users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--- neseted - works
SELECT *
FROM APP.USERs
where user_id = (
SELECT userid_user_id
FROM APP.USER_ROLE_MAP
where roleid_role_id = 1)
ORDER by user_id SC

--- join ?
select *
from APP.USERS
right join APP.USER_ROLE_MAP 
ON userid_user_id=user_id and roleid_role_id=1

SELECT roleid_role_id FROM app.user_role_map WHERE userid_user_id = 7
SELECT userid_user_id FROM app.user_role_map WHERE roleid_role_id = 1

-----------------------------



select * from app.ordered_drinks;

select * from app.orders;

delete from app.ordered_drinks where orderentity_order_id=1


update app.drinks set price=1.90 where drink_id=1;


delete from app.orders where order_id>3


ALTER TABLE app.orders add COLUMN drinks user-defined


select * from app.consumers;
RENAME COLUMN app.consumers.user_id TO user_user_id

delete from app.consumers where consumer_id=2
ALTER TABLE app.consumers ADD column closed BOOLEAN default false NOT NULL


select * from app.drinks;
alter table app.drinks add column price DECIMAL(5,2)
alter table app.drinks alter column price NOT NULL


select * from app.orders;
alter table app.orders add column bill DECIMAL(5,2)
alter table app.orders alter column bill NOT NULL

RENAME COLUMN app.orders.consumer_id TO consumer_consumer_id

SELECT status FROM app.orders WHERE order_id=5

SELECT sum(bill) FROM app.orders WHERE consumer_id=6
 
-- returns the count of buied drinks per kind for a client  
SELECT sum(od.drinks) 
FROM app.ordered_drinks od
join app.orders o
on od.orderentity_order_id=o.order_id and o.consumer_id=6 and od.drinks=1 




WHERE consumer_id=6 and drinks_key=1 -- needs a join

