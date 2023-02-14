package kpn.ctrlf;

import kpn.ctrlf.eventDrive.publisher.AfterStartEventPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CtrlfApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CtrlfApplication.class, args);
		context.getBean(AfterStartEventPublisher.class).publish();
	}
}
