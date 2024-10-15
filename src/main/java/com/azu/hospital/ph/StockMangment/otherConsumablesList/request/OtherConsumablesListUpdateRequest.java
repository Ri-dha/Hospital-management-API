package com.azu.hospital.ph.StockMangment.otherConsumablesList.request;

import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.entity.OtherConsumables;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesRequest;

import java.util.List;

public record OtherConsumablesListUpdateRequest (

    List<OtherConsumablesRequest> requestList
)
{
}
