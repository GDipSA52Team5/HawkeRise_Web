package sg.edu.iss.hawkerise.service;

import java.util.List;

import sg.edu.iss.hawkerise.model.Centre;

public interface CentreInterface {
	public List<Centre> findAllCentres();
	
	public Centre findCentreByName(String name);

}
