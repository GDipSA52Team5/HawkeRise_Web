package sg.edu.iss.hawkerise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.hawkerise.repo.CentreRepository;
import sg.edu.iss.hawkerise.repo.HawkerRepository;
import sg.edu.iss.hawkerise.repo.MenuItemRepository;

@SpringBootApplication
public class HawkeriseApplication {
	
	@Autowired
	CentreRepository crepo;
	
	@Autowired
	HawkerRepository hrepo;
	
	@Autowired
	MenuItemRepository mrepo;
	


	public static void main(String[] args) {
		SpringApplication.run(HawkeriseApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> { 		
//			Centre centre1 = new Centre("Adam Road Food Centre", "2, Adam Road, S(289876)", null);
//			crepo.saveAndFlush(centre1);
//			
//			Hawker hawker1 = new Hawker("Apple", "Bean", "ABC", "#01-01", "hawker1", "12345", centre1);
//			hrepo.saveAndFlush(hawker1);
		};
	}

}
