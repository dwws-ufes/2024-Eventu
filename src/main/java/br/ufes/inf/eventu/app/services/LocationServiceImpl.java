package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.Location;
import br.ufes.inf.eventu.app.persistence.LocationDAO;
import br.ufes.inf.eventu.app.services.interfaces.LocationService;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX foaf: <http://xmlns.com/foaf/0.1/>
                PREFIX yago: <http://dbpedia.org/class/yago/>
                SELECT DISTINCT ?name WHERE {
                    ?state a yago:WikicatStatesOfBrazil .
                    OPTIONAL {
                        ?state foaf:name ?label .
                        BIND(
                        REPLACE(
                        REPLACE(
                        REPLACE(
                        REPLACE(
                        REPLACE(
                        REPLACE(?label, 
                        "Estado de ", ""), 
                        "Estado do ", ""), 
                        "Estado da ", ""), 
                        "Administrative Region of ", ""), 
                        "Região Administrativa de ", ""), 
                        "State of ", "") 
                        AS ?name)
                    }
                }
                ORDER BY ?name
                """;

        List<Location> states = new ArrayList<>();
        Query query = QueryFactory.create(sparqlQuery);

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode label = soln.get("name");

                if(label == null) continue;

                var location = new Location();
                location.setName(label.toString());
                states.add(location);
            }
        }
        return states;
    }
}
