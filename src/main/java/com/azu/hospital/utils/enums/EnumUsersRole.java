package com.azu.hospital.utils.enums;

public enum EnumUsersRole {

    SECRETARY("Secretary"),
    SECURITY_GUARD("Security Guard"),
    RECEPTIONIST("Receptionist"),
    PHARMACY_CLERK("Pharmacy Clerk"),
    OFFICE_ASSISTANT("Office Assistant"),
    OFFICE_CLERK("Office Clerk"),
    MANAGER_OF_OFFICE_MANAGER("Manager of Office Manager"),
    INVENTORY_CLERK("Inventory Clerk"),
    INFORMATION_TECHNOLOGY_MANAGER("Information Technology Manager"),
    INFORMATION_TECHNOLOGY_SPECIALIST("Information Technology Specialist"),
    HUMAN_RESOURCES_MANAGER("Human Resources Manager"),
    HUMAN_RESOURCE_SPECIALIST("Human Resource Specialist"),
    FINANCIAL_ANALYST("Financial Analyst"),
    FRONT_OFFICE_CLERK("Front Office Clerk"),
    EXECUTIVE_ASSISTANT("Executive Assistant"),
    EXECUTIVE_DIRECTOR("Executive Director"),
    CUSTOMER_SERVICE_REPRESENTATIVE("Customer Service Representative"),
    COMPUTER_PROGRAMMER("Computer Programmer"),
    CLEANER("Cleaner"),
    BIOMEDICAL_ENGINEER("Biomedical Engineer"),
    BUSINESS_ANALYST("Business Analyst"),
    ACCOUNTANT("Accountant"),
    ACCOUNTING_CLERK("Accounting Clerk"),
    ACCOUNTING_MANAGER("Accounting Manager"),
    ACCOUNT_EXECUTIVE("Account Executive"),
    ACCOUNT_MANAGER("Account Manager"),
    ADMINISTRATIVE("Administrative"),
    ADMINISTRATION_MEMBER("Administration Member"),
    ADMINISTRATIVE_ASSISTANT("Administrative Assistant"),
    ADMINISTRATIVE_MANAGER("Administrative Manager"),
    ADMINISTRATIVE_MEDICAL_ASSISTANT("Administrative Medical Assistant"),
    ADMISSIONS_DIRECTOR("Admissions Director"),
    ACADEMIC_ADVISOR("Academic Advisor"),
    ADMINISTRATIVE_CLERK("Administrative Clerk"),
    ACCOUNTING_SUPERVISOR("Accounting Supervisor"),
    ACCOUNTING_SPECIALIST("Accounting Specialist"),
    ACCOUNTING_ASSISTANT("Accounting Assistant"),
    BUILDING_CODE_ADMINISTRATOR("Building Code Administrator"),
    BUILDING_MAINTENANCE_MANAGER("Building Maintenance Manager"),
    BUILDING_PLANS_EXAMINER("Building Plans Examiner"),
    BUILDING_SYSTEMS_CONTROLS_TECHNICIAN("Building Systems Controls Technician"),
    CAREER_COUNSELOR("Career Counselor"),
    ERP_ANALYST("ERP Analyst"),
    EXECUTIVE_ADMINISTRATIVE_SPECIALIST("Executive Administrative Specialist"),
    LEAD_TELECOM_TECHNOLOGY_SPECIALIST("Lead Telecom Technology Specialist"),
    MANAGER_OF_LAW_UNIT("Manager of Law Unit"),
    ASSISTANT_OF_MANAGER_OF_LAW_UNIT("Assistant of Manager of Law Unit"),
    SR_ELECTRICAL_ENGINEER("Senior Electrical Engineer"),
    DIRECTOR_OF_THE_LEGAL_UNIT("Director of the Legal Unit"),
    ASSISTANT_DIRECTOR_OF_THE_LEGAL_UNIT("Assistant Director of the Legal Unit"),
    LEGAL_ADMINISTRATIVE("Legal Administrative"),
    MAINTENANCE_ENGINEER("Maintenance Engineer"),
    PLUMBING_ENGINEER("Plumbing Engineer"),
    ELECTRONIC_ENGINEER("Electronic Engineer"),
    DIRECTOR_OF_MAINTENANCE_DIVISION("Director of Maintenance Division"),
    ASSISTANT_DIRECTOR_OF_MAINTENANCE_DIVISION("Assistant Director of Maintenance Division"),
    HOTEL_SERVICES_MANAGER("Hotel Services Manager"),
    SERVICE_MANAGER("Service Manager"),
    ASSISTANT_DIRECTOR_OF_SERVICES("Assistant Director of Services"),
    ASSISTANT_DIRECTOR_OF_HOTEL_SERVICES("Assistant Director of Hotel Services"),
    DIRECTOR_OF_THE_ADMINISTRATIVE_DIVISION("Director of the Administrative Division"),
    DIRECTOR_OF_THE_ELECTRONIC_ARCHIVING_UNIT("Director of the Electronic Archiving Unit");

    private final String roleName;

    EnumUsersRole(String roleName) {
        this.roleName = roleName;
    }

    public String getName() {
        return roleName;
    }
}
