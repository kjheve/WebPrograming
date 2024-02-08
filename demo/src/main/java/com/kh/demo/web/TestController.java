// 타임리프 test1을 위한 컨트롤러 (타임리프 연습)

package com.kh.demo.web;

import com.kh.demo.collectiontest.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

//  @GetMapping("/t1") // 접속 URL
//  public String t1() {
//    return "test/test1"; // 폴더 경로 맞추기
//  }

  @GetMapping("/t1") // 접속 URL, Get, http://localhost:9080/test/t1
  public String t1(Model model) {

//    model.addAttribute(KEY, VALUE);
    model.addAttribute("key1", "KH인재교육원1");
    model.addAttribute("key2", "KH인재교육원2");
    model.addAttribute("key3", "KH인재교육원3");


    // ▶컬렉션을 공부하고 시작!
    // 이 List에는 여러개의 사람(Person) 객체를 담을 수 있다
    List<Person> persons = new ArrayList<>();
    Person p1 = new Person("홍길동", 30);
    Person p2 = new Person("홍길순", 20);
    Person p3 = new Person("홍길남", 40);

    persons.add(p1);
    persons.add(p2);
    persons.add(p3);

    model.addAttribute("persons", persons);


    return "test/test1"; // 폴더 경로 맞추기
  }
}
