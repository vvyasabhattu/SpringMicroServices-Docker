package org.evoke.user.persistence.dao;

import java.util.ArrayList;
import java.util.List;


import org.evoke.user.model.Address;
import org.evoke.user.model.AddressResponse;
import org.evoke.user.model.AddressResponseLst;
import org.evoke.user.model.CustomAddressResponse;
import org.evoke.user.util.AddressMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class UserDao {
	
	@Autowired
	private Session session;
	
	@Autowired
	AddressMapper addressMapper;
	

	public AddressResponseLst getAddress(int userId) {
		AddressResponse addressResponse = new AddressResponse();
		Query query = session.createQuery("from Address where User_id=:id"); 
		 query.setInteger("id", userId);
   	    List<Address> addressList = query.list() ;
   	   
   	     return MapAddressResponse(addressList);
	}
	
	
	public AddressResponseLst MapAddressResponse(List<Address> addressList) {
		List<CustomAddressResponse> addressResList = new ArrayList<CustomAddressResponse>();
		if(!CollectionUtils.isEmpty(addressList)) {
			addressMapper.map(addressList, addressResList);
		}
	 
		AddressResponseLst arl =new AddressResponseLst();
		arl.setAddressResponseLst(addressResList);
	    return arl;
	}
}
