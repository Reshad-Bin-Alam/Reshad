package view;

import util.OpportunityAnalyzer;
import model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Modern panel to display career opportunities for LinkedIn profile optimization.
 */
public class CareerOpportunitiesPanel extends JPanel {
    public JButton btnBack;
    private DefaultTableModel tableModel;
    private JTable opportunitiesTable;
    private JLabel titleLabel;
    private Employee employee;

    public CareerOpportunitiesPanel(Employee employee, ActionListener listener) {
        this.employee = employee;
        setLayout(new GridBagLayout());
        setBackground(new Color(236, 239, 244)); // Modern soft background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Use dark accent color (matches existing UI)
        Color accent = new Color(60, 72, 88);

        JPanel card = new RoundedPanel(24, new Color(255, 255, 255), accent, 2, true);
        card.setLayout(new BorderLayout(0, 18));
        card.setBorder(BorderFactory.createEmptyBorder(32, 40, 32, 40));

        // Title
        titleLabel = new JLabel("Top 10 LinkedIn Career Opportunities for " + employee.getName(), JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(accent);
        card.add(titleLabel, BorderLayout.NORTH);

        // Create table for opportunities
        String[] columnNames = {"#", "Job Title", "Company", "Salary Range", "Location", "Score"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        opportunitiesTable = new JTable(tableModel);
        opportunitiesTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        opportunitiesTable.setRowHeight(25);
        opportunitiesTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        opportunitiesTable.getTableHeader().setBackground(new Color(245, 247, 250));
        opportunitiesTable.setSelectionBackground(new Color(218, 235, 255));
        opportunitiesTable.setGridColor(new Color(230, 230, 230));

        // Set column widths
        opportunitiesTable.getColumnModel().getColumn(0).setPreferredWidth(40);  // #
        opportunitiesTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Job Title
        opportunitiesTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Company
        opportunitiesTable.getColumnModel().getColumn(3).setPreferredWidth(120); // Salary Range
        opportunitiesTable.getColumnModel().getColumn(4).setPreferredWidth(120); // Location
        opportunitiesTable.getColumnModel().getColumn(5).setPreferredWidth(80);  // Score

        JScrollPane scrollPane = new JScrollPane(opportunitiesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(800, 400));
        card.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel with description and back button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        // Description
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("These opportunities are personalized based on your current role (" + 
                               employee.getPosition() + ") and experience level. " +
                               "Higher scores indicate better matches for career growth. " +
                               "Click on any opportunity to see detailed requirements and apply on LinkedIn.");
        descriptionArea.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        descriptionArea.setForeground(new Color(120, 130, 170));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.add(descriptionArea, BorderLayout.CENTER);

        // Back button
        btnBack = createButton("Back to Dashboard", listener, new Color(186, 191, 208));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnBack);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        card.add(bottomPanel, BorderLayout.SOUTH);

        add(card, gbc);

        // Load opportunities
        loadOpportunities();

        card.setMinimumSize(new Dimension(900, 600));
        card.setPreferredSize(new Dimension(1100, 700));
    }

    private void loadOpportunities() {
        List<OpportunityAnalyzer.Opportunity> opportunities = 
            OpportunityAnalyzer.getTopOpportunities(employee);

        tableModel.setRowCount(0); // Clear existing data

        for (int i = 0; i < opportunities.size(); i++) {
            OpportunityAnalyzer.Opportunity opp = opportunities.get(i);
            tableModel.addRow(new Object[]{
                i + 1,
                opp.getTitle(),
                opp.getCompany(),
                "$" + String.format("%.0f", opp.getSalaryRange()),
                opp.getLocation(),
                opp.getScore() + "/100"
            });
        }
    }

    private JButton createButton(String text, ActionListener listener, Color primary) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 18;
                if (getModel().isPressed()) {
                    g2.setColor(primary.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(primary.brighter());
                } else {
                    g2.setColor(primary);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(255, 255, 255, 50)); // Soft highlight
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() / 2, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(180, 42));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        return btn;
    }

    // Rounded panel with drop shadow (matches existing UI style)
    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;
        private final Color borderColor;
        private final int borderWidth;
        private final boolean shadow;

        public RoundedPanel(int radius, Color bgColor, Color borderColor, int borderWidth, boolean shadow) {
            this.radius = radius;
            this.bgColor = bgColor;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
            this.shadow = shadow;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            if (shadow) {
                g2.setColor(new Color(60, 72, 88, 40)); // dark accent shadow
                g2.fillRoundRect(8, 8, w - 16, h - 16, radius, radius);
            }
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, w, h, radius, radius);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(borderColor);
            g2.drawRoundRect(borderWidth/2, borderWidth/2, w-borderWidth, h-borderWidth, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}