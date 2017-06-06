/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.bakalarka.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeDeserializer extends JsonDeserializer<Time> {

    private SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

    @Override
    public Time deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try {
            String timeString = jp.getText().trim();
            Date time = sdf.parse(timeString);
            Time sqlTime = new Time(time.getTime());
            return sqlTime;

        } catch (ParseException ex) {
            Logger.getLogger(DateTimeDeserializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
