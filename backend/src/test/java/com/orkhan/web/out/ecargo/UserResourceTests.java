package com.orkhan.web.out.ecargo;

import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.resources.UserListResource;
import com.orkhan.web.out.ecargo.resources.UserResource;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserResourceTests {


    @Test
    public void createsAResource() {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setPassword("test");
        user1.setId(1L);
        user1.setCountry("test");
        user1.setFirstname("test");
        user1.setSurname("test");
        user1.setPhone("test");
        user1.setRole("test");


        UserResource userResource = new UserResource(user1);
        assertEquals(userResource.getEmail(),user1.getEmail());
        assertEquals(userResource.getUsername(),user1.getUsername());
        assertEquals(userResource.getFirstname(),user1.getFirstname());
        assertEquals(userResource.getCountry(),user1.getCountry());
        assertEquals(userResource.getId(),user1.getId());
        assertEquals(userResource.getStatus(),user1.getStatus());
        assertEquals(userResource.getRole(),user1.getRole());
        assertEquals(userResource.getPhone(),user1.getPhone());
        assertEquals(userResource.getSurname(),user1.getSurname());
        assertEquals(userResource.getPassword(),user1.getPassword());
    }


    @Test
    public void setsAResource() {

        UserResource userResource = new UserResource();
        userResource.setEmail("test@test.com");
        userResource.setUsername("test");
        userResource.setPassword("test");
        userResource.setId(1L);
        userResource.setCountry("test");
        userResource.setFirstname("test");
        userResource.setSurname("test");
        userResource.setPhone("test");
        userResource.setRole("test");
        userResource.setStatus("test");

        UserListResource userListResource = new UserListResource();
        userListResource.setUserList(new ArrayList<UserResource>(Arrays.asList(userResource)));

    }
}
