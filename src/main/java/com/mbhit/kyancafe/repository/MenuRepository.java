
package com.mbhit.kyancafe.repository;
  
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbhit.kyancafe.entity.Product;
  
  public interface MenuRepository extends JpaRepository<Product, Integer> {
 
  @Query("from Product where productId=:productId")
   Product getProductDetails(int productId);
  @Query("from Product where productId=:productId")
  Product findById1(int productId);

	/*
	 * @Query("Select o from Product o where o.offer >10 Order by o.offer DESC")
	 * List<Product> ProductId();
	 */

  
  }
 