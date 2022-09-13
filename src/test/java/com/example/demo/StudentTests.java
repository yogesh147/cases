package com.example.demo;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.StudentController;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.utility.AppUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ CaseApplication.class })
@AutoConfigureMockMvc
public class StudentTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentController studentController;
	
	@MockBean
	private StudentService studentService;
	
	ObjectMapper objMapper = new ObjectMapper();
	ObjectWriter objWritter = objMapper.writer();

	@Test
	public void saveStudent() throws Exception {

		System.out.println("test case start for saving student ::");
		
		Random rand = new Random();
		int id = rand.nextInt(1000);

		Student record = Student.builder()
				.id(id)
				.name("JohnDoe")
				.age(id)
				.email("john@gmail.com")
				.build();
		
	    Mockito.when(studentRepository.save(record)).thenReturn(record);

		String content = objWritter.writeValueAsString(record);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				    .post("/api/student")
				    .characterEncoding("UTF-8")
		            .contentType(MediaType.APPLICATION_JSON_VALUE)
		            .accept(MediaType.APPLICATION_JSON_VALUE)
		            .content(content);
	
		 mockMvc.perform(mockRequest)
			        .andExpect(status().isOk())
					.andExpect(jsonPath("$", notNullValue()))
//			        .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("JohnDoe"))
					.andExpect(jsonPath("$.name", is("JohnDoe")));
		 
		 
		  
		System.out.println("test case end for saving student ::");
	}

//	@Test
	public void testReverseWord() {
		System.out.println("test case reverse word");
		assertEquals("ym eman si ss", AppUtil.reverseWord("my name is ss"));
	}

	
}
