package gr.uom.elevateFinal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", allowedHeaders="*")
@RestController
public class HelloController {

	@Autowired
	private HelloService hs;
	
	
	@GetMapping(path="/cars")
	public List<Car> getAllCars() throws Exception {
		return hs.getAllCars();
	}
	//------------------------ Leitourgia 3 ----------------//
	@PostMapping(path="/addcar")
	public void addCar(@RequestBody Car c) throws Exception {
		System.out.println("Received Car: " + c);
		System.out.println("Received Dealership ID: " + c.getDealerShip().getAfm());
		hs.addCar(c);
	}
	
	//-------------------- Leitourgia 5  -------------------//
	//It works, try Localhost/searchCars?brand=Audi
	@GetMapping(path="/searchcars")
	public List<Car> searchCars(@RequestParam(required = false) String brand,
								@RequestParam(required = false) String model,
								@RequestParam(required = false) String fuelType,
								@RequestParam(required = false) String engine) throws Exception{
		return hs.searchCars(brand, model, fuelType, engine);
	}
	
	//-----------------------Leitourgia 1 --------------------------//
	
	//------Sundesh Xrhsth, IF NOT WORKING DELETE HERE -----------//
	//An valoume @RequestBody Citizen citizen, to JSON 8a perilambanei OLA ta pedia tou Citizen
	//Otan epistrepsoume to plhres Citizen antikeimeno, 8a sumperilhf8oun kai ta upoloipa dedomena
	//tou Citizen ta opoia o client den xreiazetai na gnwrizei
	//@RequestBody LoginRequest request 
	
	
//	@PostMapping(path="/logincitizen")
//	public String loginCitizen(@RequestBody Citizen citizen) throws Exception {
//		hs.loginCitizen(citizen.getEmail(), citizen.getPassword());
//		return "Citizen logged in successfully";
//	}
	
	// --------------------------- AUTO PARAMENEI SE SXOLIA ------------------------------ //
	
	//----Sundesh DealerShip, IF NOT WORKING DELETE HERE -----//
//	@PostMapping(path="/logindealership")
//	public DealerShip loginDealerShip(@RequestBody LoginRequest request) throws Exception {
//		return hs.loginDealerShip(request.getEmail(), request.getPassword());
//	}
	
	
	
//	@PostMapping(path="/logindealership")
//	public String loginDealersShip(@RequestBody DealerShip dealership) throws Exception {
//		hs.loginDealerShip(dealership.getOwner(), dealership.getPassword());
//		return "DealerShip logged in successfully";
//	}
	
	
	
	//-----------------------   Leitourgia 2 ------------------------------//
	
	
	//it works, try POSTMAN -> http://localhost:8080/registercitizen -> body -> raw -> json
	// {id=2 px} you get dealership already exists
	//EGGRAFH POLITH //
	
	
	
// ---- Prosoxh, 19/1/2025 egine apopira dhmiourgias "/register" vriskete terma katw!! ----- //
	
//	@PostMapping(path="/registercitizen")
//	public void registerCitizen(@RequestBody Citizen citizen) throws Exception {
//		hs.registerCitizen(citizen);
//	}
//	
//	//EGGRAFH DEALERSHIP //
//	@PostMapping(path="/registerdealership")
//	public void registerDealerShip(@RequestBody DealerShip dealership) throws Exception {
//		hs.registerDealerShip(dealership);
//	}

	
	//---------------------- Leitourgia 4 ----------------------//
	
	@PutMapping(path="/updatecaravailability")
	public void updateCarAvailability(@RequestParam String carId,
										@RequestParam int newavailability) throws Exception {
		hs.updateCarAvailability(carId, newavailability);
	}
	
	
	//--------------------- Leitourgia 6 -----------------------//
	//Krathsh Test Drive (meta apo elegxo dia8esimothtas)
	@PostMapping(path="/booktestdrive")
	public void bookTestDrive(@RequestBody Booking booking) throws Exception {
		hs.bookTestDrive(booking);
	}
	
	//--------------------- LEITOURGIA 7 ---------------------//
	@PostMapping(path="/purchasecar")
	public void purchaseCar(@RequestParam String carId) throws Exception {
		hs.purchaseCar(carId);
	}
	
	
	
	// ------------------- Testing Login ----------------------//
	@PostMapping(path = "/login")
	public String login(@RequestBody LoginIdentifier request) throws Exception {
		if("citizen".equalsIgnoreCase(request.getRole() )) {
			hs.loginCitizen(request.getEmail(), request.getPassword());
			return "Citizen logged in successfully";
		} else if("dealership".equalsIgnoreCase(request.getRole())) {
			hs.loginDealerShip(request.getAfm(), request.getPassword() );
			return "Dealership Logged in Successfully";
		} else {
			throw new Exception("Invalid role was provided");
		}
	
	}
	
	// --------- Testing registration, if not working delete here!! --------------- //
	@PostMapping(path ="/register")
	public String register(@RequestBody RegisterUser request) throws Exception {
		if("citizen".equalsIgnoreCase(request.getRole() )) {
			Citizen citizen = new Citizen (request.getAfm(),
										   request.getFname(),
										   request.getLname(),
										   request.getEmail(),
										   request.getPassword()
										   );
			hs.registerCitizen(citizen);
			return "Citizen has registered Successfully";
		} else if ("dealership".equalsIgnoreCase(request.getRole() )) {
			DealerShip dealership = new DealerShip (request.getAfm(),
													request.getCompanyname(),
													request.getOwner(),
													request.getPassword()
													);
			hs.registerDealerShip(dealership);
			return "Dealership has registered successfully!";
		} else {
			throw new Exception("Something went wrong");
		}
		
		
		
	}

}
