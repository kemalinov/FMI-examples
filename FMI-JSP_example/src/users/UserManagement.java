package users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class UserManagement {
	private static final String admins_file = "/WEB-INF/users/admins.txt";
	private static final String users_file = "/WEB-INF/users/users.txt";
	private Properties users = new Properties();
	private Properties admins = new Properties();
	private ServletContext context;	
	
	public UserManagement(ServletContext context) {
		this.context = context;				
	}
	
	// synchronize reading/writing in the files because it might get corrupted
	
	public void loadUsers() {
		InputStream is = context.getResourceAsStream(users_file);
		try {
			users.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadAdmins() {
		InputStream is = context.getResourceAsStream(admins_file);
		try {
			admins.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkUser(String username, String password) {
		if(password.equals(users.getProperty(username))) {
			return true;
		}
		if(password.equals(admins.getProperty(username))) {
			return true;
		}
		return false;
	}
	
	public boolean registerUser(String username, String password) {
		if(users.getProperty(username) == null) {
			users.put(username, password);
			try {
				String pathname = context.getRealPath(users_file);
				System.out.println(pathname);
				users.store(new FileWriter(pathname), "new user added");
			} catch (IOException e) {
				e.printStackTrace();
				users.remove(username);
				return false;
			}
		}
		return false;
	}
	
	public boolean registerAdmin(String username, String password) {
		if(admins.getProperty(username) == null) {
			admins.put(username, password);
			try {
				String pathname = context.getRealPath(admins_file);
				System.out.println(pathname);
				admins.store(new FileWriter(pathname), "new user added");
			} catch (IOException e) {
				e.printStackTrace();
				admins.remove(username);
				return false;
			}
		}
		return false;		
	}
		
	
	public boolean isAdminUser(String username) {
		return admins.getProperty(username) != null;
	}
	

}
