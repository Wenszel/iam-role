package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.IAMRole;
import org.example.model.Statement;

public class IAMRoleParser {

    public IAMRole parse(String json) throws IllegalArgumentException {
        IAMRole iamRole = new Gson().fromJson(json, IAMRole.class);
        if (iamRole == null || !containsAllRequiredFields(iamRole)) {
            throw new IllegalArgumentException("Invalid JSON");
        }
        if (!policyNameMatchesPattern(iamRole.getPolicyName())) {
            throw new IllegalArgumentException("Invalid policy name");
        }
        if (!policyNameMatchesLength(iamRole.getPolicyName())) {
            throw new IllegalArgumentException("Policy name length should be between 1 and 128 characters");
        }
        if (!isVersionSupported(iamRole.getPolicyDocument().getVersion())) {
            throw new IllegalArgumentException("Invalid version");
        }
        if (!iamRole
                .getPolicyDocument()
                .getStatement()
                .stream()
                .allMatch(statement -> isEffectAllowOrDeny(statement.getEffect()))) {
            throw new IllegalArgumentException("Invalid effect");
        }
        return iamRole;
    }

    private boolean containsAllRequiredFields(IAMRole iamRole) {
        return iamRole.containsRequiredFields()
                && iamRole.getPolicyDocument().containsRequiredFields()
                && iamRole.getPolicyDocument().getStatement().stream().allMatch(Statement::containsRequiredFields);
    }

    private boolean policyNameMatchesPattern(String policyName) {
        return policyName.matches("[\\w+=,.@-]+");
    }

    private boolean policyNameMatchesLength(String policyName) {
        return !policyName.isEmpty() && policyName.length() <= 128;
    }

    private boolean isVersionSupported(String version) {
        return version.equals("2012-10-17") || version.equals("2008-10-17");
    }

    private boolean isEffectAllowOrDeny(String effect) {
        return effect.equals("Allow") || effect.equals("Deny");
    }
}
