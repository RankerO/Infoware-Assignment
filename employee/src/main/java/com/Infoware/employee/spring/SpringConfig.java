package com.Infoware.employee.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan("com.Infoware.employee")
@PropertySources({ //
		@PropertySource(value = "file:./employee.properties", ignoreResourceNotFound = true) //
})
public class SpringConfig {


}
