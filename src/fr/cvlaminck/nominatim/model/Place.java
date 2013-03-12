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

import java.util.List;

public class Place 
{
	int m_placeId;
	String m_osmType;
	int m_osmId;
	int m_placeRank;
	BoundingBox m_boundingBox;
	List<Point> m_polygonPoints;
	double m_longitude;
	double m_latitude;
	String m_displayName;
	String m_class;
	String m_type;
	float m_importance;
	Address m_address;
	
	public Place()
	{
	}

	public int getPlaceId() {
		return m_placeId;
	}

	public void setPlaceId(int m_placeId) {
		this.m_placeId = m_placeId;
	}

	public String getOsmType() {
		return m_osmType;
	}

	public void setOsmType(String m_osmType) {
		this.m_osmType = m_osmType;
	}

	public int getOsmId() {
		return m_osmId;
	}

	public void setOsmId(int m_osmId) {
		this.m_osmId = m_osmId;
	}

	public int getPlaceRank() {
		return m_placeRank;
	}

	public void setPlaceRank(int m_placeRank) {
		this.m_placeRank = m_placeRank;
	}

	public BoundingBox getBoundingBox() {
		return m_boundingBox;
	}

	public void setBoundingBox(BoundingBox m_boundingBox) {
		this.m_boundingBox = m_boundingBox;
	}

	public List<Point> getPolygonPoints() {
		return m_polygonPoints;
	}

	public void setPolygonPoints(List<Point> m_polygonPoints) {
		this.m_polygonPoints = m_polygonPoints;
	}

	public double getLongitude() {
		return m_longitude;
	}

	public void setLongitude(double m_longitude) {
		this.m_longitude = m_longitude;
	}

	public double getLatitude() {
		return m_latitude;
	}

	public void setLatitude(double m_latitude) {
		this.m_latitude = m_latitude;
	}

	public String getDisplayName() {
		return m_displayName;
	}

	public void setDisplayName(String m_displayName) {
		this.m_displayName = m_displayName;
	}

	public String getOsmClass() {
		return m_class;
	}

	public void setOsmClass(String m_class) {
		this.m_class = m_class;
	}

	public String getType() {
		return m_type;
	}

	public void setType(String m_type) {
		this.m_type = m_type;
	}

	public float getImportance() {
		return m_importance;
	}

	public void setImportance(float m_importance) {
		this.m_importance = m_importance;
	}
	
	public Address getAddress() {
		return m_address;
	}
	
	public void setAddress(Address address) {
		m_address = address;
	}
	
}
