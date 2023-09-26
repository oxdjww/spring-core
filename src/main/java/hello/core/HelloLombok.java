package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok1 = new HelloLombok();
        HelloLombok helloLombok2 = new HelloLombok();

        helloLombok1.setName("asdfasdf");

        String name1 = helloLombok1.getName();
        System.out.println("name1 = " + name1);

        System.out.println("hell = " + helloLombok1);
    }
}
