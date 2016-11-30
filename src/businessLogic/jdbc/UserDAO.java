package businessLogic.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessLogic.javaClass.Room;
import businessLogic.javaClass.User;
import businessLogic.library.EmailApi;
public class UserDAO {
	

   public User findExistingUserById(int n) throws SQLException
   {
      User user = new User();
      MysqlOperation o = new MysqlOperation();
      Connection connection = o.DBConnect();
      String query = "SELECT * FROM user WHERE user_id = "+n;
      ResultSet rs = o.searchDB(connection, query);
      while(rs.next()){
         user.setUserId(rs.getInt(1));
         user.setUsername(rs.getString(2));
      }
      return user;
   }
   
public int findUser(String user,String password) {
		int result = 0; //0 =  success;
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Md5Encryption md5 = new Md5Encryption();
//		password = md5.MD5(password);
		String query = "select * from user where "+ "username = '" + user + "'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		String pass = null;
		try {
			if(!rs.next()) {
				result = -1; // 1 = "error", "No such user"
				System.out.println("No such user!");
			}
			else {
				pass = rs.getString("password");
				if (pass.equals(md5.MD5(password))) {
					result = rs.getInt("user_id"); //return user ID;
//					System.out.println("login successful!");
				} else {
					result = -2; //2 = "error", "Wrong pass word"
					System.out.println("wrong password!");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


      o.closeDB(connection);
      return result;
   }
   
   /**
    * @return hotel ID, or -1 if invalid
    */
   public int findManager(String user,String password) {
      int result = -1; //-1 =  fail;
      MysqlOperation o = new MysqlOperation();
      Connection connection = o.DBConnect();
      Md5Encryption md5 = new Md5Encryption();
//    password = md5.MD5(password);
      String query = "select * from manager where "+ "username = '" + user + "'";
      System.out.println(query);
      ResultSet rs = o.searchDB(connection, query);
      String pass = null;
      try {
         if(!rs.next()) {
            result = -1;
            System.out.println("No such user!");
         }
         else {
            pass = rs.getString("password");
            if (pass.equals(md5.MD5(password))) {
               result = rs.getInt("hotel_id"); //return hotel ID;
               System.out.println("login successful!");
            } else {
               result = -1;
               System.out.println("wrong password!");
            }
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      o.closeDB(connection);
      return result;
   }
   
   /**
    * @return true if valid owner, or false if invalid
    */
   public boolean findOwner(String user, String password) {
      // TODO if we store the owner in the database, implement it here
      //      int result = -1; //-1 =  fail;
      //      MysqlOperation o = new MysqlOperation();
      //      Connection connection = o.DBConnect();
      //      Md5Encryption md5 = new Md5Encryption();
      ////    password = md5.MD5(password);
      //      String query = "select * from manager where "+ "username = '" + user + "'";
      //      System.out.println(query);
      //      ResultSet rs = o.searchDB(connection, query);
      //      String pass = null;
      //      try {
      //         if(!rs.next()) {
      //            result = -1;
      //            System.out.println("No such user!");
      //         }
      //         else {
      //            pass = rs.getString("password");
      //            if (pass.equals(md5.MD5(password))) {
      //               result = rs.getInt("hotel_id"); //return hotel ID;
      //               System.out.println("login successful!");
      //            } else {
      //               result = -1;
      //               System.out.println("wrong password!");
      //            }
      //         }
      //         
      //      } catch (SQLException e) {
      //         // TODO Auto-generated catch block
      //         e.printStackTrace();
      //      }
      //
      //      o.closeDB(connection);
      //      return result;
      return ("Owner".equals(user) && "secure".equals(password));
   }
   
	//call this only if pass the above one
	//wait... I don't need it
	public String findOneUser(String user,String password) {
		User this_user= new User();
		Md5Encryption md5 = new Md5Encryption();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from user where "+ "username = '" + user + "'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		String pass = null;
		try {
			if(!rs.next()) {
			}
			else {
				pass = rs.getString("password");
				if (pass.equals(md5.MD5(password))) {
					
					this_user.setUserId(rs.getInt("user_id"));
					this_user.setEmail(rs.getString("email"));
					this_user.setUsername(rs.getString("Username"));
					
					
					
				} else {
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		o.closeDB(connection);
		return this_user.getUsername();
	}
	
	public String getEmail(int id) {
		User this_user= new User();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from user where "+ "user_id = '" + id + "'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		String pass = null;
		try {
			if(!rs.next()) {
			}
			else {
					this_user.setUserId(rs.getInt("user_id"));
					this_user.setEmail(rs.getString("email"));
					this_user.setUsername(rs.getString("Username"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		o.closeDB(connection);
		return this_user.getEmail();
	}
	
	public void addUser(String username, String password, String email) 
	{
		MysqlOperation o = new MysqlOperation();
		Md5Encryption md5 = new Md5Encryption();
		PreparedStatement pst = null;
		int userId = 0;
		try {
			Connection connection = o.DBConnect();
			String sqlInsert = "INSERT INTO user (username, password, email) VALUES (?,?,?)";
			pst = connection.prepareStatement(sqlInsert);
			pst.setString(1, username);
			pst.setString(2, md5.MD5(password));
			pst.setString(3, email);
			
			pst.executeUpdate();
			pst.close();
			String query = "select user_id from user where "+ "username = '" + username + "'";
			ResultSet rs = o.searchDB(connection, query);
			rs.next();
			userId = rs.getInt("user_id");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EmailApi emailApi = new EmailApi();
		emailApi.verifyEmail(userId, username, email);
		
		
	}
	
	public int checkDuplicate(String username,String email){
		int result = 1;	
		try {
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			
			String query = "select COUNT(*) AS rowcount from user where username = '" + username + "'";
			System.out.println(query);
			ResultSet rs1 = o.searchDB(connection, query);
			rs1.next();
			int count1 = rs1.getInt("rowcount");
			
			query = "select COUNT(*) AS rowcount from user where email = '" + email + "'";
			System.out.println(query);
			ResultSet rs2 = o.searchDB(connection, query);
			rs2.next();
			int count2 = rs2.getInt("rowcount");
			
			if (count1>0) return 2;
			if (count2>0) return 3;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
	public int verifyEmail(int userId)  {
		MysqlOperation o = new MysqlOperation();
		try {
			Connection connection = o.DBConnect();
			String query = "select * from user where user_id = " + String.valueOf(userId);
			System.out.println(query);
			ResultSet rs = o.searchDB(connection, query);
			if (rs.next() == false) {
				connection.close();
				return 0;
			} 
			int verify = rs.getInt("email_verification");
			if (verify == 1) {
				connection.close();
				return 1;
			}
			String sqlInsert = "update user set email_verification = ? where user_id = ?";
			PreparedStatement pst = connection.prepareStatement(sqlInsert);
			pst.setInt(1, 1);
			pst.setInt(2, userId);
			pst.executeUpdate();
			pst.close();
			System.out.println("Update successful!");
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return 2;
	}
	
	public int updateUser(User user) 
	{
		MysqlOperation o = new MysqlOperation();
		PreparedStatement pst = null;
		int result = 0;
		try {
			Connection connection = o.DBConnect();
			String query = "select * from user where user_id = " + user.getUserId();
			ResultSet rs = o.searchDB(connection, query);
			rs.next();
			String oldEmail = rs.getString("email");
			System.out.println(oldEmail + "=="+user.getEmail());
			int email_verification = 1;
			if (oldEmail.equals(user.getEmail())) {
				email_verification = 1;
				result = 1;
			} else {
				result = 0;
				email_verification = 0;
			}
			
			System.out.println("email check = "+email_verification);
			String sqlInsert = "update user SET email = ?, nickname = ?, firstname = ?, lastname = ?, "
					+ "address = ?, credit_card_type = ?, credit_card_number = ?, credit_card_exp_month = ?, "
					+ "credit_card_exp_year = ?, credit_card_cvv = ?, email_verification = ?"
					+ " where user_id = ?";
			pst = connection.prepareStatement(sqlInsert);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getNickname());
			pst.setString(3, user.getFirstname());
			pst.setString(4, user.getLastname());
			pst.setString(5, user.getAddress());
			pst.setString(6, user.getCreditCardType());
			pst.setString(7, user.getCreditCardNum());
			pst.setString(8, user.getCreditCardExpMonth());
			pst.setString(9, user.getCreditCardExpYear());
			pst.setString(10, user.getCreditCardCvv());
			pst.setInt(11, email_verification);
			pst.setInt(12, user.getUserId());			
			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}
	public User findUpdate(int userId) {
		User user = new User(userId);
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from user where user_id = "+String.valueOf(userId);
		ResultSet rs = o.searchDB(connection, query);
		try {
			rs.next();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setNickname(rs.getString("nickname"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));
			user.setCreditCardType(rs.getString("credit_card_type"));
			user.setCreditCardNum(rs.getString("credit_card_number"));
			user.setCreditCardExpMonth(rs.getString("credit_card_exp_month"));
			user.setCreditCardExpYear(rs.getString("credit_card_exp_year"));
			user.setCreditCardCvv(rs.getString("credit_card_cvv"));
			user.setEmailVerification(rs.getInt("email_verification"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
