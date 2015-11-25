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

import fr.cvlaminck.nominatim.exceptions.NominatimAPIResponseException;
import fr.cvlaminck.nominatim.model.OsmType;
import fr.cvlaminck.nominatim.model.PlaceCategory;
import fr.cvlaminck.nominatim.model.PlaceType;
import org.json.JSONException;
import org.json.JSONObject;

import fr.cvlaminck.nominatim.model.Place;

import java.util.Arrays;

public class PlaceJsonParser
    extends AbstractJsonParser<Place>
{
	public final static String PLACE_ID = "place_id";
    public final static String PLACE_OSM_ID="osm_id";

    public final static String PLACE_TYPE = "type";
	public final static String PLACE_OSM_TYPE="osm_type";
    public final static String PLACE_CATEGORY = "category"; //v2
    public final static String PLACE_CLASS = "class"; //v1
    public final static String PLACE_ICON = "icon";

	public final static String PLACE_RANK = "place_rank";
	public final static String PLACE_BOUNDINGBOX = "boundingbox";
	public final static String PLACE_LATITUDE = "lat";
	public final static String PLACE_LONGITUDE = "lon";
	public final static String PLACE_DISPLAY_NAME = "display_name";


	public final static String PLACE_IMPORTANCE = "importance";
	public final static String PLACE_ADDRESS = "address";
    public final static String PLACE_LICENCE = "licence";

    public static final String[] MANDATORY_FIELDS = new String[] {
            PLACE_ID, PLACE_OSM_TYPE, PLACE_OSM_ID, PLACE_LONGITUDE, PLACE_LONGITUDE,
            PLACE_DISPLAY_NAME, PLACE_TYPE, PLACE_LICENCE, PLACE_IMPORTANCE,
            PLACE_LICENCE
    };

    private AddressJsonParser addressParser = null;

    public PlaceJsonParser() {
        super(Arrays.asList(MANDATORY_FIELDS));
    }

    private AddressJsonParser getAddressParser() {
        if (addressParser == null) {
            addressParser = new AddressJsonParser();
        }
        return addressParser;
    }

    @Override
    protected void assertAllMandatoryFieldsAvailable(JSONObject json) throws NominatimAPIResponseException {
        boolean hasCategory = json.has(PLACE_CATEGORY);
        boolean hasClass = json.has(PLACE_CLASS);
        if ((!hasCategory && !hasClass) || (hasClass && hasCategory)) {
            throw new NominatimAPIResponseException("Response must contain one and only one of this two fields: 'category' or 'class'.", json.toString());
        }
        super.assertAllMandatoryFieldsAvailable(json);
    }

    @Override
    protected Place doParseFromJson(JSONObject json) throws JSONException, NominatimAPIResponseException {
        Place p = new Place();

        p.setPlaceId(json.getLong(PLACE_ID));
        p.setOsmId(json.getLong(PLACE_OSM_ID));

        p.setType(PlaceType.typeForName(json.getString(PLACE_TYPE)));
        p.setOsmType(OsmType.typeForName(json.getString(PLACE_OSM_TYPE)));

        if (json.has(PLACE_CATEGORY)) {
            p.setCategory(PlaceCategory.categoryForName(json.getString(PLACE_CATEGORY)));
        }
        if (json.has(PLACE_CLASS)) {
            p.setCategory(PlaceCategory.categoryForName(json.getString(PLACE_CLASS)));
        }


        p.setType(PlaceType.typeForName(json.getString(PLACE_TYPE)));
        if (json.has(PLACE_ICON)) {
            p.setIcon(json.getString(PLACE_ICON));
        }

        p.setDisplayName(json.getString(PLACE_DISPLAY_NAME));
        p.setLongitude(json.getDouble(PLACE_LONGITUDE));
        p.setLatitude(json.getDouble(PLACE_LATITUDE));
        if (json.has(PLACE_ADDRESS)) {
            p.setAddress(getAddressParser().parse(json.getJSONObject(PLACE_ADDRESS)));
        }

        //FIXME bounding box

        if (json.has(PLACE_RANK)) {
            p.setPlaceRank(json.getInt(PLACE_RANK));
        }
        p.setImportance(json.getDouble(PLACE_IMPORTANCE));

        p.setLicence(json.getString(PLACE_LICENCE));

        return p;
    }
}
