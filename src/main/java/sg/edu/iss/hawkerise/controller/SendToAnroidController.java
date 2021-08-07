package sg.edu.iss.hawkerise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.hawkerise.model.Centre;
import sg.edu.iss.hawkerise.model.Hawker;
import sg.edu.iss.hawkerise.model.MenuItem;
import sg.edu.iss.hawkerise.service.CentreInterface;
import sg.edu.iss.hawkerise.service.HawkerInterface;
import sg.edu.iss.hawkerise.service.MenuItemInterface;

@RestController
@RequestMapping(path="/api")
public class SendToAnroidController {

	@Autowired
	CentreInterface cservice;
	
	@Autowired
	HawkerInterface hservice;
	
	@Autowired
	MenuItemInterface mservice;
	
	@GetMapping(path="listCentre")
	public List<Centre> getCentres(){
		
		List<Centre> resultCentres = new ArrayList<>();
		List<Centre> centreList = cservice.findAllCentres();
		
		for (Centre centre : centreList) {
			int id = centre.getId();
			String name = centre.getName();
			String address = centre.getAddress();
			String imgUrl = centre.getImgUrl();
			double latitude = centre.getLatitude();
			double longitude = centre.getLongitude();
			
			Centre c = new Centre(id, name, address, latitude, longitude, imgUrl);
			
			resultCentres.add(c);
		}
		
		return resultCentres;
	}
	
	@GetMapping(path="listHawkers/{id}")
	public List<Hawker> getHawkers(@PathVariable("id") Integer id){
		
		//get integer (Center Id) to show the list of hawker belong that Center.
		List<Hawker> hawkerList = hservice.listHawkers(id);
		List<Hawker> resultHawkers = new ArrayList<>();
		
		for (Hawker hawker: hawkerList) {
			
			int hawkerId = hawker.getId();
			String stallName = hawker.getStallName();
			String unitNumber = hawker.getUnitNumber();
			String contactNumber = hawker.getContactNumber();
			String operatingHour = hawker.getOperatingHours();
			String closeTime = hawker.getCloseHours();
			String[] tags = hawker.getTags();
			
			Hawker h = new Hawker(hawkerId, stallName, unitNumber, contactNumber, tags, operatingHour, closeTime);
			
			resultHawkers.add(h);
		}
		
		return resultHawkers;
	}
	
	@GetMapping(path="listMenuItem/{id}")
	public List<MenuItem> getMenuItems(@PathVariable("id") Integer id){
		
		
		List<MenuItem> menuItemList = mservice.listMenuItems(id);
		List<MenuItem> resultMenuItems = new ArrayList<>();
		
		for (MenuItem menuItem: menuItemList) {
			
			int menuItemId = menuItem.getId();
			String menuItemName = menuItem.getName();
			String menuItemDesc = menuItem.getDescription();
			double menuItemPrice = menuItem.getPrice();
			String menuItemStatus = menuItem.getStatus();
			String menuItemPhoto = menuItem.getLocalUrl();

			MenuItem m = new MenuItem(menuItemId, menuItemName, menuItemDesc, menuItemPrice, menuItemStatus, menuItemPhoto);
			
			resultMenuItems.add(m);
		}
		
		return resultMenuItems;
	}
}
