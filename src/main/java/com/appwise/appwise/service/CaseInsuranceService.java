package com.appwise.appwise.service;

import com.appwise.appwise.dto.CaseInsuranceDto;
import com.appwise.appwise.dto.CaseInsuranceDtoConverter;
import com.appwise.appwise.model.CaseInsurance;
import com.appwise.appwise.repository.CaseInsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CaseInsuranceService {
    private final CaseInsuranceRepository caseInsuranceRepository;
    private final CaseInsuranceDtoConverter dtoConverter;

    public List<CaseInsuranceDto> getAllInsurancesCaseDto() {
        List<CaseInsurance> allInsuranceCaseList = caseInsuranceRepository.findAll();
        return convertToDto(allInsuranceCaseList);
    }

    private List<CaseInsuranceDto> convertToDto(List<CaseInsurance> allInsuranceCaseList) {
        return allInsuranceCaseList.stream()
                .map(dtoConverter::convert)
                .collect(Collectors.toList());
    }
}
