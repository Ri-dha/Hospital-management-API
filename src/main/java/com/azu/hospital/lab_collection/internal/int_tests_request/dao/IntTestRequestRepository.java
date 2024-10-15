package com.azu.hospital.lab_collection.internal.int_tests_request.dao;

import com.azu.hospital.lab_collection.RequestCountData;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IntTestRequestRepository extends JpaRepository<IntTestRequest, Long> {


    List<IntTestRequest> getAllByInternalLabTestId(Long internalLabTest_id);

    List<IntTestRequest> findAllByInternalLabTest_Id(Long internalLabTest_id);

    @Query("SELECT new com.azu.hospital.lab_collection.RequestCountData(i" +
            ".testName, count(i.testName)) FROM IntTestRequest i group by i.testName order by count(i" +
            ".testName) DESC ")
    List<RequestCountData> getTop15By();
   @Query("SELECT itr FROM IntTestRequest itr LEFT JOIN FETCH itr.intTestResults WHERE itr.internalLabTest.id = :labTestId")
   List<IntTestRequest> findAllByInternalLabTestIdWithResults(@Param("labTestId") Long labTestId);


    @Query("SELECT itr FROM InternalLabTest itr WHERE itr.id = :testId")
    Optional<IntTestRequest> getAllByTestId(@Param("testId") Long testId);



    @Query("SELECT i from IntTestRequest i where i.internalLabTest.patient.id = :patientId and" +
            " i.state  = com.azu.hospital.lab_collection.internal.int_tests_request.util.IntTestRequestState.COMPLETED  and  " +
            " i.isArchived = false")
    List<IntTestRequest> getAllCompletedByPatientId(Long patientId);

    @Query("SELECT count (i) from IntTestRequest i where i.testNameForMainTest.testName = :testName AND i.internalLabTest.patient.id = :patientId and" +
            " i.internalLabTest.state  = com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum.Complete and" +
            " i.internalLabTest.isArchived = false")
    Integer countAllByPatientId(Long patientId,String testName);

}
