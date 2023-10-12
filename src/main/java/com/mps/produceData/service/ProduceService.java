package com.mps.produceData.service;

import com.mps.produceData.proto.CCAssociatedDataEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
//@RequiredArgsConstructor
public class ProduceService {

    @Autowired
    private KafkaTemplate<String,Object> template;
    public void publishData() throws ExecutionException, InterruptedException {
        CCAssociatedDataEvent.AssociatedDataDTOList associatedDataDTOList1 = CCAssociatedDataEvent.AssociatedDataDTOList.newBuilder().setCcode("c1").setBunit("b1").setDcode("d1").build();
        CCAssociatedDataEvent.AssociatedDataDTOList associatedDataDTOList2 = CCAssociatedDataEvent.AssociatedDataDTOList.newBuilder().setCcode("c2").setBunit("b2").setDcode("d2").build();
        CCAssociatedDataEvent.AssociatedDataDTOList associatedDataDTOList3 = CCAssociatedDataEvent.AssociatedDataDTOList.newBuilder().setCcode("c3").setBunit("b3").setDcode("d3").build();
        CCAssociatedDataEvent.AssociatedDataDTOList associatedDataDTOList4 = CCAssociatedDataEvent.AssociatedDataDTOList.newBuilder().setCcode("c4").setBunit("b4").setDcode("d4").build();
        CCAssociatedDataEvent.AssociatedDataDTOList associatedDataDTOList5 = CCAssociatedDataEvent.AssociatedDataDTOList.newBuilder().setCcode("c5").setBunit("b5").setDcode("d5").build();

        List<CCAssociatedDataEvent.AssociatedDataDTOList> associatedDataDTOList = List.of(associatedDataDTOList1, associatedDataDTOList2, associatedDataDTOList3, associatedDataDTOList4, associatedDataDTOList5);

        CCAssociatedDataEvent event = CCAssociatedDataEvent.newBuilder().setTpName("WHIRLPCT").addAllAssociatedDataDTOList(associatedDataDTOList).build();

        CompletableFuture<SendResult<String, Object>> future = template.send("first_topic", "one", event);
        long timestamp = future.get().getRecordMetadata().timestamp();
//        future.whenComplete()
//        System.out.println("TS: "+timestamp);

        //Converting epoch into UTC format
        Date date = new Date(timestamp);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        System.out.println("TS: "+formatted);
    }
}
