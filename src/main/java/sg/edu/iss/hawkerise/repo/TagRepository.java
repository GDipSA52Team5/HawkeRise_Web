package sg.edu.iss.hawkerise.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.hawkerise.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
