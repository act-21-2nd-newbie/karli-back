package guide.springboot.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class MytaskIdentifier {

    private Integer id;

    public MytaskIdentifier(Mytask mytask) {
        this.id = mytask.getId();
    }
    public MytaskIdentifier(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
