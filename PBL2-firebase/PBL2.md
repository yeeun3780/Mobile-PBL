### 카테고리

​	카테고리 별 제품 나열

~~~
* 확장 가능하게 interface/abstract class 사용 구현

~~~

### 제품 검색  

​	검색된 제품 나열

~~~
/*
* 키워드로 내용 검색 sql문
*/
sql = "select * from blackboard where concat(bb_Content) like ? order by bb_id desc";

~~~

### 목록에서 선택한 제품을 자세히 보여줌

​	보여주는 정보 (이름, 가격, 이미지, 설명 텍스트)



