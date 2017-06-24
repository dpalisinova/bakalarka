package sk.upjs.ics.bakalarka.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

class TimeSerializer extends JsonSerializer<Time> {

    private SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

    @Override
    public void serialize(Time t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String formattedTime = sdf.format(t);
        jg.writeString(formattedTime);
    }

}
