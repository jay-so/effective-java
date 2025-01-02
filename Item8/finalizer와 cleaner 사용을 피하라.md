## 📖 Item8. finalizer와 cleaner 사용을 피하라

---

JAVA는 일반적으로 2가지 객체 소멸자를 제공함
- finalizer는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적으로 불필요함
- 오작동, 낮은 성능, 이식성 문제의 원인이 되기도함<br/>
-> Java 9에서 finalizer가 사라지고 cleaner로 대안이 존재함

cleaner는 finalizer보다 덜 위험하지만, 예측할 수 없고 느리고 일반적으로 불필요함

finalizer와 cleaner는 즉시 수행된다는 보장이 없음
- 객체에 접근한 후 finalizer와 cleaner가 실행되기까지 얼마나 걸릴지 알 수 없음 <br/>
-> finalizer나 cleaner로는 제때 실행되어야 하는 작업은 절대 할 수 없음

- 수행 여부도 전혀 보장하지 않음 <br/>
-> 상태를 영구적으로 수정하는 작업에서는 절대 finalizer나 cleaner에 의존해서는 안됨

- finalizer 동작 중 발생한 예외는 무시되며, 처리할 작업이 남았더라도 그 순간 종료됨

- finalizer와 cleaner는 심각한 성능 문제도 동반함

- finalizer를 사용한 클래스는 finalizer 공격에 노출되어 심각한 보안 문제를 일으킬 수 있음 <br/>
-> finalizer 대신 final로 선언함

**대안**
- AutoCloaseable을 구현하고, 사용자가 인스턴스를 다 쓰면 close 메소드를 호출함


