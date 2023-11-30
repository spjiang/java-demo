package springboottest.demo;

public class Test {

    public Person test01(){
        Person person = new Person();
        person.setA("spjiangl");
        sTest(person);
        return person;
    }

    public void sTest (Person person) {
        person.setB(1);
    }
}
