package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class FlightModel {
	
	private String flightNumber;
	private String weekdays;
	private String airline;
	private String departureAirportCode;
	private String arrivalAirportCode;
	private Timestamp DepartureTime;
	private Timestamp arrivalTime;
	
	
	public String getDepartureAirportCode() {
		return departureAirportCode;
	}
	public void setDepartureAirportCode(String departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}
	public String getArrivalAirportCode() {
		return arrivalAirportCode;
	}
	public void setArrivalAirportCode(String arrivalAirportCode) {
		this.arrivalAirportCode = arrivalAirportCode;
	}
	
	public Timestamp getDepartureTime() {
		return DepartureTime;
	}
	public void setDepartureTime(Timestamp departureTime) {
		DepartureTime = departureTime;
	}
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	@Override
	public String toString() {
		return "FlightModel [flightNumber=" + flightNumber + ", weekdays="
				+ weekdays + ", airline=" + airline + "]";
	}
	
	
	
	
}
