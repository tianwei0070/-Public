package com.gray.wei.repository.teacher;

import org.springframework.data.repository.CrudRepository;

import com.gray.wei.bean.Teacher;

/**
 * 通过继承CrudRespository接口(因为可以快速进行crud相关的方法开发)
 */
public interface TeacherCrudRespository extends CrudRepository<Teacher, Integer> {

}
