package com.allroundbeauty.server.repository;

import com.allroundbeauty.server.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {

}
