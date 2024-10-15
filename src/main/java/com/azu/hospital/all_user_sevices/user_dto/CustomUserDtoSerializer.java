package com.azu.hospital.all_user_sevices.user_dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.math.BigDecimal;

public class CustomUserDtoSerializer extends JsonSerializer<UserDto> {

    @Override
    public void serialize(UserDto userDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }


        ObjectNode userJson = JsonNodeFactory.instance.objectNode();

        userJson.put("id", userDto.getId());

        userJson.put("username", userDto.getUserPersonalInfo().getUsername());
        userJson.put("gender", userDto.getUserPersonalInfo().getGender().toString());
        userJson.put("martialStatus", userDto.getUserPersonalInfo().getMartialStatus().toString());
        userJson.put("email", userDto.getUserPersonalContact().getEmail());
        userJson.put("phoneNumber", userDto.getUserPersonalContact().getMobile());
        userJson.put("address", userDto.getUserPersonalContact().getAddress());
        userJson.put("birthday", userDto.getUserPersonalDate().getBirthday());
        userJson.put("employeeDate", userDto.getUserPersonalDate().getEmployeeDate());
        userJson.put("bloodGroup", userDto.getUserPersonalJopInfo().getBloodGroup());
        userJson.putPOJO("roles", userDto.getUserPersonalJopInfo().getRoles());
        userJson.put("specialty", userDto.getUserPersonalJopInfo().getSpecialist());
        userJson.put("subSpecialty", userDto.getUserPersonalJopInfo().getSubSpecialty());
        userJson.put("image", userDto.getImage());
        userJson.put("frontPersonalId", userDto.getFrontPersonalId());
        userJson.put("backPersonalId", userDto.getBackPersonalId());
        userJson.put("frontMedicalId", userDto.getFrontMedicalId());
        userJson.put("backMedicalId", userDto.getBackMedicalId());
        userJson.put("isEnabled", userDto.getUserPersonalJopInfo().getEnabled());
        userJson.put("isAccountNonExpired", userDto.getUserPersonalJopInfo().getAccountNonExpired());
        userJson.put("isCredentialsNonExpired", userDto.getUserPersonalJopInfo().getCredentialsNonExpired());
        userJson.put("isAccountNonLocked", userDto.getUserPersonalJopInfo().getAccountNonLocked());
        jsonGenerator.writeObject(userJson);
    }
}
