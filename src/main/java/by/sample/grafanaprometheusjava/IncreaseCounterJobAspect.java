package by.sample.grafanaprometheusjava;

import io.micrometer.core.instrument.MeterRegistry;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class IncreaseCounterJobAspect {
    //Note: AOP doesn't work with lombok @AllArgsConstructor + modifier final
    //      Use constructor with arg + spring @Component for aspect class
    private MeterRegistry meterRegistry;

    public IncreaseCounterJobAspect(@Autowired MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Pointcut("@annotation(by.sample.grafanaprometheusjava.MonitoringIncreaseCounter)")
    public void annotated() {
        System.out.println("Pointcut: @MonitoringIncreaseCounter");
    }

    @Before("annotated() && !within(IncreaseCounterJobAspect)")
    public void printAspect(JoinPoint joinPoint) {
        System.out.println("Before: @MonitoringIncreaseCounter " + joinPoint.toString());
    }

    @After("execution(* by.sample.grafanaprometheusjava.OrderService.*(..)) && args(country,paymentMethod,shippingMethod)")
    public void after(JoinPoint joinPoint, String country, String paymentMethod, String shippingMethod) throws Throwable {
        System.out.println("Entering " + joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName() + "() using arguments: " + Arrays.toString(joinPoint.getArgs()));
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

            meterRegistry.counter("orders.created",
                            "country", country,
                            "payment_method", paymentMethod,
                            "shipping_method", shippingMethod)
                    .increment();
    }

}
