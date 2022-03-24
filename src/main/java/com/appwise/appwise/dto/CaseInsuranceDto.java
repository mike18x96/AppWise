package com.appwise.appwise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CaseInsuranceDto {

    private String regNumber;
    private double insuranceValueToPaid;
}
