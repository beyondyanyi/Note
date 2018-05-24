package utils;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class MySpringBootHealth implements HealthIndicator{
	@Override
	public Health health() {
		 return Health.up().build();  
	}

}
