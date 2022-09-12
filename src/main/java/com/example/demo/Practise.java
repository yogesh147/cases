package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.model.Student;

public class Practise {

	public static void main(String[] args) {

		List<Student> list = Stream.of(new Student(1, "ABC", 10, "a"), new Student(2, "CDE", 80, "c"),
				new Student(3, "FGH", 50, "j"), new Student(4, "IJK", 60, "b")).collect(Collectors.toList());

		System.out.println(list.stream().sorted((s1, s2) -> s1.getAge() - s2.getAge())
				.collect(Collectors.toList()));
		
	}
}
