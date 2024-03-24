package org.example.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.example.AlwaysListTypeAdapterFactory;

import java.util.List;

public class Statement {
    @SerializedName("Sid")
    public String sid;

    @SerializedName("Effect")
    public String effect;

    @SerializedName("Action")
    @JsonAdapter(AlwaysListTypeAdapterFactory.class)
    public List<String> action;

    @SerializedName("Resource")
    @JsonAdapter(AlwaysListTypeAdapterFactory.class)
    public List<String> resource;

    @Override
    public String toString() {
        return "Statement{" +
                "sid='" + sid + '\'' +
                ", effect='" + effect + '\'' +
                ", action=" + action +
                ", resource='" + resource + '\'' +
                '}';
    }

    public boolean containsRequiredFields() {
        return effect != null && action != null && !action.isEmpty();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public List<String> getAction() {
        return action;
    }

    public void setAction(List<String> action) {
        this.action = action;
    }

    public List<String> getResource() {
        return resource;
    }

    public void setResource(List<String> resource) {
        this.resource = resource;
    }
}