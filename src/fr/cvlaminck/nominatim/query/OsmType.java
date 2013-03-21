package fr.cvlaminck.nominatim.query;

public enum OsmType 
{
	Node("N"),
	Way("W"),
	Relation("R");
	
	private String m_osmType;
	
	OsmType(String osmType)
	{
		m_osmType = osmType;
	}
	
	public String toString()
	{
		return m_osmType;
	}
}
