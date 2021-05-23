package com.orkhan.web.out.ecargo.entity;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountryTests {

    @Test
    public  void createCountry() {
        Country country = new Country();
        country.setName("test");
        country.setId(1L);
        assertEquals(country.getName(),"test");
        assertEquals(country.getId().toString(),"1");
    }
}
