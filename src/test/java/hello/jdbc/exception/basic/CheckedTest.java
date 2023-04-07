package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

    //Exception을 상속 받은 예외는 체크예외가 된다

    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    //예외를 잡아서 처리하거나 던지거나 둘중하나 필수선택.
    static class Service {
        Repository repository = new Repository();
        //예외를 잡아서 처리하는 코드
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외처리로직
                log.info("예외처리,message={}", e.getMessage(), e);//exception출력할땐 마지막에 적으면됨.
            }
        }
    }

    static class Repository {
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }


}
