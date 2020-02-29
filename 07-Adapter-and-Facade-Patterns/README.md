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

#### 실제 세계의 예
* Enumeration 과 Iterator
    - Enumeration 은 초기 Collections 타입인 Vector, Stack, Hashtable 등에서 사용되었다
    - Iterator 는 이후 버전에서 생긴 새로운 Collections 클래스들을 위해 생겼다
    - Enumeration 은 hasMoreElements(), nextElements() 메소드를 가졌고
    - Iterator 는 hasNext(), next(), remove() 메소드를 가졌다
    - 이제는 항상 Iterator 만을 사용하도록 하고싶기 때문에 EnumerationIterator adapter 를 만든다
    - hashMoreElements() 는 hasNext() 와 목적이 같고, nextElements() 는 next() 와 목적이 같다.
    - 고로, 어댑터의 hashNext() 와 next() 가 호출될 경우 delegate 하면 된다.
    - 하지만 Enumeration 은 remove 을 지원하지 않기 때문에 Adapter 에서 next() 가 호출되면 Exception 을 던져야한다
    - 이것은 Adapter 가 완벽하지는 않다는 것을 보여준다
    - 하지만 잘 문서화되고, Client 가 조심한다면 Adapter 는 굉장히 합리적인 솔루션이다
    - 아무튼 자바는 Iterator 를 사용하는 방향으로 가고있기에 client code 에는 Enumeration 에 의존하는 많은 레거시가 존재한다
    - 그렇기 때문에 Iterator 를 Enumeration 으로 변환해주는 어댑터도 꽤 유용하다

### Facade Pattern
* 지금까지는 incompatible 한 interface 간의 변환을 위해 어댑터를 만들었다
* 이번엔 interface 를 단순화하기 위한 어댑터

|  Pattern  	| Intent                                               	|
|:---------:	|------------------------------------------------------	|
| Decorator 	| Doesn't alter the interface, but adds responsibility 	|
|  Adapter  	| Converts one interface to another                    	|
|   Facade  	| Makes an interface simpler                           	|

