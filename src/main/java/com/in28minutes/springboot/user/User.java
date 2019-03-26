package com.in28minutes.springboot.user;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Complete details about USER")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Name should have atleast 2 characters")

	private String name;
	@Size
	@ApiModelProperty(notes = "Passport shuld not be issued in 2000")
	private String passportNumber;
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User() {
	}

	public User(String name, String passportNumber) {
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public User(Integer id, String name, String passportNumber) {
		this.id = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + passportNumber + "]";
	}

}
