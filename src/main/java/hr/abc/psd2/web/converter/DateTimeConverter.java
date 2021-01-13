package hr.abc.psd2.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Kristijan on 31.01.19..
 */
@FacesConverter("dateTimeConverter")
public class DateTimeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss.S"));
        } catch (IllegalArgumentException | DateTimeException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Message"), e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }

        if (!(value instanceof LocalDateTime)) {
            throw new ConverterException("Message");
        }

        return DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(((LocalDateTime) value));
        // According to a time zone of a specific user.
    }
}
