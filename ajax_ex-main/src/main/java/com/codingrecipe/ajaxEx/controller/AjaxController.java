package com.codingrecipe.ajaxEx.controller;

import com.codingrecipe.ajaxEx.dto.AjaxDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    @GetMapping("/ex01")
    public String ex01() {
        System.out.println("AjaxController.ex01");
        return "index"; // index.html에 작성된 내용이 출력됨
    }
//GET
// - URL에 데이터를 포함시켜 요청
// - 데이터를 헤더에 포함하여 전송
// - URL에 데이터가 노출되어 보안에 취약
// - 캐싱할 수 있음
// => 주로 조회할때만 사용
//POST 란?
// - URL에 데이터를 노출하지 않고 요청
// - 데이터를 바디에 포함
// - URL에 데이터가 노출되지 않아 GET방식보다 보안이 높음
// - 캐싱할 수 없음
//=> 주로 노출되면 안되는 데이터를 저장할 때 사용
//getmapping과 postmapping의 차이점은 요청방식에 따라서 달라짐
//java에는 json 형식이 존재 하지 않는데 Controller에서 데이터를 보내 주려면 JSON 형식으로 보내야 되는데 그 때 사용하는게 @ResponseBody 이다.
// @responsebod는 return값 안녕하세요를 ajax-ex-02.html파일의 body tag에 그대로 실어서 보내주는 역할
    @PostMapping("/ex02")
    public @ResponseBody String ex02() {
        System.out.println("AjaxController.ex02");
        return "안녕하세요"; // 리턴값이 그대로 출력됨
    }
//@RequestParam은  parameter를 전달 하기위해서 쓴다
    @GetMapping("/ex03")
    public @ResponseBody String ex03(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
// 키값이html파일과 맞는지 확인하고 valu값이 제대로 찍히는지 확인
        return "ex03메서드 호출 완료";
    }

    @PostMapping("/ex04")
    public @ResponseBody String ex04(@RequestParam("param1") String param1,
                                     @RequestParam("param2") String param2) {
        System.out.println("param1 = " + param1 + ", param2 = " + param2);
        return "ex04메서드 호출 완료";
    }
//String으로 리턴할때
//    @GetMapping("/ex05")
//    public @ResponseBody String ex05(@ModelAttribute AjaxDTO ajaxDTO) {
//        System.out.println("ajaxDTO = " + ajaxDTO);
//        return "ex05메서드 호출 완료";
//    }
//컨트롤러 입장에서 필드 값이 다 세팅된 DTO객체를 사용
//@ModelAttribute 는 클라이언트로부터 일반 HTTP 요청 파라미터나 multipart/form-data 형태의 파라미터를 받아 객체로 사용하고 싶을 때 이용된다.
// String 대신 AjaxDTO를 리턴타입으로 설정했을때
    @GetMapping("/ex05")
    public @ResponseBody AjaxDTO ex05(@ModelAttribute AjaxDTO ajaxDTO) {
         System.out.println("ajaxDTO = " + ajaxDTO);
         return ajaxDTO;
    }
//요청방식이 Post일때, 보여지는게 크게 다른점은 없음
    @PostMapping("/ex06")
    public @ResponseBody AjaxDTO ex06(@ModelAttribute AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }
//우리 시스템으로 날아온 JSON 형태의 데이터를 JAVA 객체에 자동으로 값을 넣어주는 주석이 @RequestBody입니다.
//  requestbody로 받을 수 있다.
    @PostMapping("/ex07")
    public @ResponseBody AjaxDTO ex07(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        return ajaxDTO;
    }
// @ResponseBody을 이용하면 자바 객체를 HTTP 응답 body로 전송할 수 있다.
// Ajax에서 파라미터를 컨트롤러에 보내면 컨트롤러에선 @RequestBody를 사용하여 파라미터를 받는다.

    //    private list 메소드는 지금 DB를 쓰지않고 컨트롤러에서만 처리를 하려고 만든 것
    private List<AjaxDTO> DTOList() {
        List<AjaxDTO> dtoList = new ArrayList<>();
        dtoList.add(new AjaxDTO("data1", "data11"));
        dtoList.add(new AjaxDTO("data2", "data22"));
        return dtoList;
    }
//responsebody로 리스트실행
// DTO객체가 담긴 리스트이기 때문에 List<AjaxDTO>
    @PostMapping("/ex08")
    public @ResponseBody List<AjaxDTO> ex08(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
//       DTOList를 호출하고
        List<AjaxDTO> dtoList = DTOList();
//        DTOlist 호출결과를 받아오고
        dtoList.add(ajaxDTO);
        return dtoList;
    }
//ResponseEntity란, httpentity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
//데이터와 코드를 함께 다룰수있음
// ResponseEntity에는 사용자의  HttpRequest에 대한 응답 데이터가 포함된다
    @PostMapping("/ex09")
    public ResponseEntity ex09(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
//Httpstatus는 http 상태코드
//       리턴해줄 데이터가 딱히없는경우
//        return new ResponseEntity<>(HttpStatus.OK);
//        사용자가 잘못된요청을 했을때, 에러페이지를 컨트롤러에서 제어
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        데이터와 코드를 같이보내는경우 예)문제가 생기면 에러코드와 에러 메시지 같이보내는것
        return new ResponseEntity<>(ajaxDTO, HttpStatus.OK);
    }
//responseentity로 리스트 실행
    @PostMapping("/ex10")
    public ResponseEntity ex10(@RequestBody AjaxDTO ajaxDTO) {
        System.out.println("ajaxDTO = " + ajaxDTO);
        List<AjaxDTO> dtoList = DTOList();
        dtoList.add(ajaxDTO);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


}









