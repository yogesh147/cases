package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Retrieve Student by Id")
    public void getStudentById() {
 
        Student record = Student.builder()
				.id(123123)
				.name("JohnDoe")
				.age(38)
				.email("john@gmail.com")
				.build();
 
        Mockito.when(studentRepository.findById(123123)).thenReturn(Optional.of(record));
        
        Student actualResponse = studentService.getStudentById(123123);
		assertEquals(actualResponse.getId(), record.getId());
		assertEquals(actualResponse.getName(), record.getName());
    }
	
	@Test
    @DisplayName("Delete Student by Id")
	public void deleteStudentById() {

		Student record = Student.builder().id(123123).name("JohnDoe").age(38).email("john@gmail.com").build();
		Mockito.when(studentRepository.findById(123123)).thenReturn(Optional.of(record));
		Student actualResponse = studentService.getStudentById(123123);
		System.out.println("cutting test cases" + actualResponse.getId());
		assertEquals(actualResponse.getId(), record.getId());
		
		Mockito.doNothing().when(studentRepository).deleteById(actualResponse.getId());
		String onDeleteResponse = studentService.delete(actualResponse.getId());
		assertEquals("delete successfully!!", onDeleteResponse);
	}

	@Test
	public void testReverseWord() {
		System.out.println("test case reverse word");
		assertEquals("ym eman si ss", AppUtil.reverseWord("my name is ss"));
	}

}
