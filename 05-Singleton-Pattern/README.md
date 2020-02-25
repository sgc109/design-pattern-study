## 05 Singleton Pattern

### 특징
* single instance 와 global access point 제공
* private 생성자라 상속불가
* global variables 와 다른점은 lazy initialization 이 가능
* class loader 여러개일 땐 그 개수만큼의 instance 가 생기므로 주의
* 멀티스레드일때 *volatile static* 으로 instance 변수 선언해야함
* volatile 은 CPU 캐시가 아니라 메모리에 저장하라는 뜻. 캐시에 저장하면 스레드마다 서로 사용하는 데이터가 다를 수 있기 때문
* Java 1.2 이전 버전에선 GC 가 싱글톤을 죽일 수 있다
* Java 1.5 이전 버전에선 volatile 키워드가 double-checked locking 이 제대로 동작하지 않도록하는 JVM implementation 을 가지는 경우가 많음. 고로 멀티스레드에서  동작 제대로 안할 수 있음

### 구현방식
* 공통
  - instance 를 담을 static field
  - private default constructor
  - instance 를 리턴해줄 static method
* 싱글스레드
  - static factory method 에서 null 체크를 하여, null 일 때만 *new* 로 static field 초기화. 그다음 instance 를 리턴
  - enum 으로 구현할 수도 있음 (by Effective Java 3rd, item3)
* 멀티스레드
  - class 가 로드되는 순간 초기화 (static 으로 선언된 instance 에 *new* 키워드로 초기화)
  - lock (synchronized static method)
  - double-checked lock (null-check, lock, and additional null-check)
