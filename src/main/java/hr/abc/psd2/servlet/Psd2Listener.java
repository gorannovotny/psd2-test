package hr.abc.psd2.servlet;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;

/**
 * IB listener.
 * 
 * - inicijalizacija int na web-u to null, umjesto 0
 * - preskakanje kontrole da se za parametar ne mogu koristiti rezervirane riječi java-e
 * 
 * @author Matija Hlapčić
 */
public class Psd2Listener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
			System.setProperty("org.apache.el.parser.SKIP_IDENTIFIER_CHECK", "true");
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error( e.getMessage(), e);			
		}
	}

}
