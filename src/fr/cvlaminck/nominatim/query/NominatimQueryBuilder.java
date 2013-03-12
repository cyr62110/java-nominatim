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

package fr.cvlaminck.nominatim.query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple builder class for request URL.
 * See this page for informations about the Nominatim API : http://wiki.openstreetmap.org/wiki/Nominatim
 */
public class NominatimQueryBuilder 
{
	public final static String QUERY_BASE = "http://nominatim.openstreetmap.org/search?";
	public final static String URL_ENCODING_SCHEME = "utf-8";
	
	private String m_urlEncodingScheme = URL_ENCODING_SCHEME;
	
	public final static String FORMAT = "format";
	private Format m_format = null;
	public final static String JSON_CALLBACK = "json_callback";
	private String m_jsonCallback = null;
	public final static String ACCEPT_LANGUAGE = "accept-language";
	private String m_acceptLanguage = null;
	public final static String QUERY = "q";
	private String m_query = null;
	//or
	public final static String STREET = "street";
	private String m_street = null;
	public final static String CITY = "city";
	private String m_city = null;
	public final static String COUNTY = "country";
	private String m_county = null;
	public final static String STATE = "state";
	private String m_state = null;
	public final static String COUNTRY = "country";
	private String m_country = null;
	public final static String POSTAL_CODE = "postalcode";
	private String m_postalCode = null;
	
	public final static String COUNTRY_CODES = "countrycodes";
	private List<String> m_countryCodes = null;
	//viewbox TODO
	public final static String BOUNDED = "bounded";
	private Boolean m_bounded = null;
	public final static String POLYGON = "polygon";
	private Boolean m_polygon = null;
	public final static String ADDRESS_DETAILS = "addressdetails";
	private Boolean m_addressDetails = null;
	public final static String EMAIL = "email";
	private String m_email = null;
	public final static String EXCLUDE_PLACE_IDS = "exclude_place_ids";
	private List<String> m_excludePlaceIds = null;
	public final static String LIMIT = "limit";
	private Integer m_limit = null;
	public final static String DEDUP = "dedup";
	private Boolean m_dedup = null;
	public final static String DEBUG = "debug";
	private Boolean m_debug = null;
	
	public NominatimQueryBuilder()
	{
		this(Format.JSON);
	}
	
	public NominatimQueryBuilder(Format f)
	{
		this(f, URL_ENCODING_SCHEME);
	}
	
	public NominatimQueryBuilder(Format f, String urlEncodingScheme)
	{
		if(f == null || urlEncodingScheme == null)
			throw new Error();//TODO
		m_format = f;
		m_urlEncodingScheme = urlEncodingScheme;
	}
	
	public String getUrlEncodingScheme()
	{
		return m_urlEncodingScheme;
	}
	
	public NominatimQueryBuilder jsonCallback(String jc)
	{
		m_jsonCallback = jc;
		return this;
	}
	
	public NominatimQueryBuilder acceptLanguage(String al)
	{
		m_acceptLanguage = al;
		return this;
	}
	
	public NominatimQueryBuilder query(String q)
	{
		if(m_street != null || m_city != null || m_county != null || m_state != null || m_country != null || m_postalCode != null)
			throw new Error(); //TODO
		m_query = q;
		return this;
	}
	
	public NominatimQueryBuilder street(String street)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_street = street;
		return this;
	}
	
	public NominatimQueryBuilder city(String city)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_city = city;
		return this;
	}
	
	public NominatimQueryBuilder county(String county)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_county = county;
		return this;
	}
	
	public NominatimQueryBuilder state(String state)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_state = state;
		return this;
	}
	
	public NominatimQueryBuilder country(String country)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_country = country;
		return this;
	}
	
	public NominatimQueryBuilder postalCode(String postalCode)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_postalCode = postalCode;
		return this;
	}
	
	public NominatimQueryBuilder countryCodes(String... countryCodes)
	{
		if(countryCodes.length > 0)
		{
			m_countryCodes = new ArrayList<String>();
			for(int i = 0; i < countryCodes.length; i++)
			{
				//TODO : validate countryCodes
				m_countryCodes.add(countryCodes[i]);
			}
		}
		else
			m_countryCodes = null;
		return this;
	}
	
	public NominatimQueryBuilder bounded(boolean bounded)
	{
		m_bounded = new Boolean(bounded);
		return this;
	}
	
	public NominatimQueryBuilder polygon(boolean polygon)
	{
		m_polygon = new Boolean(polygon);
		return this;
	}
	
	public NominatimQueryBuilder addressDetails(boolean addressDetails)
	{
		m_addressDetails = new Boolean(addressDetails);
		return this;
	}
	
	public NominatimQueryBuilder email(String email)
	{
		m_email = email;
		return this;
	}
	
	public NominatimQueryBuilder excludedPlaceIds(int... ids)
	{
		if(ids == null || ids.length == 0)
			m_excludePlaceIds = null;
		else
		{
			m_excludePlaceIds = new ArrayList<String>();
			for(int i = 0; i < ids.length; i++)
			{
				m_excludePlaceIds.add(Integer.toString(ids[i]));
			}
		}
		return this;
	}
	
	public NominatimQueryBuilder limit(int limit)
	{
		m_limit = new Integer(limit);
		return this;
	}
	
	public NominatimQueryBuilder dedup(boolean dedup)
	{
		m_dedup = new Boolean(dedup);
		return this;
	}
	
	public NominatimQueryBuilder debug(boolean debug)
	{
		m_debug = new Boolean(debug);
		return this;
	}
	
	public String build()
	{
		StringBuffer query = new StringBuffer(QUERY_BASE);
		List<String> parameters = new ArrayList<String>();
		
		//We had all parameters to a list
		addParameter(parameters, FORMAT, m_format.toString());
		addParameter(parameters, JSON_CALLBACK, m_jsonCallback);
		addParameter(parameters, ACCEPT_LANGUAGE, m_acceptLanguage);
		addParameter(parameters, QUERY, m_query);
		addParameter(parameters, STREET, m_street);
		addParameter(parameters, CITY, m_city);
		addParameter(parameters, COUNTY, m_county);
		addParameter(parameters, STATE, m_state);
		addParameter(parameters, COUNTRY, m_country);
		addParameter(parameters, POSTAL_CODE, m_postalCode);
		addParameter(parameters, COUNTRY_CODES, m_countryCodes);
		//TODO : addParameter(parameters, VIEWBOX, m_viewBox);
		addParameter(parameters, BOUNDED, m_bounded);
		addParameter(parameters, POLYGON, m_polygon);
		addParameter(parameters, ADDRESS_DETAILS, m_addressDetails);
		addParameter(parameters, EMAIL, m_email);
		addParameter(parameters, EXCLUDE_PLACE_IDS, m_excludePlaceIds);
		addParameter(parameters, LIMIT, m_limit);
		addParameter(parameters, DEDUP, m_dedup);
		addParameter(parameters, DEBUG, m_debug);

		int parametersCount = 0;
		for(String s : parameters)
		{
			if(parametersCount != 0)
				query.append('&');
			query.append(s);
			parametersCount ++;
		}
		
		return query.toString();
	}
	
	private void addParameter(List<String> parameters, String key, Object value)
	{
		if(key == null || value == null || parameters == null)
			return;
		try
		{
			parameters.add(key + "=" + URLEncoder.encode(parameterValue(value), m_urlEncodingScheme));
		} catch (UnsupportedEncodingException e) {
			return;
		}
	}
	
	private String parameterValue(Object value)
	{
		if(value.getClass() == String.class)
			return (String)value;
		else if(value.getClass() == Boolean.class)
			return ((Boolean)value).booleanValue()?"1":"0";
		else if(value.getClass() == List.class)
		{
			@SuppressWarnings("unchecked")
			List<String> values = (List<String>)value;
			StringBuilder sb = new StringBuilder();
			int count = 0;
			for(String s : values)
			{
				if(count != 0)
					sb.append(',');
				sb.append(s);
				count ++;
			}
			return sb.toString();
		}
		else
			return null;
	}
	
}
