//import java.awt.GridLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout; //import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FloorPanelUI extends JPanel {

	FloorPanel floorPanel;
	JButton UpButton = null;
	JButton DownButton = null;
	int currentFloorNumber = 1;
	FloorPanelColor activeButtonColor = null;
	FloorPanelColor buttonColor = null;
	JTextField floorInd2 = null;
	JTextField floorInd4 = null;
	JTextField floorInd5 = null;

	int numAlarmIndicators;
	List<JButton> lstAlrmInd = new ArrayList<JButton>();

	public FloorPanelUI(FloorPanel floorPanel, FloorPanelColor buttonColor,
			FloorPanelColor activeButtonColor, int CurrentFloorNumber,
			String upText, String downText, int numAlarmIndicators) {

		this.currentFloorNumber = CurrentFloorNumber;
		this.floorPanel = floorPanel;
		this.activeButtonColor = activeButtonColor;
		this.buttonColor = buttonColor;
		this.numAlarmIndicators = numAlarmIndicators;

		setLayout(new GridBagLayout());
		JLabel label = new JLabel("FloorNumber " + CurrentFloorNumber);
		add(label);

		JLabel floorInd1 = new JLabel("Car ID");
		add(floorInd1);
		floorInd2 = new JTextField(1);
		add(floorInd2);
		JLabel floorInd3 = new JLabel("Car Type");
		add(floorInd3);
		floorInd4 = new JTextField(5);
		add(floorInd4);
		floorInd5 = new JTextField(6);
		add(floorInd5);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;

		UpButton = new JButton(upText);
		UpButton.setBackground(buttonColor.getJColor());
		UpListener upListener = new UpListener();
		UpButton.addActionListener(upListener);
		add(UpButton, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;

		DownButton = new JButton(downText);
		DownButton.setBackground(buttonColor.getJColor());
		DownListener downListener = new DownListener();
		DownButton.addActionListener(downListener);
		add(DownButton, gbc);

		for (int i = 1; i <= numAlarmIndicators; i++) {
			JButton button = new JButton("Alarm in Car" + i);
			button.setBackground(Color.ORANGE);
			button.setEnabled(false);
			gbc.gridx = i;
			gbc.gridy = 2;
			add(button, gbc);
			lstAlrmInd.add(button);
		}
	}

	void disableUpButton() {
		UpButton.setEnabled(false);

	}

	void disableDownButton() {
		DownButton.setEnabled(false);
	}

	void processedRequest() {
		UpButton.setBackground(buttonColor.getJColor());
		DownButton.setBackground(buttonColor.getJColor());

	}

	void processIndicatorRequest(int id, String type) {
		floorInd2.setText(new Integer(id).toString());
		floorInd4.setText(type);
	}

	void processStatusRequest(int status) {
		this.floorInd5.setText(new Integer(status).toString());
	}

	private class UpListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			UpButton.setBackground(activeButtonColor.getJColor());
			floorPanel.setFloorPanelStatus("Active");
			floorPanel.getFloorPanelQueueType().putFloorRequest(
					currentFloorNumber, Direction.UP);
		}
	}

	private class DownListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			DownButton.setBackground(activeButtonColor.getJColor());
			floorPanel.setFloorPanelStatus("Active");
			floorPanel.getFloorPanelQueueType().putFloorRequest(
					currentFloorNumber, Direction.DOWN);
		}
	}
	void processAlarmRequest(String status, int carId) {
		if (status.equalsIgnoreCase("on")) {
			lstAlrmInd.get(carId - 1).setBackground(Color.RED);
		}
		if (status.equalsIgnoreCase("off")) {
			for (int i = 1; i <= this.currentFloorNumber; i++)
				lstAlrmInd.get(carId - 1).setBackground(Color.ORANGE);
		}
	}
}
