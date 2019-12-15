# 카카오페이 사전 과제: 주택 금융 서비스 API 개발

## 1. 개발 환경

#### 기본 환경
* IntelliJ IDEA Ultimate
* Mac OS X
* Git

#### Backend
* Java 12
* Spring Boot 2.2.2
* Spring Data JPA
* H2
* gradle
* JUnit 5

#### Frontend
* Bootstrap

---

## 2. 빌드 및 실행 방법

    $ git clone https://github.com/dasistHYOJIN/kakaopay-assignment.git
    $ cd kakaopay-assignment
    $ ./gradlew clean build
    $ java -jar build/libs/kakaopay-0.0.1-SNAPSHOT.jar

### Lombok 설정하기
Lombok 라이브러리를 사용하였기 때문에 IntelliJ에서 프로그램을 실행시키기 위해서는 다음과 같은 환경설정이 필요합니다.
![Untitled](https://user-images.githubusercontent.com/25656510/70863387-0ef3df00-1f8b-11ea-94c7-42c1249de068.png)

Preferences > Build, Execution, Deployment > Compiler > Annotation Processors

Enable annotation processing에 체크합니다.

## 3. 해결 방법
### CSV 파일의 데이터를 정형화하는 파서 구현
저는 데이터를 읽고 정형화하는 과정까지 이 과제의 요구사항이라고 판단했기 때문에 CSV 라이브러리를 사용하지 않고 직접 Parser를 구현하였습니다.

파일의 데이터를 다루는 로직은 크게 다음과 같이 나누어 해결했습니다.
1. 파일을 컨트롤러(`GuaranteeApiController`)에서 받아온다.
2. `FileUtils` 클래스를 통해 파일의 내용을 읽고 행 단위로 나눈다.
3. `GuaranteeParser` 클래스를 통해 헤더와 바디 부분을 나누고, 쉼표(,)를 기준으로 데이터를 쪼갠다.
4. 자릿수가 쉼표(,)로 구분되어 있는 데이터를 찾아 순수한 숫자 데이터로 변환한다.
5. 데이터를 형식에 맞게 `Guarantee` 객체로 파싱한다.

### 엔티티 모델링
엔티티 모델은 다음과 같습니다.
1. `Guarantee`: 신용보증한 금액의 정보를 갖는 엔티티
    * `Long id`
    * `Year year`
    * `Month month`
    * `Amount amount`
    * `Institute institute`
2. `Institute`: 주택금융 공급 금융기관의 정보를 갖는 엔티티
    * `InstituteType instituteType`: Enum 클래스로 금융기관 코드와 이름을 저장하고 있습니다.

엔티티의 필드는 VO 및 Enum 클래스 객체로 선언하여 유효성 검사 및 타입, 값 캐싱을 구현했습니다.

두 엔티티는 ManyToOne 단방향 연관관계를 맺고 있으며 `Guarantee`가 Many, `Institute`가 One입니다.

### 그 외
`ApiResponse`로 데이터를 감싸 실 데이터 외에 상태코드와 데이터 메시지를 함께 전달했습니다.

`ControllerAdvice`를 구현하여 예외에 대한 처리를 수행합니다.

---

## 4. Rest API

### 데이터 파일 첨부 페이지
.csv 파일을 쉽게 첨부하기 위해 만든 정적 페이지입니다.

> GET /guarantee  HTTP/1.1

### 데이터 파일 데이터베이스에 저장
데이터베이스에 저장하고자 하는 .csv 파일을 파라미터로 전달해 주택 금융 데이터를 저장합니다.

> POST /api/guarantee/new  HTTP/1.1

Parameter

|필수 여부|변수명|타입|변수 설명|값 설명|
|---|---|---|---|---|
|O|file|file|데이터베이스에 저장하고자 하는 파일|.csv 타입의 파일|

### 등록된 모든 금융기관 리스트 출력
데이터베이스에 저장된 모든 금융기관의 리스트를 출력합니다.

> GET /api/institute/list HTTP/1.1

### 각 연도별 가장 높은 금액을 지원한 금융기관 데이터
데이터베이스에 저장된 금융 기관 중 각 연도별로 가장 높은 금액을 지원한 금융기관에 대한 데이터를 출력합니다.

> GET /api/guarantee/max HTTP/1.1

### 특정 연도의 가장 높은 금액을 지원한 금융 기관 데이터
특정 연도에서 가장 높은 금액을 지원한 금융기관에 대한 데이터를 출력합니다.

> GET /api/guarantee/max/{year} HTTP/1.1

Parameter

|필수 여부|변수명|타입|변수 설명|값 설명|
|---|---|---|---|---|
|O|year|integer|열람하고자 하는 연도|-|

### 연도별 주택 금융 공급 현황 데이터
각 연도별 금융 기관의 지원 금액 합계와 전체 합계 데이터를 출력합니다.

> GET /api/guarantee/sum HTTP/1.1

### 특정 금융 기관의 지원금액 평균 최소 및 최대 금액 데이터
특정 금융 기관의 전체 연도의 평균 최솟값과 최댓값 데이터를 출력합니다.

> GET /api/guarantee/avg/{instituteName} HTTP/1.1

|필수 여부|변수명|타입|변수 설명|값 설명|
|---|---|---|---|---|
|O|instituteName|integer|열람하고자 하는 금융기관명|-|

---

## 5. 과제 설명
### 주택금융 공급현황 분석 서비스
* 국내 주택금융 신용보증 기관으로부터 년도별 각 금융기관(은행)에서 신용보증한 금액에 대한 데이터가 주어집니다. 이를 기반으로 아래 기능명세에 대한 API 를 개발하고 각 기능별 Unit Test 코드를 개발하세요.

### API 기능명세
**기본 문제 (필수)**
1. 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발
2. 주택금융 공급 금융기관(은행) 목록을 출력하는 API 개발
3. 년도별 각 금융기관의 지원금액 합계를 출력하는 API 개발

### 제약사항
**기본 문제 (필수)**
* API 기능명세에 나온 API를 개발하세요.
* 데이터 영속성 관리 및 매핑을 위한 ORM(Object Relational Mapping)을 사용하여 각 엔티티를 정의하고 레퍼지토리를 개발하세요.
    * 예를 들어, Java의 경우 JPA, Python의 경우 SQLAlchemy 적용
    * 단, 엔티티 디자인은 지원자의 문제해결 방법에 따라 자유롭게 합니다.
    * 단, 주택금융 공급기관은 독립 엔티티(기관명과 기관코드)로 디자인합니다.
    {“institute_name”, “institute_code”}
* 단위 테스트 (Unit Test) 코드를 개발하여 각 기능을 검증하세요.
* 모든 입/출력은 JSON 형태로 주고 받습니다.
* README.md 파일을 추가하여, 개발 프레임워크, 문제해결 전략, 빌드 및 실행 방법을 기술하세요.
* 단, 프로그램 언어는 평가에 반영되지 않으니 자유롭게 선택하세요.
* 단, 각 API 의 HTTP Method 들( GET | POST | PUT | DEL )은 알아서 선택하세요.
