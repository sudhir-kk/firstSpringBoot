package com.in28minutes.springboot.filter;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.*;

@RestController
public class FilterController {

	@GetMapping("/filtering")
	public MappingJacksonValue getCustomer() {
		Student student=new Student("student1","student2","student3");
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("someStudentFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(student);
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping("/filtering-list")
	public MappingJacksonValue getCustomerList() {
		List<Student> list=Arrays.asList(new Student("student1","student2","student2"),new Student("student11","student22","student33"));
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("someStudentFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}
}
