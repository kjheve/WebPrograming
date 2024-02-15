package com.kh.demo.web;


// V2 : 유효성검사 추가 된 코드

import com.kh.demo.domain.entity.Product;
import com.kh.demo.domain.product.svc.ProductSVC;
import com.kh.demo.web.form.product.AddForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/products") // URL 매핑 // http://localhost:9080/products
public class ProductControllerV2 {

  private ProductSVC productSVC;
  ProductControllerV2(ProductSVC productSVC) {
    this.productSVC = productSVC;
  }

  // 상품등록양식
  @GetMapping("/add") // Get, http://localhost:9080/productsv2/add
  public String addForm() {

    return "productsv2/add"; // view 이름 (상품 등록 화면)
    // resources/templates/productsv2/add.html 파일 이름이 맞아야함
  }

  // ★상품등록처리
  @PostMapping("/add") // Post, http://localhost:9080/productsv2/add
  public String add(AddForm addForm, // form객체 : 양식과 매핑되는 객체
                    Model model,
                    RedirectAttributes redirectAttributes) {

    log.info("addForm={}", addForm); // toString() 형태로 값을 찍어볼 수 있다.

    // 유효성체크
    if (addForm.getPname().length() > 10) {
      model.addAttribute("s_err_panme", "상품명은 10자 이내여야 합니다.");
      return "productv2/add";
    }

    // ★상품등록
    Product product = new Product();
    product.setPname(addForm.getPname());
    product.setQuantity(addForm.getQuantity());
    product.setPrice(addForm.getPrice());

    Long productId = productSVC.save(product);
    log.info("상품번호={}", productId);

    redirectAttributes.addAttribute("pid", productId);
    return "redirect:/products/{pid}/detail"; // 상품조회 화면 302 GET, http://localhost:9080/productsv2/pid/detail
  }


  // ★★★★★★★★조회★★★★★★★★
  @GetMapping("/{pid}/detail")     // GET, http://localhost:9080/productsv2/상품번호(productId)/detail
  public String findById(@PathVariable("pid") Long productId, Model model) {

    Optional<Product> findedProduct = productSVC.findByID(productId);

    Product product = findedProduct.orElseThrow();
    model.addAttribute("product", product);

    return "productsv2/detailForm";
  }
  
  // ---------------------------------------------------------------------------------

  // ★단건 삭제
  @GetMapping("/{pid}/del")
  public String deleteById(@PathVariable("pid") Long productId) {
    log.info("deleteById={}", productId);

    // 1) 상품 삭제 -> 상품 테이블에서 삭제
    productSVC.deleteById(productId);

    // 2) 상품 목록 -> 상품 테이블에서 상품목록을 가져와서 Model 객체에 저장 (이미 ★목록에 있음 redirect:로 요청)
    return "redirect:/products"; // GET http://localhost:9080/productsv2/
  }

  // ★여러건 삭제
  @PostMapping("/del") // POST, http://localhost:9080/productsv2/del
  public String deleteByIds(@RequestParam("pids") List<Long> pids) {
    log.info("deleteByIds={}", pids);
    int deleteRowCnt = productSVC.deleteByIds(pids);

    return "redirect:/products";
  }

  // ★수정양식
  @GetMapping("/{pid}/edit") // GET, http://localhost:9080/productsv2/상품번호/edit
  public String updateForm(@PathVariable("pid") Long productId, Model model) {
    Optional<Product> optionalProduct = productSVC.findByID(productId);
    Product findedproduct = optionalProduct.orElseThrow();

    model.addAttribute("product", findedproduct);
    return "productsv2/updateForm";
  }

  // ★수정처리
  @PostMapping("/{pid}/edit")
  public String update(
          // 경로변수 pid로부터 상품번호를 읽어온다
          @PathVariable("pid") Long productId,
          @RequestParam("pname") String pname,
          @RequestParam("quantity") Long quantity,
          @RequestParam("price") Long price,
          // 리다이렉트시 경로변수에 값을 설정하기위해 사용
          RedirectAttributes redirectAttributes) {

    Product product = new Product();
    product.setPname(pname);
    product.setQuantity(quantity);
    product.setPrice(price);
    int updateRowCnt = productSVC.updateById(productId, product);

    redirectAttributes.addAttribute("pId", productId);
    return "redirect:/products/{pId}/detail";
  }

  
  // ★목록
  @GetMapping // GET, http://localhost:9080/products
  public String findAll(Model model) {

    List<Product> list = productSVC.findAll();
    model.addAttribute("list", list);

    return "productsv2/all";
  }

}
