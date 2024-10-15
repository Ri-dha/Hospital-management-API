package com.azu.hospital.all_user_sevices.employee.doctors.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.catalina.Role;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class CustomDoctorDtoSerializer extends JsonSerializer<DoctorDto> {


    @Override
    public void serialize(DoctorDto doctorDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

            if (doctorDto == null) {
                throw new IllegalArgumentException("UserDto cannot be null");
            }
            ObjectNode userJson = JsonNodeFactory.instance.objectNode();

            userJson.put("id", doctorDto.getId());
            userJson.put("username", doctorDto.getUserPersonalInfo().getUsername());
            userJson.put("gender", doctorDto.getUserPersonalInfo().getGender().toString());
            userJson.put("martialStatus", doctorDto.getUserPersonalInfo().getMartialStatus().name());
            userJson.put("email", doctorDto.getUserPersonalContact().getEmail());
            userJson.put("phoneNumber", doctorDto.getUserPersonalContact().getMobile());
            userJson.put("address", doctorDto.getUserPersonalContact().getAddress());
            userJson.put("birthday", doctorDto.getUserPersonalDate().getBirthday());
            userJson.put("employeeDate", doctorDto.getUserPersonalDate().getEmployeeDate());
            userJson.put("bloodGroup", doctorDto.getUserPersonalJopInfo().getBloodGroup());
            userJson.put("specialty", doctorDto.getUserPersonalJopInfo().getSpecialist());
            userJson.put("subSpecialty", doctorDto.getUserPersonalJopInfo().getSubSpecialty());
            userJson.putPOJO("roles", doctorDto.getRoles());
            userJson.put("image", doctorDto.getImage());
            userJson.put("isEnabled", doctorDto.getUserPersonalJopInfo().getEnabled());
            userJson.put("isAccountNonExpired", doctorDto.getUserPersonalJopInfo().getAccountNonExpired());
            userJson.put("isCredentialsNonExpired", doctorDto.getUserPersonalJopInfo().getCredentialsNonExpired());
            userJson.put("isAccountNonLocked", doctorDto.getUserPersonalJopInfo().getAccountNonLocked());
            jsonGenerator.writeObject(userJson);

        }
}
