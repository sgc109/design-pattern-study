## 07 Adapter and Facade Patterns

### Adapter Pattern

#### 비유
* 실제 세계의 어댑터란 110V 의 플러그를 220V 의 콘센트에 끼우는 것처럼 110V 플러그를 220V 콘센트가 기대하는 형태로 알아서 바꿔주는 것
* 우리가 어떤 소프트웨어를 만들었는데 기존의 벤더에 맞게 만들었기 때문에 지금까진 잘 동작했지만, 새로운 벤더의 모듈과는 맞지 않을 때, 기존의 코드를 바꾸고싶지 않고, 벤더의 코드를 변경할 수는 없기 때문에, 어댑터라는 중간 매체를 만들어서 둘을 연결하는것

#### 오리 시뮬레이션 게임
오리 객체가 부족해서 칠면조 객체를 오리객체로서 사용하고 싶을 때

#### Object Adapter vs Class Adapter

* Object Adapter

![image](https://user-images.githubusercontent.com/7943694/75609094-25044b00-5b49-11ea-9030-02d7c22cf944.png)

* Class Adapter
  - 다중 상속이 가능해야 구현할 수 있으므로 Java 에선 쓸 수 없다
  
![image](https://user-images.githubusercontent.com/7943694/75609099-2b92c280-5b49-11ea-94f8-1b8c2411d94a.png)

### Facade Pattern

