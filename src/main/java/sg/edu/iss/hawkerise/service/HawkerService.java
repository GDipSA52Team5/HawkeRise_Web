package sg.edu.iss.hawkerise.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.hawkerise.model.Hawker;
import sg.edu.iss.hawkerise.repo.HawkerRepository;

@Service
public class HawkerService implements HawkerInterface {
	@Autowired
	HawkerRepository hrepo;

	@Transactional
	public boolean authenticate(Hawker hawker) {
		Hawker fromDb = hrepo.findHawkerByUserNameAndPassword(hawker.getUserName(), hawker.getPassword());
		if (fromDb != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Hawker findByUserName(String username) {
		return hrepo.findHawkerByUserName(username);
	}

	@Override
	public boolean checkExists(Hawker hawker) {
		if (hrepo.findAll().contains(hawker)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void createHawker(Hawker hawker) {
		hrepo.saveAndFlush(hawker);
	}

	@Override
	public void update(Hawker hawker) {
		Hawker h = hrepo.findHawkerByUserName(hawker.getUserName());
		h.setStallName(hawker.getStallName());
		h.setContactNumber(hawker.getContactNumber());
		h.setPassword(hawker.getPassword());
		h.setOperatingHours(hawker.getOperatingHours());
		h.setCloseHours(hawker.getCloseHours());
		h.setTags(hawker.getTags());
		h.setStatus(hawker.getStatus());
		hrepo.saveAndFlush(h);

	}

	@Override
	public boolean checkCentreAndUnitNumber(Hawker hawker) {
		boolean isExist = false;
		List<Hawker> existHawkers = hrepo.findAll();
		for (Hawker h : existHawkers) {
			if ((Objects.equals(h.getCentre().getName(), hawker.getCentre().getName())
					&& Objects.equals(h.getUnitNumber(), hawker.getUnitNumber()))) {
				isExist = true;
				return isExist;
			}
		}
		return isExist;

	}

	@Override
	public boolean checkUserName(Hawker hawker) {
		boolean isExist = false;
		List<Hawker> existHawkers = hrepo.findAll();
		for (Hawker h : existHawkers) {
			if (Objects.equals(h.getUserName(), hawker.getUserName())) {
				isExist = true;
				return isExist;
			}
		}
		return isExist;
	}

	@Override
	public List<Hawker> listHawkers(int id) {

		return hrepo.findHawkersByCentreId(id);
	}

	@Override
	public boolean checkValidTime(Hawker hawker) {

		boolean validStatus = true;
		int startPoint = hawker.getOperatingHours().indexOf(":");
		String openHour = hawker.getOperatingHours().substring(0, startPoint);
		String openMinutes = hawker.getOperatingHours().substring(startPoint + 1, hawker.getOperatingHours().length());

		int endPoint = hawker.getCloseHours().indexOf(":");
		String closeHour = hawker.getCloseHours().substring(0, endPoint);
		String closeMinutes = hawker.getCloseHours().substring(endPoint + 1, hawker.getCloseHours().length());

		int startH = Integer.parseInt(openHour);
		int startM = Integer.parseInt(openMinutes);
		int closeH = Integer.parseInt(closeHour);
		int closeM = Integer.parseInt(closeMinutes);

		if ((startH - closeH) > 0) {
			validStatus = false;
		}
		if ((startH - closeH) == 0) {
			if ((startM - closeM) >= 0) {
				validStatus = false;
			}
		}
		return validStatus;
	}

}
