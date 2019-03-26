package com.in28minutes.springboot.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import  static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserResource {
	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return daoService.findAll();
	}
	@GetMapping("/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User user= daoService.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("ID:"+id);
		}
		Resource<User> resource =new Resource<User>(user);
		
		resource.add(linkTo(methodOn(this.getClass()).retriveAllUsers()).withRel("all-user"));
		
		return resource;
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user= daoService.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("ID-"+id);
		}
	}
	@PostMapping("/users")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody User user) {
	User savedUser=	daoService.save(user);
	URI location=	ServletUriComponentsBuilder
	.fromCurrentRequest().path("/{id}")
	.buildAndExpand(savedUser.getId())
	.toUri();
	return ResponseEntity.created(location).build();
		
	}
}
