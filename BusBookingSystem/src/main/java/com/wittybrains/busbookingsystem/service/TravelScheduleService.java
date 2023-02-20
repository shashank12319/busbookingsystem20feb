package com.wittybrains.busbookingsystem.service;



import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;
import com.wittybrains.busbookingsystem.exception.InvalidSourceOrDestinationException;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.TravelSchedule;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.TravelScheduleRepository;


@Service
public class TravelScheduleService {
	private static final int MAX_SEARCH_DAYS = 30;
    @Autowired
    private TravelScheduleRepository scheduleRepository;

    @Autowired
    private BusRepository busRepository;


    
    public List<TravelScheduleDTO> getAvailableSchedules(String source, String destination, LocalDate searchDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalTime currentTime = currentDateTime.toLocalTime();

        LocalDateTime searchDateTime = LocalDateTime.of(searchDate, LocalTime.MIDNIGHT);
        LocalTime searchTime = searchDateTime.toLocalTime();

        if (searchDate.isBefore(currentDate)) {
            // cannot search for past schedules
            throw new IllegalArgumentException("Cannot search for schedules in the past");
        } else if (searchDate.equals(currentDate) && searchTime.isBefore(currentTime.plusHours(1))) {
            // search for schedules at least 1 hour from now
            searchDateTime = LocalDateTime.of(searchDate, currentTime.plusHours(1));
        }

        LocalDateTime maxSearchDateTime = currentDateTime.plusDays(MAX_SEARCH_DAYS);
        if (searchDateTime.isAfter(maxSearchDateTime)) {
            // cannot search for schedules more than one month in the future
            throw new IllegalArgumentException("Cannot search for schedules more than one month in the future");
        }

       
        
        
        List<TravelSchedule> travelScheduleList = scheduleRepository
                .findBySourceAndDestinationAndEstimatedArrivalTimeAfter(source, destination, currentDateTime);
        List<TravelScheduleDTO> travelScheduleDTOList = new ArrayList<>();
        for (TravelSchedule travelSchedule : travelScheduleList) {
            TravelScheduleDTO travelScheduleDTO = new TravelScheduleDTO();
            travelScheduleDTO.setBusId(travelSchedule.getBus().getId());
            travelScheduleDTO.setSource(travelSchedule.getSource());
            travelScheduleDTO.setDestination(travelSchedule.getDestination());
            travelScheduleDTO.setFareAmount(travelSchedule.getFareAmount());
            travelScheduleDTO.setEstimatedArrivalTime(travelSchedule.getEstimatedArrivalTime().toString());
            travelScheduleDTO.setEstimatedDepartureTime(travelSchedule.getEstimatedDepartureTime().toString());
            travelScheduleDTO.setDate(travelSchedule.getEstimatedArrivalTime().toLocalDate().toString());
            travelScheduleDTOList.add(travelScheduleDTO);
        }
        return travelScheduleDTOList;
    }
      
    
    public TravelSchedule getScheduleById(Long scheduleId) {
        Optional<TravelSchedule> optionalSchedule = scheduleRepository.findById(scheduleId);

        if (!optionalSchedule.isPresent()) {
            throw new IllegalArgumentException("Schedule with ID " + scheduleId + " not found");
        }

        return optionalSchedule.get();
    }

    public boolean createSchedule(TravelScheduleDTO travelScheduleDTO) throws ParseException {
        if (travelScheduleDTO.getBusId() == null) {
            throw new IllegalArgumentException("Bus ID cannot be null");
        }

        Optional<Bus> optionalBus = busRepository.findById(travelScheduleDTO.getBusId());

        if (!optionalBus.isPresent()) {
            throw new IllegalArgumentException("Bus with ID " + travelScheduleDTO.getBusId() + " not found");
        }

        TravelSchedule travelschedule = new TravelSchedule();

        travelschedule.setBus(optionalBus.get());

        String source = travelScheduleDTO.getSource();
        String destination = travelScheduleDTO.getDestination();
        if (source == null || source.isBlank() || destination == null || destination.isBlank()) {
            throw new InvalidSourceOrDestinationException("Invalid source or destination");
        }

        travelschedule.setDestination(destination);

        try {
            travelschedule.setEstimatedArrivalTime(LocalDateTime.parse(travelScheduleDTO.getEstimatedArrivalTime()));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid estimated arrival time format: " + ex.getMessage());
        }

        try {
            travelschedule.setEstimatedDepartureTime(LocalDateTime.parse(travelScheduleDTO.getEstimatedDepartureTime()));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid estimated departure time format: " + ex.getMessage());
        }

        try {
            travelschedule.setFareAmount(travelScheduleDTO.getFareAmount());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid fare amount: " + ex.getMessage());
        }

        travelschedule.setSource(source);

       

        travelschedule = scheduleRepository.save(travelschedule);
        return travelschedule.getScheduleId() != null;
    }




}







