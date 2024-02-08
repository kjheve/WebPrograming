package com.kh.demo.domain.product.dao;

import com.kh.demo.domain.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository // DAO 역할을 하는 클래스
public class ProductDAOImpl implements ProductDAO {

  private final NamedParameterJdbcTemplate template;

  // 생성자
  ProductDAOImpl(NamedParameterJdbcTemplate template) {
    this.template = template;
  }


  // ★등록
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

  // ★조회
  @Override
  public Optional<Product> findByID(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, pname, quantity, price, cdate, udate ");
    sql.append("from product ");
    sql.append("where product_id = :productId ");

    // RowMapper
    // Product product = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(Product.class));
                          // queryForObject는 값이 없으면 예외를 발생시킴 그래서 아래처럼

    try {
      Map<String, Long> map = Map.of("productId", productId); // (키, 값)
      Product product = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(Product.class));
      return Optional.of(product);
    } catch (EmptyResultDataAccessException e) {
      //조회 결과가 없는 경우
      return Optional.empty();
    }
  }

  // ★목록
  @Override
  public List<Product> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, pname, quantity, price, cdate, udate ");
    sql.append("from product ");
    sql.append("order by product_id desc ");

    List<Product> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Product.class));

    return list;
  }


}
