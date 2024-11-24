USE classicmodels;
select * from products;

select * from employees where officeCode=1 or officeCode=6;
select * from orders where (status= 'On hold' or status='shipped') and customerNumber>300;
select * from customers where (state='NV' or state='CT') and creditLimit>600000;
select productdescription,buyprice,quantityinstock from products where productname in 
('1972 Alfa Romeo GTA','1952 Alpine Renault 1300','1968 Ford Mustang','1969 Ford Falcon',
'2002 Suzuki XREO') and quantityinstock>6000;
select checkNumber,customernumber from payments where paymentDate between '2003-06-25' and '2004-05-30';
select productname,productVendor,buyPrice,productcode from products where productCode like 'S32%';

select * from products where productcode like '_32%';


