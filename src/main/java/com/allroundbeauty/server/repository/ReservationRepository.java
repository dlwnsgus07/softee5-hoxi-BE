package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
