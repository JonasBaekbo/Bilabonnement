package com.example.bilabonnement.repository;

import com.example.bilabonnement.models.Car;
import junit.framework.TestCase;

public class CarRepositoryTest extends TestCase {

    public void testGetSingleById() {
        //Arrange
        CarRepository carRepo = new CarRepository();
        int carID = 5;
        Car expectedResult = carRepo.getSingleById(carID);
        //Act
        Car actualResult = carRepo.getSingleById(5);
        //Assert
        assertEquals(expectedResult, actualResult);
    }
}