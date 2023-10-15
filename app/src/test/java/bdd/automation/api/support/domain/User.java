package bdd.automation.api.support.domain;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class User {
    @Builder.Default
    private int id = 10;
    @Builder.Default
    private String username = "rafalima";
    @Builder.Default
    private String firstName = "rafael";
    @Builder.Default
    private String lastName = "Lima";
    @Builder.Default
    private String email = "rafael@gmail.com";
    @Builder.Default
    private String password = "12345";
    @Builder.Default
    public String phone = "8199999999999";
    @Builder.Default
    public int userStatus = 1;

}
