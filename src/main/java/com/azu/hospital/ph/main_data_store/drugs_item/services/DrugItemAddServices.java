package com.azu.hospital.ph.main_data_store.drugs_item.services;

import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.entringItemList.entity.StockBill;
import com.azu.hospital.ph.StockMangment.entringItemList.dao.StockBillDao;
import com.azu.hospital.ph.main_data_store.drugs_item.dao.DrugItemDao;
import com.azu.hospital.ph.main_data_store.drugs_item.entity.DrugsItem;
import com.azu.hospital.ph.main_data_store.drugs_item.request.DrugItemRegistrationRequest;
import com.azu.hospital.ph.main_data_store.item_archive.dao.DrugItemArchiveDao;
import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao.DrugExpanseInStoreDao;
import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.image.ImageService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
public class DrugItemAddServices {
    private final ExceptionsMessageReturn messageReturn;

    private final DrugItemDao dao;
    private final StockBillDao stockBillDao;
    private final ImageService imageService;
    private final DrugItemArchiveDao archiveDao;
    private final DrugExpanseInStoreDao expanseDao;

    @Autowired
    public DrugItemAddServices(ExceptionsMessageReturn messageReturn, @Qualifier("DrugItemJpa") DrugItemDao dao,
                               @Qualifier("BillJpa") StockBillDao stockBillDao,
                               ImageService imageService,
                               @Qualifier("DrugItemArchiveJpa")DrugItemArchiveDao archiveDao,
                               @Qualifier("DrugExpanseInStoreJpa") DrugExpanseInStoreDao expanseDao) {
        this.messageReturn = messageReturn;
        this.dao = dao;
        this.stockBillDao = stockBillDao;
        this.imageService = imageService;
        this.archiveDao = archiveDao;
        this.expanseDao = expanseDao;
    }

    @Transactional
    public void addNewDrugItem(
            @RequestParam Long billId,
            @ModelAttribute DrugItemRegistrationRequest request
    ) throws IOException {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

//        if (dao.findDrugByBarcode(request.barcode()).isPresent()) {
//            throw new DuplicateResourceException(
//                    messages.getString("alreadyExist")
//            );
//        }
//        if (Objects.equals(request.drugTradeName().toLowerCase(), request.drugScientificName().toLowerCase())) {
//            throw new DuplicateResourceException(
//                    messages.getString("cannotAccept")
//            );
//        }
        DrugsItem newDrug = createNewDrug(request);

        DrugItemArchive archive = addDrugItemArchive(request, newDrug);
        newDrug.setDrugItemArchive(archive);
        DrugExpanseTable expanseTable = new DrugExpanseTable();
        expanseTable.setDrugsItem(newDrug);
        expanseDao.addDrugsToExpanseInStore(expanseTable);
        dao.insertDrug(newDrug);
    }

    @NotNull
    private DrugItemArchive addDrugItemArchive(DrugItemRegistrationRequest request, DrugsItem newDrug) {
        DrugItemArchive archive = new DrugItemArchive
                .DrugItemArchiveBuilder()
                .itemName(request.drugTradeName())
                .drugScientificName(request.drugScientificName())
                .itemCompany(request.drugCompany())
                .dose(request.dose())
                .barcode(request.barcode())
                .price(request.price())
                .type(request.type())
                .build();
        archive.setDrugsItem(newDrug);
        archiveDao.addDrugToArchives(archive);
        return archive;
    }


    private DrugsItem createNewDrug(DrugItemRegistrationRequest request) throws IOException {
        DrugsItem newDrug = DrugsItem
                .builder()
                .itemName(request.drugTradeName())
                .itemCompany(request.drugCompany())
                .expDate(request.expDate())
                .proDate(request.proDate())
                .description(request.description())
                .barcode(request.barcode())
                .imageUrls(request.drugImageUrls()==null?null:imageService.saveImage(request.drugImageUrls()))
                .exchange(request.exchange())
                .typeOfCurrency(request.typOfCurrency())
                .quantity(request.quantity())
                .drugScientificName(request.drugScientificName())
                .dose(request.dose())
                .type(request.type())
                .drugBonus(request.drugBonus())
                .ndcNumber(request.ndcNumber())
                .price(request.price())
                .drugSellingPrice(request.drugSellingPrice())
                .isBack(false)
                .build();
        
        return newDrug;
    }


    public void addDrugByBarcode(
            @RequestParam("barcode") String barcode,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("isBack") Boolean isBack
    ){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        DrugsItem drugsItem = dao.findDrugByBarcode(barcode)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("DrugsItemNotFound") + " " + barcode
                        )
                );
        int existQuantity = drugsItem.getQuantity();
        if(!isBack){
            drugsItem.setQuantity(quantity + existQuantity);
        }if (isBack){
            drugsItem.setQuantity(quantity + existQuantity);
            drugsItem.setIsBack(true);
        }
        dao.updateDrug(drugsItem);

    }





    /**
     * treat if the drug is not finished and new drug coming with new expire date which one of the drugs expire should
     * notify.
     * <p>
     *     there is some items coming without expire date or Scientific name or trade name or company name.
     *     <p>
     *
     *         <p>
     *           there is some items coming with tow different barcode.
     *           <p>
     *
     *
     *               <p>
     *                   there is some items coming with the same barcode and different expire date for same company .
     *                   <p>
     * */

}
