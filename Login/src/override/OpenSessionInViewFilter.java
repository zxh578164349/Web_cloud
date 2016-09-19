package override;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class OpenSessionInViewFilter extends org.springframework.orm.hibernate3.support.OpenSessionInViewFilter {
	protected void closeSession(Session session,SessionFactory sessionFactory){
		// TODO Auto-generated method stub
		session.flush();
		super.closeSession(session, sessionFactory);
	}
	protected Session getSession(SessionFactory sessionFactory)throws DataAccessResourceFailureException{
		 // TODO Auto-generated method stub 
		Session session=SessionFactoryUtils.getSession(sessionFactory,true);
		this.setFlushMode(FlushMode.COMMIT);
		return session;
		
	}


}
