package fr.cvlaminck.nominatim.query.builders;

import fr.cvlaminck.nominatim.model.OsmType;
import fr.cvlaminck.nominatim.query.enums.ResponseFormat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NominatimLookUpQueryBuilder
    extends  AbstractNominatimQueryBuilder<NominatimLookUpQueryBuilder> {

    private List<String> osmIds = new ArrayList<>();

    private Boolean nameDetails = null;
    private Boolean extraTags = null;

    @Override
    protected String getPath() {
        return "lookup";
    }

    @Override
    protected Collection<ResponseFormat> getSupportedFormats() {
        return Arrays.asList(ResponseFormat.XML, ResponseFormat.JSON);
    }

    public NominatimLookUpQueryBuilder id(OsmType type, long id) {
        if (osmIds == null) {
            osmIds = new ArrayList<>();
        }
        String osmId = type.getPrefix() + id;
        osmIds.add(osmId);
        return this;
    }

    public NominatimLookUpQueryBuilder extraTags(boolean extraTags) {
        this.extraTags = extraTags;
        return this;
    }

    public NominatimLookUpQueryBuilder nameDetails(boolean nameDetails) {
        this.nameDetails = nameDetails;
        return this;
    }

    @Override
    public URI build() {
        setParameterValue("osm_ids", osmIds);

        setParameterValue("namedetails", nameDetails);
        setParameterValue("extratags", extraTags);

        return super.build();
    }
}
