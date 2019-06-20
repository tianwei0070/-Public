package com.gray.wei.repository.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gray.wei.bean.Teacher;

/**
 * 测试继承JpaRepository接口的方法
 */
public interface TeacherJpaRepository extends JpaRepository<Teacher, Integer> {
}
