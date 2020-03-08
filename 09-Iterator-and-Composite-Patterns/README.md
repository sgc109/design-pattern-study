## 09 Iterator and Composite Patterns

### Iterator Pattern

> aggregate object(data 의 collection 을 들고있는 object) 의 세부 구현을 드러내지 않으면서 그것의 elements 들을 순차적으로 접근할 수 있는 방법을 제공하는 디자인 패턴

![image](https://user-images.githubusercontent.com/7943694/76165858-af7a2980-619d-11ea-9862-f180b1315544.png)

* SRP(Single Responsibility Principle)
  * 하나의 클래스는 변경될 하나의 이유를 가져야한다
* Cohesion
  * high cohesion : 하나의 클래스가 하나의 기능을 함
  * low cohesion : 하나의 클래스가 서로 관련이 적은 여러 기능을 함
  * 즉, SRP 를 잘 지키면 high cohesion, 안 지키면 low cohesion 이 됨
* iterator pattern 은 iteration 기능을 캡슐화하여 aggregate 으로 부터 분리함으로써 SRP 를 지킬 수 있게 한다.
  * aggregate 은 data 들의 collection 을 가지고 있는 class 라고 보면된다
* 왜냐하면 분리하기 전에는 collection 의 세부 구현과 iteration 이라는 두가지 책임을 가지기 때문이다
* iteration 은 client 가 aggregate 의 세부 구현을 몰라도 되게 함으로써 둘 사이의 커플링을 낮춘다


### Composite pattern
> 객체들이 트리구조를 이루도록 하여 Client 가 Composites(내부노드)와 Individual Objects(리프노드)를 구분하지 않고 사용할 수 있도록 하는 디자인 패턴

![image](https://user-images.githubusercontent.com/7943694/76165973-8908be00-619e-11ea-9bdd-ebdac88b6bc6.png)

* client 가 단 한번의 메소드 호출만으로도 모든 노드에 대해 특정 동작을 수행하게 함으로써 client 가 해야할 일을 단순하게 만들어 준다는 것이 가장 큰 장점이다
* Iterator pattern 과 함께 사용할 수 있다

* internal iteration vs external iteration
  * internal iteration 은 functional programming 에서처럼 client 가 collection 에 대해 operation 을 정해주면 내부적으로 iteration 을 수행하는것
  * external iteration 은 client 가 직접 iteration 을 수행하는것
