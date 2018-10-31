package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.PilotModel;

/*
 * PilotService
 */

public interface PilotService {
	Optional<PilotModel> getPilotDetailByLicenseNumber(String licenseNumber);
	Optional<PilotModel> getPilotDetailById(long pilotId);
	PilotModel addPilot(PilotModel pilot);
	void deletePilot(PilotModel pilot);
}
