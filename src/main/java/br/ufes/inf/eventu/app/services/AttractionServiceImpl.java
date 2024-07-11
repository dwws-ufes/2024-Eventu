package br.ufes.inf.eventu.app.services;

import java.time.LocalTime;
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

import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.Topic;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionService;

@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionDAO attractionDAO;

    @Override
    public Attraction save(Attraction model) throws Exception {
        model.setCreatedAt(LocalTime.now());
        return attractionDAO.save(model);
    }

    @Override
    public Attraction edit(long id, Attraction model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }

    public List<Topic> retrieveTopicsRDF(){
        String sparqlEndpoint = "http://dbpedia.org/sparql";
        String sparqlQuery = """
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX owl: <http://www.w3.org/2002/07/owl#>
                PREFIX dbr: <http://dbpedia.org/resource/>
                PREFIX dbp: <http://dbpedia.org/property/>
                SELECT DISTINCT  ?label ?topic  WHERE {
                        ?_  dbp:discipline ?topic  .
                        ?topic   rdf:type owl:Thing .
                        ?topic   rdfs:label ?name
                        FILTER (lang(?name ) = 'pt')
                        BIND(
                        REPLACE(?name,"@pt", "") 
                        AS ?label)
                    }
                ORDER BY ?name
                """;

        List<Topic> topics = new ArrayList<>();
        Query query = QueryFactory.create(sparqlQuery);

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode label = soln.get("label");
                RDFNode topicUri = soln.get("topic");

                if(label == null) continue;

                var topic = new Topic();
                topic.setLabel(label.toString());
                topic.setTopic(topicUri.toString());
                topics.add(topic);
            }
        }
        return topics;
    }
}
