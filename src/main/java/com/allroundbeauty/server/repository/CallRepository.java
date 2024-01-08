package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.Call;
import com.allroundbeauty.server.domain.user.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, Long> {
}
