
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;

public class Main {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("http://www.arizmendibakery.com/pizza").get();
        Elements pizzas = doc.select("p.yasp-item");

        try (final WebClient webClient = new WebClient()) {

            HtmlPage page1 = webClient.getPage("http://www.arizmendibakery.com/pizza");


            HtmlForm form = page1.getFormByName("myform");

            HtmlSubmitInput button = form.getInputByName("SUBMIT");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
        }




        BufferedWriter output = null;
        try {
            File file = new File("arizmendi.tsv");
            output = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < pizzas.size(); i++) {

                Element pizza = pizzas.get(i);

                output.write(pizza.text() + "\t");

            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
    }

}
