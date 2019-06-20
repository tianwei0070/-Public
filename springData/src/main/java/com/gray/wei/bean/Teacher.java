package com.gray.wei.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * 编写一个老师实体类 用于示例演示，对应表（Teacher）
 * 
 */
@Entity
public class Teacher {
	// 配置表的id，并且是使用自增
	@Id
	@GeneratedValue
	private Integer id;
	// 设置列的长度为15，并且不能为空
	@Column(length = 15, nullable = false)
	private String name;
	private String classNumber;

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

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

	@Override
	public String toString() {
		return "Teacher{" + "id=" + id + ", name='" + name + '\'' + ", classNumber='" + classNumber + '\'' + '}';
	}
}
