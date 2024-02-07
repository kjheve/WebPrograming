package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository // DAO 역할을 하는 클래스
public class ProductDAOImpl implements ProductDAO {

  private final NamedParameterJdbcTemplate template;

  ProductDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public Long save(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(product_id,pname,quantity,price) ");
    sql.append("values(product_product_id_seq.nextval, :pname, :quantity, :price) ");

    // SQL파라미터 자동매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
//    template.update(sql.toString(), param, keyHolder, new String[]{"product_id", "pname"}); // ★두개 넣으면 에러뜸
    template.update(sql.toString(), param, keyHolder, new String[]{"product_id"});
    long productId = keyHolder.getKey().longValue(); // 상품 아이디 // ★여기서 한개만 받기 때문


//    template.update(sql.toString(), param, keyHolder, new String[]{"product_id", "pname", "quantity"});
////    log.info("keyHolder={}", keyHolder.getKeys());
//    String pname = (String)keyHolder.getKeys().get("pname");
//    log.info("pname = {}", pname);
//    Long product_id = ((BigDecimal)keyHolder.getKeys().get("product_id")).longValue();
//    log.info("product_id = {}", product_id);
//return product_id;
    return productId;
  }
}
