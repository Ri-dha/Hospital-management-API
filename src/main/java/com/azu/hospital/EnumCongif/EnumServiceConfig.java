package com.azu.hospital.EnumCongif;

import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
@Service
public class EnumServiceConfig {

    private static final Logger LOGGER = Logger.getLogger(EnumServiceConfig.class.getName());

    public EnumDTO convertToDTO(LinkedMap<String, LinkedMap<String, Integer>> enumMap) {
        EnumDTO enumDTO = new EnumDTO();
        enumDTO.setCode(HttpStatus.OK.value());
        enumDTO.setMessage(HttpStatus.OK.getReasonPhrase());
        enumDTO.setData(enumMap);
        return enumDTO;
    }

    public EnumDTO scanEnums() {
        LinkedMap<String, LinkedMap<String, Integer>> enumMap = new LinkedMap<>();

        try {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AssignableTypeFilter(Enum.class));

            for (BeanDefinition bd : scanner.findCandidateComponents("com.azu.hospital")) {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                if (clazz.isEnum()) {
                    LinkedMap<String, Integer> innerMap = new LinkedMap<>();
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isEnumConstant()) {
                            innerMap.put(field.getName(), ((Enum<?>) field.get(null)).ordinal());
                        }
                    }
                    enumMap.put(clazz.getSimpleName(), innerMap);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            LOGGER.log(Level.SEVERE, "Error while scanning enums", e);
        }

        return convertToDTO(enumMap);
    }
}
