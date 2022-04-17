package com.vaserson.app.webs.ui.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaserson.app.webs.ui.model.request.UpdateUserDetailsRequestModel;
import com.vaserson.app.webs.ui.model.request.UserDetailsRequestModel;
import com.vaserson.app.webs.ui.model.response.UserResp;
import com.vaserson.app.webs.userservice.UserService;

@RestController
@RequestMapping("/users") // https://localhost:8080/users
public class UserController{
	
	Map<String, UserResp> users;
	
	@Autowired
	UserService userService;
	
    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
    		@RequestParam(value="limit", defaultValue="50") int limit,
    		@RequestParam(value="sort", defaultValue = "desc", required=false) String sort)
			{
        return "get user was called with page = " + page + " and limit = " + limit + " parameters and sort = " + sort;
    }
    
    @GetMapping(path="/{userId}", produces = {
    									MediaType.APPLICATION_XML_VALUE, 
    									MediaType.APPLICATION_JSON_VALUE
    									})
    public ResponseEntity<UserResp> getUser(@PathVariable String userId)
    {
    	//if(true) throw new UserServiceException("A user service exception is thrown");
    	
    	if(users.containsKey(userId))
    	{
    		return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
    	} else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} 
    }
    
    @PostMapping(
    				consumes = {
    						MediaType.APPLICATION_XML_VALUE, 
    						MediaType.APPLICATION_JSON_VALUE
							}, 
							produces = {
							MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_VALUE
							})
	public ResponseEntity<UserResp> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) 
	{
		/* Перенесено в UserServiceImpl.java
		 * 		
		
    	UserResp returnValue = new UserResp();
    	returnValue.setEmail(userDetails.getEmail());
    	returnValue.setFirstName(userDetails.getFirstName());
    	returnValue.setLastName(userDetails.getLastName());

    	String userId = UUID.randomUUID().toString();
    	returnValue.setUserId(userId);
    	
    	if(users == null) users = new HashMap<>();
    	users.put(userId, returnValue);
    	*/
    	UserResp returnValue = userService.createUser(userDetails);
    	
    	return new ResponseEntity<UserResp>(returnValue, HttpStatus.OK);
	}
    
    @PutMapping(path="/{userId}", consumes = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
			}, 
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					} )
    public UserResp updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
        UserResp storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());
        
        users.put(userId, storedUserDetails);
        
        return storedUserDetails;
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id)
    {
        users.remove(id);
		
        return ResponseEntity.noContent().build();
    }
}
