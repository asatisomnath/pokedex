
package com.maltem.pokedex.datamodels;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "candyCost"
})
public class CostToEvolve_ implements Serializable
{

    @JsonProperty("candyCost")
    private Integer candyCost;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 297499681394600942L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CostToEvolve_() {
    }

    /**
     * 
     * @param candyCost
     */
    public CostToEvolve_(Integer candyCost) {
        super();
        this.candyCost = candyCost;
    }

    @JsonProperty("candyCost")
    public Integer getCandyCost() {
        return candyCost;
    }

    @JsonProperty("candyCost")
    public void setCandyCost(Integer candyCost) {
        this.candyCost = candyCost;
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
