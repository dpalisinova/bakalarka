package sk.upjs.ics.bakalarka.entity;



public class PossibleCause {

    private Long id;
    private String cause;

    public PossibleCause(String cause) {
        this.cause = cause;
    }

    public PossibleCause() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "PossibleCause{" + "id=" + id + ", cause=" + cause + '}';
    }
}
