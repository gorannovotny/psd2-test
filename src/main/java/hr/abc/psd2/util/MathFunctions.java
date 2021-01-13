package hr.abc.psd2.util;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * @author comp2
 */

public class MathFunctions {

	/**
	* Pretvara BigDecimal koji vraća hibernate iz oracle baze podataka u integer.
	*
	* @param number
	* @return
	* @author Matija Hlapčić
	*/
	public static Integer resolveOracleNumberToInteger(Object number) {
		Integer result = null;
		try {
			if (number != null) {
				if (number instanceof Integer) {
					result = (Integer) number;
				} else {
					result = Integer.parseInt(number.toString());
				}
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(MathFunctions.class.getName()).warn(e.getMessage(), e);
			return null;
		}
		return result;
	}

}
