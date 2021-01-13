
package hr.abc.psd2.resources.ws.mapper;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Dependent
@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {

	private final ObjectMapper objectMapper;

	public JacksonConfig() {

		objectMapper = new ObjectMapper();

		JavaTimeModule javaTimeModule = new JavaTimeModule();

//		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(ISO_LOCAL_DATE_TIME));
//		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ISO_LOCAL_DATE_TIME));

		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ISO_LOCAL_DATE_TIME));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ISO_LOCAL_DATE_TIME));
		objectMapper.registerModule(javaTimeModule);

		SimpleModule module = new SimpleModule();
		objectMapper.registerModule(module);

		objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.setSerializationInclusion(NON_NULL);
	}

	@Override
	public ObjectMapper getContext(Class<?> aClass) {
		return objectMapper;
	}
}