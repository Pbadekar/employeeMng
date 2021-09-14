package com.study.geeks.core.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.geeks.core.config.ReportConfig;
import com.study.geeks.core.services.GetLoginDetailsService;

@Component(service = GetLoginDetailsService.class, immediate = true)

public class GetLoginDetailsImpl implements GetLoginDetailsService {
@Reference
ReportConfig reportConfig;

	@Reference
	private ResourceResolverFactory resolverFactory;
	private static final Logger LOG = LoggerFactory.getLogger(GetLoginDetailsImpl.class);

	@Override
	public String VerifyUser(String id, String pass) {
		String Message = "Incorrect Id or Pass1";
		LOG.debug("id");
		LOG.debug("1."+reportConfig.Getschedulerstatus());
		Map<String, Object> param = new HashMap<String, Object>();
		ResourceResolver resourceResolver = null;
		Session session = null;
		param.put(ResourceResolverFactory.SUBSERVICE, "prakashserviceuser");
		try {
			String resourcePath = "/content/studygeeks/us/en/Data/" + id;

			resourceResolver = resolverFactory.getServiceResourceResolver(param);
			session = resourceResolver.adaptTo(Session.class);
			Node node = session.getNode(resourcePath);

			String Userid = node.getProperty("id").getString();
			String password = node.getProperty("pwd").getString();

			if (Userid.equalsIgnoreCase(id) && password.equals(pass)) {

				Message = "Validated";
			}

		} catch (Exception E) {
			LOG.info("Error VerifyUser" + E);

		}
		return Message;
	}

	@Override
	public String CreateUser(String fname, String lname, String add, String id, String pwd) {

		String Message = null;
		// TODO Auto-generated method stub
		try {

			Map<String, Object> param = new HashMap<String, Object>();
			ResourceResolver resourceResolver = null;
			Session session = null;
			param.put(ResourceResolverFactory.SUBSERVICE, "prakashserviceuser");

			String resourcePath = "/content/studygeeks/us/en/Data";

			resourceResolver = resolverFactory.getServiceResourceResolver(param);
			session = resourceResolver.adaptTo(Session.class);
			Node node = session.getNode(resourcePath);
			Node unstructure = null;

			if (node != null) {

				if (node.hasNode(id)) {
					Message = "User is already Exists.";
				}

				unstructure = node.addNode(id, "nt:unstructured");
				if (unstructure != null) {

					unstructure.setProperty("id", id);
					unstructure.setProperty("pwd", pwd);
					unstructure.setProperty("fname", fname);
					unstructure.setProperty("lname", lname);
					unstructure.setProperty("add", add);
					Message = "User saved Successfully.";
				}
			}

			session.save();
			session.logout();
			LOG.debug("Login_id" + id);
			LOG.debug("pwd" + pwd);
		}

		catch (Exception E) {

			LOG.info("Error CreateUser" + E);
		}

		return Message;

	}

}
