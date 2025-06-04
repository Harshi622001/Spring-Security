package com.test.SpringSecurity.Controller;

import com.test.SpringSecurity.Entity.Employee;
import com.test.SpringSecurity.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee emp=employeeRepo.save(employee);
        return new ResponseEntity<Employee>(emp, HttpStatus.OK);
    }
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the application with security!";
    }
}
