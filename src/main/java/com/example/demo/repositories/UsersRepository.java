package com.example.demo.repositories;



import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>
{
	

	
	public void save(User user);
	public Users findByEmail(String email);

	

	

}
