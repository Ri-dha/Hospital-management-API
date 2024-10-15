package com.azu.hospital.lab_collection.external.ext_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Transactional
public interface ExtTestRequestRepository extends JpaRepository<ExtTestRequest, Long> {


    List<ExtTestRequest> getAllByExternalLabTestId(Long externalLabTest_id);

    List<ExtTestRequest> findAllByExternalLabTest_Id(Long externalLabTest_id);

    @Query("SELECT new com.azu.hospital.lab_collection.RequestCountData(i" +
            ".labList.labName, count(i.labList)) FROM ExtTestRequest i group by i.labList.labName order by count(i" +
            ".labList) DESC ")
    List<RequestCountData> getTop15By();

}
