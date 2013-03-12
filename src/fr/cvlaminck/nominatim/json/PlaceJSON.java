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

import fr.cvlaminck.nominatim.model.Place;

public class PlaceJSON 
{
	public final static String PLACE_ID = "place_id";
	public final static String PLACE_OSM_TYPE="osm_type";
	public final static String PLACE_OSM_ID="osm_id";
	public final static String PLACE_RANK = "place_rank";
	public final static String PLACE_BOUNDINGBOX = "boundingbox";
	public final static String PLACE_POLYGONPOINTS = "polygonpoints";
	public final static String PLACE_LATITUDE = "lat";
	public final static String PLACE_LONGITUDE = "lon";
	public final static String PLACE_DISPLAY_NAME = "display_name";
	public final static String PLACE_CLASS = "class";
	public final static String PLACE_TYPE = "type";
	public final static String PLACE_IMPORTANCE = "importance";
	public final static String PLACE_ADDRESS = "address";
	
	public static Place placeFromJSON(JSONObject json) throws JSONException
	{
		Place p = new Place();
		
		p.setPlaceId(json.getInt(PLACE_ID));
		p.setOsmType(json.getString(PLACE_OSM_TYPE));
		p.setOsmId(json.getInt(PLACE_OSM_ID));
		if(json.has(PLACE_RANK))
			p.setPlaceRank(json.getInt(PLACE_RANK));
		p.setBoundingBox(null); //TODO
		p.setPolygonPoints(null); //TODO
		p.setLatitude(json.getDouble(PLACE_LATITUDE));
		p.setLongitude(json.getDouble(PLACE_LONGITUDE));
		p.setDisplayName(json.getString(PLACE_DISPLAY_NAME));
		p.setType(json.getString(PLACE_TYPE));
		p.setOsmClass(json.getString(PLACE_CLASS));
		if(json.has(PLACE_ADDRESS))
			p.setAddress(AddressJSON.addressFromJSON(json.getJSONObject(PLACE_ADDRESS)));
		return p;
	}

}
