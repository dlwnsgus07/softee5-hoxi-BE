package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
