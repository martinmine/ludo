package no.hig.imt3281.ludo.backend;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by Martin on 27.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> forName = Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex) {
            System.out.println("ERror");
        }
        System.out.println("Hello");
        factory = new Configuration().configure().buildSessionFactory();
        addUser(new Player("Martin", "martin_mine@hotmail.com"));
    }

    private static SessionFactory factory;

    public static Integer addUser(Player player){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try{
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(player);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return employeeID;
    }

}

