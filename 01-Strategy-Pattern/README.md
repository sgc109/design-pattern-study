## 01 Strategy Pattern


### 오리 시뮬레이션 게임 구현하기
* 방법 1
  - Duck 이라는 슈퍼 클래스를 만들고 꽥꽥대기(quack), 수영하기(swim) 등의 공통된 행위를 메소드로 정의한다. 
  - 오리의 종류에 따라 화면에 표시하는 방식이 다르므로 display 라는 추상 메소드를 둔다. 
  - 다양한 오리의 클래스들이 Duck 클래스를 상속받아 각자의 기능을 정의한다. 
  - 하지만 만약 오리에 나는 기능을 추가하기위해 날기(fly) 라는 새로운 행위를 메소드로 정의한다면 잠재적인 문제가 발생한다. 
  - 일부 서부클래스들은 슈퍼클래스의 일부, 혹은 전체 행위를 할 수 없어야 하는 경우다. 예를 들어, *장난감 오리* 를 나타내는 *RubberDuck* 이라는 서브클래스는 날면 안되기 때문에 *fly* 메소드를 오버라이딩하여 아무것도 하지않도록 해야하며, *나무를 깎아 만든 오리장식* 을 나타내는 *DecoyDuck* 라는 서브클래스는 나는것은 물론 꽥꽥대지도 말아야하기 때문에 *fly* 와 *quack* 메소드 모두 오버라이드 하여 아무 행위도 하지않도록 해야한다. 즉, **재사용을 목적으로 상속을 사용하는 것은 좋지만,  maintenance 측면에서는 그다지 좋지 않다는 것이다**
<br/>

![image](https://user-images.githubusercontent.com/7943694/75551365-675d5780-5a77-11ea-8278-2f0c8dbdd635.png)

* 방법 2
  - 그렇다면 Interface 는 어떨까?
  - display 나 swim 같은 공통된 성질 및 행위는 Duck 이라는 슈퍼클래스에 두고 상속받도록 하되, fly, quack 등의 optional한 behavior 는 interface 로 정의하고, 각각의 오리 subclass 들이 필요한 behavior 를 impplements 하여 스스로 각자의 기능을 정의하도록 하는 방법이다. 
  - **필요한 행위만 골라** 쓸 수 있다는 점은 분명 장점이지만, 행위 코드의 **재사용**이 불가능해지기때문에 **코드의 중복**이 발생한다. 
  - 만약, fly 라는 행위에 대한 요구사항이 조금이라도 발생한다면, 이를 구현하고있는 **모든 subclass 들의 코드들을 모두 변경해주어야** 한다는 문제도 있다. 
  - 결국 이 방식은 이전의 inheritance 를 사용한 방식의 일부 문제는 해결주지만 코드 재사용의 이점을 없애 또다른 maintenance nightmare 를 만든다.

**여기서 중요한 법칙이 나온다.** 프로그래밍을 할 때 절대 변하지 않는 법칙은, 프로그램은 항상 **변한다**는것이다. 규모가 커지거나 기능이 변경될 수가 있다는 것이다.
그러므로 변하는 부분을 **Encapsulation(캡슐화)** 하여 변하지 않는 부분으로 부터 분리해야한다. 그러면 분리한 부분이 다른 것으로 대체되거나 확장되어도 변하지 않는 부분에 영향을 끼치지 않게 된다. (SOLID 의 OCP 와도 일맥상통한다) 결과적으로 코드 변경 시 의도치않은 결과가 줄고, 유연함이 생긴다.
<br/>

![image](https://user-images.githubusercontent.com/7943694/75551297-3aa94000-5a77-11ea-8947-dd5d9d2c2b74.png)

* 방법 3 (Strategy Pattern)
  - 그렇다면, optional 한 behavior 들을 캡슐화하여 오리 subclass 를 생성할 때 행위를 정의해 줄 수는 없을까?
  - 행위들을 interface 로 정의하고, 이를 implements 하는 구체적인 행위들을 정의한뒤, setter method 를 통해 초기화 해준다. 
  - 여기서 또다른 Design Principal 이 등장하는데, behavior 를 전달받는 오리 class에서 behavior 의 type 을 구체적인(인터페이스를 구현하는 클래스)타입이 아니라 인터페이스의 타입으로 사용하는 것이다. 
  - 이렇게되면 런타임에 행위를 다른 것으로 동적으로 변경해줄 수도 있게된다.
  - 오리 클래스는 행위의 구체적인 구현을 알 필요가 없어진다.
  - 이 원칙은 **Program to an interface, not an implementation** 이다.
  - 만약 행위를구체적인 타입으로 선언할 경우 변경에 대한 여지가 사라지게 된다.

예를 들어, 다음과 같은 클래스가 있다고 할 때

![image](https://user-images.githubusercontent.com/7943694/75550280-ebfaa680-5a74-11ea-98ce-35e41d0988ba.png)

```java
Dog d = new Dog();
d.bark();
```
보다는

```java
Animal animal = new Dog();
animal.makeSound();
```
또 이것 보다는

```java
a = getAnimal();
a.makeSound();
```
로 구현하는게 좋다. 왜냐면 두번째 형식도 코드에 특정 클래스 타입이 등장하기 때문이다

스트레티지 패턴의 키 포인트는 behavior 를 *delegate* 한다는 것이다
기존에 오리 subclass에 있던 fly 함수대신 performFly 함수를 선언하고 내부에서 behavior class 에 정의된 fly 함수를 호출한다
그럼 오리 subclass는 behavior 의 디테일을 신경쓰지않아도 된다

여기서 우리는 IS-A 관계인 inheritance 대신 HAS-A 관계인 composition 을 사용한 것인데, 여기서 또다른 Design Principle 이 등장한다.

Favor composition over inheritance

### 추가 설명
OO basics 를 잘 따른다고, 그것이 항상 확장가능하고, 유연하며, maintainable 한 설계를 보장해주진 않는다. OO basics 들 중에는 불명확한 부분이 있기때문에 OO basics 를 따른 설계들 중에서도 오랫동안 검증된 방식의 모음이 디자인 패턴인것 

개발 도중과 완료 이후 중에 더 시간을 많이 들어가는 것은 개발 완료 이후이기 때문에, reuse 보단 maintainability 와 flexability 를 더 신경써야한다

reuse 를 달성하는 방법은 상속 이외에도 다양한 방법들이 있기때문에 꼭 상속을 사용할 필요는 없다

### Strategy Pattern 에 적용되는 Design Principles
1. 변경되는 부분을 변경되지 않는 부분과 분리해라(Identify the aspects of your application that vary and seperate them from what stays the same)
2. 구현체가 아니라 인터페이스를 사용하여 프로그래밍해라(Program to an interface, not an implementation)
3. 상속보다 구성을 선호해라(Favor composition over inheritance)

추가) 상속은 subclass 가 superclass  강하게 결합되며, 컴파일 타임에 subclass 의 성격이 정해져버리는 단점이 있어서 좋지 않을 때도 있다. (favor composition over inheritance)
