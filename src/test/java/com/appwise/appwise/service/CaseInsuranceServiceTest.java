package com.appwise.appwise.service;

import com.appwise.appwise.dto.CaseInsuranceDto;
import com.appwise.appwise.dto.CaseInsuranceDtoConverter;
import com.appwise.appwise.model.CaseInsurance;
import com.appwise.appwise.repository.CaseInsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CaseInsuranceServiceTest {

    @Mock
    private CaseInsuranceRepository caseInsuranceRepository;
    @Mock
    private CaseInsuranceDtoConverter caseInsuranceDtoConverter;
    @InjectMocks
    private CaseInsuranceService caseInsuranceService;

    @Test
    void getAllInsurancesCaseDto_listOfProductIsNotEmpty_returnListProduct() {
        //given
        CaseInsurance caseInsurance = CaseInsurance.builder()
                .id(1l)
                .car("car")
                .regNumber("abc123")
                .dateOfInsurance(LocalDate.parse("2021-03-03"))
                .dateOfReportDamage(LocalDate.parse("2021-03-03"))
                .carValuationOfBeginningInsurance(10000)
                .costOfDamage(7000)
                .build();
        CaseInsuranceDto caseInsuranceDto = new CaseInsuranceDto("abc123", 7000);

        ArrayList<CaseInsurance> caseInsuranceList = new ArrayList<>();
        caseInsuranceList.add(caseInsurance);

        when(caseInsuranceRepository.findAll()).thenReturn(caseInsuranceList);
        when(caseInsuranceDtoConverter.convert(caseInsuranceList.get(0))).thenReturn(caseInsuranceDto);
        //when
        List<CaseInsuranceDto> resultList = caseInsuranceService.getAllInsurancesCaseDto();

        //then
        assertThat(resultList.get(0).getRegNumber()).isEqualTo("abc123");
        assertThat(resultList.get(0).getInsuranceValueToPaid()).isEqualTo(7000);
        verify(caseInsuranceRepository, times(1)).findAll();
        verify(caseInsuranceDtoConverter, times(1)).convert(any(CaseInsurance.class));
        verifyNoMoreInteractions(caseInsuranceRepository);
        verifyNoMoreInteractions(caseInsuranceDtoConverter);
    }

    //there should be more tests here :)

}