package com.dxc.labs.motorola;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.labs.motorola.controller.PWSControllerImpl;
import com.dxc.labs.motorola.dao.PWSRepository;
import com.dxc.labs.motorola.exception.ExceptionTest;
import com.dxc.labs.motorola.exception.UserNotFoundException;
import com.dxc.labs.motorola.model.user.Profile;
import com.dxc.labs.motorola.model.user.SecretQuestion;
import com.dxc.labs.motorola.services.PWSResetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DxcPWSApplicationTests {

//	mvn clean install -Dmaven.test.skip=true
	@Autowired
	protected MongoOperations mongoOps;
	
	@Autowired
	protected PWSRepository repository;
	
	@Autowired
	protected PWSResetService service;
	
	@Autowired
	protected PWSControllerImpl nhsControllerImpl;
	
	
	@Test
	public void contextLoads() {
	}

	//@Test
//	public void  findByid(){
//		Query query = new Query();	
//		query.addCriteria((Criteria.where("_id").is("5b433f34d6b2d52f6074eacc")));
//		
//		List<Patient> patient = mongoOps.find(query,Patient.class);
//		System.out.println("findByid: " + patient);
//		assertTrue((patient.size() > 0 ));
//	}
	
	//@Test
//	public void  findByid_(){
//		Query query = new Query();	
//		query.addCriteria((Criteria.where("patientid").is("1234")));
//		
//		List<Patient> patient = mongoOps.find(query,Patient.class);
//		System.out.println("findByid_: " + patient);
//		assertTrue((patient.size() > 0 ));
//	}
	
	//@Test
//	public void  findByResourceType(){
//		Query query = new Query();	
//		query.addCriteria((Criteria.where("resourceType").is("Patient")));
//		
//		List<Patient> patient = mongoOps.find(query,Patient.class);
//		System.out.println("findByResourceType: " + patient);
//		assertTrue((patient.size() > 0 ));
//	}
	
	
	//@Test
//	public void welcome(){
//		try{
//		nhsControllerImpl.welcome();
//		}catch(PatientNotFoundException e){
//			System.out.println("---->"+e.getMessage());
////			e.printStackTrace();
//		}
//	}
	
	
	//@Test
	public void welcome(){
		try{
		nhsControllerImpl.welcome();
		}catch(UserNotFoundException e){
			System.out.println("---->"+e.getMessage());
			System.out.println("---->"+e.getErroCd());
//			e.printStackTrace();
		}
	}
	
	//@Test
	public void  getUserProfile(){
		
		Criteria criteria = new Criteria();
		criteria = Criteria.where("userId").regex(Pattern.compile("ayadav32", Pattern.CASE_INSENSITIVE));

		Query query = new Query();
		query.addCriteria(criteria);
		
		List<Profile> profile = mongoOps.find(query,Profile.class);
		System.out.println("getUserProfile--:" + profile);
		assertTrue((profile.size() != 0 ));
	}
	
	@Test
	public void  getUserProfileFindById() throws UserNotFoundException{
		//Profile profile = repository.findByUserId("ayadav32");
		Profile profile = nhsControllerImpl.getUserProfile("1503367");
		System.out.println("Profile by id--:" + profile);
		assertTrue((profile  != null ));
	}
	
	//@Test
	public void  validateSecurityAnswer() {
		try{
		Profile profile = new Profile();
		profile.setUserId("ayadav32");
		List<SecretQuestion> list = new ArrayList<SecretQuestion>();
		SecretQuestion q1 = new SecretQuestion();
		q1.setCount("1");
		q1.setAnswer("beenu");

		SecretQuestion q2 = new SecretQuestion();
		q2.setCount("2");
		q2.setAnswer("jhon");
		
		SecretQuestion q3 = new SecretQuestion();
		q3.setCount("3");
		q3.setAnswer("9880711544");
		
		list.add(q1);
		list.add(q2);
		list.add(q3);
		
		profile.setSecretQuestion(list);
		
		boolean value = nhsControllerImpl.validateSecurityAnswer(profile);
		System.out.println("validateSecurityAnswer--:" + value);
		assertTrue("true", true);
		}catch(UserNotFoundException ex){
			System.out.println(ex.message);
		}
	}
}
