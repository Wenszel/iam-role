import org.example.IAMRoleVerifier;
import org.example.model.IAMRole;
import org.example.model.PolicyDocument;
import org.example.model.Statement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IAMRoleVerifierTests {

    @Test
    public void givenIAMRoleWithoutAsterisk_whenVerify_thenReturnsTrue() {
        Statement statement = new Statement();
        statement.setResource(Collections.singletonList("some:resource"));
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setStatement(Collections.singletonList(statement));
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyDocument(policyDocument);

        IAMRoleVerifier verifier = new IAMRoleVerifier();
        boolean result = verifier.verify(iamRole);

        assertTrue(result);
    }

    @Test
    public void givenIAMRoleWithAsterisk_whenVerify_thenReturnsFalse() {
        Statement statement = new Statement();
        statement.setResource(Collections.singletonList("*"));
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setStatement(Collections.singletonList(statement));
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyDocument(policyDocument);

        IAMRoleVerifier verifier = new IAMRoleVerifier();
        boolean result = verifier.verify(iamRole);

        assertFalse(result);
    }

    @Test
    public void givenIAMRoleWithMultipleStatementsAndOneWithAsterisk_whenVerify_thenReturnsFalse() {
        Statement statement1 = new Statement();
        statement1.setResource(Collections.singletonList("some:resource"));
        Statement statement2 = new Statement();
        statement2.setResource(Collections.singletonList("*"));
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setStatement(Arrays.asList(statement1, statement2));
        IAMRole iamRole = new IAMRole();
        iamRole.setPolicyDocument(policyDocument);

        IAMRoleVerifier verifier = new IAMRoleVerifier();
        boolean result = verifier.verify(iamRole);

        assertFalse(result);
    }
}
