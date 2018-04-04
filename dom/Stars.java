package dom;
import java.util.List;
import java.util.ArrayList;

public class Stars {

    private List<String> movieId;

    private String id;

    private String name;

    private Integer birthYear;

    public Stars(String id, String name, Integer birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.movieId = new ArrayList<String>();
    }

    public Stars() {
        this.movieId = new ArrayList<String>();
    }

    public List<String> getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId.add(movieId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
}
