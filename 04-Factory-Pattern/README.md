## 04 Factory Pattern

### Factory method pattern
* super-class 에서 특정 object 를 반환하는 abstract 메소드를 둠으로써, sub-class 에게 object 의 생성을 위임하는 패턴
* DIP(Dependency Inversion Principal) 을 실천하는 강력한 테크닉 중 하나
* Concrete 한 class 들에 직접 의존하는 것이 아니라 Object 를 생성하는 역할을 factory mothod 에게 위임함으로써 DIP 를 실천

### Abstract factory pattern
* 서로 관련이있는 Object 들을 생성하는 메소드들을 가진 interface 를 정의하고, 이를 구현하는 class 들을 만든 뒤, object 를 필요로 하는 class 에 주입하여, 이를 사용하게 하는 패턴
* 객체의 생성을 interface 로 추상화하였기 때문에, 런타임에도 자유롭게 팩토리를 바꿔 쓸 수가 있다
* 사실 abstract factory 역할을 하는 interface 안에 factory method 있다.

### 공통점
* Factory Pattern 임
* 둘 모두 구체적인 구현으로부터 decoupling
* 왜냐하면 factory 를 사용하는 client 입장에서는 concrete 한 class 를 몰라도 되며, high-level 의 abstract 만 가지고 동작하기 

### 차이점
* Factory method 는 Inheritance 을 통해 object 를 생성하며, Abstract factory 는 Object composition 을 통해 생성
