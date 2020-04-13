package br.com.jdrmservices.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.util.RecaptchaUtil;
import groovy.util.logging.Log4j;

@Log4j
@Service
public class RecaptchaService {
	
	private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	public String verifyRecaptcha(String ip, String recaptchaResponse) {
		Map<String, String> body = new HashMap<>();
		body.put("secret", "6LdHgN0UAAAAADzqh-ft5_WXQqgBIzOPV5sW_eVt");
		body.put("response", recaptchaResponse);
		body.put("remoteip", ip);
		//log.debug("Request body for recaptcha: {}", body);
		ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build().postForEntity(
				GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}&remoteip={remoteip}", body,
				Map.class, body);

		//log.debug("Response from recaptcha: {}", recaptchaResponseEntity);
		Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

		boolean recaptchaSucess = (Boolean) responseBody.get("success");
		if (!recaptchaSucess) {
			List<String> errorCodes = (List) responseBody.get("error-codes");

			String errorMessage = errorCodes.stream().map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s))
					.collect(Collectors.joining(", "));

			return errorMessage;
		} else {
			return StringUtils.EMPTY;
		}
	}
}
