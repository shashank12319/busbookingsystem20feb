package com.wittybrains.busbookingsystem.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.time.format.DateTimeParseException;

import com.wittybrains.busbookingsystem.dto.TravelScheduleDTO;

import com.wittybrains.busbookingsystem.exception.UnprocessableEntityException;
import com.wittybrains.busbookingsystem.service.TravelScheduleService;

@RestController
@RequestMapping("/schedules")
public class TravelScheduleController {

	@Autowired
	private TravelScheduleService travelScheduleService;
	@GetMapping("avalibility")
	

		public ResponseEntity<?> getSchedules(
		        @RequestParam("source") String source,
		        @RequestParam("destination") String destination,
		        @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
		    try {
		        LocalDate parsedDate = LocalDate.parse(date);
		        List<TravelScheduleDTO> schedules = travelScheduleService.getAvailableSchedules(source, destination, parsedDate);
		        if (schedules.isEmpty()) {
		        	String message = "No bus is available between " + source + " and " + destination + " on " + date;
		            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		           
		        } else {
		            return new ResponseEntity<>(schedules, HttpStatus.OK);
		        }
		    } catch (DateTimeParseException ex) {
		        return new ResponseEntity<>("Invalid date format. The correct format is ISO date format (yyyy-MM-dd)", HttpStatus.BAD_REQUEST);
		    } catch (UnprocessableEntityException ex) {
		        return handleUnprocessableEntityException(ex);
		    }
		}
	
	


	@PostMapping
	public ResponseEntity createSchedule(@RequestBody TravelScheduleDTO travelScheduleDTO) throws ParseException {
	    try {
	        if (travelScheduleService.createSchedule(travelScheduleDTO)) {
	            return new ResponseEntity<>("Successfully created travel schedule", HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>("Unable to create travel schedule", HttpStatus.BAD_REQUEST);
	        }
	    } catch (UnprocessableEntityException ex) {
	        return handleUnprocessableEntityException(ex);
	    }
	}


	@ExceptionHandler(UnprocessableEntityException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<String> handleUnprocessableEntityException(UnprocessableEntityException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
}

