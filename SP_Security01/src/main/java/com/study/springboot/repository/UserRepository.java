package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.model.User;

// CRUD 함수를 JpaRepository가 들고 있음
// @Repository라는 어노테이션이 없어도 IoC가됨. 이유는 JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer>{
    // select * from user where username=?
	public User findByUsername(String username);
}
