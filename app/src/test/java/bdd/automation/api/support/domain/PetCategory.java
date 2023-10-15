package bdd.automation.api.support.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetCategory {

    @Builder.Default
    private int id = 2;
    @Builder.Default
    private String name = "Cats";

}
