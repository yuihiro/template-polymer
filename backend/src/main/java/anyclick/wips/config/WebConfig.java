package anyclick.wips.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import anyclick.wips.filter.AuthInterceptor;

@Configuration
//<mvc:annotation-driven/>
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	/* <mvc:default-servlet-handler> 처리할 수 없는 요청은 DefaultServletHandler에게 위임 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor());
	}

	//	@Override
	//	public void addCorsMappings(CorsRegistry registry) {
	//		registry.addMapping("/api/**");
	//		//registry.addMapping("/api/**").allowedOrigins("http://localhost:5000").allowCredentials(true);
	//	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Bean
	public ErrorPageFilter errorPageFilter() {
		return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.setEnabled(false);
		return filterRegistrationBean;
	}

	//	@Bean
	//	public EmbeddedServletContainerFactory servletContainer() {
	//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
	//
	//			@Override
	//			protected void postProcessContext(Context context) {
	//				SecurityConstraint securityConstraint = new SecurityConstraint();
	//				securityConstraint.setUserConstraint("CONFIDENTIAL");
	//				SecurityCollection collection = new SecurityCollection();
	//				collection.addPattern("/*");
	//				securityConstraint.addCollection(collection);
	//				context.addConstraint(securityConstraint);
	//			}
	//		};
	//
	//		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
	//		return tomcat;
	//	}
	//
	//	private Connector initiateHttpConnector() {
	//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	//		connector.setScheme("http");
	//		connector.setPort(8080);
	//		connector.setSecure(false);
	//		connector.setRedirectPort(8443);
	//
	//		return connector;
	//	}

	//	@Override
	//	public void configurePathMatch(PathMatchConfigurer matcher) {
	//		matcher.setUseRegisteredSuffixPatternMatch(true);
	//	}
}
