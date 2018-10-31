package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

/*
 * FlightController
 */

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
		flight.setPilot(pilot);
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		return "addFlight";		
	}

    @RequestMapping(value="/flight/add/{licenseNumber}", params={"add"})
    public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
        if (pilot.getPilotFlight()==null)
            pilot.setPilotFlight(new ArrayList<>());
        pilot.getPilotFlight().add(new FlightModel());
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }
    @RequestMapping(value = "/flight/add/{licenseNumber}", params={"submit"}, method = RequestMethod.POST)
    private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
        PilotModel thisPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).get();
        for (FlightModel flight:pilot.getPilotFlight()) {
            flight.setPilot(thisPilot);
            flightService.addFlight(flight);
        }
        return "add";
    }
	
	@RequestMapping(value = "/flight/delete", method=RequestMethod.POST)
	private String deletePilot(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flightNya : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flightNya.getId());
		}
		return "delete";
	}
	
    @RequestMapping("/flight/update/{licenseNumber}")
    private String update(@PathVariable(name = "licenseNumber")String licenseNumber,@ModelAttribute FlightModel flightNya , Model model){
        PilotModel pilotNya = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
        flightNya.setPilot(pilotNya);
        model.addAttribute("flight",flightNya);
        return "updateFlight";

    }
	
	@RequestMapping(value = "/flight/update",method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "update";
	}
	
	@RequestMapping(value = "/flight/view",method = RequestMethod.GET)
	private String viewFlight(@RequestParam("flightNumber") String flightNumber, Model model) {
		List<FlightModel> flightsNya = flightService.getAllFlightByFlightNumber(flightNumber);
		model.addAttribute("flightList", flightsNya);
		return "view-flight";
	}
}
