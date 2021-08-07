package sg.edu.iss.hawkerise.service;

import java.util.List;

import sg.edu.iss.hawkerise.model.MenuItem;

public interface MenuItemInterface {
	
	public List<MenuItem> findByHawker(String userName);
	
	public void saveMenuItem(MenuItem menuItem);

	public MenuItem findById(int menuItemId);
	
	public void deleteMenuItem(MenuItem menuItem);
	
	public void updateMenuItem(MenuItem menuItem);
	
	public List<MenuItem>listMenuItems(int id);

}
