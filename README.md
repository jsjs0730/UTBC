# NewsPBN

문서작성일 : 2017년 12월 30일

##### 프로젝트 시작 : 2017년 11월

##### 프로젝트 종료 : 2018년 1월

###### 버전 : 2.0.1230 (최초버전은 스프링시큐리티 미적용, 댓글X, 반응형X, 미완성)





## 주요기능

1. HOME

   ```
   기상청 날씨 정보 크롤링(Jsoup)
   권한에 따른 우측 로그인메뉴, 간단한 회원정보 전환
   오늘 인기글
   오늘 최신글
   각 섹션별 최신글(Slide 형태로 제공)
   ```

   ​


1. 회원가입

   ```
   스프링 시큐리티 적용
   bcryptPasswordEncoder를 이용한 비밀번호 암호화 
   프로필 사진, 멤버쉽 포인트, 내가 쓴 글, 내가 쓴 댓글
   ```

   ​

2. CRUD

   > ### Create(입력)
   >
   > ```
   > 네이버 스마트 에디터를 연동한 글쓰기
   > FormData를 이용한 drag and drop 다중파일 업로드
   > ajax를 이용한 화면 전환 없는 실시간 업로드와 수정
   > 썸네일 파일 생성으로 조금더 간편한 업로드파일 제어
   > 글쓴이의 포인트가 증가
   > ```
   >
   > ### Read(읽기)
   >
   > ```
   > 페이징처리가 가능한 댓글, 무한 대댓글 기능(ajax 사용)
   > 스프링 시큐리티 권한에 따른 사용자버튼 생성(삭제, 수정)
   > ```
   >
   > ### Update(수정)
   >
   > ```
   > 파일 업로드와 수정처리
   > 수정이 완료된 후 보고 있던 목록으로 복귀
   > ```
   >
   > ### Delete(삭제)
   >
   > ```
   > 삭제가 완료된 후 보고 있던 목록으로 복귀
   > 해당 글의 글쓴이 포인트 감소
   > ```

   ​

3. 기타 Interface

   ```
   모바일 완벽 지원을 위한 반응형 페이지처리
   게시판별 검색 기능
   + 제목, 내용, 닉네임, 제목+내용, 제목+닉네임, 제목+닉네임+내용
   게시판별 페이징 처리
   한페이지당 20개의 게시글, 한페이지당 10페이지 버튼 
   + 추가설정 확장가능
   어느 페이지에서나 가능한 로그인, 로그아웃
   Breadcrumb를 이용한 간편한 이동
   CSRF 공격 방어를 위한 모든 페이지 form 에 csrf 토큰 적용
   ```



## Requirements

- Java 1.8.0.121

- Java Servlet 3.1

- Spring Framework 4.3.9.RELEASE

- Spring Security 4.2.3

- MySQL 6.3.9

- MyBatis 3.4.1

- log4j 1.2.15

- Junit 4.12

- Jackson-databind 2.9.2

- Imgscalr 4.2

- Jsoup 1.11.2

- ETC

  - Jquery 2.1.4
  - Handlebars 4.0.11


  - Bootstrap 3.3.4

  - Ionicons

  - Font Awesome Icons 4.7.0

  - AdminLTE(for Bootstrap theme)

  - Tomcat 9.0

  - Eclipse Oxygen.1a

  - Editplus

    ​

  ​





