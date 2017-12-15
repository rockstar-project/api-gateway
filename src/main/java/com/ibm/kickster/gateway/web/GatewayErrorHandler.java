package com.ibm.kickster.gateway.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ibm.kickster.gateway.dto.Error;

@RestController
public class GatewayErrorHandler implements ErrorController {
	
	private static final String ERROR_PATH = "/error";
	@Autowired
    private ErrorAttributes errorAttributes;
	
	@RequestMapping(value = ERROR_PATH)
    public ResponseEntity<Error> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, false);
        Error error = new Error((String) errorAttributes.get("error"), (String) errorAttributes.get("message"));
        return new ResponseEntity<Error>(error, this.httpstatus(response.getStatus()));
    }
	
	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

	public String getErrorPath() {
		return ERROR_PATH;
	}
	
	private HttpStatus httpstatus(int statuscode) {
		HttpStatus status = HttpStatus.OK;
		switch (statuscode) {
			case 400:
				status = HttpStatus.BAD_REQUEST;
				break;
			case 401: 
			case 403:
				status = HttpStatus.UNAUTHORIZED;
				break;
			case 404:
				status = HttpStatus.NOT_FOUND;
				break;
			case 409:
				status = HttpStatus.CONFLICT;
				break;
			default:
				break;
				
		}
		return status;
	}
	
}
