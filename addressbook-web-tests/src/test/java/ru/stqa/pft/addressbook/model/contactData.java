package ru.stqa.pft.addressbook.model;

import ru.stqa.pft.addressbook.appmanager.HelperBase;

public class contactData {
    private  String firstname;
    private  String lastname;
    private  String address;
    private  String mobile;
    private  String email;
    private String group; //добавили возможность выбора группы при создании контакта
    private int id = Integer.MAX_VALUE;;


    public int getId() {        return id;
    }
    public contactData withId(int id) {
        this.id = id;
        return this;
    }

    public contactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public contactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public contactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public contactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public contactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public contactData withGroup(String group) {
        this.group = group;
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

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

  public String getGroup() {
      return group;
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

        contactData that = (contactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }
}
