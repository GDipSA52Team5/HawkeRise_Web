package sg.edu.iss.hawkerise.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.hawkerise.model.Tag;
import sg.edu.iss.hawkerise.repo.TagRepository;

@Service
public class TagService implements TagInterface {

	@Autowired
	TagRepository trepo;
	@Transactional
	public List<Tag> findAllTags() {
		return trepo.findAll();
	}

}
