package com.gray.wei.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gray.wei.bean.Teacher;
import com.gray.wei.repository.teacher.TeacherCrudRespository;
import com.gray.wei.repository.teacher.TeacherPagingAndSortRespository;
import com.gray.wei.repository.teacher.TeacherRepository;
import com.gray.wei.service.SpringDataService;

/**
 * 用于示例演示 编写springdata相关的service层代码 @ Modified By：
 */
@Service("springDataService")
public class SpringDataServiceImpl implements SpringDataService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherCrudRespository teacherCrudRespository;

	@Autowired
	private TeacherPagingAndSortRespository teacherPagingAndSortRespository;

	/**
	 * 根据id进行修改老师的名字()
	 * 
	 * @param name
	 * @param id
	 */
	@Transactional
	public void updateTeacher(String name, Integer id) {
		teacherRepository.updateTeacherById(name, id);
	}

	// ==========下面的是通过CrudResposity接口进行开发=====================

	/**
	 * 添加list参数中的所有老师
	 * 
	 * @param teacherList
	 */
	@Transactional
	public void addTeacherByCrudResposity(List<Teacher> teacherList) {
		teacherCrudRespository.save(teacherList);
	}

	@Transactional
	public List<Teacher> getTeacher() {
		Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
		Sort sort = new Sort(order);
		Pageable page = new PageRequest(0, 10, sort);
		Page<Teacher> data = teacherPagingAndSortRespository.findAll(page);
		return data.getContent();
	}
}
