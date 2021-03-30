package ru.stqa.pft.addressbook.model;

import ru.stqa.pft.addressbook.appmanager.HelperBase;

public class contactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String address;
    private final String mobile;
    private final String email;
    private String group; //добавили возможность выбора группы при создании контакта

    public contactData(String firstname, String middlename, String lastname, String address, String mobile, String email, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;


    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
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
}
