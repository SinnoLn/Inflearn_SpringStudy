package hello.core.singleton;

public class SingletonService {
    //static calss 레벨에 올라가기 때문에 유일한 객체로 생성된다.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    
}
