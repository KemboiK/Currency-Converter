import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;

    private final HashMap<String, Double> rates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Hardcoded exchange rates relative to USD
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.92);
        rates.put("INR", 83.25);
        rates.put("JPY", 155.72);
        rates.put("GBP", 0.79);

        String[] currencies = {"USD", "EUR", "INR", "JPY", "GBP"};

        add(new JLabel("From:"));
        fromCurrency = new JComboBox<>(currencies);
        add(fromCurrency);

        add(new JLabel("To:"));
        toCurrency = new JComboBox<>(currencies);
        add(toCurrency);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultLabel = new JLabel("Converted Amount: ");
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            double usdAmount = amount / rates.get(from);
            double converted = usdAmount * rates.get(to);

            resultLabel.setText(String.format("Converted Amount: %.2f %s", converted, to));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a numeric value.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
