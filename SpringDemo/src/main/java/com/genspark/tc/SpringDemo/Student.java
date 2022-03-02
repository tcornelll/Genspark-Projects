package com.genspark.tc.SpringDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student {
    public int studentId;
    public String name;
    public List<String> contactNumbers;
    public Address add;

    public Student(int studentID, String name, List<String> contNums, Address add){
        this.studentId = studentID;
        this.name = name;
        this.contactNumbers = contNums;
        this.add = add;
    }

    public Student(){}

    public int getStudendId() {
        return studentId;
    }

    public void setStudendId(int studendId) {
        this.studentId = studendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }

    @Override
    public String toString() {

    String first = String.format("Id: %d%nName: %s%nAddress: %s%n", this.studentId, this.name, this.add.toString());
    String second = contactNumbers.stream().reduce("", (last, next) -> last + "\n" + next);
    String secondFmt = String.format("Contacts: %s%n", second);
    return first + secondFmt;

    }
}
