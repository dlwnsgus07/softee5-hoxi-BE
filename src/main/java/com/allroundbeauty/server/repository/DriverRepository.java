package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.user.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
