package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.message.request.SearchTruck;
import com.orkhan.web.out.ecargo.message.request.TruckPhoto;
import com.orkhan.web.out.ecargo.repository.TruckOrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class TruckServiceTests {

    @InjectMocks
    private TruckRequestService truckRequestService;


    @Mock
    private TruckOrderRepository truckOrderRepository;



    @Test
    public void saveTruckOrder() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(truckOrderRepository.findTruckBefore(truck.getDepartureLocation(),truck.getArrivalLocation(),truck.getArrivalLocation(),truck.getArrivalLocation())).thenReturn(new ArrayList<>());

        Truck truck1 = truckRequestService.saveTruckOrder(truck,1L);
        assertEquals(truck1.getArrivalLocation(),truck.getArrivalLocation());


    }

    @Test
    public void saveTruckOrderReturnError() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(truckOrderRepository.findTruckBefore(truck.getDepartureLocation(),truck.getArrivalLocation(),truck.getArrivalLocation(),truck.getArrivalLocation())).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            Truck truck1 = truckRequestService.saveTruckOrder(truck,1L);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Route Already Created");
        }

    }



    @Test
    public void getAllTrucks() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findAllTrucks()).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            List<Truck> truck1 = truckRequestService.getAllTrucks();
            assertEquals(truck1.get(0).getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Route Already Created");
        }

    }

    @Test
    public void getAllTrucksByTruckId() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findByTruckDriver(1L)).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            List<Truck> truck1 = truckRequestService.getAllTrucks();
            assertEquals(truck1.get(0).getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {
        }

    }

    @Test
    public void getTruckByTruckType() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findByTruckType(new ArrayList<String>(Arrays.asList("test")))).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            List<Truck> truck1 = truckRequestService.getAllTrucksByType(new ArrayList<String>(Arrays.asList("test")));
            assertEquals(truck1.get(0).getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {

        }

    }


    @Test
    public void getTruckById() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));

        try {
            Optional<Truck> truck1 = truckRequestService.getTruckById(1L);
            assertEquals(truck1.get().getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {

        }

    }


    @Test
    public void updateTruckPhoto() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(truckOrderRepository.save(truck)).thenReturn(truck);

        try {
            TruckPhoto truckPhoto = new TruckPhoto();
            truckPhoto.setPhoto("test");
            truckPhoto.setId(1L);
            Truck truck1 = truckRequestService.updateTruckPhoto(truckPhoto);
            assertEquals(truck1.getArrivalLocation(),truck.getArrivalLocation());
            assertEquals(truckPhoto.getPhoto(),"test");
        }
        catch (Exception e) {

        }

    }

    @Test
    public void updateTruckPhotoReturnError() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findById(1L)).thenReturn(null);

        try {
            TruckPhoto truckPhoto = new TruckPhoto();
            Truck truck1 = truckRequestService.updateTruckPhoto(truckPhoto);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Invalid truck id");
        }

    }

    @Test
    public void getAllTrucksBySearch() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        SearchTruck searchTruck = new SearchTruck();
        searchTruck.setDropOffLocation("test");
        searchTruck.setDropOffTime(LocalDateTime.now());
        searchTruck.setPickUpLocation("test");
        searchTruck.setPickUpTime(LocalDateTime.now());

        when(truckOrderRepository.findByTruckSearch(searchTruck.getDropOffLocation(),searchTruck.getPickUpLocation(),searchTruck.getDropOffTime().toString(),searchTruck.getPickUpTime().toString())).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            List<Truck> truck1 = truckRequestService.getAllTrucksBySearch(searchTruck);
            assertEquals(truck1.get(0).getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {

        }

    }

    @Test
    public void getTruckByTruckDriver() throws Exception {

        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(1000.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findByTruckDriver(1L)).thenReturn(new ArrayList<Truck>(Arrays.asList(truck)));

        try {
            List<Truck> truck1 = truckRequestService.getAllTrucksByDriver(1L);
            assertEquals(truck1.get(0).getArrivalLocation(),truck.getArrivalLocation());
        }
        catch (Exception e) {

        }

    }









}
