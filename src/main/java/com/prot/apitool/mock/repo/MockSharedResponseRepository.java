package com.prot.apitool.mock.repo;

import com.prot.apitool.mock.model.def.SharedApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockSharedResponseRepository extends JpaRepository<SharedApiResponse, Long> {
}
