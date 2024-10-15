package com.azu.hospital.ph.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PaginationInfo {
    private long totalElements;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private String previousPageUrl;
    private String nextPageUrl;

    public PaginationInfo() {
    }

    public PaginationInfo getPaginationInfo(Page<?> page) {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setTotalElements(page.getTotalElements());
        paginationInfo.setPageNumber(page.getNumber());
        paginationInfo.setPageSize(page.getSize());
        paginationInfo.setTotalPages(page.getTotalPages());
        paginationInfo.setHasPreviousPage(page.hasPrevious());
        paginationInfo.setHasNextPage(page.hasNext());

        if (page.hasPrevious()) {
            int previousPageNumber = page.getNumber() - 1;
            String previousPageUrl = "https://web.azu-app.com?page=" + previousPageNumber + "&size=" + page.getSize();
            paginationInfo.setPreviousPageUrl(previousPageUrl);
        }

        if (page.hasNext()) {
            int nextPageNumber = page.getNumber() + 1;
            String nextPageUrl = "https://web.azu-app.com?page=" + nextPageNumber + "&size=" + page.getSize();
            paginationInfo.setNextPageUrl(nextPageUrl);
        }

        return paginationInfo;
    }
}
