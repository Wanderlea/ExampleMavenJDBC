//Wanderleá lodi
//20/06/2017

/* Criar sequence no postgre 
   create SEQUENCE usersequence
   increment 1
   minvalue 1
   maxvalue 9223372036875807
   start 1;   
   alter table userjavajdbc ALTER column id set default nextval('usersequence'::regclass);
   
   chave unica 
   alter table userjavajdbc add unique (id);
   
   criar tabela e chave estrangeira
   create table telephoneuser 
	(
		id bigint not null,
		telephonenumber character varying (255) not null,
		type character varying (255) not null,
		userpeople bigint not null,
	
		constraint telephone_id primary key (id)
	
	);
	
	alter table telephoneuser add foreign key (userpeople) references userjavajdbc(id);
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BeanUserPhone;
import model.Telephone;
import model.UserJavaJdbc;
import connectionjdbc.SingleConnection;

public class UserJavaJdbcDAO {
	
	private Connection  connection;
	
	public UserJavaJdbcDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void save(UserJavaJdbc userJavaJdbc){
		try{
		String sql = "insert into userjavajdbc (nome, email) values (?,?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		insert.setString(1, userJavaJdbc.getNome());
		insert.setString(2, userJavaJdbc.getEmail());
		insert.execute();
		connection.commit();		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void saveTelephone(Telephone telephone){
		try {
			
			String sql = "INSERT INTO public.telephoneuser(telephonenumber, type, userpeople) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telephone.getTelephonenumber());
			statement.setString(2, telephone.getType());
			statement.setLong(3, telephone.getUserpeople());
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<UserJavaJdbc> list() throws Exception{
		List<UserJavaJdbc> list = new ArrayList<UserJavaJdbc>();
		
		String sql = "select * from userjavajdbc";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()){
			UserJavaJdbc userJavaJdbc = new UserJavaJdbc();
			userJavaJdbc.setId(result.getLong("id"));
			userJavaJdbc.setNome(result.getString("nome"));
			userJavaJdbc.setEmail(result.getString("email"));
			list.add(userJavaJdbc);
		}
		
		return list;
	}
	
	public UserJavaJdbc search(Long id) throws Exception{
		UserJavaJdbc returnSearch = new UserJavaJdbc();
		
		String sql = "select * from userjavajdbc where id = "+id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()){// Retorna um ou nenhum
			returnSearch.setId(result.getLong("id"));
			returnSearch.setNome(result.getString("nome"));
			returnSearch.setEmail(result.getString("email"));
		}
		
		return returnSearch;
	}
	
	public void update(UserJavaJdbc userJavaJdbc){
		
		try{
		
		String sql = "update userjavajdbc set nome = ? where id = "+ userJavaJdbc.getId();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userJavaJdbc.getNome());
		statement.execute();
		connection.commit();
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletar(Long id){
		
		try {
			
			String sql = "delete from userjavajdbc where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<BeanUserPhone> listUserPhone(Long idUser){
		
		List<BeanUserPhone> beanUserPhones = new ArrayList<BeanUserPhone>();
		
		String sql = " select email, telephonenumber, nome  from telephoneuser as phone "+
					 " inner join userjavajdbc as userj on (phone.userpeople = userj.id) "+
					 " where userpeople = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				BeanUserPhone userPhones = new BeanUserPhone();
				userPhones.setEmail(resultSet.getString("email"));
				userPhones.setNumber(resultSet.getString("telephonenumber"));
				userPhones.setNome(resultSet.getString("nome"));
				beanUserPhones.add(userPhones);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return beanUserPhones;		
	}
	
	public void deletePhonesUser(Long idUser){
		
		String sqlPhone = "delete from telephoneuser where userpeople = " + idUser;
		String sqlUser = "delete from userjavajdbc where id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlPhone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}
