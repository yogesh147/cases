package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private String email;
}