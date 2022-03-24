package data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbUtils {
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            SESSION_FACTORY = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session INSTANCE = SESSION_FACTORY.openSession();

    /**
     * Retrieve the thread safe singleton of the DB session.
     *
     * @return An instance of the DB session.
     */
    public static Session getSessionInstance() {
        synchronized (SESSION_FACTORY) {
            if(INSTANCE == null) {
                INSTANCE = SESSION_FACTORY.openSession();
            }
        }
        return INSTANCE;
    }
}
