package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.Application;
import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/heaters")
@Transactional
public class HeaterController {

    private final HeaterDao heaterDao;
    private final RoomDao roomDao;

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public HeaterController(HeaterDao heaterDao, RoomDao roomDao)
    {
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public HeaterDto findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDto::new).orElse(null);
    }

    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto){
        Room room = roomDao.getReferenceById(dto.getRoomId());
        if (dto.getRoomId() == null)
        {
            LOGGER.error("Heater must contain a room id!");
        }
        Heater heater = null;
        if (dto.getId() == null){
            heater = heaterDao.save(new Heater(dto.getName(), dto.getHeaterStatus(), room));
            if (dto.getPower() == null)
            {
                LOGGER.warn("Initializing new heater without power.");
            }
        }
        else {
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
        }
        if (dto.getPower() != null){
            heater.setPower(dto.getPower());
        }
        return new HeaterDto(heater);
    }

    @PutMapping(path = "/{id}/switch")
    public HeaterDto switchStatus(@PathVariable Long id) {
        Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDto(heater);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}
