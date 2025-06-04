package com.test.SpringSecurity.Service;

import com.test.SpringSecurity.Entity.Employee;
import com.test.SpringSecurity.Entity.MyUserDetails;
import com.test.SpringSecurity.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Employee> optEmployee=employeeRepo.findByUserName(username);
      if(optEmployee.isPresent()){
          return new MyUserDetails(optEmployee.get());

      }
      else {
          throw new UsernameNotFoundException("No User Exist with this username");
      }

    }
}
