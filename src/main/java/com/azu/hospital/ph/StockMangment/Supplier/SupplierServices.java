package com.azu.hospital.ph.StockMangment.Supplier;

import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.RequestValidationException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierServices {
    private final SupplierDao supplierDao;
    private final SupplierDtoMapper supplierDtoMapper;
    private final SupplierRepository supplierRepository;

    public SupplierServices(@Qualifier("suppliersJpa")
                            SupplierDao supplierDao,
                            SupplierDtoMapper supplierDtoMapper,
                            SupplierRepository supplierRepository
    ) {
        this.supplierDao = supplierDao;
        this.supplierDtoMapper = supplierDtoMapper;
        this.supplierRepository = supplierRepository;
    }


    public Page<SupplierDto> findBySupplierSortedOrderBy(
            String supplierName,
            String supplierEmail,
            Pageable pageable
    ) {
        Page<Supplier> supplierPage = supplierRepository.findBySupplierSortedOrderBy(supplierName, supplierEmail, pageable);
        List<SupplierDto> supplierDtoList = supplierPage.map(supplierDtoMapper::apply).getContent();
        return new PageImpl<>(supplierDtoList, pageable, supplierPage.getTotalElements());
    }


    public SupplierDto getSupplierById(Integer supplierId){
        return supplierDao.findSupplierById(supplierId)
                .map(supplierDtoMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier With Id [%] not Found" .formatted(supplierId)
                        )
                );
    }

    public void CreateSupplier(SupplierRegistrationRequest request){
        String email = request.supplierEmail();
        if (supplierDao.supplierExistsByEmail(email)){
            throw new DuplicateResourceException(
                    "Email Already Taken"
            );
        }
        Supplier supplier = new Supplier(
                request.supplierName(),
                request.supplierEmail(),
                request.supplierPhone(),
                request.address()
        );
        supplierDao.insertSupplier(supplier);
    }

    public void updateSupplier(Integer supplierId, SupplierUpdateRequest request){
         Supplier supplier = supplierDao.findSupplierById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier With id [%s] not found".formatted(supplierId)
                ));
         boolean changes = false;
        if (request.supplierName() != null && !request.supplierName().equals(supplier.getSupplierName())){
            supplier.setSupplierName(request.supplierName());
            changes = true;
        }
        if (request.supplierEmail() != null && !request.supplierEmail().equals(supplier.getSupplierEmail())){
            supplier.setSupplierEmail(request.supplierEmail());
            changes = true;
        }
        if (request.supplierPhone() != null && !request.supplierPhone().equals(supplier.getSupplierPhone())){
            supplier.setSupplierPhone(request.supplierPhone());
            changes = true;
        }
        if (request.address() != null && !request.address().equals(supplier.getAddress())){
            supplier.setAddress(request.address());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("No Data changes found");
        }
        supplierDao.updateSupplier(supplier);
    }

    public void deleteSupplierById(Integer supplierId){
        checkIfSupplierExistsOrThrow(supplierId);
        supplierDao.deleteSupplierById(supplierId);
    }


    private void checkIfSupplierExistsOrThrow(Integer supplierId) {
        if (!supplierDao.supplierExistsById(supplierId)){
            throw new ResourceNotFoundException(
                    "Supplier with id [%s] not found".formatted(supplierId)
            );
        }
    }
}
