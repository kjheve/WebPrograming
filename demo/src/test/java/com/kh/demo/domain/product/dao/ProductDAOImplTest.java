package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest // springboot 테스트환경 실행
class ProductDAOImplTest {

  @Autowired // springboot 컨테이너의 객체를 주입 받는다.
  ProductDAO productDAO;

  @Test
  void save() {

    Product product = new Product();
    product.setPname("노트북");
    product.setQuantity(3L);
    product.setPrice(2_000_000L);


    Long productId = productDAO.save(product);
//    log.info("productId={}{}", productId, "2"); // 오류발생, 정상작동은 함 (오류+DB에 목록의 제일 위로 올라감)
    log.info("productId={}{}", productId);
    
  }
}