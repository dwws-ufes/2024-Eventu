package br.ufes.inf.eventu.app.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.inf.eventu.app.domain.Location;
import br.ufes.inf.eventu.app.persistence.LocationDAO;
import br.ufes.inf.eventu.app.services.interfaces.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO attractionDAO;

    @Override
    public Location save(Location model) throws Exception {
        return attractionDAO.save(model);
    }

    @Override
    public Location edit(long id, Location model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }

    public List<Location> retrieveLocationRDF(){
        String sparqlEndpoint = "http://dbpedia.org/sparql";
        String sparqlQuery = """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX dbo: <http://dbpedia.org/ontology/>
                PREFIX dbr: <http://dbpedia.org/resource/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT DISTINCT ?stateName WHERE {
                        ?resource dbp:settlementType dbr:States_of_Brazil  .
                        ?resource dbp:name ?name .
                        FILTER (lang(?name ) = 'en')
                        BIND(
                        REPLACE(?name,"@en", "") 
                        AS ?stateName)
                }
                ORDER BY ?name
                """;

        List<Location> states = new ArrayList<>();
        Query query = QueryFactory.create(sparqlQuery);

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode label = soln.get("stateName");

                if(label == null) continue;

                var location = new Location();
                location.setName(label.toString());
                states.add(location);
            }
        }
        return states;
    }
}
