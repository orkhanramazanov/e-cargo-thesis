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
public class OrderTests {

    @Test
    public void createOrder() {

        LocalDateTime date= LocalDateTime.now();
        Order order = new Order();
        order.setUpdated(date);
        order.setStatus("test");
        order.setDepartureTime(date);
        order.setCity("test");
        order.setAdditionalInformation("test");
        order.setBirthPlace("test");
        order.setClientId("1L");
        order.setDateofBirth("test");
        order.setEmail("test");
        order.setFirstName("test");
        order.setId(1L);
        order.setIdNumber("test");
        order.setIdType("test");
        order.setItemCategory("test");
        order.setLastName("test");
        order.setDepartureTime(date);
        order.setItemDiameter(1.0);
        order.setItemHeight("test");
        order.setItemName("test");
        order.setCity("test");
        order.setItemLength(1.0);
        order.setItemQuantity(1.0);
        order.setItemWeight(1.0);
        order.setItemWidth(1.0);
        order.setTruckDriverId("1L");
        order.setTruckId("1L");
        order.setNationalityName("test");
        order.setMotherMaidenName("test");
        order.setPaymentType("test");
        order.setZip("test");
        order.setResidence("test");
        order.setStreet("test");
        order.setTotalPrice(1.0);
        order.setPhone("test");
        order.setPackageType("test");


        assertEquals( order.getUpdated(),date);
        assertEquals( order.getDepartureTime(),date);
        assertEquals(order.getCity(),"test");
        assertEquals(order.getAdditionalInformation(),"test");
        assertEquals(order.getBirthPlace(),"test");
        assertEquals(order.getClientId(),"1L");
        assertEquals(order.getDateofBirth(),"test");
        assertEquals(order.getEmail(),"test");
        assertEquals(order.getFirstName(),"test");
        assertEquals(order.getId().toString(),"1");
        assertEquals(order.getIdNumber(),"test");
        assertEquals(order.getIdType(),"test");
        assertEquals(order.getItemCategory(),"test");
        assertEquals(order.getDepartureTime(),date);
        assertEquals(order.getItemDiameter().toString(),"1.0");
        assertEquals(order.getItemHeight(),"test");
        assertEquals(order.getItemName(),"test");
        assertEquals(order.getItemLength().toString(),"1.0");
        assertEquals(order.getItemQuantity().toString(),"1.0");
        assertEquals(order.getItemWeight().toString(),"1.0");
        assertEquals(order.getItemWidth().toString(),"1.0");
        assertEquals(order.getTruckDriverId(),"1L");
        assertEquals(order.getNationalityName(),"test");
        assertEquals(order.getMotherMaidenName(),"test");
        assertEquals(order.getPackageType(),"test");
        assertEquals(order.getLastName(),"test");
        assertEquals(order.getResidence(),"test");
        assertEquals(order.getZip(),"test");
        assertEquals(order.getStreet(),"test");
        assertEquals(order.getPhone(),"test");
        assertEquals(order.getTotalPrice().toString(),"1.0");
        assertEquals(order.getPaymentType(),"test");






    }

}
