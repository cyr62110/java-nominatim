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

package fr.cvlaminck.nominatim.model;

public class Address 
{
	private String m_hamlet; //optional
	private String m_village; //optional
	private String m_city; //optional
	private String m_county;
	private String m_stateDistrinct; //optional
	private String m_state;
	private String m_country;
	private String m_countryCode;
	
	public Address()
	{
		
	}

	public String getHamlet() {
		return m_hamlet;
	}

	public void setHamlet(String m_hamlet) {
		this.m_hamlet = m_hamlet;
	}

	public String getVillage() {
		return m_village;
	}

	public void setVillage(String m_village) {
		this.m_village = m_village;
	}
	
	public void setCity(String city) {
		m_city = city;
	}
	
	public String getCity() {
		return m_city;
	}

	public String getCounty() {
		return m_county;
	}

	public void setCounty(String m_county) {
		this.m_county = m_county;
	}

	public String getStateDistrinct() {
		return m_stateDistrinct;
	}

	public void setStateDistrinct(String m_stateDistrinct) {
		this.m_stateDistrinct = m_stateDistrinct;
	}

	public String getState() {
		return m_state;
	}

	public void setState(String m_state) {
		this.m_state = m_state;
	}

	public String getCountry() {
		return m_country;
	}

	public void setCountry(String m_country) {
		this.m_country = m_country;
	}

	public String getCountryCode() {
		return m_countryCode;
	}

	public void setCountryCode(String m_countryCode) {
		this.m_countryCode = m_countryCode;
	}
	
}
