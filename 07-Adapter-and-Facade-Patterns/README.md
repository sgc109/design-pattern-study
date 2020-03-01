## 07 Adapter and Facade Patterns

* 두 패턴 모두 기존의 interface 를 다른 interface 로 대체하여 Clients 와 이들이 사용하는 system 과의 커플링을 낮추는 것을 목적으로 한다.
* 하지만 둘의 목적과  분명히 다르다

|  Pattern  	| Intent                                               	|
|:---------:	|------------------------------------------------------	|
| Decorator 	| Doesn't alter the interface, but adds responsibility 	|
|  Adapter  	| Converts one interface to another                    	|
|   Facade  	| Makes an interface simpler                           	|

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
* Adapter 는 incompatible 한 interface 간의 변환을 위한 것
* 반면, Facade 는 기존의 복잡한 interface 를 단순화하기 위해 interface 를 교체하는것
* Client 는 단 하나의 Friend 인 Facade 만을 통해서 subsystem 을 조작한다(단 하나의 Friend 를 갖는것은 OOP 에서 좋은것이다)
* Facade 가 관리하는 subsystem 은 Principle of Least Knowledge 를 지키도록 해야한다
* 만약 subsystem 이 너무 복잡하고, 너무 많은 클래스가 섞인다면, 추가적인 Facades 들을 만들 수도 있다
* 단점으로는 복잡도가 감소하고 유지보수성이 증가하지만, 런타임 퍼포먼스가 감소하거나 Wrapper 클래스가 많아질 수 있다
* 참고로 Facade[fəˈsɑːd]  는 **퍼사드**로 읽는다

#### Principle of Least Knowledge
* Principle of Least Knowledge - talk only to your immediate friends
* 데메테르의 법칙(The law of demeter) 이라고도 불리나 선호하지는 말자 (이름만 봐선 의미를 알수없고, 법칙까진 아니다)
* 서로 강하게 결합된 많은 클래스들을 가지고, 이 중 하나가 변경 되었을 때 이 변화가 다른 부분에 cascade 되는것을 막는다
* 여러 클래스들 간에 많은 의존성이 존재할 경우, 이는 유지보수하기 어렵고, 다른 사람이 이해하기 어려운 fragile 한 system 이 된다

#### 호출해도 되는 메소드
* 자신이 가진 메소드
* 메소드의 파라미터로 전달되는 객체가 가진 메소드
* 메소드가 생성하거나 인스턴스화 하는 객체의 메소드
* 멤버 변수의 메소드

#### 예
```java
public class Car {
    Engine engine;
    // other instance variables

    public Car() {
        // initialize engine, etc.
    }

    public void start(Key key) {
        Doors doors = new Doors();

        boolean authorized = key.turns(); // 파라미터로 넘어온 객의 메소드는 호출해도 된다

        if (authorized) {
            engine.start(); // 인스턴스 변수의 메소드는 호출해도 된다
            updateDashboardDisplay(); // 객체 내의 다른 메소드는 호출해도 된다
            doors.lock(); // 직접 생성했거나 인스턴스화한 객체의 메소드는 호출해도 된다
        }
    }

    public void updateDashboardDisplay() {
        // update display
    }
}

```

#### 호출하지 말아야 하는 메소드
* 다른 메소드가 return 한 객체의 메소드

#### 예
* 위의 원칙을 적용하지 않은 코드

```java
public float getTemp() {
    Thermometer thermometer = station.getThermometer(); 
    return thermometer.getTemperature(); 
} 
```

* 원칙을 적용한 코드

```java
public float getTemp() { 
    return station.getTemperature(); 
} 
```

* 이렇게 하면 우리가 의존하는 클래스의 개수를 줄여준다

놀랍게도 다음의 코드는 Principle of Least Knowledge 를 위반하지만

```java
public House { 
    WeatherStation station;
    
    // other methods and constructor

    public float getTemp() {
      return station.getThermometer().getTemperature();
    }
}
```

다음의 코드는 Principle of Least Knowledge 를 위반하지 않는다

```java
public House { 
    WeatherStation station;

    // other methods and constructor

    public float getTemp() {
        Thermometer thermometer = station.getThermometer(); 
        return getTempHelper(thermometer);
    }

    public float getTempHelper(Thermometer thermometer) { 
        return thermometer.getTemperature();
    } 
}
```

### 참고자료
* Android 의 RecyclerViewAdapter 는 디자인 패턴에서 말하는 Adapter Pattern 을 사용한것인가
  - https://stackoverflow.com/questions/41626980/are-android-adapters-an-example-of-adapter-design-pattern
  - 상반되는 의견이 있지만 아니라고봄
  - 애초에 목적과 구조가 다르다
  - 우선 어댑터 패턴은 한 인터페이스(Adaptee)를 다른 인터페이스(Target Interface)로 변환하는 것이 목적이다
  - Adapter Pattern 에서는 Adapter 내에서 Composition 을 통해 function call 을 delegate 하지만, 안드로이드에서는 그렇지 않다
