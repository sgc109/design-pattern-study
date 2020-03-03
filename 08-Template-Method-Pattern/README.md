## 08 Template Method Pattern

**superclass 가 알고리즘을 정의하고, 단계별로 실행될 메소드 등 로직을 정해놓으면 subclass 에게 일부 로직의 구현만을 맡기는 패턴**
* 프레임워크에서 많이 사용된다. 
* 프레임워크는 개발자에게 일부 로직의 구현만 위임하고, 그 로직들이 언제 어떤 로직이 실행될지에 대한 흐름은 직접 제어하기 때문이다.
* factory method 는 template method 의 특수한 형태다
* 예시
```java
public abstract class CaffeineBeverage {
    final void prepareRecipe() { // Template Method
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() { // Concrete Method
        System.out.println("Boil water");
    }

    void pourInCup() { // Concrete Method
        System.out.println("Pour in cup");
    }

    boolean customerWantsCondiments() { // Hook
        return true;
    }
}

```

일부 디테일만 다른 비슷한 로직들을 추상화시켜 공통된부분만 분리시킨 뒤 캡슐화하여 template method 로 만들고 디테일은 subclass 에게 위임하면 중복 코드를 많이 줄일 수 있어서, 로직의 변경도 한 곳에서 관리할수있다.

template method 는 위의 알고리즘이 정의되어있는 메소드다. template method 의 일부 단계는 아직 정의되지 않아 subclass 가 무조건 구현해야만 한다.

template method 는 final 로 선언하여 알고리즘이 변경되지 않도록 한다.

concrete method 란 template method 를 포함하는 클래스에서 이미 구현되어있는 메소드 subclass 에서 이를 호출하기도한다.
concrete method 도 final 로 선언한다

hook 은 optional 한 step 을 정의 할 수 있다. 꼭 구현하지 않아도된다. 아무 로직이 없거나 default 로직이 있다.
boolean 을 반환하는 hook 을 만들어서 조건부 실행제어가 가능하다.
각 단계의 실행 직전/후에 subclass 가 react 할 수 있는 통로

꼭 항상 상속의 형태인것은 아니다. Arrays.sort 는 설계자가 모든 클래스에 대해서 사용할 수 있게 하기 위해 static method 로 정의하였고, 자바의 배열은 상속이 불가능하다는 점으로인해 interface 를 사용했다. 정렬되는 item 에게 비교 로직의 구현을 맡겼다. Comparable 의 item 이 compareTo 만 구현하면 이를 호출하여 사용하는것은 sort 의 mergeSort 내에서 한다. 
Arrays.sort compareTo
JFrame 의 paint(hook 이라 JFrame 클래스에선 아무것도 하지않는다)
applet 의 paint(hook) 와 repaint

Hollywood Principle
Don't call us, we'll call you

high-level 컴포넌트가 low-level 컴포넌트의 로직의 호출 타이밍을 정하고, low-level 컴포넌트는 단지 로직을 정의하기만 하는 원칙이다.


소스코드
CaffeineBeverage
Arrays.sort
JFrame
Applet
