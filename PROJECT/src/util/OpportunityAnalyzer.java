package util;

import model.Employee;
import java.util.*;

/**
 * Utility class to analyze employee profiles and suggest career opportunities.
 */
public class OpportunityAnalyzer {
    
    /**
     * Represents a career opportunity suggestion.
     */
    public static class Opportunity {
        private final String title;
        private final String company;
        private final String description;
        private final double salaryRange;
        private final String location;
        private final int score;
        
        public Opportunity(String title, String company, String description, 
                          double salaryRange, String location, int score) {
            this.title = title;
            this.company = company;
            this.description = description;
            this.salaryRange = salaryRange;
            this.location = location;
            this.score = score;
        }
        
        // Getters
        public String getTitle() { return title; }
        public String getCompany() { return company; }
        public String getDescription() { return description; }
        public double getSalaryRange() { return salaryRange; }
        public String getLocation() { return location; }
        public int getScore() { return score; }
    }
    
    /**
     * Analyzes an employee profile and returns top 10 career opportunities.
     */
    public static List<Opportunity> getTopOpportunities(Employee employee) {
        List<Opportunity> opportunities = new ArrayList<>();
        String currentPosition = employee.getPosition();
        double currentSalary = employee.getBaseSalary();
        
        // Generate opportunities based on current position and experience level
        switch (currentPosition.toLowerCase()) {
            case "developer":
                opportunities.addAll(generateDeveloperOpportunities(currentSalary));
                break;
            case "manager":
                opportunities.addAll(generateManagerOpportunities(currentSalary));
                break;
            case "hr":
                opportunities.addAll(generateHROpportunities(currentSalary));
                break;
            case "analyst":
                opportunities.addAll(generateAnalystOpportunities(currentSalary));
                break;
            case "qa":
                opportunities.addAll(generateQAOpportunities(currentSalary));
                break;
            case "support":
                opportunities.addAll(generateSupportOpportunities(currentSalary));
                break;
            default:
                opportunities.addAll(generateGeneralOpportunities(currentSalary));
                break;
        }
        
        // Sort by score (highest first) and return top 10
        opportunities.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return opportunities.subList(0, Math.min(10, opportunities.size()));
    }
    
    private static List<Opportunity> generateDeveloperOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior Software Engineer", "TechCorp Inc.", 
            "Lead development of scalable web applications using modern frameworks",
            currentSalary * 1.3, "Remote", 95
        ));
        
        opportunities.add(new Opportunity(
            "Full Stack Developer", "StartupHub", 
            "Build end-to-end solutions with React, Node.js, and cloud technologies",
            currentSalary * 1.2, "San Francisco", 90
        ));
        
        opportunities.add(new Opportunity(
            "DevOps Engineer", "CloudSystems Ltd", 
            "Manage CI/CD pipelines and cloud infrastructure automation",
            currentSalary * 1.4, "New York", 88
        ));
        
        opportunities.add(new Opportunity(
            "Mobile App Developer", "MobileTech", 
            "Develop native iOS and Android applications",
            currentSalary * 1.1, "Austin", 85
        ));
        
        opportunities.add(new Opportunity(
            "Data Engineer", "DataFlow Inc", 
            "Build data pipelines and analytics platforms",
            currentSalary * 1.35, "Seattle", 82
        ));
        
        opportunities.add(new Opportunity(
            "Frontend Architect", "WebSolutions", 
            "Design scalable frontend architectures and lead UI/UX development",
            currentSalary * 1.5, "Remote", 80
        ));
        
        opportunities.add(new Opportunity(
            "AI/ML Engineer", "IntelliSoft", 
            "Develop machine learning models and AI-powered applications",
            currentSalary * 1.6, "Boston", 78
        ));
        
        opportunities.add(new Opportunity(
            "Security Engineer", "SecureTech", 
            "Implement cybersecurity measures and secure coding practices",
            currentSalary * 1.3, "Washington DC", 75
        ));
        
        opportunities.add(new Opportunity(
            "Technical Lead", "Enterprise Solutions", 
            "Lead technical teams and architecture decisions",
            currentSalary * 1.4, "Chicago", 72
        ));
        
        opportunities.add(new Opportunity(
            "Game Developer", "GameStudio Pro", 
            "Create immersive gaming experiences using Unity and Unreal",
            currentSalary * 1.2, "Los Angeles", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateManagerOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior Project Manager", "Enterprise Corp", 
            "Lead large-scale digital transformation projects",
            currentSalary * 1.4, "New York", 95
        ));
        
        opportunities.add(new Opportunity(
            "Director of Operations", "GrowthCorp", 
            "Oversee operational excellence and strategic initiatives",
            currentSalary * 1.6, "San Francisco", 92
        ));
        
        opportunities.add(new Opportunity(
            "Product Manager", "InnovateTech", 
            "Drive product strategy and roadmap for B2B solutions",
            currentSalary * 1.3, "Remote", 88
        ));
        
        opportunities.add(new Opportunity(
            "Team Lead", "AgileWorks", 
            "Manage cross-functional teams in agile environment",
            currentSalary * 1.2, "Austin", 85
        ));
        
        opportunities.add(new Opportunity(
            "Business Development Manager", "SalesForce Plus", 
            "Expand market reach and drive revenue growth",
            currentSalary * 1.35, "Miami", 82
        ));
        
        opportunities.add(new Opportunity(
            "Scrum Master", "AgileTech", 
            "Facilitate agile processes and team productivity",
            currentSalary * 1.1, "Remote", 80
        ));
        
        opportunities.add(new Opportunity(
            "VP of Engineering", "TechStartup", 
            "Lead engineering organization and technical vision",
            currentSalary * 1.8, "Silicon Valley", 78
        ));
        
        opportunities.add(new Opportunity(
            "Consultant", "McKinsey & Co", 
            "Provide strategic consulting for Fortune 500 companies",
            currentSalary * 1.5, "Multiple Locations", 75
        ));
        
        opportunities.add(new Opportunity(
            "Program Manager", "Microsoft", 
            "Coordinate complex technical programs across multiple teams",
            currentSalary * 1.45, "Seattle", 72
        ));
        
        opportunities.add(new Opportunity(
            "Change Management Lead", "TransformCorp", 
            "Lead organizational change initiatives",
            currentSalary * 1.25, "Chicago", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateHROpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior HR Business Partner", "Global Corp", 
            "Strategic HR support for business units and leadership",
            currentSalary * 1.4, "New York", 95
        ));
        
        opportunities.add(new Opportunity(
            "Talent Acquisition Manager", "TechRecruiter Pro", 
            "Lead recruitment strategies for tech talent",
            currentSalary * 1.3, "San Francisco", 90
        ));
        
        opportunities.add(new Opportunity(
            "HR Director", "Growth Industries", 
            "Oversee all HR functions and people strategy",
            currentSalary * 1.6, "Remote", 88
        ));
        
        opportunities.add(new Opportunity(
            "Compensation & Benefits Specialist", "RewardSystems", 
            "Design competitive compensation packages",
            currentSalary * 1.2, "Chicago", 85
        ));
        
        opportunities.add(new Opportunity(
            "People Operations Manager", "StartupSpace", 
            "Build and scale people operations for growth",
            currentSalary * 1.35, "Austin", 82
        ));
        
        opportunities.add(new Opportunity(
            "Learning & Development Lead", "SkillBuilder Inc", 
            "Create employee development and training programs",
            currentSalary * 1.25, "Remote", 80
        ));
        
        opportunities.add(new Opportunity(
            "Employee Relations Specialist", "WellnessCorp", 
            "Manage employee relations and workplace culture",
            currentSalary * 1.15, "Denver", 78
        ));
        
        opportunities.add(new Opportunity(
            "Chief People Officer", "ScaleUp Co", 
            "Lead people strategy for rapidly growing company",
            currentSalary * 1.8, "San Francisco", 75
        ));
        
        opportunities.add(new Opportunity(
            "HR Analytics Manager", "DataHR Solutions", 
            "Use data analytics to drive HR insights",
            currentSalary * 1.4, "Remote", 72
        ));
        
        opportunities.add(new Opportunity(
            "Diversity & Inclusion Manager", "InclusiveTech", 
            "Lead D&I initiatives and create inclusive workplace",
            currentSalary * 1.3, "Multiple Locations", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateAnalystOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior Data Analyst", "DataDriven Corp", 
            "Lead data analysis projects and business intelligence",
            currentSalary * 1.4, "Remote", 95
        ));
        
        opportunities.add(new Opportunity(
            "Business Intelligence Analyst", "InsightTech", 
            "Create dashboards and analytical reports for executives",
            currentSalary * 1.3, "New York", 90
        ));
        
        opportunities.add(new Opportunity(
            "Financial Analyst", "Investment Group", 
            "Analyze financial data and investment opportunities",
            currentSalary * 1.35, "Wall Street", 88
        ));
        
        opportunities.add(new Opportunity(
            "Market Research Analyst", "TrendWatch", 
            "Research market trends and consumer behavior",
            currentSalary * 1.2, "Chicago", 85
        ));
        
        opportunities.add(new Opportunity(
            "Data Scientist", "AI Analytics", 
            "Apply machine learning to solve business problems",
            currentSalary * 1.5, "San Francisco", 82
        ));
        
        opportunities.add(new Opportunity(
            "Operations Analyst", "Efficiency Plus", 
            "Optimize business processes and operations",
            currentSalary * 1.25, "Remote", 80
        ));
        
        opportunities.add(new Opportunity(
            "Product Analyst", "ProductTech", 
            "Analyze product performance and user behavior",
            currentSalary * 1.3, "Seattle", 78
        ));
        
        opportunities.add(new Opportunity(
            "Risk Analyst", "SecureBank", 
            "Assess and manage financial and operational risks",
            currentSalary * 1.4, "Boston", 75
        ));
        
        opportunities.add(new Opportunity(
            "Research Analyst", "Think Tank Pro", 
            "Conduct research and analysis for strategic decisions",
            currentSalary * 1.2, "Washington DC", 72
        ));
        
        opportunities.add(new Opportunity(
            "Quality Analyst", "ProcessExcellence", 
            "Ensure quality standards and process improvements",
            currentSalary * 1.15, "Austin", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateQAOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior QA Engineer", "QualityFirst Tech", 
            "Lead quality assurance processes and automation",
            currentSalary * 1.4, "Remote", 95
        ));
        
        opportunities.add(new Opportunity(
            "Test Automation Engineer", "AutoTest Pro", 
            "Develop automated testing frameworks and tools",
            currentSalary * 1.35, "San Francisco", 90
        ));
        
        opportunities.add(new Opportunity(
            "QA Manager", "TestSystems Inc", 
            "Manage QA teams and testing strategies",
            currentSalary * 1.5, "New York", 88
        ));
        
        opportunities.add(new Opportunity(
            "Performance Test Engineer", "LoadTest Solutions", 
            "Conduct performance and load testing",
            currentSalary * 1.3, "Remote", 85
        ));
        
        opportunities.add(new Opportunity(
            "Mobile QA Specialist", "AppQuality", 
            "Specialize in mobile app testing across platforms",
            currentSalary * 1.25, "Austin", 82
        ));
        
        opportunities.add(new Opportunity(
            "Security QA Engineer", "SecureTest", 
            "Focus on security testing and vulnerability assessment",
            currentSalary * 1.4, "Washington DC", 80
        ));
        
        opportunities.add(new Opportunity(
            "API Test Engineer", "APITesting Pro", 
            "Specialize in API testing and backend validation",
            currentSalary * 1.2, "Remote", 78
        ));
        
        opportunities.add(new Opportunity(
            "Quality Assurance Lead", "QualityWorks", 
            "Lead QA initiatives across multiple projects",
            currentSalary * 1.45, "Chicago", 75
        ));
        
        opportunities.add(new Opportunity(
            "Game QA Tester", "GameStudio", 
            "Test gaming applications and user experience",
            currentSalary * 1.1, "Los Angeles", 72
        ));
        
        opportunities.add(new Opportunity(
            "DevOps QA Engineer", "CloudQuality", 
            "Integrate QA processes into DevOps pipelines",
            currentSalary * 1.35, "Seattle", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateSupportOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Senior Technical Support Engineer", "TechSupport Plus", 
            "Provide advanced technical support and mentoring",
            currentSalary * 1.4, "Remote", 95
        ));
        
        opportunities.add(new Opportunity(
            "Customer Success Manager", "ClientFirst Corp", 
            "Ensure customer satisfaction and retention",
            currentSalary * 1.5, "San Francisco", 90
        ));
        
        opportunities.add(new Opportunity(
            "Support Team Lead", "HelpDesk Pro", 
            "Lead support teams and improve processes",
            currentSalary * 1.35, "Remote", 88
        ));
        
        opportunities.add(new Opportunity(
            "Technical Account Manager", "Enterprise Support", 
            "Manage technical relationships with key accounts",
            currentSalary * 1.6, "New York", 85
        ));
        
        opportunities.add(new Opportunity(
            "Product Support Specialist", "SoftwareSupport Inc", 
            "Provide specialized product support and training",
            currentSalary * 1.25, "Austin", 82
        ));
        
        opportunities.add(new Opportunity(
            "Support Operations Manager", "OpSupport", 
            "Optimize support operations and metrics",
            currentSalary * 1.4, "Chicago", 80
        ));
        
        opportunities.add(new Opportunity(
            "Field Support Engineer", "OnSite Solutions", 
            "Provide on-site technical support and installation",
            currentSalary * 1.3, "Multiple Locations", 78
        ));
        
        opportunities.add(new Opportunity(
            "Support Documentation Lead", "KnowledgeBase Pro", 
            "Create and maintain support documentation",
            currentSalary * 1.2, "Remote", 75
        ));
        
        opportunities.add(new Opportunity(
            "Escalation Specialist", "AdvancedSupport", 
            "Handle complex technical escalations",
            currentSalary * 1.35, "Seattle", 72
        ));
        
        opportunities.add(new Opportunity(
            "Support Training Manager", "SkillSupport", 
            "Develop training programs for support teams",
            currentSalary * 1.3, "Denver", 70
        ));
        
        return opportunities;
    }
    
    private static List<Opportunity> generateGeneralOpportunities(double currentSalary) {
        List<Opportunity> opportunities = new ArrayList<>();
        
        opportunities.add(new Opportunity(
            "Business Analyst", "Consulting Group", 
            "Analyze business processes and recommend improvements",
            currentSalary * 1.3, "Remote", 85
        ));
        
        opportunities.add(new Opportunity(
            "Project Coordinator", "ProjectWorks", 
            "Coordinate projects and facilitate team collaboration",
            currentSalary * 1.2, "Various Locations", 80
        ));
        
        opportunities.add(new Opportunity(
            "Sales Representative", "GrowthSales", 
            "Build client relationships and drive revenue",
            currentSalary * 1.4, "Multiple Cities", 78
        ));
        
        opportunities.add(new Opportunity(
            "Digital Marketing Specialist", "MarketTech", 
            "Develop and execute digital marketing campaigns",
            currentSalary * 1.25, "Remote", 75
        ));
        
        opportunities.add(new Opportunity(
            "Operations Coordinator", "EfficiencyFirst", 
            "Streamline operations and improve workflows",
            currentSalary * 1.15, "Regional Office", 72
        ));
        
        opportunities.add(new Opportunity(
            "Account Manager", "ClientCare Inc", 
            "Manage client accounts and ensure satisfaction",
            currentSalary * 1.35, "Major Cities", 70
        ));
        
        opportunities.add(new Opportunity(
            "Training Specialist", "LearnTech", 
            "Develop and deliver professional training programs",
            currentSalary * 1.2, "Remote", 68
        ));
        
        opportunities.add(new Opportunity(
            "Content Creator", "MediaWorks", 
            "Create engaging content for digital platforms",
            currentSalary * 1.1, "Remote", 65
        ));
        
        opportunities.add(new Opportunity(
            "Administrative Manager", "AdminPro", 
            "Oversee administrative functions and office operations",
            currentSalary * 1.25, "Local Office", 62
        ));
        
        opportunities.add(new Opportunity(
            "Customer Service Lead", "ServiceExcellence", 
            "Lead customer service teams and improve satisfaction",
            currentSalary * 1.2, "Call Center", 60
        ));
        
        return opportunities;
    }
}