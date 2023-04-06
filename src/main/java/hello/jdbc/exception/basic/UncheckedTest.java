package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UncheckedTest {


    //runtimeexception을 상속받은 예외는 언체크 예외가 된다.

    static class MyUncheckedException extends RuntimeException{
        public MyUncheckedException(String message){
            super(message);
        }
    }

    //Uncehcked 예외는 예외를 잡거나,던지지 않아도됨.자동으로 밖으로 던져줌.

    static class Service{
        Repository repository = new Repository();
        //필요한경우 예외를잡아서 처리하면됨.
        public void callCatch(){
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외처리,message={}", e.getMessage(), e);
            }
        }
        //예외를 잡지않아도됨.자연스럽게 상위로 넘어감.
        public void callThrow(){
            repository.call();
        }
    }

    public void callThrow() {

    }

    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }

}
