package fanjoker.cash.commands;

import java.math.BigDecimal;

public class Numbers {

	public Numbers() {
	}

	public BigDecimal getDecimal(String numberString) {
		try {
			return new BigDecimal(numberString);
		} catch (Exception exception) {
			return new BigDecimal(-1.0);
		}
	}
}
