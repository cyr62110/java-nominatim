/*
 * Copyright 2015 Cyril Vlaminck
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
	private long placeId;
    private long osmId;

    private PlaceType type;
	private OsmType osmType;
    private PlaceCategory category;
    private String icon;

    private String displayName;
    private double longitude;
    private double latitude;
    private Address address;

    private Box boundingBox;

	private int placeRank;
	private double importance;

    private String licence;

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
        this.type = type;
    }

    public OsmType getOsmType() {
        return osmType;
    }

    public void setOsmType(OsmType osmType) {
        this.osmType = osmType;
    }

    public PlaceCategory getCategory() {
        return category;
    }

    public void setCategory(PlaceCategory category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Box getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Box boundingBox) {
        this.boundingBox = boundingBox;
    }

    public int getPlaceRank() {
        return placeRank;
    }

    public void setPlaceRank(int placeRank) {
        this.placeRank = placeRank;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Place{");
        sb.append("placeId=").append(placeId);
        sb.append(", osmId=").append(osmId);
        sb.append(", type=").append(type);
        sb.append(", osmType=").append(osmType);
        sb.append(", category=").append(category);
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", address=").append(address);
        sb.append(", boundingBox=").append(boundingBox);
        sb.append(", placeRank=").append(placeRank);
        sb.append(", importance=").append(importance);
        sb.append(", licence='").append(licence).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
