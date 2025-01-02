## 📖 Item9. try-finally보다는 try-with-resources를 사용해라

---

자바 라이브러리에는 close 메소드를 호출하여 직접 닫아줘야 하는 자원이 많음
- InputStream, OutputStream, java.sql.Connection 등이 등장함
- 자원 close를 놓친 다면, 예측할 수 없는 성능 문제로 이어질 수 있음

**전통적으로 try-finally 가 사용됨**

예시 - try-finally(더 이상 자원을 회수하는 최선의 방법은 아님)

자원을 하나 더 사용한다면?
- 자원이 둘 이상이 되면 try-finally 방식은 너무 지저분함
- 두 번째 예외가 첫 번째 예외를 집어삼킴

**자바 7버전 이후 등장한 try-with-resources 방식**

- 해당 자원이 AutoCloseable 인터페이스를 구현해야 함
- 짧고 읽기 쉬우며, 문제를 진단하기도 좋음
- catch절을 사용할 수 있으며, catch절 덕분에 try 문을 더 중첩하지 않고 다수의 예외를 처리할 수 있음

