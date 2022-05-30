package com.example.bilabonnement.repository;

import junit.framework.TestCase;

public class EconomyRepositoryTest extends TestCase {

    public void testTotalMonthlyIncomeThisMonthFromRentedCars() {
        //Arrange
        EconomyRepository economyRepo = new EconomyRepository();
        double expectedResult = 30;
        //Act
        double actualResult = economyRepo.totalMonthlyIncomeThisMonthFromRentedCars();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}