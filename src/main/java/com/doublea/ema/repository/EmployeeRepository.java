package com.doublea.ema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doublea.ema.model.Employee;

public interface EmployeeRepository extends JpaRepository <Employee, Long>{

}
