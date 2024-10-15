package com.azu.hospital.ph.StockMangment.math.money;


public enum Currency {
    USD("$"),
    EUR("€"),
    GBP("£"),
    JPY("¥"),
    IQD("IQD"), // Iraqi Dinar
    TRY("₺"),  // Turkish Lira
    CNY("¥"),  // Chinese Yuan
    EGP("E£"), // Egyptian Pound
    KWD("KD"), // Kuwaiti Dinar
    AED("DH"), // United Arab Emirates Dirham
    SAR("SR "), // Saudi Riyal (Kingdom of Saudi Arabia)
    QAR("QR "), // Qatari Riyal
    BHD("BD "), // Bahraini Dinar

    ;

    private final String symbol;


    Currency(String symbol) {
        this.symbol = symbol;

    }

    public String getSymbol() {
        return symbol;
    }


}
