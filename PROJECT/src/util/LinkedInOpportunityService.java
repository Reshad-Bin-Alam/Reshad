package util;

import model.Employee;
import model.LinkedInOpportunity;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class to generate LinkedIn profile optimization opportunities.
 */
public class LinkedInOpportunityService {
    
    /**
     * Generates top 10 LinkedIn opportunities based on employee position and profile.
     */
    public static List<LinkedInOpportunity> generateOpportunities(Employee employee) {
        List<LinkedInOpportunity> opportunities = new ArrayList<>();
        String position = employee.getPosition().toLowerCase();
        
        // Universal opportunities for all employees
        opportunities.add(new LinkedInOpportunity(
            "Complete Your Professional Headline",
            "Craft a compelling headline that showcases your expertise and value proposition",
            "Profile Optimization", 1,
            "Write a headline beyond just job title - include key skills and value you bring"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Add a Professional Profile Photo",
            "Profiles with photos get 21x more profile views and 36x more messages",
            "Profile Optimization", 2,
            "Upload a high-quality headshot with good lighting and professional attire"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Write a Compelling Summary Section",
            "Your summary is prime real estate to tell your professional story",
            "Content Strategy", 3,
            "Write 3-5 paragraphs highlighting achievements, skills, and career goals"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Showcase Key Skills & Get Endorsements",
            "Skills section helps you appear in recruiter searches",
            "Skills Development", 4,
            "Add 5-10 relevant skills and ask colleagues for endorsements"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Request Strategic Recommendations",
            "Recommendations provide social proof of your capabilities",
            "Social Proof", 5,
            "Ask 2-3 former managers or colleagues for detailed recommendations"
        ));
        
        // Position-specific opportunities
        if (position.contains("developer") || position.contains("analyst")) {
            opportunities.add(new LinkedInOpportunity(
                "Highlight Technical Projects",
                "Showcase your coding projects and technical achievements",
                "Technical Portfolio", 6,
                "Add links to GitHub, portfolio sites, or describe key technical projects"
            ));
            
            opportunities.add(new LinkedInOpportunity(
                "Join Technology Communities",
                "Connect with other developers and stay updated on industry trends",
                "Networking", 7,
                "Follow tech companies, join developer groups, engage with tech content"
            ));
        } else if (position.contains("manager")) {
            opportunities.add(new LinkedInOpportunity(
                "Demonstrate Leadership Experience",
                "Highlight team management and leadership accomplishments",
                "Leadership Portfolio", 6,
                "Detail team sizes managed, projects led, and leadership methodologies used"
            ));
            
            opportunities.add(new LinkedInOpportunity(
                "Share Management Insights",
                "Establish thought leadership by sharing management tips and experiences",
                "Content Strategy", 7,
                "Post weekly insights about leadership, team building, or industry trends"
            ));
        } else if (position.contains("hr")) {
            opportunities.add(new LinkedInOpportunity(
                "Showcase HR Certifications",
                "Display your professional HR credentials and continuous learning",
                "Professional Development", 6,
                "Add HR certifications, training completed, and professional memberships"
            ));
            
            opportunities.add(new LinkedInOpportunity(
                "Share HR Best Practices",
                "Position yourself as an HR thought leader",
                "Content Strategy", 7,
                "Post about recruitment strategies, employee engagement, or HR trends"
            ));
        } else {
            opportunities.add(new LinkedInOpportunity(
                "Highlight Industry Expertise",
                "Showcase your deep knowledge in your specific field",
                "Industry Knowledge", 6,
                "Share insights about industry trends, challenges, and opportunities"
            ));
            
            opportunities.add(new LinkedInOpportunity(
                "Build Industry Network",
                "Connect with professionals in your field",
                "Networking", 7,
                "Connect with industry leaders, join relevant groups, engage with content"
            ));
        }
        
        // Additional universal opportunities
        opportunities.add(new LinkedInOpportunity(
            "Optimize for Search Keywords",
            "Include relevant keywords so recruiters can find you",
            "SEO Optimization", 8,
            "Use industry keywords in headline, summary, and experience descriptions"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Engage with Your Network Regularly",
            "Stay visible by liking, commenting, and sharing relevant content",
            "Engagement Strategy", 9,
            "Spend 15 minutes daily engaging with connections' posts and industry content"
        ));
        
        opportunities.add(new LinkedInOpportunity(
            "Keep Your Profile Current",
            "Regular updates show you're active and growing professionally",
            "Profile Maintenance", 10,
            "Update your profile monthly with new achievements, skills, or experiences"
        ));
        
        return opportunities.subList(0, Math.min(10, opportunities.size()));
    }
}