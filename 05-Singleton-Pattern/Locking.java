/*
처음 instantiation 을 할 때만 lock 을 걸면 되는데
이후에도 계속 불필요한 lock 으로 성능 저하를 야기한다는 단점이 있다.
*/
class Singleton {
  private static Singleton instance;

  private Singleton() {}

  public static synchronized Singleton getInstance() {
    if(instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}