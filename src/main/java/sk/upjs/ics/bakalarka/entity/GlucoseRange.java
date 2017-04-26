package sk.upjs.ics.bakalarka.entity;

public class GlucoseRange {

    private Long id;
    private float high;
    private float low;
    private int noOfDays;
    private String units;

    public GlucoseRange(GlucoseRange range) {
        this.high = range.high;
        this.low = range.low;
        this.noOfDays = range.noOfDays;
        this.units = range.units;
    }

    public GlucoseRange() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Range{" + "id=" + id + ", high=" + high + ", low=" + low + ", noOfDays=" + noOfDays + ", units=" + units + '}';
    }
}
