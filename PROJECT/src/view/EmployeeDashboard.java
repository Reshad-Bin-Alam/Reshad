package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Enhanced Modern Employee dashboard panel with more features and creative layout.
 */
public class EmployeeDashboard extends JPanel {
    public JButton btnViewPayslip, btnPayrollHistory, btnProfile, btnCareerOpportunities, btnSupport, btnLogout;
    public JLabel lblWelcome, lblRole, lblAvatar;
    public JSeparator separator;

    public EmployeeDashboard(String empName, ActionListener listener) {
        setLayout(new GridBagLayout());
        setBackground(new Color(220, 223, 228)); // Ash background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.BOTH;

        // Main Card
        JPanel card = new RoundedPanel(24, new Color(255, 255, 255), new Color(60, 72, 88), 2);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(24, 38, 24, 38));
        card.setPreferredSize(new Dimension(430, 420));

        // Top: Profile section
        JPanel profilePanel = new JPanel();
        profilePanel.setOpaque(false);
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

        // Placeholder Avatar
        lblAvatar = new JLabel(getAvatarIcon());
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePanel.add(lblAvatar);
        profilePanel.add(Box.createVerticalStrut(7));

        lblWelcome = new JLabel("Welcome, " + empName + "!", JLabel.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblWelcome.setForeground(new Color(40, 69, 160));
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePanel.add(lblWelcome);

        lblRole = new JLabel("Employee");
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblRole.setForeground(new Color(100, 120, 160));
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePanel.add(lblRole);

        card.add(profilePanel, BorderLayout.NORTH);

        // Center: Grid of feature buttons
        JPanel btnGrid = new JPanel();
        btnGrid.setOpaque(false);
        btnGrid.setLayout(new GridLayout(2, 3, 22, 18));
        btnGrid.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        btnViewPayslip = createStyledButton("Payslip", "💰", listener);
        btnPayrollHistory = createStyledButton("Payroll History", "📑", listener);
        btnProfile = createStyledButton("Profile", "👤", listener);
        btnCareerOpportunities = createStyledButton("Career Opportunities", "🚀", listener);
        btnSupport = createStyledButton("Support", "🛠️", listener);
        btnLogout = createStyledButton("Logout", "🚪", listener);

        btnGrid.add(btnViewPayslip);
        btnGrid.add(btnPayrollHistory);
        btnGrid.add(btnProfile);
        btnGrid.add(btnCareerOpportunities);
        btnGrid.add(btnSupport);
        btnGrid.add(btnLogout);

        card.add(btnGrid, BorderLayout.CENTER);

        // Bottom: Tip/message
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        JLabel tip = new JLabel("Tip: Use Career Opportunities to discover your next big break on LinkedIn!");
        tip.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        tip.setForeground(new Color(120, 130, 170));
        tip.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(tip);

        card.add(bottomPanel, BorderLayout.SOUTH);

        add(card, gbc);
    }

    /**
     * Creates a styled button with icon and label.
     */
    private JButton createStyledButton(String text, String icon, ActionListener listener) {
        JButton btn = new JButton(icon + "  " + text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int arc = 16;
                int bw = 2;
                g2.setColor(new Color(245, 248, 252));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.setColor(new Color(60, 72, 88));
                g2.setStroke(new BasicStroke(bw));
                g2.drawRoundRect(bw / 2, bw / 2, getWidth() - bw, getHeight() - bw, arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    /**
     * Returns a circular colored avatar icon.
     */
    private Icon getAvatarIcon() {
        int size = 54;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background circle
        g2.setColor(new Color(110, 130, 210));
        g2.fillOval(0, 0, size, size);

        // User initials/emoji
        g2.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        g2.setColor(Color.WHITE);
        String initials = "😊";
        FontMetrics fm = g2.getFontMetrics();
        int w = fm.stringWidth(initials);
        int h = fm.getAscent();
        g2.drawString(initials, (size - w) / 2, (size + h) / 2 - 4);

        g2.dispose();
        return new ImageIcon(image);
    }

    // Rounded panel for card effect
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
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(borderColor);
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, radius, radius);
            g2.dispose();
        }
    }
}