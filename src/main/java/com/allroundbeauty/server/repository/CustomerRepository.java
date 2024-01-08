package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.controller.CustomerController;
import com.allroundbeauty.server.domain.user.Customer;
import com.allroundbeauty.server.domain.user.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
