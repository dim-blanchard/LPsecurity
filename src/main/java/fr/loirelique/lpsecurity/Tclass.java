package fr.loirelique.lpsecurity;

public class Tclass {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    

    public Tclass(String name, String username) {
        this.name = name;
        this.username = username;

        System.out.println(name + "-------" + username);
    }

}
