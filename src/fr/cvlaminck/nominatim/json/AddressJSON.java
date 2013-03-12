/*
 * Copyright 2013 Cyril Vlaminck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.cvlaminck.nominatim.json;

import org.json.JSONException;
import org.json.JSONObject;

import fr.cvlaminck.nominatim.model.Address;

public class AddressJSON 
{
	public final static String HAMLET = "hamlet";
	public final static String VILLAGE = "village";
	public final static String CITY = "city";
	public final static String COUNTY = "county";
	public final static String STATE_DISTRICT = "state_district";
	public final static String STATE = "state";
	public final static String COUNTRY = "country";
	public final static String COUNTRY_CODE = "country_code";
	
	public static Address addressFromJSON(JSONObject json) throws JSONException
	{
		Address addr = new Address();
		
		if(json.has(HAMLET))
			addr.setHamlet(json.getString(HAMLET));
		if(json.has(VILLAGE))
			addr.setVillage(json.getString(VILLAGE));
		if(json.has(CITY))
			addr.setCity(json.getString(CITY));
		if(json.has(COUNTY))
			addr.setCounty(json.getString(COUNTY));
		if(json.has(STATE_DISTRICT))
			addr.setStateDistrinct(json.getString(STATE_DISTRICT));
		if(json.has(STATE))
			addr.setState(json.getString(STATE));
		addr.setCountry(json.getString(COUNTRY));
		addr.setCountryCode(json.getString(COUNTRY_CODE));
		
		return addr;
	}

}
