package com.mobiversa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mobiversa")
public class PortalConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver configureViewResolver() {
		InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
		viewResolve.setPrefix("/WEB-INF/views/");
		viewResolve.setSuffix(".jsp");

		return viewResolve;
	}

	
	@Bean
	public TilesConfigurer tilesConfigurer() {
	    TilesConfigurer configurer = new TilesConfigurer();
	    configurer.setDefinitions("/WEB-INF/views/**/tiles.xml");
	    configurer.setCheckRefresh(true);
	    return configurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/attachments/receipt/**").addResourceLocations("file:/c:/ocsweb/receipt/");
		registry.addResourceHandler("/attachments/signature/**").addResourceLocations("file:/c:/ocsweb/signature/");
		registry.addResourceHandler("/attachments/document/**").addResourceLocations("file:/c:/ocsweb/document/");
		registry.addResourceHandler("/attachments/welcomeLetter/**")
				.addResourceLocations("file:/c:/ocsweb/welcomeLetter/");
		registry.addResourceHandler("/attachments/quotation/**").addResourceLocations("file:/c:/ocsweb/quotation/");
		registry.addResourceHandler("/attachments/profoma/**").addResourceLocations("file:/c:/ocsweb/profoma/");
		registry.addResourceHandler("/attachments/mma/**").addResourceLocations("file:/c:/ocsweb/mma/");
		registry.addResourceHandler("/documents/files/quotation/**")
				.addResourceLocations("file:/c:/ocsweb/fileSharing/Quotation/");

		registry.addResourceHandler("/documents/files/schedule/**")
				.addResourceLocations("file:/c:/ocsweb/fileSharing/Schedule/");

		registry.addResourceHandler("/documents/files/welcome/**")
				.addResourceLocations("file:/c:/ocsweb/fileSharing/Welcome/");

		registry.addResourceHandler("/documents/old/files/quotation/**")
				.addResourceLocations("file:/C:/ocsweb/oldData/Quotation/");

		registry.addResourceHandler("/documents/old/files/fileShare/**")
				.addResourceLocations("file:/C:/ocsweb/oldData/FileShare/");

		registry.addResourceHandler("/documents/old/files/schedule/**")
				.addResourceLocations("file:/C:/ocsweb/oldData/Schedule/");

		registry.addResourceHandler("/documents/old/files/welcome/**")
				.addResourceLocations("file:/C:/ocsweb/oldData/Welcome/");
	}

	public void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setCaseSensitive(false);
		configurer.setPathMatcher(matcher);
		configurer.setUseTrailingSlashMatch(true);
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
