package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // DAO 역할을 하는 클래스
public class ProductDAOImpl implements ProductDAO {

  private final NamedParameterJdbcTemplate template;
  ProductDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Long save(Product product) {



    return 0L;
  }
}
