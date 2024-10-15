package com.azu.hospital.ph.lab_inventory.lab_main_table.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dao.MainLabTubeStoreDao;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabTubeStoreDto;
import com.azu.hospital.ph.lab_inventory.lab_main_table.dto.MainLabTubeStoreDtoMapper;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainLabTubeStoreService {

    private final MainLabTubeStoreDao mainLabTubeStoreDao;
    private final MainLabTubeStoreDtoMapper storeDtoMapper;
    private final ExceptionsMessageReturn messageReturn;



    @Autowired
    public MainLabTubeStoreService(MainLabTubeStoreDao mainLabTubeStoreDao, MainLabTubeStoreDtoMapper storeDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.mainLabTubeStoreDao = mainLabTubeStoreDao;
        this.storeDtoMapper = storeDtoMapper;
        this.messageReturn = messageReturn;
    }


    public List<MainLabTubeStoreDto> getMainTubeById(@RequestParam(name = "id") Long id) {
        return mainLabTubeStoreDao.findMainLapTubeStoreById(id)
                .stream()
                .map(storeDtoMapper)
                .toList();
    }


    public Page<MainLabTubeStoreDto> getAllTube(Pageable pageable){
       return mainLabTubeStoreDao.getAllMainLapTubeStore(pageable)
                .map(storeDtoMapper);
// TODO: 1/25/2024 error
    }


    public void deleteById(@PathVariable("id") Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        mainLabTubeStoreDao.findMainLapTubeStoreById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("labTubeNotFound") + " " + id
                        )
                );
        mainLabTubeStoreDao.deleteMainLapTubeStore(id);
    }
}
