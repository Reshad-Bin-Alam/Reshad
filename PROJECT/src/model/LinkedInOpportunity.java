package model;

/**
 * Represents a LinkedIn profile opportunity/recommendation.
 */
public class LinkedInOpportunity {
    private String title;
    private String description;
    private String category;
    private int priority; // 1-10, 1 being highest priority
    private String actionItem;

    public LinkedInOpportunity(String title, String description, String category, int priority, String actionItem) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.actionItem = actionItem;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public int getPriority() { return priority; }
    public String getActionItem() { return actionItem; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(String category) { this.category = category; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setActionItem(String actionItem) { this.actionItem = actionItem; }

    @Override
    public String toString() {
        return title + " (" + category + ")";
    }
}