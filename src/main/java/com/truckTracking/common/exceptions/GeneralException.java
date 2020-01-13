package com.truckTracking.common.exceptions;

import com.truckTracking.common.utils.Constants.SYSTEM_ERRORS;

public class GeneralException extends SystemException {

	public GeneralException() {
		super(SYSTEM_ERRORS.GENERAL_ERROR.getCode(), SYSTEM_ERRORS.GENERAL_ERROR.getDescription());
	}

	public GeneralException(String msg, Throwable cause) {
		super(SYSTEM_ERRORS.GENERAL_ERROR.getCode(), SYSTEM_ERRORS.GENERAL_ERROR.getDescription(), msg, cause);
	}

	public GeneralException(Throwable cause) {
		super(SYSTEM_ERRORS.GENERAL_ERROR.getCode(), SYSTEM_ERRORS.GENERAL_ERROR.getDescription(), cause);
	}

}
