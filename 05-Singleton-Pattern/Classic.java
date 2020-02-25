/*
가장 기본적인 구현
Lazy Initialization 으로 리소스를 절약할 수 있다
멀티스레드 환경에서는 여러개의 인스턴스가 만들어질 수 있다는 문제가 있다
*/
class Singleton {
  private static Singleton instance;

  private Singleton() {}

  public static Singleton getInstance() {
    if(instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}