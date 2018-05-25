import javax.swing.*;
import javax.swing.table.*;
import javax.imageio.*;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.Image;
import java.awt.image.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.io.*;

import java.util.*;

public class Main {

	static JFrame window = null;
	static JPanel currentPanel = null;
	static File contentDir = new File("content/");

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
		currentPanel.setLayout(new GridBagLayout());

		// Painel de recebimento do arquivo
		CustomBGPanel droppablePanel = new CustomBGPanel();
		droppablePanel.setLayout(new GridBagLayout());
		droppablePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Painel textual
		JPanel textPanel = new JPanel(new GridBagLayout());
		JLabel panelTitle = new JLabel(
			new String((
			"<html>" +
				"<span style='font-size: 24px'>" +
					"Arraste seu arquivo <br>" +
					"<strong style='font-size: 28px'>.CSV</strong> " + 
					"ao lado" +
				"</span><br><br>" +
				"ou clique para procurá-lo" +
			"</html>").getBytes(), "UTF-8")
		);
		panelTitle.setFont(new Font("Raleway", Font.PLAIN, 18));
		panelTitle.setForeground(new Color(120, 120, 120, 1));
		textPanel.add(panelTitle);

		contentDir.mkdirs();
		File bgImageFile = new File(contentDir, "poly-wallpapers.jpg");
		Image bgImage = ImageIO.read(bgImageFile);
		droppablePanel.setImageBG(bgImage);

		File dropIconFile = new File(contentDir, "drop-file-icon.png");
		BufferedImage dropIconImage = ImageIO.read(dropIconFile);
		Image dropIconAux = ImageIO.read(dropIconFile);
		ImageIcon dropIcon = new ImageIcon(dropIconAux);
		int iconX = new Double(droppablePanel.getWidth() / 2 - dropIconImage.getWidth() / 2).intValue();
		int iconY = new Double(droppablePanel.getHeight() / 2 - dropIconImage.getHeight() / 2).intValue();
		JLabel dropIconLabel = new JLabel(dropIcon);
		dropIconLabel.setBounds(iconX, iconY, 100, 100);


		// Sistema de escolha de arquivo
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);

		DropTarget dropHandle = new DropTarget() {
			private static final long serialVersionUID = 1L; // unique id

			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);

					setCSVFile(droppedFiles.get(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		droppablePanel.setDropTarget(dropHandle);

		// Ação de clique no Botão de Upload
		MouseListener clickListener = new MouseListener() {
			public void mouseClicked(MouseEvent event) {
				file.setCurrentDirectory(new File(
						"C:\\Users\\samir.DESKTOP-QOF6N6C\\Documents\\Atividades\\tecnicas_programacao\\trab-final\\Interface\\csv"));
				int action = file.showSaveDialog(null);

				if (action == 1) {
					System.exit(0);
				} else {
					File selectedFile = file.getSelectedFile();
					setCSVFile(selectedFile);
				}
			} 
			public void mouseEntered(MouseEvent event) {} 
			public void mouseExited(MouseEvent event) {} 
			public void mousePressed(MouseEvent event) {} 
			public void mouseReleased(MouseEvent event) {}
		};

		droppablePanel.addMouseListener(clickListener);

		// uploadButton.setBounds();

		//currentPanel.add(uploadButton);
		droppablePanel.add(dropIconLabel); // Adicionando o ícone de arquivo
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty = 1;
		cons.weightx = 0.2;
		currentPanel.add(textPanel, cons); // Adicionando o texto de título da janel

		cons.weightx = 0.8;
		currentPanel.add(droppablePanel, cons); // Adicionado o painel de recebimento do arquivo

		window.getContentPane().add(currentPanel);

		launch();
	}

	static void setCSVFile(File selectedFile) {
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

	static void setWindowSize(double xrate, double yrate) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Redimensionando a janela pra 50% da largura da tela
		int screenW = new Double(screenSize.width * xrate).intValue();
		// Redimensionando a janela pra 70% da altura da tela
		int screenH = new Double(screenSize.height * yrate).intValue();

		window.setSize(screenW, screenH);
		window.setLocationRelativeTo(null); // Centralizando a Janela
	}

	static void launch() {
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		setWindowSize(0.5, 0.7);
		window.setLocationRelativeTo(null); // Centralizando a Janela
	}

	static void renderTable(Object[][] records, String[] header) {
		window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		JTable csvTable = new JTable();
		csvTable.setModel(new DefaultTableModel(records, header));

		csvTable.setBounds(0, 20, 300, 300);

		window.getContentPane().remove(currentPanel);
		window.revalidate();
		window.repaint();
		window.getContentPane().add(csvTable); // Adicionando a tabela ao Painel atual
		window.revalidate();
	}
}
