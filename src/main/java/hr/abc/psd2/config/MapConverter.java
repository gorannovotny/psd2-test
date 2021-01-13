package hr.abc.psd2.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapConverter implements Converter<Map> {
    ObjectMapper mapper= new ObjectMapper();
    @Override
    public Map convert(String s) {
        Map map=new HashMap();
        try {
            map=mapper.readValue(s, Map.class);

           /* List<Object> list= (List<Object>) value.get(value.keySet().toArray()[0]);
            list.forEach(el-> {
                map.putAll((Map) el);
            });*/
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
}
