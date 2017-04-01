
package sk.upjs.ics.bakalarka.entity;

public class PossibleCause {
    private int id;
    private String cause;

    public PossibleCause(String cause) {
        this.cause = cause;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
