package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.message.request.SearchTruck;
import com.orkhan.web.out.ecargo.message.request.TruckPhoto;
import com.orkhan.web.out.ecargo.repository.TruckOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TruckRequestService {
    @Autowired
    private TruckOrderRepository truckOrderRepository;

    public Truck saveTruckOrder(Truck truck, long id) throws Exception {
        try {
            int year = truck.getArrivalTime().getYear();
            int month = truck.getArrivalTime().getMonthValue();
            int day = truck.getArrivalTime().getDayOfMonth();

            String monthConverted = ""+month;
            if(month<10){
                monthConverted = "0"+monthConverted;
            }

            String dropoff = year + "-" + monthConverted + "-" + day;



            int year1 = truck.getDepartureTime().getYear();
            int month1 = truck.getDepartureTime().getMonthValue();
            int day1 = truck.getDepartureTime().getDayOfMonth();

            String monthConverted1 = ""+month1;
            if(month1<10){
                monthConverted1 = "0"+monthConverted1;
            }

            String pickup = year1 + "-" + monthConverted1 + "-" + day1;

            List<Truck> truck1 = truckOrderRepository.findTruckBefore(truck.getDepartureLocation(),pickup,truck.getArrivalLocation(),dropoff);
            if(truck1.isEmpty())
            {
                truck.setTruckDriverId(id);
                return truckOrderRepository.save(truck);
            }

            throw new Exception("Route Already Created");
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public List<Truck> getAllTrucks() throws Exception {
        try {
            return truckOrderRepository.findAllTrucks();
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public List<Truck> getAllTrucksByDriver(long id) throws Exception {
        try {
            return truckOrderRepository.findByTruckDriver(id);
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public List<Truck> getAllTrucksByType(List<String> types) throws Exception {
        try {
            return truckOrderRepository.findByTruckType(types);
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public Optional<Truck> getTruckById(long id) throws Exception {
        try {
            return truckOrderRepository.findById(id);
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public Truck updateTruckPhoto(TruckPhoto truckPhoto) throws Exception {
        try {
            Optional<Truck> truck= getTruckById(truckPhoto.getId());
            if(truck.isPresent()) {
                truck.get().setPhoto(truckPhoto.getPhoto());
                return truckOrderRepository.save(truck.get());
            }
            throw new Exception("Invalid truck ID");
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }

    public List<Truck> getAllTrucksBySearch(SearchTruck truck) throws Exception {
        try {

            int year = truck.getDropOffTime().getYear();
            int month = truck.getDropOffTime().getMonthValue();
            int day = truck.getDropOffTime().getDayOfMonth();

            String monthConverted = ""+month;
            if(month<10){
                monthConverted = "0"+monthConverted;
            }

            String dropoff = year + "-" + monthConverted + "-" + day;



            int year1 = truck.getPickUpTime().getYear();
            int month1 = truck.getPickUpTime().getMonthValue();
            int day1 = truck.getPickUpTime().getDayOfMonth();

            String monthConverted1 = ""+month1;
            if(month1<10){
                monthConverted1 = "0"+monthConverted1;
            }

            String pickup = year1 + "-" + monthConverted1 + "-" + day1;


            return truckOrderRepository.findByTruckSearch(truck.getPickUpLocation(),pickup,truck.getDropOffLocation(),dropoff);
        }
        catch(Exception err) {
            throw new Exception(err.getMessage());
        }

    }
}
