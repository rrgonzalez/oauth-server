package cl.newit.oauth2baas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
          .withClient("portal-pagos-id")
          .secret("secret")
          .redirectUris(
        		  "http://10.42.0.206:8080/v1", 
        		  "http://10.42.0.206:8080/v1/login")
          .authorizedGrantTypes("password", "refresh_token", "implicit", "authorization_code")
          .scopes("user_info")
          .autoApprove(true)
          .and()
          .withClient("example-client-id")
          .secret("secret")
          .redirectUris("http://console-aetoledano93995427.codeanyapp.com:8081/ui/login",
        		  		"http://console-aetoledano93995427.codeanyapp.com:8081/ui/",
        		  		"http://ubuntu14-04-reinierrg92695293.codeanyapp.com:8081/**",
        		  		"http://ubuntu14-04-reinierrg92695293.codeanyapp.com:8081/ui/login",
        		  		"http://port-8081.Ubuntu14-04-reinierrg92695293.codeanyapp.com/ui/login",
        		  		"http://port-8081.ubuntu14-04-reinierrg92695293.codeanyapp.com/ui/login",
        		  		"http://port-8081.Ubuntu14-04-reinierrg92695293.codeanyapp.com/auth/**",
        		  		"http://localhost:8081/ui/login",
        		  		"http://localhost:8081/ui/")
          .authorizedGrantTypes("password", "refresh_token", "implicit", "authorization_code")
          .scopes("user_info")
          .autoApprove(true)
          .and()
          .withClient("gicona-id")
          .secret("secret")
          .redirectUris("http://example.com")
          .authorizedGrantTypes("password", "refresh_token", "implicit", "authorization_code")
          .scopes("user_info")
          .autoApprove(true); 
    }
 
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
        		.authenticationManager(authenticationManager)
        		.pathMapping("/oauth/authorize", "/authorize")
        		.pathMapping("/oauth/token", "/token");        		
    }
}
