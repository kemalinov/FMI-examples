package web.utils;

import org.jasypt.digest.PooledStringDigester;


public class PasswordEncryption {
    
    private static PooledStringDigester digester;
    
    static {
	digester = new PooledStringDigester();
	digester.setPoolSize(4);          // This would be a good value for a 4-core system 
	digester.setAlgorithm("SHA-1");
	digester.setIterations(50000);	
    }
    
    // TODO: should be used on user registration, before persist!!! 
    public static String encryptPassword(String password) {
	return digester.digest(password);
    }
    
    public static boolean checkPassords(String pass, String digest) {
	return digester.matches(pass, digest);
    }
    
}
