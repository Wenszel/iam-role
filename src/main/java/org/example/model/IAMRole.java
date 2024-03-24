package org.example.model;

import com.google.gson.annotations.SerializedName;

public class IAMRole {
    @SerializedName("PolicyName")
    public String policyName;
    @SerializedName("PolicyDocument")
    public PolicyDocument policyDocument;

    @Override
    public String toString() {
        return "IAMRole{" +
                "policyName='" + policyName + '\'' +
                ", policyDocument=" + policyDocument +
                '}';
    }

    public boolean containsRequiredFields() {
        return policyName != null && policyDocument != null;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public PolicyDocument getPolicyDocument() {
        return policyDocument;
    }

    public void setPolicyDocument(PolicyDocument policyDocument) {
        this.policyDocument = policyDocument;
    }
}