package hello.core.member;

//public class 선언, 다른 패키지에서도 접근 가능
    public class Member {
    private Long id;
    private String name;
    private Grade grade; //enum 으로 만든 자료형

    //생성자, 객체 생성 시 설정
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }


    //각각 값을 가져 오고, 값을 세팅함
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
