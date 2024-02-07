package com.kh.demo.web;

import com.kh.demo.domain.product.svc.ProductSVC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Controller 역할을 하는 클래스
@RequestMapping("/products") // URL 매핑 // http://localhost:9080/products
public class ProductController {

  private ProductSVC productSVC;
  ProductController(ProductSVC productSVC) {
    this.productSVC = productSVC;
  }

  // 상품등록양식
  @GetMapping("/add") // http://localhost:9080/products/add
  public String addForm() {


    return "products/add"; // view 이름
    // resources/templates/products/add.html 파일 이름이 맞아야함
  }

}
