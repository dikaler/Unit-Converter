
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UnitConverter implements ActionListener {
    private static JTextField measurementField;
    private static JButton convert;
    private static JLabel metricRes;
    private static String measurement = "Feet";

    private static String metric = "Meters";

    private static HashMap<String, String> conversions;

    public static void main(String[] args) {
        conversions = new HashMap<>();
        conversions.put("Feet", "Meters");
        conversions.put("Pounds", "Kilograms");
        conversions.put("Miles", "Kilometers");
        conversions.put("Fahrenheit", "Celsius");

        // create outer display
        JFrame display = new JFrame("Imperial to Metric Converter");
        display.setSize(350, 200);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setVisible(true);

        // create inner display
        JPanel window = new JPanel();
        window.setLayout(null);
        display.add(window);

        String[] options = {"Feet", "Pounds", "Miles", "Fahrenheit"};
        JComboBox dropdown = new JComboBox(options);
        dropdown.setBounds(10, 20, 120, 25);
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                measurement = String.valueOf(dropdown.getSelectedItem());
                metric = conversions.get(measurement);
                convert.setText("Convert to " + metric);
                //System.out.println("Metric is " + metric);
            }
        });
        window.add(dropdown);

        measurementField = new JTextField(10);
        measurementField.setBounds(140, 20, 100, 25);
        window.add(measurementField);

        convert = new JButton("Convert to " + metric);
        convert.setBounds(25, 80, 200, 25);
        convert.addActionListener(new UnitConverter());
        window.add(convert);

        metricRes = new JLabel("");
        metricRes.setBounds(10, 120, 350, 25);
        window.add(metricRes);

        display.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (measurementField.getText().isBlank() || !(isNumeric(measurementField.getText()))) {
            metricRes.setText("Please input a numeric value in the text box above.");
        } else {
            double beforeConv = Double.valueOf(measurementField.getText());
            double afterConv = 0;
            //System.out.println(measurement + " to " + metric);

            if (measurement.equals("Fahrenheit")) {
                afterConv = ((beforeConv - 32) * 5) / 9;
            }

            if (measurement.equals("Feet")) {
                afterConv = beforeConv / 3.281;
            }

            if (measurement.equals("Miles")) {
                afterConv = beforeConv * 1.609;
            }

            if (measurement.equals("Pounds")) {
                afterConv = beforeConv / 2.205;
            }


            String res = String.format("%.3f", afterConv);
            //System.out.println(String.valueOf(afterConv));
            metricRes.setText(res + " " + metric);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}