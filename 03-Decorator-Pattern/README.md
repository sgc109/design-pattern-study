## 03 Decorator Pattern

![image](https://user-images.githubusercontent.com/7943694/75876653-71a59a00-5e59-11ea-98ac-a8fceaac994d.png)

### 커피 가격 계산기 만들기
1. 각 커피 종류에 재료를 더할 수 있는 모든 경우에 대해 클래스를 만들고 Beverage 라는 super 클래스를 상속
* 문제점
    * 클래스가 너무 많아짐

2. 각 재료 추가 여부를 boolean field 로 가지고 있도록 구현
* 재료들에 대한 추가 비용을 계산하는 cost 메소드를 Beverage 에서 정의
* subclass 들은 cost 를 오버라이드하고 super.cost() 와 자신들의 가격을 더한 값을 반환한다
* 문제점
    * 가격이 바뀌면 코드 바꿔야함
    * 새로운 재료가 추가되면 슈퍼클래스의 필드 선언부와 cost 메소드 코드 바꿔야함
    * 기존의 재료와는 어울리지 않는 음료가 추가될 수 있음(e.g. Tea 클래스인데 hasWhip 메소드가 있으면 이상함)
    * 같은 재료를 여러개 필요로 하는 경우엔?

3. Decorator Pattern
![image](https://user-images.githubusercontent.com/7943694/75877226-8b93ac80-5e5a-11ea-9eb1-9b6ff539bcaf.png)
* Decorator 패턴은 4가지로 구성된다
    - Abstract Component
    - Concrete Components
    - Abstract Decorator
    - Concrete Decorators
* Decorator 는 Wrapper Class 다
* Object Composition 과 Delegation 을 통해 기존의 코드를 변경하지 않고 런타임에 기능을 추가할 수 있음
* Inheritance 의 단점을 해결
* 이를 사용하는 Client 는 내부적으로 데코레이터를 사용하는지 알필요가 없다
* 런타임에 동적으로 기능도 추가할 수 있는 상속으로 기능을 확장하는 것의 유연한 대체제의 역할
* OCP 의 대표적인 예
* 문제점
    * 너무 많은 작은 클래스들을 만들어 직관적이지 못한 코드가 될 수가 있다.
    * 하지만 동작 원리를 알면 금방 이해할 수 있다

### Open-Closed Principle (OCP)
* Classes should be open for extension, but closed for modification
* 클래스는 코드의 변경없이 확장 가능해야한다는 설계 원칙
* 가장 중요한 설계 원칙 중 하나
* 모든곳에 OCP 를 적용할 필요는 없다. 불필요한 낭비이며 복잡한 코드를 만들것이다

### 상속의 문제점
* 컴파일타임에 behavior 가 고정되어버림
* 그의 subclass 들도 마찬가지로 같은 behavior 들을 상속받아야함
* composition and delegation 으로 상속행위를 구현할수있음
* 기존의 코드를 바꾸지않고 responsibility 를 추가해줄 수도 있으며,
* 심지어 superclass 의 작성자조차 생각하지 못한 것까지도 가능
* 기존의 코드를 바꾸지 않으면 잘 돌아가던 코드에서 버그를 발생할 가능성이 줄어듬
* OCP(Open-Closed Principle) 를 명심하자. 변화에는 닫혀있어야하며, 확장에는 열려있어야 한다

### Q&A
* Q: inheritance 대신 composition 을 쓴다고했는데 왜 상속이 등장하나?
    * A: 상속을 하는것은 타입 매칭 때문이지 behavior 를 얻기 위함이 아니다
* Q: 바깥 데코레이터가 안쪽에 있는 녀석들을 모두 파악할 수 있도록 할 수 있나?
    * 가능하긴 하지만 데코레이터 패턴의 원래의 의도를 넘어선다고 볼 수 있다

### 실생활 예 - java.io
![image](https://user-images.githubusercontent.com/7943694/75877456-21c7d280-5e5b-11ea-9c1a-34d86b2ad5ad.png)
* java.io 패키지의 상당 부분은 데코레이터 패턴을 기반으로한다
* 잘 보면 위에 나온 모든 클래스 다이어그램의 모양이 닮아있다는 것을 알 수 있다(4가지 요소로 이루어져있다)
