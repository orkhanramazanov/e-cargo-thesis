package com.orkhan.web.out.ecargo.controller;

import com.orkhan.web.out.ecargo.entity.Order;
import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.message.request.OrderSubmitForm;
import com.orkhan.web.out.ecargo.message.request.TruckDriverOrderForm;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.service.OrderService;
import com.orkhan.web.out.ecargo.service.TruckRequestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderControllerTests {

    @InjectMocks
    OrderController orderController;

    @Mock
    TruckRequestService truckRequestService;

    @Mock
    OrderService orderService;

    @Before
    public void init() {
        truckRequestService = mock(TruckRequestService.class);
        orderService = mock(OrderService.class);
    }

    @Test
    public void createOrder() throws Exception {
        Truck truck = new Truck();
        TruckDriverOrderForm truckDriverOrderForm = new TruckDriverOrderForm();
        truckDriverOrderForm.setArrivalLocation("test");
        truckDriverOrderForm.setTruckType("test");
        truckDriverOrderForm.setArrivalTime(LocalDateTime.now());
        truckDriverOrderForm.setTruckName("test");
        truckDriverOrderForm.setDepartureLocation("test");
        truckDriverOrderForm.setDepartureTime(LocalDateTime.now());
        truckDriverOrderForm.setPricePerKg(1.0);
        truckDriverOrderForm.setPhoto("test");
        truckDriverOrderForm.setFreeSpace(1.0);
        when(truckRequestService.saveTruckOrder(truck,1L)).thenReturn(truck);
        ResponseEntity<?> responseMessage = orderController.createOrder(1L,truckDriverOrderForm);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void completeOrderByDriver() throws Exception {
        when(orderService.completeOrderByDriver(1L)).thenReturn(new ResponseMessage("Done"));
        ResponseEntity<?> responseMessage = orderController.completeOrderByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void acceptOrderByDriver() throws Exception {
        when(orderService.acceptOrderByDriver(1L)).thenReturn(new ResponseMessage("Done"));
        ResponseEntity<?> responseMessage = orderController.acceptOrderByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void cancelOrderByDriver() throws Exception {
        when(orderService.cancelOrderByDriver(1L)).thenReturn(new ResponseMessage("Done"));
        ResponseEntity<?> responseMessage = orderController.cancelOrderByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getActiveOrders() throws Exception {
        when(orderService.getActiveOrders(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getActiveOrders(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getAllOrders();
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getOrderByDriver() throws Exception {
        when(orderService.getOrdersByTruckDriver(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getOrderByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getCompletedOrderByDriver() throws Exception {
        when(orderService.getCompletedOrdersByDriver(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getCompletedOrderByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getOrderById() throws Exception {
        when(orderService.getOrderById(1L)).thenReturn(java.util.Optional.of(new Order()));
        ResponseEntity<?> responseMessage = orderController.getOrderById(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void cancelOrder() throws Exception {
        when(orderService.cancelOrder(1L)).thenReturn(new Order());
        ResponseEntity<?> responseMessage = orderController.cancelOrder(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getInactiveOrders() throws Exception {
        when(orderService.getInactiveOrders(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getInactiveOrders(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void updateOrder() throws Exception {
        Order order = new Order();
        OrderSubmitForm orderSubmitForm  = new OrderSubmitForm();
        when(orderService.updateOrder(1L,order)).thenReturn(order);
        ResponseEntity<?> responseMessage = orderController.updateOrder(1L,orderSubmitForm);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getCompletedOrders() throws Exception {
        when(orderService.getCompletedUsers(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = orderController.getCompletedOrders(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }


    @Test
    public void createClientOrder() throws Exception {
        Order order1 = new Order();
        OrderSubmitForm order = new OrderSubmitForm();
        order.setDepartureTime(LocalDateTime.now());
        order.setCity("test");
        order.setNationalityName("test");
        order.setLastName("test");
        order.setResidence("test");
        order.setZip("test");
        order.setStreet("test");
        order.setPhone("test");
        order.setPackageType("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("test");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setIdNumber("test");
        order.setDiameter(1.0);
        order.setTotalPrice(1.0);
        order.setPaymentType("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setDepartureTime(LocalDateTime.now());
        order.setItemDiameter(1.0);
        order.setMotherMaidenName("test");
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1");
        order.setTruckId("1");
        when(orderService.addNewOrder(order1)).thenReturn(order1);
        ResponseEntity<?> responseMessage = orderController.createClientOrder(order);
        assertEquals(responseMessage.getStatusCodeValue(),200);
        assertEquals(order.getDiameter().toString(),"1.0");

    }




}
