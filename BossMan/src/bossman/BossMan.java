package bossman;

/* *****************************************************************************************************************************************************************
 * Copyright 2018 Grant Alexander
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * 		The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 * 
 ******************************************************************************************************************************************************************/
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
public class BossMan {

	private JFrame frmBossmanRandomizer;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BossMan window = new BossMan();
					window.frmBossmanRandomizer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BossMan() {
		initialize();
	}

	private void initialize() {
		frmBossmanRandomizer = new JFrame();
		frmBossmanRandomizer.setResizable(false);
		frmBossmanRandomizer.setTitle("BossMan Randomizer");
		frmBossmanRandomizer.setBounds(100, 100, 693, 447);
		frmBossmanRandomizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBossmanRandomizer.getContentPane().setLayout(null);
		
		JLabel lblPoorSaps = new JLabel("Poor Saps:");
		lblPoorSaps.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPoorSaps.setBounds(10, 11, 115, 22);
		frmBossmanRandomizer.getContentPane().add(lblPoorSaps);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setRows(10);
		textArea.setToolTipText("Separate users by commas!");
		textArea.setBounds(10, 41, 657, 128);
		frmBossmanRandomizer.getContentPane().add(textArea);
		
		JLabel lblOrder = new JLabel("Ordered:");
		lblOrder.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOrder.setBounds(10, 238, 115, 22);
		frmBossmanRandomizer.getContentPane().add(lblOrder);
		
		String[] selection = populateRotationSelection(100);
		JComboBox cbRotations = new JComboBox(selection);
		cbRotations.setBounds(108, 181, 84, 25);
		frmBossmanRandomizer.getContentPane().add(cbRotations);
		
		String[] formatSelection = {"Line by Line", "List", "Lottery"};
		JComboBox cbFormat = new JComboBox(formatSelection);
		cbFormat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String currentSelection = (String) cbFormat.getSelectedItem();
				if (currentSelection == "Lottery") {
					cbRotations.setEnabled(false);
				}
				else {
					if (!cbRotations.isEnabled()) {
						cbRotations.setEnabled(true);
					}
				}
			}
		});
		cbFormat.setBounds(293, 181, 168, 25);
		frmBossmanRandomizer.getContentPane().add(cbFormat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 272, 657, 124);
		frmBossmanRandomizer.getContentPane().add(scrollPane);
		
		JTextArea taOutput = new JTextArea();
		taOutput.setEditable(false);
		taOutput.setLineWrap(true);
		taOutput.setWrapStyleWord(true);
		taOutput.setRows(5000);
		scrollPane.setViewportView(taOutput);
		
		JButton btnRandomize = new JButton("Randomize!");
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Model model = new Model(textArea.getText());
				String rotationChoice = (String) cbRotations.getSelectedItem();
				String formatChoice = (String) cbFormat.getSelectedItem();
				taOutput.setText(model.generateOutput(rotationChoice, formatChoice));
			}
		});
		btnRandomize.setBounds(520, 180, 147, 23);
		frmBossmanRandomizer.getContentPane().add(btnRandomize);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 227, 657, 22);
		frmBossmanRandomizer.getContentPane().add(separator);
		
		JLabel lblRotations = new JLabel("Rotations:");
		lblRotations.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRotations.setBounds(10, 183, 115, 22);
		frmBossmanRandomizer.getContentPane().add(lblRotations);
		
		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFormat.setBounds(210, 183, 71, 22);
		frmBossmanRandomizer.getContentPane().add(lblFormat);
		
	}
	
	private String[] populateRotationSelection(int limit) {
		String[] rotations = new String[limit];
		for(int x = 1; x <= limit; x++) {
			rotations[x-1] = Integer.toString(x);
		}
		
		return rotations;
	}
}
