package sk.upjs.ics.bakalarka.postgresql.convertor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonToJava {

    public static void main(String[] args) {
        JsonToJava obj = new JsonToJava();
        obj.run();
        //Study e = new Study();
    }

    private void run() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Example ex = mapper.readValue(new File("C:\\Users\\Juraj\\Desktop\\kucerave.json"), Example.class);

            System.out.println(ex);
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ex);
            System.out.println(prettyStaff1);
        } catch (IOException ex1) {
            Logger.getLogger(JsonToJava.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

}
