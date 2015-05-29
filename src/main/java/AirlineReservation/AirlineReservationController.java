package AirlineReservation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Model.FareModel;
import Model.FlightModel;
import Model.LegModel;
import Model.PassengerModel;
import Utilities.AirlineUtilities;



@Controller
public class AirlineReservationController {

	@Autowired
	AirlineUtilities airlineUtilities;

	/**
	 * welcome page
	 * @return
	 */
	@RequestMapping("/welcome.htm")
	public ModelAndView welcomePage() {
		System.out.println("in controller");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("SerachFlightInfo");
		return mv;
	}

	/**
	 * Gets fight number and weekdays given arrival and departure airport code
	 * @param arrAirportCode
	 * @param deptAirportCode
	 * @param hops
	 * @return
	 */
	@RequestMapping(value="/getFlightWeekdaysInformation.json", method = RequestMethod.GET)
	public @ResponseBody  List<FlightModel> getFlightWeekdaysInformation(@RequestParam(value = "arrAirportCode") String arrAirportCode,@RequestParam(value = "deptAirportCode") String deptAirportCode,@RequestParam(value = "hops") int hops) {
		System.out.println("in controller");
		return airlineUtilities.getFlightWeekdaysInformation(arrAirportCode,deptAirportCode,hops);
		
	}
	
	/**
	 * Gets number of available seat for the given flight number and date
	 * @param flightNumber
	 * @param date
	 * @return
	 */
	@RequestMapping(value="/getSeatAvailibility.json",method = RequestMethod.GET)
	public @ResponseBody int getSeatAvailibility(@RequestParam(value = "flightNumber") String flightNumber,@RequestParam(value = "date") String date) {
		System.out.println("in controller");
		return airlineUtilities.getSeatAvailibility(flightNumber,date);
		
	}

	@RequestMapping(value="/getFlightLegInfo.json",method = RequestMethod.GET)
	public @ResponseBody List<LegModel> getFlightLegInfo(@RequestParam(value = "flightNumber") String flightNumber){
		System.out.println("in controller");
		return airlineUtilities.getFlightLegInfo(flightNumber);
		
	}
	
	/**
	 * Gets fare information for the given flight number
	 * @param flightNumber
	 * @return
	 */
	@RequestMapping(value="/getFareInformation.json",method = RequestMethod.GET)
	public @ResponseBody List<FareModel> getFareInformation(@RequestParam(value = "flightNumber") String flightNumber) {
		System.out.println("in controller");
		return airlineUtilities.getFareInformation(flightNumber);
		
	}
	
	/**
	 * gets customer name , phone number and seat number given flight number and date
	 * @param flightNumber
	 * @return
	 */
	@RequestMapping(value="/getCustomerInformation.json", method = RequestMethod.GET)
	public @ResponseBody List<PassengerModel> getCustomerInformation(@RequestParam(value = "flightNumber") String flightNumber,@RequestParam(value = "flightDate") String flightDate) {
		System.out.println("in controller");
		return airlineUtilities.getCustomerInformation(flightNumber,flightDate);
		
	}
	
	/**
	 * Gets flight number, travel date, seat number, arrival and departure time for the given customer name.
	 * @param custName
	 * @return
	 */
	@RequestMapping(value="/getFlightInfoOfCust.json",method=RequestMethod.GET)
	public  @ResponseBody List<LegModel> getFlightInfoOfCust(@RequestParam(value = "custName") String custName) {
		System.out.println("in controller");
		return airlineUtilities.getFlightInfoOfCust(custName.toUpperCase());
		
	}

}