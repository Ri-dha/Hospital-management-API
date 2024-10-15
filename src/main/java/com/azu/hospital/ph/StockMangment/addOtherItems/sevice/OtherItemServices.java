package com.azu.hospital.ph.StockMangment.addOtherItems.sevice;


import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.dto.OtherItemDtoMapper;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.request.OtherItemRegistrationRequest;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.DeviceType;
import com.azu.hospital.utils.enums.OtherItemRequest.OtherItemRequestPlaces;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OtherItemServices {
    private final OtherItemDao otherItemDao;
    private final OtherItemDtoMapper otherItemDtoMapper;
    private final StockBillDao stockBillDao;
    private final ImageService imageService;
    private final DeviceExistDao deviceExistDao;
    private final DeviceExpanseDao expanseDao;

    private final FileService fileService;

    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public OtherItemServices(@Qualifier("otherItemJPA")
                             OtherItemDao otherItemDao,
                             OtherItemDtoMapper otherItemDtoMapper,
                             StockBillDao stockBillDao, ImageService imageService,
                             @Qualifier("DeviceExistJpa") DeviceExistDao deviceExistDao,
                             @Qualifier("DeviceExpanseJpa") DeviceExpanseDao expanseDao,
                             FileService fileService,
                             ExceptionsMessageReturn messageReturn) {
        this.otherItemDao = otherItemDao;
        this.otherItemDtoMapper = otherItemDtoMapper;
        this.stockBillDao = stockBillDao;
        this.imageService = imageService;
        this.deviceExistDao = deviceExistDao;
        this.expanseDao = expanseDao;
        this.fileService = fileService;
        this.messageReturn = messageReturn;
    }

//**************************************************************Create***********************************************\\
   @Transactional()
    public void addNewItem(@RequestParam Long billId,
                           OtherItemRegistrationRequest registrationRequest
    )throws IOException {
       Locale locale = messageReturn.getMessageLocally();
       ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

       StockBill stockBill = stockBillDao.getBillById(billId)
               .orElseThrow(
                       () -> new ResourceNotFoundException(
                            messages.getString("stockBillNotFound") + billId
                       )
               );


       String itemBarcode = registrationRequest.getItemBarcode();
//       if (otherItemDao.existByItemBarcode(itemBarcode)) {
//           throw new DuplicateResourceException(
//                      messages.getString("barCodeAlreadyExist") + " " + itemBarcode
//           );
//       }
        String imageUrl;
       if (registrationRequest.getItemImageUrl() != null) {
       imageUrl = imageService.saveImage(registrationRequest.getItemImageUrl());

       }
         else {
              imageUrl = null;
         }
       List<String> files;
       if (registrationRequest.getFiles() != null) {
           files = fileService.saveFiles(registrationRequest.getFiles());
       }else {
           return;
       }

       OtherItems otherItems = new OtherItems(
               registrationRequest.getItemName(),
               registrationRequest.getItemCompany(),
               registrationRequest.getItemQuantity(),
               registrationRequest.getItemBuyingPrice(),
               registrationRequest.getItemBarcode(),
               registrationRequest.getItemDescription(),
               registrationRequest.getSerialNumber(),
               registrationRequest.getItemProDate(),
               registrationRequest.getDeviceWarrantyDate(),
               registrationRequest.getDeviceType(),
               registrationRequest.getBonus(),
               imageUrl,
               files
       );
       otherItems.setBill(stockBill);
       stockBill.setItems(List.of(otherItems));
       otherItems = otherItemDao.createItem(otherItems);

       DeviceExistsTable table = getDeviceExistsTable(registrationRequest, otherItems);
       getDeviceExpanseTable(table);
   }

    private void getDeviceExpanseTable(DeviceExistsTable table) {
        DeviceExpanseTable expanseTable = new DeviceExpanseTable();
        expanseTable.setDeviceExistsTable(table);
        expanseTable.setDevicePlace(OtherItemRequestPlaces.Ward);
        expanseDao.addNewDeviceExpanseTable(expanseTable);
        table.setExpanseTable(expanseTable);
    }

    @NotNull
    private DeviceExistsTable getDeviceExistsTable(OtherItemRegistrationRequest registrationRequest, OtherItems otherItems) {
        DeviceExistsTable table = new DeviceExistsTable();
        table.setType(registrationRequest.getDeviceType());
        table.setQuantity(registrationRequest.getItemQuantity());
        table.setDeviceDetails(otherItems);
        otherItems.setDeviceExistsTable(table);
        deviceExistDao.addNewDevice(table);
        return table;
    }

//**************************************************************Read***********************************************\\

    public Page<OtherItemDto> getAllItems(
            @RequestParam (required = false) String itemName,
            @RequestParam (required = false) String itemCompany,
            @RequestParam (required = false) String itemBarcode,
            @RequestParam (required = false) DeviceType type,
                                          Pageable pageable) {



        Page<OtherItems> otherItemsPage = otherItemDao.findByItemSortedBy(
                itemName, itemCompany, itemBarcode, type, pageable
        );

        return otherItemsPage.map(otherItemDtoMapper);
    }



    public OtherItemDto getItemById(@PathVariable Long itemId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return otherItemDao.selectById(itemId)
                .map(otherItemDtoMapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + itemId
                        )
                );

    }

//**************************************************************Delete***********************************************\\
// TODO: 11/23/2023 error handling route

    @Transactional
    public void deleteItemById(Long itemId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        OtherItems item = otherItemDao.selectById(itemId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + itemId
                        )
                );
        if (item.getFilesUrls() != null){
            fileService.deleteFiles(item.getFilesUrls());
        }
        otherItemDao.deleteItemById(itemId);
    }
//**************************************************************Other***********************************************\\


}
