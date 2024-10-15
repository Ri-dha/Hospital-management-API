package com.azu.hospital.lab_collection.internal.int_test_result.request;

import com.azu.hospital.utils.files.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record InternalTestDataRequestList(
        Long testId,
        List<InternalTestResultDataRequest> results,
        List<MultipartFile> file,
        String note

) {
}
