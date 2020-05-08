
package com.maltem.pokedex.datamodels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "futureBranches"
})
public class Evolution implements Serializable
{

    @JsonProperty("futureBranches")
    private List<FutureBranch> futureBranches = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6209785968599301468L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Evolution() {
    }

    /**
     * 
     * @param futureBranches
     */
    public Evolution(List<FutureBranch> futureBranches) {
        super();
        this.futureBranches = futureBranches;
    }

    @JsonProperty("futureBranches")
    public List<FutureBranch> getFutureBranches() {
        return futureBranches;
    }

    @JsonProperty("futureBranches")
    public void setFutureBranches(List<FutureBranch> futureBranches) {
        this.futureBranches = futureBranches;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
