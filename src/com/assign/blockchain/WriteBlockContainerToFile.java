package com.assign.blockchain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteBlockContainerToFile {
	private String filePath = "";
	private String folderName = "BlockDataStorage"; // the folder store blocks.
	private String fileName = "blockContainer.dat";
	private ArrayList<BlockUnit> blockContainer;

	public WriteBlockContainerToFile(String filePath, ArrayList<BlockUnit> blockContainer) {
		this.filePath = filePath + this.folderName;
		this.blockContainer = blockContainer;
	}
	public WriteBlockContainerToFile(String filePath) {
		this.filePath = filePath + this.folderName;
	}

	/**
	 * Create the directory if the file path does not exist.
	 */
	public void createDirectoryPath() {
		File directory = new File(this.filePath);
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("BlockContainer Folder created successfully.");
			} else {
				System.out.println("Failed to create the BlockContainer folder.");
				return;
			}
		}
	}

	/**
	 * Write the block container into a file.
	 */
	public void writeBlockContainer() {

		this.createDirectoryPath();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(this.filePath + "/" + this.fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(this.blockContainer);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException e) {
			// Handle exceptions appropriately
			e.printStackTrace();
		}

	}

	/**
	 * Read out the block container from a file.
	 * 
	 * @return
	 */
	public ArrayList<BlockUnit> readBlockContainer() {
		ArrayList<BlockUnit> blockContainer = null;

		File file = new File(this.filePath + "/" + this.fileName);
		if (file.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(this.filePath + "/" + this.fileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

				blockContainer = (ArrayList<BlockUnit>) objectInputStream.readObject();

				objectInputStream.close();
				fileInputStream.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return blockContainer;
	}
}
