package bdd.automation.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) // roda com a classe do cucumber
@CucumberOptions(
        tags = "not @wip and not @quarentine", // oque quer rode e não rode
        plugin = { "pretty", "html:build/reports/feature.html" }, // adiciona plugins
        features = { "src/test/java/resources/features" } // caminho das features
)
public class CucumberRunner {

}
