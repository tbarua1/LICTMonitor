package licttrainer.ttinnovations.lictmonitor;
/**
 * Created by tarun on 3/2/17.
 */
public class GetSetTrainerInfo
{
private String university,Trainername,track,contactnumber,skypeid,email,whatsappno;

    public String getWhatsappno() {
        return whatsappno;
    }

    public void setWhatsappno(String whatsappno) {
        this.whatsappno = whatsappno;
    }

    @Override
    public String toString() {
        return "GetSetTrainerInfo{" +
                "university='" + university + '\'' +
                ", Trainername='" + Trainername + '\'' +
                ", track='" + track + '\'' +
                ", contactnumber='" + contactnumber + '\'' +
                ", skypeid='" + skypeid + '\'' +
                ", email='" + email + '\'' +
                ", whatsappno='" + whatsappno + '\'' +
                '}';
    }

    public String getSkypeid() {
        return skypeid;
    }

    public void setSkypeid(String skypeid) {
        this.skypeid = skypeid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getTrainername() {
        return Trainername;
    }

    public void setTrainername(String trainername) {
        Trainername = trainername;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}
