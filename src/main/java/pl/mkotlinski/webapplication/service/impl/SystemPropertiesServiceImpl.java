package pl.mkotlinski.webapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import pl.mkotlinski.webapplication.service.SystemPropertiesService;

@Service("systemPropertiesService")
@PropertySource(value= {"classpath:system.properties"})
public class SystemPropertiesServiceImpl implements SystemPropertiesService {

	@Autowired
	private Environment environment;
	
	@Override
	public String getSystemVersion() {
		String systemFullVersion = environment.getRequiredProperty("system.version");
		
		return systemFullVersion.substring(0, systemFullVersion.indexOf("-"));
	}
}
