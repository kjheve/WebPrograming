package com.kh.demo.web;

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller // Controller 역할을 하는 클래스
@RequestMapping("/products") // URL 매핑 // http://localhost:9080/products
public class ProductController {

  private ProductSVC productSVC;
  ProductController(ProductSVC productSVC) {
    this.productSVC = productSVC;
  }

  // 상품등록양식
  @GetMapping("/add") // Get, http://localhost:9080/products/add
  public String addForm() {


    return "products/add"; // view 이름 (상품 등록 화면)
    // resources/templates/products/add.html 파일 이름이 맞아야함
  }

  // 상품등록처리
  @PostMapping("/add") // Post, http://localhost:9080/products/add
  public String add(@RequestParam("pname") String pname,
                    @RequestParam("quantity") Long quantity,
                    @RequestParam("price") Long price,
                    Model model) {
    // @RequestParam() HTML의 input:name=""과 매핑
    log.info("pname = {}, quantity = {}, price = {}", pname, quantity, price);


    //상품등록
    Product product = new Product();
    product.setPname(pname);
    product.setQuantity(quantity);
    product.setPrice(price);
    Long productId = productSVC.save(product);

    log.info("상품번호 = {}", productId);

    model.addAttribute("productId", productId);
    //상품조회

    return "products/detailForm"; // 상품조회 화면 (view 이름)
  }

}
