package by.sample.grafanaprometheusjava;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class OrderService {

  @Lazy
  @Autowired
  private OrderService orderService;

  private static final List<String> availablePaymentMethods =
      Arrays.asList("INVOICE", "CREDITCARD", "PAYPAL");
  private static final List<String> availableShippingMethods = Arrays.asList("STANDARD", "EXPRESS");

  private String randomPaymentMethod() {
    int randomIndex = new Random().nextInt(availablePaymentMethods.size());
    return availablePaymentMethods.get(randomIndex);
  }

  private String randomShippingMethod() {
    int randomIndex = new Random().nextInt(availableShippingMethods.size());
    return availableShippingMethods.get(randomIndex);
  }

  //@MonitoringIncreaseCounter
  @Scheduled(fixedDelay = 1000)
  public void simulateForDE() {
    //System.out.println("simulateForDE");
    orderService.simulateNewOrder("DE", randomPaymentMethod(), randomShippingMethod());
  }

  @Scheduled(fixedDelay = 5000)
  //@MonitoringIncreaseCounter
  public void simulateForAT() {
    //System.out.println("simulateForAT");
    orderService.simulateNewOrder("AT", randomPaymentMethod(), randomShippingMethod());
  }


  @MonitoringIncreaseCounter
  public void simulateNewOrder(String country, String paymentMethod, String shippingMethod) {
    System.out.println("simulateNewOrder: " + country + " " + paymentMethod + " " + shippingMethod);
  }



}
