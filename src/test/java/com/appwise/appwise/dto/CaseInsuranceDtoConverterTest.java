package com.appwise.appwise.dto;

import com.appwise.appwise.model.CaseInsurance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

class CaseInsuranceDtoConverterTest {

    @Test
    void convert_durationOfInsuranceHaveMoreThanOnYear_returnsValueToPaidZero() {
        //when
        CaseInsurance caseInsurance = CaseInsurance.builder()
                .id(1l)
                .car("car")
                .regNumber("abc123")
                .dateOfInsurance(LocalDate.parse("2021-03-03"))
                .dateOfReportDamage(LocalDate.parse("2022-05-05"))
                .carValuationOfBeginningInsurance(100000)
                .costOfDamage(10000)
                .build();
        CaseInsuranceDtoConverter converter = new CaseInsuranceDtoConverter();
        CaseInsuranceDto caseInsuranceDto = converter.convert(caseInsurance);

        Assertions.assertThat(caseInsuranceDto.getRegNumber()).isEqualTo("abc123");
        Assertions.assertThat(caseInsuranceDto.getInsuranceValueToPaid()).isZero();
    }

    @ParameterizedTest
    @MethodSource("provideValueLessThan70PctOfCurrentValueCar")
    void convert_durationOfInsuranceHaveLessThanOnYear_returnsValueToPaidEqualCostOfDamage(double valueOfCar,
                                                                                           double costOfDamage ) {
        //when
        CaseInsurance caseInsurance = CaseInsurance.builder()
                .id(1l)
                .car("car")
                .regNumber("abc123")
                .dateOfInsurance(LocalDate.parse("2021-03-03"))
                .dateOfReportDamage(LocalDate.parse("2021-03-03"))
                .carValuationOfBeginningInsurance(valueOfCar)
                .costOfDamage(costOfDamage)
                .build();
        CaseInsuranceDtoConverter converter = new CaseInsuranceDtoConverter();
        CaseInsuranceDto caseInsuranceDto = converter.convert(caseInsurance);

        Assertions.assertThat(caseInsuranceDto.getRegNumber()).isEqualTo("abc123");
        Assertions.assertThat(caseInsuranceDto.getInsuranceValueToPaid()).isEqualTo(costOfDamage);
    }

    private static Stream<Arguments> provideValueLessThan70PctOfCurrentValueCar() {
        return Stream.of(
                Arguments.of(10000, 7000),
                Arguments.of(10000, 6000),
                Arguments.of(10000, 5000));
    }

    @ParameterizedTest
    @MethodSource("provideValueMoreThan70PctOfCurrentValueCar")
    void convert_durationOfInsuranceHaveLessThanOnYear_returnsValueToPaidLessThenCostOfDamage(double valueOfCar,
                                                                                           double costOfDamage) {
        //when
        CaseInsurance caseInsurance = CaseInsurance.builder()
                .id(1l)
                .car("car")
                .regNumber("abc123")
                .dateOfInsurance(LocalDate.parse("2021-03-03"))
                .dateOfReportDamage(LocalDate.parse("2021-03-03"))
                .carValuationOfBeginningInsurance(valueOfCar)
                .costOfDamage(costOfDamage)
                .build();
        CaseInsuranceDtoConverter converter = new CaseInsuranceDtoConverter();
        CaseInsuranceDto caseInsuranceDto = converter.convert(caseInsurance);

        Assertions.assertThat(caseInsuranceDto.getRegNumber()).isEqualTo("abc123");
        Assertions.assertThat(caseInsuranceDto.getInsuranceValueToPaid()).isEqualTo(valueOfCar*0.7);
    }

    private static Stream<Arguments> provideValueMoreThan70PctOfCurrentValueCar() {
        return Stream.of(
                Arguments.of(10000, 7001),
                Arguments.of(10000, 8000),
                Arguments.of(10000, 9000));
    }

    //there should be more tests here :)

}