package fr.cvlaminck.nominatim;

import fr.cvlaminck.nominatim.query.executors.NominatimLookUpQueryExecutor;
import fr.cvlaminck.nominatim.query.executors.NominatimSearchQueryExecutor;

public class Nominatim {

    public static NominatimSearchQueryExecutor search() {
        return new NominatimSearchQueryExecutor();
    }

    public static NominatimLookUpQueryExecutor lookUp() { return new NominatimLookUpQueryExecutor(); }

}
