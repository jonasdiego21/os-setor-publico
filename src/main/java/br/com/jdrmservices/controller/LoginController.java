package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.VIEW_LOGIN;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jdrmservices.service.RecaptchaService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private RecaptchaService recaptchaService;
	
	@GetMapping
	public String Login(@AuthenticationPrincipal User user) {

		if(user != null) {
			return "redirect:/";
		}
		
		return VIEW_LOGIN;
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> recaptcha(@AuthenticationPrincipal User user,
			@RequestParam(name = "g-recaptcha-response") String recaptchaResponse, HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		String captchaVerifyMessage = recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

		if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			
			return ResponseEntity.badRequest().body(response);
		}
		
		return ResponseEntity.ok().build();
	}
}