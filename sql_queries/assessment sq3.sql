drop database assignment3;
create database assignment3;
use assignment3;
create table Customers(CustomerID int auto_increment PRIMARY KEY, FirstName varchar(20) , 
LastName varchar(20), Email varchar(30), Phone varchar(15) , Address varchar(50));
create table accounts(AccountID int auto_increment primary KEY, CustomerID int, AccountType varchar(20) , 
Balance int, CreatedDate date , FOREIGN KEY (CustomerID) references customers(customerid));
create TABLE Transactions(TransactionID int auto_increment PRIMARY KEY, AccountID INT, TransactionType varchar(20),
Amount varchar(20),TransactionDate datetime, FOREIGN KEY (AccountID) references accounts(accountid));
create TABLE Branches(BranchID int PRIMARY KEY, BranchName varchar(20),Location varchar(20));
create table Employees(EmployeeID int PRIMARY KEY, BranchID int , FirstName varchar(20), LastName 
varchar(20), Role varchar(20), Salary int , FOREIGN KEY (BranchID) references branches(branchid));
INSERT INTO Customers (FirstName, LastName, Email, Phone, Address) VALUES ('John', 'Doe', 'john.doe@example.com', '1234567890', '123 Elm St'), ('Jane', 'Smith', 'jane.smith@example.com', '9876543210', '456 Oak St'), ('Michael', 'Brown', 'michael.brown@example.com', '5678901234', '789 Pine St');
INSERT INTO Accounts (CustomerID, AccountType, Balance, CreatedDate)
VALUES(1, 'Savings', 5000.00, '2023-01-15'),
(1, 'Checking', 2000.00, '2023-02-10'),
(2, 'Savings', 10000.00, '2023-03-05'),
(3, 'Savings', 7000.00, '2023-04-20');
INSERT INTO Branches (BranchID, BranchName, Location)
VALUES (101, 'New York Branch', 'New York'),
(102, 'LA Branch', 'Los Angeles'),
(103, 'Chicago Branch', 'Chicago'),
(104, 'Houston Branch', 'Houston');
INSERT INTO Employees (EmployeeID, BranchID, FirstName, LastName, Role, Salary)
VALUES (1, 101, 'Alice', 'Johnson', 'Manager', 70000),
(2, 101, 'Bob', 'Smith', 'Salesperson', 50000),
(3, 102, 'Charlie', 'Brown', 'Clerk', 40000),
(4, 103, 'David', 'Wilson', 'Technician', 45000);
INSERT INTO Transactions (AccountID, TransactionType, Amount, TransactionDate)
VALUES(1, 'Deposit', 1000.00, '2023-01-20 10:00:00'),
(1, 'Withdrawal', 500.00, '2023-01-25 14:30:00'),
(2, 'Deposit', 2000.00, '2023-02-15 09:15:00'),
(3, 'Withdrawal', 1000.00, '2023-04-25 16:45:00');  
select * from customers;
select * from accounts;
select * from transactions;
select * from employees;
select * from branches;

#List all customers and their accounts with balances.
select c.customerid,c.firstname,c.lastname,a.accountid,a.balance from accounts a inner join customers c on a.customerid=c.customerid;

# List all employees who manage branches where the total account balances exceed $20,000.
SELECT e.employeeid, e.firstname, e.lastname, b.branchname, SUM(a.balance) AS total_balance FROM employees e INNER JOIN branches b ON e.branchid = b.branchid
INNER JOIN accounts a ON a.customerid IN (SELECT c.customerid FROM customers c INNER JOIN accounts acc ON c.customerid = acc.customerid 
WHERE acc.createddate IS NOT NULL)WHERE e.role = 'Manager'GROUP BY e.employeeid, e.branchid HAVING SUM(a.balance) > 20000;

#Identify accounts whose balance is higher than the average balance of accounts within their branch.
SELECT a.accountid, a.balance, b.branchname  FROM accounts a INNER JOIN customers c ON a.customerid = c.customerid INNER JOIN branches b ON c.customerid = a.customerid 
WHERE a.balance > (SELECT AVG(balance)FROM accounts sub_a WHERE sub_a.customerid = a.customerid);


#Find customers who have at least one transaction of more than $1,000. 
select c.customerid,c.firstname,c.lastname from  ((accounts a inner join customers c on a.customerid=c.customerid) inner join transactions t  on a.accountid=c.customerid) where t.amount>1000;

# Get the total deposits and total withdrawals for each account, along with the account type.
select transactiontype,sum(amount) as total from  transactions  group by transactiontype;

#Find pairs of customers who have accounts with the same account type and belong to the same branch.
SELECT c1.CustomerID AS Customer1, c2.CustomerID AS Customer2, a1.AccountType from Accounts a1 INNER JOIN Accounts a2 ON a1.AccountType = a2.AccountType 
and a1.CustomerID < a2.CustomerID INNER JOIN Customers c1 ON a1.CustomerID = c1.CustomerID INNER JOIN Customers c2 on a2.CustomerID = c2.CustomerID;

#Find customers who do not have any transactions recorded.
SELECT c.CustomerID, c.FirstName, c.LastName from Customers c LEFT JOIN Accounts a on c.CustomerID = a.CustomerID LEFT JOIN Transactions t ON a.AccountID = t.AccountID
WHERE t.TransactionID IS NULL;


#Rank customers based on their total balance across all accounts.
SELECT c.CustomerID, c.FirstName, c.LastName, SUM(a.Balance) AS TotalBalance,RANK() OVER (ORDER BY SUM(a.Balance) DESC)
from Customers c INNER JOIN Accounts a on c.CustomerID = a.CustomerID GROUP BY c.CustomerID, c.FirstName, c.LastName;



#List employees whose salary is above the average salary of all employees in their branch.
SELECT e.EmployeeID, e.FirstName, e.LastName, e.Salary, b.BranchName FROM Employees e INNER JOIN Branches b ON e.BranchID = b.BranchID WHERE e.Salary > (SELECT AVG(Salary)
from Employees WHERE BranchID = e.BranchID);





