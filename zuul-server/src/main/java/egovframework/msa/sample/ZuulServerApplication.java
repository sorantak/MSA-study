package egovframework.msa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

    /** Default Resilience4j circuit breaker configuration */
//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory ->
//
//                factory.configureDefault(
//                        id ->
//                                new Resilience4JConfigBuilder(id)
//                                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                                        .timeLimiterConfig(
//                                                TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                                        .build());
//    }
}
