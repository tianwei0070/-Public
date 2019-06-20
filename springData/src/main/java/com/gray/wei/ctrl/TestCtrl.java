package com.gray.wei.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gray.wei.bean.Teacher;
import com.gray.wei.service.SpringDataService;

/**
 * 测试用
 * 
 * @author TianWei
 *
 */
@Controller
@RequestMapping("/test")
public class TestCtrl {

	@Autowired
	private SpringDataService springDataService;

	/**
	 * 查询活动任务完成状态
	 */
	@RequestMapping(value = "getTeacher", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getActivityTaskStat(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/plain;charset=UTF-8");

		JSONObject jo = new JSONObject();
		String remoteIp = req.getHeader("x-forwarded-for");
		if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
			remoteIp = req.getHeader("Proxy-Client-IP");
			if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
				remoteIp = req.getHeader("WL-Proxy-Client-IP");
				if (remoteIp == null || remoteIp.length() == 0 || remoteIp.equalsIgnoreCase("unknown")) {
					remoteIp = req.getRemoteAddr();
				}
			}
		}

		try {
			List<Teacher> data = springDataService.getTeacher();
			jo.put("time", System.currentTimeMillis());
			jo.put("data", data);
			return jo.toJSONString();
		} catch (Exception e) {
			jo.put("code", "-4");
			jo.put("time", System.currentTimeMillis());
			jo.put("msg", "服务器异常");
			return jo.toJSONString();
		} finally {

		}
	}

}
