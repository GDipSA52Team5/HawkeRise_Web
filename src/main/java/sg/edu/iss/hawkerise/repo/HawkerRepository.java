package sg.edu.iss.hawkerise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.hawkerise.model.Hawker;

public interface HawkerRepository extends JpaRepository<Hawker, Integer> {
	
	public Hawker findHawkerByUserNameAndPassword(String username, String password);
	
	public Hawker findHawkerByUserName(String username);
	
	@Query("select h from Hawker h where h.centre.id = :id")
	 public List<Hawker> findHawkersByCentreId(@Param("id") int id);

}
