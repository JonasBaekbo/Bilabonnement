//Jonas

package com.example.bilabonnement.repository;

import junit.framework.TestCase;

public class EconomyRepositoryTest extends TestCase {

    public void testTotalMonthlyIncomeThisMonthFromRentedCars() {
        //Arrange
        EconomyRepository economyRepo = new EconomyRepository();

        double expectedMinimumResult = 0;
        //Act
        double actualResult = economyRepo.totalMonthlyIncomeThisMonthFromRentedCars();

        //Assert
        assertTrue(actualResult > expectedMinimumResult);
    }
}