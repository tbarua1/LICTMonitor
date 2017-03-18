package licttrainer.ttinnovations.lictmonitor;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
public class University
{
    private Long id;
    private String name, university_type,status;
    private Date date;
    private boolean program_topup, program_fs;
    private double latitude, longitude;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", university_type='" + university_type + '\'' +
                ", date=" + date +
                ", program_topup=" + program_topup +
                ", program_fs=" + program_fs +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity_type() {
        return university_type;
    }

    public void setUniversity_type(String university_type) {this.university_type = university_type;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isProgram_topup() {
        return program_topup;
    }

    public void setProgram_topup(boolean program_topup) {
        this.program_topup = program_topup;
    }

    public boolean isProgram_fs() {
        return program_fs;
    }

    public void setProgram_fs(boolean program_fs) {
        this.program_fs = program_fs;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

