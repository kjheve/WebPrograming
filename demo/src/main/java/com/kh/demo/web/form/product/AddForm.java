package com.kh.demo.web.form.product;

import lombok.Data;

@Data
public class AddForm {
  // input 태그의 name속성의 값과 같아야함
  private String pname;
  private Long quantity;
  private Long price;
}
