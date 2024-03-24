package model;

import org.example.model.PolicyDocument;
import org.example.model.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolicyDocumentTests {
    @Test
    public void givenPolicyDocumentWithoutVersion_whenContainsRequiredFields_thenShouldReturnFalse() {
        PolicyDocument policyDocument = new PolicyDocument();
        assertFalse(policyDocument.containsRequiredFields());
    }

    @Test
    public void givenPolicyDocumentWithoutStatement_whenContainsRequiredFields_thenShouldReturnFalse() {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setVersion("2012-10-17");
        assertFalse(policyDocument.containsRequiredFields());
    }

    @Test
    public void givenPolicyDocumentWithEmptyStatementList_whenContainsRequiredFields_thenShouldReturnFalse() {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setVersion("2012-10-17");
        policyDocument.setStatement(new ArrayList<>());
        assertFalse(policyDocument.containsRequiredFields());
    }

    @Test
    public void givenPolicyDocumentWithVersionAndStatement_whenContainsRequiredFields_thenShouldReturnTrue() {
        PolicyDocument policyDocument = new PolicyDocument();
        policyDocument.setVersion("2012-10-17");
        List<Statement> statements = new ArrayList<>();
        Statement statement = new Statement();
        statements.add(statement);
        policyDocument.setStatement(statements);
        assertTrue(policyDocument.containsRequiredFields());
    }
}