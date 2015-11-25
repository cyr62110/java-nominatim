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
    private String houseNumber;
    private String road;
	private String hamlet;
    private String suburb;
	private String village;
    private String town;
	private String city;
	private String county;
	private String stateDistrict;
	private String state;
	private String country;
	private String countryCode;
    private String continent;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStateDistrict() {
        return stateDistrict;
    }

    public void setStateDistrict(String stateDistrict) {
        this.stateDistrict = stateDistrict;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        if (houseNumber != null) {
            sb.append("houseNumber='").append(houseNumber).append('\'');
        }
        if (road != null) {
            sb.append(", road='").append(road).append('\'');
        }
        if (hamlet != null) {
            sb.append(", hamlet='").append(hamlet).append('\'');
        }
        if (suburb != null) {
            sb.append(", suburb='").append(suburb).append('\'');
        }
        if (village != null) {
            sb.append(", village='").append(village).append('\'');
        }
        if (town != null) {
            sb.append(", town='").append(town).append('\'');
        }
        if (city != null) {
            sb.append(", city='").append(city).append('\'');
        }
        if (county != null) {
            sb.append(", county='").append(county).append('\'');
        }
        if (stateDistrict != null) {
            sb.append(", stateDistrict='").append(stateDistrict).append('\'');
        }
        if (state != null) {
            sb.append(", state='").append(state).append('\'');
        }
        if (country != null) {
            sb.append(", country='").append(country).append('\'');
        }
        if (countryCode != null) {
            sb.append(", countryCode='").append(countryCode).append('\'');
        }
        if (continent != null) {
            sb.append(", continent='").append(continent).append('\'');
        }
        sb.append('}');
        return sb.toString();
    }
}
