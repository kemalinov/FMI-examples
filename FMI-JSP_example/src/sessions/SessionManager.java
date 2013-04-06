package sessions;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

// singleton
public class SessionManager {
	
	private static SessionManager manager = new SessionManager();
	// why we are using ConcurrentHashMap? - synchronized hashmap - one object for every user... 
	private ConcurrentHashMap<HttpSession, String> sessions = new ConcurrentHashMap<HttpSession, String>();
	
	public static SessionManager getInstance() {
		return manager;
	}
	
	private SessionManager() {
		
	}

	public ConcurrentHashMap<HttpSession, String> getSessions() {
		return sessions;
	}
	
	public void addSession(HttpSession session) {
		sessions.put(session, session.getId());
	}
	
	
	public void removeSession(HttpSession session) {
		sessions.remove(session);
	}

}
