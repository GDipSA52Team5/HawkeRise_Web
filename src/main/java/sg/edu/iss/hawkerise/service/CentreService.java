package sg.edu.iss.hawkerise.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.hawkerise.model.Centre;
import sg.edu.iss.hawkerise.repo.CentreRepository;

@Service
public class CentreService implements CentreInterface {

	@Autowired
	public CentreRepository cRepo;
	
	@Transactional
	public List<Centre> findAllCentres() {
		return cRepo.findAll();
	}

	@Override
	public Centre findCentreByName(String name) {
		return cRepo.findCentreByName(name);
	}



}
