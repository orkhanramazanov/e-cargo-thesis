package com.orkhan.web.out.ecargo.repository;

import java.util.List;
import java.util.Optional;

import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.resources.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);

    User findByEmail(String email);

    @Query("SELECT u.status FROM User u WHERE u.username=:username ")
    String findByStatus(@ Param("username")String username);
    
    @Query("SELECT u.id FROM User u WHERE u.username=:username")
    long getIdByUsername(@ Param("username") String username);

    @Query("SELECT u.firstname FROM User u WHERE u.username=:username")
    String getFirstName(@ Param("username") String username);
    
    public static final String FIND_USER = "SELECT id, name, username, email, password FROM users";

    @Query(value = FIND_USER, nativeQuery = true)
    public List<UserResource> findAllUsers();
}