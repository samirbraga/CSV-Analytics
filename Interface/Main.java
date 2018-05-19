import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Main {
	
	static JFrame window = null;
	static JPanel currentPanel = null;
	
	public static void main(String[] args) throws IOException {
		System.setProperty("file.encoding", "UTF-8");

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ignored) {
			// Se o tema do Windows não poder se adicinado,
			// O tema ficará como o padrão
		}
		
		// Janela Principal
		window = new JFrame("Trabalho Final");

		// Painel inicial
		currentPanel = new JPanel();

		// Sistema de escolha de arquivo
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// Botão de upload do arquivo
		JButton uploadButton = new JButton("UPLOAD");

		// Ação de clique no Botão de Upload
		ActionListener clickListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file.setCurrentDirectory(
					new File(
						"C:\\Users\\samir.DESKTOP-QOF6N6C\\Documents\\Atividades\\tecnicas_programacao\\trab-final\\Interface\\csv"
					)
				);
				int action = file.showSaveDialog(null);
		
				if (action == 1) {
					System.exit(0);
				} else {
					File selectedFile = file.getSelectedFile();
					String filePath = selectedFile.getPath();
					String[] aux = filePath.split("\\.");
					String fileExt = aux[aux.length - 1].toLowerCase();

					if (fileExt.equals("csv")) {
						try {	
							CSVReader csvReader = new CSVReader(selectedFile);
							csvReader.parse();

							String[] header = csvReader.getHeader();
							Object[][] records = csvReader.getRecords();

							renderTable(records, header);

							JOptionPane.showMessageDialog(null, selectedFile.getPath());
						} catch (IOException err) {
							err.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "O arquivo selecionado não é um CSV.");
					}
				}
			}
		};

		uploadButton.addActionListener(clickListener);

		// uploadButton.setBounds();

		currentPanel.add(uploadButton); // Adicionando a Tabela ao Frame
		
		window.getContentPane().add(currentPanel);

		launch();
	}
	
	static void launch() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Redimensionando a janela pra 50% da largura da tela
		int screenW = new Double(screenSize.width * 0.5).intValue(); 
		// Redimensionando a janela pra 70% da altura da tela
		int screenH = new Double(screenSize.height * 0.7).intValue(); 
	
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		window.setSize(screenW, screenH);
		window.setLocationRelativeTo(null); // Centralizando a Janela
	}

	static void renderTable(Object[][] records, String[] header) {
		JTable csvTable = new JTable();
		csvTable.setModel(new DefaultTableModel(records, header));

		csvTable.setBounds(0, 20, 300, 300);

		window.getContentPane().remove(currentPanel);
		window.revalidate();
		window.repaint();

		window.getContentPane().add(csvTable); // Adicionando o painel da Tabela
	}
}
