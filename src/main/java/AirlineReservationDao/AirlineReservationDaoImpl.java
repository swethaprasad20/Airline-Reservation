package AirlineReservationDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import Model.FareModel;
import Model.FlightModel;
import Model.LegModel;
import Model.PassengerModel;

public class AirlineReservationDaoImpl {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;  




	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}




	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}




	public List<FlightModel> getFlightWeekdaysInformation(String arrAirportCode,String deptAirportCode,int maxHops) {  
		String SQL=null;
		
		String SQL_NO_HOP="select concat(flight.flight_number,'(',departure_airport_code,' to ',arrival_airport_code,')') as leg , weekdays "+
				"from flight natural join flight_instance where departure_airport_code=:DEPARTURE_AIRPORT_CODE and arrival_airport_code=:ARRIVAL_AIRPORT_CODE";

		String SQL_ONE_HOP="select CONCAT(a1.flight_number,'(',a1.departure_airport_code,' to ',a1.arrival_airport_code,')',' -> ',a2.flight_number,'(',a2.departure_airport_code,' to ',a2.arrival_airport_code,')') as leg, "+
				" concat(a1.weekdays,'|',a2.weekdays) as weekdays from "+
				" (select * from flight_instance natural join flight) a1,  "+
				" (select * from flight natural join flight_instance) a2  "+
				" where a2.departure_airport_code=a1.arrival_airport_code  "+
				" and TIME_TO_SEC(TIMEDIFF(a2.departure_time,a1.arrival_time)) >=3600 "+
				" and a1.departure_airport_code=:DEPARTURE_AIRPORT_CODE and a2.arrival_airport_code=:ARRIVAL_AIRPORT_CODE ";


		String SQL_TWO_HOP="select CONCAT(a1.flight_number,'(',a1.departure_airport_code,' to ',a1.arrival_airport_code,')',' -> ',a2.flight_number,'(',a2.departure_airport_code,' to ',a2.arrival_airport_code,')',' -> ',a3.flight_number,'(',a3.departure_airport_code,' to ',a3.arrival_airport_code, ')') as leg, "+
				" concat(a1.weekdays,'|',a2.weekdays,'|',a3.weekdays) as weekdays from "+
				" (select * from flight_instance natural join flight) a1, "+
				" (select * from flight natural join flight_instance) a2,"+
				" (select * from flight natural join flight_instance) a3 "+
				" where a2.departure_airport_code=a1.arrival_airport_code "+
				" and a3.departure_airport_code=a2.arrival_airport_code "+
				" and a1.departure_airport_code <> a2.arrival_airport_code "+
				" and TIME_TO_SEC(TIMEDIFF(a2.departure_time,a1.arrival_time)) >=3600 " +
				" and TIME_TO_SEC(TIMEDIFF(a3.departure_time,a1.arrival_time)) >=3600 "+
				" and a1.departure_airport_code=:DEPARTURE_AIRPORT_CODE and a3.arrival_airport_code=:ARRIVAL_AIRPORT_CODE";
		
		if(maxHops==0){
			SQL=SQL_NO_HOP;
		}else if(maxHops==1){
			SQL=SQL_NO_HOP.concat(" UNION ").concat(SQL_ONE_HOP);
		}else{
			SQL=SQL_NO_HOP.concat(" UNION ").concat(SQL_ONE_HOP).concat(" UNION ").concat(SQL_TWO_HOP);
		}

		System.out.println(" getFlightWeekdaysInformation "+arrAirportCode+" "+deptAirportCode);
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("ARRIVAL_AIRPORT_CODE", arrAirportCode);
		parameterSource.addValue("DEPARTURE_AIRPORT_CODE", deptAirportCode);
		
		return  namedParameterJdbcTemplate.query(SQL, parameterSource, new RowMapper<FlightModel>() {

			public FlightModel mapRow(ResultSet rs, int arg1) throws SQLException {
				FlightModel fm= new FlightModel();
				fm.setFlightNumber(rs.getString("leg"));
				fm.setWeekdays(rs.getString("WEEKDAYS"));
				//fm.setArrivalAirportCode(rs.getString("ARRIVAL_AIRPORT_CODE"));
				//fm.setDepartureAirportCode(rs.getString("DEPARTURE_AIRPORT_CODE"));
				//fm.setArrivalTime(rs.getTimestamp("ARRIVAL_TIME"));
				//fm.setDepartureTime(rs.getTimestamp("DEPARTURE_TIME"));

				System.out.println(fm);
				return fm;

			}

		});

	}

	public List<LegModel> getFlightLegInfo(String flightNumber){
		String SQL="SELECT FLIGHT_NUMBER,LEG_NUMBER,FLIGHT_DATE,NUMBER_OF_AVAILABLE_SEATS,AIRPLANE_ID,DEPARTURE_AIRPORT_CODE,"
				+ "DEPARTURE_TIME,ARRIVAL_AIRPORT_CODE, ARRIVAL_TIME FROM LEG_INSTANCE WHERE FLIGHT_NUMBER=:FLIGHT_NUMBER";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("FLIGHT_NUMBER", flightNumber);
		return  namedParameterJdbcTemplate.query(SQL, parameterSource, new RowMapper<LegModel>() {

			public LegModel mapRow(ResultSet rs, int arg1) throws SQLException {
				LegModel lm= new LegModel();
				lm.setFlightNumber(rs.getString("FLIGHT_NUMBER"));
				lm.setLegNumber(rs.getString("LEG_NUMBER"));
				lm.setDate(rs.getString("FLIGHT_DATE"));
				lm.setNumberOfAvailiableSeats(rs.getInt("NUMBER_OF_AVAILABLE_SEATS"));
				lm.setAirplaneId(rs.getString("AIRPLANE_ID"));
				lm.setDepartureAirportCode(rs.getString("DEPARTURE_AIRPORT_CODE"));
				lm.setDeptTime(rs.getString("DEPARTURE_TIME"));
				lm.setArrivalAirportCode(rs.getString("ARRIVAL_AIRPORT_CODE"));
				lm.setArrivalTime(rs.getString("ARRIVAL_TIME"));

				System.out.println(lm);
				return lm;

			}

		});

	}

	public int getSeatAvailibility(String flightNumber, String date) {
		String SQL="SELECT  T2.TOTAL_NUM_OF_SEATS-T1.COUNT AS TOTAL_SEATS FROM ( "+
				" select count(*) AS COUNT from seat_reservation where FLIGHT_NUMBER=:FLIGHT_NUMBER AND FLIGHT_DATE=:FLIGHT_DATE) T1,"+
				" (SELECT TOTAL_NUM_OF_SEATS FROM AIRPLANE,FLIGHT_INSTANCE WHERE AIRPLANE.AIRPLANE_ID=FLIGHT_INSTANCE.AIRPLANE_ID"+
				" AND FLIGHT_NUMBER=:FLIGHT_NUMBER AND FLIGHT_DATE=:FLIGHT_DATE ) T2";

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("FLIGHT_NUMBER", flightNumber);
		parameterSource.addValue("FLIGHT_DATE", date);
		try{
			return  namedParameterJdbcTemplate.queryForInt(SQL, parameterSource);
		}catch(EmptyResultDataAccessException e){
			return -1;
		}

	}

	public List<FareModel> getFareInformation(String flightNumber) {

		String SQL = "SELECT FARE_CODE,AMOUNT,RESTRICTIONS FROM FARE WHERE FLIGHT_NUMBER=:FLIGHT_NUMBER ORDER BY AMOUNT DESC";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("FLIGHT_NUMBER", flightNumber);
		return  namedParameterJdbcTemplate.query(SQL, parameterSource, new RowMapper<FareModel>() {

			public FareModel mapRow(ResultSet rs, int arg1) throws SQLException {
				FareModel fm = new FareModel();
				fm.setFareCode(rs.getString("FARE_CODE"));
				fm.setAmount(rs.getString("AMOUNT"));
				fm.setRestirction(rs.getString("RESTRICTIONS"));
				System.out.println(fm);
				return fm;
			}

		});
	}

	public List<PassengerModel> getCustomerInformation(String flightNumber,String flightDate) {
		String SQL = "SELECT CUSTOMER_NAME,SEAT_NUMBER,CUSTOMER_PHONE FROM SEAT_RESERVATION WHERE FLIGHT_NUMBER=:FLIGHT_NUMBER AND FLIGHT_DATE=:FLIGHT_DATE";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("FLIGHT_NUMBER", flightNumber);
		parameterSource.addValue("FLIGHT_DATE", flightDate);
		return  namedParameterJdbcTemplate.query(SQL, parameterSource, new RowMapper<PassengerModel>() {

			public PassengerModel mapRow(ResultSet rs, int arg1) throws SQLException {
				PassengerModel fm = new PassengerModel();
				fm.setPassengerName(rs.getString("CUSTOMER_NAME"));
				fm.setSeatNum(rs.getString("SEAT_NUMBER"));
				fm.setContactInfo(rs.getString("CUSTOMER_PHONE"));
				System.out.println(fm);
				return fm;
			}

		});

	}

	public List<LegModel> getFlightInfoOfCust(String custName) {
		String SQL="SELECT CONCAT(FLIGHT_INSTANCE.FLIGHT_NUMBER,' ( ',DEPARTURE_AIRPORT_CODE,' -> ',ARRIVAL_AIRPORT_CODE,')') AS FLIGHT_NUMBER,FLIGHT_INSTANCE.FLIGHT_DATE,AIRPLANE_ID,SEAT_NUMBER,DEPARTURE_TIME,ARRIVAL_TIME FROM SEAT_RESERVATION,FLIGHT_INSTANCE,FLIGHT WHERE FLIGHT.FLIGHT_NUMBER=FLIGHT_INSTANCE.FLIGHT_NUMBER AND FLIGHT_INSTANCE.FLIGHT_NUMBER= SEAT_RESERVATION.FLIGHT_NUMBER AND FLIGHT_INSTANCE.FLIGHT_DATE= SEAT_RESERVATION.FLIGHT_DATE AND UCASE(CUSTOMER_NAME)=:CUSTOMER_NAME";
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("CUSTOMER_NAME", custName);
		return  namedParameterJdbcTemplate.query(SQL, parameterSource, new RowMapper<LegModel>() {

			public LegModel mapRow(ResultSet rs, int arg1) throws SQLException {
				LegModel fm = new LegModel();
				fm.setFlightNumber(rs.getString("FLIGHT_NUMBER"));
				fm.setAirplaneId(rs.getString("AIRPLANE_ID"));
				fm.setDate(rs.getString("FLIGHT_DATE"));
				fm.setSeatNumber(rs.getString("SEAT_NUMBER"));
				fm.setArrivalTime(rs.getString("ARRIVAL_TIME"));
				fm.setDeptTime(rs.getString("DEPARTURE_TIME"));
				System.out.println(fm);
				return fm;
			}

		});
	}



}
