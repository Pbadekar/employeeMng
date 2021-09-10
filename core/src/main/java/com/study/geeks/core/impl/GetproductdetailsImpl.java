package com.study.geeks.core.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.study.geeks.core.beans.productdls;
import com.study.geeks.core.services.ProductdetailsService;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ProductdetailsService.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GetproductdetailsImpl implements ProductdetailsService {

	private static final Logger log = LoggerFactory.getLogger(GetproductdetailsImpl.class);
	@Inject
	Resource ColumnList;

	@Override
	public List<productdls> getProductdetails() {
 

//		ArrayList<String> AL = new ArrayList<String>();
		productdls productdlsVo ;
//		productdls productdlsVo = new productdls();
		List<productdls> LI = new ArrayList<productdls>();
		try {

			Resource productdetails = ColumnList.getChild("prodcutdetails");
			if (productdetails != null) {
				for (Resource product : productdetails.getChildren()) {
					  productdlsVo = new productdls();
					log.info("ProductName fetched --------------> "+product.getValueMap().get("ProductName", String.class) );
					log.info("price fetched -------------->  "+product.getValueMap().get("price", String.class) );
					
					productdlsVo.setProductName(product.getValueMap().get("ProductName", String.class));
					productdlsVo.setPrice(product.getValueMap().get("price", String.class));
					productdlsVo.setImageURL(product.getValueMap().get("ImageURL", String.class));
					productdlsVo.setProductlink(product.getValueMap().get("productlink", String.class));
					LI.add(productdlsVo);
					
				}
				
			for (int i=0;i<LI.size();i++) {
				log.info("product name ----->"+ LI.get(i).getProductName());
				log.info("product Price -----> "+LI.get(i).getPrice());
				log.info("product URL -----> "+LI.get(i).getImageURL());
				log.info("product Link -----> "+LI.get(i).getProductlink());
				
			}

			}
		} catch (Exception E) {
			log.info("Error" + E);

		}
		return LI;
	}

}
