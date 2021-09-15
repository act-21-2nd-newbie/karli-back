package guide.springboot.sample;

public class TaskAttributes {
    private final String details;

    public TaskAttributes(final String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
