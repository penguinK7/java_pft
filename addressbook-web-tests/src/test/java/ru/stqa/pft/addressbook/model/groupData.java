package ru.stqa.pft.addressbook.model;


public class groupData {
    private int id = Integer.MAX_VALUE;;
    private  String name;
    private  String header;
    private String footer;


    public int getId() {        return id;
    }
    public groupData withId(int id) {
        this.id = id;
        return this;
    }
    public groupData withName(String name) {
        this.name = name;
        return this;
    }

    public groupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public groupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        groupData groupData = (groupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
