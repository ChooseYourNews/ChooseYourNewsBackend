package com.revature.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
@Table(name = "interests", schema = "public")
//@SecondaryTable(name = "UserInterests",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
//@org.hibernate.annotations.Table(
//        appliesTo = "UserInterests",
//        sqlInsert = @SQLInsert(
//                sql = "INSERT INTO person_details (image, person_id, valid) VALUES (?, ?, true) ",
//                check = ResultCheckStyle.COUNT
//        ),
//        sqlDelete = @SQLDelete(
//                sql = "UPDATE person_details SET valid = false WHERE person_id = ? "
//        )
//)
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "interest_name")
    private String interestName;

    public Interest(){
        super();
    }

    public Interest(String interestName) {
        super();
        this.interestName = interestName;
    }

    public Interest(int id, String interestName) {
        super();
        this.id = id;
        this.interestName = interestName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInterestName() {
        return interestName;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", interestName='" + interestName +
                '}';
    }
}
