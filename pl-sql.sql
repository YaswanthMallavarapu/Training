USE hexaware_training;

DROP TABLE IF EXISTS sales;

CREATE TABLE sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    category VARCHAR(255),
    quantity INT,
    price DOUBLE,
    date_of_sale DATE
);

DESC sales;


INSERT INTO sales (product_name, category, quantity, price, date_of_sale)
VALUES 
('Laptop', 'Electronics', 2, 55000.00, '2026-03-01'),
('Mobile', 'Electronics', 5, 20000.00, '2026-03-02'),
('Shoes', 'Fashion', 3, 2500.00, '2026-03-03'),
('Watch', 'Accessories', 4, 3000.00, '2026-03-04'),
('Headphones', 'Electronics', 6, 1500.00, '2026-03-05');

select * from sales;

-- 1. WAP get_sales_by_category 
-- input: category 

delimiter $$
create procedure get_sales_by_category(in p_category varchar(255))
begin
if trim(p_category)="" or p_category is null then
signal sqlstate "45000"
set message_text="category must be valid and cannot be empty or null";
end if;
select * 
from sales 
where category=p_category;
end
$$
drop procedure get_sales_by_category;
call get_sales_by_category("Electronics");


-- WAP Count Total sales by given category 
-- input: category 
-- output: INT total_sales

delimiter $$
create procedure total_sale(in p_category varchar(255),out  total_sales int)
begin
if trim(p_category)="" or p_category is null then
signal sqlstate "45000"
set message_text="category must be valid and cannot be empty or null";
end if;
select count(*) into total_sales
from sales
where category=p_category;
end
$$ 
drop procedure total_sales;
set @total=0;
call total_sale("Electronics",@total);
select @total;


-- 3. Create a View to product sales summary 
-- product_name, cateogry, quantity, price 

create view sales_view as
select product_name,category,quantity,price
from sales;

select * from sales_view;

use hex_ecom_proj;

select v.name as vendor ,count(p.vendor_id) as number_of_product,avg(p.price) as average_price from product p right join vendor v on p.vendor_id=v.id 
group by v.name; 
