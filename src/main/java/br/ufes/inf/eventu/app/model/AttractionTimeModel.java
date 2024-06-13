package br.ufes.inf.eventu.app.model;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttractionTimeModel {

    private Long attractionId;

    private String date;

    private String start;

    private String finish;

    private Long locationId;
}
