# bitcamp-study
비트캠프 네이버 클라우드 과정 훈련 소스
git clone https://gitghub.com/eomjinyoung/bitcamp-study bitcamp-teacher
ctl shit p hex 에디터 사용 

## 네이버 클라우드 7기(02023-04/24 - 2023-10-27)
학습내용 

# 1일(5/22,월)
오전: 매직에콜 대표 세미나
프로그래밍 개론 - 소프트웨어 아키텍처 소개, 네이버클라우드 VPC, Subnet, ACG, Ubuntu Linux 생성, 컴파일 방식

# 2일(5/23,화)
컴파일 방식(계속), 
  -C 소스 컴파일 과정 및 gcc 컴파일러 사용법, 
인터프리터 방식(node js 사용법), 
하이브리드 방식(java 컴파일 및 실행)
JIT 컴파일과 AOT 컴파일 비교
기계어, OS, CPU 관계 
개발 도구 준비
  -JDK 설치 및 환경 변수 설정
  -VSCode 설치 및 플러그인 설치 
  -git 클라이언트 설치

# 3일(5/24,수)
개발 도구 준비(계속)
  -형상관리시스템 소개
  -git 사용법: clone, add(명단등록), commit(명단등록된거 백업),push, pull
  -gradle 설치 및 사용법: init, build, run
	-빌드 도구 소개 및 비교: Ant, Maven, Gradle 
자바컴파일러 사용법: 
  -d옵션 (클래스파일을 지정폴더에 둠),
  -소스폴더와 컴파일파일 폴더 분리(src,bin)
학습용 git 저장소 준비
  -개인 별 git 저장소 생성 및 복제 : bitcamp-study
  -저장소와 프로젝트 폴더의 관계(1:1, 1:多)
실습 프로젝트 준비
  -gradle을 이용한 실습 프로젝트 폴더 구성 
  -구성된 폴더의 용도
자바프로그래밍 
  -소스 파일과 클래스블럭의 관계 : 컴파일하면 클래스 블럭에 따라 각각 소스파일이 만들어짐 , 패키지 확인
  -클래스의 접근 범위 : public 클래스와 (default)클래스
  -entry point : main() 메서드 

# 4일(5/25, 목)
git 사용법
  - .gitignore 파일 소개, 
  - 'gitignore.io' 사이트에서 .gitignore 설정 내용 만들기 
자바프로그래밍
  - 문자열,문자, 논리 ,정수, 부동소수점 표기법
  -정수 리터럴
	  -각 진수별 표현방법
	  -2진수로 변환하는 다양한 방법 
		    -sing magnitude, 1의보수, 2의보수,Excess-K
	-부동소수점 리터럴
		-32비트 크기의 2진수로 바꾸는 방법
		-64비트 크기의 2진수로 바꾸는 방법
프로젝트 실습 
  -1. 자바 프로젝트 준비하기
  -2. 리터럴을 사용하여 데이터 출력하기

# 5일(5/26, 금)
자바프로그래밍
  -문자 리터럴
    -2진수로 변환하는 방법: 
    ASC(amerian standard code for information interchange 미국정보교환표준부호),
    ISO-8859-1, KSC-5601(완성형(한글)), 조합형, MS-949, Unicode, UTF-8
  -변수 사용법
    -jvm이 관리하는 메모리 영역 소개
    -변수 선언, 변수에 값 할당
    -Primitive Data Type 소개: byte, short, long, char, float, double, boolean
프로젝트 실습
  -변수를 사용하여 데이터를 저장하기 