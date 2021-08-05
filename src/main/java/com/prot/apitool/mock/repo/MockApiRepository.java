package com.prot.apitool.mock.repo;

import com.prot.apitool.mock.model.def.MockApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MockApiRepository extends JpaRepository<MockApi, Long> {
    @Query(value = "SELECT ID, URL_PATTERN, HTTP_METHOD FROM MOCK_API", nativeQuery = true)
    List<Object[]> getAllLightApis();
// TODO: if returning MockApi, it contains other columns. Don't know why yet??? Because MockApi is a managed-entity, magic happens???
//    List<MockApi> getAllLightApis();
}