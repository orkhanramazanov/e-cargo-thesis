package com.orkhan.web.out.ecargo.repository;

import com.orkhan.web.out.ecargo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrderRepository extends JpaRepository<Order,Long> {

    @Query(
            value = "SELECT orders.* , truck.departure_time FROM orders  INNER JOIN truck  on truck.id=orders.truck_id WHERE orders.client_id = ?1 AND (orders.status = 'Processing' OR orders.status= 'Accepted')",
            nativeQuery = true)
    List<Order> findActiveOrderByClient(long id);

    @Query(
            value = "SELECT orders.* , truck.departure_time FROM orders  INNER JOIN truck  on truck.id=orders.truck_id WHERE orders.client_id = ?1 AND orders.status = 'Cancelled'",
            nativeQuery = true)
    List<Order> findInActiveOrderByClient(long id);

    @Query(
            value = "SELECT orders.* , truck.departure_time FROM orders  INNER JOIN truck  on truck.id=orders.truck_id WHERE orders.client_id = ?1 AND orders.status = 'Completed'",
            nativeQuery = true)
    List<Order> findCompletedOrderByClient(long id);

    @Query(value = "SELECT orders.* , truck.departure_time FROM orders  INNER JOIN truck  on truck.id=orders.truck_id WHERE orders.truck_driver_id = ?1 AND (orders.status = 'Processing' OR orders.status= 'Accepted'  OR orders.status= 'Cancelled')",nativeQuery = true)
    List<Order> findOrderByTruckDriverId(long id);

    @Query(
            value = "SELECT orders.* , truck.departure_time FROM orders  INNER JOIN truck  on truck.id=orders.truck_id WHERE orders.truck_driver_id = ?1 AND orders.status = 'Completed'",
            nativeQuery = true)
    List<Order> findCompletedOrderByDriver(long id);

}
