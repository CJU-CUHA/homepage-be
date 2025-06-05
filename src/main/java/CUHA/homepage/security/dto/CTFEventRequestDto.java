package CUHA.homepage.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class CTFEventRequestDto {

    private List<OrganizerDto> organizers;

    @JsonProperty("ctftime_url")
    private String ctftimeUrl;

    @JsonProperty("ctf_id")
    private int ctfId;

    private double weight;
    private DurationDto duration;

    @JsonProperty("live_feed")
    private String liveFeed;

    private String logo;
    private int id;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime start;

    private int participants;
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime finish;

    private String description;
    private String format;

    @JsonProperty("is_votable_now")
    private boolean isVotableNow;

    private String prizes;

    @JsonProperty("format_id")
    private int formatId;

    private boolean onsite;
    private String restrictions;
    private String url;

    @JsonProperty("public_votable")
    private boolean publicVotable;

}
