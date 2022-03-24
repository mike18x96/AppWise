package com.appwise.appwise.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String car;
    @NotNull
    private String regNumber;
    @NotNull
    private LocalDate dateOfInsurance;
    @NotNull
    private LocalDate dateOfReportDamage;
    @NotNull
    private double carValuationOfBeginningInsurance;
    @NotNull
    private double costOfDamage;

}
