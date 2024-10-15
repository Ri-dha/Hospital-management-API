package com.azu.hospital.ph.StockMangment.AddPharmacy;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PharmacyServices {
    private final PharmacyRepository pharmacyRepository;
    private final ExceptionsMessageReturn messageReturn;

    public PharmacyServices(PharmacyRepository pharmacyRepository, ExceptionsMessageReturn messageReturn) {
        this.pharmacyRepository = pharmacyRepository;
        this.messageReturn = messageReturn;
    }


    public void addPharmacy(Pharmacy pharmacy){
        pharmacyRepository.save(pharmacy);

    }

    public List<Pharmacy> getPharmacy() {
        return pharmacyRepository.findAll();
    }
    public void deletePharmacy(Integer pharmacyId){
        checkPharmacyId(pharmacyId);
        pharmacyRepository.deleteById(pharmacyId);
    }

    public void updatePharmacy(Integer pharmacyId, Pharmacy pharmacy){
        checkPharmacyId(pharmacyId);
        pharmacyRepository.save(pharmacy);

    }

    public void checkPharmacyId( Integer pharmacyId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        if (!pharmacyRepository.existsById(pharmacyId)){
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound") + pharmacyId
            );
        }

    }
}
