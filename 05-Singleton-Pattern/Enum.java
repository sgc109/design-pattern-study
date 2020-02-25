/*
Effective Java 의 저자 조슈아 블로크가 제안한 방식
단일 인스턴스를 보장해주며, Serialization 등을 기본적으로 지원한다는 장점이 있음
하지만 lazy initialization 이 아니며,
인스턴스 생성시 외부에서 parameter 를 전달해줄 수 없다는 단점이 있음
*/
public enum Singleton {
  INSTANCE;
}