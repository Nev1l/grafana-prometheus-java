package by.sample.grafanaprometheusjava;

import java.lang.reflect.Method;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@SpringBootTest
class GrafanaPrometheusJavaApplicationTests {
/*
	@Autowired
	private ApplicationContext applicationContext;

	private OrderService orderService;

    @PostConstruct
    private void setup() {
		orderService = applicationContext.getBean(OrderService.class);
    }


	@Test
	void contextLoads() {
		orderService.simulateForDE();

		System.out.println("//------------------------------------------//");

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(OrderService.class);
		enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
					throws Throwable {
				if(method.getName().equals("simulateNewOrder")) {
					System.out.println("SampleProxy.simulateNewOrder");
					return null;
				} else {
					return proxy.invokeSuper(obj, args);
				}
			}
		});
		((OrderService) enhancer.create()).simulateForDE();
	}
*/
}
