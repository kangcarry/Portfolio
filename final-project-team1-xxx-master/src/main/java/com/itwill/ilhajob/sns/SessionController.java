package com.itwill.ilhajob.sns;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
	 @GetMapping("/sessionAjax")
	    public Map<String, Object> getSessionInfo(HttpSession session) {
	        Map<String, Object> result = new HashMap<>();
	        String snsType = (String) session.getAttribute("snsType");
	        result.put("snsType", snsType);
	        return result;
	    }
}
