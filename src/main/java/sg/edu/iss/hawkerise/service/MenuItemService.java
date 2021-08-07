package sg.edu.iss.hawkerise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.hawkerise.model.MenuItem;
import sg.edu.iss.hawkerise.repo.MenuItemRepository;

@Service
public class MenuItemService implements MenuItemInterface {
	@Autowired
	MenuItemRepository mrepo;

	@Override
	public List<MenuItem> findByHawker(String userName) {
		return mrepo.findMenuItemByHawker(userName);
	}

	@Override
	public void saveMenuItem(MenuItem menuItem) {
		mrepo.save(menuItem);
	}

	@Override
	public MenuItem findById(int menuItemId) {
		return mrepo.findMenuItemById(menuItemId);
	}

	@Override
	public void deleteMenuItem(MenuItem menuItem) {
		mrepo.delete(menuItem);
	}

	@Override
	public void updateMenuItem(MenuItem menuItem) {
		MenuItem m = mrepo.findMenuItemById(menuItem.getId());
		m.setDescription(menuItem.getDescription());
		m.setName(menuItem.getName());
		m.setPrice(menuItem.getPrice());
		m.setStatus(menuItem.getStatus());
		m.setPhoto(menuItem.getPhoto());
		m.setLocalUrl(menuItem.getLocalUrl());
		mrepo.saveAndFlush(m);
		
	}
	
	@Override
	 public List<MenuItem> listMenuItems(int id) {
	  return mrepo.findMenuItemsByHawkerId(id);
	 }

}
