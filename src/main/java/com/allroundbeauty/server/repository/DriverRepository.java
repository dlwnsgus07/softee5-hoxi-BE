package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.user.Driver;
import com.allroundbeauty.server.dto.driverInfo.DriverDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver save(Driver driver);
    @Query("select new com.allroundbeauty.server.dto.driverInfo.DriverDetailDto(d.name,d.phoneNumber,d.carType,d.carNumber,d.image)from Driver d where d.id = :id")
    DriverDetailDto findByDriverDetailDto(@Param("id") Long id);



}