package com.study.geeks.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.geeks.core.services.GetLoginDetailsService;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/prakash/addNodes" })

public class LoginServlet extends SlingAllMethodsServlet {
	@Reference
	GetLoginDetailsService getLoginDetailsService;
	/**
	 * 
	 */

	private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

	private static final long serialVersionUID = 1L;
	String status = "System Error";

	@Override
	protected void doPost(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {

		try {

			String Jsondata = req.getParameter("data");
			LOG.info("Jsondata" + Jsondata);
			JSONObject json = new JSONObject(Jsondata);
			String flag = json.getString("flg");

			if (flag.equals("Save")) {
				LOG.info("inside of Save");

				String FirstName = json.getString("fname");
				String LastName = json.getString("lname");
				String Address = json.getString("add");
				String UserID = json.getString("id");
				String Password = json.getString("pwd");
				status = getLoginDetailsService.CreateUser(FirstName, LastName, Address, UserID, Password);
			}

			if (flag.equals("Login")) {
				LOG.info("inside of Login");
				String FirstName = json.getString("fname");
				String Password = json.getString("pwd");

				status = getLoginDetailsService.VerifyUser(FirstName, Password);
			} else {
				LOG.info("inside of Else");

			}

		} catch (Exception E) {
			LOG.info("Error " + E.toString());
		} finally {

			LOG.info("Connection cloased");
			res.getWriter().write(status);
			;
		}

	}

}
