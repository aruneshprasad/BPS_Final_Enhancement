package bps.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bps.utils.HibernateUtils;

public class Main {
	public static void main(String[] args) {

		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session ses = sf.openSession();

		ses.beginTransaction().commit();

		HibernateUtils.shutdown();
	}
}
