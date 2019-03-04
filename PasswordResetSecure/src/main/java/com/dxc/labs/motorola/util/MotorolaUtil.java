package com.dxc.labs.motorola.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dxc.labs.motorola.exception.UserNotFoundException;
import com.dxc.labs.motorola.model.user.Profile;
import com.dxc.labs.motorola.model.user.SecretQuestion;
@Component
public class MotorolaUtil {
	
	public Profile getUserDetails(Profile profile){
		Profile pf = new Profile();
		
		pf.setUserId(profile.getUserId());
		pf.setFirstName(profile.getFirstName());
		pf.setLastName(profile.getLastName());
		pf.setPhoneNumber(profile.getPhoneNumber());
		pf.setEmail(profile.getEmail());
		
		List<SecretQuestion> list = new ArrayList<SecretQuestion>();
		for(SecretQuestion q : profile.getSecretQuestion()){
			SecretQuestion secretQuestion = new SecretQuestion();
			secretQuestion.setCount(q.getCount());
			secretQuestion.setQuestion(q.getQuestion());
			
			list.add(secretQuestion);
			
			pf.setSecretQuestion(list);
		}
		return pf;
	}

	public void vaidate(Profile profile) throws UserNotFoundException{
		
		if(profile == null){
			throw new UserNotFoundException(Constent.ERR_CD_102,Constent.ERR_TX_102);
		}
		if(profile.getUserId() == null){
			throw new UserNotFoundException(Constent.ERR_CD_103,Constent.ERR_TX_103);
		}
		if(profile.getSecretQuestion() == null || profile.getSecretQuestion().isEmpty()){
			throw new UserNotFoundException(Constent.ERR_CD_104,Constent.ERR_TX_104);
		}
		for( SecretQuestion qns : profile.getSecretQuestion()){
	
			if(qns.getCount() == null || qns.getCount().trim().length() <= 0){
				throw new UserNotFoundException(Constent.ERR_CD_105,Constent.ERR_TX_105);
			}
			if(qns.getCount() != null){
				try{
					Integer.parseInt(qns.getCount());
				}catch(NumberFormatException e){
					throw new UserNotFoundException(Constent.ERR_CD_106,Constent.ERR_TX_106);
				}
				if(Integer.parseInt(qns.getCount()) <= 0){
					throw new UserNotFoundException(Constent.ERR_CD_105,Constent.ERR_TX_105);
				}
			}
			if(qns.getAnswer() == null || qns.getAnswer().trim().length() ==0 ){
				throw new UserNotFoundException(Constent.ERR_CD_104,Constent.ERR_TX_104);
			}
		}
		
	}

	
}
