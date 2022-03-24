package com.appwise.appwise.dto;

import com.appwise.appwise.model.CaseInsurance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class CaseInsuranceDtoConverter implements Converter<CaseInsurance, CaseInsuranceDto> {

    @Override
    public CaseInsuranceDto convert(CaseInsurance caseInsurance) {
            LocalDate dateOfInsurance = caseInsurance.getDateOfInsurance();
            LocalDate dateOfReportDamage = caseInsurance.getDateOfReportDamage();
            long durationOfInsurance = DAYS.between(dateOfInsurance, dateOfReportDamage);

            CaseInsuranceDto caseInsuranceDto;

            if (durationOfInsurance > 365) {
                caseInsuranceDto = new CaseInsuranceDto(caseInsurance.getRegNumber(), 0);
            } else {
                caseInsuranceDto = CaseInsuranceDto.builder()
                        .regNumber(caseInsurance.getRegNumber())
                        .insuranceValueToPaid(amountOfCompensation(caseInsurance))
                        .build();
            }
            return caseInsuranceDto;
    }

    private double amountOfCompensation(CaseInsurance caseInsurance) {
        double currentValueOfCar = currentValueCar(
                caseInsurance.getDateOfInsurance(),
                caseInsurance.getDateOfReportDamage(),
                caseInsurance.getCarValuationOfBeginningInsurance());
        double percentageOfDamage = caseInsurance.getCostOfDamage() / currentValueOfCar;

        if (percentageOfDamage <= 0.7) {
            return caseInsurance.getCostOfDamage();
        } else {
            return currentValueOfCar * 0.7;
        }
    }

    private double currentValueCar(LocalDate dateOfInsurance, LocalDate dateOfReportDamage, double currentValueOfCar) {
        long monthsBetween = ChronoUnit.MONTHS.between(
                YearMonth.from(dateOfInsurance),
                YearMonth.from(dateOfReportDamage));
        for (int i = 0; i < monthsBetween; i++) {
            currentValueOfCar *= 0.99;
        }
        return currentValueOfCar;
    }
}