package test;

import model.Employee;
import model.LinkedInOpportunity;
import util.LinkedInOpportunityService;
import java.util.List;

/**
 * Simple test to validate LinkedIn opportunities functionality.
 */
public class LinkedInOpportunityTest {
    public static void main(String[] args) {
        System.out.println("=== LinkedIn Opportunities Test ===\n");
        
        // Test with different employee positions
        testPosition("Developer");
        testPosition("Manager");
        testPosition("HR");
        testPosition("Analyst");
    }
    
    private static void testPosition(String position) {
        System.out.println("Position: " + position);
        System.out.println("=" + "=".repeat(position.length() + 10));
        
        // Create a test employee
        Employee testEmployee = new Employee("TEST001", "pass123", "Test User", 
                                           "test@company.com", position, 50000, 5.0, 15.0, 1000, 5);
        
        // Generate opportunities
        List<LinkedInOpportunity> opportunities = LinkedInOpportunityService.generateOpportunities(testEmployee);
        
        // Display opportunities
        for (int i = 0; i < opportunities.size(); i++) {
            LinkedInOpportunity opp = opportunities.get(i);
            System.out.printf("%d. %s (%s) - Priority: %d\n", 
                             i + 1, opp.getTitle(), opp.getCategory(), opp.getPriority());
            System.out.println("   Action: " + opp.getActionItem().substring(0, Math.min(80, opp.getActionItem().length())) + "...");
        }
        
        System.out.println("\nTotal opportunities: " + opportunities.size());
        System.out.println("\n" + "-".repeat(50) + "\n");
    }
}