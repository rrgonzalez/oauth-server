// package cl.newit.oauth2baas;

// import org.apache.catalina.Context;
// import org.apache.catalina.connector.Connector;
// import org.apache.tomcat.util.descriptor.web.SecurityCollection;
// import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
// import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class TomcatHttpConfig {

// 	@Value("${server.port}")
// 	int defaultPort;

// 	@Value("${server.httpPort}")
// 	int httpPort;

// 	@Value("${server.httpConfig}")
// 	String config;

// 	@Bean
// 	public EmbeddedServletContainerFactory servletContainer() {
// 		switch (config) {
// 		case "redirect":
// 			return http2httpsFactory();
// 		default:
// 			return httpDefultFactory();
// 		}
// 	}

// 	private EmbeddedServletContainerFactory httpDefultFactory() {
// 		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
// 		tomcat.addAdditionalTomcatConnectors(httpConnector());
// 		return tomcat;
// 	}

// 	private Connector httpConnector() {
// 		Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
// 		connector.setScheme("http");
// 		connector.setPort(httpPort);
// 		connector.setSecure(false);
// 		return connector;
// 	}

// 	private EmbeddedServletContainerFactory http2httpsFactory() {
// 		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
// 			@Override
// 			protected void postProcessContext(Context context) {
// 				SecurityConstraint securityConstraint = new SecurityConstraint();
// 				securityConstraint.setUserConstraint("CONFIDENTIAL");
// 				SecurityCollection collection = new SecurityCollection();
// 				collection.addPattern("/*");
// 				securityConstraint.addCollection(collection);
// 				context.addConstraint(securityConstraint);
// 			}
// 		};
// 		tomcat.addAdditionalTomcatConnectors(http2httpsConnector());
// 		return tomcat;
// 	}

// 	private Connector http2httpsConnector() {
// 		Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
// 		connector.setScheme("http");
// 		connector.setPort(httpPort);
// 		connector.setSecure(false);
// 		connector.setRedirectPort(defaultPort);
// 		return connector;
// 	}
// }
