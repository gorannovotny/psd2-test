package hr.abc.psd2.util;//package psd2.xs2a.hr.abit.util;
//
//
////import org.apache.log4j.Logger;
//import hr.abit.b3.biz.core.util.LookupHelper;
//import hr.abit.b3.biz.psd2.xs2a.util.Psd2Constants;
//import org.apache.commons.lang3.StringUtils;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@WebListener
//public class ElasticListener implements ServletContextListener {
////public class ElasticListener {
//
//	private static final Logger logger = LoggerFactory.getLogger(ElasticListener.class);
//	private static TransportClient client = null;
//	private static String interfaceEnvironment;
//
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		log.info("Destroying Elastic TransportClient...");
//		if (client != null) client.close();
//	}
//
//	@SuppressWarnings("resource")
//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		log.info("Initializing Elastic TransportClient...");
//		try {
//			// settings
//			Settings settings = formSettingsHttp();
//			//get host and port
//			String elasticSearchServer = LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_ELASTIC_SEARCH_LINK);
//			String host = null;
//			String port = null;
//			if (StringUtils.isNotBlank(elasticSearchServer)) {
//				host = StringUtils.substringBefore(elasticSearchServer, ":");
//				port = StringUtils.substringAfter(elasticSearchServer, ":");
//			}
//			// http
//			client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Integer.valueOf(port)));
//			// https
//			// client = new PreBuiltXPackTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("zelenac"), 9300));
//		} catch (UnknownHostException e) {
//			log.error(e.getMessage());
//		} catch (Exception ex) {
//			log.error(ex.getMessage());
//		}
//	}
//
//	public static TransportClient getClient() {
//		return client;
//	}
//
//	/**
//	 * Formiranje settingsa za elastic search server.
//	 *
//	 * @return
//	 * @author Mata
//	 */
//	private Settings formSettingsHttp() {
//        Settings settings = Settings.builder()
//        		.put("cluster.name", "elasticsearch")
//        		.build();
//        return settings;
//	}
//
//	/**
//	 * Formiranje settingsa za elastic search server - https.
//	 *
//	 * @return
//	 * @author Mata
//	 */
//	@SuppressWarnings("unused")
//	private Settings formSettingsHttps() {
//		Settings settings = Settings.builder()
//				.put("cluster.name", "elasticsearch")
//        		.put("xpack.security.user", System.getProperty("es.user"))
//        		.put("xpack.ssl.key", "/usr/share/elasticsearch/config/x-pack/node01.key")
//                .put("xpack.ssl.certificate", "/usr/share/elasticsearch/config/x-pack/node01.crt")
//                .put("xpack.ssl.certificate_authorities", "/usr/share/elasticsearch/config/x-pack/ca.crt")
//                .put("xpack.security.transport.ssl.enabled", "true")
//				.build();
//		return settings;
//	}
//
//}
