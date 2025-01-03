## 📖 Item3. private 생성자나 열거 타입으로 싱글턴임을 보증하라

---

**싱글턴**
- 인스턴스를 오직 하나만 생성할 수 있는 클래스를 의미함 <br/>

예시 - 함수, 무상태 객체, 설계상 유일해야 하는 시스템 컴포넌트

**싱글턴의 단점**
- 클래스를 싱글턴으로 만들며, 테스트하기가 어려워질 수 있음 <br/>
-> 타입을 인터페이스로 정의한 다음, 해당 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 가짜(mock)으로 구현하여 대체할 수 없기 때문
<br/>

**싱글턴을 만드는 방식 2가지** <br/>
- 두 방식 모두 생성자는 private으로 감춰드고, 유일한 인스턴스에 접근할 수 있는 수단으로 public static 멤버를 하나 만들어둠

1. public static 멤버가 final 필드인 방식
- private 생성자는 public static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 1번만 호출됨
- public이나 protected 생성자가 없으므로 Elvis 클래스가 초기화할 때, 만들어진 인스턴스가 전체 시스템에서 하나뿐임을 보장함
- 리플랙션 API인 AccessibleObject.setAccesible을 사용하여 private 생성자를 호출할 수 있음<br/>
-> 해당 공격을 방어하려면 생성자를 수정하여 2번째 객체가 생성되려 할 때 예외를 던짐
<br/>

**장점**
- 클래스가 싱글턴임이 API에 명확히 드러남 <br/>
-> public static필드가 final이니 절대로 다른 객체를 참조할 수 없음
- 간결함

<br/>

2. 정적 팩토리 메서드를 public static 멤버로 제공함
- Elvis.getInstance는 항상 같은 객체의 참조를 반환함으로 제2의 Elvis 인스턴스가 만들어지지 않음
- 리플렉션을 통한 예외도 동일하게 적용됨

**장점**
- API를 변경하지 않아도 싱글턴이지 않게 변경할 수 있음 <br/>
-> 팩터리 메서드가 호출하는 스레드별로 다른 인스턴스를 넘겨주게 할 수 있음
- 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있음
- 정적 팩터리의 참조를 공급자로 사용할 수 있음

예시 - Elvis::getInstance를 Supplier<Elvis>로 사용함

-> 해당 장점들이 굳이 필요하지 않다면 public 필드 방식이 좋음
<br/>

**싱글턴 클래스 직렬화**
- 단순히 Serializable을 구현한다고 선언하는 것만으로는 부족함
- 모든 인스턴스 필드를 일시적이라고 선언하고, readResolve 메서드를 제공해야 함<br/>
-> 이렇게 하지 않으면 직렬화된 인스턴스를 역직렬화할때마다 새로운 인스턴스가 생성됨<br/>
-> 가짜 Elvis가 탄생함

방지 - Elvis클래스에 readResolve 메서드를 추가해야 함
```Java
private Object readResolve(){
    return INSTANCE;
}
```

3. 원소가 하나인 열거 타입을 선언하는 것
- public 필드 방식과 비슷하지만, 간결하고 추가 노력없이 직렬화를 할 수 있음
- 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽하게 막아줌 <br/>
-> **원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법임** <br/>
-> **단, 만들려는 싱글턴이 Enum외의 클래스를 상속해야 한다면, 해당 방법은 사용할 수 없음**


