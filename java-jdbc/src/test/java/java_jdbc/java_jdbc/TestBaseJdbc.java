//Wanderleá lodi
//20/06/2017

package java_jdbc.java_jdbc;

import java.util.List;

import model.BeanUserPhone;
import model.Telephone;
import model.UserJavaJdbc;

import org.junit.Test;

import dao.UserJavaJdbcDAO;

public class TestBaseJdbc{
	
	@Test
	public void initBase(){
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		UserJavaJdbc userJavaJdbc = new UserJavaJdbc();
		
		//Id está usando a sequencia criada no banco
		userJavaJdbc.setNome("Paula");
		userJavaJdbc.setEmail("paulaTest@gmail.com");
		
		dao.save(userJavaJdbc);
	}
	
	@Test
	public void initList(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		
		try {
			List<UserJavaJdbc> list = dao.list();
			
			for (UserJavaJdbc userJavaJdbc : list) {
				//Neste caso utiliza o toString sobrescrito na classe UserJavaJdbc
				System.out.println(userJavaJdbc);
				System.out.println("--------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initsearch(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		
		try {
			UserJavaJdbc userJavaJdbc = dao.search(5L);
			System.out.println(userJavaJdbc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initUpdade(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		
		try{
			
			UserJavaJdbc objectBD = dao.search(5L);
			objectBD.setNome("Nome alterado");
			dao.update(objectBD);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDelete(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		
		try {
			
			dao.deletar(6L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initInsertTelephone(){
		
		Telephone telephone = new Telephone();
		telephone.setTelephonenumber("41 997874-7489");
		telephone.setType("Casa");
		telephone.setUserpeople(10L);
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		dao.saveTelephone(telephone);
		
	}
	
	@Test
	public void initLoadPhoneUser(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		List<BeanUserPhone> benaUserPhone = dao.listUserPhone(4L);
		
		for (BeanUserPhone beanUserPhone : benaUserPhone) {
			
			System.out.println(beanUserPhone);
			System.out.println("-------------------------------");
			
		}
	}
	
	@Test
	public void initDeleteUserPhone(){
		
		UserJavaJdbcDAO dao = new UserJavaJdbcDAO();
		dao.deletePhonesUser(4L);
		
	}

}
