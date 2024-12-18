package com.emp.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

//	static final String QUERY_TO_GET_EMPLOYEE_DETAILS_BY_FRISTNAME_AND_LASTNAME="SELECT * FROM employee"
//			+"WHERE fristName =:fristName AND lastName =:lastName";

	@Query(value = "SELECT * FROM employee WHERE frist_Name=:frist_Name AND last_Name=:last_Name", nativeQuery = true)
	EmployeeEntity findByFristnameAndLastname(@Param("frist_Name") String frist_Name,
			@Param("last_Name") String last_Name);

	@Query(value = " SELECT * FROM employee ud WHERE ud.email = :email ", nativeQuery = true)
	EmployeeEntity findByEmail(@Param(value = "email") String email);
	
	
	
//	@Query(value = "SELECT *  FROM employee WHERE LOWER(email)= LOWER(:email)", nativeQuery = true)
//	EmployeeEntity findByUserName(@Param(value = "email") String email);

	Optional<EmployeeEntity> findByEmailAndPassword(String email, String password);

	@Transactional
	@Query(value = " DELETE * FROM employee ud WHERE ud.email = :email ", nativeQuery = true)
	EmployeeEntity deleteByEmailId(@Param(value = "email") String email);
}
