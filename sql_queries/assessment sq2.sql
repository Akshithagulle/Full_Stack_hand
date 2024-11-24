CREATE DATABASE IF NOT EXISTS CompanyDB;
USE CompanyDB;

-- Create Departments Table
CREATE TABLE Departments (
    DepartmentID INT AUTO_INCREMENT PRIMARY KEY,
    DepartmentName VARCHAR(50) NOT NULL UNIQUE,
    Location VARCHAR(100) NOT NULL
);

-- Create Employees Table
CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    DepartmentID INT NOT NULL,
    DateOfBirth DATE NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE NOT NULL CHECK (HireDate >= '2000-01-01'),
    FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)
);

-- Create Projects Table
CREATE TABLE Projects (
    ProjectID INT AUTO_INCREMENT PRIMARY KEY,
    ProjectName VARCHAR(100) NOT NULL UNIQUE,
    StartDate DATE NOT NULL,
    EndDate DATE, CHECK (EndDate > StartDate),
    Budget DECIMAL(15, 2) NOT NULL CHECK (Budget > 0)
);

-- Create Assignments Table
CREATE TABLE Assignments (
    AssignmentID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeID INT NOT NULL,
    ProjectID INT NOT NULL,
    HoursWorked DECIMAL(5, 2) NOT NULL CHECK (HoursWorked >= 0),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
);

-- Create Salaries Table
CREATE TABLE Salaries (
    SalaryID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeID INT NOT NULL,
    BaseSalary DECIMAL(10, 2) NOT NULL CHECK (BaseSalary > 0),
    Bonus DECIMAL(10, 2) CHECK (Bonus >= 0),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Insert into Departments
INSERT INTO Departments (DepartmentName, Location) VALUES
('HR', 'New York'),
('IT', 'San Francisco'),
('Finance', 'Chicago'),
('Marketing', 'Los Angeles');

-- Insert into Employees
INSERT INTO Employees (FirstName, LastName, DepartmentID, DateOfBirth, Email, Gender, HireDate) VALUES
('John', 'Doe', 1, '1985-04-12', 'john.doe@example.com', 'Male', '2010-05-10'),
('Jane', 'Smith', 2, '1990-08-23', 'jane.smith@example.com', 'Female', '2015-07-19'),
('Alice', 'Brown', 3, '1982-11-17', 'alice.brown@example.com', 'Female', '2008-02-25'),
('Bob', 'Johnson', 4, '1979-03-30', 'bob.johnson@example.com', 'Male', '2005-01-15');

-- Insert into Projects
INSERT INTO Projects (ProjectName, StartDate, EndDate, Budget) VALUES
('Website Redesign', '2023-01-01', '2023-12-31', 100000),
('Mobile App Development', '2023-03-01', '2024-02-28', 150000),
('Data Migration', '2022-06-01', '2023-06-30', 50000);

-- Insert into Assignments
INSERT INTO Assignments (EmployeeID, ProjectID, HoursWorked) VALUES
(1, 1, 120),
(2, 2, 250),
(3, 3, 180),
(4, 1, 90);

-- Insert into Salaries
INSERT INTO Salaries (EmployeeID, BaseSalary, Bonus) VALUES
(1, 60000, 5000),
(2, 80000, 7000),
(3, 75000, 6000),
(4, 90000, 10000);

select * from departments;
select * from employees;
select * from projects;
select * from assignments;
select * from salaries;

#retrieve all employees in the IT Department
select * from employees e inner join departments d on e.departmentid=d.departmentid where d.departmentName='IT';

#Find employees hired after 2010
select * from employees where hiredate>'2010-12-31';

#list projects with a budget exceeding $80,000
select * from projects where budget>80000;

#sort employees by their hire date in descending order.
select * from employees order by hiredate desc;

#show projects sorted by their budget in ascending order
select * from projects order by budget desc;

#count the  numbers of employees in each department
SELECT d.departmentname, COUNT(e.employeeid) AS employee_count FROM departments d JOIN employees e
 ON d.departmentid = e.departmentid GROUP BY d.departmentname;
 
#display the top 3 employees with highest base salary;
select * from employees e inner join salaries s on e.employeeid=s.employeeid order by basesalary desc limit 3;

#retrieve employee names along with their department names
select e.employeeid,e.firstname,e.lastname,d.departmentname from employees e inner join 
departments d on e.departmentid=d.departmentid where departmentname='IT';

#list all assignments,including employee and project details
select a.assignmentId,a.employeeid,a.projectid,a.hoursworked from  ((assignments a inner join employees e on e.employeeid=a.employeeid) inner join projects p on a.projectid=p.projectid);

#find employees working on the project with highest budget;
select e.employeeid,e.firstname,e.lastname from ((assignments a inner join employees e on e.employeeid=a.employeeid) inner join projects p on a.projectid=p.projectid) order by  p.budget desc limit 1;

#Calculate the age of each employee
#select employeeid,firstname,floor(datediff(curdate(),dateofbirth)/365) as age from employees;
select employeeid,firstname,timestampdiff(year,dateofbirth,curdate()) as age from employees;

#calculate the total salary(base+bonus) for each employee
select e.employeeid,(s.basesalary+s.bonus) as total_sal from employees e inner join salaries s on e.employeeid=s.employeeid ;

#find all employees hired in 2015
select * from employees where year(hiredate)=2015;

#retrieve the names of projects ending before december 2023
select projectname,enddate from projects where enddate<'2023-12-01';

#list employees with base salaries greater than $70,000
select e.employeeid,e.firstname,e.lastname,e.departmentid,e.hiredate from employees e inner join salaries s on e.employeeid=s.employeeid where s.basesalary>70000;

#count the number of projects handled by each employee.
select e.firstname,count(*) as count from  ((assignments a inner join employees e on e.employeeid=a.employeeid) inner join projects p on a.projectid=p.projectid) group by e.firstname;

#list all departments located in 'San Francisco
select * from departments where location='San Francisco';

#display project names along with total hours worked on each.
select projectname,datediff(enddate,startdate)*24 as total_hours from projects;

#find the highest bonus received by any employee.
select e.employeeid,s.bonus  from employees e inner join salaries s on e.employeeid=s.employeeid order by bonus desc limit 1;

#identify projects that lasted for more than 12months.
select * from projects where timestampdiff(month,startdate,enddate)>12;

#retrieve all projects starting in 2023.
select * from projects where year(startdate)='2023';

#Calculate the total hours worked by each employee across all projects.
select a.employeeid,e.firstname,datediff(p.enddate,p.startdate)*24 as total_hours from  ((assignments a inner join employees e on e.employeeid=a.employeeid) inner join projects p on a.projectid=p.projectid);

#Find the department with the most employees.
SELECT d.departmentname, COUNT(e.employeeid) AS employee_count FROM departments d JOIN employees e
 ON d.departmentid = e.departmentid GROUP BY d.departmentname ORDER BY employee_count DESC limit 1 ;

#List employees who were born before 1985.
select * from employees where year(dateofbirth)<'1985';
