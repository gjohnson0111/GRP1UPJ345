import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// Run tests

public class JunitTestSuiteRunner {
    
    public static void main(String [] a) {
        
        Result result = JUnitCore.runClasses(JUnitTestSuite.class);
        for(Failure fail:result.getFailures()) {
            System.out.println(fail.toString());
        }
        
        if(result.wasSuccessful()) {
            System.out.println("All tests finished successfully...");
        }
        
        
    }

}