use car_renatl;


INSERT INTO car_details (year_of_purchase, milage) VALUES
(2015, 45000),(2016, 38000),(2017, 52000),(2018, 30000),(2019, 25000),
(2020, 15000),(2021, 10000),(2014, 60000),(2013, 75000),(2012, 82000),
(2011, 90000),(2010, 110000),(2022, 8000),(2023, 5000),(2016, 47000),
(2017, 35000),(2018, 29000),(2019, 21000),(2020, 16000),(2021, 12000),
(2015, 53000),(2014, 62000),(2013, 70000),(2012, 88000),(2011, 94000),
(2022, 6000),(2023, 3000),(2016, 41000),(2018, 27000),(2019, 22000);

INSERT INTO owner (name, address) VALUES
('Ramesh','Hyderabad'),
('Suresh','Chennai'),
('Mahesh','Bangalore'),
('Rajesh','Mumbai'),
('Anil','Delhi'),
('Kiran','Pune'),
('Vikram','Ahmedabad'),
('Arjun','Kolkata'),
('Rahul','Jaipur'),
('Naveen','Lucknow'),
('Prakash','Bhopal'),
('Sanjay','Patna'),
('Amit','Nagpur'),
('Deepak','Indore'),
('Harish','Surat'),
('Manoj','Vijayawada'),
('Naresh','Visakhapatnam'),
('Teja','Warangal'),
('Karthik','Coimbatore'),
('Dinesh','Mysore'),
('Ravi','Noida'),
('Tarun','Gurgaon'),
('Lokesh','Chandigarh'),
('Yogesh','Kanpur'),
('Vamsi','Guntur'),
('Ajay','Thane'),
('Pavan','Rajahmundry'),
('Krishna','Nellore'),
('Varun','Trichy'),
('Rohit','Shimla');

INSERT INTO car 
(registration_number, chasis_number, brand, variant, owner_id, car_details_id, registration_state, model)
VALUES
('TS31AB1234','CH031','Toyota','Petrol',21,21,'Telangana','Innova'),
('TN32BC2345','CH032','Honda','Diesel',22,22,'Tamil Nadu','City'),
('KA33CD3456','CH033','Hyundai','Petrol',23,23,'Karnataka','i20'),
('MH34DE4567','CH034','Maruti','Petrol',24,24,'Maharashtra','Swift'),
('DL35EF5678','CH035','Tata','Diesel',25,25,'Delhi','Nexon'),
('TS36FG6789','CH036','Toyota','Diesel',26,26,'Telangana','Fortuner'),
('TN37GH7890','CH037','Honda','Petrol',27,27,'Tamil Nadu','Amaze'),
('KA38HI8901','CH038','Hyundai','Diesel',28,28,'Karnataka','Creta'),
('MH39IJ9012','CH039','Mahindra','Diesel',29,29,'Maharashtra','Scorpio'),
('DL40JK0123','CH040','Tata','Petrol',30,30,'Delhi','Punch'),
('TS41KL1122','CH041','Kia','Petrol',31,31,'Telangana','Seltos'),
('TN42LM2233','CH042','MG','Diesel',32,32,'Tamil Nadu','Hector'),
('KA43MN3344','CH043','Skoda','Petrol',33,33,'Karnataka','Slavia'),
('MH44NO4455','CH044','Volkswagen','Diesel',34,34,'Maharashtra','Virtus'),
('DL45OP5566','CH045','Renault','Petrol',35,35,'Delhi','Kiger'),
('TS46PQ6677','CH046','Nissan','Diesel',36,36,'Telangana','Magnite'),
('TN47QR7788','CH047','Hyundai','Petrol',37,37,'Tamil Nadu','Verna'),
('KA48RS8899','CH048','Toyota','Hybrid',38,38,'Karnataka','Camry'),
('MH49ST9900','CH049','Honda','Petrol',39,39,'Maharashtra','WRV'),
('DL50TU1010','CH050','Mahindra','Diesel',40,40,'Delhi','XUV700'),
('TS51UV2020','CH051','Tata','Electric',41,41,'Telangana','Tiago EV'),
('TN52WX3030','CH052','Hyundai','Petrol',42,42,'Tamil Nadu','Venue'),
('KA53YZ4040','CH053','Kia','Diesel',43,43,'Karnataka','Carens'),
('MH54AA5050','CH054','Maruti','Petrol',44,44,'Maharashtra','Baleno'),
('DL55BB6060','CH055','Toyota','Diesel',45,45,'Delhi','Hilux'),
('TS56CC7070','CH056','MG','Electric',46,46,'Telangana','ZS EV'),
('TN57DD8080','CH057','Renault','Petrol',47,47,'Tamil Nadu','Triber'),
('KA58EE9090','CH058','Skoda','Diesel',48,48,'Karnataka','Kushaq'),
('MH59FF1111','CH059','Volkswagen','Petrol',49,49,'Maharashtra','Taigun'),
('DL60GG2222','CH060','Mahindra','Diesel',50,50,'Delhi','Thar');

select c.id as id ,c.registration_number number,c.chasis_number chasis,c.registration_state as state,c.brand brand,c.model model,c.variant variant,o.name as name,cd.year_of_purchase as year,cd.milage as milage
from car c,owner o,car_details cd where o.id=c.owner_id and c.car_details_id=cd.id;

select brand ,count(id) as count from car group by brand;

select * from car_details;
select * from owner;

TRUNCATE TABLE car;
TRUNCATE TABLE owner;
TRUNCATE TABLE car_details;
