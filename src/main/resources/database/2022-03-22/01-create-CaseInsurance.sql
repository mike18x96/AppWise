--liquibase formatted sql
--changeset aolejniczak:1
CREATE TABLE IF NOT EXISTS case_insurance
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    car VARCHAR(100) NOT NULL,
    reg_number VARCHAR(20) NOT NULL,
    date_of_insurance DATE NOT NULL,
    date_of_report_damage DATE NOT NULL,
    car_valuation_of_beginning_insurance DOUBLE NOT NULL,
    cost_of_damage DOUBLE NOT NULL
);
insert into case_insurance (id, car, reg_number, date_of_insurance, date_of_report_damage, car_valuation_of_beginning_insurance, cost_of_damage)
values (1, 'Audi A6', 'ABC 1234', '2021-03-01', '2021-06-01', 40000, 4500);
insert into case_insurance (id, car, reg_number, date_of_insurance, date_of_report_damage, car_valuation_of_beginning_insurance, cost_of_damage)
values (2, 'VW Passat', 'DEF 567', '2021-01-01', '2021-09-01', 12000, 7730);
insert into case_insurance (id, car, reg_number, date_of_insurance, date_of_report_damage, car_valuation_of_beginning_insurance, cost_of_damage)
values (3, 'Skoda Octavia', 'GHI 8910', '2020-01-01', '2021-05-01', 25000, 7000);
