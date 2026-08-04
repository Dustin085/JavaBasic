package com.example.practice;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LottoIO {
	public static void main(String[] args) {
		LottoIO lottoIO = new LottoIO();
		lottoIO.createLottoTXT(100);
		lottoIO.copyLottoFile();
		lottoIO.queryLottoTXT(20);
		lottoIO.createMultipleLottoTXT(100, 20);
		lottoIO.zipLottoFiles();
	}

	// 1. 隨機產生100筆大樂透,並將結果輸出至Lotto.txt檔
	public void createLottoTXT(int lottoCount) {
		// AutoCloseable 可以使用 try-with-resources，編譯會自動加上 finally {close()}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lotto.txt"))) { // try-with-resources
			for (int i = 0; i < lottoCount; i++) {
				writer.write(playLottoSet().toString());
				if (i != lottoCount) {
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 2. 複製題目一的Lotto_(今天日期).txt檔到其他資料夾 例:今天是2017-05-25產生Lotto_20170525.txt
	public void copyLottoFile() {
		Path source = Path.of("Lotto.txt");
		LocalDate nowLocalDate = LocalDate.now();
		// DateTimeFormatter 產生我們想要的日期格式
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		Path target = Path.of("Lotto_" + nowLocalDate.format(dateTimeFormatter) + ".txt");

		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 3. 讀取題目一的Lotto.txt檔並選擇查詢其中某一筆資料,將查詢結果縱向輸出
	public void queryLottoTXT(int lineNum) {
		String line = "";
		try {
			line = Files.lines(Path.of("Lotto.txt")).skip(lineNum - 1).findFirst().orElse(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		line = line.substring(1, line.length() - 1); // 去頭尾 []
		String[] lottoNumArr = line.split(", ");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lotto_new.txt"))) {
			writer.write("第 " + lineNum + " 大樂透");
			writer.newLine();
			for (String lottoNum : lottoNumArr) {
				writer.write(lottoNum);
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 4. 產生20個有100筆的大樂透輸出Lotto_(筆數).txt
	public void createMultipleLottoTXT(int lottoCount, int fileCount) {
		for (int fNum = 1; fNum <= fileCount; fNum++) {
			String fNumStr = String.valueOf(fNum);
			if (fNumStr.length() == 1) {
				fNumStr = "0" + fNumStr;
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Lotto_" + fNumStr + ".txt"))) { // try-with-resources
				for (int i = 0; i < lottoCount; i++) {
					writer.write(playLottoSet().toString());
					if (i != lottoCount) {
						writer.newLine();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 5. 將題目四的20個txt檔壓縮輸出一個zip檔
	public void zipLottoFiles() {
		try (FileOutputStream fos = new FileOutputStream("Lotto.zip"); // Stream 代表資料流動的管道，output stream 代表 程式把資料寫出去
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ZipOutputStream zos = new ZipOutputStream(bos);) {
			for (int fNum = 1; fNum <= 20; fNum++) {
				String fNumStr = String.valueOf(fNum);
				if (fNumStr.length() == 1) {
					fNumStr = "0" + fNumStr;
				}
				String fileName = "Lotto_" + fNumStr + ".txt";

				// 建立 zip 項目
				ZipEntry entry = new ZipEntry(fileName);
				// 開始寫入
				zos.putNextEntry(entry);

				// 讀寫檔案
				try (FileInputStream fis = new FileInputStream(new File(fileName))) {// input stream 代表把資料寫入程式

					// 建立 buffer 暫存區，避免一次讀取過多資料導致記憶體不足 OutOfMemoryError
					byte[] buffer = new byte[1024];
					// 紀錄讀取到多少 byte
					int length;

					// 每次讀取最多 buffer.length 的資料，並且一次一次地將資料寫入 ZIP 檔案
					while ((length = fis.read(buffer)) > 0) {// read 會回傳這次讀取進 buffer 的 bytes 總數，檔案讀取完畢時回傳 -1
						zos.write(buffer, 0, length);// 參數一 => buffer, 參數二 => offset, 參數三 => 要寫入的長度
						// 要注意， buffer 始終不會被清空，所以需要紀錄這次寫入的 byte 長度 length，避免最後一次寫入時寫進了上個 buffer 殘留的資料
					}
				}

				// 結束這個 zip 項目
				zos.closeEntry();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TreeSet<String> playLottoSet() {
		// TreeSet 預設按照自然順序排列
		TreeSet<String> treeSet = new TreeSet<String>();
		while (treeSet.size() < 6) {
			int ranNum = randomInt(1, 49);
			String ranNumStr = String.valueOf(ranNum);
			if (ranNumStr.length() == 1) {
				ranNumStr = "0" + ranNumStr;
			}
			// 無須判斷是否重複，嘗試往 Set 裡面加入重複的元素時，Set 不會引發錯誤，也不會將重複的元素加入
			treeSet.add(ranNumStr);
		}
		return treeSet;
	}

	private int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
