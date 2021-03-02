# idus-exam

1. Bussiness분석
  제시한 문제의 테이블에 대한 추가 필드를 만들었습니다.

  회원속성/br
    PK가 따로 존재하지 않아 Integer형태의 user_id속성을 추가하였고 AUTO_INCREMENT를 사용하여 관리햐였습니다.
    제시된 문제중 여러회원 목록 조회 에서 "각 회원의 마지막 주문 정보"를 기록하기위한 
          last_order_no char(12) NULL COMMENT '최근 주문번호',
          last_goods_name varchar(100) NULL COMMENT '최근 주문상품명',
          last_payment_date datetime NULL COMMENT '최근 주문시간',
    필드를 추가하였습니다.
    사유 : 주문테이블에서 최근 1건을 Outer Join등으로 뽑아오기에는 DBMS의 부하를 많이 줄듯 하여,
          최근 주문 속성을 두어 주문이 될때마다 업데이트 하는 것이 효율적으로 판단하였습니다.
          
  주문속성
    회원속성과 연관관계가 없어 회원속성과 연결해주는 FK키인 user_id속성을 추가하였습니다.
    비회원 주문등을 고려하여 회원 속성과 물리적인 FK제약을 두지않고 NULL을 허용하는 속성을 두었습니다.
    
2. 로그인 관련
    로그인의 ID로 이메일 속성을 사용하였습니다. 사유는 Email을 중복으로 여러명이 공유하지 않기 때문입니다.
    초기 개발에서는 Spring Boot의 Controller 에서 HttpSession 개체를 사용하여 SessionID를 받았습니다.
    하지만 Swagger 문서 작성시 불필요한 속성들이 노출되어 String 파라미터로 입력 받도록 변경하였습니다.
    
    Password는 상용 프로젝트에서는 Sha256등의 알고리즘을 이용하여 복호화 불가능하게 만들어야 하겠지만, 과제 프로젝트여서 암호화를 생략하고 PlanText 가 그대로 입력되도록 하였습니다.
    
    로그인된 유저의 정보는 싱글톤으로 생성된 LoginUserInfo 클래스를 만들어서 메모리 상에만 저장하였습니다.
    상용 프로젝트에서 다른 저장장소(레디스 등)을 이용할 경우 LoginUserInfo 내의 addUserLoginInfo, delUserLoginInfo, searchLoginUser등의 Method 로직만 변경하면 되도록 개발하였습니다.
    
  
3. swagger-ui URL
  http://localhost:8080/swagger-ui.html
    
    
    
