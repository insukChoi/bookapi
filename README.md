도서 검색 API
==========================

## 실행 방법
- Execute jar 다운로드 

```
git clone https://github.com/insukChoi/bookapi.git
cd bookapi
java -jar ./download/bookapi-0.0.1-SNAPSHOT.jar
```

- http://localhost:8080
- http://localhost:8080/h2-console (DB 관리콘솔)


## 실행 화면

- 로그인 화면
<img width="1235" alt="login" src="https://user-images.githubusercontent.com/14847562/61794538-5eab0000-ae5c-11e9-9428-9402d01bff60.png">

- 메인 화면
<img width="1233" alt="main" src="https://user-images.githubusercontent.com/14847562/61794697-ae89c700-ae5c-11e9-84f4-a89b27ab78d8.png">

- DB 관리 콘솔
<img width="954" alt="db" src="https://user-images.githubusercontent.com/14847562/61794705-b21d4e00-ae5c-11e9-8a3f-1fa0cbe42e14.png">


## 구현 설명

- 백엔드
  - SpringBoot , JPA, H2DB (In memory Databae) 기반의 구현
  - 도메인 (login, book, entertainment) 기반의 패키지 구조
  - Spring Security 인증을 통한 로그인 구현
    - UserDetailsService 기반의 AuthenticationProvider 구현
  - RestTemplate 를 이용한 API 통신 구현
  - Spring Cloud Netflix Hystrix 를 이용한 Fallback 구현
  - Mock 객체를 사용하여 객체간 메세지 전송 테스트 코드 및 MockMvc 테스트 코드 구현
  - 예외처리 및 페이징 처리
  
- 프론트엔드
  - jQuery 기반의 IIFE(Immediately Invoked Function Expression) 패턴
  - Ajax 기반 구현
  - 도서 상세정보 레이어팝업으로 jQuery bpopup 라이브러리 사용


## 라이브러리 및 오픈소스
- Java8
- SpringBoot2.1.6
- JPA
- H2DB
- Lombok
  - 반복 코드 제거를 위하여 사용.
- Maven
  - 프로젝트 빌드 툴로 Maven 사용.
- Spring Cloud Hystrix
  - 도서 API 장애시, Fallback 으로 대체 API 호출.
- Thymeleaf
  - 서버사이드 자바 템플릿 엔진 사용.
- Bootstrap
  - 뷰 템플릿 양식을 위해 사용.
- jQuery
  - javascript 라이브러리 사용.
- jQuery bpopup
  - 도서 상세내용 화면을 레이어 팝업으로 구현.
