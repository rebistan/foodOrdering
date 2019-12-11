package com.mbhit.kyancafe.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbhit.kyancafe.config.ResourceNotFoundException;
import com.mbhit.kyancafe.entity.Product;
import com.mbhit.kyancafe.entity.ProductForFilter;
import com.mbhit.kyancafe.repository.MenuRepository;
import com.mbhit.kyancafe.repository.RepPro;

/**
 * @author REBISTAN
 *  MBH Information Technology
 */

@RestController
@RequestMapping("/kyancafe")
public class OnoffersController {
@Autowired
MenuRepository menuRepository;
@Autowired
RepPro repPro;
	/*
	 * @GetMapping("offer") public List<Product>showOffer() { return
	 * menuRepository.ProductId() ;
	 * 
	 * }
	 */

/*
 * -------------ADD OFFERS-----------------------
 */

@PutMapping("addOffers/{id}")
public ResponseEntity<Product> addOffers(@PathVariable(value = "id") Integer id,
		@Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
	Product product = menuRepository.findById(id)
			
	.orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + id));
    ProductForFilter pro=repPro.findById1(id);
	
	 product.setProductId(productDetails.getProductId());
     product.setProductName(productDetails.getProductName());
     product.setPrice(productDetails.getPrice());
     product.setImageurl(productDetails.getImageurl());
     product.setCategoryId(productDetails.getCategoryId());
     product.setOffer(productDetails.getOffer());
     
     pro.setProductId(productDetails.getProductId());
     pro.setProductName(productDetails.getProductName());
     pro.setPrice(productDetails.getPrice());
     pro.setImageurl(productDetails.getImageurl());
     pro.setOffer(productDetails.getOffer());
     
	final Product offerupdated=menuRepository.save(product);
	return ResponseEntity.ok(offerupdated);
}

/*
 * --------------VIEW OFFERS---------------------
 */

@GetMapping("showOffers")
public ProductForFilter showOffers(){
	return repPro.showOffers();
}
}