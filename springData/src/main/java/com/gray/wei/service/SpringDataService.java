package com.gray.wei.service;

import java.util.List;
import com.gray.wei.bean.Teacher;

/**
 * 用于示例演示 编写springdata相关的service层代码 @ Modified By：
 */

public interface SpringDataService {

	public void updateTeacher(String name, Integer id);

	public void addTeacherByCrudResposity(List<Teacher> teacherList);

	public List<Teacher> getTeacher();
}
