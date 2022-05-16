package com.example.bilabonnement.servises;
import com.example.bilabonnement.models.Car;
import com.example.bilabonnement.models.CarEconomy;
import com.example.bilabonnement.repository.EconomyRepository;

import java.util.List;

public class Economy {

    public int totalMonthlyIncomeThisMonthFromRentedCars(){

        EconomyRepository economyRepository = new EconomyRepository();
        List<CarEconomy> carEconomies = economyRepository.getAllEntities();
        int totalMoeny = 0;
        for (CarEconomy carEconomy : carEconomies) {
            int moneyPrMonthFromCar = carEconomy.getPricePrMonth();
            totalMoeny = totalMoeny + moneyPrMonthFromCar;
        }

        return totalMoeny;
    }
}
