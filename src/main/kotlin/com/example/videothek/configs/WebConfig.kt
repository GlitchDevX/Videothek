package com.example.videothek.configs

import com.example.videothek.interceptor.RequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var requestInterceptor: RequestInterceptor;

    public override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor);
    }
}