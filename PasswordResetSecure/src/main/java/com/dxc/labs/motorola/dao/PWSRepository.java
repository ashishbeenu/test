package com.dxc.labs.motorola.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.dxc.labs.motorola.model.user.Profile;

public interface PWSRepository extends MongoRepository<Profile,String>{

	@Query("{ 'userId' : ?0 }")
	public Profile findByUserId(String userId);
	
	
}
