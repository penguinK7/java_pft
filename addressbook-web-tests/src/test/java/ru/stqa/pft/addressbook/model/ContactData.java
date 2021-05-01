package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private  String firstname;
    @Expose
    @Column(name = "lastname")
    private  String lastname;
    @Expose
    @Transient
    private  String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workphone;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Transient
    private  String email;
    @Expose
    @Transient
    private  String email2;
    @Expose
    @Transient
    private  String email3;
    @Transient
    private  String allEmails;
    @Expose

    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;;
    @Expose
    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups"
            , joinColumns = @JoinColumn(name = "id")
            , inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public int getId() {        return id;
    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilephone() {
        return mobilephone;
    }
    public String getHomephone() {
        return homephone;
    }
    public String getWorkphone() {
        return workphone;
    }
    public String getAllPhones() {
        return allPhones;

    }

    public String getEmail() {
        return email;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }



    @Override
    public String toString() {
        return "contactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
