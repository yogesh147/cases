package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentService studentService;

//creating post mapping that post the student detail in the database
	@PostMapping("/student")
	public ResponseEntity<?> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentService.saveOrUpdate(student), HttpStatus.OK);
	}

//creating a get mapping that retrieves all the students detail from the database 
	@GetMapping("/student")
	private List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

//creating a get mapping that retrieves the detail of a specific student
	@GetMapping("/student/{id}")
	private Student getStudent(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}
	
	@PutMapping("/student")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.saveOrUpdate(student);
	}

//creating a delete mapping that deletes a specific student
	@DeleteMapping("/student/{id}")
	private String deleteStudent(@PathVariable("id") int id) {
		studentService.delete(id);
		return "delete sucessfully!!";
	}

}
