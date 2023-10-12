package com.mps.produceData.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataDTO {
    private String tpName;
    private String cCode;
    private String dCode;
    private String bUnit;
}
