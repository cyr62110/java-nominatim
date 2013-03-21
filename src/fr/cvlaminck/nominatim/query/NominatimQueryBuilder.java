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
import java.util.List;

/**
 * A simple builder class for request URL.
 * See this page for informations about the Nominatim API : http://wiki.openstreetmap.org/wiki/Nominatim
 */
public abstract class NominatimQueryBuilder 
{
	public final static String QUERY_BASE = "http://nominatim.openstreetmap.org";
	public final static String URL_ENCODING_SCHEME = "utf-8";
	
	private String m_urlEncodingScheme = URL_ENCODING_SCHEME;
	
	public NominatimQueryBuilder(String urlEncodingScheme)
	{
		m_urlEncodingScheme = urlEncodingScheme;
	}
	
	public String getUrlEncodingScheme()
	{
		return m_urlEncodingScheme;
	}
	
	public abstract String build();
	
	protected void addParameter(List<String> parameters, String key, Object value)
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
			return value.toString();
	}
	
}
