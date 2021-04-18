package ru.stqa.pft.addressbook.model;

public class contactData {
    private  String firstname;
    private  String lastname;
    private  String address;
    private String homephone;
    private String mobilephone;
    private String workphone;
    private String allPhones;
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

    public contactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public contactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public contactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public contactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
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
