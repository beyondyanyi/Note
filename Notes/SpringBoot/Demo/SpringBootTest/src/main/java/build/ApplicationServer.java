package build;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import controller.SampleController;

@SpringBootApplication
public class ApplicationServer {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
