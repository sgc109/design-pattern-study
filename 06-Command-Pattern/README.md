## 06 Command Pattern

* Request(Command) 를 Object 로 Encapsulate 하여 해당 Request 를 큐에 넣거나, 로깅을 할 수 있게도 하며, 실행취소도 가능하게 하는 디자인 패턴
* Command 라는 인터페이스를 통해 실제 액션을 수행하는 클래스와 명령을 내리는 클래스 간의 결합도를 낮춤
* 명령을 내리는 클래스는 실제 액션이 어떻게 동작하는지 몰라도 되게 하는것
* 런타임에서 Command 를 구현하는 클래스간의 교체를 통해 동적으로 action 을 변경할 수도 있음
* 리모컨을 예로들면 리모컨의 구현을 가능한한 단순하게 만들 수 있으며, 새로운 vendor 의 제품이 추가되거나 기존의 제품이 변경되더라도 리모컨은 변경될 필요가 없음
* 리모컨의 슬롯은 실제 제품의 동작 방식에 대해 알 필요가 없음
* 단순히 버튼이 눌렸을 때 슬롯에 연결된 제품의 정의된 동작을 수행하라는 명령만 내리면 됨

### 비유
|  Client  	|          Invoker         	|    Command    	| Receiver 	|
|:--------:	|:------------------------:	|:-------------:	|:--------:	|
| Customer 	|         Waitress         	|     Order     	|   Chef   	|
|   User   	| Universal Remote Control 	| Device Driver 	|  Device  	|

### 만능 리모컨 만들기 예제
1. 각 소켓마다 on/off 버튼이 있는 리모컨
  - 각 소켓에 디바이스를 연결할 수 있다.
2. 이전 명령 실행취소 기능
  - Command interface 
3. 모드가 여러개 있는 디바이스(선풍기)
  - 하나의 소켓에 특정 디바이스의 한가지 모드로 설정할 수 있는 기능을 넣을 수 있다
4. 매크로 기능
    - Command 의 배열을 가진 또다른 Concrete Command 를 정의하여 invoker 에 셋팅한다
    - execute() 에서 반복문을 통해 들고있는 모든 Command 들의 execute() 를 호출한다
    - 실행취소 기능은, 반복문을 통해 들고있는 모든 Command 들의 undo() 를 호출한다.
5. 실행취소 여러번

### 활용 예
* Job queue, schedulers, thread pools
* Logging requests
    - 예를 들어, 스프레드시트 앱에서 변경사항이 발생할 때마다 파일 전체를 저장하는 것은 현실적으로 힘들다
    - 그러므로 매번 해당 변경 사항만 따로 store 하고, 만약 프로그램이 죽었다가 다시 살아났을 경우, 저장했던 command 들을 다시 load 하여 복구하는것
    - 해당 기능을 확장해서 Transactional 한 기능을 구현할 수도 있다
