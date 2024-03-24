import org.example.IAMRoleParser;
import org.example.model.IAMRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IAMRoleParserTests {

    @Test
    public void givenCorrectJSON_whenParse_shouldNotReturnNull() {
        String validJson = """
                {
                  "PolicyName": "root",
                  "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": [
                      {
                        "Sid": "IamListAccess",
                        "Effect": "Allow",
                        "Action": [
                          "iam:ListRoles",
                          "iam:ListUsers"
                        ],
                        "Resource": "*"
                      }
                    ]
                  }
                }

                """;
        IAMRoleParser iamRoleParser = new IAMRoleParser();
        IAMRole iamRole = iamRoleParser.parse(validJson);
        assertNotNull(iamRole);
    }

    @Test
    public void givenCorrectJSONWithStatementAsObject_whenParse_shouldNotReturnNull() {
        String validJson = """
                {
                  "PolicyName": "root",
                  "PolicyDocument": {
                    "Version": "2012-10-17",
                    "Statement": {
                        "Sid": "IamListAccess",
                        "Effect": "Allow",
                        "Action": [
                          "iam:ListRoles",
                          "iam:ListUsers"
                        ],
                        "Resource": "*"
                      }
                  }
                }

                """;
        IAMRoleParser iamRoleParser = new IAMRoleParser();
        IAMRole iamRole = iamRoleParser.parse(validJson);
        assertNotNull(iamRole);
    }

    @Test
    public void givenJSONWithoutRequiredFields_whenParse_shouldThrowsException() {
        String invalidJson = "{\"policyName\": \"example_policy\"}";
        assertThrows(IllegalArgumentException.class, () -> {
            IAMRoleParser iamRoleParser = new IAMRoleParser();
            iamRoleParser.parse(invalidJson);
        });
    }

    @Test
    public void givenInvalidPolicyName_whenParse_shouldThrowsException() {
        String invalidJson = "{\"policyName\": \"invalid_policy_@\"}";
        assertThrows(IllegalArgumentException.class, () -> {
            IAMRoleParser iamRoleParser = new IAMRoleParser();
            iamRoleParser.parse(invalidJson);
        });
    }

    @Test
    public void givenTooLongName_whenParse_shouldThrowsException() {
        String invalidJson = String.format("{\"policyName\": %s\"\"}", generateTooLongPolicyName());
        assertThrows(IllegalArgumentException.class, () -> {
            IAMRoleParser iamRoleParser = new IAMRoleParser();
            iamRoleParser.parse(invalidJson);
        });
    }

    private String generateTooLongPolicyName() {
        return "a".repeat(129);
    }

    @Test
    public void givenInvalidVersion_whenParse_shouldThrowsException() {
        String invalidJson = "{\"policyName\": \"example_policy\", \"policyDocument\": {\"version\": \"2000-01-01\"}}";
        assertThrows(IllegalArgumentException.class, () -> {
            IAMRoleParser iamRoleParser = new IAMRoleParser();
            iamRoleParser.parse(invalidJson);
        });
    }
}
