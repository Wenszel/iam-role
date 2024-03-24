package org.example.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.example.AlwaysListTypeAdapterFactory;

import java.util.List;
public class PolicyDocument {

    @SerializedName("Version")
    public String version;

    @SerializedName("Statement")
    @JsonAdapter(AlwaysListTypeAdapterFactory.class)
    public List<Statement> statement;

    @Override
    public String toString() {
        return "PolicyDocument{" +
                "version='" + version + '\'' +
                ", statement=" + statement +
                '}';
    }

    public boolean containsRequiredFields() {
        return version != null
                && statement != null
                && !statement.isEmpty();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Statement> getStatement() {
        return statement;
    }

    public void setStatement(List<Statement> statement) {
        this.statement = statement;
    }
}