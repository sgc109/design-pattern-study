/*
Class 가 로드될 때 초기화 되므로 
멀티스레드 환경에서도 unique 한 인스턴스는 보장되지만
Lazy initialization 이 아니라는 단점이 있다.
*/
class Singleton {
  private static Singleton instance = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
    return instance;
  }
}