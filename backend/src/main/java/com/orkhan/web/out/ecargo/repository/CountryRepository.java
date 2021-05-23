package com.orkhan.web.out.ecargo.repository;

import com.orkhan.web.out.ecargo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    @Query(value = "Select * from countries WHERE name = ?1",nativeQuery = true)
    Country getCountryByName(String name);
}
