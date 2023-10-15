package bdd.automation.api.support.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class Pet {

    @Builder.Default
    private int id  = 2;
    @Builder.Default
    private String name = "Panthro";
    @Builder.Default
    private PetCategory category = new PetCategory();
    @Builder.Default
    private String status = "available";
    @Builder.Default
    private List<String> photosUrl = Arrays.asList("url1", "url2");


}
