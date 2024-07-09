package br.ufes.inf.eventu.app.controller.rdf;

import org.springframework.web.bind.annotation.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.ufes.inf.eventu.app.domain.Speaker;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;

@RestController
@RequestMapping("/api/rdf/speakers")
public class SpeakerRDFController {

    @Autowired
    private SpeakerDAO speakerRepository;

    private static final String BASE_URI = "https://eventu.inf.ufes.br/";
    private static final String FOAF_NS = "http://xmlns.com/foaf/0.1/";

    @GetMapping("/{id}")
    public ResponseEntity<String> getSpeakerRDF(@PathVariable Long id) {
        Speaker speaker = speakerRepository.findById(id).orElse(null);
        if (speaker == null) {
            return ResponseEntity.notFound().build();
        }

        Model model = ModelFactory.createDefaultModel();
        String speakerUri = BASE_URI + speaker.getId();
        Resource speakerResource = model.createResource(speakerUri);

        speakerResource.addProperty(FOAF.name, speaker.getName());
        speakerResource.addProperty(DC.description, speaker.getDescription());

        // Link birthPlace to DBpedia if available
        if (speaker.getBirthplace() != null && !speaker.getBirthplace().isEmpty()) {
            String dbpediaUri = "http://dbpedia.org/resource/" + speaker.getBirthplace().replace(" ", "_");
            speakerResource.addProperty(model.createProperty("http://dbpedia.org/ontology/birthPlace"), 
                                        model.createResource(dbpediaUri));
        }

        // Add attractions
        Property hasAttraction = model.createProperty(BASE_URI + "hasAttraction");
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