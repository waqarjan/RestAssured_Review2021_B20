package pojo;

//POJO (Plain Old java Object) class  is used to create object that represent Data
    /*
        A POJO Class must have:
            1.	encapsulated fields (getter & setter),
            2.	public no argument constructor,
            3.	everything else is optional
     */

public class Spartan {

    // 1.	encapsulated fields (getter & setter)
    private String name;
    private String gender;
    private long phone;

    // 2.	public no argument constructor as required for Pojo, since we create constructor with argument
    public Spartan() {
    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


    // (getter)
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }


    // (setter)
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
