package com.azu.hospital.bulding.floor;

import com.azu.hospital.bulding.floor.dto.FloorWithWardsDto;
import com.azu.hospital.bulding.floor.dto.FloorWithWardsDtoMapper;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.floor.dao.FloorDao;
import com.azu.hospital.bulding.floor.dto.FloorDto;
import com.azu.hospital.bulding.floor.dto.FloorDtoMapper;
import com.azu.hospital.bulding.floor.request.FloorApiResponse;
import com.azu.hospital.bulding.floor.request.FloorBaseRequest;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FloorServices {
    private final ExceptionsMessageReturn messageReturn;


    private final FloorDao floorDao;
    private final FloorDtoMapper mapper;

    private final FloorWithWardsDtoMapper withWardsDtoMapper;

    @Autowired
    public FloorServices(ExceptionsMessageReturn messageReturn, @Qualifier("floorJpa") FloorDao floorDao, FloorDtoMapper mapper, FloorWithWardsDtoMapper withWardsDtoMapper) {
        this.messageReturn = messageReturn;
        this.floorDao = floorDao;
        this.mapper = mapper;
        this.withWardsDtoMapper = withWardsDtoMapper;
    }


    public FloorApiResponse createNewFloor(@RequestBody FloorBaseRequest request){
        existByName(request.getFloorName());
        existsByNumber(request.getFloorNumber());

        Floor floor = new Floor(
                request.getFloorNumber(),
                request.getFloorName()
        );
        floorDao.insertFloor(floor);
        FloorDto dto = mapper.toDto(floor);
        String message = "Floor add Successfully";
        boolean status = true;
        int statusCode = HttpStatusCode.valueOf(200).value();
        return new FloorApiResponse(message, status, statusCode, dto);
    }


    public FloorApiResponse updateExistsFloor(@RequestParam Long floorId, @RequestBody FloorBaseRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Floor floor = floorDao.selectFloorById(floorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("floorNotFound")
                        )
                );
        if (request.getFloorNumber() != null){
            floor.setFloorNumber(request.getFloorNumber());
        }if (request.getFloorName() != null){
            floor.setFloorName(request.getFloorName());
        }
        floorDao.updateFloor(floor);
        String message = "Floor add Successfully";
        boolean status = true;
        int statusCode = HttpStatusCode.valueOf(200).value();
        FloorDto dto = mapper.toDto(floor);
        return new FloorApiResponse(message, status, statusCode, dto);
    }


    public List<FloorDto> selectAllFloors(Pageable pageable) {
        return floorDao.selectAllFloors(pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public FloorDto getFloorById(@PathVariable Long floorId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return floorDao.selectFloorById(floorId)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("floorNotFound"))
                        );
    }

    public Map<String, Object> deleteFloor(@RequestParam Long floorId){
        floorDao.deleteFloorById(floorId);
        Map<String, Object> response= new HashMap<>();
        response.put("message", "Floor Delete Successfully");
        response.put("status",  true);
        response.put("statusCode", HttpStatusCode.valueOf(200).value());
        return response;
    }


    private void existByName(String floorName){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        final boolean isFloorName = floorDao.existsByFloorNameContainingIgnoreCase(floorName);
        if (isFloorName){
            throw new DuplicateResourceException(
                   messages.getString("alreadyExist")
            );

        }
    }

    private void existsByNumber(String floorNumber){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        final boolean isFloorNumber = floorDao.existsByFloorNumber(floorNumber);

        if (isFloorNumber){
               throw new DuplicateResourceException(
                          messages.getString("alreadyExist")
            );
        }
    }



    public Page<FloorWithWardsDto> getAllFloorsWithWards(Pageable pageable){
        return floorDao.getAllFloors(pageable)
                .map(withWardsDtoMapper);
    }
}
