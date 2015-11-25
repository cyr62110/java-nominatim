package fr.cvlaminck.nominatim;

import fr.cvlaminck.nominatim.model.OsmType;
import fr.cvlaminck.nominatim.model.Place;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NominatimTest {

    @Test
    public void testSearch() throws Exception {
        List<Place> results = Nominatim.search()
                .query("135, pilkington avenue, birmingham")
                .acceptLanguage(Arrays.asList("en-GB"))
                .get();

        assertFalse(results.isEmpty());
        assertEquals("135, Pilkington Avenue, Castle Vale, Maney, Birmingham, West Midlands, England, B72 1LH, United Kingdom", results.get(0).getDisplayName());
    }

    @Test
    public void testLookUp() throws Exception {
        List<Place> results = Nominatim.lookUp()
                .id(OsmType.RELATION, 58404)
                .get();

        assertFalse(results.isEmpty());
        assertEquals("Lille", results.get(0).getAddress().getCity());
    }
}