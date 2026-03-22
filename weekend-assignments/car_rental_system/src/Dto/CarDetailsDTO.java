package Dto;

import Model.Car;

public record CarDetailsDTO(
        Car car,
        String oname,
        int year_of_purchase,
        int milage

) {
}
