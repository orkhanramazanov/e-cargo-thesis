package com.orkhan.web.out.ecargo.security;

import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import com.orkhan.web.out.ecargo.security.services.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailsTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void userDetailsTest() {
        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test");
        user.setFirstname("testtest");
        user.setPassword("testtest");

        when(userRepository.findByUsername("test")).thenReturn(java.util.Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername("test");
        assertEquals(userDetails.getUsername(),"test");

    }

}
