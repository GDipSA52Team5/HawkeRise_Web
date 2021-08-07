package sg.edu.iss.hawkerise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.hawkerise.model.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
	
	@Query("Select m from MenuItem m where m.hawker.userName = :susername")
	public List<MenuItem> findMenuItemByHawker(@Param("susername") String susername);
	
	@Query("Select m from MenuItem m where m.hawker.userName = :susername and m.name = :sname")
	public List<MenuItem> findMenuItemByHawkerAndName(@Param("susername") String susername,@Param("sname") String sname);
	
	public MenuItem findMenuItemById(Integer menuItemId);
	
	@Query("select m from MenuItem m where m.hawker.id = :id")
	 public List<MenuItem> findMenuItemsByHawkerId(@Param("id") int id);

}
