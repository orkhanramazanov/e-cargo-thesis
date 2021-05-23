package com.orkhan.web.out.ecargo.dto;


import com.orkhan.web.out.ecargo.message.response.JwtResponse;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtDTOTest {

    @Test
    public void jwtResponse() {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("CLIENT_ROLE"));
        JwtResponse jwtResponse = new JwtResponse("test","test",authorities,1L,"test");
        jwtResponse.setFirstName("test");
        jwtResponse.setId(1L);
        jwtResponse.setAccessToken("test");
        jwtResponse.setTokenType("test");
        jwtResponse.setUsername("test");
        assertEquals(jwtResponse.getAccessToken(),"test");
        assertEquals(jwtResponse.getId(),1L);
        assertEquals(jwtResponse.getFirstName(),"test");
        assertEquals(jwtResponse.getAuthorities(),authorities);
        assertEquals(jwtResponse.getUsername(),"test");
        assertEquals(jwtResponse.getTokenType(),"test");



    }
}
