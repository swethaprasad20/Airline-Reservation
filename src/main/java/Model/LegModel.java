package Model;

public class LegModel {
	private String flightNumber;
	private String legNumber;
	private String date;
	private int numberOfAvailiableSeats;
	private String airplaneId;
	private String arrivalTime;
	private String arrivalAirportCode;
	private String deptTime;
	private String departureAirportCode;
	private String seatNumber;
	
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getLegNumber() {
		return legNumber;
	}
	public void setLegNumber(String legNumber) {
		this.legNumber = legNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNumberOfAvailiableSeats() {
		return numberOfAvailiableSeats;
	}
	public void setNumberOfAvailiableSeats(int numberOfAvailiableSeats) {
		this.numberOfAvailiableSeats = numberOfAvailiableSeats;
	}
	public String getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}
	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}
	public String getDeptTime() {
		return deptTime;
	}
	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}
	public String getDepartureAirportCode() {
		return departureAirportCode;
	}
	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

}
