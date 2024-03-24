package model;

import org.example.model.Statement;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatementTests {
    @Test
    public void givenStatementWithoutEffect_whenContainsRequiredFields_thenReturnsFalse() {
        Statement statement = new Statement();
        statement.setAction(Collections.singletonList("iam:ListRoles"));
        statement.setResource(Collections.singletonList("*"));
        assertFalse(statement.containsRequiredFields());
    }

    @Test
    public void givenStatementWithoutAction_whenContainsRequiredFields_thenReturnsFalse() {
        Statement statement = new Statement();
        statement.setEffect("Allow");
        statement.setResource(Collections.singletonList("*"));
        assertFalse(statement.containsRequiredFields());
    }

    @Test
    public void givenStatementWithEmptyActionList_whenContainsRequiredFields_thenReturnsFalse() {
        Statement statement = new Statement();
        statement.setEffect("Allow");
        statement.setAction(Collections.emptyList());
        statement.setResource(Collections.singletonList("*"));
        assertFalse(statement.containsRequiredFields());
    }

    @Test
    public void givenStatementWithAllRequiredFieldsSet_whenContainsRequiredFields_thenReturnsTrue() {
        Statement statement = new Statement();
        statement.setEffect("Allow");
        statement.setAction(Arrays.asList("iam:ListRoles", "iam:ListUsers"));
        statement.setResource(Collections.singletonList("*"));
        assertTrue(statement.containsRequiredFields());
    }
}
