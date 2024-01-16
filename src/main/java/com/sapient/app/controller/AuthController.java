package com.sapient.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.app.exception.controllerException;
import com.sapient.app.exception.serviceException;
import com.sapient.app.model.Customer;
import com.sapient.app.model.JwtRequest;
import com.sapient.app.model.JwtResponse;
import com.sapient.app.model.User;
import com.sapient.app.security.JwtHelper;
import com.sapient.app.service.UserService;
import com.sapient.app.service.customerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
//    @Autowired
//    private UserService userService;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private customerService custService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
   
    @PostMapping("/signup")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer)
	{	try {
			Customer cust=custService.addCustomer(customer);
			System.out.println("inside controller 1");
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		catch(serviceException e)
		{
			System.out.println("inside controller 2");
			controllerException ce=new controllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<>(ce,HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			System.out.println("inside controller 3");
			controllerException ce=new controllerException("603","Something went wrong in controller");
			return new ResponseEntity<>(ce,HttpStatus.NOT_FOUND);
		}
	}

}