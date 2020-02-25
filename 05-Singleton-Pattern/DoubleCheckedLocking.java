/*
줄여서 DCL 이라고 불린다.
Lazy initialization 과 성능 모두 준수하나
구현이 복잡하며, 이 구현 또한 낮은 확률로 반례가 있어
둘 이상의 인스턴스가 생성될 수 있다고 한다.
*/
class Singleton {
  private volatile static Singleton instance;

  private Singleton() {}

  public static Singleton getInstance() {
    if(instance == null) {
      synchronized (Singleton.class) {
        if(instance == null) {
          instance = new Singleton();
        }
      } 
    }
    return instance;
  }
}