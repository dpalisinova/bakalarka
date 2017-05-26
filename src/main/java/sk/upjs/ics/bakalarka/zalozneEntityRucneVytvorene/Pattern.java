package sk.upjs.ics.bakalarka.zalozneEntityRucneVytvorene;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Pattern {

    private Long id;
    private String type;
    private String daytime;
    private Time timePeriodStart;
    private Time timePeriodEnd;
    private int noOfDays;
    private List<String> possibleCauses = new ArrayList<>();
    private List<GlucoseRange> ranges = new ArrayList<>();

    public Pattern() {
    }
    

    public List<GlucoseRange> getRanges() {
        return ranges;
    }
//to iste pre naplnenie listu ranges, ked pride JSON, naplnim list cez pattern.setRanges
    public void sGlucoseRangeges(List<GlucoseRange> ranges) {
        this.ranges = ranges;
    }

    // list possibleCauses budemnaplnat, ked mi pride JSON s polom stringov, dam si Pattern.setPossibleCauses a ako parameter dam pole ktore si vytvorim zo stringov, ktore prisli
    public List<String> getPossibleCauses() {
        return possibleCauses;
    }

    public void setPossibleCauses(List<String> possibleCauses) {
        this.possibleCauses = possibleCauses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public Time getTimePeriodStart() {
        return timePeriodStart;
    }

    public void setTimePeriodStart(Time timePeriodStart) {
        this.timePeriodStart = timePeriodStart;
    }

    public Time getTimePeriodEnd() {
        return timePeriodEnd;
    }

    public void setTimePeriodEnd(Time timePeriodEnd) {
        this.timePeriodEnd = timePeriodEnd;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    @Override
    public String toString() {
        return "Pattern{" + "id=" + id + ", type=" + type + ", daytime=" + daytime + ", timePeriodStart=" + timePeriodStart + ", timePeriodEnd=" + timePeriodEnd + ", noOfDays=" + noOfDays + '}';
    }
}
