package com.wittybrains.busbookingsystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.BusDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.repository.BusRepository;

@RestController
@RequestMapping("/bus")
public class BusController {
	
    private final BusRepository busRepository;
    
    public BusController(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
    
    @PostMapping("/createBuses")
    public ResponseEntity<String> createBuses(@RequestBody List<BusDTO> busList) {
        try {
            for (BusDTO busDTO : busList) {
                Bus bus = new Bus();
                bus.setDestination(busDTO.getDestination());
                bus.setNumber(busDTO.getNumber());
                bus.setSource(busDTO.getSource());
                bus.setType(busDTO.getType());
                bus.setTiming(busDTO.getTiming());
                busRepository.save(bus);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created buses");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding buses: " + e.getMessage());
        }
    }
    
    @GetMapping("/searchBuses")
    public List<Bus> searchBuses(@RequestParam("source") String source,
                                 @RequestParam("destination") String destination,
                                 @RequestParam("timing") String timing) {
        return busRepository.findBySourceAndDestinationAndTiming(source, destination, timing);
    }
}
