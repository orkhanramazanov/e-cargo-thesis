package com.orkhan.web.out.ecargo.service;


import com.orkhan.web.out.ecargo.entity.Order;
import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.repository.ClientOrderRepository;
import com.orkhan.web.out.ecargo.repository.TruckOrderRepository;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage mimeMessage;

    @InjectMocks
    OrderService orderService;

    @Mock
    UserRepository userRepository;

    @Mock
    ClientOrderRepository clientOrderRepository;

    @Mock
    TruckOrderRepository truckOrderRepository;

    @Mock
    private MailService mailService;

    @Before
    public void init() {
        mailService = mock(MailService.class);
    }





    @Test
    public void addNewOrder() throws Exception {
        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.save(order)).thenReturn(order);
        Order order2 = orderService.addNewOrder(order);
        assertEquals(order2.getStatus(),order.getStatus());
    }

    @Test
    public void getActiveOrders() throws Exception {
        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findActiveOrderByClient(1L)).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getActiveOrders(1L);
        assertEquals(orders.get(0),order);


    }

    @Test
    public void  getCompletedOrdersByDriver() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findCompletedOrderByDriver(1L)).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getCompletedOrdersByDriver(1L);
        assertEquals(orders.get(0),order);

    }

    @Test
    public void  getOrdersByTruckDriver() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findOrderByTruckDriverId(1L)).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getOrdersByTruckDriver(1L);
        assertEquals(orders.get(0),order);

    }


    @Test
    public void  getAllOrders() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findAll()).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(orders.get(0),order);

    }

    @Test
    public void  getCompletedUsers() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findCompletedOrderByClient(1L)).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getCompletedUsers(1L);
        assertEquals(orders.get(0),order);

    }

    @Test
    public void  getInactiveOrders() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);

        when(clientOrderRepository.findInActiveOrderByClient(1L)).thenReturn(new ArrayList<Order>(Arrays.asList(order)));

        List<Order> orders = orderService.getInactiveOrders(1L);
        assertEquals(orders.get(0),order);

    }

    @Test
    public void  cancelOrderByDriver() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        when(clientOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        ResponseMessage responseMessage = orderService.cancelOrderByDriver(1L);
        assertEquals(responseMessage.getMessage(),"Order Cancelled");

    }

    @Test
    public void  cancelOrderByDriverReturnsErrorOnIncorrectOrderID() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        Order order2=null;

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(order2));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try{
            ResponseMessage responseMessage = orderService.cancelOrderByDriver(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Incorrect Order Id");
        }



    }

    @Test
    public void  acceptOrderByDriver() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        when(clientOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        ResponseMessage responseMessage = orderService.acceptOrderByDriver(1L);
        assertEquals(responseMessage.getMessage(),"Order Accepted");

    }

    @Test
    public void  acceptOrderByDriverReturnsErrorOnIncorrectOrderID() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        Order order2=null;

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(order2));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try{
            ResponseMessage responseMessage = orderService.acceptOrderByDriver(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Incorrect Order Id");
        }



    }

    @Test
    public void  acceptOrderByDriverReturnsErrorOnSpaceExceed() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


        Truck truck = new Truck();
        truck.setTruckType("test");
        truck.setTruckName("test");
        truck.setArrivalLocation("test");
        truck.setTruckDriverId(1L);
        truck.setArrivalTime(LocalDateTime.now());
        truck.setDepartureTime(LocalDateTime.now());
        truck.setArrivalLocation("test");
        truck.setDepartureLocation("test");
        truck.setFreeSpace(0.0);
        truck.setPhoto("test");
        truck.setPricePerKg(12.0);

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try{
            ResponseMessage responseMessage = orderService.acceptOrderByDriver(1L);
            assertEquals(responseMessage.getMessage(),"Order Space Exceeds Current Capacity");
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Incorrect Order Id");
        }



    }


    @Test
    public void  completeOrderByDriver() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        when(clientOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        ResponseMessage responseMessage = orderService.completeOrderByDriver(1L);
        assertEquals(responseMessage.getMessage(),"Order Completed");

    }

    @Test
    public void completeOrderByDriverReturnsErrorOnIncorrectOrderID() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1");


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
        Order order2=null;

        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(order2));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try{
            ResponseMessage responseMessage = orderService.completeOrderByDriver(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Incorrect Order Id");
        }



    }


    @Test
    public void  updateOrder() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");


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

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("tests");


        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(clientOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        Order responseMessage = orderService.updateOrder(1L,order);
        assertEquals(order.getCity(),responseMessage.getCity());

    }
    @Test
    public void  updateOrderReturnsError() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");


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

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("tests");

        Order order1=null;


        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(order1));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try {
            Order responseMessage = orderService.updateOrder(1L,order);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Not found order");
        }



    }

    @Test
    public void getOrderById() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");

        when(clientOrderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> order1= orderService.getOrderById(1L);
        assertEquals(order1.get().getCity(),order.getCity());

    }

    @Test
    public void getOrderByIdReturnsError() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");

        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        try{
            Optional<Order> order1= orderService.getOrderById(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Not found order");
        }



    }

    @Test
    public void  cancelOrder() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");


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

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("tests");


        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try {
            Order responseMessage = orderService.cancelOrder(1L);
            assertEquals(responseMessage.getCity(),order.getCity());
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Not found order");
        }



    }


    @Test
    public void  cancelOrderReturnsError() throws Exception {

        Order order = new Order();
        order.setUpdated(LocalDateTime.now());
        order.setStatus("New");
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");


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

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("tests");

        Order order1=null;


        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(truckOrderRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(truckOrderRepository.save(truck)).thenReturn(truck);
        when(clientOrderRepository.findById(1L)).thenReturn(Optional.ofNullable(order1));
        when(clientOrderRepository.save(order)).thenReturn(order);

        when(mailService.sendMail(order.getEmail(), MessageFormat.format("Hello , Your order with ID {0} has been cancelled by The truck driver. Please contact them. Thankyou", order.getId()),"Order Cancelled")).thenReturn(true);
        try {
            Order responseMessage = orderService.cancelOrder(1L);
            assertEquals(responseMessage.getCity(),order.getCity());
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Not found order");
        }



    }














}
