package com.azu.hospital.ph.StockMangment.addOtherItems.sevice;

import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.addOtherItems.request.OtherItemUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.files.FileService;
import com.azu.hospital.utils.image.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service("UpdateService")
public class UpdateService {
    private final OtherItemDao otherItemDao;
    private final ImageService imageService;

    private final FileService fileService;
    private final ExceptionsMessageReturn messageReturn;

    public UpdateService(@Qualifier("otherItemJPA")
            OtherItemDao otherItemDao,
                         ImageService imageService, FileService fileService, ExceptionsMessageReturn messageReturn) {
        this.otherItemDao = otherItemDao;
        this.imageService = imageService;
        this.fileService = fileService;
        this.messageReturn = messageReturn;
    }


    public void updateItem(  @PathVariable Long itemId,
                                        @RequestBody OtherItemUpdateRequest updateRequest) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        OtherItems otherItems = otherItemDao.selectById(itemId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + itemId
                )
        );
        boolean changes = false;

        if (updateRequest.itemName() != null && !updateRequest.itemName().equals(otherItems.getItemName())) {
            otherItems.setItemName(updateRequest.itemName());
            changes = true;
        }if (updateRequest.itemCompany() != null && !updateRequest.itemCompany().equals(otherItems.getItemCompany())) {
            otherItems.setItemCompany(updateRequest.itemCompany());
            changes = true;
        }if (updateRequest.itemQuantity() != null && !updateRequest.itemQuantity().equals(otherItems.getItemQuantity())) {
            otherItems.setItemQuantity(updateRequest.itemQuantity());
            changes = true;
        }if (updateRequest.itemBuyingPrice() != null && !updateRequest.itemBuyingPrice().equals(otherItems.getItemBuyingPrice())) {
            otherItems.setItemBuyingPrice(updateRequest.itemBuyingPrice());
            changes = true;
        }if (updateRequest.itemBarcode() != null && !updateRequest.itemBarcode().equals(otherItems.getItemBarcode())) {
            otherItems.setItemBarcode(updateRequest.itemBarcode());
            changes = true;
        }if (updateRequest.itemDescription() != null && !updateRequest.itemDescription().equals(otherItems.getItemDescription())) {
            otherItems.setItemDescription(updateRequest.itemDescription());
            changes = true;
        }if (updateRequest.serialNumber() != null && !updateRequest.serialNumber().equals(otherItems.getSerialNumber())) {
            otherItems.setSerialNumber(updateRequest.serialNumber());
            changes = true;
        }if (updateRequest.deviceType() != null && !updateRequest.deviceType().equals(otherItems.getDeviceType())) {
            otherItems.setDeviceType(updateRequest.deviceType());
            changes = true;
        }if (updateRequest.bounce() != null && !updateRequest.bounce().equals(otherItems.getBounce())) {
            otherItems.setBounce(updateRequest.bounce());
            changes = true;
        }if (updateRequest.itemProDate() != null && !updateRequest.itemProDate().equals(otherItems.getItemProDate())) {
            otherItems.setItemProDate(updateRequest.itemProDate());
            changes = true;
        }if (updateRequest.deviceWarrantyDate() != null && !updateRequest.deviceWarrantyDate().equals(otherItems.getDeviceWarrantyDate())) {
            otherItems.setDeviceWarrantyDate(updateRequest.deviceWarrantyDate());
            changes = true;
        }if (updateRequest.itemImageUrl() != null ) {
            String imageUrl = imageService.saveImage(updateRequest.itemImageUrl());
            otherItems.setItemImageUrl(imageUrl);
            changes = true;
        }if(updateRequest.files() != null){
            List<String> files = fileService.saveFiles(updateRequest.files());
            otherItems.setFilesUrls(files);
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException(
                    messages.getString("noChanges"));
        }
        otherItemDao.updateItems(otherItems);

    };
}
