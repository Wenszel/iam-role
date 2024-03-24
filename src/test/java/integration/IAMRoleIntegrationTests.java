package integration;

import org.example.IAMRoleParser;
import org.example.IAMRoleVerifier;
import org.example.JSONReader;
import org.example.JSONResourceReader;
import org.example.model.IAMRole;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IAMRoleIntegrationTests {
    private final static JSONReader jsonReader = new JSONResourceReader();
    private final static IAMRoleParser iamRoleParser = new IAMRoleParser();
    private final static IAMRoleVerifier iamRoleVerifier = new IAMRoleVerifier();
    @Test
    public void givenIAMRoleWithAsterisk_whenVerify_thenReturnsFalse() {
        String json = jsonReader.readJSON("json/correct_asterisk.json");
        IAMRole iamRole = iamRoleParser.parse(json);
        assertFalse(iamRoleVerifier.verify(iamRole));
    }

    @Test
    public void givenIAMRoleWithNoAsterisk_whenVerify_thenReturnsTrue() {
        String json = jsonReader.readJSON("json/correct_no_asterisk.json");
        IAMRole iamRole = iamRoleParser.parse(json);
        assertTrue(iamRoleVerifier.verify(iamRole));
    }

    @Test
    public void givenIAMRoleWithMultipleStatementsWithAsterisk_whenVerify_thenReturnsFalse() {
        String json = jsonReader.readJSON("json/correct_multiple_statements_asterisk.json");
        IAMRole iamRole = iamRoleParser.parse(json);
        assertFalse(iamRoleVerifier.verify(iamRole));
    }

    @Test
    public void givenIncorrectIAMRoleFormat_whenParse_thenThrowsException() {
        String json = jsonReader.readJSON("json/incorrect_iamrole_format.json");
        assertThrows(IllegalArgumentException.class, () -> iamRoleParser.parse(json));
    }

    @Test
    public void givenIncorrectJSON_whenReadJSON_thenThrowsException() {
        assertThrows(JSONException.class, () -> jsonReader.readJSON("json/incorrect_json.json"));
    }
}
