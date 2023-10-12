package com.mps.produceData.route;

import com.mps.produceData.service.ProduceService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteBuilder extends RouteBuilder {
    @Autowired
    private ProduceService service;
    @Override
    public void configure() throws Exception {
//        from("timer:testing?period=5000")
//                .bean(service,"publishData");

        from("direct:push-event")
                .log("Direct Thread Name: ${threadName}")
                .bean(service,"publishData");

        from("timer:test?period=5000")
                .log("Timer Thread Name: ${threadName}")
                .to("direct:push-event");
    }
}
