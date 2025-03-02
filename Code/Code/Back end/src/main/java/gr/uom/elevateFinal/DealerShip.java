package gr.uom.elevateFinal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DealerShip{

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) Paragei automata id, an den bazoume emeis xeirokinhta. To .INDENTITY xrhsimopoietai gia thn MySQL
	private int afm;
	
//	private int id;
	private String companyname;
	private String owner;
	private String password;
	
	
	//OneToMany dealership-car
	@OneToMany(mappedBy="dealership",
				cascade = CascadeType.ALL,
				fetch = FetchType.EAGER)
	private List<Car> cars = new ArrayList<Car>();
	
	
	//Default Constructor
	public DealerShip() {}
	
	
	public DealerShip(int afm, String companyname, String owner, String password) {
//		this.id = id;
		this.afm = afm;
		this.companyname = companyname;
		this.owner = owner;
		this.password = password;
	}


	//DealerShip's Getters
	public int getAfm() {return afm;}
//	public int getId() {return id;}
	public String getCompanyname() {return companyname;}
	public String getOwner() {return owner;}
	public String getPassword() {return password;}
	
	//--------------------------------------------------------------------------------------//
									//OneToMany dealership-car
	public void addCar(Car c) {
		cars.add(c);
	}
}
