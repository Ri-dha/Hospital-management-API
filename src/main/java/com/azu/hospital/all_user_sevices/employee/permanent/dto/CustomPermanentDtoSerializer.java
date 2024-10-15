package com.azu.hospital.all_user_sevices.employee.permanent.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class CustomPermanentDtoSerializer extends JsonSerializer<PermanentDto> {


    @Override
    public void serialize(PermanentDto permanentDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (permanentDto == null){
            throw new ResourceNotFoundException(
                    "Null return data"
            );
        }else {

            ObjectNode permanentJson = JsonNodeFactory.instance.objectNode();

            permanentJson.put("id", permanentDto.getId());
            permanentJson.put("username", permanentDto.getPermanentPersonalInfo().getUsername());
            permanentJson.put("gender", permanentDto.getPermanentPersonalInfo().getGender().toString());
            permanentJson.put("martialStatus", permanentDto.getPermanentPersonalInfo().getMartialStatus().toString());
            permanentJson.put("email", permanentDto.getPermanentPersonalContact().getEmail());
            permanentJson.put("phoneNumber", permanentDto.getPermanentPersonalContact().getMobile());
            permanentJson.put("address", permanentDto.getPermanentPersonalContact().getAddress());
            permanentJson.put("birthday", permanentDto.getPermanentPersonalDate().getBirthday());
            permanentJson.put("employeeDate", permanentDto.getPermanentPersonalDate().getEmployeeDate());
            permanentJson.put("bloodGroup", permanentDto.getPermanentPersonalJopInfo().getBloodGroup());

            permanentJson.putPOJO("roles", permanentDto.getPermanentPersonalJopInfo().getRoles());
            permanentJson.put("specialty", permanentDto.getPermanentPersonalJopInfo().getSpecialist());
            permanentJson.put("subSpecialty", permanentDto.getPermanentPersonalJopInfo().getSubSpecialty());
            permanentJson.put("image", permanentDto.getImage());
            permanentJson.put("frontPersonalId", permanentDto.getFrontPersonalId());
            permanentJson.put("backPersonalId", permanentDto.getBackPersonalId());
            permanentJson.put("frontMedicalId", permanentDto.getFrontMedicalId());
            permanentJson.put("backMedicalId", permanentDto.getBackMedicalId());
            permanentJson.put("isEnabled", permanentDto.getPermanentPersonalJopInfo().getEnabled());
            permanentJson.put("isAccountNonExpired", permanentDto.getPermanentPersonalJopInfo().getAccountNonExpired());
            permanentJson.put("isCredentialsNonExpired", permanentDto.getPermanentPersonalJopInfo().getCredentialsNonExpired());
            permanentJson.put("isAccountNonLocked", permanentDto.getPermanentPersonalJopInfo().getAccountNonLocked());
            jsonGenerator.writeObject(permanentJson);

        }


    }
}
