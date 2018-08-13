package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PKUvisitJIDexpire {
    public final boolean success;
    public final boolean validate_notice_stamp;

    @JsonCreator
    public PKUvisitJIDexpire(@JsonProperty("success") boolean success, @JsonProperty("validate_notice_stamp") boolean validate_notice_stamp){
        this.success = success;
        this.validate_notice_stamp = validate_notice_stamp;
    }

    @Override
    public String toString() {
        return "PKUvisitJIDexpire{" +
                "success='" + success + '\'' +
                ", validate_notice_stamp=" + validate_notice_stamp +
                '}';
    }
}