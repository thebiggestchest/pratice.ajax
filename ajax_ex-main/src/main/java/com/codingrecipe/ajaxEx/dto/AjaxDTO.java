package com.codingrecipe.ajaxEx.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
//@AllArgsConstructor 해당 객체 내에 있는 모든 변수들을 인수로 받는 생성자를 만들어내는 주석이다.
public class AjaxDTO {
    private String param1;
    private String param2;
}
// Dto는 웹 서비스의 클라이언트와 서버, 더 자세히는 클라이언트와 서버의 서비스 계층 사이에서 교환되는 데이터를 담는 그릇이다.
//05.html에 세팅된 parameter변수가 세팅
