package fr.cvlaminck.nominatim.query;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryBuilder extends NominatimQueryBuilder 
{
	public final static String QUERY_BASE = NominatimQueryBuilder.QUERY_BASE + "/search?";
	
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
	
	public SearchQueryBuilder()
	{
		this(Format.JSON);
	}
	
	public SearchQueryBuilder(Format f)
	{
		this(f, URL_ENCODING_SCHEME);
	}
	
	public SearchQueryBuilder(Format f, String urlEncodingScheme)
	{
		super(urlEncodingScheme);
		if(f == null || urlEncodingScheme == null)
			throw new Error();//TODO
		m_format = f;
	}
	
	public SearchQueryBuilder jsonCallback(String jc)
	{
		m_jsonCallback = jc;
		return this;
	}
	
	public SearchQueryBuilder acceptLanguage(String al)
	{
		m_acceptLanguage = al;
		return this;
	}
	
	public SearchQueryBuilder query(String q)
	{
		if(m_street != null || m_city != null || m_county != null || m_state != null || m_country != null || m_postalCode != null)
			throw new Error(); //TODO
		m_query = q;
		return this;
	}
	
	public SearchQueryBuilder street(String street)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_street = street;
		return this;
	}
	
	public SearchQueryBuilder city(String city)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_city = city;
		return this;
	}
	
	public SearchQueryBuilder county(String county)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_county = county;
		return this;
	}
	
	public SearchQueryBuilder state(String state)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_state = state;
		return this;
	}
	
	public SearchQueryBuilder country(String country)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_country = country;
		return this;
	}
	
	public SearchQueryBuilder postalCode(String postalCode)
	{
		if(m_query != null)
			throw new Error(); //TODO
		m_postalCode = postalCode;
		return this;
	}
	
	public SearchQueryBuilder countryCodes(String... countryCodes)
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
	
	public SearchQueryBuilder bounded(boolean bounded)
	{
		m_bounded = new Boolean(bounded);
		return this;
	}
	
	public SearchQueryBuilder polygon(boolean polygon)
	{
		m_polygon = new Boolean(polygon);
		return this;
	}
	
	public SearchQueryBuilder addressDetails(boolean addressDetails)
	{
		m_addressDetails = new Boolean(addressDetails);
		return this;
	}
	
	public SearchQueryBuilder email(String email)
	{
		m_email = email;
		return this;
	}
	
	public SearchQueryBuilder excludedPlaceIds(int... ids)
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
	
	public SearchQueryBuilder limit(int limit)
	{
		m_limit = new Integer(limit);
		return this;
	}
	
	public SearchQueryBuilder dedup(boolean dedup)
	{
		m_dedup = new Boolean(dedup);
		return this;
	}
	
	public SearchQueryBuilder debug(boolean debug)
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
}
