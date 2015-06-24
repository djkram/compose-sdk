package org.bdigital.compose.sdk.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComposeEntitiesData {

    @JsonCreator
    public ComposeEntitiesData(@JsonProperty("security_data") String security_data, @JsonProperty("deployment_data") String deployment_data, @JsonProperty("catalog_data") String catalog_data,
    	@JsonProperty("id") Integer id, @JsonProperty("type") String type, @JsonProperty("name") String name) {
        this.security_data = security_data;
        this.deployment_data = deployment_data;
        this.catalog_data = catalog_data;
        this.id = id;
        this.type = type;
        this.name = name;
    }

    private String security_data;
    private String deployment_data;
    private String catalog_data;
    private Integer id;
    private String type;
    private String name;

    public String getSecurity_data() {
        return security_data;
    }

    public void setSecurity_data(String security_data) {
        this.security_data = security_data;
    }

    public String getDeployment_data() {
        return deployment_data;
    }

    public void setDeployment_data(String deployment_data) {
        this.deployment_data = deployment_data;
    }

    public String getCatalog_data() {
        return catalog_data;
    }

    public void setCatalog_data(String catalog_data) {
        this.catalog_data = catalog_data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
