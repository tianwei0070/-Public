package com.gray.wei.junit;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gray.wei.bean.Teacher;
import com.gray.wei.repository.teacher.TeacherPagingAndSortRespository;
import com.gray.wei.repository.teacher.TeacherRepository;

/**
 * 单元测试工具类
 */
public class SpringDataTest {
	// 用于加载spring配置文件
	private ApplicationContext context = null;

	// 用于操作老师实体的接口
	@Autowired
	private TeacherRepository teacherRepository = null;
	// 用于操作老师实体分页
	@Autowired
	private TeacherPagingAndSortRespository teacherPagingAndSortRespository = null;

	@Before
	public void getContext() {
		context = new ClassPathXmlApplicationContext("spring-datacontext.xml");
		// 通过类名进行注入
		teacherRepository = context.getBean(TeacherRepository.class);
		teacherPagingAndSortRespository = context.getBean(TeacherPagingAndSortRespository.class);
	}

	/**
	 * 直接执行这个测试方法，然后就再去看一下数据库就会发生对应实体中的内容到数据库中了
	 */
	// @Test
	public void testCreateTableAuto() {

	}

	/**
	 * 测试springdata中的findByName方法(没有任何的实现，这就是springdata的强大)
	 */
	@Test
	public void testSpringDataFindName() {
		Teacher teacher = teacherRepository.findByName("哈哈");
		List<Teacher> teachers = teacherRepository.findByclassNumberLike("哈哈");
		System.out.println(teacher);
		System.out.println(teachers);
	}

	@Test
	public void teacherPagingAndSortRespository() {
		// 根据id 进行降序
		Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
		Sort sort = new Sort(order);

		Pageable page = new PageRequest(0, 10, sort);
		Page<Teacher> teacherList = teacherPagingAndSortRespository.findAll(page);
		System.out.println("查询总页数:" + teacherList.getTotalPages());
		System.out.println("查询总记录数:" + teacherList.getTotalElements());
		System.out.println("查询当前第几页:" + teacherList.getNumber() + 1);
		System.out.println("查询当前页面的集合:" + teacherList.getContent());
		System.out.println("查询当前页面的记录数:" + teacherList.getNumberOfElements());
		System.out.println("查询数据信息:" + teacherList.getContent().toString());
	}

	/**
	 * 测试使用springdata进行模糊匹配
	 */
	// @Test
	public void testSpringDataLike() {
		List<Teacher> teachers = teacherRepository.findByclassNumberLike("%班班%");
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}

	/**
	 * 测试使用springdata中的query注解进行开发模糊查询
	 */
	// @Test
	public void testQueryTeacher() {
		List<Teacher> teachers = teacherRepository.queryTeacher("班班");
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}

	/**
	 * 测试通过占位符进行操作查询
	 */
	// @Test
	public void testQueryTeacherByName() {
		Teacher teacher = teacherRepository.queryTeacherByName("哈哈");
		System.out.println(teacher);
	}

	/**
	 * 测试通过别名进行操作查询
	 */
	// @Test
	public void testQueryTeacherByName2() {
		Teacher teacher = teacherRepository.queryTeacherByName2("哈哈");
		System.out.println(teacher);
	}

	/**
	 * 测试使用原生的SQL语句进行开发
	 */
	// @Test
	public void testCountTeacherNumber() {
		long number = teacherRepository.countTeacherNumber();
		System.out.println("数据总条数为：" + number);
	}
}
