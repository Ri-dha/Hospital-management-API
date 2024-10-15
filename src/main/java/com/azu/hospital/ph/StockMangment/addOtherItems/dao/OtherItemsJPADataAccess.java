package com.azu.hospital.ph.StockMangment.addOtherItems.dao;

import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("otherItemJPA")
@Qualifier("otherItemJPA")
public class OtherItemsJPADataAccess implements OtherItemDao {

    private final OtherItemsRepository otherItemsRepository;

    @Autowired
    public OtherItemsJPADataAccess(OtherItemsRepository otherItemsRepository) {
        this.otherItemsRepository = otherItemsRepository;
    }

    @Override
    public Page<OtherItems> findByItemSortedBy(String itemName, String itemCompany, String itemBarcode, DeviceType type, Pageable pageable) {
        return otherItemsRepository.findByItemSortedBy( itemName, itemCompany, itemBarcode, type, pageable);
    }

    @Override
    public Optional<OtherItems> selectById(Long itemId) {
        return otherItemsRepository.findById(itemId);
    }

    @Override
    public OtherItems createItem(OtherItems otherItems) {
        return otherItemsRepository.save(otherItems);
    }

    @Override
    public void updateItems(OtherItems updateRequest) {
         otherItemsRepository.save(updateRequest);
    }

    @Override
    public boolean existsById(Long itemId) {
        return otherItemsRepository.existsById(itemId);
    }

    @Override
    public boolean existByItemBarcode(String itemBarcode) {
        return otherItemsRepository.existByItemBarcode(itemBarcode);
    }

    @Override
    public void deleteItemById(Long itemId) {
         otherItemsRepository.deleteById(itemId);
    }

    @Override
    public List<OtherItems> getItemsListByBillId(Integer billId) {
        return otherItemsRepository.getItemsListByBillId(Long.valueOf(billId));
    }
}
