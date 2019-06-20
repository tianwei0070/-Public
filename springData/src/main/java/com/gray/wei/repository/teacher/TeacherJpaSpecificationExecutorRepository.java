package com.gray.wei.repository.teacher;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gray.wei.bean.Teacher;

/**
 * 继承JpaSpecificationExecutorRepository接口
 */
public interface TeacherJpaSpecificationExecutorRepository
		extends PagingAndSortingRepository<Teacher, Integer>, JpaSpecificationExecutor<Teacher> {
}
