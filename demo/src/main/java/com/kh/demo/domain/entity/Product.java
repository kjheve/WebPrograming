package com.kh.demo.domain.entity;

// wrapper class
// byte -> Byte , short -> Short , char->Charater, int -> Integer, long -> Long
// boolean -> Bollean, double -> Double, float -> Float


import java.time.LocalDateTime;

public class Product {
  private Long productId; // 상품 아이디 PRODUCT_ID NUMBER(10, 0)
  private String panme; // 상품명 PNAME VARCHAR2(30 BYTE)
  private Long quantity;	// 수량 QUANTITY NUMBER(10,0)
  private Long price;     // 가격 PRICE	NUMBER(10,0)
  private LocalDateTime cdate; // 생성일시 CDATE	TIMESTAMP(6)
  private LocalDateTime update; // 수정일시 UDATE	TIMESTAMP(6)

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getPanme() {
    return panme;
  }

  public void setPanme(String panme) {
    this.panme = panme;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public LocalDateTime getCdate() {
    return cdate;
  }

  public void setCdate(LocalDateTime cdate) {
    this.cdate = cdate;
  }

  public LocalDateTime getUpdate() {
    return update;
  }

  public void setUpdate(LocalDateTime update) {
    this.update = update;
  }

  @Override
  public String toString() {
    return "Product{" +
            "productId=" + productId +
            ", panme='" + panme + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            ", cdate=" + cdate +
            ", update=" + update +
            '}';
  }
}
