package com.orkhan.web.out.ecargo.entity;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class TruckTests {

    @Test
    public void createTruck() {
        Truck truck = new Truck();
        LocalDateTime localDateTime = LocalDateTime.now();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(localDateTime);
        truck.setDepartureTime(localDateTime);
        truck.setId(1L);
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);
        assertEquals(truck.getTruckType(),"test");
        assertEquals(truck.getTruckName(),"test");
        assertEquals(truck.getArrivalLocation(),"test");
        assertEquals(truck.getId().toString(),"1");
        assertEquals(truck.getTruckDriverId(),1L);
        assertEquals(truck.getFreeSpace().toString(),"1000.0");
        assertEquals(truck.getPhoto(),"test");
        assertEquals(truck.getDepartureLocation(),"test");
        assertEquals(truck.getPricePerKg().toString(),"12.0");
        assertEquals(truck.getArrivalTime(),localDateTime);
        assertEquals(truck.getDepartureTime(),localDateTime);
    }
}
