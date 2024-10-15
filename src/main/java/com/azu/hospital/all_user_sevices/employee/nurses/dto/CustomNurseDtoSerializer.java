package com.azu.hospital.all_user_sevices.employee.nurses.dto;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class CustomNurseDtoSerializer extends JsonSerializer<NurseDto> {


    @Override
    public void serialize(NurseDto nurseDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (nurseDto == null){
            throw new ResourceNotFoundException(
                    "Null return data"
            );

        }

        ObjectNode nurseJson = JsonNodeFactory.instance.objectNode();

        nurseJson.put("id", nurseDto.getId());
        nurseJson.put("username", nurseDto.getNursePersonalInfo().getUsername());
        nurseJson.put("gender", nurseDto.getNursePersonalInfo().getGender().toString());
        nurseJson.put("martialStatus", nurseDto.getNursePersonalInfo().getMartialStatus().toString());
        nurseJson.put("email", nurseDto.getNursePersonalContact().getEmail());
        nurseJson.put("phoneNumber", nurseDto.getNursePersonalContact().getMobile());
        nurseJson.put("address", nurseDto.getNursePersonalContact().getAddress());
        nurseJson.put("birthday", nurseDto.getNursePersonalDate().getBirthday());
        nurseJson.put("employeeDate", nurseDto.getNursePersonalDate().getEmployeeDate());
        nurseJson.put("bloodGroup", nurseDto.getNursePersonalJopInfo().getBloodGroup());

        nurseJson.putPOJO("roles", nurseDto.getNursePersonalJopInfo().getRoles());
        nurseJson.put("specialty", nurseDto.getNursePersonalJopInfo().getSpecialist());
        nurseJson.put("subSpecialty", nurseDto.getNursePersonalJopInfo().getSubSpecialty());
        nurseJson.put("image", nurseDto.getImage());
        nurseJson.put("frontPersonalId", nurseDto.getFrontPersonalId());
        nurseJson.put("backPersonalId", nurseDto.getBackPersonalId());
        nurseJson.put("frontMedicalId", nurseDto.getFrontMedicalId());
        nurseJson.put("backMedicalId", nurseDto.getBackMedicalId());
        nurseJson.put("isEnabled", nurseDto.getNursePersonalJopInfo().getEnabled());
        nurseJson.put("isAccountNonExpired", nurseDto.getNursePersonalJopInfo().getAccountNonExpired());
        nurseJson.put("isCredentialsNonExpired", nurseDto.getNursePersonalJopInfo().getCredentialsNonExpired());
        nurseJson.put("isAccountNonLocked", nurseDto.getNursePersonalJopInfo().getAccountNonLocked());
        jsonGenerator.writeObject(nurseJson);


        }



}
