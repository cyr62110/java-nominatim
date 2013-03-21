package fr.cvlaminck.nominatim.query;

import java.util.ArrayList;
import java.util.List;

public class ReverseQueryBuilder extends NominatimQueryBuilder 
{
	public final static String QUERY_BASE = NominatimQueryBuilder.QUERY_BASE + "/reverse?";
	
	public final static String FORMAT = "format";
	private Format m_format = null;
	
	public final static String JSON_CALLBACK = "json_callback";
	private String m_jsonCallback = null;
	public final static String ACCEPT_LANGUAGE = "accept-language";
	private String m_acceptLanguage = null;
	//This block
	public final static String OSM_TYPE = "osm_type";
	private OsmType m_osmType = null;
	public final static String OSM_ID = "osm_id";
	private Long m_osmId = null;
	//or this one
	public final static String LATITUDE = "lat";
	private Double m_latitude = null;
	public final static String LONGITUDE = "lon";
	private Double m_longitude = null;
	
	public final static String ZOOM = "zoom";
	private Integer m_zoom = null;
	public final static String ADDRESS_DETAILS = "addressdetails";
	private Boolean m_addressDetails = null;
	public final static String EMAIL = "email";
	private String m_email = null;
	
	public ReverseQueryBuilder()
	{
		this(Format.JSON);
	}
	
	public ReverseQueryBuilder(Format f)
	{
		this(f, URL_ENCODING_SCHEME);
	}
	
	public ReverseQueryBuilder(Format f, String urlEncodingScheme)
	{
		super(urlEncodingScheme);
		if(f == null || urlEncodingScheme == null)
			throw new Error();//TODO
		m_format = f;
	}
	
	public ReverseQueryBuilder jsonCallbask(String jsonCallback)
	{
		m_jsonCallback = jsonCallback;
		return this;
	}
	
	public ReverseQueryBuilder acceptLanguage(String acceptLanguage)
	{
		m_acceptLanguage = acceptLanguage;
		return this;
	}
	
	public ReverseQueryBuilder osmType(OsmType osmType)
	{
		if(m_latitude != null || m_longitude != null)
			throw new Error();
		m_osmType = osmType;
		return this;
	}
	
	public ReverseQueryBuilder osmId(long osmId)
	{
		if(m_latitude != null || m_longitude != null)
			throw new Error();
		m_osmId = Long.valueOf(osmId);
		return this;
	}
	
	public ReverseQueryBuilder longitude(double longitude)
	{
		if(m_osmType != null || m_osmId != null)
			throw new Error();
		m_longitude = Double.valueOf(longitude);
		return this;
	}
	
	public ReverseQueryBuilder latitude(double latitude)
	{
		if(m_osmType != null || m_osmId != null)
			throw new Error();
		m_latitude = Double.valueOf(latitude);
		return this;
	}
	
	public ReverseQueryBuilder zoom(int zoom)
	{
		if(zoom <= 0 || zoom > 18)
			throw new Error();
		m_zoom = Integer.valueOf(zoom);
		return this;
	}
	
	public ReverseQueryBuilder addressDetails(boolean addressDetails)
	{
		m_addressDetails = Boolean.valueOf(addressDetails);
		return this;
	}
	
	public ReverseQueryBuilder email(String email)
	{
		m_email = email;
		return this;
	}
	
	@Override
	public String build() 
	{
		StringBuffer query = new StringBuffer(QUERY_BASE);
		List<String> parameters = new ArrayList<String>();
		
		//We had all parameters to a list
		addParameter(parameters, FORMAT, m_format.toString());
		addParameter(parameters, JSON_CALLBACK, m_jsonCallback);
		addParameter(parameters, ACCEPT_LANGUAGE, m_acceptLanguage);
		addParameter(parameters, OSM_TYPE, m_osmType);
		addParameter(parameters, OSM_ID, m_osmId);
		addParameter(parameters, LATITUDE, m_latitude);
		addParameter(parameters, LONGITUDE, m_longitude);
		addParameter(parameters, ZOOM, m_zoom);
		addParameter(parameters, ADDRESS_DETAILS, m_addressDetails);
		addParameter(parameters, EMAIL, m_email);

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
