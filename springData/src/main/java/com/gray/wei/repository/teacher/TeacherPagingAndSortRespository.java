package com.gray.wei.repository.teacher;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gray.wei.bean.Teacher;

/**
 * 
 * 通过继承PagingAndSortRespository接口来快速进行分页开发
 */
public interface TeacherPagingAndSortRespository extends PagingAndSortingRepository<Teacher, Integer> {

}
