package com.study.geeks.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.geeks.core.services.LoginDetailsExportService;

@Component(immediate = true, service = LoginDetailsExportService.class)
public class LogindetailsexportImpl implements LoginDetailsExportService {

	@Reference
	private ResourceResolverFactory resolverFactory;

	private final Logger Log = LoggerFactory.getLogger(LogindetailsexportImpl.class);

	List<Node> childrenList;

	@Override

	public void Exportlogindetails() {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		ResourceResolver resorceResolver = null;
		Session session = null;
		ArrayList<String> userArray = new ArrayList<String>();
		param.put(ResourceResolverFactory.SUBSERVICE, "prakashserviceuser");
		// List<Node> childrenList;
		try {

			String Resourcepath = "/content/studygeeks/us/en/Data/";
			childrenList = new ArrayList<Node>();
			resorceResolver = resolverFactory.getServiceResourceResolver(param);
			session = resorceResolver.adaptTo(Session.class);
			Node node = session.getNode(Resourcepath);

			collectChildList(node);

			Iterator<Node> it = childrenList.iterator();
			int i = 0;
			while (it.hasNext()) {
				Log.info(it.next().getPath());
				Node node1 = session.getNode(it.next().getPath());
				if (i >= 1) {

					Log.info("Path----------> " + session.getNode(it.next().getPath()));
					String Userid = node1.getProperty("id").getString();
					String password = node1.getProperty("pwd").getString();
					Log.info("Userid---------> " + Userid);
					Log.info("password---------->" + password);

					userArray.add(Userid);
					userArray.add(password);

				}
				i++;
			}

			//Log.info("userArray" + userArray.size() + "  " + userArray.get(0));

		} catch (Exception E) {
//			StackTraceElement l = new Exception().getStackTrace()[0];
			  E.printStackTrace();

		}
	}

	private void collectChildList(Node node) {
		try {
			childrenList.add(node);
			if (node.hasNodes()) {
				NodeIterator ni = node.getNodes();
				while (ni.hasNext()) {
					//Log.info("node" + ni.toString());
					collectChildList(ni.nextNode());
				}
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 
		}

	}
}
