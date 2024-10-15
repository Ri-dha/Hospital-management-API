package com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces;

import java.math.BigDecimal;

public interface BaseExpansePatient {
     Long getId();

     Long getItemId();

     String getItemName();

     Integer getItemCount();

     BigDecimal getItemPrice();

     BigDecimal getTotalPrice();
}
