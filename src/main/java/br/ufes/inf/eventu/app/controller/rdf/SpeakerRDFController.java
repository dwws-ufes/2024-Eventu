package br.ufes.inf.eventu.app.controller.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.inf.eventu.app.domain.Speaker;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;

@RestController
@RequestMapping("/api/rdf/speakers")
public class SpeakerRDFController {

    @Autowired
    private SpeakerDAO speakerRepository;

    private static final String BASE_URI = "https://eventu.inf.ufes.br/api/rdf/";
    private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    @GetMapping("/{id}")
    public ResponseEntity<String> getSpeakerRDF(@PathVariable Long id) {
        Speaker speaker = speakerRepository.findById(id).orElse(null);
        if (speaker == null) {
            return ResponseEntity.notFound().build();
        }

        Model model = ModelFactory.createDefaultModel();
        String speakerUri = BASE_URI + "speakers/" + speaker.getId();
        Resource speakerResource = model.createResource(speakerUri);

        speakerResource.addProperty(FOAF.name, speaker.getName());
        speakerResource.addProperty(DC.description, speaker.getDescription());

        if (speaker.getBirthplace() != null && !speaker.getBirthplace().isEmpty()) {
            String dbpediaUri = "http://dbpedia.org/resource/" + speaker.getBirthplace().replace(" ", "_");
            speakerResource.addProperty(model.createProperty("http://dbpedia.org/ontology/birthPlace"), 
                                        model.createResource(dbpediaUri));
        }

        Property hasAttraction = model.createProperty(BASE_URI + "speakers/hasAttraction");
        speaker.getAttractions().forEach(attraction -> 
            speakerResource.addProperty(hasAttraction, BASE_URI + "attractions/" + attraction.getId())
        );

        String rdfOutput = modelToString(model, "TURTLE");
        return ResponseEntity.ok()
                .header("Content-Type", "text/turtle")
                .body(rdfOutput);
    }

    private String modelToString(Model model, String format) {
        java.io.StringWriter out = new java.io.StringWriter();
        model.write(out, format);
        return out.toString();
    }
}