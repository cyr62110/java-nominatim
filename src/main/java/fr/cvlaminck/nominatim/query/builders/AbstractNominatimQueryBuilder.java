package fr.cvlaminck.nominatim.query.builders;

import fr.cvlaminck.builders.Builder;
import fr.cvlaminck.builders.uri.Uri;
import fr.cvlaminck.builders.uri.UriBuilder;
import fr.cvlaminck.nominatim.exceptions.NominatimAPIProtocolException;
import fr.cvlaminck.nominatim.model.Box;
import fr.cvlaminck.nominatim.query.enums.ResponseFormat;

import java.net.URI;
import java.util.*;

public abstract class AbstractNominatimQueryBuilder<T extends AbstractNominatimQueryBuilder>
    implements Builder<URI> {

    private static final String BASE_URI = "http://nominatim.openstreetmap.org";

    protected UriBuilder queryBuilder;

    private ResponseFormat format = null;
    private String jsonCallback = null;
    private List<String> languages = null;
    private Boolean addressDetails = null;
    private String email = null;

    protected AbstractNominatimQueryBuilder() {
        queryBuilder = Uri.parse(BASE_URI).buildUpon();
        queryBuilder.appendPathSegment(getPath());
    }

    /**
     * Returns the path that identifies the API.
     */
    protected abstract String getPath();

    /**
     * Returns a collection of all supported response format for the current API.
     */
    protected abstract Collection<ResponseFormat> getSupportedFormats();

    /**
     * Set the value for the given parameter. Value can be null.
     */
    protected void setParameterValue(String parameter, String value) {
        String values[] = new String[(value != null)?1:0];
        if (value != null) {
            values[0] = value;
        }
        queryBuilder.replaceQueryParameter(parameter, values);
    }

    protected void setParameterValue(String parameter, Boolean value) {
        String sValue = null;
        if (value != null) {
            sValue = (value) ? "1" : "0";
        }
        setParameterValue(parameter, sValue);
    }

    protected void setParameterValue(String parameter, Integer value) {
        String sValue = null;
        if (value != null) {
            sValue = value.toString();
        }
        setParameterValue(parameter, sValue);
    }

    protected void setParameterValue(String parameter, Object value) {
        setParameterValue(parameter, Objects.toString(value));
    }

    protected <T> void setParameterValue(String parameter, List<T> values) {
        String sValue = null;
        if (values != null) {
            StringBuffer sb = new StringBuffer();
            for (Object o : values) {
                if (sb.length() != 0) {
                    sb.append(',');
                }
                sb.append(o);
            }
            sValue = sb.toString();
        }
        setParameterValue(parameter, sValue);
    }

    protected void setParameterValue(String parameter, Box value) {
        String sValue = null;
        if (value != null) {
            sValue = String.format(Locale.US, "%d,%d,%d,%d", value.left, value.top, value.right, value.bottom);
        }
        setParameterValue(parameter, sValue);
    }

    /**
     * Output format
     */
    public T format(ResponseFormat responseFormat) {
        if (responseFormat == null) {
            this.format = null;
        } else {
            if (!getSupportedFormats().contains(responseFormat)) {
                throw new NominatimAPIProtocolException("'%s' format is not supported by '%s' API.", responseFormat, getPath());
            }
            this.format = responseFormat;
        }
        return (T)this;
    }

    /**
     * Wrap json output in a callback function (JSONP) i.e. <string>(<json>)
     */
    public T jsonCallback(String jsonCallback) {
        this.jsonCallback = jsonCallback;
        return (T)this;
    }

    /**
     * Preferred language order for showing search results, overrides the value specified in the "Accept-Language" HTTP header.
     * @param languages a list of language codes
     */
    public T acceptLanguage(List<String> languages) {
        //TODO Check the format of languages
        if (languages == null) {
            this.languages = null;
        } else {
            this.languages = new ArrayList<>();
            this.languages.addAll(languages);
        }
        return (T)this;
    }

    /**
     * Include a breakdown of the address into elements
     */
    public T addressDetails(boolean addressDetails) {
        this.addressDetails = addressDetails;
        return (T)this;
    }

    /**
     * If you are making large numbers of request please include a valid email address
     * or alternatively include your email address as part of the User-Agent string.
     * This information will be kept confidential and only used to contact you in the
     * event of a problem, see Usage Policy for more details.
     */
    public T email(String email) {
        //TODO Check that the value looks like a valid email
        this.email = null;
        return (T)this;
    }

    @Override
    public URI build() {
        setParameterValue("format", format);
        setParameterValue("json_callback", jsonCallback);
        setParameterValue("accept-language", languages);
        setParameterValue("addressdetails", addressDetails);
        setParameterValue("email", email);

        return queryBuilder.build().toURI();
    }
}
