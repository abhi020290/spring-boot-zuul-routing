package com.springbootcloud.eureka.zuulgatewayapi;

import filters.RequestInfoLogPreFilter;
import filters.ResponseInfoLogPostFilter;
import filters.RoutingInfoLogRouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulGatewayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApiApplication.class, args);
    }

    @Bean
    public RequestInfoLogPreFilter requestInfoLogPreFilter() {
        return new RequestInfoLogPreFilter();
    }

    @Bean
    public ResponseInfoLogPostFilter responseInfoLogPostFilter() {
        return new ResponseInfoLogPostFilter();
    }

    @Bean
    public RoutingInfoLogRouteFilter routingInfoLogRouteFilter() {
        return new RoutingInfoLogRouteFilter();
    }
}
