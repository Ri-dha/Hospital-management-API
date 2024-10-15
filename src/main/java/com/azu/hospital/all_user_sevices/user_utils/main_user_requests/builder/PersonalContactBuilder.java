package com.azu.hospital.all_user_sevices.user_utils.main_user_requests.builder;

import com.azu.hospital.all_user_sevices.user_utils.main_user_requests.PersonalContact;
public class PersonalContactBuilder {

    private String email;
    private String mobile;
    private String address;

    public PersonalContactBuilder() {
    };

    public PersonalContactBuilder(String email, String mobile, String address) {
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public PersonalContactBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public PersonalContactBuilder withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public PersonalContactBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public PersonalContact build() {
        final String finalEmail = this.email;
        final String finalMobile = this.mobile;
        final String finalAddress = this.address;

        return new PersonalContact() {
            @Override
            public String getEmail() {
                return finalEmail;
            }

            @Override
            public void setEmail(String email) {
                // This method intentionally left empty
            }

            @Override
            public String getMobile() {
                return finalMobile;
            }

            @Override
            public void setMobile(String mobile) {
                // This method intentionally left empty
            }

            @Override
            public String getAddress() {
                return finalAddress;
            }

            @Override
            public void setAddress(String address) {
                // This method intentionally left empty
            }
        };
    }
}
