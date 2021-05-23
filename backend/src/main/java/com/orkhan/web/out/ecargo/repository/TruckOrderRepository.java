package com.orkhan.web.out.ecargo.repository;

import com.orkhan.web.out.ecargo.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckOrderRepository extends JpaRepository<Truck, Long> {
    @Override
    Optional<Truck> findById(Long aLong);

    @Query(
            value = "SELECT * FROM truck u WHERE u.truck_type in (:types)",
            nativeQuery = true)
    List<Truck> findByTruckType(@Param("types") List<String> types);

    @Query(
            value = "SELECT * FROM truck u WHERE u.truck_driver_id =?1",
            nativeQuery = true)
    List<Truck> findByTruckDriver(Long id);


    @Query(
            value = "SELECT * FROM truck u WHERE   u.departure_location = :departure_location AND u.arrival_location = :arrival_location AND DATE(u.departure_time) = :departure_time AND DATE(u.arrival_time) = :arrival_time",
            nativeQuery = true)
    List<Truck> findTruckBefore(@Param("departure_location") String pickuplocation,@Param("departure_time") String pickuptime , @Param("arrival_location") String dropofflocation, @Param("arrival_time") String dropofftime);



    @Query(
            value = "SELECT * FROM truck u WHERE u.free_space > 0",
            nativeQuery = true)
    List<Truck> findAllTrucks();

    @Query(
            value = "SELECT * FROM truck u WHERE   u.departure_location = :departure_location AND u.arrival_location = :arrival_location AND DATE(u.departure_time) >= :departure_time AND DATE(u.arrival_time) <= :arrival_time",
            nativeQuery = true)
    List<Truck> findByTruckSearch(@Param("departure_location") String pickuplocation,@Param("departure_time") String pickuptime , @Param("arrival_location") String dropofflocation, @Param("arrival_time") String dropofftime);


}
