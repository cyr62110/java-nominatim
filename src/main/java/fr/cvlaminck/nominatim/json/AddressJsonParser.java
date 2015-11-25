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

import fr.cvlaminck.nominatim.model.Address;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class AddressJsonParser extends AbstractJsonParser<Address> {
    public final static String HOUSE_NUMBER = "house_number";
    public final static String ROAD = "road";
    public final static String SUBURB = "suburb";
    public final static String HAMLET = "hamlet";
    public final static String VILLAGE = "village";
    public final static String TOWN = "town";
    public final static String CITY = "city";
    public final static String COUNTY = "county";
    public final static String STATE_DISTRICT = "state_district";
    public final static String STATE = "state";
    public final static String COUNTRY = "country";
    public final static String COUNTRY_CODE = "country_code";
    public final static String CONTINENT = "continent";

    public AddressJsonParser() {
        super(Collections.EMPTY_LIST);
    }

    @Override
    protected Address doParseFromJson(JSONObject json) throws JSONException {
        Address addr = new Address();

        if (json.has(HOUSE_NUMBER)) {
            addr.setHouseNumber(json.getString(HOUSE_NUMBER));
        }
        if (json.has(ROAD)) {
            addr.setRoad(json.getString(ROAD));
        }
        if (json.has(SUBURB)) {
            addr.setSuburb(json.getString(SUBURB));
        }
        if (json.has(HAMLET)) {
            addr.setHamlet(json.getString(HAMLET));
        }
        if (json.has(VILLAGE)) {
            addr.setVillage(json.getString(VILLAGE));
        }
        if (json.has(TOWN)) {
            addr.setTown(json.getString(TOWN));
        }
        if (json.has(CITY)) {
            addr.setCity(json.getString(CITY));
        }
        if (json.has(COUNTY)) {
            addr.setCounty(json.getString(COUNTY));
        }
        if (json.has(STATE_DISTRICT)) {
            addr.setStateDistrict(json.getString(STATE_DISTRICT));
        }
        if (json.has(STATE)) {
            addr.setState(json.getString(STATE));
        }
        if (json.has(COUNTRY)) {
            addr.setCountry(json.getString(COUNTRY));
        }
        if (json.has(COUNTRY_CODE)) {
            addr.setCountryCode(json.getString(COUNTRY_CODE));
        }
        if (json.has(CONTINENT)) {
            addr.setContinent(json.getString(CONTINENT));
        }

        return addr;
    }
}
