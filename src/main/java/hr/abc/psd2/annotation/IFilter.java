package hr.abc.psd2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 	Filter annotation that holds entity attribute name.
 * 	Attribute name must be prefixed with prefix that is used in basic sql (GenericDao --> getBasicSql()).
 *
 *	@author Matija Hlapčić
 */
@Target(value={ElementType.FIELD, ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface IFilter {

	/**
	 * Entity's attribute name.
	 *
	 * @return
	 */
	String entityField();

}
