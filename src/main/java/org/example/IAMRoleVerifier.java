package org.example;

import org.example.model.IAMRole;

public class IAMRoleVerifier {

    public boolean verify(IAMRole iamRole) {
        return !containsAsterisk(iamRole);
    }

    private boolean containsAsterisk(IAMRole iamRole) {
        return iamRole
                .getPolicyDocument()
                .getStatement()
                .stream()
                .anyMatch(
                        statement -> statement
                                .getResource()
                                .stream()
                                .anyMatch(resource -> resource.equals("*"))
                );
    }
}
