/*
현재까지 가장 완벽하다고 평가받은 이디엄
아직까지는 문제가 없다고 한다
Lazy initialization, 멀티스레드, 성능 모두 완벽하다
*/
class Singleton {
  private Singleton() {}

  public static Singleton getInstance() {
    return LazyHolder.instance;
  }

  private static class LazyHolder {
    private static final Singleton instance = new Singleton();
  }
}