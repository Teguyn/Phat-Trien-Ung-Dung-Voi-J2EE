package com.example.BT4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${app.upload.dir}")
    private String uploadDir;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (uploadDir != null && !uploadDir.isEmpty()) {
            try {
                // Tạo đường dẫn tuyệt đối và chuẩn hóa cho Windows/Linux
                String uploadPath = "file:" + Paths.get(uploadDir).toAbsolutePath().toString().replace("\\", "/") + "/";
                
                registry.addResourceHandler("/uploads/**")
                        .addResourceLocations(uploadPath);
            } catch (Exception e) {
                System.out.println("Error configuring resource handler: " + e.getMessage());
            }
        }
    }
}
