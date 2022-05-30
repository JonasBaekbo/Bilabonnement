//Jonas

package com.example.bilabonnement.repository;

import junit.framework.TestCase;

public class EconomyRepositoryTest extends TestCase {

    public void testTotalMonthlyIncomeThisMonthFromRentedCars() {
        //Arrange
        EconomyRepository economyRepo = new EconomyRepository();
        //Vi er klar over at der ikke kommer tallet 30 ud, da de vil ændre sig alt efter hvor mange og hvilke lejeaftaler der er oprettet
        double expectedResult = 30;
        //Act
        double actualResult = economyRepo.totalMonthlyIncomeThisMonthFromRentedCars();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}