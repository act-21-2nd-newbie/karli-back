package guide.springboot.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class MytaskIdentifier {

    private String id;

    public MytaskIdentifier(Mytask mytask) {
        this.id = mytask.getId();
    }
    public MytaskIdentifier(String id) {
        this.id = id;
    }
}
