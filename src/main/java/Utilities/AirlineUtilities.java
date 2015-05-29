package Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import AirlineReservationDao.AirlineReservationDaoImpl;
import Model.FareModel;
import Model.FlightModel;
import Model.LegModel;
import Model.PassengerModel;

public class AirlineUtilities {
	private int count=0;

	private AirlineReservationDaoImpl aiReservationDaoImpl;

	public AirlineReservationDaoImpl getAiReservationDaoImpl() {
		return aiReservationDaoImpl;
	}

	public void setAiReservationDaoImpl(
			AirlineReservationDaoImpl aiReservationDaoImpl) {
		this.aiReservationDaoImpl = aiReservationDaoImpl;
	}

	public List<FlightModel> getFlightWeekdaysInformation(String arrAirportCode,
			String deptAirportCode,int hops) {

		System.out.println("arrAirportCode "+arrAirportCode+", deptAirportCode "+deptAirportCode);
		List<FlightModel> flightInfo= aiReservationDaoImpl.getFlightWeekdaysInformation(arrAirportCode, deptAirportCode,hops);
		updateWeekdays(flightInfo);
		return flightInfo;
		
	}

	private void updateWeekdays(List<FlightModel> flightInfo) {
		for(FlightModel flightInstance:flightInfo){
			String[] weekdays= flightInstance.getWeekdays().split("\\|");
			String updatedWk=new String();
			//if(weekdays.length>1){
				List<List<String>> weekdayList= new ArrayList<List<String>>();
				for(String weekdayInstance:weekdays){
					
					String[] weekday=weekdayInstance.split("_");
					List<String> tempList= Arrays.asList(weekday);
					weekdayList.add(tempList);
				}
				Set<String> set =  new LinkedHashSet<String>();
				for ( List<String> list : weekdayList ) 
				{ 
				    set.addAll( list );
				} 
				for ( List<String> list : weekdayList )
				{
				    set.retainAll( list );
				}
				for(String s:set){
					updatedWk=updatedWk.concat(s+" ");
				}
				flightInstance.setWeekdays(updatedWk);
				
			}
		}
	//}



	public int getSeatAvailibility(String flightNumber, String date) {
		int noOfseats = aiReservationDaoImpl.getSeatAvailibility(flightNumber, date);
		System.out.println(noOfseats);
		return noOfseats;

	}

	public List<LegModel> getFlightLegInfo(String flightNumber) {

		return aiReservationDaoImpl.getFlightLegInfo(flightNumber);

	}

	public List<FareModel> getFareInformation(String flightNumber) {
		return aiReservationDaoImpl.getFareInformation(flightNumber);

	}

	public List<PassengerModel> getCustomerInformation(String flightNumber,String flightDate) {
		return aiReservationDaoImpl.getCustomerInformation(flightNumber,flightDate);

	}

	public List<LegModel> getFlightInfoOfCust(String custName) {
		return aiReservationDaoImpl.getFlightInfoOfCust(custName);

	}

}
