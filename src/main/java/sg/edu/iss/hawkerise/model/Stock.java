//package sg.edu.iss.hawkerise.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class Stock {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	private String name;
//	private double purchaseQty;
//	private double remainingQty;
//	private int week;
//	private int day;
//	
//	@ManyToOne
//	@JoinColumn(name="hawker_id")
//	private Hawker hawker;
//
//	public Stock(String name, double purchaseQty, double remainingQty, int week, int day, Hawker hawker) {
//		super();
//		this.name = name;
//		this.purchaseQty = purchaseQty;
//		this.remainingQty = remainingQty;
//		this.week = week;
//		this.day = day;
//		this.hawker = hawker;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public double getPurchaseQty() {
//		return purchaseQty;
//	}
//
//	public void setPurchaseQty(double purchaseQty) {
//		this.purchaseQty = purchaseQty;
//	}
//
//	public double getRemainingQty() {
//		return remainingQty;
//	}
//
//	public void setRemainingQty(double remainingQty) {
//		this.remainingQty = remainingQty;
//	}
//
//	public int getWeek() {
//		return week;
//	}
//
//	public void setWeek(int week) {
//		this.week = week;
//	}
//
//	public int getDay() {
//		return day;
//	}
//
//	public void setDay(int day) {
//		this.day = day;
//	}
//
//	public Hawker getHawker() {
//		return hawker;
//	}
//
//	public void setHawker(Hawker hawker) {
//		this.hawker = hawker;
//	}
//
//}
