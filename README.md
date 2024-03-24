# IAM Role Task 
## Approach 
1. Reading file and checking if it is a JSON with **JSON library**
2. Parsing JSON to Java classes with **GSON library**
3. Verifying if the role has an asterisk in any resource field

## Covered edge cases
1. [JSONReader](https://github.com/Wenszel/iam-role-parser/blob/main/src/main/java/org/example/JSONReader.java)
- invalid JSON format(not closed brackets, not closed quotes etc.)
 utilising JSON library 
2. [IAMRoleParser](https://github.com/Wenszel/iam-role-parser/blob/main/src/main/java/org/example/IAMRoleParser.java)
- Handling the situation when a field can be either an object or an array with the annotation @JsonAdapter(AlwaysListTypeAdapterFactory.class)

- Checking if JSON contains all **required** fields
- PolicyName with pattern **"[\w+=,.@-]+"** and with length **1-128**
- Correct "PolicyDocument -> **Version**": https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies_elements_version.html
- Statement -> **Effect** has to be "Allow" or "Deny"

3. [IAMRoleVerifier](https://github.com/Wenszel/iam-role-parser/blob/main/src/main/java/org/example/IAMRoleVerifier.java)
- Checks if the role has an asterisk in any resource field

## Tests
- [**Integration tests**](https://github.com/Wenszel/iam-role-parser/blob/main/src/test/java/integration/IAMRoleIntegrationTests.java)
- [JSONReader tests](https://github.com/Wenszel/iam-role-parser/blob/main/src/test/java/JSONReaderTests.java)
- [IAMRoleParser tests](https://github.com/Wenszel/iam-role-parser/blob/main/src/test/java/IAMRoleParserTests.java)
- [IAMRoleVerifier tests](https://github.com/Wenszel/iam-role-parser/blob/main/src/test/java/IAMRoleVerifierTests.java)

## How to run

### IntelliJ IDEA

1. Make sure you have installed **Java 17** or above
2. Clone the repository
3. Open the project in IntelliJ IDEA
4. Run the tests / Run main file 

### Command line 
Make sure you have installed **Java 17** or above and [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)

``` 
git clone https://github.com/Wenszel/iam-role
cd iam-role
mvn package
mvn exec:java -Dexec.mainClass="org.example.Main"  
```

## How to use
When you run the program, you will be asked for preferred file loading option:
1. Load file from resources -> predefined JSON files in resources folder
2. Load file from path -> provide the path to the file
### Example usage:
```
Choose data loading mode:
1. Resources (predefined JSON files in the resources directory)
2. File path (provide the path to the JSON file)
>>>>>>> Your choice: 1
Enter the file path: e.g. json/correct_asterisk.json
>>>>>>> json/correct_asterisk.json
Output: false

```