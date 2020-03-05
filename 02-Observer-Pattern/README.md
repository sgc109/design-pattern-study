## 02 Observer Pattern

![image](https://user-images.githubusercontent.com/7943694/75998442-37fd8d80-5f44-11ea-8d4f-9ebac933f5d5.png)  

**객체들간의 일대다 의존관계를 만들어 하나의 객체가 변하면 이 객체에 의존하는 객체들이 모두 알림을 받고 자동으로 업데이트 되는 디자인 패턴**


### 날씨 앱 만들기
#### 초기버전
```java
class WeatherData {
    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();

        currentConditionsDisplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }
}
```

* 문제점
  - display 가 추가될 때마다 코드를 변경해야함
  - display 를 런타임에 추가/삭제할 수 없다
  - 인터페이스가 아닌 구체적인 구현으로 코딩하고있다
  - 변화하는부분을 캡슐화하지 못했다

#### 옵저버 패턴 적용
* Subject 라는 데이터를 관리하는 단 하나의 대상을 두고 변경시에만 동기화를 하여 Observer 들이 공유하도록 함
* 굳이 모든 대상이 데이터를 관리하지 않아도 됨
* notify 순서에 의존하지마라
* 장점
    * 어떤 클래스이던간에 상관없이 Observer 인터페이스를 구현하기만하면  데이터 변경시 알림을 받을 수 있다
    * Subject 는 각 Observer 들이 실제로 어떤 클래스인지 알 필요 없이 일관된 인터페이스로 데이터 변경 알림을 줄 수 있다
    * 새로운 Observer 를 추가해야 하는 경우에도 Subject 는 코드의 변경 없이 이를 구행할 수 있음

### Design Principal
* Strive for loosely coupled designs between objects that interact
* 느슨하게 결합된 설계는 더 유연한 코드를 만든다

### Java built-in Observer Pattern
<java built-in observer pattern 의 class diagram>

### 일반적인 observer pattern
* Subject 와 Observer 로 이루어짐
* push 방식(subject 만 observer 에게 데이터를 보내줄 수 있음)

### Java built-in observer pattern
* 특징
  * Observable 과 Observer 로 이루어짐
  * 일반적인 옵저버 패턴의 Subject interface 와는 다르게 Observable 이 클래스임
* 장점
  * push 방식과 pull 방식(옵저버가 직접 subject/observable 의 데이터를 가져가는 방식) 모두 지원
  * setChanged() 와 changed 필드를 통해 유연함을 추가했다(잦은 데이터가 변경으로 notify 를 너무 자주하는경우, 특정 수준 이상으로 크게 변경되었을 경우만 setChanged() 를 호출하여 notify 하도록 가능. pretected 로 선언되어있어서 observer 가 호출하는게아니라 subject 자신이 호출하는것임)
  * 주요 메소드를 상속받기 때문에 직접 구현할 필요없다
* 단점
  * setChanged 가 protected 여서 서브클래스 내부가 아니라면 Composition 방식으로는 호출할수가 없다
  * class 를 상속받는방식이어서 우리 나름대로 로직을 구현할수없다

### java.util Observer 는 Deprecated 
* 자바9 부터 java.util.Observable/Observer 가 deprecated 되었다
* https://stackoverflow.com/questions/46380073/observer-is-deprecated-in-java-9-what-should-we-use-instead-of-it
#### 이유
    * Observable 이 serializable 을 구현하짇 않앗고, 모든 멤버가 private 이라 serialize 하지 못한다
    * Thread safety 하지 않음
    * 뭐가 변했는지 안알려줌 단순히 변했다는것만 알수있음
    * 모든 Observable 이 똑같다. instanceof 로 검사하여 어떤 타입으로 타입캐스트하는 로직을 구현해야한다
    * 그밖에 버그 및 레거시 코드 관리의 어려움 등
#### 대안
    * java.beans 에 있는 PropertyChangeSupport 와 PropertyChangedListener (그리고 PropertyChangedEvent)
    * Listeners 는 많은 타입이 있고 callback method 도있고 casting 할 필요도없다
    * PropertyChangeListener 추천
    * Listener 도 역시 Observer Pattern 이다!

### pull 방식 vs push 방식
#### push 방식
* 장점
    * 데이터 변경시에만 알림을 받을 수 있어서 효율적임
* 단점
    * 모든 옵저버에게 일관된 방식으로 데이터를 줘야하기 때문에 옵저버 입장에서는 필요하지 않은 데이터일지라도 모두 받아야함
    * subject 가 관리하는 새로운 데이터가 추가될 경우 observer 들에게 변경된 데이터를 notify 하는 부분의 코드의 변경이 불가피함
#### pull 방식
* 장점
    * 각 옵저버들은 필요한 데이터만 getter 메소드를 통해 받아올 수 있음
    * subject 가 관리해야하는 새로운 데이터가 추가되어도 notify 해주는 부분의 코드 변경없이 getter 만 만들어 주면됨
* 단점
    * 데이터가 변경되지 않았을 때도 매번 getter 를 통해 데이터를 직접 가져가야함

**push 보단 pull 이 더 적절한 방식으로 여겨진다**  

### 실생활 예
Java swing 의 JFrame 에서 JButton 과 ActionListener 
