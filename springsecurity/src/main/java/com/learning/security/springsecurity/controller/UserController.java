package com.learning.security.springsecurity.controller;

import com.learning.security.springsecurity.config.EazyBankUserDetailsService;
import com.learning.security.springsecurity.model.Customer;
import com.learning.security.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final EazyBankUserDetailsService eazyBankUserDetailsService;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try{
            Optional<Customer> byEmail = customerRepository.findByEmail(customer.getEmail());
            if(byEmail.isEmpty()) {
                String hashedPwd = passwordEncoder.encode(customer.getPwd());
                customer.setPwd(hashedPwd);
                Customer savedC = customerRepository.save(customer);

                if (savedC.getId() > 0) {
                    return ResponseEntity.ok().body("User registered successfully");
                } else {
                    return ResponseEntity.badRequest().body("User registration failed");
                }
            }else{
                return ResponseEntity.badRequest().body("User already exists");
            }
        }catch(Exception ex){
            return ResponseEntity.internalServerError().body("An error occured:"+ex.getMessage());
        }
    }
}
