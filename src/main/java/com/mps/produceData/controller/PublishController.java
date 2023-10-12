package com.mps.produceData.controller;

import lombok.RequiredArgsConstructor;
import com.mps.produceData.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
public class PublishController {
    @Autowired
    private ProduceService service;
//    @Autowired
    private KafkaTemplate<String,String> template;
    @GetMapping("/v1")
    public ResponseEntity<String> publishData(){
        try {
            service.publishData();
            System.out.println("Sent Successfully");
            return new ResponseEntity<>("Sent Successfully", HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Something wrong!! "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

    @GetMapping("/v2")
    public ResponseEntity<String> pushData(){
        try {
            int i=1;
            while (i<=1000) {
                CompletableFuture<SendResult<String, String>> result = template.send("topic_1", "Hello " + i);
                String value = result.get().getProducerRecord().value();
                System.out.println("Produced Msg: "+value);
                i++;
            }
            System.out.println("Sent Successfully");
            return new ResponseEntity<>("Sent Successfully", HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Something wrong!! "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/v3")
    public ResponseEntity<DataDTO> getData(){
        DataDTO build = DataDTO.builder().tpName("").cCode("c1").dCode("d1").bUnit("b1").build();
        return new ResponseEntity<>(build,HttpStatus.OK);
    }
}
