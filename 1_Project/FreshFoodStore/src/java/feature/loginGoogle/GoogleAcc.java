package feature.loginGoogle;

/**
 *
 * @author HoangNam
 */
public class GoogleAcc {

    private String id;

    private String email;

    private boolean email_verified;

    private String name;

    private String given_name;      //Tên

    private String family_name;     //Họ

    private String picture;         //ảnh đại diện

    public GoogleAcc() {
    }

    public GoogleAcc(String id, String email, boolean email_verified, String name, String given_name, String family_name, String picture) {
        this.id = id;
        this.email = email;
        this.email_verified = email_verified;
        this.name = name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "GoogleAcc{" + "id=" + id + ", email=" + email + ", email_verified=" + email_verified + ", name=" + name + ", given_name=" + given_name + ", family_name=" + family_name + ", picture=" + picture + '}';
    }
    
}
