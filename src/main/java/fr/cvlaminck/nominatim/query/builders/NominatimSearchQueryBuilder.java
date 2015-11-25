package fr.cvlaminck.nominatim.query.builders;

import fr.cvlaminck.nominatim.exceptions.NominatimAPIProtocolException;
import fr.cvlaminck.nominatim.model.Box;
import fr.cvlaminck.nominatim.query.enums.PolygonFormat;
import fr.cvlaminck.nominatim.query.enums.ResponseFormat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NominatimSearchQueryBuilder
    extends AbstractNominatimQueryBuilder<NominatimSearchQueryBuilder> {

    private String query = null;

    private String street = null;
    private String city = null;
    private String county = null;
    private String state = null;
    private String country = null;
    private String postalCode = null;

    private List<String> countryCodes = null;

    private Box viewBox = null;
    private Boolean bounded = null;

    private List<Integer> excludedPlaceIds = null;

    private Integer limit = null;
    private Boolean dedupe = null;
    private Boolean debug = null;
    private Boolean extraTags = null;
    private Boolean nameDetails = null;

    private PolygonFormat polygonFormat = null;

    @Override
    protected String getPath() {
        return "search";
    }

    @Override
    protected Collection<ResponseFormat> getSupportedFormats() {
        return Arrays.asList(ResponseFormat.values());
    }

    public NominatimSearchQueryBuilder query(String query) {
        if (query == null) {
            this.query = null;
            return this;
        }
        if (street != null) {
            throw new NominatimAPIProtocolException("Cannot set query if street has already been set.");
        }
        if (city != null) {
            throw new NominatimAPIProtocolException("Cannot set query if city has already been set.");
        }
        if (county != null) {
            throw new NominatimAPIProtocolException("Cannot set query if county has already been set");
        }
        if (state != null) {
            throw new NominatimAPIProtocolException("Cannot set query if state has already been set");
        }
        if (country != null) {
            throw new NominatimAPIProtocolException("Cannot set query if country has already been set");
        }
        if (postalCode != null) {
            throw new NominatimAPIProtocolException("Cannot set query if postalCode has already been set");
        }
        this.query = query;
        return this;
    }

    public NominatimSearchQueryBuilder street(String street) {
        if (street != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set street if query has already been set");
        }
        this.street = street;
        return this;
    }

    public NominatimSearchQueryBuilder city(String city) {
        if (city != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set city if query has already been set");
        }
        this.city = city;
        return this;
    }

    public NominatimSearchQueryBuilder county(String county) {
        if (county != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set county if query has already been set");
        }
        this.county = county;
        return this;
    }

    public NominatimSearchQueryBuilder state(String state) {
        if (state != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set state if query has already been set");
        }
        this.state = state;
        return this;
    }

    public NominatimSearchQueryBuilder country(String country) {
        if (country != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set country if query has already been set");
        }
        this.country = country;
        return this;
    }

    public NominatimSearchQueryBuilder postalCode(String postalCode) {
        if (postalCode != null && query != null) {
            throw new NominatimAPIProtocolException("Cannot set postalCode if query has already been set");
        }
        this.postalCode = postalCode;
        return this;
    }

    public NominatimSearchQueryBuilder countryCodes(List<String> countryCodes) {
        //TODO: Validate that countryCodes only contains valid ISO 3166-1alpha2 code
        if (countryCodes == null) {
            this.countryCodes = null;
        } else {
            this.countryCodes = new ArrayList<>();
            this.countryCodes.addAll(countryCodes);
        }
        return this;
    }

    public NominatimSearchQueryBuilder viewBox(double left, double top, double right, double bottom) {
        //TODO: Check that the view box is valid. top < bottom etc...
        viewBox = new Box();
        viewBox.left = left;
        viewBox.top = top;
        viewBox.right = right;
        viewBox.bottom = bottom;
        return this;
    }

    public NominatimSearchQueryBuilder bounded(boolean bounded) {
        this.bounded = bounded;
        return this;
    }

    public NominatimSearchQueryBuilder excludePlaceIds(List<Integer> placeIds) {
        if (placeIds == null) {
            this.excludedPlaceIds = null;
        } else {
            this.excludedPlaceIds = new ArrayList<>();
            this.excludedPlaceIds.addAll(placeIds);
        }
        return this;
    }

    public NominatimSearchQueryBuilder limit(int limit) {
        if (limit < 0) {
            throw new NominatimAPIProtocolException("limit cannot be negative.");
        }
        this.limit = limit;
        return this;
    }

    public NominatimSearchQueryBuilder dedupe(boolean dedupe) {
        this.dedupe = dedupe;
        return this;
    }

    public NominatimSearchQueryBuilder debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public NominatimSearchQueryBuilder polygonFormat(PolygonFormat format) {
        this.polygonFormat = format;
        return this;
    }

    public NominatimSearchQueryBuilder extraTags(boolean extraTags) {
        this.extraTags = extraTags;
        return this;
    }

    public NominatimSearchQueryBuilder nameDetails(boolean nameDetails) {
        this.nameDetails = nameDetails;
        return this;
    }

    @Override
    public URI build() {
        setParameterValue("q", query);
        setParameterValue("street", street);
        setParameterValue("city", city);
        setParameterValue("county", county);
        setParameterValue("state", state);
        setParameterValue("country", country);
        setParameterValue("postalcode", postalCode);
        setParameterValue("countrycodes", countryCodes);
        setParameterValue("viewbox", viewBox);
        setParameterValue("bounded", bounded);
        setParameterValue("exclude_place_ids", excludedPlaceIds);
        setParameterValue("limit", limit);
        setParameterValue("dedupe", dedupe);
        setParameterValue("debug", debug);
        if (polygonFormat != null) {
            setParameterValue(polygonFormat.getParameterName(), true);
        }
        setParameterValue("extratags", extraTags);
        setParameterValue("namedetails", nameDetails);

        return super.build();
    }
}
