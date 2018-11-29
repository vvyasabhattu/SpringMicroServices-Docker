package org.evoke.user.util;

import java.util.List;

import org.evoke.user.model.Address;
import org.evoke.user.model.AddressResponse;
import org.evoke.user.model.CustomAddressResponse;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
	
	@Autowired
	private Session session;
	
	
	public void map(List<Address> addresses,List<CustomAddressResponse> addressResponses) {
		
		CustomAddressResponse addressResponse =null;
		
		if(addresses!=null) {
		for (Address address : addresses) {
			 
			addressResponse = new CustomAddressResponse();
			
			addressResponse.setAddrLine1(address.getAddrLine1());
			addressResponse.setAddrLine2(address.getAddrLine2());
			addressResponse.setCity(address.getCity());
			addressResponse.setCountry(address.getCountry());
			addressResponse.setId(address.getId());
			addressResponse.setPincode(address.getPincode());
			addressResponse.setState(address.getState());
			//addressResponse.setUser_id(address.getUser().getU);
			
			addressResponses.add(addressResponse);
		}
		}
		
			
			
		}
		
	}
	



