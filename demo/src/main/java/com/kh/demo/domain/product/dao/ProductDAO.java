// DAO 코드

package com.kh.demo.domain.product.dao;


import com.kh.demo.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
  // ★등록
  Long save(Product product);

  // ★조회
  // Product findByID(Long productId); // Product가 null일 수도 있기 때문에
  // Optional 객체를 최대 1개를 저장할 수 있는 컬렉션
  Optional<Product> findByID(Long productId);

  // ★목록
  List<Product> findAll();


  // ★단건삭제
  int deleteById(Long productId);

  // ★여러건삭제
  int deleteByIds(List<Long> productIds);

  // ★수정
  int updateById(Long productId, Product product);
}
