package hr.abc.psd2.exception;

import java.util.Arrays;
import java.util.List;

import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.error.ErrorCode;

public class ConsentAISException extends Exception {

	private static final long serialVersionUID = 1L;

	private ErrorCode code;
	private List<String> errors;
	private Throwable throwable;
	private RequestType reqType;
	
	private ConsentAISException() {

	}

	private ConsentAISException(Builder builder) {
		super(builder.throwable);
		this.code = builder.code;
		this.errors = builder.errors;
		this.throwable = builder.throwable;
		this.reqType = builder.reqType;		
	}

	public static class Builder {

		private ErrorCode code;
		private List<String> errors;
		private Throwable throwable;
		private RequestType reqType;

		public Builder withCode(ErrorCode code) {
			this.code = code;
			return this;
		}

		public Builder withThrowable(Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public Builder withErrors(String... errors) {
			this.errors = Arrays.asList(errors);
			return this;
		}
		
		public Builder withReqType(RequestType reqType) {
			this.reqType = reqType;
			return this;
		}		
		
		public ConsentAISException build() {
			return new ConsentAISException(this);
		}
	}

	public ErrorCode getCode() {
		return code;
	}

	public List<String> getErrors() {
		return errors;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public RequestType getReqType() {
		return reqType;
	}
}