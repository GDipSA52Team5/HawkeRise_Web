package sg.edu.iss.hawkerise.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path menuItemUploadDir = Paths.get("./item-photo/");
		String menuItemUploadPath = menuItemUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/item-photo/**").addResourceLocations("file:/" + menuItemUploadPath + "/");
	}
}
