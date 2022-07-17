package by.sample.grafanaprometheusjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy(proxyTargetClass = true) //breakpoint DefaultAopProxyFactory.createAopProxy() Verify it:  true = CglibAutoProxyConfiguration;
@EnableScheduling
@SpringBootApplication
public class GrafanaPrometheusJavaApplication {
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(GrafanaPrometheusJavaApplication.class, args);
		checkBeansPresence(
				"orderService","increaseCounterJobAspect");
	}

	@Autowired
	private OrderService orderService;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			orderService.simulateForDE();
			orderService.simulateForAT();
		};
	}

	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " +
					applicationContext.containsBean(beanName));
		}
	}
}
