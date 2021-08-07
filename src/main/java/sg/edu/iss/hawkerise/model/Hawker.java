package sg.edu.iss.hawkerise.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;


@Entity
public class Hawker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;
	private String lastName;
	private String stallName;
	private String unitNumber;
	public String contactNumber;

	private String userName;
	private String password;
	
	private String[] tags;

	private String status;

	//@Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
	private String operatingHours;
	private String closeHours;

	@ManyToOne
	@JoinColumn(name = "centre_id")
	private Centre centre;

//	@ManyToMany
//	@JoinTable(name = "hawker_tag", joinColumns = @JoinColumn(name = "hawker_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
//	private Set<Tag> tags;

	@OneToMany(mappedBy = "hawker", cascade = {CascadeType.REMOVE})
	private Set<MenuItem> menuItems;

//	@OneToMany(mappedBy = "hawker", cascade = {CascadeType.REMOVE})
//	private Set<Stock> stock;

	public Hawker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCloseHours() {
		return closeHours;
	}

	public void setCloseHours(String closeHours) {
		this.closeHours = closeHours;
	}

	public Hawker(int id, String stallName, String unitNumber, String contactNumber, String[] tags,
			   String operatingHours, String closeTime) {
			  super();
			  this.id = id;
			  this.stallName = stallName;
			  this.unitNumber = unitNumber;
			  this.contactNumber = contactNumber;
			  this.tags = tags;
			  this.operatingHours = operatingHours;
			  this.closeHours = closeTime;
			 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperatingHours() {
		return operatingHours;
	}

	public void setOperatingHours(String operatingHours) {
		this.operatingHours = operatingHours;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}



	public Set<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

//	public Set<Stock> getStock() {
//		return stock;
//	}
//
//	public void setStock(Set<Stock> stock) {
//		this.stock = stock;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(centre, unitNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hawker other = (Hawker) obj;
		if ((Objects.equals(centre.getName(), other.centre.getName()) && Objects.equals(unitNumber, other.unitNumber))
				|| Objects.equals(userName, other.userName)){
			return true;
		} else {
			return false;
		}
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
