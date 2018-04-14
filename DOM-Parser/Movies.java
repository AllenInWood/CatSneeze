package dom;
import java.util.List;

public class Movies {

    private String id;

    private String title;

    private Integer year;

    private String director;

    private List<String> genres;

    public Movies(String id, String title, Integer year, String director, List<String> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        if (genres != null) {
            this.genres = genres;
        }
        else {
            this.genres = null;
        }
    }

    public Movies() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getGenres() {
        return genres == null ? null : genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
