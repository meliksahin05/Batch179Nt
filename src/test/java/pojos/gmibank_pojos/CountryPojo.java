package pojos.gmibank_pojos;

import java.util.List;

public class CountryPojo {



    private Integer id;

    private String name;

    private List<StatePojo> statePojos;

    public CountryPojo() {
    }

    public CountryPojo(Integer id, String name, List<StatePojo> statePojos) {
        this.id = id;
        this.name = name;
        this.statePojos = statePojos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StatePojo> getStates() {
        return statePojos;
    }

    public void setStates(List<StatePojo> statePojos) {
        this.statePojos = statePojos;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + statePojos +
                '}';
    }
}
