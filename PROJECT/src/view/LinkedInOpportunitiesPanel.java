package view;

import model.LinkedInOpportunity;
import util.LinkedInOpportunityService;
import model.Employee;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel displaying top 10 LinkedIn profile optimization opportunities.
 */
public class LinkedInOpportunitiesPanel extends JPanel {
    public JButton btnBack;
    private JList<LinkedInOpportunity> opportunityList;
    private DefaultListModel<LinkedInOpportunity> listModel;
    private JTextArea detailArea;
    
    public LinkedInOpportunitiesPanel(ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 242, 247)); // Light background
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        
        // Main card panel
        JPanel card = new RoundedPanel(20, new Color(255, 255, 255), new Color(70, 130, 180), 2);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        card.setPreferredSize(new Dimension(800, 600));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("🚀 Top 10 LinkedIn Profile Opportunities");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(new Color(70, 130, 180));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        JLabel subtitleLabel = new JLabel("Boost your professional presence and career opportunities");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        card.add(headerPanel, BorderLayout.NORTH);
        
        // Content panel with split layout
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Left side - Opportunities list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            "Opportunities (Click to view details)",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(70, 130, 180)
        ));
        
        listModel = new DefaultListModel<>();
        opportunityList = new JList<>(listModel);
        opportunityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        opportunityList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        opportunityList.setCellRenderer(new OpportunityListCellRenderer());
        
        // Add selection listener to show details
        opportunityList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                LinkedInOpportunity selected = opportunityList.getSelectedValue();
                if (selected != null) {
                    showOpportunityDetails(selected);
                }
            }
        });
        
        JScrollPane listScrollPane = new JScrollPane(opportunityList);
        listScrollPane.setPreferredSize(new Dimension(350, 400));
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        
        // Right side - Details panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            "Opportunity Details & Action Items",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(70, 130, 180)
        ));
        
        detailArea = new JTextArea();
        detailArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
        detailArea.setEditable(false);
        detailArea.setBackground(new Color(248, 249, 250));
        detailArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detailArea.setText("Select an opportunity from the list to view detailed information and action items.");
        
        JScrollPane detailScrollPane = new JScrollPane(detailArea);
        detailScrollPane.setPreferredSize(new Dimension(350, 400));
        rightPanel.add(detailScrollPane, BorderLayout.CENTER);
        
        // Split pane for resizable layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setOpaque(false);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        // Bottom panel with back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        btnBack = new JButton("🔙 Back to Dashboard");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(70, 130, 180));
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(listener);
        
        bottomPanel.add(btnBack);
        card.add(bottomPanel, BorderLayout.SOUTH);
        
        add(card, gbc);
    }
    
    /**
     * Load opportunities for the given employee.
     */
    public void loadOpportunities(Employee employee) {
        listModel.clear();
        List<LinkedInOpportunity> opportunities = LinkedInOpportunityService.generateOpportunities(employee);
        for (LinkedInOpportunity opportunity : opportunities) {
            listModel.addElement(opportunity);
        }
        
        // Auto-select first item if available
        if (!opportunities.isEmpty()) {
            opportunityList.setSelectedIndex(0);
        }
    }
    
    /**
     * Show details for the selected opportunity.
     */
    private void showOpportunityDetails(LinkedInOpportunity opportunity) {
        StringBuilder details = new StringBuilder();
        details.append("🎯 ").append(opportunity.getTitle()).append("\n\n");
        details.append("📝 Description:\n").append(opportunity.getDescription()).append("\n\n");
        details.append("📂 Category: ").append(opportunity.getCategory()).append("\n\n");
        details.append("⭐ Priority: ").append(opportunity.getPriority()).append(" out of 10\n\n");
        details.append("✅ Action Item:\n").append(opportunity.getActionItem());
        
        detailArea.setText(details.toString());
        detailArea.setCaretPosition(0); // Scroll to top
    }
    
    /**
     * Custom list cell renderer for opportunities.
     */
    private static class OpportunityListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            if (value instanceof LinkedInOpportunity) {
                LinkedInOpportunity opportunity = (LinkedInOpportunity) value;
                setText("<html><b>" + (index + 1) + ". " + opportunity.getTitle() + "</b><br>" +
                       "<small style='color: #666;'>" + opportunity.getCategory() + "</small></html>");
                setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
            }
            
            if (isSelected) {
                setBackground(new Color(70, 130, 180, 50));
                setForeground(new Color(70, 130, 180));
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            
            return this;
        }
    }
    
    /**
     * Rounded panel for visual appeal.
     */
    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;
        private final Color borderColor;
        private final int borderWidth;
        
        public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth) {
            this.radius = radius;
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
            setOpaque(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            
            // Background
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, w, h, radius, radius);
            
            // Border
            if (borderWidth > 0) {
                g2.setStroke(new BasicStroke(borderWidth));
                g2.setColor(borderColor);
                g2.drawRoundRect(borderWidth/2, borderWidth/2, w-borderWidth, h-borderWidth, radius, radius);
            }
            
            g2.dispose();
            super.paintComponent(g);
        }
    }
}