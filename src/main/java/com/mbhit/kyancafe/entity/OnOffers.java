/*
 * package com.mbhit.kyancafe.entity;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.Id; import javax.persistence.OneToMany;
 * 
 * @Entity public class OnOffers {
 * 
 * @Id
 * 
 * @Column(name="offerId") private int offerId;
 * 
 * @Column(name="offer") private int offer;
 * 
 * @OneToMany(mappedBy="offerId") private List<Product> products; public int
 * getOfferId() { return offerId; } public void setOfferId(int offerId) {
 * this.offerId = offerId; } public OnOffers(int offerId, int offer,
 * List<Product> pro) { super(); this.offerId = offerId; this.offer = offer;
 * this.products = pro; } public OnOffers() {
 * 
 * } public int getOffer() { return offer; } public void setOffer(int offer) {
 * this.offer = offer; } public List<Product> getProducts() { return products; }
 * public void setPro(List<Product> products) { this.products = products; }
 * 
 * 
 * }
 */