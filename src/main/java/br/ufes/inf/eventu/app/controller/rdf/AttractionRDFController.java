package br.ufes.inf.eventu.app.controller.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rdf/attractions")
public class AttractionRDFController {

    @Autowired
    private AttractionDAO attractionDAO;

    @Autowired
    private HttpServletRequest request;

    private static final String BASE_URI = "https://eventu.inf.ufes.br/api/rdf/";
    private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    @GetMapping("/{id}")
    public ResponseEntity<String> getAttractionRDF(@PathVariable Long id) {

        var attraction = attractionDAO.findById(id).orElse(null);
        if (attraction == null) {
            return ResponseEntity.notFound().build();
        }

        var model = ModelFactory.createDefaultModel();
        String attractionUri = BASE_URI + "attractions/" + attraction.getId();
        var attractionResource = model.createResource(attractionUri);

        attractionResource.addProperty(FOAF.name, attraction.getName());
        attractionResource.addProperty(DC.description, attraction.getDescription());

        if (attraction.getTopic() != null && !attraction.getTopic().isEmpty()) {
            Property disciplineProperty = model.createProperty("http://dbpedia.org/property/discipline");
            attractionResource.addProperty(disciplineProperty, attraction.getTopic());
        }

        var hasAttraction = model.createProperty(BASE_URI + "attractions/hasSpeakers");
        attraction.getSpeakers().forEach(x ->
            attractionResource.addProperty(hasAttraction, BASE_URI + "speakers/" + x.getId())
        );

        var rdfOutput = modelToString(model, "Turtle");
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/turtle");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attraction.ttl\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(rdfOutput);
    }

    private String modelToString(Model model, String format) {
        var out = new java.io.StringWriter();
        model.write(out, format);
        return out.toString();
    }
}