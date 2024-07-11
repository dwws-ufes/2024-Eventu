package br.ufes.inf.eventu.app.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Topic {
    @Setter
    private String label;

    @Setter
    private String topic;

}
