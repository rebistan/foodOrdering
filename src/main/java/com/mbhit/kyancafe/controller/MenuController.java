
package com.mbhit.kyancafe.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbhit.kyancafe.entity.Category;
import com.mbhit.kyancafe.entity.CategoryForFilter;
import com.mbhit.kyancafe.entity.Favourites;
import com.mbhit.kyancafe.entity.Product;
import com.mbhit.kyancafe.entity.ProductForFilter;
import com.mbhit.kyancafe.repository.RepCat;
import com.mbhit.kyancafe.repository.RepPro;
import com.mbhit.kyancafe.repository.Categoryrepository;
import com.mbhit.kyancafe.repository.FavouriteRepository;
import com.mbhit.kyancafe.repository.MenuRepository;

/**
 * @author REBISTAN
 * MBH Information Technology
 *
 */
@RestController

@RequestMapping("/kyancafe")
public class MenuController {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	FavouriteRepository favouriteRepository;
	
	@Autowired
	Categoryrepository  categoryRepository;
	
	@Autowired
	RepCat catRep;
	
	@Autowired
	RepPro repPro;

	/*
	 * -------------MENU ----------------------
	 */

	private String filepath = "C:\\Users\\InworthIT-App\\Pictures\\";

	@RequestMapping(value = "addMenuItem", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Product addItem(Product menu, MultipartFile mf) throws IOException {
        ProductForFilter pro=new ProductForFilter();
		String image = filepath + mf.getOriginalFilename();
		File file = new File(image);
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(mf.getBytes());
		fout.close();
		menu.setImageurl(image);
		pro.setProductId(menu.getProductId());
		pro.setProductName(menu.getProductName());
		pro.setImageurl(menu.getImageurl());
		pro.setOffer(menu.getOffer());
		pro.setPrice(menu.getPrice());		
		repPro.save(pro);
		return menuRepository.save(menu);
	}
	
    @GetMapping("/showsMenu")
    public List<Category> catProduct()
    {
    	
		return categoryRepository.findAll();
    	
    }
    
    /*
     * -------FAVOURITES--------------------
     */
    
	@GetMapping("/favourites/{id}")
	public List<Favourites> findByUserId(@PathVariable("id") Integer id) {

		return favouriteRepository.getByUserid(id);
	}

	@PostMapping("/favourites/{id}/user/{user}")
	public Favourites addFav(@PathVariable(value="id") int id,@PathVariable(value="user") int user) {
		Favourites fav=new Favourites();
		Product prof=menuRepository.findById1(id);
		fav.setProductId(prof.getProductId());
		fav.setProductName(prof.getProductName());
		fav.setPrice(prof.getPrice());
		fav.setImageurl(prof.getImageurl());
	    fav.setUserid(user);
		return favouriteRepository.save(fav);
	}

	@DeleteMapping("favourites/{id}")
	public void deleteById(@PathVariable("id") Integer id) {

		favouriteRepository.deleteById(id);

	}
/*
 * ------------CATEGORY-------------------------	
 */
	@PostMapping("addCategory")
	public Category addCategory(Category cat) {
		CategoryForFilter caty=new CategoryForFilter();
		caty.setCategoryId(cat.getCategoryId());
		caty.setCategoryName(cat.getCategoryName());
		catRep.save(caty);
		return categoryRepository.save(cat);
	}
	
	@GetMapping("listCategory")
	public List<CategoryForFilter> getAll() {
		return catRep.findAll();
	}
}
