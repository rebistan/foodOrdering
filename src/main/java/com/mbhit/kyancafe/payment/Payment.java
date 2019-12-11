/*
 * package com.mbhit.kyancafe.payment;
 * 
 * import java.util.Date; import javax.validation.constraints.NotNull;
 * 
 * import org.hibernate.validator.constraints.CreditCardNumber; import
 * com.fasterxml.jackson.annotation.JsonCreator; import
 * com.fasterxml.jackson.annotation.JsonProperty;
 * 
 * public class Payment {
 * 
 * @NotNull CardType cardType;
 * 
 * @NotNull
 * 
 * @CreditCardNumber String cardNumber;
 * 
 * @NotNull Date expirationDate;
 * 
 * 
 * 
 * 
 * @JsonCreator public Payment(@JsonProperty("cardType") CardType
 * cardType, @JsonProperty("cardNumber") String
 * cardNumber, @JsonProperty("expirationDate") Date expirationDate ) { super();
 * this.cardType = cardType; this.cardNumber = cardNumber; this.expirationDate =
 * expirationDate;
 * 
 * }
 * 
 * public CardType getCardType() { return cardType; }
 * 
 * public String getCardNumber() { return cardNumber; }
 * 
 * public Date getExpirationDate() { return expirationDate; } }
 */