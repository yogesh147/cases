package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.controller.StudentController;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.utility.AppUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)
public class StudentTests {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentController studentController;

	private StudentService studentService;

	ObjectMapper objMapper = new ObjectMapper();
	ObjectWriter objWritter = objMapper.writer();

	@Captor
	private ArgumentCaptor<Student> captor;
	
	@BeforeEach
    public void setup() {
		studentService = new StudentService(studentRepository);
    }
	
	@Test
	public void saveStudent() throws Exception {

		System.out.println("test case start for saving students ::");

		Student student1 = new Student(1, "Harry", 12, "A");
		Student student2 = new Student(2, "Tae", 22, "B");
		Student student3 = new Student(3, "Louis", 32, "C");
		
		studentService.saveOrUpdate(student1);
		studentService.saveOrUpdate(student2);
		studentService.saveOrUpdate(student3);

		Mockito.verify(studentRepository, Mockito.times(3)).save(captor.capture());
		List<Student> studentList = captor.getAllValues();

		assertEquals("Harry", studentList.get(0).getName());
		assertEquals("Tae", studentList.get(1).getName());
		assertEquals("Louis", studentList.get(2).getName());
		
		System.out.println("test case end for saving students ::");
	}

	@Test
	public void testReverseWord() {
		System.out.println("test case reverse word");
		assertEquals("ym eman si ss", AppUtil.reverseWord("my name is ss"));
	}

}
