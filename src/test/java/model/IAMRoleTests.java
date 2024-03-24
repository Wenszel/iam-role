package model;

import org.example.model.IAMRole;
import org.example.model.PolicyDocument;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IAMRoleTests {
    @Test
    public void givenIAMRoleWithRequiredFields_whenContainsRequiredFields_shouldReturnsTrue() {
        PolicyDocument policyDocument = new PolicyDocument();
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyName("examplePolicy");
        iamRole.setPolicyDocument(policyDocument);
        assertTrue(iamRole.containsRequiredFields());
    }

    @Test
    public void givenIAMRoleWithoutPolicyName_whenContainsRequiredFields_shouldReturnsFalse() {
        PolicyDocument policyDocument = new PolicyDocument();
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyDocument(policyDocument);
        assertFalse(iamRole.containsRequiredFields());
    }

    @Test
    public void givenIAMRoleWithoutPolicyDocumentFields_whenContainsRequiredFields_shouldReturnsFalse() {
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyName("examplePolicy");
        assertFalse(iamRole.containsRequiredFields());
    }
}