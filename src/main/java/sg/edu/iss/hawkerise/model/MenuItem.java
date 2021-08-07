package sg.edu.iss.hawkerise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private String name;
	private String description;
	private double price;
	private String photo;
	private String status;
	
	private String localUrl;
	
	@ManyToOne
	@JoinColumn(name = "hawker_id")
	private Hawker hawker;

	public MenuItem(String name, String description, double price, String photo, String status, Hawker hawker) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.photo = photo;
		this.status = status;
		this.hawker = hawker;
	}

	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(int id, String name, String description, double price, String status, String localUrl) {
		  super();
		  this.id = id;
		  this.name = name;
		  this.description = description;
		  this.price = price;
		  this.status = status;
		  this.localUrl = localUrl;
		 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Hawker getHawker() {
		return hawker;
	}

	public void setHawker(Hawker hawker) {
		this.hawker = hawker;
	}
	
	@Transient
    public String getPhotoImagePath() {
        if (photo == null || id <= 0) return null;
         
        return "/item-photo/" + id + "/" + photo;
    }

	public String getLocalUrl() {
		return localUrl;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	
}
