//Jonas

package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.CarStatus;
import junit.framework.TestCase;

public class CarStatusRepositoryTest extends TestCase {

    public void testGetByName() {
        //Arrange
        CarStatusRepository carStatusRepo = new CarStatusRepository();
        String carStatusName = "Hjemme";
        String expectedResult = new CarStatus(4, "Hjemme").getCarStatusName();

        //Act
        String actualResult = carStatusRepo.getByName(carStatusName).getCarStatusName();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    public void testGetByID() {
        //Arrange
        CarStatusRepository carStatusRepo = new CarStatusRepository();
        int carStatusID = 4;
        int expectedResult = new CarStatus(4, "Hjemme").getCarStatusId();

        //Act
        int actualResult = carStatusRepo.getByID(carStatusID).getCarStatusId();
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}