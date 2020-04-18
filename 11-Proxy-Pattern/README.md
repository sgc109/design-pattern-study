## 11 Proxy Pattern

![image](https://user-images.githubusercontent.com/7943694/79637851-48e52400-81bd-11ea-8d9a-ab4571312903.png)

```
Proxy Pattern 은 어떤 객체로의 접근을 제어하기 위해 대리자(proxy)를 제공하는 패턴
```

* Proxy 는 특정 객체(Real Subject)가
  * Remote machine 에서 실행되거나
  * 생성하는데 비용이 많이 들거나
  * 접근을 제한할 때(권한에 따라 접근을 허용할 때)
* 유용하다
* 여러가지 형태로 활용이 된다
* 대표적인 예
  * [Remote Proxy]()
  * [Virtual Proxy]()
  * [Protection Proxy](#protection-proxy)
* 그외의 예
  * Caching Proxy
  * Firewall Proxy
  * Smart Reference Proxy
  * Synchronization Proxy
  * Complexity Hiding Proxy(Facade Proxy)
  * Copy-On-Write Proxy(Virtual Proxy 의 변형)
* 단점은 클래스의 수가 더 많아 질 수 있다
  * 다른 Wrapper 형식의 패턴들과 마찬가지로.
* Client 가 Proxy 를 쓰게 하는 방법
  * 흔한 방법중 하나는 Factory method 를 만들어서 기존의 subject 를 wrapping 하는 proxy 를 만들어서 반환


### Remote Proxy
* Client 와 Remote object 사이의 interaction 을 제어한다
* RMI(Remote Method Invocation) 에서 Remote Proxy 를 사용한다
* Stub(Proxy) 과 Skeleton 이 각각 client helper 와 server helper 의 역할을 하여 통신을 담당한다

### Virtual Proxy
* 인스턴스의 생성에 비용이 많이 드는 경우 사용
* 예를 들어, swing 에서 icon 가 network 상의 이미지를 띄울 때 사용

### Protection Proxy 
* Caller 에 기반하여 접근을 제어하는 프록시
* Java 의 Dynamic Proxy API 를 사용하여 구현
![image](https://user-images.githubusercontent.com/7943694/79637867-5ef2e480-81bd-11ea-910b-00c13d76df37.png)
* 데이팅 앱을 예로 들면, 자신의 개인정보는 자신만 변경할 수 있어야 하고, 자신의 평가점수는 타인만 변경할 수 있어야 하기 때문에 접근 권한을 제어하기위해 Protection Proxy 가 필요
* Proxy.newProxyInstance() 로 런타임에 생성
* 런타임에 생성해서 Dynamic Proxy
* 사용법
    * InvocationHandler 를 작성(java.lang.reflect.InvocationHandler 인터페이스를 구현)
    * Real subject 를 생성자에 넣어 전 단계에 작성한 InvocationHandler 를 인스턴스화
    * Proxy.newProxyInstance() 의 마지막 인자로 InvocationHandler 객체를 넣어주며 dynamic proxy 생성
    * proxy 를 사용하여 원하는 메소드를 호출

#### RMI 사용법
* java.rmi.Remote 를 상속받는 Remote interface 를 만들어 메서드를 정의한다
* 각 메서드는 RemoteException 을 throw 해야한다
* 이 interface 를 구현하고 UnicastRemoteObject 를 상속받는 concrete class 를 만든다
* 서버측에서는 Naming.rebind() 를 통해 RMI Registry 에 service 에 해당하는 stub 이 등록되게 한다
* 클라측에서는 Naming.lookup() 을 통해 입력한 서버의 RMI Registry 에 접근하여 stub 을 받아온다
* 이 때 앞서 정의한 Remote interface 타입으로 변환하여 사용한다
* 이 때 stub 이 Remote proxy 의 역할을 한다
* Java 5 부터는 rmic 로 stub 만들 필요 없이 Dynamic Proxy 를 사용하면 동적으로 생성된다.

Proxy 는 Real Subject 의 대역이다
client 는 Proxy 를 통해 Real Subject 에 접근한다
Subject 라는 동일한 인터페이스를 구현하기 때문에 Proxy 를 사용해서 Real Subject 에 접근할 수 있다
Proxy 는 가끔 Real Subject 의 생성과 소멸을 담당한다

### Proxy 패턴과 Decorator 패턴
* Object 를 wrapping 하여 function call 을 delegate 한다는 점에서 비슷
* 하지만 둘의 목적이 다름
* Decorator 패턴은 이름에서도 알 수 있듯이 기능을 추가하는것이 목표
* Proxy 패턴은 access 를 control 하는 것이 목표
* Decorator 패턴은 단순히 다른 object 를 wrapping 하지만
* Proxy 패턴은 사실상 wrapping 이 아닌경우가 있음
  * Virtual Proxy 의 경우 proxy 를 처음 사용하는 시점에는 real subject 가 아예 존재하지 않기도 함
  * Remote Proxy 의 경우 object 가 local 이 아닌 remote machine 에 존재함
