use hexaware_training;
create table products(
id int primary key auto_increment,
name varchar(255),
price int,
stock int
);

create table transactions(
id int primary key auto_increment,
product_id int,
quantity int,
sale_date date
);

insert into products(name,price,stock) values("REDMI note 8",13200,6),
("lenova ideapad","52000",3);

select * from products;

-- insert into transactions values (1, 2, now()); 
/*
	NEW.product_id = 1
    NEW.quantity = 2 
    
    OLD:- points to records which are already present in DB 
    NEW:- points to records which are waiting to be inserted in DB 
*/

delimiter $$
create trigger tgr_check_stock_availabilty
before insert on transactions
for each row
begin
declare curr_stock int;
 select stock into curr_stock
 from products
 where id=new.product_id;
 
if new.quantity>curr_stock then
signal sqlstate "45000"
set message_text="insufficient stock";
end if;
end
$$
drop trigger  tgr_check_stock_availabilty;
insert into transactions(product_id,quantity,sale_date) values(1,10,now());

select * from transactions;

DELIMITER $$ 
create trigger tgr_update_stock
AFTER INSERT ON transactions
FOR EACH ROW 
BEGIN
update products 
SET stock = stock - NEW.quantity 
where id = NEW.product_id;
END
$$

insert into transactions(product_id, quantity, sale_date) values (1, 1, now()); 

delimiter $$
create trigger tgr_out_of_stocks
after insert on transactions
for each row
begin
declare rem_stock int;
select stock into rem_stock
from products
where id=new.product_id;

if rem_stock=0 then
signal sqlstate "45000"
set message_text="this product is out of stocks";
end if;
end
$$

delimiter $$
create procedure get_all_products()
begin
select * 
from products ;
end
$$

call get_all_products();



delimiter $$
create procedure get_product_names()
begin

declare v_name varchar(255);
declare done boolean DEFAULT FALSE;
declare product_name_cursor cursor for 
select name from products;
declare continue handler for not found set done=true;

open product_name_cursor;
cursor_loop:LOOP
fetch product_name_cursor into v_name;
if done then
leave cursor_loop;
end if;
select v_name;
end loop;
close product_name_cursor;

end
$$

call get_product_names();


-- delimiter $$
-- create procedure get_product_details()
-- begin
-- declare p_name varchar(255);
-- declare p_price int;
-- declare done boolean default false;
-- declare get_products_cursor cursor for
-- select 
-- name into p_name,
-- price into p_price from products;
-- end
-- $$


use  hexaware_assest_management;

-- - Display all Assets that a certain employee has (employee_id)
/*
	1. Manual mapping
    2. Join
*/

-- using manual join

select a.title,a.category,ae.date_of_allocation,e.name from asset a,
asset_employee ae,employee e
where a.id=ae.asset_id and 
ae.employee_id=e.id and
 e.id=1;

-- using join

select a.title,a.category,ae.date_of_allocation,e.name from 
asset a join asset_employee ae on a.id=ae.asset_id
join employee e on e.id=ae.employee_id
where e.id=1;


-- Query -- 2: Display all Employees that belong to given department and have borrowed asset with category='Peripherals'

-- using manual
select e.* from 
asset a,asset_employee ae,employee e,department d
where a.id=ae.asset_id and
ae.employee_id=e.id and
e.department_id=d.id and
d.id=1 and
a.category="Peripheral";


-- using join
SELECT e.*
FROM asset a
JOIN asset_employee ae ON a.id = ae.asset_id
JOIN employee e ON ae.employee_id = e.id
JOIN department d ON e.department_id = d.id
WHERE d.id = 1
AND a.category = "Peripheral";

-- Display number of employees for each department 
select d.name as"department_name",count(*) as "number of employees" from 
department d,employee e
where d.id=e.department_id
group by d.name;


-- To count assets being given to employees of each department 

select d.name,count(ae.asset_id) from  asset_employee ae join employee e on ae.employee_id = e.id
right join department d on d.id=e.department_id
group by d.id;



select * from employee e
where e.id in(
              select id from asset_employee ae where ae.asset_id in (
              select id from asset where category="laptop"));


