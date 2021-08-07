package sg.edu.iss.hawkerise.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.hawkerise.model.Centre;

public interface CentreRepository extends JpaRepository<Centre, Integer> {

	public Centre findCentreByName(String name);
}
