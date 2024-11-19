// Employee Management System Class
class EmployeeManagement {
    constructor() {
      this.employees = [];
    }
  
    add_emp(employee) {
      const exists = this.employees.some(emp => emp.id === employee.id);
      if (exists) {
        return "Employee ID already exists!";
      }
      this.employees.push(employee);
      return "Employee added successfully!";
    }
  
    remove_emp(id) {
      const index = this.employees.findIndex(emp => emp.id === id);
      if (index !== -1) {
        this.employees.splice(index, 1);
        return "Employee removed successfully!";
      }
      return "Employee not found!";
    }
  
    searchEmployee(id) {
      const employee = this.employees.find(emp => emp.id === id);
      if (employee) {
        const experience = this.calculateExperience(employee.doj);
        return { ...employee, experience };
      }
      return "Employee not found!";
    }
  
    calculateExperience(doj) {
      const joiningDate = new Date(doj);
      const currentDate = new Date();
      const years = currentDate.getFullYear() - joiningDate.getFullYear();
      const months = currentDate.getMonth() - joiningDate.getMonth();
      return months < 0 ? years - 1 : years;
    }
  }
  
  // Instance of the system
  const system = new EmployeeManagement();
  
  // DOM Manipulation Functions
  function add_emp() {
    const name = document.getElementById("name").value;
    const id = document.getElementById("id").value;
    const skill = document.getElementById("skill").value;
    const doj = document.getElementById("doj").value;
    const department = document.getElementById("department").value;
  
    if (name && id && skill && doj && department) {
      const employee = { name, id, skill, doj, department };
      const message = system.add_emp(employee);
      document.getElementById("result").innerText = message;
    } else {
      document.getElementById("result").innerText = "Please fill all fields!";
    }
  }
  
  function remove_emp() {
    const id = document.getElementById("removeId").value;
    if (id) {
      const message = system.remove_emp(id);
      document.getElementById("result").innerText = message;
    } else {
      document.getElementById("result").innerText = "Please enter an ID!";
    }
  }
  
  function searchEmployee() {
    const id = document.getElementById("searchId").value;
    if (id) {
      const result = system.searchEmployee(id);
      if (typeof result === "string") {
        document.getElementById("result").innerText = result;
      } else {
        document.getElementById("result").innerText = `
          Name: ${result.name}
          ID: ${result.id}
          Skill: ${result.skill}
          DOJ: ${result.doj}
          Department: ${result.department}
          Experience: ${result.experience} years
        `;
      }
    } else {
      document.getElementById("result").innerText = "Please enter an ID!";
    }
  }
  