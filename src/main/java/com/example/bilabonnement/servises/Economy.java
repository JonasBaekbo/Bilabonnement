package com.example.bilabonnement.servises;
import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.repository.EconomyRepository;

import java.util.List;

public class Economy {

    public int totalMonthlyIncomeThisMonthFromRentedCars(){

        EconomyRepository repository = new EconomyRepository();
        List<Car> cars = repository.getAllEntities();
        int totalMoeny = 0;
        for (Car car : cars) {
            int moneyPrMonthFromCar = car.getPricePrMonth();
            totalMoeny = totalMoeny + moneyPrMonthFromCar;
        }

        return totalMoeny;
    }
}
