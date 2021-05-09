package com.spring.cloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RecommendationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/recommendations", method = RequestMethod.GET)
    @ResponseBody
    public Movie[] recommendations() {
        Movie[] result = restTemplate.getForObject("http://movie-service/movies", Movie[].class);
        return result;
    }

    @RequestMapping(value = "/discoveryClient/recommendations", method = RequestMethod.GET)
    @ResponseBody
    public Movie[] discoveryClient() throws Exception {
        List<ServiceInstance> serviceList = discoveryClient.getInstances("movie-service");
        if (serviceList != null && serviceList.size() > 0) {
            System.out.println("Sevice list===>" + serviceList.size());
            Movie[] result = restTemplate.getForObject(serviceList.get(0).getUri() + "/movies", Movie[].class);
            return result;
        }

        throw new Exception();
    }
}
