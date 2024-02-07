// ProductSVC 구현 클레스

package com.kh.demo.domain.product.svc;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service // SVC 역할을 하는 클래스
public class ProductSVCImpl implements ProductSVC{

  private ProductDAO productDAO; // 이 객체가 생성될 때

  ProductSVCImpl(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }
}