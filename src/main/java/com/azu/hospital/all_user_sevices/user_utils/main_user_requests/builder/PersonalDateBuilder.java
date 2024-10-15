package com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder;

import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.PersonalDate;

public class PersonalDateBuilder {

    private String birthday;
    private String employeeDate;

    public PersonalDateBuilder() {
    };

    public PersonalDateBuilder withBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public PersonalDateBuilder withEmployeeDate(String employeeDate) {
        this.employeeDate = employeeDate;
        return this;
    }

    public PersonalDate build() {
        final String finalBirthday = this.birthday;
        final String finalEmployeeDate = this.employeeDate;

        return new PersonalDate() {
            @Override
            public String getBirthday() {
                return finalBirthday;
            }

            @Override
            public void setBirthday(String birthday) {
                // This method intentionally left empty
            }

            @Override
            public String getEmployeeDate() {
                return finalEmployeeDate;
            }

            @Override
            public void setEmployeeDate(String employeeDate) {
                // This method intentionally left empty
            }
        };
    }
}
