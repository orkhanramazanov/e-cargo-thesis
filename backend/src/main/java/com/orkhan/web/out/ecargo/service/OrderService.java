package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.Order;
import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.repository.ClientOrderRepository;
import com.orkhan.web.out.ecargo.repository.TruckOrderRepository;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TruckOrderRepository truckOrderRepository;

    @Autowired
    private MailService mailService;

    public OrderService() {

    }


    public Order addNewOrder(Order order) throws Exception {

        try {
            order.setStatus("Processing");
            order.setUpdated(LocalDateTime.now());
            return clientOrderRepository.save(order);
        }
        catch(Exception error) {
            throw new Exception("An error occured");
        }

    }

    public List<Order> getActiveOrders(long id) throws Exception {

        try {
            return clientOrderRepository.findActiveOrderByClient(id);
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public List<Order> getCompletedOrdersByDriver(long id) throws Exception {

        try {
            return clientOrderRepository.findCompletedOrderByDriver(id);
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public List<Order> getOrdersByTruckDriver(long id) throws Exception {

        try {
            return clientOrderRepository.findOrderByTruckDriverId(id);
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public List<Order> getAllOrders() throws Exception {

        try {
            return clientOrderRepository.findAll();
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public Order updateOrder(long id, Order updatedOrder) throws Exception {

        try {
            Optional<Order> order = clientOrderRepository.findById(id);
            if(!order.isEmpty())
            {
                Optional<Truck> truck = truckOrderRepository.findById(Long.parseLong(order.get().getTruckId()));
                Double freeSpace = truck.get().getFreeSpace();
                Double currentSpace = order.get().getItemWeight() * order.get().getItemQuantity();

                freeSpace += currentSpace;
                truck.get().setFreeSpace(freeSpace);
                truckOrderRepository.save(truck.get());

                updatedOrder.setStatus("Processing");
                updatedOrder.setId(order.get().getId());
                updatedOrder.setUpdated(LocalDateTime.now());
                String text = MessageFormat.format("Hello! \n Order with ID {0} has been updated by the client.Please contact them. \n Thank you! \n E-Cargo services!", order.get().getId());
                Optional<User> user= userRepository.findById(Long.parseLong(order.get().getTruckDriverId()));
                mailService.sendMail(user.get().getEmail(),text,"Order Updated");
                return clientOrderRepository.save(updatedOrder);
            } else {
                throw new NotFoundException("Not found order");
            }
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }


    public Optional<Order> getOrderById(long id) throws Exception {

        try {
            Optional<Order> order = clientOrderRepository.findById(id);
            if(!order.isEmpty())
            {
                return order;
            } else {
                throw new NotFoundException("Not found order");
            }
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public Order cancelOrder(long id) throws Exception {

        try {
            Optional<Order> order = clientOrderRepository.findById(id);
            if(!order.isEmpty())
            {
                Optional<Truck> truck = truckOrderRepository.findById(Long.parseLong(order.get().getTruckId()));
                truck.get().setFreeSpace(truck.get().getFreeSpace() + (order.get().getItemWeight() * order.get().getItemQuantity()));
                truckOrderRepository.save(truck.get());
                order.get().setStatus("Cancelled");
                order.get().setUpdated(LocalDateTime.now());
                String text = MessageFormat.format("Hello! \n Order with ID {0} has been cancelled by the client.Please contact them. \n Thank you! \n E-Cargo services!", order.get().getId());
                Optional<User> user= userRepository.findById(Long.parseLong(order.get().getTruckDriverId()));
                mailService.sendMail(user.get().getEmail(),text,"Order Cancelled");
                return clientOrderRepository.save(order.get());
            } else {
                throw new NotFoundException("Not found order");
            }
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public List<Order> getCompletedUsers(long id) throws Exception {

        try {
            return clientOrderRepository.findCompletedOrderByClient(id);
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public List<Order> getInactiveOrders(long id) throws Exception {

        try {
            return clientOrderRepository.findInActiveOrderByClient(id);
        }
        catch(Exception error) {
            throw new Exception("An error occured");
        }

    }



    public ResponseMessage cancelOrderByDriver(long id) throws Exception {

        try {
            Optional<Order> order=  clientOrderRepository.findById(id);
            if(order.isPresent())
            {
                Optional<Truck> truck = truckOrderRepository.findById(Long.parseLong(order.get().getTruckId()));
                truck.get().setFreeSpace(truck.get().getFreeSpace() + (order.get().getItemWeight() * order.get().getItemQuantity()));
                truckOrderRepository.save(truck.get());
                order.get().setStatus("Cancelled");
                order.get().setUpdated(LocalDateTime.now());
                clientOrderRepository.save(order.get());

                String text = MessageFormat.format("Hello , Your order with ID {0} has been cancelled by the truck driver. Please contact them. \n Thank you!", order.get().getId());
                mailService.sendMail(order.get().getEmail(),text,"Order Cancelled");

                return new ResponseMessage("Order Cancelled");
            }
            throw new Exception("Incorrect Order Id");
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }

    public ResponseMessage acceptOrderByDriver(long id) throws Exception {

        try {
            Optional<Order> order=  clientOrderRepository.findById(id);
            if(order.isPresent())
            {
                Optional<Truck> truck1 = truckOrderRepository.findById(Long.parseLong(order.get().getTruckId()));
                Double freeSpace1 = truck1.get().getFreeSpace();
                if(freeSpace1 < (order.get().getItemWeight() * order.get().getItemQuantity()))
                {
                    return new ResponseMessage("Order Space Exceeds Current Capacity");
                }
                order.get().setStatus("Accepted");
                order.get().setUpdated(LocalDateTime.now());
                clientOrderRepository.save(order.get());

                Optional<Truck> truck = truckOrderRepository.findById(Long.parseLong(order.get().getTruckId()));

                Double freeSpace = truck.get().getFreeSpace();
                Double newFreeSpace = freeSpace - (order.get().getItemWeight() * order.get().getItemQuantity());

                truck.get().setFreeSpace(newFreeSpace);
                truckOrderRepository.save(truck.get());

                String text = MessageFormat.format("Hello , Your order with ID {0} has been accepted by The truck driver. Please contact them. Thankyou", order.get().getId());
                mailService.sendMail(order.get().getEmail(),text,"Order Accepted");
                return new ResponseMessage("Order Accepted");
            }
            throw new Exception("Incorrect Order Id");
        }
        catch(Exception error) {
            throw new Exception(error.getMessage());
        }

    }

    public ResponseMessage completeOrderByDriver(long id) throws Exception {

        try {
            Optional<Order> order=  clientOrderRepository.findById(id);
            if(order.isPresent())
            {
                order.get().setStatus("Completed");
                order.get().setUpdated(LocalDateTime.now());
                clientOrderRepository.save(order.get());

                String text = MessageFormat.format("Hello , Your order with ID {0} has been completed by the truck driver. Thank you for using our service!", order.get().getId());
                mailService.sendMail(order.get().getEmail(),text,"Order Completed");

                return new ResponseMessage("Order Completed");
            }
            throw new Exception("Incorrect Order Id");
        }
        catch(Exception error) {

            throw new Exception(error.getMessage());
        }

    }
}
