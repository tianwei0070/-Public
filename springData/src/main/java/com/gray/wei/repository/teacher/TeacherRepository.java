package com.gray.wei.repository.teacher;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.gray.wei.bean.Teacher;

/**
 * 基于Spring Data接口的dao层开发接口 @ Modified By：
 */
// 有两种方式，要么用注解要么用继承
// @RepositoryDefinition(domainClass = Teacher.class ,idClass = Integer.class)
public interface TeacherRepository extends Repository<Teacher, Integer> {
	// ===============使用springdata默认方式=============================
	/**
	 * 根据名字查询老师
	 * 
	 * @param name
	 * @return
	 */
	Teacher findByName(String name);

	/**
	 * 根据班级名称进行查询老师（这里用到模糊匹配like）
	 * 
	 * @param classNumber
	 * @return
	 */
	List<Teacher> findByclassNumberLike(String classNumber);

	// ==============使用query注解开发==============================================
	/**
	 * 通过query注解进行开发模糊匹配（利用索引参数的方法）
	 * 
	 * @param classNumber
	 * @return
	 */
	@Query("select t from Teacher t where t.classNumber like %?1%")
	List<Teacher> queryTeacher(String classNumber);

	/**
	 * 通过老师的名字来进行查询数据
	 * 
	 * @param name
	 * @return
	 */
	@Query("select t from Teacher t where t.name = ?1")
	Teacher queryTeacherByName(String name);

	/**
	 * 通过老师的名字来进行查询数据（利用命名参数的方法，注意query注解的写法不一样的）
	 * 
	 * @param name
	 * @return
	 */
	@Query("select t from Teacher t where t.name = :name")
	Teacher queryTeacherByName2(@Param("name") String name);

	/**
	 * 使用原生的SQL语句进行操作（注意from这时候用的就是数据库的表名，而不是实体类名） 必须添加nativeQuery =
	 * true，因为默认是false的
	 * 
	 * @return
	 */
	@Query(nativeQuery = true, value = "select count(1) from teacher")
	long countTeacherNumber();

	// ================进行springdata的更新删除的处理======================

	/**
	 * 根据老师表的id进行修改对应数据的老师名字 必须要添加@Modifying注解，并且要在调用的方法上添加事务注解@Transactional
	 * 
	 * @param name
	 * @param id
	 */
	@Modifying
	@Query("update Teacher  t set t.name = ?1 where t.id = ?2")
	void updateTeacherById(String name, Integer id);

}
