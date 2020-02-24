## 04 Factory Pattern

### Factory method pattern
* super-class 에서 특정 object 를 반환하는 abstract 메소드를 둠으로써, sub-class 에게 object 의 생성을 위임하는 패턴
* DIP(Dependency Inversion Principal) 을 실천하는 강력한 테크닉 중 하나
* Concrete 한 class 들에 직접 의존하는 것이 아니라 Object 를 생성하는 역할을 factory mothod 에게 위임함으로써 DIP 를 실천

### Abstract factory pattern
* 특정 Object 들을 생성하는 메소드들을 가진 interface 를 정의하고, 이를 구현하는 class 들을 만든 뒤, 
