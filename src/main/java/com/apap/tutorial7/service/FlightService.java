package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.FlightModel;

/*
 * PilotService
 */

public interface FlightService {
	FlightModel addFlight(FlightModel flight);
	void deleteFlight(FlightModel flight);
	void deleteFlightById(long flightId);
	
	Optional<FlightModel> getFlightById(long id);
	Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
	
	List<FlightModel> getAllFlightByFlightNumber(String flightNumber);
	List<FlightModel> getAllFlight();
}
