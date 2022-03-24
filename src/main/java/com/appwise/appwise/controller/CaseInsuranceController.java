package com.appwise.appwise.controller;

import com.appwise.appwise.dto.CaseInsuranceDto;
import com.appwise.appwise.service.CaseInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/case")
@RequiredArgsConstructor
public class CaseInsuranceController {

    private final CaseInsuranceService caseInsuranceService;

    @GetMapping
    public List<CaseInsuranceDto> getAll() {
        return caseInsuranceService.getAllInsurancesCaseDto();
    }

}
