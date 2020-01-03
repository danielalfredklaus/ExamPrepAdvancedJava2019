package Testing;

import Reflections_and_Annotations.Author;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Author(value="Dan")
@Author(value="StillJustDan")
public class Human {
    String name;
    int age;
    int salary;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void increaseSalary(int percent){
        salary = (1+percent/100) * salary;
    }
}
