package com.apap.tutorial7.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

/*
 * FlightServiceImpl
 */

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public FlightModel addFlight(FlightModel flight) {
		return flightDb.save(flight);
	}

	@Override
	public void deleteFlight(FlightModel flight) {
		flightDb.delete(flight);
		
	}

	@Override
	public Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}

	@Override
	public List<FlightModel> getAllFlightByFlightNumber(String flightNumber) {
		List<FlightModel> allFlight  = flightDb.findAll();
        List<FlightModel> byFlightNumber = new LinkedList<>();
        for (FlightModel flight : allFlight){
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber))
                byFlightNumber.add(flight);
        }
        return byFlightNumber;
	}

	@Override
	public void deleteFlightById(long flightId) {
		// TODO Auto-generated method stub
		flightDb.deleteById(flightId);
	}

	@Override
	public Optional<FlightModel> getFlightById(long id) {
		 return flightDb.findById(id);
	}

	@Override
	public List<FlightModel> getAllFlight() {
		return flightDb.findAll();
	}
	
	

}
