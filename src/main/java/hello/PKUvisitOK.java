package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PKUvisitOK {
    public final boolean success;
    public final long results;
    public final Row rows[];

    @JsonCreator
    public PKUvisitOK(@JsonProperty("success") boolean success, @JsonProperty("results") long results, @JsonProperty("rows") Row[] rows){
        this.success = success;
        this.results = results;
        this.rows = rows;
    }

    public static final class Row {
        public final String action;
        public final String date;
        public final String leftCount;
        public final long leftCount1;
        public final long leftCount2;
        public final String limitCount;
        public final long limitCount1;
        public final long limitCount2;
        public final String timeInterval;
        public final String timeInterval1;
        public final String timeInterval2;

        @JsonCreator
        public Row(@JsonProperty("action") String action, @JsonProperty("date") String date, @JsonProperty("leftCount") String leftCount, @JsonProperty("leftCount1") long leftCount1, @JsonProperty("leftCount2") long leftCount2, @JsonProperty("limitCount") String limitCount, @JsonProperty("limitCount1") long limitCount1, @JsonProperty("limitCount2") long limitCount2, @JsonProperty("timeInterval") String timeInterval, @JsonProperty("timeInterval1") String timeInterval1, @JsonProperty("timeInterval2") String timeInterval2){
            this.action = action;
            this.date = date;
            this.leftCount = leftCount;
            this.leftCount1 = leftCount1;
            this.leftCount2 = leftCount2;
            this.limitCount = limitCount;
            this.limitCount1 = limitCount1;
            this.limitCount2 = limitCount2;
            this.timeInterval = timeInterval;
            this.timeInterval1 = timeInterval1;
            this.timeInterval2 = timeInterval2;
        }
    }
}