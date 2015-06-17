package org.bdigital.compose.sdk.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeEntitiesMetadata {

    @JsonCreator
    public ComposeEntitiesMetadata(@JsonProperty("items_count") Integer items_count, @JsonProperty("prev_url") String prev_url, @JsonProperty("next_url") String next_url) {
        this.items_count = items_count;
        this.prev_url = prev_url;
        this.next_url = next_url;
    }

    @JsonProperty("items_count") private Integer items_count;
    @JsonProperty("prev_url") private String prev_url;
    @JsonProperty("next_url") private String next_url;

    public Integer getItems_count() {
        return items_count;
    }

    public void setItems_count(Integer items_count) {
        this.items_count = items_count;
    }

    public String getPrev_url() {
        return prev_url;
    }

    public void setPrev_url(String prev_url) {
        this.prev_url = prev_url;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

}
