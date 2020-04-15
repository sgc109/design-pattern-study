## 10 State Pattern

![image](https://user-images.githubusercontent.com/7943694/79338965-1b9d3980-7f63-11ea-9c4a-50fa460f3e8d.png)

```
Object 의 Behavior 를 내부 state 의 변화에 따라 바꿀 수 있는 패턴
```

* 각각의 State 들을 class 로 구현한다
* 공통된 행위의 종류를 interface 로 빼고, concrete 클래스들이 이를 구현하도록 한다.

### 장점
* 여러개의 if 문을 사용하여 현재 state 에 따른 behavior 를 결정하는 것(procedural programming)이 아닌
* 각각의 state 를 class 로 만들고, 현재 state 를 composition 하는 방식으로 구현하여
* 로직에서 달라지는 부분인 각 state 의 behavior 들을 캡슐화할 수 있고
* Open-Closed Principal 을 잘 지키기 때문에
* 특정 state 의 로직이 변경되거나 새로운 state 가 추가되었을 때 유리하다.

### 단점
* 클래스의 수는 더 많아진다

### 특징
* State 클래스들은 여러 Context 들 사이에서 공유될 수 있다.

### Strategy 패턴 과의 차이점
* State 패턴은 Strategy 패턴과 클래스 다이어그램은 같다.
* Behavior 를 런타임에 동적으로 변경시킨 다는 점에서는 유사하다
* 그러나 의도가 다르다.
* Strategy pattern 은 behavior 의 변화를 context 가 컨트롤한다
* State pattern 은 내부적으로 정의된 state transition 에 따라 behavior 가 변경된다
