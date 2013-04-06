package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sessions.SessionManager;

@WebListener
public class SessionListener implements HttpSessionListener {

	private SessionManager manager = SessionManager.getInstance();
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		manager.addSession(event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		manager.removeSession(event.getSession());
	}

}
// Session listener - this one
// application listener - ApplicationStartedListener
// one more listener - in a new listener <request, response> object on every click