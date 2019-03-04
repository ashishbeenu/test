package com.dxc.labs.motorola.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.dxc.labs.motorola.dao.PWSRepository;
import com.dxc.labs.motorola.exception.UserNotFoundException;
import com.dxc.labs.motorola.model.user.Profile;
import com.dxc.labs.motorola.model.user.SecretQuestion;
import com.dxc.labs.motorola.util.Constent;

@Service
public class PWSResetService {


	@Autowired
	protected MongoOperations mongoOps;

	@Autowired
	PWSRepository pwsRepository;


	private static final Logger LOGGER = LoggerFactory.getLogger(PWSResetService.class);

//	public Profile getUserProfileById(String userId) {
//		LOGGER.info("Start");
//		LOGGER.info("userId - {}" , userId);
//		Criteria criteria = new Criteria();
//		criteria = Criteria.where("firstName").regex(Pattern.compile(userId, Pattern.CASE_INSENSITIVE));
//
//		Query query = new Query();
//		query.addCriteria(criteria);
//		List<Profile> list = mongoOps.find(query,Profile.class);
//		LOGGER.info("getUserProfileById: {}" , list);
//
//		if(list !=null && !list.isEmpty()){
//			LOGGER.info("userId list size: {}" , list.size());
//			LOGGER.info("End");
//			return list.get(0);
//		}
//
//		LOGGER.info("End");
//		return null;
//	}
	
	public Profile getUserProfileById(String userId) throws UserNotFoundException{
		LOGGER.info("Start");
		LOGGER.info("userId - {}" , userId);
		
		Profile profile = pwsRepository.findByUserId(userId);
		if(profile == null){
			LOGGER.info("End");
			LOGGER.error("User id not found...");
			throw new UserNotFoundException(Constent.ERR_CD_101,Constent.ERR_TX_101);
		}
		LOGGER.info("End");
		return profile;
	}

	public boolean validateSecurityAnswer(Profile profile) throws UserNotFoundException{
		List<SecretQuestion> userList = profile.getSecretQuestion();
		
		Profile dbProfile = getUserProfileById(profile.getUserId());
//		Compare all security question with DB
		List<SecretQuestion> list = dbProfile.getSecretQuestion();
		Map<String,String> map = new HashMap<String,String>();
		for(SecretQuestion q : list){
			map.put(q.getCount(), q.getAnswer());
		}
		int count = 0 ;
		for(SecretQuestion userQ : userList){
			String key = userQ.getCount();
			String ans = map.get(key);
			if(userQ.getAnswer().equalsIgnoreCase(ans)){
				count++;
			}
		}
		if(count == 3){
			return true;
		}
		return false;
	}
	
//	public boolean validateSecurityAnswer(Profile profile) {
//		Criteria c1 = new Criteria();
//		c1 = Criteria.where("userId").is(profile.getUserId());
//		
//		Criteria c2 = new Criteria();
//		c2 = Criteria.where("secretQuestion").elemMatch(Criteria.where("answer").
//				regex(Pattern.compile("beenu", Pattern.CASE_INSENSITIVE)));
//		
//		Query query = new Query();
//		query.addCriteria(c1).addCriteria(c2);
//		Profile pf = mongoOps.findOne(query,Profile.class);
//		System.out.println("---->" + pf);
//		return false;
//	}
}
